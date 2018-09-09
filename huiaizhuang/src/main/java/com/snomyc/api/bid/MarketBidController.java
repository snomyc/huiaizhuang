package com.snomyc.api.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.snomyc.api.bid.request.GetGroupMarketBidRequest;
import com.snomyc.api.bid.request.MarketAddRequest;
import com.snomyc.api.user.request.UserAddRequest;
import com.snomyc.base.domain.ResponseConstant;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.sys.service.MarketBidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "沙盘演练市场投标接口", tags = "沙盘演练市场投标接口")
@RestController
@RequestMapping("/api/bid")
public class MarketBidController {
	
	@Autowired
	private MarketBidService marketBidService;
	
	@ApiOperation(value = "讲师创建公司产品投标信息",httpMethod = "POST")
	@RequestMapping(value = "/createMarketBid", method = RequestMethod.POST)
	public ResponseEntity createMarketBid(@RequestBody MarketAddRequest request) {
		ResponseEntity responseEntity = null;
		try {
			responseEntity = marketBidService.createMarketBid(request);
		} catch (Exception e) {
			responseEntity = new ResponseEntity();
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "各小组获取公司产品投标信息",httpMethod = "POST")
	@RequestMapping(value = "/getGroupMarketBid", method = RequestMethod.POST)
	public ResponseEntity getGroupMarketBid(@RequestBody GetGroupMarketBidRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "各小组编辑公司产品投标信息",httpMethod = "POST")
	@RequestMapping(value = "/editGroupMarketBid", method = RequestMethod.POST)
	public ResponseEntity editGroupMarketBid(@RequestBody UserAddRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			//各小组编辑完投标信息表示已投标isBid = 1
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "公司产品投标分析汇总",httpMethod = "POST")
	@RequestMapping(value = "/marketBidAnalysis", method = RequestMethod.POST)
	public ResponseEntity marketBidAnalysis(@RequestBody UserAddRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			//只有所有公司都投标了才能看最终分析结果
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "讲师标注某公司为市场/产品第一",httpMethod = "POST")
	@RequestMapping(value = "/labelCompanyMarket", method = RequestMethod.POST)
	public ResponseEntity labelCompanyMarket(@RequestBody UserAddRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			//讲师标注完，表示该年投标已完结isFinish = 1
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
}
