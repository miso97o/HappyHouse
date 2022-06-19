package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Air;
import com.ssafy.happyhouse.model.dto.PageBean;

public interface AirService {

	//public List<Air> getAir(String dong);
	public List<Air> getAir2(String gugun) throws Exception;
	public boolean insertAir() throws Exception;
}
