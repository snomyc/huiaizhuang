package com.snomyc.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.snomyc.api.bid.request.MarketAddRequest;
import com.snomyc.api.user.request.UserRequest;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.base.service.BaseServiceImpl;
import com.snomyc.sys.bean.Market;
import com.snomyc.sys.bean.MarketBid;
import com.snomyc.sys.bean.User;
import com.snomyc.sys.dao.MarketBidDao;
import com.snomyc.sys.dao.MarketDao;
import com.snomyc.sys.dao.UserDao;
import com.snomyc.sys.service.UserService;
import com.snomyc.util.CodecUtil;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService{

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private MarketDao marketDao;
    
    @Autowired
    private MarketBidDao marketBidDao;

	@Override
	public PagingAndSortingRepository<User, String> getDao() {
		return userDao;
	}

	@Override
	public List<User> findByOrderByGroupNumAsc() {
		return userDao.findByOrderByGroupNumAsc();
	}

	@Override
	public void createGroup(int num) {
		
		
		//创建沙盘小组的同时，将投标信息也插入进去
		Market market = marketDao.findTopByOrderByCreateTimeDesc();
		if(market == null) {
			return;
		}
		
		//根据传入的小组数量创建小组信息
		for (int i = 1; i <= num; i++) {
			User user = new User();
			user.setGroupNum(i);
			//设置随机6位数密码
			user.setPassword(CodecUtil.createRandom(6));
			userDao.save(user);
		}
		
		List<User> userList = userDao.findByOrderByGroupNumAsc();
		
		int curYear = Integer.valueOf(market.getYear());
		List<String> productList = Arrays.asList(market.getProductName().split(","));
		List<String> marketList = Arrays.asList(market.getMarketName().split(","));
		
		List<MarketBid>  marketBidList = new ArrayList<MarketBid>();
		//模拟活动年限
		for (int i = 0; i < market.getCount(); i++) {
			for (User user : userList) {
				
				for (String productName : productList) {
					
					for (String marketName : marketList) {
						MarketBid marketBid = new MarketBid();
						marketBid.setYear(String.valueOf(curYear+i));
						marketBid.setGroupNum(user.getGroupNum());
						marketBid.setCompany(user.getCompany());
						marketBid.setProductName(productName);
						marketBid.setMarketName(marketName);
						marketBid.setIsLabel(0);
						marketBid.setIsBid(0);
						marketBid.setIsFinish(0);
						marketBidList.add(marketBid);
					}
				}
			}
		}
		marketBidDao.save(marketBidList);
	}

	@Override
	public void deleteAll() {
		marketDao.deleteAllInBatch();
		userDao.deleteAllInBatch();
		marketBidDao.deleteAllInBatch();
	}

	@Override
	public ResponseEntity groupLogin(UserRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		if(StringUtils.isBlank(request.getCompany()) || StringUtils.isBlank(request.getPassword())) {
			responseEntity.failure("请填写公司名称或进入密码!");
		}
		User user = userDao.findByGroupNumAndPassword(request.getGroupNum(),request.getPassword());
		if(user == null) {
			responseEntity.failure("随机密码输入错误!");
		}
		user.setCompany(request.getCompany());
		userDao.save(user);
		
		//批量更新投标市场表小组公司信息
		marketBidDao.updateByGroupNum(user.getGroupNum(), request.getCompany());
		responseEntity.success();
		return responseEntity;
	}

	@Override
	public List<User> findByCompanyIsNull() {
		List<User> user = userDao.findByCompanyIsNull();
		return user;
	}

	@Override
	public Map<String, Object> activeStatus() {
		Map<String, Object> map = new HashMap<String,Object>();
		int count = marketDao.countByOrderByCreateTimeAsc();
		if(count > 0) {
			map.put("active", 1);
		}else {
			map.put("active", 0);
		}
		
		count = userDao.countByOrderByGroupNumAsc();
		if(count > 0) {
			map.put("group", 1);
		}else {
			map.put("group", 0);
		}
		return map;
	}
}

