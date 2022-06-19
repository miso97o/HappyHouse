package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.happyhouse.model.service.AirService;
import com.ssafy.happyhouse.model.service.MarketService;

@Controller
public class MainController {

	@Autowired
	MarketService marketService;
	@Autowired
	AirService airService;
	
	@GetMapping("/")
	public String index() throws Exception {
		//airService.insertAir();
		//marketService.insertMarket();
		return "index";
	}
	
	@GetMapping("/qnaboard")
	public String qna() {
		return "qnaboard";
	}
	
}
