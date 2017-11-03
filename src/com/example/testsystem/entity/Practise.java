package com.example.testsystem.entity;

public class Practise {
	private int id;
	private String topic;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String correct_option;
	private String explain;
	public Practise(int id, String topic, String optionA, String optionB,
			String optionC, String optionD, String correct_option,
			String explain) {
		super();
		this.id = id;
		this.topic = topic;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.correct_option = correct_option;
		this.explain = explain;
	}
	
	public Practise(int id, String topic, String optionA, String optionB,
			String optionC, String optionD, String correct_option) {
		super();
		this.id = id;
		this.topic = topic;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.correct_option = correct_option;
	}
	
	public Practise(String topic, String optionA, String optionB,
			String optionC, String optionD, String correct_option) {
		super();
		this.topic = topic;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.correct_option = correct_option;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getCorrect_option() {
		return correct_option;
	}
	public void setCorrect_option(String correct_option) {
		this.correct_option = correct_option;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", topic=" + topic + ", optionA=" + optionA
				+ ", optionB=" + optionB + ", optionC=" + optionC
				+ ", optionD=" + optionD + ", correct_option=" + correct_option
				+ ", explain=" + explain + "]";
	}
}
