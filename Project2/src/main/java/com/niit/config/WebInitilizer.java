package com.niit.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//import org.springframework.web.servlet.mvc.support.AbstractAnnotatiuonConfigDispatcherServletInitilizer;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
public class WebInitilizer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		System.out.println("root config");
		return new Class[] {ApplicationContextConfig.class,WebSocketConfig.class};
	}

	
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		System.out.println("servlet config");
		return new Class[] { WebAppConfig.class};
	}


	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	
	
}

