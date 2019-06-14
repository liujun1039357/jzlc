package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品信息
 * */

@Data
public class Product implements Serializable{

	/**产品类型 默认0 活期*/
	public static Integer CURRENT = 0;
	/**产品类型 1 定期*/
	public static Integer REGULAR = 1;

	/**产品状态 默认 0 待审核*/
	public static Integer CHECK_PENDING = 0;
	/**产品状态 1 审核中*/
	public static Integer IN_AUDIT = 1;
	/**产品状态 2 已审核*/
	public static Integer AUDITED = 2;
	/**产品状态 3 待发行*/
	public static Integer TO_BE_ISSUED = 3;
	/**产品状态 4 发行中*/
	public static Integer ISSUED = 4;
	/**产品状态 5 已售完*/
	public static Integer SOLD_OUT = 5;
	/**产品状态 6 已过期*/
	public static Integer EXPIRED = 6;

	/**收益方式 默认 0 日结*/
	public static Integer DAY = 0;
	/**收益方式 1 期结算*/
	public static Integer TERM = 1;

	/**可否赎回 默认 0 可*/
	public static Integer YES = 0;
	/**可否赎回 1 不可*/
	public static Integer NO = 1;

	/**风险等级 1（保本保收）*/
	public static Integer CAPITAL_PRESERVATION = 1;
	/**风险等级 2（保本不保收）*/
	public static Integer CAPITAL_NOT_REVENUE = 2;
	/**风险等级 3（不保本不保收）*/
	public static Integer NOT_CAPITAL_NOT_REVENUE = 3;

	/**审核状态，默认 0未审核 */
	public static Integer NOT = 0;
	/**审核状态，1一审通过*/
	public static Integer ONE = 1;
	/**审核状态，2二审通过*/
	public static Integer TWO = 2;
	/**审核状态，3审核通过*/
	public static Integer THREE = 3;

	private static final long serialVersionUID = 1L;

	/**产品流水号*/
	private String productId;
	/**产品名称*/
	private String productName;
	/**产品额度*/
	private Integer productQuota;
	/**产品类型 0活期1定期*/
	private Integer productType = CURRENT;
	/**预期收益率*/
	private BigDecimal productProfit;
	/**已售额度*/
	private BigDecimal productSaledQuota;
	/**售完时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date productSaledTime;
	/**产品经理人*/
	private String productManager;
	/**产品状态 0待审核 1审核中 2已审核 3待发行 4发行中 5已售完 6已过期*/
	private Integer productState = CHECK_PENDING;
	/**起售金额*/
	private BigDecimal productLowQuota;
	/**收益方式 0日结1期结算*/
	private Integer productProfitType = DAY;
	/**可否赎回 0可1不可*/
	private Integer productBack = YES;
	/**最短收益生效日*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date productLowProfitDate;
	/**风险等级1（保本保收）
	 2（保本不保收）
	 3（不保本不保收）*/
	private Integer productRiskGrade = CAPITAL_PRESERVATION;
	/**上线时间生效时间（预计发布时间）*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date productEffectDate;
	/**审核状态，0未审核/1一审通过/2二审通过/3审核通过 */
	private Integer checkFlag = NOT;
	/**提交时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date addTime;
	/**修改时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateTime;
	/**死期类结算时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date productAcountTime;
	/**所属公司*/
	private String businessid;
	/**结算周期*/
	private Integer  acountcycle;
}
