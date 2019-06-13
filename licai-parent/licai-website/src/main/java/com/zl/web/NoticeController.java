package com.zl.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.pojo.Notice;
import com.zl.service.INoticeService;

@Controller
public class NoticeController {

	@Autowired
	private INoticeService iNoticeService;

	@RequestMapping("systemInformation")
	public String systemInformation(@RequestParam(required=true,defaultValue="1")Integer pageindex,Model model) {
		//在查询调用方法前声明分页信息（当前页，页容量）
	    //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		//page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
		PageHelper.startPage(pageindex,8);
		List <Notice> notices =  iNoticeService.getNotices();
		//再对查询结果进行包装成PageInfo对象,保存查询出的结果，PageInfo是pageHelper中的对象
		PageInfo<Notice> pageInfo = new PageInfo<Notice>(notices);
		model.addAttribute("notices",notices);
		model.addAttribute("pageInfo",pageInfo);
		return "personal/systemInformation";
	}
	
	
	//查询公告详情
	@GetMapping("noticeInfo/{nId}")
	public String noticeInfo(@PathVariable("nId") Integer nId,Model model) {
		System.out.println("nId="+nId);
		Notice notice = iNoticeService.getNoticeInfo(nId);
		model.addAttribute("notice",notice);
		return"personal/noticeInfo";
	}
	

}
