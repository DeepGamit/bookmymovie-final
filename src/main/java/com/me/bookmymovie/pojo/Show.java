package com.me.bookmymovie.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Table;

@Entity
@javax.persistence.Table(name="Shows")
public class Show{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SHOW_ID")
	private long showID;
	
	@Column(name= "SHOW_DATE")
	@Temporal(TemporalType.DATE)
	private Date showDate;
	
	@Column(name= "SHOW_TIMINGS")
	@Temporal(TemporalType.TIME)
	private Date showTime;
	
	@Column(name="SHOW_PRICE")
	private double showPrice;
	
	@Column(name="NUMBER_OF_ROWS")
	private int numberOfRows;
	
	@Column(name="NUMBER_OF_SEAT")
	private int numberOfSeat;
	
	@Column(name="SCREEN_NUMBER")
	private int screenNumber;
	
	@ManyToOne()
	@JoinColumn(name="MOVIE_ID")
	private Movie movie;
	
	@ManyToOne()
	@JoinColumn(name = "THEATRE_ID")
	private Theatre theatre;
	 
	@OneToMany(mappedBy="show", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Seat> seats = new ArrayList<Seat>();
	 
	@OneToMany(mappedBy="show", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<Booking>();
	 
	 
	public void addBooking(Booking booking) {
		this.bookings.add(booking);
		booking.setShow(this);
	}

	 
	public void addSeat(Seat seat) {
		this.seats.add(seat);
		seat.setShow(this);
	}

	public long getShowID() {
		return showID;
	}
	
	public int getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(int screenNumber) {
		this.screenNumber = screenNumber;
	}

	public void setShowID(long showID) {
		this.showID = showID;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Date getShowTime() {
		return showTime;
	}

	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}


	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getNumberOfSeat() {
		return numberOfSeat;
	}

	public void setNumberOfSeat(int numberOfSeat) {
		this.numberOfSeat = numberOfSeat;
	}

	public double getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(double showPrice) {
		this.showPrice = showPrice;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	} 

}

