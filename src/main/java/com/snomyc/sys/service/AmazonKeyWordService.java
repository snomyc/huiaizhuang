package com.snomyc.sys.service;

import java.util.List;

import com.snomyc.base.service.BaseService;
import com.snomyc.sys.bean.AmazonKeyWord;
public interface AmazonKeyWordService extends BaseService<AmazonKeyWord, String>{

	
	/**
	 * @param keyWordRoot
	 * @return
	
	 * 方法说明:通过关键词词根 保存并获取相关联的关键词集合
	
	 * 创立日期:2018年6月3日 下午5:08:56
	 * 创建人:snomyc
	
	 */
	public List<AmazonKeyWord> findByKeyWordRoot(String keyWordRoot);
	
	/**
	 * @param keyWordRoot
	 * @return
	
	 * 方法说明:通过关键词词根获取相关联的关键词集合
	
	 * 创立日期:2018年6月3日 下午7:05:22
	 * 创建人:snomyc
	
	 */
	public List<AmazonKeyWord> findListByKeyWordRoot(String keyWordRoot);
	
	public void updateKeyWord(String keyWordRoot);
}
