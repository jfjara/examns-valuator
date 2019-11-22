package com.world.jfjara.views;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MessageDialog extends Dialog {

	private static final long serialVersionUID = -7837990781010539064L;

	public MessageDialog(String message) {
		setCloseOnEsc(true);
		setCloseOnOutsideClick(true);
		Label messageLabel = new Label(message);
		VerticalLayout layout = new VerticalLayout();
		layout.add(messageLabel);
		NativeButton cancelButton = new NativeButton("Cerrar", event -> {
		    close();
		});
		add(layout, cancelButton);
	}
	
	
}
