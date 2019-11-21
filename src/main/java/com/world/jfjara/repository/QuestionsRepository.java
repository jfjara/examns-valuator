package com.world.jfjara.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.world.jfjara.views.model.Question;

public interface QuestionsRepository extends MongoRepository<Question, Long> {

	
	
}
