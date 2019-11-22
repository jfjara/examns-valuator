package com.world.jfjara.views;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@Component
@Route("examcreated")
public class ExamCreatedView extends VerticalLayout implements HasUrlParameter<String> {

	private static final long serialVersionUID = 6335385033370316741L;
	
	private void initComponents(Long id) {		
		H1 header = new H1("Se ha creado el examen con identificador %1. Muchas Gracias!".replace("%1", id.toString()));
		Button createExamButton = new Button("Crear otro examen", event ->  {
			navigateToCreateExam();
		});		
		add(header, createExamButton);		
	}

	private void navigateToCreateExam() {
		UI.getCurrent().navigate("create");		
	}

	@Override
	public void setParameter(BeforeEvent event, String id) {
		initComponents(Long.parseLong(id));		
	}
	
	
}
