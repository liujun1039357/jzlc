package com.zl.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zl.util.DateUtil;

import lombok.Data;

@Data
public class SelectTradeListCondition {
	
		private int tradeType;
		
		@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
		private Date start = DateUtil.OneMonthAgoDate(new Date());
		@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
		private Date end = new Date();
		
		
	
	
}


