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
@Api(value = "运维接口测试", tags = "运维接口测试")
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@ApiOperation(value = "小组获取公司产品投标信息",httpMethod = "POST")
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ResponseEntity test() {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			responseEntity.success("成功");
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}

}
