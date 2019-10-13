package cl.rollers.tbdproject.DB;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = "cl.rollers.tbdproject.SQL.firstDataSource.dao",
    entityManagerFactoryRef = "firstEntityManagerFactory",
    transactionManagerRef = "firstTransactionManager"
)
public class FirstDataSourceConfiguration {

  @Autowired
  private Environment env;

  @Bean
  @Primary
  @ConfigurationProperties(prefix="datasource.first")
  public DataSourceProperties firstDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  public DataSource firstDataSource() {
    DataSourceProperties primaryDataSourceProperties = firstDataSourceProperties();
    return DataSourceBuilder.create()
        .driverClassName(primaryDataSourceProperties.getDriverClassName())
        .url(primaryDataSourceProperties.getUrl())
        .username(primaryDataSourceProperties.getUsername())
        .password(primaryDataSourceProperties.getPassword())
        .build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager firstTransactionManager()
  {
    EntityManagerFactory factory = firstEntityManagerFactory().getObject();
    return new JpaTransactionManager(factory);
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory()
  {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(firstDataSource());
    factory.setPackagesToScan(new String[]{"cl.rollers.tbdproject.SQL.firstDataSource.models"});
    factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
    jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
    factory.setJpaProperties(jpaProperties);

    return factory;

  }

  @Bean
  @Primary
  public DataSourceInitializer firstDataSourceInitializer()
  {
    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(firstDataSource());
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    // databasePopulator.addScript(new ClassPathResource("first-data.sql"));
    dataSourceInitializer.setDatabasePopulator(databasePopulator);
    dataSourceInitializer.setEnabled(env.getProperty("datasource.first.initialize", Boolean.class, false));
    return dataSourceInitializer;
  }

}
