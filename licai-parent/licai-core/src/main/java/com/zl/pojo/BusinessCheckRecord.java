package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司审核记录
 * */

@Data
public class BusinessCheckRecord implements Serializable{
	/**审核结果 0通过*/
	public static Integer PASS = 0;
	/**审核结果 默认 1不通过*/
	public static Integer NOT_PASS = 1;

	private static final long serialVersionUID = 1L;

	/**姓名*/
	private String managerId;
	/**审核员ID*/
	private String jobNumber;
	/**审核权限*/
	private Integer jurisdiction;
	/**审核结果(0通过，1不通过) */
	private Integer checkResult = NOT_PASS;
	/**备注（原因）*/
	private String memo;
	/**企业类型*/
	private String businessType;
	/**审核时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date checkTime;
	/**提交时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date addDate;
}
