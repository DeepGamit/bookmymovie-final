package com.me.bookmymovie.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.me.bookmymovie.pojo.Actor;
import com.me.bookmymovie.pojo.Category;
import com.me.bookmymovie.pojo.Language;
import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Show;
import com.me.bookmymovie.pojo.Theatre;

@Repository
public class ShowDAO extends DAO {
	
	// Get All Shows List
	@SuppressWarnings({"deprecation"})
	public  List<Show> getAllShows(){
			
		List<Show> show = new ArrayList<Show>();
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = session.createCriteria(Show.class);
			show = crit.list();
			t.commit();
			return show;
			
		} catch(HibernateException e) {
			
			 e.printStackTrace();
			 rollback();
			 return null;
			 
		} finally {
			
			close();
		}
			
	}
	
	// Add New Show to the Database
	public boolean addShow(Movie movie, Date showDate, Date showTime, Double seatPrice, Integer nor, Integer nos, Theatre theatre, Integer sn) {
		
		Show show = new Show();
		
		try {
			
			 show.setMovie(movie);
			 show.setShowDate(showDate);
			 show.setShowTime(showTime);
			 show.setTheatre(theatre);
			 show.setNumberOfRows(nor);
			 show.setNumberOfSeat(nos);
			 show.setScreenNumber(sn);
			 show.setShowPrice(seatPrice);
			 
			Session session = getSession();
			Transaction t = session.beginTransaction();	
			session.save(show);
			t.commit();
			
			SeatDAO.createSeats(nor, nos, seatPrice, show);
			
			return true;
			
		} catch(HibernateException e) {
			
			 e.printStackTrace();
			 rollback();
			 return false;
			 
		} finally {
			
			close();
		}
		
	}
	
	// Get specific Show by ID
	@SuppressWarnings({"deprecation"})
	public Show getShowById(Long showid) {
			
		
		Criteria crit = getSession().createCriteria(Show.class);
		crit.add(Restrictions.eq("showID", showid));
		Show show = (Show) crit.uniqueResult();
		return show;
			
	}

	// Search Show by keyword along with SearchBy
	public List<Show> searchShow(String searchby, String keyword, List<Show> shows) {
		
		List<Show> showlist = new ArrayList<Show>();
			 
			 if(searchby.equals("Search By")) {
				 
		            return shows;
			 } 
			 
			 if(searchby.equals("Movie Title")) {
				 
				for(Show s : shows) {
					
					if(s.getMovie().getMovieTitle().startsWith(keyword)) {
						
						showlist.add(s);
					}	
				}
				
				return showlist; 
			 } 
			 
	     return showlist;	
	}

	// Get List of Shows on particular Date
	public List<Show> getShowsByDate(Date filterDate, List<Show> shows) throws ParseException {
		
		List<Show> showlist = new ArrayList<Show>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tdate = sdf.format(filterDate);
		Date inputDate = sdf.parse(tdate);
		
		for(Show s: shows) {
			
			if(s.getShowDate().compareTo(inputDate) == 0) {
				showlist.add(s);	
			}
		}
		
		return showlist;
	}
	
	// Get List of Shows from a particular Theatre
	public List<Show> getShowsByTheatre(Theatre theatre) {
		
		List<Show> shows = new ArrayList<Show>();
		
		 try {
	       
	        	Session session = getSession();
				Transaction t = session.beginTransaction();
				Criteria crit = getSession().createCriteria(Show.class);
				crit.add(Restrictions.eq("theatre", theatre));
				shows = crit.list();
	            return shows;
	            
	        } catch(HibernateException e){
	        	
	        	e.printStackTrace();
				rollback();
				return null;
				 
	        } finally{
	        	
	            close(); 
	        }	
	}
	
	// Get List of all Shows for particular Movie Title and Theatre
	public List viewShows(String title, String city, String theatreName) throws ParseException {
		
		try {
		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String tdate = formatter.format(date);
			Date today = formatter.parse(tdate);
			
			SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
			String ttime = parser.format(date);
			Date time = parser.parse(ttime);
			System.out.println(time);
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			return session.createCriteria(Show.class, "show")
	                .createAlias("show.theatre", "theatre")
	                .createAlias("show.movie", "movie")
	                .add(Restrictions.eq("theatre.theatreCity", city))
	                .add(Restrictions.eq("movie.movieTitle", title))
	                .add(Restrictions.eq("theatre.theatreName", theatreName))
	                .add(Restrictions.ge("show.showDate", today))
	                .addOrder(Order.asc("show.showDate"))
	                .addOrder(Order.asc("show.showTime"))
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
