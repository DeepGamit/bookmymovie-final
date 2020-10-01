package com.me.bookmymovie.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@SuppressWarnings("deprecation")
@Entity
@Table(appliesTo="Theatre")
public class Theatre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "THEATRE_ID")
    private long theatreId;
	
	@Column(name="THEATRE_Name")
	private String theatreName;
	
	@Column(name="THEATRE_CITY")
	private String theatreCity;
	
	@Column(name="THEATRE_ADDRESS")
	private String theatreAddress;

	@JsonIgnore
	@OneToMany(mappedBy="theatre", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Show> shows = new ArrayList<Show>();

	@OneToOne
    @JoinColumn(name="USER_ID")
    private User user;
	
	public void addShow(Show show) {
		this.shows.add(show);
		show.setTheatre(this);
	}

	public long getTheatreId() {
		return theatreId;
	}
	
	public void setTheatreId(long theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTheatreCity() {
		return theatreCity;
	}

	public void setTheatreCity(String theatreCity) {
		this.theatreCity = theatreCity;
	}

	public String getTheatreAddress() {
		return theatreAddress;
	}

	public void setTheatreAddress(String theatreAddress) {
		this.theatreAddress = theatreAddress;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> show) {
		this.shows = show;
	}
}