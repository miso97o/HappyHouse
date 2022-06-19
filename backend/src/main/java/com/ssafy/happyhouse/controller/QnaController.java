package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.dto.Qna;
import com.ssafy.happyhouse.model.service.QnaService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RequestMapping("/qna")
public class QnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping
	public ResponseEntity<List<Qna>> selectQna(PageBean pageBean) throws SQLException {
		logger.debug("selectQna.................");
		return new ResponseEntity<List<Qna>>(qnaService.selectQna(pageBean), HttpStatus.OK);
	}
	
	@GetMapping("{qnano}")
	public ResponseEntity<Qna> getQna(@PathVariable int qnano) throws SQLException {
		logger.debug("getQna.................");
		return new ResponseEntity<Qna>(qnaService.getQna(qnano), HttpStatus.OK);
	}
	
	@GetMapping("/cnt")
	public ResponseEntity<Integer> getCnt() throws SQLException {
		return new ResponseEntity<Integer>(qnaService.getCnt(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createQna(@RequestBody Qna qna){
		logger.debug("createQna..................");
		if(qnaService.createQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping("{qnano}")
	public ResponseEntity<String> updateQna(@RequestBody Qna qna){
		logger.debug("updateQna...................");
		logger.debug("\n"+qna);
		if (qnaService.updateQna(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{qnano}")
	public ResponseEntity<String> deleteQna(@PathVariable int qnano){
		logger.debug("deleteQna...................");
		if (qnaService.deleteQna(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
