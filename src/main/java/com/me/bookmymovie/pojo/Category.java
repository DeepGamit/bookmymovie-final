package com.me.bookmymovie.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(appliesTo="Category")
public class Category implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CATEGORY_ID", updatable = false, nullable = false)
	private long categoryID;
	
	@Column(name="CATEGORY_NAME")
	private String categoryName;
	
	@JsonIgnore
	@ManyToMany(mappedBy="category", cascade= CascadeType.ALL)
	private Set<Movie> movies = new HashSet<Movie>();

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public void addMovie(Movie m) {
		this.movies.add(m);
		m.getCategory().add(this);
	}

	public void removeMovie(Movie m) {
		this.movies.remove(m);
		m.getCategory().remove(this);
	}
}
