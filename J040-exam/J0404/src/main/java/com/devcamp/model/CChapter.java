package com.devcamp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "chapters")
public class CChapter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Must be has chapterCode!!")
	@Column(name = "chapter_code", unique = true)
	private String chapterCode;

	@NotEmpty(message = "Must be has chapterName!!")
	@Column(name = "chapter_name")
	private String chapterName;

	@NotEmpty(message = "Must be has introduction of chapter!!")
	@Column(name = "introduction")
	private String introduction;

	@NotEmpty(message = "Must be has translator Name!!")
	@Column(name = "translator_name")
	private String translatorName;

	@NotNull(message = "Must be has page number of chapter!!")
	@Column(name = "page")
	private long page;

	@OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
//	@JsonManagedReference
	@JsonIgnoreProperties(value = "chapter")
	private Set<CLesson> lessons;

	public CChapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CChapter(long id, String chapterCode, String chapterName, String introduction, String translatorName,
			long page, Set<CLesson> lessons) {
		super();
		this.id = id;
		this.chapterCode = chapterCode;
		this.chapterName = chapterName;
		this.introduction = introduction;
		this.translatorName = translatorName;
		this.page = page;
		this.lessons = lessons;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChapterCode() {
		return chapterCode;
	}

	public void setChapterCode(String chapterCode) {
		this.chapterCode = chapterCode;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTranslatorName() {
		return translatorName;
	}

	public void setTranslatorName(String translatorName) {
		this.translatorName = translatorName;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public Set<CLesson> getLessons() {
		return lessons;
	}

	public void setLessons(Set<CLesson> lessons) {
		this.lessons = lessons;
	}

}
