package cl.rollers.tbdproject.DB;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.sql2o.Sql2o;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseContext implements TransactionManagementConfigurer {

  @Bean
  public DataSource dataSource() {
    final BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl("jdbc:postgresql://localhost:5432/tbd1");
    dataSource.setUsername("postgres");
    dataSource.setPassword("secret");
    return dataSource;
  }

  @Bean(name="transactionManager")
  @Override
  public PlatformTransactionManager annotationDrivenTransactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

  @Bean
  public Sql2o sql2o() {
    return new Sql2o(dataSource());
  }

}