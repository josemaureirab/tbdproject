package cl.rollers.tbdproject.DB;

import cl.rollers.tbdproject.SQL.models.Task;
import cl.rollers.tbdproject.SQL.models.User;
import cl.rollers.tbdproject.SQL.models.Voluntary;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    entityManagerFactoryRef = "firstEntityManagerFactory",
    transactionManagerRef= "firstTransactionManager")
public class firstDataSourceConfiguration{
  @Bean
  @Primary
  @ConfigurationProperties("app.datasource.first")
  public DataSourceProperties firstDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("app.datasource.first.configuration")
  public DataSource firstDataSource() {
    return firstDataSourceProperties().initializeDataSourceBuilder()
        .type(HikariDataSource.class).build();
  }

  /* EntityManagerFactories */
  @Primary
  @Bean(name = "firstEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(firstDataSource())
        .packages(User.class, Voluntary.class, Task.class)
        .build();
  }

  /* TransactionManager  */
  @Bean
  @Primary
  public PlatformTransactionManager firstTransactionManager(
      final @Qualifier("firstEntityManagerFactory") LocalContainerEntityManagerFactoryBean firstEntityManagerFactory) {
    return new JpaTransactionManager(firstEntityManagerFactory.getObject());
  }

  @Bean(name = "firstDSJdbcTemplate")
  public JdbcTemplate firstJdbcTemplate(@Qualifier("firstDataSource") DataSource firstDs) {
    return new JdbcTemplate(firstDs);
  }

}