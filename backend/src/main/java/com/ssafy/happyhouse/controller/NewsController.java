package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.News;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/list")
	public ResponseEntity<List<News>> getNews(PageBean pageBean) throws SQLException{
		logger.debug("....................getNews");
		return new ResponseEntity<List<News>>(newsService.getNews(pageBean), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> insertNews() throws SQLException {
		logger.debug("....................insertNews");
		if(newsService.insertNews()) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cnt")
	public ResponseEntity<?> getCnt() throws SQLException {
		int cnt = newsService.getCount();
		if(cnt > 0) {
			return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
}
