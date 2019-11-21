package com.atsistemas.jfjara.views.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.atsistemas.jfjara.container.QuestionsContainer;
import com.atsistemas.jfjara.views.model.Question;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@Component
@Scope("prototype")
public class ExamPreviewComponent extends Div {

	private static final long serialVersionUID = -2092651467321256812L;
	
	@Autowired
	private QuestionsContainer questionContainer;
	
	private VerticalLayout layout;
	
	@PostConstruct
	private  void initComponents() {
		layout = new VerticalLayout();
		
		for (Question question : questionContainer.getQuestions()) {
			QuestionElementComponent questionDiv = new QuestionElementComponent(question);
			layout.add(questionDiv);
		}		
		add(layout);
	}
	
	public void refresh() {
		layout.removeAll();
		for (Question question : questionContainer.getQuestions()) {
			QuestionElementComponent questionDiv = new QuestionElementComponent(question);
			layout.add(questionDiv);
		}		
		add(layout);
	}
	
	
}
