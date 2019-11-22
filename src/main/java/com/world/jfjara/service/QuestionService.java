package com.world.jfjara.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.world.jfjara.views.model.Exam;
import com.world.jfjara.views.model.Question;

@Service
public interface QuestionService {

	public Exam save(List<Question> question);
	
}
