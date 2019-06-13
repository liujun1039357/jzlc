package com.zl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 公告
 * */

@Data
public class Notice implements Serializable{


	/**是否已读：默认 1是*/
	public static Integer YES = 1;
	/**是否已读：0否*/
	public static Integer NO = 0;

	private static final long serialVersionUID = 1L;

	/**标题*/
	private String title;
	/**文本*/
	private String textContent;
	/**是否已读：1是 0否*/
	private Integer inUse = YES;
	/**序列号*/
	private Integer nId;

}
