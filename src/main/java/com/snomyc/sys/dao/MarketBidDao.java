package com.snomyc.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.snomyc.sys.bean.MarketBid;


public interface MarketBidDao extends JpaRepository<MarketBid, String> {
	
	@Query(value="delete from market_bid where 1=1", nativeQuery = true)
	@Modifying
	public void deleteAll();
	
	@Query(value="update market_bid set company = ?2 where group_num = ?1", nativeQuery = true)
	@Modifying
	public void updateByGroupNum(int groupNum,String company);
	
	
	@Query(value="select * from market_bid s where s.group_num = ?1 and s.`year` = "
			+ " (select mb.`year` from market_bid mb where mb.is_finish = 0 GROUP BY mb.`year` ORDER BY mb.`year` asc limit 1) "
			+ " ORDER BY s.num asc", nativeQuery = true)
	public List<MarketBid> findByGroupNumAndYear(int groupNum);
}
