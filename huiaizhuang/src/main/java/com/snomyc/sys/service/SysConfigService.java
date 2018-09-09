package com.snomyc.sys.service;

import com.snomyc.base.service.BaseService;
import com.snomyc.sys.bean.SysConfig;
public interface SysConfigService extends BaseService<SysConfig, String>{

	public String findParamValByCode(String code);
}
