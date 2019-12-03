package com.assignment.customer.config.db;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * 
 * @author ricardomorais
 *
 *         This class is responsible for configuring the data repositories used
 *         in the app.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.assignment.customer.repository")
public class DBConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(DBConfiguration.class);
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("driverClassName"));
		dataSource.setUrl(env.getProperty("url"));
		
		if (logger.isTraceEnabled()) {
			logger.trace("URL used to connect into database: " + env.getProperty("url"));
		}
		
		if (env.getProperty("user") != null && !env.getProperty("user").isEmpty()) {
			dataSource.setUsername(env.getProperty("user"));
		}
		
		if (env.getProperty("password") != null && !env.getProperty("password").isEmpty()) {
			dataSource.setPassword(env.getProperty("password"));
		}

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.assignment.customer.model" });
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());

		return em;
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();

		if (env.getProperty("hibernate.hbm2ddl.auto") != null) {
			hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		}

		if (env.getProperty("hibernate.dialect") != null) {
			hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		}

		if (env.getProperty("hibernate.show_sql") != null) {
			hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		}

		return hibernateProperties;
	}

}

@Configuration
@Profile("sqlite")
@PropertySource("classpath:persistence-sqlite.properties")
class SqliteConfig {

}
