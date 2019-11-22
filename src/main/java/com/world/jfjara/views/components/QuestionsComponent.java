package com.world.jfjara.views.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.world.jfjara.container.QuestionsContainer;
import com.world.jfjara.views.model.Answer;
import com.world.jfjara.views.model.Question;

@Component
@Scope("prototype")
public class QuestionsComponent extends Div {

	private static final long serialVersionUID = -2940817512545163330L;

	@Autowired
	private QuestionsContainer questionContainer;
	
	private Question question = new Question();
	
	@PostConstruct
	private void initComponents() {
		VerticalLayout mainLayout = new VerticalLayout();
		
		TextField questionTextField = new TextField();
		questionTextField.setPlaceholder("Introduzca la pregrunta");
		questionTextField.setWidth("600px");
		
		HorizontalLayout responsesLayout = new HorizontalLayout();
		TextField answerTextField = new TextField();
		answerTextField.setPlaceholder("Introduce una respuesta");
		answerTextField.setWidth("500px");
		Checkbox correctAnswerCheckBox = new Checkbox("Es respuesta correcta");
		Div listAnswerDiv = new Div();
		Button createAnswerButton = new Button("Añadir", event ->  {
			//TODO: validaciones
			Answer answer = new Answer();
			answer.setText(answerTextField.getValue());
			answer.setCorrect(correctAnswerCheckBox.getValue());
			question.addAnswer(answer);		
			AnswersElementComponent answerComponent = new AnswersElementComponent(answer, true, false);
			listAnswerDiv.add(answerComponent);
			answerTextField.clear();
			answerTextField.setPlaceholder("Introduce una respuesta");
			correctAnswerCheckBox.setValue(false);			
		});		
		Button createQuestionButton = new Button("Crear pregunta", event ->  {	
			question.setText(questionTextField.getValue());
			questionContainer.add(question);
			question = new Question();
			listAnswerDiv.removeAll();			
			questionTextField.clear();
			//listAnswerDiv = new Div();
			
		});
		responsesLayout.add(answerTextField,correctAnswerCheckBox, createAnswerButton);
		
		mainLayout.add(questionTextField, responsesLayout, listAnswerDiv, createQuestionButton);
		
		add(mainLayout);
	}
	
}
