package com.snomyc.sys.dao;

import java.util.List;
import java.util.Map;

public interface MarketBidQueryDao {
	
	public List<Map<String,Object>> findCompanySales(String year);
}
