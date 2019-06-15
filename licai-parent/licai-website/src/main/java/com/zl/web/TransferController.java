package com.zl.web;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.pojo.Product;
import com.zl.pojo.ProductCheckList;
import com.zl.pojo.Profit;
import com.zl.pojo.RealAuthShow;
import com.zl.pojo.SelectCondition;
import com.zl.pojo.TradeRecord;
import com.zl.service.IBankCardInfoService;
import com.zl.service.IConsumerInfoService;
import com.zl.service.IProductService;
import com.zl.service.ITradeRecordService;
import com.zl.util.BitStateUtil;
import com.zl.util.CheckLogin;
/**
 * 所有页面的跳转
 * @author ivy
 *
 */
@Controller
public class TransferController {
	@Autowired
	private IConsumerInfoService consumerInfoService;
	@Autowired
	private IBankCardInfoService bankCardInfoService;
	@Autowired
	private IProductService productService;
	@Autowired
	private ITradeRecordService tradeRecordService;
	
	/**跳转登录页面*/
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	/** 跳转注册页面*/
	@RequestMapping("register")
	public String register() {
		return "register";
	}


	/**企业注册页面1*/
	@RequestMapping("eregister")
	public String eregister() {
		return "enterprise/eregister";
	}


	/**企业注册页面2*/
	@RequestMapping("eregister2")
	public String eregister2() {
		return "enterprise/eregister2";
	}
	

	@RequestMapping("elogin")
	public String elogin() {
		return "enterprise/elogin";
	}

	


	/**跳转添加产品页面*/
	@RequestMapping("addProduct")
	public String addProduct(Product product) {
		return "enterprise/addProduct";
	}
	
	/**产品审核记录页面*/
	@RequestMapping("productAuditProgress")
	public String productAuditProgress(ProductCheckList productCheckList) {
		return "enterprise/productAuditProgress";
	}
	
	/**个人产品审核列表页面*/
	@RequestMapping("personalProductList")
	public String personalProductList(Product product) {
		return "personal/personalProductList";
	}
	
	/**个人中心页面*/
	@RequestMapping("personalCenter")
	@CheckLogin
	public String personalCenter(@RequestParam(required=true,defaultValue="1")Integer pageindex,String id,Model model) {
		//在查询调用方法前声明分页信息（当前页，页容量）
	    //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		//page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
		PageHelper.startPage(pageindex,7);
		List<TradeRecord>list = tradeRecordService.queryTradeRecord();
		//再对查询结果进行包装成PageInfo对象,保存查询出的结果，PageInfo是pageHelper中的对象
		PageInfo<TradeRecord> pageInfo = new PageInfo<TradeRecord>(list,3);
		BigDecimal baseMoney = tradeRecordService.queryBaseMoney();
		BigDecimal interest = tradeRecordService.queryInterest();
		BigDecimal instableBaseMoney = tradeRecordService.queryInstableBaseMoney();
		BigDecimal instableInterest = tradeRecordService.queryInstableInterest();
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("baseMoney",baseMoney);
		model.addAttribute("interest",interest);
		model.addAttribute("instableBaseMoney",instableBaseMoney);
		model.addAttribute("instableInterest",instableInterest);
		return "personal/personalCenter";
	}
	
	/**安全保障页面*/
	@RequestMapping("safety")
	public String safety(String id) {
		return "indexinfo/safety";
	}
	
	/**公司简介页面*/
	@RequestMapping("aboutUs")
	public String aboutUs(String id) {
		return "indexinfo/aboutUs";
	}
	
	/**操作成功页面*/
	@RequestMapping("transferSuccessed")
	@CheckLogin
	public String transferSuccessed(String id) {
		return "personal/transferSuccessed";
	}
	
	/**交易记录页面*/
	@RequestMapping("receivableRecords")
	@CheckLogin
	public String receivableRecords(@RequestParam(required=true,defaultValue="1")Integer pageindex,Model model) {
		//在查询调用方法前声明分页信息（当前页，页容量）
	    //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		//page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
		PageHelper.startPage(pageindex,6);
		List<Profit>list = tradeRecordService.queryProfitList();
		PageInfo<Profit> pageInfo = new PageInfo<Profit>(list);
		System.out.println("list="+list);
		model.addAttribute("pageInfo",pageInfo);
		return "personal/receivableRecords";
	}
	
	/**充值页面*/
	@RequestMapping("recharge")
	@CheckLogin
	public String recharge(BigDecimal money,String cardId, Model model) {
		List<String> bankCardList = bankCardInfoService.queryCardId();
		model.addAttribute("bankCardList",bankCardList);
		System.out.println("bankCardList"+bankCardList);
		if(null != money && null != cardId) {
			//银行卡余额判断
			if(money.compareTo(new BigDecimal(5035))==1) {
				model.addAttribute("message","银行卡余额不足，请重试。");
				System.out.println("银行卡余额不足，请重试。");
			}else {
				//获取账户余额进行充值
				Boolean flag = consumerInfoService.recharge(money);
				if(flag) {
					model.addAttribute("message","支付已成功！");
					System.out.println("支付已成功！");
				}else {
					model.addAttribute("message","支付失败，请重试。");
					System.out.println("支付失败，请重试。");
				}
			}
		}
		return "personal/recharge";
	}
	
/*	@RequestMapping("recharge")
	@ResponseBody
	public AjaxJson recharge(BigDecimal money,String cardId, Model model) {
		AjaxJson json = new AjaxJson();
		//获取银行卡供用户选择
		List<String> bankCardList = bankCardInfoService.queryCardId();
		model.addAttribute("bankCardList",bankCardList);
		System.out.println("bankCardList"+bankCardList);
		try {
			if(money != null) {
				if(money.compareTo(new BigDecimal(5035))==1) {
					json.setSuccess(false);
					json.setMsg("银行卡余额不足，请重试.");
					return json;
				}
				if(!consumerInfoService.recharge(money)) { 
					json.setSuccess(false);
					json.setMsg("支付失败，请重试.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;	
	}
	*/
	/**提现页面*/
	@RequestMapping("cashOut")
	public String cashOut(BigDecimal money,String cardId, Model model) {
		
		//获取银行卡供用户选择
		List<String> bankCardList = bankCardInfoService.queryCardId();
		model.addAttribute("bankCardList",bankCardList);
		//获取账户余额进行回显
		BigDecimal balance = consumerInfoService.queryBalance();
		model.addAttribute("balance",balance);
		
		if(null != money && null != cardId) {
			//获取账户余额进行提现
			Boolean flag = consumerInfoService.cashOut(money);
			if(flag) {
				BigDecimal balance1 = consumerInfoService.queryBalance();
				model.addAttribute("message","提现已成功！");
				model.addAttribute("balance1",balance1);
				System.out.println("提现已成功！");
			}else {
				model.addAttribute("message","提现失败，请重试。");
				System.out.println("提现失败，请重试。");
			}
		}
		return "personal/cashOut";
	}
	
 /*@RequestMapping("cashOut")
	@CheckLogin
	@ResponseBody
	public AjaxJson cashOut(BigDecimal money,String cardId, Model model) {
		AjaxJson json = new AjaxJson();
		//获取银行卡供用户选择
		List<String> bankCardList = bankCardInfoService.queryCardId();
		model.addAttribute("bankCardList",bankCardList);
		//获取账户余额进行回显
		BigDecimal balance = consumerInfoService.queryBalance();
		model.addAttribute("balance",balance);
		
		if(null != money && null != cardId) {
			try {
				if(!consumerInfoService.cashOut(money)) {
					json.setSuccess(false);
					json.setMsg("提现失败，请重试.");
					return json;
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("系统忙!稍后重试...");
			}
		}
		return json;
	}*/

	/**我的钱包页面*/
	@RequestMapping("myMoney")
	@CheckLogin
	public String myMoney() {
		return "personal/myMoney";
	}

	/**账户设置页面*/
	@RequestMapping("accountSettings")
	@CheckLogin
	public String accountSettings(HttpServletRequest request) {
		String tel = consumerInfoService.queryTel();
		//身份证号
		String idCard = consumerInfoService.queryIdCard();
		//银行卡号数量
		int cardId = bankCardInfoService.queryCardId().size();
		String email = consumerInfoService.queryEmail();
		if(tel != null) {
			tel = tel.replaceAll(tel.substring(3, 7), "****");
			request.setAttribute("tel", tel);
		}
		if(idCard!=null) {
			idCard = idCard.replaceAll(idCard.substring(8, 14), "******");
			request.setAttribute("idCard", idCard);
		}
		if(cardId>0) {
			request.setAttribute("cardId", cardId);
		}
		if(email != null) {
			email = email.replaceAll(email.substring(3, 7), "****");
			request.setAttribute("email", email);
		}
		return "personal/accountSettings";
	}

	/**实名认证页面*/
	@RequestMapping("realAuth")
	@CheckLogin
	public String realAuth() {
		return "personal/realAuth";
	}
	
	/**实名认证展示页面*/
	@RequestMapping("realAuthShow")
	@CheckLogin
	public String realAuthShow(HttpServletRequest requset) {
		RealAuthShow realAuthShow = consumerInfoService.queryRealAuthInfo();
		requset.setAttribute("realAuthShow", realAuthShow);
		return "personal/realAuthShow";
	}
	
	/**绑定银行卡页面*/
	@RequestMapping("bindBank")
	@CheckLogin
	public String bindBank(HttpServletRequest requset) {
		long bitState = consumerInfoService.queryBitState();
		String name = consumerInfoService.queryRealName();
		List<String> bankCardList = bankCardInfoService.queryCardId();
		if(!BitStateUtil.hasState(bitState, BitStateUtil.OPEN_REAL_AUTH)) {
			requset.setAttribute("unRealAuth", "请先实名认证!");
			return "personal/realAuth";
		}
		if(bankCardList.size()<=0) {
			requset.setAttribute("unBindBank", "还未绑定银行卡");
		}
		requset.setAttribute("name", name);
		return "personal/bindBank";
	}
	
	/**查看银行卡页面*/
	@RequestMapping("queryBankCards")
	@CheckLogin
	public String queryBankCards(HttpServletRequest requset) {
		List<String> cardIds = bankCardInfoService.queryCardId();
		requset.setAttribute("cardIds", cardIds);
		return "personal/queryBankCards";
	}
	

	/**产品详情页面*/
	@RequestMapping("invest")
	public String invest(@RequestParam(required = true, defaultValue = "1") Integer pageindex,
			HttpServletRequest request, SelectCondition sc) {
		System.out.println(sc.toString());
		PageHelper.startPage(pageindex, 3);
		List<Product> plist = productService.queryProductByCond(sc);
		PageInfo<Product> pageInfo = new PageInfo<Product>(plist, 5);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("condition", sc);		
		return "personal/invest";
	}
	
	/**购买产品页面*/
	@RequestMapping("buyProduct")
	@CheckLogin
	public String buyProduct(HttpServletRequest requset,String productId) {
		long bitState = consumerInfoService.queryBitState();
		String name = consumerInfoService.queryRealName();
		BigDecimal balance = consumerInfoService.queryBalance();
		if(!BitStateUtil.hasState(bitState, BitStateUtil.OPEN_REAL_AUTH)) {
			requset.setAttribute("unRealAuth", "请先实名认证!");
			return "personal/realAuth";
		}
		if(!BitStateUtil.hasState(bitState, BitStateUtil.OPEN_BANKCARD)) {
			requset.setAttribute("unBindBank", "请先绑定银行卡");
			return "personal/bindBank";
		}
		requset.setAttribute("productId", productId);
		requset.setAttribute("name", name);
		requset.setAttribute("balance", balance);
		return "personal/buyProduct";
	}
	
	/**转出产品页面*/
	@RequestMapping("turnOut")
	@CheckLogin
	public String turnOut(HttpServletRequest requset,String productId) {
		String name = consumerInfoService.queryRealName();
		BigDecimal sumMoney = tradeRecordService.querySumMoney(productId);
		int productType = productService.queryProductType(productId);
		requset.setAttribute("productId", productId);
		requset.setAttribute("name", name);
		requset.setAttribute("sumMoney", sumMoney);
		requset.setAttribute("productType", productType);
		return "personal/turnOut";
	}
}
