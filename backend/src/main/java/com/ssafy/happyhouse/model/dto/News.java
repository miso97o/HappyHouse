package com.ssafy.happyhouse.model.dto;

public class News {
	private int newsno;
	private String newslink;
	private String newstitle;
	private String newscontent;
	
	public News() {
		super();
	}

	public News(int newsno, String newslink, String newstitle, String newscontent) {
		super();
		this.newsno = newsno;
		this.newslink = newslink;
		this.newstitle = newstitle;
		this.newscontent = newscontent;
	}

	public int getNewsno() {
		return newsno;
	}

	public void setNewsno(int newsno) {
		this.newsno = newsno;
	}

	public String getNewslink() {
		return newslink;
	}

	public void setNewslink(String newslink) {
		this.newslink = newslink;
	}

	public String getNewstitle() {
		return newstitle;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	public String getNewscontent() {
		return newscontent;
	}

	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}

	@Override
	public String toString() {
		return "News [newsno=" + newsno + ", newslink=" + newslink + ", newstitle=" + newstitle + ", newscontent="
				+ newscontent + "]";
	}
	
}
