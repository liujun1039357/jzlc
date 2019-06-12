package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.mapper.NoticeMapper;
import com.zl.pojo.Notice;
import com.zl.service.INoticeService;

@Service
public class NoticeServiceImpl implements INoticeService {
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<Notice> getNotices() {
		List<Notice> notices= noticeMapper.getNotices();
		return notices;
	}
	


}
