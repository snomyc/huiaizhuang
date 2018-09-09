package com.snomyc.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snomyc.sys.bean.AmazonKeyWord;
@Repository
public interface AmazonKeyWordDao extends JpaRepository<AmazonKeyWord, String> {

	public List<AmazonKeyWord> findByKeyWordRoot(String keyWordRoot);
	
	public int countByKeyWordRootAndKeyWordSecond(String keyWordRoot,String keyWordSecond);
	
}