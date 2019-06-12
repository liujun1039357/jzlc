package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.mapper.ProvincesMapper;
import com.zl.pojo.City;
import com.zl.pojo.Provincial;
import com.zl.service.IProvincesService;
@Service
public class ProvincesServiceImpl implements IProvincesService {

	@Autowired
	private ProvincesMapper  provincesMapper;
	
	@Override
	public List<Provincial> queryallProvincial() {
		return provincesMapper.queryallProvincial();
	}

	@Override
	public List<City> querycitybyPid(String pname) {
		return provincesMapper.querycitybyPid(pname);
	}

}
