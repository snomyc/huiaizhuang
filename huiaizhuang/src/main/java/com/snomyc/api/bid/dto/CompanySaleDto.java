package com.snomyc.api.bid.dto;

import java.io.Serializable;
/**
 * @author Administrator
 * 小组公司产品投标信息返参
 */
public class CompanySaleDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String company; //小组公司名称
	private int  sale; //销售费用
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
}
