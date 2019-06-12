package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户产品
 * */

@Data
public class TradeRecord implements Serializable{
	/** 默认0:其他公司*/
	public static Integer OTHER = 0;
	/** 1：本公司*/
	public static Integer OUR = 1;

	private static final long serialVersionUID = 1L;

	/**产品ID*/
	private String productId;
	/**产品名称*/
	private String productName;
	/**初始购买金额*/
	private Integer baseMoney;
	/**余额*/
	private Integer balance;
	/**利率*/
	private Integer rate;
	/**利息*/
	private Integer interest;
	/**0:其他公司 1：本公司*/
	private Integer ascription = OTHER;
	/**状态*/
	private Integer states;
	/**用户ID*/
	private String consumerId;
	/**生效日期*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date effectDate;
}
