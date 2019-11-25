package com.world.jfjara.views.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.world.jfjara.container.QuestionsContainer;
import com.world.jfjara.service.QuestionService;
import com.world.jfjara.views.MessageDialog;
import com.world.jfjara.views.model.Exam;
import com.world.jfjara.views.model.Question;

@Component
@Scope("prototype")
public class ExamPreviewComponent extends Div {

	private static final long serialVersionUID = -2092651467321256812L;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private QuestionsContainer questionContainer;

	@Autowired
	private QuestionService service;

	private VerticalLayout layout = new VerticalLayout();

	@PostConstruct
	private void initComponents() {
		layout.removeAll();
		for (Question question : questionContainer.getQuestions()) {
			HorizontalLayout hLayout = new HorizontalLayout();
			QuestionElementComponent questionDiv = (QuestionElementComponent) applicationContext
					.getBean("QuestionElementComponent");
			questionDiv.initComponents(question, false, true);
			hLayout.add(questionDiv);
			layout.add(hLayout);
		}
		Button saveButton = new Button("Guardar", event -> {
			saveQuestionsEvent();
		});
		layout.add(saveButton);
		add(layout);
	}

	private void saveQuestionsEvent() {
		if (!questionContainer.getQuestions().isEmpty()) {
			Exam exam = service.save(questionContainer.getQuestions());
			UI.getCurrent().navigate("examcreated/" + exam.getId());
		} else {
			showMessageDialog();
		}
	}

	public void refresh() {
		initComponents();
	}

	private void showMessageDialog() {
		MessageDialog dialog = new MessageDialog("Debe introducir al menos una pregunta");
		dialog.open();
	}

}
