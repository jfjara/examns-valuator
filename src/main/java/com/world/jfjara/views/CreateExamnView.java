package com.world.jfjara.views;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.world.jfjara.views.components.ExamPreviewComponent;
import com.world.jfjara.views.components.QuestionsComponent;

@Component
@Route("create")
public class CreateExamnView extends VerticalLayout {

	private static final long serialVersionUID = 1644040249938810654L;
	
	
	@Autowired
	private QuestionsComponent questionsDiv;
		
	@Autowired
	private ExamPreviewComponent previewDiv;
	
	@PostConstruct
	private void initComponents() {
		
		H1 header = new H1("Edición de prueba teórica");
		
		Tab tabCreateQuestions = new Tab("Crear Pregunta");
		questionsDiv.setVisible(false);
		
		Tab tabPreview = new Tab("Previsualizar exámen");
		previewDiv.setVisible(false);
		
		Map<Tab, Div> tabsToPages = new HashMap<>();
		tabsToPages.put(tabCreateQuestions, questionsDiv);
		tabsToPages.put(tabPreview, previewDiv);
		
		Tabs tabs = new Tabs(tabCreateQuestions, tabPreview);
		
		Div pages = new Div(questionsDiv, previewDiv);
		Set<Div> pagesShown = Stream.of(questionsDiv)
		        .collect(Collectors.toSet());
		tabs.addSelectedChangeListener(event -> {
		    pagesShown.forEach(page -> page.setVisible(false));
		    pagesShown.clear();
		    Div selectedPage = tabsToPages.get(tabs.getSelectedTab());
		    if (selectedPage instanceof ExamPreviewComponent) {
		    	((ExamPreviewComponent)selectedPage).refresh();
		    }
		    selectedPage.setVisible(true);
		    pagesShown.add(selectedPage);
		});
		
		add(header, tabs, pages);
		
	}
	

}
