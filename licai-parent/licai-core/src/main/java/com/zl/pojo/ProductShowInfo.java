/**
 * 
 */
package com.zl.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * @author ivy
 *
 */
@Data
public class ProductShowInfo {
	/**产品名称*/
	private String productName;
	/**所属企业*/
	private String businessName;
	/**风险等级1（保本保收）
	 2（保本不保收）
	 3（不保本不保收）*/
	private Integer productRiskGrade;
	/**可否赎回 0可1不可*/
	private Integer productBack;
	/**融资金额*/
	private Integer productQuota;
	/**预期收益率*/
	private BigDecimal productProfit;
	/**已售额度*/
	private BigDecimal productSaledQuota;
	/**起售金额*/
	private BigDecimal productLowQuota;
	/**收益方式 0日结1期结算*/
	private Integer productProfitType;
	/**最短收益生效日*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date productLowProfitDate;
}
