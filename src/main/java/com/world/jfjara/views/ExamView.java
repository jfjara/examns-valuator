package com.world.jfjara.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.world.jfjara.service.ExamAlumnService;
import com.world.jfjara.service.ExamService;
import com.world.jfjara.views.components.QuestionElementComponent;
import com.world.jfjara.views.model.Exam;
import com.world.jfjara.views.model.ExamAlumn;
import com.world.jfjara.views.model.Question;

@UIScope
@Component
@Route("exam")
public class ExamView extends VerticalLayout implements HasUrlParameter<Long> {

	private static final long serialVersionUID = 1977741833939630099L;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ExamService service;

	@Autowired
	private ExamAlumnService examAlumnService;

	@Override
	public void setParameter(BeforeEvent event, Long idExam) {
		Exam exam = service.findExamWithoutResponses(idExam);
		initComponents(exam);
	}

	private void initComponents(Exam exam) {
		VerticalLayout layout = new VerticalLayout();
		TextField alumnNameText = new TextField();
		alumnNameText.setPlaceholder("Nombre del alumno");
		layout.add(alumnNameText);

		for (Question question : exam.getQuestions()) {
			QuestionElementComponent questionDiv = (QuestionElementComponent) applicationContext
					.getBean("QuestionElementComponent");
			questionDiv.initComponents(question, true);
			layout.add(questionDiv);
		}
		Button saveButton = new Button("Guardar", event -> {
			saveExamEvent(exam, layout, alumnNameText);
		});
		layout.add(saveButton);
		add(layout);

	}

	private void saveExamEvent(Exam exam, VerticalLayout layout, TextField alumnNameText) {

		if (alumnNameText.getValue().trim().isEmpty()) {
			showMessageDialog();
		} else {
			ExamAlumn examAlumn = new ExamAlumn();
			examAlumn.setAlumnName(alumnNameText.getValue());
			for (int i = 0; i < layout.getComponentCount(); i++) {
				com.vaadin.flow.component.Component c = layout.getComponentAt(i);

				if (c instanceof QuestionElementComponent) {
					QuestionElementComponent questionComponent = (QuestionElementComponent) c;
					exam.setQuestionResponse(questionComponent.getQuestion().getId(),
							questionComponent.getQuestionResponse());
				}
			}
			examAlumn.setExam(exam);
			ExamAlumn examStored = examAlumnService.save(examAlumn);
			if (examStored == null) {
				UI.getCurrent().navigate("error");
			} else {			
				UI.getCurrent().navigate("finish");
			}
		}
	}

	private void showMessageDialog() {
		MessageDialog dialog = new MessageDialog("Debes introducir tu nombre en la casilla");
		dialog.open();
	}

}
