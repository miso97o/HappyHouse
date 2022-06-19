package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.dto.Qna;

public interface QnaService {
	public List<Qna> selectQna(PageBean pageBean);
	public Qna getQna(int qnano);
	public int getCnt();
	public boolean createQna(Qna qna);
	public boolean updateQna(Qna qna);
	public boolean deleteQna(int qnano);
}
