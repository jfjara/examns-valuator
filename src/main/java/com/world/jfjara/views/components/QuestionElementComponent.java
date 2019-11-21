package com.world.jfjara.views.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.world.jfjara.views.model.Answer;
import com.world.jfjara.views.model.Question;


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
		questionLabel.setText(question.getNumber() + ".- " + question.getText());
		//questionLabel.setSizeFull();
		layout.add(questionLabel);
		for (Answer answer : question.getAnswers()) {
			layout.add(new AnswersElementComponent(answer, false));
		}			
		add(layout);
	}
	
}
