package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Interest;
import com.ssafy.happyhouse.model.dto.Member;

@Mapper
public interface MemberDao {
	Member login(Map<String, String> map) throws SQLException;

	Member getMember(String id) throws SQLException;
	
	int signUp(Member member) throws SQLException;

	int update(Member member) throws SQLException;

	int delete(String id) throws SQLException;
	
	List<Interest> interestList(String id) throws Exception;
	
	int addInterest(Map<String, String> map) throws Exception;
	
	int removeInterest(Map<String, String> map) throws Exception;
}
