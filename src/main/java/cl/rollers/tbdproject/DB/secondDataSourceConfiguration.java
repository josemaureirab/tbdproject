package cl.rollers.tbdproject.DB;

import cl.rollers.tbdproject.SQL.models.Task;
import cl.rollers.tbdproject.SQL.models.User;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "cl.rollers.tbdproject.SQL.dao",
    entityManagerFactoryRef = "secondEntityManagerFactory",
    transactionManagerRef= "secondTransactionManager")
public class secondDataSourceConfiguration{

  @Bean
  @ConfigurationProperties("app.datasource.second")
  public DataSourceProperties secondDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @ConfigurationProperties("app.datasource.second.configuration")
  public DataSource secondDataSource() {
    return secondDataSourceProperties().initializeDataSourceBuilder()
        .type(BasicDataSource.class).build();
  }

  /* EntityManagerFactories */
  @Bean(name = "secondEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(secondDataSource())
        .packages(User.class, Voluntary.class, Task.class)
        .build();
  }

  /* TransactionManager  */
  @Bean
  public PlatformTransactionManager secondTransactionManager(
      final @Qualifier("secondEntityManagerFactory") LocalContainerEntityManagerFactoryBean secondEntityManagerFactory) {
    return new JpaTransactionManager(secondEntityManagerFactory.getObject());
  }

  @Bean(name = "secondDSJdbcTemplate")
  public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDataSource") DataSource secondDs) {
    return new JdbcTemplate(secondDs);
  }
}
