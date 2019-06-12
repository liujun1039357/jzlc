package com.zl.exception;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常信息显示处理
 * @author Administrator
 *
 */
@ControllerAdvice
public class JZLCExceptionHandle {
	
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, HttpServletRequest request) {
		e.printStackTrace();
		request.setAttribute("javax.servlet.error.status_code", 400);
		Map<String, Object> map = new HashMap<>();
		if(e.getMessage() != null) {
			map.put("message", e.getMessage());
		}
		
		request.setAttribute("ext", map);
		return "forward:/error";
	}

}
