/**
 * 
 */
package com.d2l2c.salary.management.data.spring.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dayanlazare
 *
 */
@Configuration
@PropertySource("classpath:salary-db.properties")
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "com.d2l2c.salary.management.data.dao", 
		entityManagerFactoryRef = "salaryEntityManager", 
		transactionManagerRef = "salaryTransactionManager")
@ComponentScans(value = { @ComponentScan("com.d2l2c.salary.management.data.service") })
public class SalaryJPAConfig {

	@Autowired
	private Environment environment;

	@Bean
	public LocalContainerEntityManagerFactoryBean salaryEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPackagesToScan(new String[] { "com.d2l2c.salary.management.data.bean" });
		em.setPersistenceUnitName("salaryPersistenceUnit");
		em.setDataSource(salaryDataSource());

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.batch.size", environment.getRequiredProperty("hibernate.batch.size"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public DataSource salaryDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("salary.db.driver"));
		dataSource.setUrl(environment.getRequiredProperty("salary.db.url"));
		dataSource.setUsername(environment.getRequiredProperty("salary.db.username"));
		dataSource.setPassword(environment.getRequiredProperty("salary.db.password"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager salaryTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(salaryEntityManager().getObject());
		return transactionManager;
	}

}
