package com.world.jfjara.views.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.world.jfjara.container.QuestionsContainer;
import com.world.jfjara.service.QuestionService;
import com.world.jfjara.views.model.Question;

@Component
@Scope("prototype")
public class ExamPreviewComponent extends Div {

	private static final long serialVersionUID = -2092651467321256812L;
	
	@Autowired
	private QuestionsContainer questionContainer;
	
	@Autowired
	private QuestionService service;
	
	private VerticalLayout layout = new VerticalLayout();
	
	@PostConstruct
	private  void initComponents() {
		layout.removeAll();
		for (Question question : questionContainer.getQuestions()) {
			QuestionElementComponent questionDiv = new QuestionElementComponent(question);
			layout.add(questionDiv);
		}	
		Button saveButton = new Button("Guardar", event ->  {
			//valida que haya al menos una pregunta
			service.save(questionContainer.getQuestions());
		});		
		layout.add(saveButton);
		add(layout);
	}
	
	public void refresh() {
		initComponents();
	}
	
	
}
