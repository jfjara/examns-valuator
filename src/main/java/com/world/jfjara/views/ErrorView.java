package com.world.jfjara.views;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@Component
@Route("error")
public class ErrorView extends VerticalLayout {

	private static final long serialVersionUID = -1797226942710624337L;

	@PostConstruct
	private void initComponents() {		
		H1 header = new H1("Ha habido un error. Ponte en contacto con el profesor.");
		add(header);		
	}
	
}
