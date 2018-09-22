package com.snomyc.api.bid.request;

import io.swagger.annotations.ApiModelProperty;
public class AnalysisBid{
	@ApiModelProperty(name = "marketName", value = "市场名称", required = true)
	private String marketName;
	
	@ApiModelProperty(name = "productName", value = "产品名称", required = true)
	private String productName;
	
	@ApiModelProperty(name = "company", value = "公司名称", required = true)
	private String company; //小组公司名称

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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
