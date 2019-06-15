package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
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
	/** 默认0:购买中*/
	public static Integer BUY = 0;
	/** 1：已转出*/
	public static Integer TURNOUT = 1;
	/**收益方式 默认 0 日结*/
	public static Integer DAY = 0;
	/**收益方式 1 期结算*/
	public static Integer TERM = 1;
	private static final long serialVersionUID = 1L;

	/**产品ID*/
	private String productId;
	/**产品名称*/
	private String productName;
	/**初始购买金额*/
	private BigDecimal baseMoney;
	/**余额*/
	private BigDecimal balance;
	/**利率*/
	private BigDecimal rate;
	/**利息*/
	private BigDecimal interest;
	/**0:其他公司 1：本公司*/
	private Integer ascription = OTHER;
	/**状态 日结:0购买中 1 转出  期结:0未到期 1已转出*/
	private Integer states = BUY;
	/**用户ID*/
	private String consumerId;
	/**生效日期*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date effectDate;
	/**收益方式 0日结1期结算*/
	private Integer productProfitType = DAY;
	/**购买产品记录id*/
	private String tradeRecordId;

}
