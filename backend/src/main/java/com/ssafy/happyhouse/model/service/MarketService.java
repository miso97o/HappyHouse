package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Market;
import com.ssafy.happyhouse.model.dto.PageBean;

public interface MarketService {

	public List<Market> getMarket(String dong);
	public boolean insertMarket() throws Exception;
}
