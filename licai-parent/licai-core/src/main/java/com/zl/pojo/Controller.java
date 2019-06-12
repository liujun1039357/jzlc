package com.zl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理员信息
 * */

@Data
public class Controller implements Serializable{

	/**软删除 默认 0正常*/
	public static Integer NORMAL = 0;
	/**软删除 1删除*/
	public static Integer DELETE = 1;

	/**审核权限 1一级管理员*/
	public static Integer ONE = 1;
	/**审核权限 2二级管理员*/
	public static Integer TWO = 2;
	/**审核权限 3三级管理员*/
	public static Integer THREE = 3;

	private static final long serialVersionUID = 1L;

	/**工号*/
	private String jobNumber;
	/**姓名*/
	private String username;
	/**职位*/
	private String job;
	/**审核权限 1一级管理员 2二级管理员 3三级管理员*/
	private Integer jurisdiction;
	/**软删除 0正常 1删除*/
	private Integer states = NORMAL;

}
