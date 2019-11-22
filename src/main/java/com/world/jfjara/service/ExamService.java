package com.world.jfjara.service;

import org.springframework.stereotype.Service;

import com.world.jfjara.views.model.Exam;

@Service
public interface ExamService {

	public Exam findExam(Long id);
}
