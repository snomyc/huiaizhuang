package com.snomyc.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.snomyc.sys.bean.Market;

@Repository
public interface MarketDao extends JpaRepository<Market, String> {

	public int countByOrderByCreateTimeAsc();
	
	public Market findTopByOrderByCreateTimeDesc();
	
}
