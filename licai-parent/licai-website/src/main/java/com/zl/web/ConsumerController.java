package com.zl.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zl.exception.JZLCException;
import com.zl.service.IAuthentication;
import com.zl.service.IConsumerService;
import com.zl.util.AjaxJson;
import com.zl.util.CheckLogin;
import com.zl.util.MD5;
import com.zl.util.UserContext;

@Controller
public class ConsumerController {
	@Autowired
	private IConsumerService consumerService;
	@Autowired
	private IAuthentication authentication;
	
	@RequestMapping("personalLogin")
	@ResponseBody
	public AjaxJson PersonalLogin(String consumerName, String password,HttpServletRequest request) {
		AjaxJson json = new AjaxJson();
		boolean login = true;
		try {
			login = consumerService.personalLogin(consumerName, MD5.encode(password),request.getRemoteAddr());
			if (!login) {
				json.setSuccess(false);
				json.setMsg("账号或密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		request.getSession().setAttribute("consumer", UserContext.getLogininfo());
		return json;
	}
	
	@RequestMapping("sendVerifyCode")
	@ResponseBody
	public AjaxJson sendVerifyCode(String tel){
		AjaxJson json = new AjaxJson();
		try{
			authentication.sendVertifyCode(tel);
		}catch(Exception e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}
	
	@RequestMapping("checkConsumerName")
	@ResponseBody
	public boolean checkConsumerName(String consumerName)throws JZLCException{
		return consumerService.checkConsumerName(consumerName);
	}
	
	@RequestMapping("checkTel")
	@ResponseBody
	public boolean checkTel(String tel)throws JZLCException{
		return consumerService.checkTel(tel);
	}
	
	@RequestMapping("personalRegister")
	@ResponseBody
	public AjaxJson personalRegister(String consumerName,String tel,String password,String verifyCode,HttpServletRequest request) {
		AjaxJson json = new AjaxJson();
		boolean register = true;
		try {
			register = consumerService.personalRegister(consumerName,tel,MD5.encode(password),verifyCode,request.getRemoteAddr());
			if (!register) {
				json.setSuccess(false);
				json.setMsg("验证码错误!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		request.getSession().setAttribute("consumer", UserContext.getLogininfo());
		return json;
	}
	
	@RequestMapping("exitLogin")
	public String exitLogin(HttpServletRequest request, HttpServletResponse response) {
		// 清除历史记录,即清除相应cookie;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					// 删除cookie
					cookie.setMaxAge(0);
					// 将cookie值置空
					cookie.setValue("");
					// 将cookie保存到服务器
					response.addCookie(cookie);
				}
			}
		}
		request.getSession().removeAttribute("consumer");
		request.getSession().removeAttribute("loginBusiness");
		return "redirect:index";
	}
	
	@RequestMapping("cancellation")
	public String cancellation(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("consumer") != null) {
			consumerService.cancellation(UserContext.getLogininfo().getConsumerId());
		}
		// 清除历史记录,即清除相应cookie;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					// 删除cookie
					cookie.setMaxAge(0);
					// 将cookie值置空
					cookie.setValue("");
					// 将cookie保存到服务器
					response.addCookie(cookie);
				}
			}
		}
		request.getSession().removeAttribute("consumer");
		request.getSession().removeAttribute("loginBusiness");
		return "redirect:index";
	}
	
	/**修改登录密码*/
	@RequestMapping("loginPasswordUpdate")
	@CheckLogin
	@ResponseBody
	public AjaxJson loginPasswordUpdate(String password,String newPassword) {
		AjaxJson json = new AjaxJson();
		boolean modify = true;
		try {
			modify = consumerService.loginPasswordUpdate(MD5.encode(password),MD5.encode(newPassword));
			if(!modify) {
				json.setSuccess(false);
				json.setMsg("原密码错误!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}
}
