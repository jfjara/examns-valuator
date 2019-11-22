package com.world.jfjara.views.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.world.jfjara.container.QuestionsContainer;
import com.world.jfjara.views.model.Answer;
import com.world.jfjara.views.model.Question;

@Component("QuestionElementComponent")
@Scope("prototype")
public class QuestionElementComponent extends Div {

	@Autowired
	private QuestionsContainer questionContainer;
	
	private static final long serialVersionUID = -8849625166847901219L;
	
	private Question question;
	VerticalLayout layout;
	
	public QuestionElementComponent() {
		
	}
	
	public void initComponents(Question question, boolean activate) {
		this.question = question;
		layout = new VerticalLayout();
		HorizontalLayout hLayout = new HorizontalLayout();
		Label questionLabel = new Label();
		questionLabel.setText(question.getNumber() + ".- " + question.getText());
		Icon deleteIcon = VaadinIcon.TRASH.create();
		deleteIcon.addClickListener(event -> {
			removeQuestionEvent(question);
		});
		hLayout.add(questionLabel);
		
		if (activate) {
			hLayout.add(deleteIcon);
		}
		
		layout.add(hLayout);
		
		for (Answer answer : question.getAnswers()) {
			layout.add(new AnswersElementComponent(answer, false, activate));
		}			
		
		add(layout);
	}

	private void removeQuestionEvent(Question question) {
		HorizontalLayout hLayoutParent = (HorizontalLayout) getParent().get();
		hLayoutParent.remove(this);
		questionContainer.remove(question);
	}

	public Question getQuestionResponse() {
		for (int i = 0; i < layout.getComponentCount() ; i++) {
			com.vaadin.flow.component.Component c = layout.getComponentAt(i);
			if (c instanceof AnswersElementComponent) {
				AnswersElementComponent answerComponent = (AnswersElementComponent) c;
				Long idAnswer = answerComponent.getAnswer().getId();
				boolean value = answerComponent.getAnswerResponse();
				question.setAnswerResponse(idAnswer, value);
			}
		}
		return question;
	}
	
	public Question getQuestion() {
		return question;
	}
	
}
