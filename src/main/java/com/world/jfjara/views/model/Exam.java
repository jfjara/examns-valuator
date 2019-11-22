package com.world.jfjara.views.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Exam")
public class Exam {

	@Id
	private long id = new Date().getTime();
	private List<Question> questions;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public void setQuestionResponse(long id, Question questionResponse) {
		// TODO Auto-generated method stub
		//buscar la question y reemplazarla
		for (Question question : questions) {
			if (id == question.getId())  {
				//TODO: reemplazar las respuestas
				question.setAnswers(questionResponse.getAnswers());
			}
		}
	}
	
	
	
}
