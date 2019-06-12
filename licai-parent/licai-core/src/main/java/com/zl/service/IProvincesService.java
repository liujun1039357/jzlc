package com.zl.service;

import java.util.List;

import com.zl.pojo.City;
import com.zl.pojo.Provincial;

public interface IProvincesService {
/*
 * 查询全国省级城市信息
 */
	List<Provincial> queryallProvincial();
	List<City> querycitybyPid(String pname);
}
