package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.pojo.City;
import com.zl.pojo.Provincial;
@Mapper
public interface ProvincesMapper {
	List<Provincial> queryallProvincial(); 
	List<City> querycitybyPid(@Param("pname")String pname);
}
