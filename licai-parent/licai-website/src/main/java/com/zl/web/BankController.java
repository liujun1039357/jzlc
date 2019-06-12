/**
 * 
 */
package com.zl.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zl.util.CheckLogin;

/**
 * @author ivy
 *
 */
@Controller
public class BankController {

	@RequestMapping("bindBankSubmit")
	@CheckLogin
	@ResponseBody
	public String bindBankSubmit(String email) {
		return "redirect:/accountSettings";
	}
}
