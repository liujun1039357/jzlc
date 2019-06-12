package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 收益流水
 * */

@Data
public class Profit implements Serializable{

	private static final long serialVersionUID = 1L;

	/**编号*/
	private String no;
	/**用户id*/
	private String consumerId;
	/**产品ID*/
	private String productId;
	/**结算日期*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date clearDate;
	/**购买金额*/
	private Integer principal;
	/**收益*/
	private Integer profit;
}
