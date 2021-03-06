package io.xunyss.ssing.web.configuration.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;

/**
 * [context-common.xml]
 * 
 * @author XUNYSS
 */
@Configuration
@ComponentScan(basePackages="io.xunyss.ssing", excludeFilters={@Filter(Controller.class), @Filter(Configuration.class)})
public class CommonConfig {
	
	/**
	 * <bean id="antPathMater" class="org.springframework.util.AntPathMatcher"/>
	 */
	@Bean
	public AntPathMatcher antPathMater() {
		return new AntPathMatcher();
	}
}
