package com.me.bookmymovie.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.me.bookmymovie.pojo.Booking;
import com.me.bookmymovie.pojo.Customer;
import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Seat;
import com.me.bookmymovie.pojo.Show;
import com.me.bookmymovie.pojo.Theatre;
import com.me.bookmymovie.pojo.User;

@Repository
public class BookDAO extends DAO {
	
	// Add Booking to Database
	public Booking addBooking(Customer customer, List<Seat> seats, Double amount, Show show) {
			
			Booking booking = new Booking();
			
			try {
				
				Date date = new Date();
				booking.setBookDate(date);
				booking.setCustomer(customer);
				booking.setTotal(amount);
				booking.setShow(show);
				booking.setBookingStatus("Active");
				
				for (Seat s: seats) {
					
					booking.addSeat(s);
				}
				 
				Session session = getSession();
				Transaction t = session.beginTransaction();	
				session.save(booking);
				t.commit();
				
				return booking;
				
			} catch(HibernateException e) {
				
				e.printStackTrace();
				rollback();
				return null;
				 
			} finally {
				
				close();
			}
			
	}
	
	// Get specific Booking by ID
	@SuppressWarnings({"deprecation"})
	public Booking getBookingById(Long bookid) {
			
		Criteria crit = getSession().createCriteria(Booking.class);
		crit.add(Restrictions.eq("bookingId", bookid));
		Booking booking = (Booking) crit.uniqueResult();
		return booking;
			
	}
	
	
	// Cancel Booking by ID
	@SuppressWarnings({"deprecation"})
	public boolean cancelBooking(Long id) {
		
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = session.createCriteria(Booking.class);
			crit.add(Restrictions.eq("bookingId", id));
			Booking booking = (Booking) crit.uniqueResult();
			booking.setBookingStatus("Inactive");
			
			List<Seat> seats = booking.getSeats();
			
			for(Seat s: seats) {
				s.setBooking(null);
			}
			
			session.update(booking);
			t.commit();
			return true;
			
		} catch(HibernateException e) {
			
			 e.printStackTrace();
			 rollback();
			 return false;
			 
		} finally {
			
			close();
		}	
		
	}
	
	// Filters for Bookings Search
	public List<Booking> filterBooking(String filter, List<Booking> bookings) {
		
		List<Booking> book = new ArrayList<Booking>();
		
		try {
			
			if (filter.equals("Active")) {
				
				for(Booking b : bookings) {
					
					if(b.getBookingStatus().equals("Active")) {	
						book.add(b);	
					}
				}
				
				return book;	
			}
			
			else if (filter.equals("Cancelled")) {
				
				for(Booking b : bookings) {
		
					if(b.getBookingStatus().equals("Inactive")) {
						book.add(b);	
					}
				}
				
				return book;
				
			} else  if (filter.equals("All")) {
				
				for(Booking b : bookings) {
					
						book.add(b);		
				}
					
			    return book;		
				   
			}
			
			return bookings;
			
		} catch(HibernateException e) {
			
			 e.printStackTrace();
			 rollback();
			 return null;
			 
		} finally {
			
			close();
		}
		
	}
	
	//Get All Bookings of Customer by Customer ID
	public List getBookingsByCustomerId(long customerId) {
			
		List<Booking> bookings = new ArrayList<Booking>();
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			
			return  session.createCriteria(Booking.class, "booking")
			        .createAlias("booking.customer", "customer")
			        .add(Restrictions.eq("customer.customerId", customerId))
			        .list();
	
		
		} catch(HibernateException e) {
			
			 e.printStackTrace();
			 rollback();
			 return null;
		 
		} finally {
		
			 close();
		
		}
	
	}

}
