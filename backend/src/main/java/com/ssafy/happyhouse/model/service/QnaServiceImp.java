package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.QnaMapper;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.dto.Qna;
@Service
public class QnaServiceImp implements QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	
	@Override
	public List<Qna> selectQna(PageBean pageBean) {
		return qnaMapper.selectQna(pageBean);
	}
	
	@Override
	public Qna getQna(int qnano) {
		return qnaMapper.getQna(qnano);
	}
	
	@Override
	public int getCnt() {
		return qnaMapper.getCnt();
	}

	@Override
	public boolean createQna(Qna qna) {
		return qnaMapper.createQna(qna) == 1;
	}

	@Override
	public boolean updateQna(Qna qna) {
		return qnaMapper.updateQna(qna) == 1;
	}

	@Override
	public boolean deleteQna(int qnano) {
		return qnaMapper.deleteQna(qnano) == 1;
	}

}
