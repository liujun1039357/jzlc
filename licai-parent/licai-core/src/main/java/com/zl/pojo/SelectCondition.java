package com.zl.pojo;

import lombok.Data;

@Data
public class SelectCondition {
	
	private int checkflag=-1;
	
	private String  businessID;
	
	private  int productstate;
	
	private  Integer productBack;
	
	private Integer productType;
	
	private  Integer productLowQuota;
	
	private  Integer productQuota;
	
	private  Integer productRiskGrade;
	
	private  Integer productProfitType;
	
	private  String productName;
	
	
	
}
