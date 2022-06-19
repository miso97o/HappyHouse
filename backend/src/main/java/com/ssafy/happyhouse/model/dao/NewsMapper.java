package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.News;
import com.ssafy.happyhouse.model.dto.PageBean;
@Mapper
public interface NewsMapper {
	public List<News> getNews(PageBean pageBean);
	public int insertNews(News news);
	public int getCount();
}
