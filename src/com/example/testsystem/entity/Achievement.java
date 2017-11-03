package com.example.testsystem.entity;

public class Achievement {
	private String information;
	private String time;
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Achievement(String information, String time) {
		super();
		this.information = information;
		this.time = time;
	}
}
