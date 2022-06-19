package com.ssafy.happyhouse.model.dto;


public class Air {

	private String station;
	private String date;
	private int pollution;

	public Air() {}
	
	public Air(String station, String date, int pollution) {
		super();
		this.station = station;
		this.date = date;
		this.pollution = pollution;
	}
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPollution() {
		return pollution;
	}
	public void setPollution(int pollution) {
		this.pollution = pollution;
	}

	@Override
	public String toString() {
		return "Air [station=" + station + ", date=" + date + ", pollution=" + pollution + "]";
	}
	
	
	
}
