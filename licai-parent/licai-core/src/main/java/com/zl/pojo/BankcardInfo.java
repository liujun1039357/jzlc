package com.zl.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户银行卡信息
 * */

@Data
public class BankcardInfo implements Serializable{
	/**0: 默认 已绑定*/
	public static Integer Bound = 0;
	/**0: 1 已解绑*/
	public static Integer UNBIND = 1;
	/**0: 2 无效*/
	public static Integer INVALID = 2;

	private static final long serialVersionUID = 1L;

	/**用户id*/
	private String consumerId;
	/**银行卡号*/
	private String cardId;
	/**修改时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateTime;
	/**0: 已绑定 1 已解绑 2 无效*/
	private Integer cardStates = Bound;
}
