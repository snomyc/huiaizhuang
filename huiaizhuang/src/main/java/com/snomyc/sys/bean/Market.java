package com.snomyc.sys.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.snomyc.base.domain.BaseEntity;

/**
 * @author Administrator
 * 市场产品表
 */
@Entity
public class Market extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false,length=10)
	private String year;//年份
	
	@Column(nullable = false, length=500)
    private String marketName; //市场名称 逗号隔开
	
	@Column(nullable = false, length=500)
    private String productName; //产品名称 逗号隔开
	
	private int  count; //活动次数
	
	private Date createTime; //创建时间

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
   
}
