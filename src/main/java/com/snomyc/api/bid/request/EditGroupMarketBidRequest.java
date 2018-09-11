package com.snomyc.api.bid.request;

import java.util.List;
import java.util.Map;

import com.snomyc.base.domain.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

public class EditGroupMarketBidRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "groupNum", value = "小组编号", required = true)
	private int groupNum;
	
	@ApiModelProperty(name = "year", value = "年份", required = true)
	private String year; //年份
	
	@ApiModelProperty(name = "bidInfo", value = "投标信息map(key:num,value:投标数量)", required = true)
	private List<Map<String,Object>> bidInfo;

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Map<String, Object>> getBidInfo() {
		return bidInfo;
	}

	public void setBidInfo(List<Map<String, Object>> bidInfo) {
		this.bidInfo = bidInfo;
	}
	
}
