package com.zl.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zl.pojo.Business;
import com.zl.pojo.Consumer;
import com.zl.pojo.MailInfo;
import com.zl.pojo.VerifyCodeInfo;

/**
 * 封装获取session,并往HttpSession中存放数据
 * @author Ivy
 *
 */
public class UserContext {
	/**当前登录用户*/
	private static String USER_IN_SESSION = "currentUser";

	private static String BUSINESSUSER_IN_SESSION = "loginBusiness";

	/**短信认证信息*/
	private static String VERIFYCODE_IN_SESSION = "verifyCodeInSession";
	/**邮箱绑定信息*/
	private static String MAILCODE_IN_SESSION = "mailCodeInSession";


	private static HttpSession getHttpSession(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	
	/**将当前登录用户存到session域*/
	public static void setLogininfo(Consumer consumer){
		getHttpSession().setAttribute(USER_IN_SESSION,consumer);
	}
	/**从session域中获取当前登录用户*/
	public static Consumer getLogininfo(){
		return (Consumer) getHttpSession().getAttribute(USER_IN_SESSION);
	}
	

	/**将当前登录的企业用户存到session域*/
	public static void setLoginBusiness(Business bs){
		getHttpSession().setAttribute(BUSINESSUSER_IN_SESSION,bs);
	}
	/**从session域中获取当前登录用户*/
	public static Consumer getLoginBusiness(){
		return (Consumer) getHttpSession().getAttribute(BUSINESSUSER_IN_SESSION);}

	/**将当前验证码信息存到session域*/
	public static void setVerifyCodeInSession(VerifyCodeInfo verifyCodeInfo){
		getHttpSession().setAttribute(VERIFYCODE_IN_SESSION,verifyCodeInfo);
	}
	/**从session域中获取当前验证码信息*/
	public static VerifyCodeInfo getVerifyCodeInSession(){
		return (VerifyCodeInfo) getHttpSession().getAttribute(VERIFYCODE_IN_SESSION);
	}
	
	/**将当前验证码信息存到session域*/
	public static void setMailCodeInSession(MailInfo mailInfo){
		getHttpSession().setAttribute(MAILCODE_IN_SESSION,mailInfo);
	}
	/**从session域中获取当前验证码信息*/
	public static MailInfo getMailCodeInSession(){
		return (MailInfo) getHttpSession().getAttribute(MAILCODE_IN_SESSION);

	}
}
