package com.zl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.pojo.Business;
import com.zl.pojo.BusinessInfo;

@Mapper
public interface BusinessMapper {
	void insertSimpleBusinssInfo(Business businss);
	void insertBusinssInfo(BusinessInfo businssInfo );
	Business querySimpleBusinessInfoByEmail(Business businss);
	BusinessInfo queryBusinessInfoById(Business bs);
}
