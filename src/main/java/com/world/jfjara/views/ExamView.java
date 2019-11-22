package com.world.jfjara.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.world.jfjara.service.ExamService;
import com.world.jfjara.views.components.QuestionElementComponent;
import com.world.jfjara.views.model.Exam;
import com.world.jfjara.views.model.Question;

@Component
@Route("exam")
public class ExamView extends VerticalLayout implements HasUrlParameter<Long> {

	private static final long serialVersionUID = 1977741833939630099L;
	@Autowired
	private ExamService service;
	
	@Override
	public void setParameter(BeforeEvent event, Long idExam) {
		Exam exam = service.findExam(idExam);
		initComponents(exam);
	}

	private void initComponents(Exam exam) {
	
		VerticalLayout layout = new VerticalLayout();
		
		//TODO: nombre alumno
		
		for (Question question : exam.getQuestions()) {
			QuestionElementComponent questionDiv = new QuestionElementComponent(question, true);
			layout.add(questionDiv);
		}	
		Button saveButton = new Button("Guardar", event ->  {
			//valida que haya al menos una pregunta
			//guardar con respuestas
		});		
		layout.add(saveButton);
		add(layout);
		
	}
	
	

}
