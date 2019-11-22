package com.world.jfjara.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.world.jfjara.views.model.Question;

@Component
@Scope("singleton")
public class QuestionsContainer {

	private List<Question> questions = new ArrayList<>();
	
	public void add(Question question) {
		question.setNumber(questions.size() + 1);
		questions.add(question);
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void remove(Question question) {
		Iterator<Question> it = questions.iterator();
		while (it.hasNext()) {
			Question q = it.next();
			if (q.getId() == question.getId()) {
				it.remove();
				break;
			}
		}		
	}
	
}
