/**
 * 
 */
package com.zl.service;

import com.zl.exception.JZLCException;

/**
 * @author ivy
 *
 */
public interface IAuthentication {

	/**发送短信验证码
	 * @param phoneNumber
	 */
	void sendVertifyCode(String tel)throws JZLCException;
	
	/**
	 * 判断短信验证码是否正确
	 * @param phoneNumber
	 * @param verifyCode
	 * @return
	 * @throws JZLCException
	 */
	public boolean bindPhone(String tel, String verifyCode) throws JZLCException;
	
	/**
	 * 发送邮箱验证码
	 * @param to
	 * @param title
	 * @param content
	 */
	void sendSimpleMail(String to,String title,String content);
	
	/**
	 * 判断邮箱验证码是否正确
	 * @param email 邮箱
	 * @param verifyCode 验证码
	 * @return 
	 */
	public boolean bindEmail(String email, String emailCode) throws JZLCException;

	/**判断是否超时
	 * @return true 表示超时 false表示没超时
	 * @throws JZLCException
	 */
	boolean isOvertime() throws JZLCException;
}
