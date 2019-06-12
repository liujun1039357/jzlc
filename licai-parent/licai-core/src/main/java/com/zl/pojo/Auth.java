/**
 * 
 */
package com.zl.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author ivy
 *
 */
@Data
public class Auth implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String realName;
	private String idCard;
}
