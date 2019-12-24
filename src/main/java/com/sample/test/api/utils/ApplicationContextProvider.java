package com.sample.test.api.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : ApplicationContextProvider.java
 * @description : Clase de utileria utilizada para poder utilizar el contexto de Spring.
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		applicationContext = appContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
