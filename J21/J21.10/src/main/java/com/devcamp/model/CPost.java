package com.devcamp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class CPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	private CUser createdBy;

	@ManyToMany(fetch = FetchType.LAZY, 
			  cascade = { 
					CascadeType.PERSIST, 
					CascadeType.MERGE 
					})
	@JoinTable( name = "post_tags", 
	 	 joinColumns = { @JoinColumn(name = "post_id") }, 
  inverseJoinColumns = { @JoinColumn(name = "tag_id") } )
	private Set<CHashTag> tags;

	public CPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CPost(long id, String title, String content, CUser createdBy, Set<CHashTag> tags) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.createdBy = createdBy;
		this.tags = tags;
	}

	public Set<CHashTag> getTags() {
		return tags;
	}

	public void setTags(Set<CHashTag> tags) {
		this.tags = tags;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(CUser createdBy) {
		this.createdBy = createdBy;
	}

}
