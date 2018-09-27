package com.snomyc.api.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.snomyc.api.bid.request.MarketAddRequest;
import com.snomyc.api.user.request.UserAddRequest;
import com.snomyc.api.user.request.UserRequest;
import com.snomyc.base.domain.ResponseConstant;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.sys.bean.Market;
import com.snomyc.sys.bean.User;
import com.snomyc.sys.service.MarketService;
import com.snomyc.sys.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "沙盘模拟", tags = "沙盘模拟")
@RestController
@RequestMapping("/api/user")
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MarketService marketService;
	
	@ApiOperation(value = "讲师活动首页",httpMethod = "POST")
	@RequestMapping(value = "/activeIndex", method = RequestMethod.POST)
	public ResponseEntity activeIndex() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			Map<String,Object> result = userService.activeStatus();
			responseEntity.success(result,"成功");
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "讲师创建公司产品投标信息",httpMethod = "POST")
	@RequestMapping(value = "/createMarketBid", method = RequestMethod.POST)
	public ResponseEntity createMarketBid(@RequestBody MarketAddRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			List<Market> list = marketService.findAll();
			if(!CollectionUtils.isEmpty(list)) {
				responseEntity.failure("活动已创建请勿重复编辑!");
				return responseEntity;
			}
			Market market = new Market();
			market.setYear(request.getYear());
			market.setCount(request.getCount());
			StringBuffer sb = new StringBuffer();
			for (String productName : request.getProductList()) {
				sb.append(productName).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			market.setProductName(sb.toString());
			
			sb = new StringBuffer();
			for (String marketName : request.getMarketList()) {
				sb.append(marketName).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			market.setMarketName(sb.toString());
			market.setCreateTime(new Date());
			marketService.save(market);
			responseEntity.success();
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	
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
	public ResponseEntity groupLogin(@RequestBody UserRequest request) {
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
	
	@ApiOperation(value = "小组活动首页",httpMethod = "POST")
	@RequestMapping(value = "/groupActiveIndex", method = RequestMethod.POST)
	public ResponseEntity groupActiveIndex() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			Map<String,Object> result = userService.groupActiveStatus();
			responseEntity.success(result,"成功");
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
}
