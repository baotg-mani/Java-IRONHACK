package com.devcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.model.CChapter;

public interface IChapterRepository extends JpaRepository<CChapter, Long> {
	public CChapter findByChapterCode(String chapterCOde);
}
