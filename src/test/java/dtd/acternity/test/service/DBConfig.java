package dtd.acternity.test.service;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "dtd.acternity.service.db.repository", entityManagerFactoryRef="entityManagerFactory")
@ComponentScan(basePackages = { "dtd.acternity" })
@EntityScan(basePackages={"dtd.acternity.service.model"})
public class DBConfig {
	private static String PROP_DB_DRIVER_CLASS = "spring.database.driverClassName";
	private static String PROP_DB_URL = "spring.datasource.url";
	private static String PROP_DB_USER = "spring.datasource.username";
	private static String PROP_DB_PASS = "spring.datasource.password";

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(env.getProperty(PROP_DB_DRIVER_CLASS));
	      dataSource.setUrl(env.getProperty(PROP_DB_URL));
	      dataSource.setUsername(env.getProperty(PROP_DB_USER));
	      dataSource.setPassword(env.getProperty(PROP_DB_PASS));
	      return (DataSource) dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan("dtd.acternity.service.model");


	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
//	      em.setJpaProperties(additionalProperties());
	      
	      return em;
	}
}
