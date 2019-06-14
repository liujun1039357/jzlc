package com.zl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zl.util.CheckLogin;
import com.zl.util.UserContext;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception{
		// 判断handler使用时Controller中一个处理请求的方法
		if (handler instanceof HandlerMethod) {
			// 强转
			HandlerMethod hm = (HandlerMethod) handler;
			// 获取处理请求方法上的注解
			CheckLogin cl = hm.getMethodAnnotation(CheckLogin.class);
			// 如果有该注解,且用户信息为空,则拦截请求,重定向到登录页面
			if (cl != null && UserContext.getCurrent() == null) {
				request.getRequestDispatcher("/login.html").forward(request, response);
				return false;
		}
	}
		// 否则放行
				return super.preHandle(request, response, handler);
}
}
