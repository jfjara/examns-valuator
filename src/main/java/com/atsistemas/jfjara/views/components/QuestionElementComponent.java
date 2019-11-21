package com.atsistemas.jfjara.views.components;

import com.atsistemas.jfjara.views.model.Answer;
import com.atsistemas.jfjara.views.model.Question;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class QuestionElementComponent extends Div {

	private static final long serialVersionUID = -8849625166847901219L;

	private Question question = null;
	
	public QuestionElementComponent(Question question) {
		this.question = question;
		initComponents();
	}
	
	private void initComponents() {
		VerticalLayout layout = new VerticalLayout();
		Label questionLabel = new Label();
		questionLabel.setText(question.getText());
		//questionLabel.setSizeFull();
		layout.add(questionLabel);
		for (Answer answer : question.getAnswers()) {
			layout.add(new AnswersElementComponent(answer, false));
		}	
		add(layout);
	}
	
}
