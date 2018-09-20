package com.snomyc.api.bid.request;


import java.util.List;

import com.snomyc.base.domain.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

public class MarketAnalysisRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "year", value = "年份", required = true)
	private String year;
	
	@ApiModelProperty(name = "count", value = "活动次数", required = true)
	private int count;
	
	@ApiModelProperty(name = "productList", value = "产品列表", required = true)
	private List<String> productList; //产品列表
	
	@ApiModelProperty(name = "marketList", value = "市场列表", required = true)
	private List<String> marketList;//市场列表

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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
}
