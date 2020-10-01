package com.me.bookmymovie.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Table;

@SuppressWarnings("deprecation")
@Entity
@javax.persistence.Table(name="Seats")

public class Seat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SEAT_ID")
	private long seatId;
	
	@Column(name="SEAT_NUMBER")
	private String seatNumber;
	
	@Column(name="SEAT_PRICE")
	private Double seatPrice;

	@ManyToOne()
	@JoinColumn(name = "SHOW_ID")
	private Show show;
	 
	@ManyToOne()
	@JoinColumn(name = "BOOKING_ID")
	private Booking booking;

	public long getSeatId() {
		return seatId;
	}

	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Double getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(Double seatPrice) {
		this.seatPrice = seatPrice;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}	 
}

