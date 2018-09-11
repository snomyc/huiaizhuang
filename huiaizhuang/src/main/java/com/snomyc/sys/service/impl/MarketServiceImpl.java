package com.snomyc.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import com.snomyc.base.service.BaseServiceImpl;
import com.snomyc.sys.bean.Market;
import com.snomyc.sys.dao.MarketDao;
import com.snomyc.sys.service.MarketService;
import com.snomyc.sys.service.UserService;
@Service
public class MarketServiceImpl extends BaseServiceImpl<Market, String> implements MarketService{

    @Autowired
    private MarketDao marketDao;
    
    @Override
	public PagingAndSortingRepository<Market, String> getDao() {
		return marketDao;
	}
}


