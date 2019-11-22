package com.world.jfjara.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.world.jfjara.repository.ExamRepository;
import com.world.jfjara.service.ExamService;
import com.world.jfjara.views.model.Exam;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository repository;
	
	@Override
	public Exam findExam(Long id) {
		return repository.findById(id).get();
	}

	
}
