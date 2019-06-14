package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品经理信息
 * */

@Data
public class ProductManager implements Serializable{
	/**性别 0 男*/
	public static Integer MAN = 0;
	/** 1：女*/
	public static Integer WOMAN = 1;

	/**审核状态，默认 0未审核 */
	public static Integer NOT = 0;
	/**审核状态，1一审通过*/
	public static Integer ONE = 1;
	/**审核状态，2二审通过*/
	public static Integer TWO = 2;
	/**审核状态，3审核通过*/
	public static Integer THREE = 3;

	private static final long serialVersionUID = 1L;

	/**经理人id*/
	private String managerId;
	/**姓名*/
	private String managerName;
	/**身份证号码*/
	private String cardId;
	/**性别(0 男 1 女)*/
	private Integer sex;
	/**企业职位*/
	private String job;
	/**审核状态，0未审核/1一审通过/2二审通过/3审核通过 */
	private Integer checkFlag = NOT;
	/**申请通过/添加时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date addTime;
	/**申请时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date applyTime;
	/**更新时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateTime;
	/**隶属企业id*/
	private String businessid;
}
