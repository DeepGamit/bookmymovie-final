package com.me.bookmymovie.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.FetchType;


@Entity
@Table(appliesTo="Actor")
public class Actor implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACTOR_ID", updatable = false, nullable = false)
	
	private long actorID;
	
	
	@Column(name="ACTOR_NAME")
	private String actorName;
	
	@JsonIgnore
	@ManyToMany(mappedBy="cast", cascade= CascadeType.ALL)
	private Set<Movie> movies = new HashSet<Movie>();

	public long getActorID() {
		return actorID;
	}

	public void setActorID(long actorID) {
		this.actorID = actorID;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
	public void addMovie(Movie m) {
		this.movies.add(m);
		m.getCast().add(this);
	}

	public void removeMovie(Movie m) {
		this.movies.remove(m);
		m.getCast().remove(this);
	}
	
}
