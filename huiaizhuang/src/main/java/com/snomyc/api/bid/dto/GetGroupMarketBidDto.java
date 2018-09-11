package com.snomyc.api.bid.dto;

import java.io.Serializable;
import java.util.List;

import com.snomyc.sys.bean.MarketBid;

/**
 * @author Administrator
 * 小组公司产品投标信息返参
 */
public class GetGroupMarketBidDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String year; //年份
	private String company;//公司名称
	private int  groupNum; //小组编号
	
	private List<String> productList; //产品列表
	private List<String> marketList;//市场列表
	private List<MarketBid> marketBidList; //小组投标信息
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public List<String> getProductList() {
		return productList;
	}
	public void setProductList(List<String> productList) {
		this.productList = productList;
	}
	public List<String> getMarketList() {
		return marketList;
	}
	public void setMarketList(List<String> marketList) {
		this.marketList = marketList;
	}
	public List<MarketBid> getMarketBidList() {
		return marketBidList;
	}
	public void setMarketBidList(List<MarketBid> marketBidList) {
		this.marketBidList = marketBidList;
	}
	
}
