package io.xunyss.ssing.web.configuration.context;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [context-datasource.xml]
 * 
 * @author XUNYSS
 */
@Configuration
public class DataSourceConfig {
	
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("");
		dataSource.setUrl("");
		dataSource.setUsername("");
		dataSource.setPassword("");
		dataSource.setValidationQuery("select 1 from dual");
		
		return dataSource;
	}
}
