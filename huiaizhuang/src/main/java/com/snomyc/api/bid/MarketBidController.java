package com.snomyc.api.bid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snomyc.api.user.request.UserAddRequest;
import com.snomyc.base.domain.ResponseConstant;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.sys.bean.User;
import com.snomyc.sys.service.MarketBidService;
import com.snomyc.sys.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;
@Api(value = "沙盘演练市场投标接口", tags = "沙盘演练市场投标接口")
@RestController
@RequestMapping("/api/bid")
public class MarketBidController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MarketBidService marketBidService;
	
}
