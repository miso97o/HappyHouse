package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.dto.Qna;
@Mapper
public interface QnaMapper {
	public List<Qna> selectQna(PageBean pageBean);
	public Qna getQna(int qnano);
	public int getCnt();
	public int createQna(Qna qna);
	public int updateQna(Qna qna);
	public int deleteQna(int qnano);
}
