package com.me.bookmymovie.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Table;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("deprecation")
@Entity
@Table(appliesTo="Booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BOOKING_ID")
	private long bookingId;
	
	@Column(name= "BOOK_DATE")
	@Temporal(TemporalType.DATE)
	private Date bookDate;
	
	@ManyToOne()
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
	 
	@ManyToOne()
	@JoinColumn(name = "SHOW_ID")
	private Show show;
	 
	@Column(name="BOOKING_TOTAL")
	private double total;
	 
	@Column(name="BOOKING_STATUS")
	private String bookingStatus;
	
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER,mappedBy="booking", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Seat> seats = new ArrayList<Seat>();
	
	public void addSeat(Seat seat) {
		this.seats.add(seat);
		seat.setBooking(this);
	}
	
	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(Double amount) {
		this.total = amount;
	}

	public long getBookingId() {
		return bookingId;
	}


	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}


	public Date getBookDate() {
		return bookDate;
	}


	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<Seat> getSeats() {
		return seats;
	}


	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
