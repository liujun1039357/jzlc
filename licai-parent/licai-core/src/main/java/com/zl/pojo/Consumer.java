package com.zl.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 客户账户基础信息
 * */

@Data
public class Consumer implements Serializable{
	/**登录状态,默认为0 正常*/
	public static Integer NORMAL= 0;
	/**登录状态, 1表示已注销*/
	public static Integer CANCELLED= 1;
	/**登录状态, 2表示已冻结*/
	public static Integer FROZEN= 2;

	private static final long serialVersionUID = 1L;
	/**用户ID，唯一*/
	private String consumerId;
	/**用户姓名*/
	private String consumerName;
	/**密码*/
	private String password;
	/**登录状态,默认为0 正常  1表示已登录 2表示已注销 3表示已冻结*/
	private Integer accountsFlag = NORMAL;
	/**创建日期*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;
	/**最后一次修改日期*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateTime;
}
