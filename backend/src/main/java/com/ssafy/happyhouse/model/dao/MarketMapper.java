package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Market;
import com.ssafy.happyhouse.model.dto.PageBean;
@Mapper
public interface MarketMapper {
	public List<Market> getMarket(String dong);
	public int insertMarket(Market market);
}
