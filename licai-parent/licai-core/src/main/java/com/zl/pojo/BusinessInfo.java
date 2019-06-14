package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司注册/详细信息
 * */

@Data
public class BusinessInfo implements Serializable{
	/**企业分类:默认 1.国有企业*/
	public static Integer STATE = 1;
	/**企业分类: 2.集体所有制*/
	public static Integer COLLECTIVE = 2;
	/**企业分类: 3.私营企业*/
	public static Integer PRIVATE = 3;
	/**企业分类: 4.股份制企业*/
	public static Integer JOINT_STOCK = 4;

	/**风险等级:默认 0 低风险*/
	public static Integer LOW = 0;
	/**风险等级: 1 中风险*/
	public static Integer MEDIUM = 1;
	/**风险等级: 2 高风险*/
	public static Integer HIGH = 2;

	/**证件类型：默认 0：身份证*/
	public static Integer IDENTITY = 0;
	/**证件类型：1：护照*/
	public static Integer PASSPORT = 1;
	/**证件类型：2：驾驶证*/
	public static Integer DRIVER = 2;

	/**审核状态：默认 1.审核中*/
	public static Integer REVIEW = 1;
	/**审核状态：2.审核失败*/
	public static Integer FAIL = 2;
	/**审核状态：3.审核通过*/
	public static Integer PASS = 3;
	/**审核状态：4.冻结*/
	public static Integer FROZEN = 4;
	/**审核状态：5.注销*/
	public static Integer CANCELLATION = 5;
	/**审核状态：6.删除*/
	public static Integer DELETE = 6;

	private static final long serialVersionUID = 1L;

	/**对应的用户或企业ID*/
	private String businessId;
	/**企业分类:1.国有企业 2.集体所有制 3.私营企业 4.股份制企业*/
	private Integer businessType = STATE;
	/**企业名称*/
	private String businessName;
	/**社会信用代码*/
	private String businessCode;
	/**注册资本*/
	private Integer registerMoney;
	/**风险等级: 1 aaa 2 aa 3 a 4 bbb 5bb 6b 7ccc 8cc 9c 10d 11e*/
	private Integer registerStates = LOW;
	/**企业地址*/
	private String address;
	/**企业电话*/
	private String tel;
	/**营业执照：图片，此处填写图片路径*/
	private String license;
	/**资格证书：图片，此处填写图片路径*/
	private String qualification;
	/**法人姓名*/
	private String corporationsName;
	/**证件类型： 0：身份证 1：护照 2：驾驶证*/
	private Integer cardType = IDENTITY;
	/**证件号码*/
	private String cardId;
	/**现居地*/
	private String liveAddress;
	/**法人联系方式*/
	private String corporationsTel;
	/**职位*/
	private String job;
	/**法人证件正面*/
	private String image1;
	/**法人证件反面*/
	private String image2;
	/* 1.一级审核 2.二级审核 3.三级审核 4.审核失败 5.审核通过 6.冻结 7.注销 8.删除 */
	private Integer checkFlag = REVIEW;
	/**提交时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date addDate;
	/**更新时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateDate;
	/**企业银行账户*/
	private String bankCardId;

}
