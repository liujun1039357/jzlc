package com.zl.util;

import java.math.BigDecimal;

/**
 * 系统常量工具类
 * 
 * @author Ivy
 *
 */
public class SystemConstant {
	// 防止丢失精度, 不要使用 new BigDecimal(double) 而使用new BigDecimal(String)
	/** 默认值0 */
	public static final BigDecimal ZARO = new BigDecimal("0.0000");

	// 图片公共文件夹
	public static final String PUBLIC_IMG_PATH = "D:/upload/";

	/**短信验证码有效时间*/
	public static final int VERIFYCODE_VALIDATE_TIME = 60;
	/**邮箱验证码有效时间*/
	public static final int MAILCODE_VALIDATE_TIME = 5*60;
	/**橘子理财ID*/
	public static final String JUZILICAI_ID="jzlc0000000";
	/**橘子理财NAME*/
	public static final String JUZILICAI_NAME="橘子理财理财宝";
	/**橘子理财利率*/
	public static final BigDecimal JUZILICAI_RATE= new BigDecimal("0.001");
	/**橘子理财类型*/
	public static final int JUZILICAI_PROFIT_TYPE= 0;

}
