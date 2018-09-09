package com.snomyc.api.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.snomyc.api.user.request.UserAddRequest;
import com.snomyc.api.user.request.UserRequest;
import com.snomyc.base.domain.ResponseConstant;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.sys.bean.User;
import com.snomyc.sys.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "沙盘模拟", tags = "沙盘模拟")
@RestController
@RequestMapping("/api/user")
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "讲师创建沙盘模拟演练小组",httpMethod = "POST")
	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	public ResponseEntity createGroup(@RequestBody UserAddRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			userService.createGroup(request.getNum());
			responseEntity.success();
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}

	@ApiOperation(value = "获取创建沙盘模拟演练小组信息",httpMethod = "POST")  
	@RequestMapping(value = "/getGroupInfo", method = RequestMethod.POST)
	public ResponseEntity getGroupInfo() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			List<User> users = userService.findByOrderByGroupNumAsc();
			responseEntity.success(users, "调用成功");
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "小组操作员公司+随机密码进入",httpMethod = "POST")  
	@RequestMapping(value = "/groupLogin", method = RequestMethod.POST)
	public ResponseEntity groupLogin(UserRequest request) {
		ResponseEntity responseEntity = null;
		try {
			responseEntity = userService.groupLogin(request);
		} catch (Exception e) {
			responseEntity = new ResponseEntity();
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "清除沙盘模拟数据",httpMethod = "POST")  
	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	public ResponseEntity deleteGroup() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			userService.deleteAll();
			responseEntity.success();
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
}
