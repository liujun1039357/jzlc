package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司审核记录
 * */

@Data
public class ProductCheckList implements Serializable{
	/**审核结果 0通过*/
	public static Integer PASS = 0;
	/**审核结果 默认 1不通过*/
	public static Integer NOT_PASS = 1;

	private static final long serialVersionUID = 1L;

	/**产品流水号*/
	private String productId;
	/**产品名称*/
	private String productName;
	/**审核员ID*/
	private String jobNumber;
	/**审核权限*/
	private Integer jurisdiction;
	/**审核结果(0通过，1不通过) */
	private Integer checkResult = NOT_PASS;
	/**备注（原因）*/
	private String memo;
	/**审核时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date checkTime;
	/**提交时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date addTime;
	/**修改时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateTime;
	/** 产品经理人 */
	private ProductManager productManager;
}
