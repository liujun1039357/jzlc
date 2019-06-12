package com.zl.service;

import com.zl.exception.JZLCException;

public interface IConsumerService {
	/**
	 * 个人用户登录
	 * @param consumerName 用户名
	 * @param password 密码
	 * @param remoteAddr ip
	 * @return
	 */
	boolean personalLogin(String consumerName, String password,String ip)throws JZLCException;

	/**判断用户名是否已存在
	 * @param consumerName 用户名
	 * @return
	 */
	boolean checkConsumerName(String consumerName)throws JZLCException;
	
	/**验证手机号是否存在
	 * @param tel 手机号
	 * @return
	 */
	boolean checkTel(String tel)throws JZLCException;

	
	/**个人用户注册
	 * @param consumerName 用户名
	 * @param tel 手机号
	 * @param password 密码
	 * @param verifyCode 手机验证码
	 * @param remoteAddr ip
	 * @return
	 */
	boolean personalRegister(String consumerName, String tel, String password, String verifyCode, String ip)throws JZLCException;

	/**注销账号
	 * @param consumerId
	 */
	void cancellation(String consumerId)throws JZLCException;

	/**修改登录密码
	 * @param password
	 * @param newPassword
	 * @return
	 */
	boolean loginPasswordUpdate(String password, String newPassword) throws JZLCException;
}
