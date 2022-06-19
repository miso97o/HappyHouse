package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.News;
import com.ssafy.happyhouse.model.dto.PageBean;

public interface NewsService {
	public List<News> getNews(PageBean pageBean);
	public boolean insertNews();
	public int getCount();
}
