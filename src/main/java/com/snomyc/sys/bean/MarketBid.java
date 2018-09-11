package com.snomyc.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.snomyc.base.domain.BaseEntity;

/**
 * @author Administrator
 * 市场投标表
 */
@Entity
public class MarketBid extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false,length=10)
	private String year;//年份
	
	private int  groupNum; //小组编号
	
	@Column(length=50)
    private String company; //小组公司名称
	
	@Column(nullable = false, length=50)
    private String marketName; //市场名称
	
	@Column(nullable = false, length=50)
    private String productName; //产品名称
	
	private int  bidNum; //投标数量
	private int  isLabel; //是否被标注，0:否 1:是  被标注的公司下年投标数量+1
	
	private int  isBid; //是否投标，0:否 1:是 
	private int  isFinish; //是否完结，0:否 1:是 
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
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
	public int getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
	}
	public int getIsBid() {
		return isBid;
	}
	public void setIsBid(int isBid) {
		this.isBid = isBid;
	}
   
}
