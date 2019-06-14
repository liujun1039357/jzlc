package com.zl.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.pojo.TradeList;
import com.zl.service.ITradeListService;
import com.zl.util.CheckLogin;


@Controller
public class tradeListController {
	
	@Autowired
	private ITradeListService tradeListService;	

	@RequestMapping("tradeList")
	@CheckLogin
	public String index(@RequestParam(required=true,defaultValue="1")Integer pageindex,Model model) {
		//在查询调用方法前声明分页信息（当前页，页容量）
	    //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		//page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
		PageHelper.startPage(pageindex, 5);
		List <TradeList> tradeLists =  tradeListService.getTradeLists();
		//再对查询结果进行包装成PageInfo对象,保存查询出的结果，PageInfo是pageHelper中的对象
		PageInfo<TradeList> pageInfo = new PageInfo<TradeList>(tradeLists);
		model.addAttribute("tradeLists",tradeLists);
		model.addAttribute("pageInfo",pageInfo);
		System.out.println("tradeLists="+tradeLists);
		return "personal/tradeList";
	}
	
}
