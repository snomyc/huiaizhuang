package com.snomyc.api.user.request;


import com.snomyc.base.domain.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

public class UserRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "groupNum", value = "小组编号", required = true)
	private int groupNum;
	
	@ApiModelProperty(name = "company", value = "公司名称", required = true)
	private String company;
	
	@ApiModelProperty(name = "password", value = "密码", required = true)
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
