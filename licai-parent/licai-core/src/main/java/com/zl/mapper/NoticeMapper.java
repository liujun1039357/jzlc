package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zl.pojo.Notice;

@Mapper
public interface NoticeMapper {

	/**
	 * 获取公告
	 * */
	List<Notice> getNotices();
	
	/**
	 * 获取公告详情
	 * */
	Notice getNoticeInfo(Integer nId);
	
}
	
/*
*/