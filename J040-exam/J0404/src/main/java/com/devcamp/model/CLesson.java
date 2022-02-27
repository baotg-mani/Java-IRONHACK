package com.devcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lessons")
public class CLesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Must be has lessonCode!!")
	@Column(name = "lesson_code", unique = true)
	private String lessonCode;

	@NotEmpty(message = "Must be has lessonName!!")
	@Column(name = "lesson_name")
	private String lessonName;

	@NotEmpty(message = "Must be has introduction of lesson!!")
	@Column(name = "introduction")
	private String introduction;

	@NotNull(message = "Must be has page number of lesson!!")
	@Column(name = "page")
	private long page;

	@ManyToOne
	@JsonIgnoreProperties(value = "lessons")
//	@JsonBackReference
	@JoinColumn(name = "chapter_id")
	private CChapter chapter;

	public CLesson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CLesson(long id, String lessonCode, String lessonName, String introduction, long page, CChapter chapter) {
		super();
		this.id = id;
		this.lessonCode = lessonCode;
		this.lessonName = lessonName;
		this.introduction = introduction;
		this.page = page;
		this.chapter = chapter;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLessonCode() {
		return lessonCode;
	}

	public void setLessonCode(String lessonCode) {
		this.lessonCode = lessonCode;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public CChapter getChapter() {
		return chapter;
	}

	public void setChapter(CChapter chapter) {
		this.chapter = chapter;
	}

}
