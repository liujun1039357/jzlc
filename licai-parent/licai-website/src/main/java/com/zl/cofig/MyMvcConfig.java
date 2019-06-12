package com.zl.cofig;

import java.lang.Override;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zl.interceptor.CheckLoginInterceptor;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				// super.addInterceptors(registry);
				registry.addInterceptor(new CheckLoginInterceptor());
			}
		};
		return adapter;
	}

	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  registry.addResourceHandler("/upload/**").addResourceLocations("file:d:/upload/");
	  super.addResourceHandlers(registry);
	 }
}
