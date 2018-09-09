package com.snomyc.sys.service;

import com.snomyc.api.bid.request.MarketAddRequest;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.base.service.BaseService;
import com.snomyc.sys.bean.MarketBid;
public interface MarketBidService extends BaseService<MarketBid, String>{
	
	public ResponseEntity createMarketBid(MarketAddRequest request);

}
