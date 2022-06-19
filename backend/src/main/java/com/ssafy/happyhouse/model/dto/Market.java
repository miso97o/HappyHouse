package com.ssafy.happyhouse.model.dto;

public class Market {

	private String marketName;
	private String marketKinds;
	private String dongCode;
	private String dongName;
	private String lat; //위도
	private String lng; //경도
	
	public Market() {}
	
	public Market(String marketName, String marketKinds, String dongCode, String dongName, String lat, String lng) {
		super();
		this.marketName = marketName;
		this.marketKinds = marketKinds;
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.lat = lat;
		this.lng = lng;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getMarketKinds() {
		return marketKinds;
	}

	public void setMarketKinds(String marketKinds) {
		this.marketKinds = marketKinds;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Market [marketName=" + marketName + ", marketKinds=" + marketKinds + ", dongCode=" + dongCode
				+ ", dongName=" + dongName + ", lat=" + lat + ", lng=" + lng + "]";
	}

	
}
