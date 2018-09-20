package com.snomyc.api.bid.dto;

import java.io.Serializable;
import java.util.List;
/**
 * @author Administrator
 * 小组公司产品投标信息返参
 */
public class MarketBidAnalysisDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String year; //年份
	private List<String> productList; //产品列表
	private List<String> marketList;//市场列表
	
	private List<CompanyBidAnalysisDto> companyBidList; //所有公司投标信息汇总
	
	private List<CompanySaleDto> companySaleList;//公司销售费用汇总
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public List<CompanyBidAnalysisDto> getCompanyBidList() {
		return companyBidList;
	}
	public void setCompanyBidList(List<CompanyBidAnalysisDto> companyBidList) {
		this.companyBidList = companyBidList;
	}
	public List<CompanySaleDto> getCompanySaleList() {
		return companySaleList;
	}
	public void setCompanySaleList(List<CompanySaleDto> companySaleList) {
		this.companySaleList = companySaleList;
	}
	
}
