package io.xunyss.ssing.web.configuration.webmvc;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * [dispatcher-servlet.xml]
 * 
 * @author XUNYSS
 */
@Configuration
@ComponentScan(basePackages="io.xunyss.ssing", useDefaultFilters=false, includeFilters={@Filter(Controller.class)})
@EnableWebMvc	// <mvc:annotation-driven/>
public class DispatcherConfig extends WebMvcConfigurerAdapter {

	/**
	 * @EnableWebMvc (<mvc:annotation-driven/>) 사용시 디폴트로 등록되는 빈
	 * RequestMappingHandlerMapping, RequestMappingHandlerAdapter
	 * 그밖에 기타 등등
	 */
//	@Bean
//	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//		RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
//		requestMappingHandlerMapping.setOrder(0);
//		
//		return requestMappingHandlerMapping;
//	}
//	@Bean
//	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//		return new RequestMappingHandlerAdapter();
//	}
	
	/**
	 * <mvc:default-servlet-handler/>
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		Properties mappings = new Properties();
		mappings.put("/**/*.do", "urlFilenameViewController");
		
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
	//	simpleUrlHandlerMapping.setOrder(1);
		simpleUrlHandlerMapping.setMappings(mappings);
		
		return simpleUrlHandlerMapping;
	}
	@Bean
	public UrlFilenameViewController urlFilenameViewController() {
		return new UrlFilenameViewController();
	}
	@Bean
	public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter() {
		return new SimpleControllerHandlerAdapter();
	}
	
	
	@Bean
	public ViewResolver beanNameViewResolver() {
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(0);
		
		return beanNameViewResolver;
	}
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
		final String VIEW_RESOLVER_PREFIX = "/WEB-INF/view/";
		final String VIEW_RESOLVER_SUFFIX = ".jsp";
		
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setOrder(1);
		internalResourceViewResolver.setViewClass(JstlView.class);
		internalResourceViewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		internalResourceViewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
		
		return internalResourceViewResolver;
	}
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
	}
}
