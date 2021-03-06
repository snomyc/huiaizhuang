package com.snomyc.sys.service;

import java.util.List;

import com.snomyc.api.bid.dto.GetGroupMarketBidDto;
import com.snomyc.api.bid.request.EditGroupMarketBidRequest;
import com.snomyc.api.bid.request.MarketAddRequest;
import com.snomyc.api.bid.request.MarketAnalysisRequest;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.base.service.BaseService;
import com.snomyc.sys.bean.MarketBid;
public interface MarketBidService extends BaseService<MarketBid, String>{
	
	public ResponseEntity createMarketBid(MarketAddRequest request);
	
	public GetGroupMarketBidDto getGroupMarketBid(int groupNum);
	
	public void editGroupMarketBid(EditGroupMarketBidRequest request) throws Exception;
	
	public List<GetGroupMarketBidDto> getAllGroupMarketBid();
	
	public ResponseEntity marketBidAnalysis();
	
	public void labelCompanyMarket(MarketAnalysisRequest request);
	
	public ResponseEntity allMarketBidAnalysis();

}
