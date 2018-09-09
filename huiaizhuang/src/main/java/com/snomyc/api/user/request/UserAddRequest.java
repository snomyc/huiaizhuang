package com.snomyc.api.user.request;


import com.snomyc.base.domain.BaseRequest;
import io.swagger.annotations.ApiModelProperty;

public class UserAddRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "小组数量", value = "小组数量", required = true)
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
