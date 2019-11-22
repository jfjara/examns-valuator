package com.world.jfjara.views;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@Component
@Route("finish")
public class FinishView extends VerticalLayout  {
	
	private static final long serialVersionUID = 4715034976626993445L;

	@PostConstruct
	private void initComponents() {		
		H1 header = new H1("Se ha enviado la prueba con éxito. Muchas Gracias!");
		add(header);		
	}
	
}
