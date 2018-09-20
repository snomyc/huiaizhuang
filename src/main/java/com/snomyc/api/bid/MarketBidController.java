package com.snomyc.api.bid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.snomyc.api.bid.dto.GetGroupMarketBidDto;
import com.snomyc.api.bid.request.EditGroupMarketBidRequest;
import com.snomyc.api.bid.request.GetGroupMarketBidRequest;
import com.snomyc.api.bid.request.MarketAnalysisRequest;
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
	
	@ApiOperation(value = "小组获取公司产品投标信息",httpMethod = "POST")
	@RequestMapping(value = "/getGroupMarketBid", method = RequestMethod.POST)
	public ResponseEntity getGroupMarketBid(@RequestBody GetGroupMarketBidRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			GetGroupMarketBidDto dto = marketBidService.getGroupMarketBid(request.getGroupNum());
			responseEntity.success(dto,"成功");
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "小组编辑公司产品投标信息",httpMethod = "POST")
	@RequestMapping(value = "/editGroupMarketBid", method = RequestMethod.POST)
	public ResponseEntity editGroupMarketBid(@RequestBody EditGroupMarketBidRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			marketBidService.editGroupMarketBid(request);
			responseEntity.success();
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "查看所有小组公司产品投标信息",httpMethod = "POST")
	@RequestMapping(value = "/getAllGroupMarketBid", method = RequestMethod.POST)
	public ResponseEntity getAllGroupMarketBid() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			
			List<GetGroupMarketBidDto> dtoList = marketBidService.getAllGroupMarketBid();
			responseEntity.success(dtoList,"成功");
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "公司产品投标分析汇总",httpMethod = "POST")
	@RequestMapping(value = "/marketBidAnalysis", method = RequestMethod.POST)
	public ResponseEntity marketBidAnalysis() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			//只有所有公司都投标了才能看最终分析结果
			responseEntity = marketBidService.marketBidAnalysis();
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "讲师标注某公司为市场/产品第一",httpMethod = "POST")
	@RequestMapping(value = "/labelCompanyMarket", method = RequestMethod.POST)
	public ResponseEntity labelCompanyMarket(@RequestBody MarketAnalysisRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			//讲师标注完，表示该年投标已完结isFinish = 1
			marketBidService.labelCompanyMarket(request);
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
}
