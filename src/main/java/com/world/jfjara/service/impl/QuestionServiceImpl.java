package com.world.jfjara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.world.jfjara.repository.QuestionsRepository;
import com.world.jfjara.service.QuestionService;
import com.world.jfjara.views.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionsRepository repository;
	
	@Override
	public void save(List<Question> questions) {
		repository.saveAll(questions);		
	}

}
