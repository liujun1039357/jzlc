package com.zl.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 *	 用户查询产品简单信息（联合企业表）
 * @author Administrator
 *
 */

@Data
public class BasisProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	/**产品流水号*/
	private String productId;
	/**产品名称*/
	private String productName;
	/**产品额度*/
	private Double productQuota;
	/**预期收益率*/
	private Double productProfit;
	/**起售金额*/
	private Double productLowQuota;
	/**周期时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date productAcountTime;
	/**对应的用户或企业ID*/
	private String businessId;
	/**企业名称*/
	private String businessName;

}
