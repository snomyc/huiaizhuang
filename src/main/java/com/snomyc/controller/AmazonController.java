package com.snomyc.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.snomyc.sys.bean.AmazonKeyWord;
import com.snomyc.sys.service.AmazonKeyWordService;
import com.snomyc.util.excel.ExcelUtilXSSF;

import springfox.documentation.annotations.ApiIgnore;
@ApiIgnore
@Controller
@RequestMapping("/amazon")
public class AmazonController {
	
	@Autowired
	private AmazonKeyWordService amazonKeyWordService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<AmazonKeyWord> list = amazonKeyWordService.findAll();
		model.addAttribute("list", list);
		return "keyword/list";
	}
	
	
	/**
	 * @param keyWordRoot
	 * @param request
	 * @param response
	 * @throws Exception
	
	 * 方法说明:导出关键词excel
	
	 * 创立日期:2018年6月3日 下午7:12:50
	 * 创建人:snomyc
	
	 */
	@RequestMapping("/export")
	public void export(@RequestParam String keyWordRoot,HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		List<AmazonKeyWord> list = amazonKeyWordService.findListByKeyWordRoot(keyWordRoot);
		if(CollectionUtils.isNotEmpty(list)) {
			String[] titles = {"关键词根词","子关键词"};
			String[] fields = {"keyWordRoot","keyWordSecond"};
			ByteArrayOutputStream bos = ExcelUtilXSSF.getInstance().exportExcelOutputStreamByReflect(list, titles, fields);
		    response.reset(); // 非常重要
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=keyword.xlsx;");
		    OutputStream out = response.getOutputStream();
		    out.write(bos.toByteArray());
	        out.flush();
	        out.close();
		}else {
			//重定向到列表页面
			//response.sendRedirect("/amazon/list.do");
			response.sendRedirect("/amazon-sup/swagger-ui.html#");
		}
	}
	
}
