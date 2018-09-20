package com.snomyc.api.bid.dto;

import java.io.Serializable;
import java.util.List;
/**
 * @author Administrator
 * 公司投标信息汇总
 */
public class CompanyBidAnalysisDto implements Serializable{

	private static final long serialVersionUID = 1L;
    private String marketName; //市场名称
    private String productName; //产品名称
    private List<CompanyBidDto> bidList;
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public List<CompanyBidDto> getBidList() {
		return bidList;
	}
	public void setBidList(List<CompanyBidDto> bidList) {
		this.bidList = bidList;
	}
}
