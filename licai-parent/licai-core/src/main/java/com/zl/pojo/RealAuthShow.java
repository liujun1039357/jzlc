/**
 * 
 */
package com.zl.pojo;

import lombok.Data;

/**实名认证页面展示信息
 * @author ivy
 *
 */
@Data
public class RealAuthShow {
	private String consumerName;
	private String name;
	private String idCard;
	private int sex;
	private String address;
	/**身份证正面照片*/
	private String imageA;
	/**身份证反面照片*/
	private String imageB;
}
