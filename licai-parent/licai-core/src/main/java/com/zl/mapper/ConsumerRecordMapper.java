package com.zl.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zl.pojo.ConsumerRecord;

@Mapper
public interface ConsumerRecordMapper {
	/**
	 * 添加登录记录
	 * @param consumerRecord
	 */
	int insertLoginRecord(ConsumerRecord consumerRecord);

}
