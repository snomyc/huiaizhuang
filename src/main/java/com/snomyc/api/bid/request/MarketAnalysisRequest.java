package com.snomyc.api.bid.request;


import java.util.List;

import com.snomyc.base.domain.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

public class MarketAnalysisRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "year", value = "年份", required = true)
	private String year;
	
	@ApiModelProperty(name = "labelList", value = "标记公司", required = true)
	private List<AnalysisBid> labelList;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<AnalysisBid> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<AnalysisBid> labelList) {
		this.labelList = labelList;
	}

}
