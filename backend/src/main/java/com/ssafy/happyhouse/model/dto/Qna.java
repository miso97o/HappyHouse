package com.ssafy.happyhouse.model.dto;

public class Qna {
	private int qnano;
	private String userid;
	private String question;
	private String answer;
	private int hit;
	private String regtime;
	
	public Qna() {
		super();
	}
	
	public Qna(int qnano, String userid, String question, String answer, int hit, String regtime) {
		super();
		this.qnano = qnano;
		this.userid = userid;
		this.question = question;
		this.answer = answer;
		this.hit = hit;
		this.regtime = regtime;
	}

	public int getQnano() {
		return qnano;
	}

	public void setQnano(int qnano) {
		this.qnano = qnano;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	@Override
	public String toString() {
		return "Qna [qnano=" + qnano + ", userid=" + userid + ", question=" + question + ", answer=" + answer + ", hit="
				+ hit + ", regtime=" + regtime + "]";
	}
	
}
