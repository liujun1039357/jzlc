/**
 * 
 */
package com.zl.service;

import com.zl.exception.JZLCException;
import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.RealAuthShow;

/**
 * @author ivy
 *
 */
public interface IConsumerInfoService {

	/**邮箱绑定
	 * @param email 邮箱
	 * @param emailCode 验证码
	 */
	void personalEmailBind(String email, String emailCode) throws JZLCException;

	/**查询当前用户邮箱
	 * @return
	 */
	String queryEmail() throws JZLCException;

	/**查询当前用户身份证
	 * @return
	 */
	String queryIdCard() throws JZLCException;

	/**查询当前用户电话号
	 * @return
	 */
	String queryTel() throws JZLCException;

	/**查询实名认证信息
	 * @return
	 */
	RealAuthShow queryRealAuthInfo() throws JZLCException;

	/**查询真实姓名
	 * @return
	 */
	String queryRealName() throws JZLCException;

	/**查询认证状态
	 * @return
	 */
	long queryBitState() throws JZLCException;

	/**更改邮箱
	 * @param email
	 * @param verifyCode
	 */
	boolean personalEmailUpdate(String tel,String email, String verifyCode) throws JZLCException;

	/**更新手机号
	 * @param newTel 新号码
	 * @param newVerify 新的验证码
	 * @return
	 */
	boolean updateTel(String newTel, String newVerify) throws JZLCException;

	/**实名认证
	 * @param realAuthShow
	 * @return
	 */
	boolean realAuth(ConsumerInfo consumerInfo) throws JZLCException;

	
	
}
