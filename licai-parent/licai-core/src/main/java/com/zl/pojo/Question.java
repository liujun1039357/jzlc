package com.zl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 客户安全问题记录
 * */

@Data
public class Question implements Serializable{
	/**问题归属用户类型 默认 0：个人用户*/
	public static Integer PERSON = 0;
	/**问题归属用户类型1：企业用户*/
	public static Integer ENTERPRISE = 1;

	private static final long serialVersionUID = 1L;

	/**对应的用户或企业ID*/
	private String id;
	/**问题1*/
	private String question1;
	/**问题2*/
	private String question2;
	/**问题3*/
	private String question3;
	/**答案1*/
	private String answer1;
	/**答案2*/
	private String answer2;
	/**答案3*/
	private String answer3;
	/**问题归属用户类型 0：个人用户 1：企业用户*/
	private Integer type = PERSON;
}
