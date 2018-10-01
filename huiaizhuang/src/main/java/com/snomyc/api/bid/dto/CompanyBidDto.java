package com.snomyc.api.bid.dto;

import java.io.Serializable;
/**
 * @author Administrator
 * 公司投标信息汇总
 */
public class CompanyBidDto implements Serializable{

	private static final long serialVersionUID = 1L;
    private String company; //小组公司名称
	private int  bidNum; //投标数量
	private int  isLabel; //是否标记，0:否 1:是 
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getBidNum() {
		return bidNum;
	}
	public void setBidNum(int bidNum) {
		this.bidNum = bidNum;
	}
	public int getIsLabel() {
		return isLabel;
	}
	public void setIsLabel(int isLabel) {
		this.isLabel = isLabel;
	}
}
