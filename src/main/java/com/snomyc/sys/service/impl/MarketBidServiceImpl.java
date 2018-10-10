package com.snomyc.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.snomyc.api.bid.dto.AllMarketBidAnalysisDto;
import com.snomyc.api.bid.dto.CompanyBidAnalysisDto;
import com.snomyc.api.bid.dto.CompanyBidDto;
import com.snomyc.api.bid.dto.CompanySaleDto;
import com.snomyc.api.bid.dto.GetGroupMarketBidDto;
import com.snomyc.api.bid.dto.MarketBidAnalysisDto;
import com.snomyc.api.bid.request.AnalysisBid;
import com.snomyc.api.bid.request.EditGroupMarketBidRequest;
import com.snomyc.api.bid.request.MarketAddRequest;
import com.snomyc.api.bid.request.MarketAnalysisRequest;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.base.service.BaseServiceImpl;
import com.snomyc.sys.bean.Market;
import com.snomyc.sys.bean.MarketBid;
import com.snomyc.sys.bean.User;
import com.snomyc.sys.dao.MarketBidDao;
import com.snomyc.sys.dao.MarketBidQueryDao;
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
    
    @Autowired
    private MarketBidQueryDao marketBidQueryDao;
    
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

	@Override
	public void editGroupMarketBid(EditGroupMarketBidRequest request) throws Exception {
		//更新小组的投标信息
		List<MarketBid> marketBidList = marketBidDao.findByGroupNumAndYear(request.getGroupNum());
		if(marketBidList.size() != request.getBidInfo().size()) {
			throw new Exception("编辑投标信息有误!");
		}
		for (int i = 0; i < marketBidList.size(); i++) {
			MarketBid marketBid = marketBidList.get(i);
			marketBid.setBidNum(Integer.valueOf(request.getBidInfo().get(i)));
			//小组编辑完投标信息表示已投标isBid = 1
			marketBid.setIsBid(1);
		}
		marketBidDao.save(marketBidList);
	}

	@Override
	public List<GetGroupMarketBidDto> getAllGroupMarketBid() {
		//获取小组信息
		List<User> userList = userService.findByOrderByGroupNumAsc();
		if(!CollectionUtils.isEmpty(userList)) {
			List<GetGroupMarketBidDto> dtoList = new ArrayList<GetGroupMarketBidDto>();
			for (User user : userList) {
				dtoList.add(this.getGroupMarketBid(user.getGroupNum()));
			}
			return dtoList;
		}
		return null;
	}

	@Override
	public ResponseEntity marketBidAnalysis() {
		//判断是否全部公司都投标
		ResponseEntity responseEntity = new ResponseEntity();
		int count = marketBidDao.validateCompanyBid();
		if(count > 0) {
			responseEntity.failure("请等待其他小组完成投标!");
			return responseEntity;
		}
		//所有小组均完成投标
		MarketBidAnalysisDto dto = new MarketBidAnalysisDto();
		Market market = marketDao.findTopByOrderByCreateTimeDesc();
		List<String> productList = Arrays.asList(market.getProductName().split(","));
		List<String> marketList = Arrays.asList(market.getMarketName().split(","));
		dto.setMarketList(marketList);
		dto.setProductList(productList);
		String year = marketBidDao.getCurrentYear();
		dto.setYear(year);
		//汇总数据
		List<CompanyBidAnalysisDto> companyBidList = new ArrayList<CompanyBidAnalysisDto>();
		for (String productName : productList) {
			for (String marketName : marketList) {
				CompanyBidAnalysisDto analysisDto = new CompanyBidAnalysisDto();
				analysisDto.setProductName(productName);
				analysisDto.setMarketName(marketName);
				List<MarketBid> list = marketBidDao.findByProductNameAndMarketNameAndYearOrderByGroupNumAsc(productName,marketName,year);
				List<CompanyBidDto> bidList = new ArrayList<CompanyBidDto>();
				for (MarketBid marketBid : list) {
					CompanyBidDto companyBidDto = new CompanyBidDto();
					companyBidDto.setCompany(marketBid.getCompany());
					//查询该公司上一年是否有被标记过，如果有则投标数+1
					//MarketBid marketBids = marketBidDao.findFirstByProductNameAndMarketNameAndYearAndGroupNum(productName, marketName, String.valueOf((Integer.valueOf(year)-1)), marketBid.getGroupNum());
//					if(marketBids != null) {
//						companyBidDto.setBidNum(marketBid.getBidNum()+marketBids.getIsLabel());
//					}else {
//						companyBidDto.setBidNum(marketBid.getBidNum());
//					}
					companyBidDto.setBidNum(marketBid.getBidNum());
					bidList.add(companyBidDto);
				}
				analysisDto.setBidList(bidList);
				companyBidList.add(analysisDto);
			}
		}
		dto.setCompanyBidList(companyBidList);
		//公司销售费用汇总
		List<CompanySaleDto> companySaleList = new ArrayList<CompanySaleDto>();
		List<Map<String,Object>> mapList = marketBidQueryDao.findCompanySales(year);
		for (Map<String, Object> map : mapList) {
			CompanySaleDto companySaleDto = new CompanySaleDto();
			companySaleDto.setCompany((String)map.get("company"));
			companySaleDto.setSale(Integer.valueOf(map.get("sale").toString()));
			companySaleList.add(companySaleDto);
		}
		dto.setCompanySaleList(companySaleList);
		responseEntity.success(dto, "成功");
		return responseEntity;
	}

	@Override
	public void labelCompanyMarket(MarketAnalysisRequest request) {
		//更新标注的投标公司为已标记，下一年会自动+1
		for (AnalysisBid analysisBid : request.getLabelList()) {
			marketBidDao.updateCompanyAnalysisBid(analysisBid.getProductName(), analysisBid.getMarketName(), request.getYear(), analysisBid.getCompany());
		}
		//讲师标注完，表示该年投标已完结isFinish = 1
		marketBidDao.updateByIsFinish(request.getYear());
	}

	@Override
	public ResponseEntity allMarketBidAnalysis() {
		ResponseEntity responseEntity = new ResponseEntity();
		
		//判断是否有汇总年份
		List<String> yearList = marketBidDao.findIsFinishActiveYear();
		if(CollectionUtils.isEmpty(yearList)) {
			responseEntity.failure("暂无汇总信息");
			return responseEntity;
		}
		List<AllMarketBidAnalysisDto> dtoList = new ArrayList<AllMarketBidAnalysisDto>();
		//通过年份依次获取每年的汇总信息
		Market market = marketDao.findTopByOrderByCreateTimeDesc();
		List<String> productList = Arrays.asList(market.getProductName().split(","));
		List<String> marketList = Arrays.asList(market.getMarketName().split(","));
		for (String year : yearList) {
			AllMarketBidAnalysisDto dto = new AllMarketBidAnalysisDto();
			dto.setYear(year);
			dto.setProductList(productList);
			dto.setMarketList(marketList);
			//汇总数据
			List<CompanyBidAnalysisDto> companyBidList = new ArrayList<CompanyBidAnalysisDto>();
			for (String productName : productList) {
				for (String marketName : marketList) {
					CompanyBidAnalysisDto analysisDto = new CompanyBidAnalysisDto();
					analysisDto.setProductName(productName);
					analysisDto.setMarketName(marketName);
					List<MarketBid> list = marketBidDao.findByProductNameAndMarketNameAndYearOrderByGroupNumAsc(productName,marketName,year);
					List<CompanyBidDto> bidList = new ArrayList<CompanyBidDto>();
					for (MarketBid marketBid : list) {
						CompanyBidDto companyBidDto = new CompanyBidDto();
						companyBidDto.setCompany(marketBid.getCompany());
						//查询该公司上一年是否有被标记过，如果有则投标数+1
						MarketBid marketBids = marketBidDao.findFirstByProductNameAndMarketNameAndYearAndGroupNum(productName, marketName, String.valueOf((Integer.valueOf(year)-1)), marketBid.getGroupNum());
						if(marketBids != null && marketBids.getIsLabel() == 1) {
							companyBidDto.setBidNum(marketBid.getBidNum());
							companyBidDto.setIsLabel(1);
						}else {
							companyBidDto.setBidNum(marketBid.getBidNum());
						}
						bidList.add(companyBidDto);
					}
					analysisDto.setBidList(bidList);
					companyBidList.add(analysisDto);
				}
			}
			dto.setCompanyBidList(companyBidList);
			dtoList.add(dto);
		}
		responseEntity.success(dtoList, "成功");
		return responseEntity;
	}
}


