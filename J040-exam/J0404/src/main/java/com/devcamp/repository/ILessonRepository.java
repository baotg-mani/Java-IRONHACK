package com.devcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.model.CLesson;

public interface ILessonRepository extends JpaRepository<CLesson, Long> {
	public CLesson findByLessonCode(String lessonCode);
}
