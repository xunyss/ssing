package com.xunyss.ssing.web.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.xunyss.ssing.web.configuration.context.CommonConfig;
import com.xunyss.ssing.web.configuration.context.DataSourceConfig;
import com.xunyss.ssing.web.configuration.webmvc.DispatcherConfig;

/**
 * [web.xml]
 * 
 * @author xuny
 */
public class WebAppInitializer implements WebApplicationInitializer {

	static final int SERVLET_LOAD_ON_STARTUP = 1;
	static final int SERVLET_LOAD_LAZILY = -1;
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		/*
		 * 
		 */
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(CommonConfig.class, DataSourceConfig.class);
		
		/*
		 * 
		 */
		servletContext.addListener(new ContextLoaderListener(applicationContext));
		
		
		/*
		 * 
		 */
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(DispatcherConfig.class);
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(SERVLET_LOAD_ON_STARTUP);
		
		
		/*
		 * 
		 */
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
