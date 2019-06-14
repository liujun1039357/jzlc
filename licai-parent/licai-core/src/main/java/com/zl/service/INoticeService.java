package com.zl.service;

import java.util.List;

import com.zl.exception.JZLCException;
import com.zl.pojo.Notice;

public interface INoticeService {
	
	List<Notice> getNotices() throws JZLCException;
	
	Notice getNoticeInfo(Integer nId) throws JZLCException;

}
