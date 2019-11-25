package com.world.jfjara.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import com.world.jfjara.service.ExamAlumnService;
import com.world.jfjara.service.ExamService;
import com.world.jfjara.utils.DNIValidator;
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
		HorizontalLayout hLayout = new HorizontalLayout();
		TextField alumnNameText = new TextField();
		alumnNameText.setPlaceholder("Nombre del alumno");
		alumnNameText.setWidth("200px");
		TextField alumnDniText = new TextField();
		alumnDniText.setPlaceholder("DNI del alumno");
		alumnDniText.setWidth("40px");
		hLayout.add(alumnNameText, alumnDniText);
		layout.add(hLayout);

		for (Question question : exam.getQuestions()) {
			QuestionElementComponent questionDiv = (QuestionElementComponent) applicationContext
					.getBean("QuestionElementComponent");
			questionDiv.initComponents(question, true, false);
			layout.add(questionDiv);
		}
		Button saveButton = new Button("Guardar", event -> {
			saveExamEvent(exam, layout, alumnNameText, alumnDniText);
		});
		layout.add(saveButton);
		add(layout);

	}

	private void saveExamEvent(Exam exam, VerticalLayout layout, TextField alumnNameText, TextField alumnDniText) {

		if (alumnNameText.getValue().trim().isEmpty() || alumnDniText.getValue().trim().isEmpty()) {
			showMessageDialog("Debes introducir tu nombre en la casilla");
		} else {			
			if (DNIValidator.validar(alumnDniText.getValue())) {
				
				//se ha examinado ya para ese examen?
				if (examAlumnService.findExamByDni(alumnDniText.getValue(), exam.getId()) == null) {
					ExamAlumn examAlumn = new ExamAlumn();
					examAlumn.setAlumnName(alumnNameText.getValue());
					examAlumn.setDni(alumnDniText.getValue());
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
				} else {
					UI.getCurrent().navigate("error");
				}
			} else {
				showMessageDialog("El DNI es incorrecto");
			}
			
		}
	}

	private void showMessageDialog(String message) {
		MessageDialog dialog = new MessageDialog(message);
		dialog.open();
	}

}
