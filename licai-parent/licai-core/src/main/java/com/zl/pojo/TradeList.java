package com.zl.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客户交易清单
 * */

@Data
public class TradeList implements Serializable{
	/**交易类型：默认 0充值；*/
	public static Integer RECHARGE = 0;
	/**交易类型：1:提现；*/
	public static Integer CASH_OUT = 1;
	/**交易类型：2：买入；*/
	public static Integer PURCHASE = 2;
	/**交易类型：3：转出*/
	public static Integer TURNOUT = 3;

	/**订单专业；默认 0：未完成；*/
	public static Integer INCOMPLETE = 0;
	/**订单专业；1：已完成；*/
	public static Integer FINISH = 1;
	/**订单专业；2：已失败*/
	public static Integer FAIL = 2;

	private static final long serialVersionUID = 1L;

	/**对应的用户或企业ID*/
	private String id;
	/**用户ID*/
	private String consumerId;
	/**产品ID*/
	private String productId;
	/**交易类型：0充值；1:提现；2：买入；3：卖出*/
	private Integer tradeType = RECHARGE;
	/**余额收支（显示正负的交易金额）*/
	private BigDecimal budGet;
	/**交易金额*/
	private BigDecimal tradeMoney;
	/**余额*/
	private BigDecimal balance;
	/**订单专业；0：未完成；1：已完成；2：已失败*/
	private Integer orderStates = FINISH;
	/**交易时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date tradeTime;
}
