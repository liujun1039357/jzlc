package com.zl.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zl.interceptor.AdminInterceptor;


@Configuration
public class MvcConfig  extends WebMvcConfigurerAdapter{
	
	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter webmvc = new WebMvcConfigurerAdapter() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				// super.addInterceptors(registry);
				registry.addInterceptor(new AdminInterceptor());
			}
		};
		return webmvc;
	}
}
