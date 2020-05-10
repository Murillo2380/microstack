package microstack.springservices;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import util.DbUtil;
import util.JdbcPoolProps;

@Configuration
@ComponentScan(basePackageClasses = AppConfig.class)
@EnableTransactionManagement
public class AppConfig {
    @Bean
    DataSourceTransactionManager txManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }

    @Bean
    DataSource dataSource(Environment env) {
        return DbUtil.createDataSource(
            JdbcPoolProps.builder()
            .withPoolName("MICROSTACK")
            .withDriverClassName(env.getRequiredProperty("jdbc.driverClassName"))
            .withUrl(env.getRequiredProperty("jdbc.url"))
            .withUsername(env.getRequiredProperty("jdbc.username"))
            .withPassword("jdbc.password")
            .withMaximumPoolSize(env.getRequiredProperty("dbpool.maxConnectionsPerPartition", Integer.class))
            .withMinimumIdle(env.getRequiredProperty("dbpool.acquireIncrement", Integer.class))
            .withStatementsCacheSize(env.getRequiredProperty("dbpool.statementsCacheSize", Integer.class))
            .withConnectionTestStatement(env.getRequiredProperty("dbpool.connectionTestStatement"))
            .withCloseConnectionWatch(env.getRequiredProperty("dbpool.closeConnectionWatch", Boolean.class))
            .build()
        );
    }

    @Bean
    public DSLContext dslContext(
        DataSource dataSource,
        @Value("${dbpool.logStatementsEnabled}") boolean logStatementsEnabled) {
        return new DefaultDSLContext(
            DbUtil.createSpringAwareDSLContext(
                dataSource,
                SQLDialect.H2,
                logStatementsEnabled));
    }

}
