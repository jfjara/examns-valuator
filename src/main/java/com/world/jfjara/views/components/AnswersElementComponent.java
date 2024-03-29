package com.world.jfjara.views.components;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.world.jfjara.utils.LetterSequence;
import com.world.jfjara.views.model.Answer;

public class AnswersElementComponent extends Div {

	private static final long serialVersionUID = 7260517562183140128L;

	private Answer answer = null;
	private Checkbox correctCheckBox;

	public AnswersElementComponent(Answer answer, boolean showOperations, boolean activate) {
		this.answer = answer;
		initComponents(showOperations, activate);
	}

	private void initComponents(boolean showOperations, boolean activate) {
		HorizontalLayout layout = new HorizontalLayout();
		Label answerLabel = new Label();
		answerLabel.setText(LetterSequence.transformToChar(answer.getNumber()) + " " + answer.getText());
		correctCheckBox = new Checkbox(answer.isCorrect());
		correctCheckBox.setEnabled(activate);
		
		if (activate) {
			correctCheckBox.setValue(false);
		}
		
		Icon deleteIcon = VaadinIcon.TRASH.create();
		deleteIcon.addClickListener(event -> {
			removeAnswerEvent();
		});
		layout.add(correctCheckBox, answerLabel);
		if (showOperations) {
			layout.add(deleteIcon);
		}
		add(layout);
	}

	private void removeAnswerEvent() {
		Div parent = (Div) getParent().get();
		parent.remove(this);
	}

	public Answer getAnswer() {
		return answer;
	}

	public boolean getAnswerResponse() {
		return correctCheckBox.getValue();
	}
}
