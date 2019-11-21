package com.world.jfjara.views.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

	private String text;
	private List<Answer> answers;
	private int number;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public void addAnswer(Answer answer) {
		if (answers == null) {
			answers = new ArrayList<>();
		}
		answer.setNumber(answers.size() + 1);
		answers.add(answer);
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
