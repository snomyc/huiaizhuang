package com.snomyc.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.snomyc.sys.bean.Market;


public interface MarketDao extends JpaRepository<Market, String> {

	public int countByOrderByCreateTimeAsc();
	
	public Market findTopByOrderByCreateTimeDesc();
	
}
