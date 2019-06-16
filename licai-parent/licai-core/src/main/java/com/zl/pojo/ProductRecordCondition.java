package com.zl.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductRecordCondition implements Serializable {
	private static final long serialVersionUID = 1L;
	/**审核状态，0未审核/1一审通过/2二审通过/3审核通过 */
	private Integer checkFlag;
	/**开始时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date start;
	/**结束时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date end;
	
}
