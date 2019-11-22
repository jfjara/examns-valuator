package com.world.jfjara.service;

import org.springframework.stereotype.Service;

import com.world.jfjara.views.model.ExamAlumn;

@Service
public interface ExamAlumnService {
	
	public ExamAlumn save(ExamAlumn exam);
}
