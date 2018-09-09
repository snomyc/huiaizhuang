package com.snomyc.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import com.snomyc.base.service.BaseServiceImpl;
import com.snomyc.sys.bean.MarketBid;
import com.snomyc.sys.dao.MarketBidDao;
import com.snomyc.sys.service.MarketBidService;
@Service
public class MarketBidServiceImpl extends BaseServiceImpl<MarketBid, String> implements MarketBidService{

    @Autowired
    private MarketBidDao marketBidDao;
    
    @Override
	public PagingAndSortingRepository<MarketBid, String> getDao() {
		return marketBidDao;
	}
	
}


