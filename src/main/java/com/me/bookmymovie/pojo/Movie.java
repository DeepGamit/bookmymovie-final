package com.me.bookmymovie.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@SuppressWarnings("deprecation")
@Entity
@Table(appliesTo="Movie")
public class Movie implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MOVIE_ID")
	private long movieID;
	
	@Column(name= "REL_DATE")
	@Temporal(TemporalType.DATE)
	private Date relDate;
	
	@Column(name="MOVIE_LENGTH")
	private String movieLength;
	
	@Column(name="MOVIE_TITLE")
	private String movieTitle;
	
	@Column(name="MOVIE_SYNOPSIS")
	private String movieSynopsis;

	@Column(name="MOVIE_RATING")
	private double movieRating;

	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade =
    {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.REFRESH,
        CascadeType.PERSIST
    })
	@JoinTable(name = "Movie_Language",
	joinColumns = { @JoinColumn(name = "fk_movie") },
	inverseJoinColumns = { @JoinColumn(name = "fk_language") })
	private Set<Language> language = new HashSet<Language>();
	
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade =
    {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.REFRESH,
        CascadeType.PERSIST
    })
	@JoinTable(name = "Movie_Category",
	joinColumns = { @JoinColumn(name = "fk_movie") },
	inverseJoinColumns = { @JoinColumn(name = "fk_category") })
	private Set<Category> category = new HashSet<Category>();
	
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade =
    {
	    CascadeType.DETACH,
	    CascadeType.MERGE,
	    CascadeType.REFRESH,
	    CascadeType.PERSIST
    })
	@JoinTable(name = "Movie_Cast",
	joinColumns = { @JoinColumn(name = "fk_movie") },
	inverseJoinColumns = { @JoinColumn(name = "fk_actor") })
	private Set<Actor> cast = new HashSet<Actor>();
	
	@JsonIgnore
	@OneToMany(mappedBy="movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Show> shows = new ArrayList<Show>();
	
	public void addShow(Show show) {
		this.shows.add(show);
		show.setMovie(this);
	}

	public Set<Actor> getCast() {
		return cast;
	}
	public void setCast(Set<Actor> cast) {
		this.cast = cast;
	}
	public long getMovieID() {
		return movieID;
	}
	public void setMovieID(long movieID) {
		this.movieID = movieID;
	}
	public Date getRelDate() {
		return relDate;
	}
	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}
	public String getMovieLength() {
		return movieLength;
	}
	public void setMovieLength(String movieLength) {
		this.movieLength = movieLength;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getMovieSynopsis() {
		return movieSynopsis;
	}
	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}
	public double getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(double movieRating) {
		this.movieRating = movieRating;
	}
	public Set<Language> getLanguage() {
		return language;
	}
	public void setLanguage(Set<Language> language) {
		this.language = language;
	}
	public Set<Category> getCategory() {
		return category;
	}
	public void setCategory(HashSet<Category> category) {
		this.category = category;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

}
