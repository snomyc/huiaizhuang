package com.snomyc.api.amazon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.snomyc.base.domain.ResponseConstant;
import com.snomyc.base.domain.ResponseEntity;
import com.snomyc.sys.bean.AmazonKeyWord;
import com.snomyc.sys.service.AmazonKeyWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(value = "亚马逊爬虫数据接口", tags = "亚马逊爬虫数据接口")
@RestController
@RequestMapping("/api/amazon")
public class AmazonApiController {
	
	@Autowired
	private AmazonKeyWordService amazonKeyWordService;
	
	@ApiOperation(value = "查询关键词",httpMethod = "POST")
	@RequestMapping(value = "/searchKeyWord", method = RequestMethod.POST)
	public ResponseEntity searchKeyWord(@ApiParam(required = true, name = "keyWordRoot", value = "关键词根词") 
				@RequestParam(name = "keyWordRoot",required = true) String keyWordRoot) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			List<AmazonKeyWord> list = amazonKeyWordService.findByKeyWordRoot(keyWordRoot);
			StringBuffer sb = new StringBuffer();
			for (AmazonKeyWord amazonKeyWord : list) {
				sb.append(amazonKeyWord.getKeyWordSecond()).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			data.put("keyWord", sb.toString());
			responseEntity.success(data,"成功");
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "更新关键词",httpMethod = "POST")
	@RequestMapping(value = "/updateKeyWord", method = RequestMethod.POST)
	public ResponseEntity updateKeyWord(@ApiParam(required = true, name = "keyWordRoot", value = "关键词根词") 
				@RequestParam(name = "keyWordRoot",required = true) String keyWordRoot) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			amazonKeyWordService.updateKeyWord(keyWordRoot);
			responseEntity.success();
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
	@ApiOperation(value = "导出excel地址",httpMethod = "POST")
	@RequestMapping(value = "/exportUrl", method = RequestMethod.POST)
	public ResponseEntity exportUrl(HttpServletRequest request) {
		ResponseEntity responseEntity = new ResponseEntity();
		try {
			Map<String,Object> data = new HashMap<String,Object>();
//			StringBuffer sb  = new StringBuffer();
//			sb.append("http://").append(request.getServerName()).append(":");
//			sb.append(request.getServerPort());
//			sb.append(request.getContextPath());
//			sb.append(request.getServletPath());
			String url = request.getRequestURL().toString();
			url = url.substring(0, url.indexOf("/",7));
			String export = "/amazon-sup/amazon/export?keyWordRoot=";
			data.put("export", export);
			data.put("url", url+export);
			responseEntity.success(data,"成功");
		} catch (Exception e) {
			responseEntity.failure(ResponseConstant.CODE_500, "接口调用异常");
		}
		return responseEntity;
	}
	
}
