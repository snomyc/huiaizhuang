package com.snomyc.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.snomyc.api.bid.dto.GetGroupMarketBidDto;
import com.snomyc.api.bid.request.MarketAddRequest;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.base.service.BaseServiceImpl;
import com.snomyc.sys.bean.Market;
import com.snomyc.sys.bean.MarketBid;
import com.snomyc.sys.bean.User;
import com.snomyc.sys.dao.MarketBidDao;
import com.snomyc.sys.dao.MarketDao;
import com.snomyc.sys.service.MarketBidService;
import com.snomyc.sys.service.UserService;
@Service
public class MarketBidServiceImpl extends BaseServiceImpl<MarketBid, String> implements MarketBidService{

	@Autowired
	private UserService userService;
	
	@Autowired
    private MarketDao marketDao;
	
    @Autowired
    private MarketBidDao marketBidDao;
    
    @Override
	public PagingAndSortingRepository<MarketBid, String> getDao() {
		return marketBidDao;
	}

	@Override
	public ResponseEntity createMarketBid(MarketAddRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		//判断是否有小组没有填公司名称
		List<User> userList = userService.findByOrderByGroupNumAsc();
		
		if(CollectionUtils.isEmpty(userList)) {
			responseEntity.failure("请创建沙盘模拟小组信息");
			return responseEntity;
		}
		
		boolean flag = false;
		StringBuffer sb = new StringBuffer();
		sb.append("第");
		for (User user : userList) {
			if(StringUtils.isBlank(user.getCompany())) {
				sb.append(user.getGroupNum()).append(",");
				flag = true;
			}
		}
		if(flag) {
			sb.append("小组未填写公司名,请完善信息.");
			responseEntity.failure(sb.toString());
			return responseEntity;
		}
		
		//各小组均完善信息则创建各小组投标初始化信息
		int curYear = Integer.valueOf(request.getYear());
		List<MarketBid>  marketBidList = new ArrayList<MarketBid>();
		//模拟活动年限
		for (int i = 0; i < request.getCount(); i++) {
			for (User user : userList) {
				
				for (String product : request.getProductList()) {
					
					for (String market : request.getMarketList()) {
						MarketBid marketBid = new MarketBid();
						marketBid.setYear(String.valueOf(curYear+i));
						marketBid.setGroupNum(user.getGroupNum());
						marketBid.setCompany(user.getCompany());
						marketBid.setProductName(product);
						marketBid.setMarketName(market);
						marketBid.setIsLabel(0);
						marketBid.setIsBid(0);
						marketBid.setIsFinish(0);
						marketBidList.add(marketBid);
					}
				}
			}
		}
		marketBidDao.save(marketBidList);
		return responseEntity;
	}

	
	@Override
	public GetGroupMarketBidDto getGroupMarketBid(int groupNum) {
		//获取某小组某年活动的投标信息
		Market market = marketDao.findTopByOrderByCreateTimeDesc();
		if(market == null) {
			return null;
		}
		GetGroupMarketBidDto dto = new GetGroupMarketBidDto();
		List<String> productList = Arrays.asList(market.getProductName().split(","));
		List<String> marketList = Arrays.asList(market.getMarketName().split(","));
		dto.setProductList(productList);
		dto.setMarketList(marketList);
		
		List<MarketBid> marketBidList = marketBidDao.findByGroupNumAndYear(groupNum);
		dto.setMarketBidList(marketBidList);
		dto.setGroupNum(groupNum);
		dto.setYear(marketBidList.get(0).getYear());
		dto.setCompany(marketBidList.get(0).getCompany());
		return dto;
	}
	
}


