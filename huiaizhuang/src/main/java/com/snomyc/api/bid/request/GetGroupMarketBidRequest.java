package com.snomyc.api.bid.request;

import com.snomyc.base.domain.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

public class GetGroupMarketBidRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "groupNum", value = "小组编号", required = true)
	private int groupNum;

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	
}
