package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息更新记录
 * */

@Data
public class ConsumerUpdateRecord implements Serializable{
	/**修改内容: 1登录密码*/
	public static Integer PASSWORD= 1;
	/**修改内容: 2.支付密码*/
	public static Integer PAY_PASSWORD= 2;
	/**修改内容: 3.手机*/
	public static Integer PHONE= 3;
	/**修改内容: 4.邮箱*/
	public static Integer MAIL= 4;
	/**修改内容: 5.密保*/
	public static Integer SECRET= 5;

	private static final long serialVersionUID = 1L;

	/**用户id*/
	private String consumerId;
	/**修改内容: 1登录密码 2.支付密码 3.手机 4.邮箱 5.密保*/
	private Integer target = PASSWORD;
	/**操作员；用户ID或管理员ID*/
	private String admin;
	/**修改时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateTime;
}
