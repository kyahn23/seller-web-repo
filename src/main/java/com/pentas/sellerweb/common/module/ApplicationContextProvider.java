package com.pentas.sellerweb.common.module;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware{
	
	private static ApplicationContext ctx = null;
	
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		ApplicationContextProvider.ctx = ctx;
	}

}
