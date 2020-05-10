package util;

import java.util.Optional;

import javax.annotation.Generated;

/**
 *  initializationFailTimeout: {@link com.zaxxer.hikari.HikariConfig#setInitializationFailTimeout}
 */
public class JdbcPoolProps {
    private final String poolName;
    private final String driverClassName;
    private final String url;
    private final String username;
    private final String password;
    private final int maximumPoolSize;
    private final int minimumIdle;
    private final int statementsCacheSize;
    private final String connectionTestStatement;
    private final boolean closeConnectionWatch;
    private final Optional<Long> initializationFailTimeout;

    @Generated("SparkTools")
    private JdbcPoolProps(Builder builder) {
        this.poolName = builder.poolName;
        this.driverClassName = builder.driverClassName;
        this.url = builder.url;
        this.username = builder.username;
        this.password = builder.password;
        this.maximumPoolSize = builder.maximumPoolSize;
        this.minimumIdle = builder.minimumIdle;
        this.statementsCacheSize = builder.statementsCacheSize;
        this.connectionTestStatement = builder.connectionTestStatement;
        this.closeConnectionWatch = builder.closeConnectionWatch;
        this.initializationFailTimeout = builder.initializationFailTimeout;
    }

    public String getPoolName() {
        return poolName;
    }
    public String getDriverClassName() {
        return driverClassName;
    }
    public String getUrl() {
        return url;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }
    public int getMinimumIdle() {
        return minimumIdle;
    }
    public int getStatementsCacheSize() {
        return statementsCacheSize;
    }
    public String getConnectionTestStatement() {
        return connectionTestStatement;
    }
    public boolean isCloseConnectionWatch() {
        return closeConnectionWatch;
    }
    public Optional<Long> getInitializationFailTimeout() {
        return initializationFailTimeout;
    }
    /**
     * Creates builder to build {@link JdbcPoolProps}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static IPoolNameStage builder() {
        return new Builder();
    }
    @Generated("SparkTools")
    public interface IPoolNameStage {
        IDriverClassNameStage withPoolName(String poolName);
    }
    @Generated("SparkTools")
    public interface IDriverClassNameStage {
        IUrlStage withDriverClassName(String driverClassName);
    }
    @Generated("SparkTools")
    public interface IUrlStage {
        IUsernameStage withUrl(String url);
    }
    @Generated("SparkTools")
    public interface IUsernameStage {
        IPasswordStage withUsername(String username);
    }
    @Generated("SparkTools")
    public interface IPasswordStage {
        IMaximumPoolSizeStage withPassword(String password);
    }
    @Generated("SparkTools")
    public interface IMaximumPoolSizeStage {
        IMinimumIdleStage withMaximumPoolSize(int maximumPoolSize);
    }
    @Generated("SparkTools")
    public interface IMinimumIdleStage {
        IStatementsCacheSizeStage withMinimumIdle(int minimumIdle);
    }
    @Generated("SparkTools")
    public interface IStatementsCacheSizeStage {
        IConnectionTestStatementStage withStatementsCacheSize(
                int statementsCacheSize);
    }
    @Generated("SparkTools")
    public interface IConnectionTestStatementStage {
        ICloseConnectionWatchStage withConnectionTestStatement(
                String connectionTestStatement);
    }
    @Generated("SparkTools")
    public interface ICloseConnectionWatchStage {
        IBuildStage withCloseConnectionWatch(
                boolean closeConnectionWatch);
    }
    @Generated("SparkTools")
    public interface IBuildStage {
        IBuildStage withInitializationFailTimeout(
                Optional<Long> initializationFailTimeout);

        JdbcPoolProps build();
    }
    /**
     * Builder to build {@link JdbcPoolProps}.
     */
    @Generated("SparkTools")
    public static final class Builder implements IPoolNameStage,
            IDriverClassNameStage, IUrlStage, IUsernameStage, IPasswordStage,
            IMaximumPoolSizeStage, IMinimumIdleStage, IStatementsCacheSizeStage,
            IConnectionTestStatementStage, ICloseConnectionWatchStage,
            IBuildStage {
        private String poolName;
        private String driverClassName;
        private String url;
        private String username;
        private String password;
        private int maximumPoolSize;
        private int minimumIdle;
        private int statementsCacheSize;
        private String connectionTestStatement;
        private boolean closeConnectionWatch;
        private Optional<Long> initializationFailTimeout = Optional.empty();

        private Builder() {
        }

        @Override
        public IDriverClassNameStage withPoolName(String poolName) {
            this.poolName = poolName;
            return this;
        }

        @Override
        public IUrlStage withDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
            return this;
        }

        @Override
        public IUsernameStage withUrl(String url) {
            this.url = url;
            return this;
        }

        @Override
        public IPasswordStage withUsername(String username) {
            this.username = username;
            return this;
        }

        @Override
        public IMaximumPoolSizeStage withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public IMinimumIdleStage withMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
            return this;
        }

        @Override
        public IStatementsCacheSizeStage withMinimumIdle(int minimumIdle) {
            this.minimumIdle = minimumIdle;
            return this;
        }

        @Override
        public IConnectionTestStatementStage withStatementsCacheSize(
                int statementsCacheSize) {
            this.statementsCacheSize = statementsCacheSize;
            return this;
        }

        @Override
        public ICloseConnectionWatchStage withConnectionTestStatement(
                String connectionTestStatement) {
            this.connectionTestStatement = connectionTestStatement;
            return this;
        }

        @Override
        public IBuildStage withCloseConnectionWatch(
                boolean closeConnectionWatch) {
            this.closeConnectionWatch = closeConnectionWatch;
            return this;
        }

        @Override
        public IBuildStage withInitializationFailTimeout(
                Optional<Long> initializationFailTimeout) {
            this.initializationFailTimeout = initializationFailTimeout;
            return this;
        }

        @Override
        public JdbcPoolProps build() {
            return new JdbcPoolProps(this);
        }
    }
}
