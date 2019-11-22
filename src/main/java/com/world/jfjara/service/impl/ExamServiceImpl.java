package com.world.jfjara.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.world.jfjara.repository.ExamRepository;
import com.world.jfjara.service.ExamService;
import com.world.jfjara.views.model.Exam;
import com.world.jfjara.views.model.Question;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository repository;
	
	@Override
	public Exam findExam(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Exam findExamWithoutResponses(Long id) {
		Exam exam = findExam(id);
		for (Question question : exam.getQuestions()) {
			question.deselectAllAnswers();
		}
		return exam;
	}
	

	
}
