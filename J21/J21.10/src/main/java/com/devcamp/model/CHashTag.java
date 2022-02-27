package com.devcamp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class CHashTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "tags")
	private Set<CPost> posts;

	public CHashTag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CHashTag(long id, String name, Set<CPost> posts) {
		super();
		this.id = id;
		this.name = name;
		this.posts = posts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CPost> getPosts() {
		return posts;
	}

	public void setPosts(Set<CPost> posts) {
		this.posts = posts;
	}

}
