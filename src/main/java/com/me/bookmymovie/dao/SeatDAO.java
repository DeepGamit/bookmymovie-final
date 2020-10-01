package com.me.bookmymovie.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.me.bookmymovie.pojo.Seat;
import com.me.bookmymovie.pojo.Show;

@Repository
public class SeatDAO extends DAO {
	
	// Create Virtual Seats
	public static void createSeats(int numberOfRows, int numberOfSeatsPerRow, double seatPrice, Show show ) {
			
			String[] Alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
			
			for(int i = 0; i< numberOfRows; i++) {
				for(int j = 1; j<= numberOfSeatsPerRow; j++) {
					
					String seatName = Alphabets[i] + "-" + j;
					Seat seat = new Seat();
					seat.setSeatNumber(seatName);
					seat.setSeatPrice(seatPrice);
					show.addSeat(seat);
					
					Session session = getSession();
					Transaction t = session.beginTransaction();	
					session.save(seat);
					t.commit();
						
				}
			}
			
		}
	
	// Get Virtual Seat by ID
	@SuppressWarnings({"deprecation"})
	public static Seat getSeatById(Long seatid) {
			
		Criteria crit = getSession().createCriteria(Seat.class);
		crit.add(Restrictions.eq("seatId", seatid));
		Seat show = (Seat) crit.uniqueResult();
		return show;
			
	}

}
