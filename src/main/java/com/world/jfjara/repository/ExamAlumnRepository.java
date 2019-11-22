package com.world.jfjara.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.world.jfjara.views.model.ExamAlumn;

public interface ExamAlumnRepository  extends MongoRepository<ExamAlumn, Long> {

}
