package com.zl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 公告
 * */

@Data
public class Notice implements Serializable{

	/**是否使用：默认 0是*/
	public static Integer YES = 0;
	/**是否使用：1否*/
	public static Integer NO = 1;

	private static final long serialVersionUID = 1L;

	/**标题*/
	private String title;
	/**文本*/
	private String text;
	/**是否使用： 0是 1否*/
	private Integer inUse = YES;

}
