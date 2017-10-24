/**
 * 
 */
package com.d2l2c.salary.management.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author dayanlazare
 *
 */
@Configuration
@PropertySource("classpath:salary-web.properties")
public class PropertiesUtil {

	@Autowired
	private Environment environment;

	public String getValue(String key) {
		return environment.getProperty(key);
	}

}
