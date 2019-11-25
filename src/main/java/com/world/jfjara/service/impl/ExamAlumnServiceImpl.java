package com.world.jfjara.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.world.jfjara.repository.ExamAlumnRepository;
import com.world.jfjara.service.ExamAlumnService;
import com.world.jfjara.views.model.ExamAlumn;

@Service
public class ExamAlumnServiceImpl implements ExamAlumnService {

	@Autowired
	private ExamAlumnRepository repository;
	
	@Override
	public ExamAlumn save(ExamAlumn exam) {		
		repository.save(exam);	
		return exam;
	}

	@Override
	public ExamAlumn findExamByDni(String dni, Long id) {
		return repository.findByIdAndDni(id, dni);		
	}

	
	
}
