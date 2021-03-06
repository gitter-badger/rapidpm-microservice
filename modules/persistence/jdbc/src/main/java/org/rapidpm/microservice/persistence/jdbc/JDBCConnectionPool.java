package org.rapidpm.microservice.persistence.jdbc;

import com.zaxxer.hikari.HikariDataSource;

import javax.annotation.Nonnull;

/**
 * Created by svenruppert on 13.07.15.
 */
public class JDBCConnectionPool {

  private String poolname;

  private String jdbcURL;
  private String username;
  private String passwd;

  private boolean autoCommit;
  private String sqlInit;
  private String sqlTest;

  private HikariDataSource dataSource;

  public void connect(){
    dataSource = new HikariDataSource();
//    dataSource.setJdbcUrl("jdbc:hsqldb:hsql://localhost/iva");
//    dataSource.setUsername("sa");

    dataSource.setJdbcUrl(jdbcURL);
    dataSource.setUsername(username);
    dataSource.setPoolName(poolname);
    dataSource.setAutoCommit(autoCommit);

    if(passwd != null)  dataSource.setPassword(passwd);
    if(sqlInit != null) dataSource.setConnectionInitSql(sqlInit);
    if(sqlTest!= null)  dataSource.setConnectionTestQuery(sqlTest);
  }

  public void close(){
    dataSource.shutdown();
  }


  private JDBCConnectionPool(final Builder builder) {
    poolname = builder.poolname;
    jdbcURL = builder.jdbcURL;
    username = builder.username;
    passwd = builder.passwd;
    autoCommit = builder.autoCommit;
    sqlInit = builder.sqlInit;
    sqlTest = builder.sqlTest;
  }

  public static Builder newBuilder() {
    return new Builder();
  }


  public static final class Builder extends NestedBui{
    private String poolname;
    private String jdbcURL;
    private String username;
    private String passwd;
    private boolean autoCommit;
    private String sqlInit;
    private String sqlTest;
    private HikariDataSource dataSource;

    private Builder() {
    }

    @Nonnull
    public Builder withPoolname(@Nonnull final String poolname) {
      this.poolname = poolname;
      return this;
    }

    @Nonnull
    public Builder withJdbcURL(@Nonnull final String jdbcURL) {
      this.jdbcURL = jdbcURL;
      return this;
    }

    @Nonnull
    public Builder withUsername(@Nonnull final String username) {
      this.username = username;
      return this;
    }

    @Nonnull
    public Builder withPasswd(@Nonnull final String passwd) {
      this.passwd = passwd;
      return this;
    }

    @Nonnull
    public Builder withAutoCommit(final boolean autoCommit) {
      this.autoCommit = autoCommit;
      return this;
    }

    @Nonnull
    public Builder withSqlInit(@Nonnull final String sqlInit) {
      this.sqlInit = sqlInit;
      return this;
    }

    @Nonnull
    public Builder withSqlTest(@Nonnull final String sqlTest) {
      this.sqlTest = sqlTest;
      return this;
    }

    @Nonnull
    public JDBCConnectionPool build() {
      return new JDBCConnectionPool(this);
    }
  }
}
