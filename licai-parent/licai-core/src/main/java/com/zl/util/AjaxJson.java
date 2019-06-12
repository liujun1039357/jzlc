package com.zl.util;
import lombok.Data;
/**
 * 登录返回前台视图所需json数据
 */

@Data
public class AjaxJson {
	/**
	 * success 是否登录成功  true表示成功,false表示失败
	 */
	private boolean success = true;
	private String msg;
	
	public AjaxJson(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	public AjaxJson(String msg) {
		this.msg = msg;
	}
	public AjaxJson() {}
	
}
