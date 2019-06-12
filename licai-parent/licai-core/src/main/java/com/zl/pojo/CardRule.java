package com.zl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 银行卡归属配置
 * */

@Data
public class CardRule implements Serializable{

	private static final long serialVersionUID = 1L;

	/**银行名称*/
	private String bankName;
	/**银行卡前6位;发卡行识别码区段起始*/
	private Integer sBin;
	/**银行卡前6位;发卡行识别码区段结束*/
	private Integer eBin;

}
