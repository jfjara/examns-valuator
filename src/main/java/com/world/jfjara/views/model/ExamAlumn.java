package com.world.jfjara.views.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ExamAlumn")
public class ExamAlumn {

	@Id
	private Long id = new Date().getTime();
	private Exam exam;
	private String alumnName;
	private String dni;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public String getAlumnName() {
		return alumnName;
	}
	public void setAlumnName(String alumnName) {
		this.alumnName = alumnName;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
}
