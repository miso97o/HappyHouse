package com.ssafy.happyhouse.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.NewsMapper;
import com.ssafy.happyhouse.model.dto.News;
import com.ssafy.happyhouse.model.dto.PageBean;
@Service
public class NewsServiceImp implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public List<News> getNews(PageBean pageBean) {
		return newsMapper.getNews(pageBean);
	}

	@Override
	public boolean insertNews() {
		Document doc = null;
		List<News> list = new ArrayList<News>();
		String url = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&page=1";
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements elements = doc.select("div.list_body>ul>li");	//기사를 이루는 li들을 가져옴
//		System.out.println(elements.size());		//20개
		for(int i=0; i<elements.size(); ++i) {
			News tmp = new News();					//디비에 넣을 뉴스 객체
			Elements atags = elements.get(i).select("a");	//a태그들을 가져옴
//			System.out.println(el);
			Element atag = atags.size() > 1 ? atags.get(1) : atags.get(0);			//a태그중에 두번째
			tmp.setNewslink(atag.attr("href"));		//기사의 링크 저장
			tmp.setNewstitle(atag.text());			//기사 제목 저장
			Elements spans = elements.get(i).select("span");	//기사 본문
			tmp.setNewscontent(spans.get(0).text());	//저장
//			System.out.println(tmp);
			newsMapper.insertNews(tmp);
		}
		
		return true;
	}

	@Override
	public int getCount() {
		return newsMapper.getCount();
	}
}
