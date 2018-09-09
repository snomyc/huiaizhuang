package com.snomyc.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.snomyc.base.domain.BaseEntity;

@Entity
public class User extends BaseEntity{
    
	private static final long serialVersionUID = 1L;
	
	@Column(length=50)
    private String company; //小组公司名称 唯一
	
	private int  groupNum; //小组编号
	
	@Column(nullable = false,length=50)
    private String password; //小组密码

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
   
}
