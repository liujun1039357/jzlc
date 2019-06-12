package com.zl.util;
/**
 * 认证状态码
 * @author Ivy
 *
 */
public class BitStateUtil {
	/**实名制认证的状态码*/
	public static final long OPEN_REAL_AUTH = 1 << 0;  //左移0位 二进制 --> 0001
	/**短信认证的状态码*/
	public static final long OPEN_MSG_AUTH = 1 << 1;   //左移一位 二进制 --> 0010
	/**邮箱认证的状态码*/
	public static final long OPEN_EMAIL_AUTH = 1 << 2;  //左移两位 二进制 --> 0100
	/**银行卡认证的状态码*/
	public static final long OPEN_BANKCARD = 1 << 3;   //左移三位 二进制 --> 1000
	
	/**
	 * 判断是否有该认证
	 * @param bitState  用户认证状态
	 * @param stateCode  认证状态码
	 * @return   true  有   false 没有
	 */
	public static boolean hasState(long bitState,long stateCode){
		return (bitState & stateCode) > 0;
	}
	
	/**
	 * 添加状态码(进行认证)
	 * @param bitState    用户认证状态
	 * @param stateCode   需要添加认证状态码
	 * @return   添加之后的状态
	 */
	public static long addAuthentication(long bitState,long stateCode){
		if(hasState(bitState,stateCode)){
			return bitState;
		}
		return bitState | stateCode;
	}
	
	/**
	 * 移除状态码
	 * @param bitState    用户认证状态
	 * @param stateCode   需要移除认证状态码
	 * @return   移除之后的状态
	 */
	public  static  long  removeState(long bitState, long stateCode){
		if(hasState(bitState,stateCode)){
			return bitState ^ stateCode;
		}
		return bitState;
	}
}
