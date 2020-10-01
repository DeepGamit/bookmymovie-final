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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(appliesTo="Language")
public class Language implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LANGUAGE_ID", updatable = false, nullable = false)
	private long languageID;
	
	@Column(name="LANGUAGE_NAME")
	private String languageName;
	
	@Fetch(FetchMode.SELECT)
	@JsonBackReference
	@ManyToMany(mappedBy="language", cascade= CascadeType.ALL)
	private Set<Movie> movies = new HashSet<Movie>();

	public long getLanguageID() {
		return languageID;
	}

	public void setLanguageID(long languageID) {
		this.languageID = languageID;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public void addMovie(Movie m) {
		this.movies.add(m);
		m.getLanguage().add(this);
	}

	public void removeMovie(Movie m) {
		this.movies.remove(m);
		m.getLanguage().remove(this);
	}
	
}