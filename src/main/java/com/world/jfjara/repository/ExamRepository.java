package com.world.jfjara.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.world.jfjara.views.model.Exam;

public interface ExamRepository extends MongoRepository<Exam, Long> {

}
