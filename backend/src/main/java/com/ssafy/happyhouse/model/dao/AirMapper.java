package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Air;
import com.ssafy.happyhouse.model.dto.PageBean;
@Mapper
public interface AirMapper {
	//public List<Air> getAir(String dong);
	public List<Air> getAir2(String gugun);
	public int insertAir(Air air);
}
