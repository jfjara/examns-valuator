package com.world.jfjara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.world.jfjara.repository.ExamRepository;
import com.world.jfjara.service.QuestionService;
import com.world.jfjara.views.model.Exam;
import com.world.jfjara.views.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private ExamRepository examRepository;
	
	@Override
	public Exam save(List<Question> questions) {
		Exam exam = new Exam();
		exam.setQuestions(questions);
		examRepository.save(exam);		
		return exam;
	}

}
