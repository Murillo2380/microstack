package microstack.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.jooq.ConnectionProvider;
import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListener;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DbUtil {
    private static final Logger log = LoggerFactory.getLogger(DbUtil.class);

    private DbUtil() {
        throw new AssertionError("Instantiating utility class.");
    }

    public static DefaultConfiguration createSpringAwareDSLContext(
            DataSource dataSource,
            SQLDialect dialect,
            boolean logStatementsEnabled) {
        /*
         * Usamos este para evitar un bug de JOOQ que hace cache de la conexión
         */
        class SpringTransactionConnectionProvider implements ConnectionProvider {
            private final DataSource ds;

            public SpringTransactionConnectionProvider(DataSource ds) {
                this.ds = ds;
            }

            @Override
            public Connection acquire() {
                try {
                    return DataSourceUtils.doGetConnection(ds);
                } catch (SQLException e) {
                    throw new DataAccessException("Error getting connection from data source " + ds, e);
                }
            }

            @Override
            public void release(Connection conn) {
                try {
                    DataSourceUtils.doReleaseConnection(conn, ds);
                } catch (SQLException e) {
                    throw new DataAccessException("Error closing connection " + conn, e);
                }
            }
        }

        class SpringExceptionTranslationExecuteListener extends DefaultExecuteListener {
            private static final long serialVersionUID = 1L;

            @Override
            public void exception(ExecuteContext ctx) {
                String name = ctx.configuration().dialect().getName();

                /* Prefer product name, if available. */
                SQLExceptionTranslator translator = name != null
                    ? new SQLErrorCodeSQLExceptionTranslator(name)
                    : new SQLStateSQLExceptionTranslator();

                ctx.exception(
                    translator
                        .translate("jOOQ", ctx.sql(), ctx.sqlException()));
            }
        }

        SpringTransactionConnectionProvider stcp = new SpringTransactionConnectionProvider(dataSource);
        DefaultConfiguration conf = new DefaultConfiguration();
        conf.set(stcp);
        conf.set(dialect);
        conf.set(new Settings()
            .withExecuteLogging(logStatementsEnabled)
            .withRenderFormatted(true));
        conf.set(new DefaultExecuteListenerProvider(
            new SpringExceptionTranslationExecuteListener()));
        return conf;
    }

    public static DataSource createDataSource(JdbcPoolProps jdbcPoolProps) {
        log.debug(
            "Url de conexión a la BBDD para {}: {}",
            jdbcPoolProps.getPoolName(),
            jdbcPoolProps.getUrl()
        );

        HikariConfig config = new HikariConfig();
        config.setPoolName(jdbcPoolProps.getPoolName());
        config.setDriverClassName(jdbcPoolProps.getDriverClassName());
        config.setJdbcUrl(jdbcPoolProps.getUrl());
        config.setUsername(jdbcPoolProps.getUsername());
        if (StringUtils.isNoneEmpty(jdbcPoolProps.getPassword())) {
            config.setPassword(jdbcPoolProps.getPassword());
        }
        config.setConnectionTimeout(4000); // millis
        config.setMinimumIdle(jdbcPoolProps.getMinimumIdle());
        config.setConnectionTestQuery(jdbcPoolProps.getConnectionTestStatement());
        // Ver https://github.com/brettwooldridge/HikariCP/wiki/About-Pool-Sizing usar el número de cores como base
        config.setMaximumPoolSize(jdbcPoolProps.getMaximumPoolSize());
        config.setAutoCommit(false);
        if (jdbcPoolProps.isCloseConnectionWatch()) {
            config.setLeakDetectionThreshold(10000L); // millis
        }
        jdbcPoolProps.getInitializationFailTimeout().ifPresent(config::setInitializationFailTimeout);

        HikariDataSource ds = new HikariDataSource(config);
        // Usamos este proxy para que JOOQ use las transacciones de Spring
        LazyConnectionDataSourceProxy lcds = new LazyConnectionDataSourceProxy();
        lcds.setDefaultAutoCommit(false);
        lcds.setDefaultTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        lcds.setTargetDataSource(ds);
        return lcds;
    }

}
