package com.snomyc.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import com.snomyc.base.service.BaseServiceImpl;
import com.snomyc.sys.bean.SysConfig;
import com.snomyc.sys.dao.SysConfigDao;
import com.snomyc.sys.service.SysConfigService;
@Service
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig, String> implements SysConfigService{

    @Autowired
    private SysConfigDao sysConfigDao;
    
    @Override
	public PagingAndSortingRepository<SysConfig, String> getDao() {
		return sysConfigDao;
	}

	@Override
	public String findParamValByCode(String code) {
		return sysConfigDao.findParamValByCode(code);
	}

	
}


