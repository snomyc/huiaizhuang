package com.snomyc.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.snomyc.base.persistence.PagingHibernateJdbcDao;
import com.snomyc.sys.dao.MarketBidQueryDao;

@Repository
public class MarketBidQueryDaoImpl implements MarketBidQueryDao{

	@Autowired
	private PagingHibernateJdbcDao dao;
	
	@Override
	public List<Map<String, Object>> findCompanySales(String year) {
		StringBuffer selSql = new StringBuffer();
		List<Object> queryParams = new ArrayList<Object>();
		selSql.append("select m.company,SUM(m.bid_num) as sale from market_bid m ");
		selSql.append("where m.`year` = ? GROUP BY m.company");
		queryParams.add(year);
		return dao.findMap(selSql.toString(), queryParams.toArray());
	}

}
