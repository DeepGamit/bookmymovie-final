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
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.me.bookmymovie.pojo.Booking;
import com.me.bookmymovie.pojo.Customer;
import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Show;
import com.me.bookmymovie.pojo.Theatre;
import com.me.bookmymovie.pojo.User;

@Repository
public class TheatreDAO extends DAO {
	
	// Update Theatre Details in Database
	public boolean updateTheatre(Theatre theatre, String theatreName, String theatreCity, String theatreAddress) {
			
		try {
			
			theatre.setTheatreName(theatreName);
			theatre.setTheatreCity(theatreCity);
			theatre.setTheatreAddress(theatreAddress);
			
			Session session = getSession();
			Transaction t = session.beginTransaction();	
			session.saveOrUpdate(theatre);
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
	
	// Get All Theatres List
	public static  List<Theatre> getAllTheatres(){
		
		List<Theatre> theatres = new ArrayList<Theatre>();
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = session.createCriteria(Theatre.class);
			theatres = crit.list();
			t.commit();
			return theatres;
			
		} catch(HibernateException e) {
				

			e.printStackTrace();
			rollback();
			return null;
			 
		} finally {
			
			close();
		}

	}
	
	// Get specific Theatre by TheatreID
	@SuppressWarnings({"deprecation"})
	public Theatre getTheatreById(Long theatreid) {
			
		Criteria crit = getSession().createCriteria(Theatre.class);
		crit.add(Restrictions.eq("theatreId", theatreid));
		Theatre theatre = (Theatre) crit.uniqueResult();
		return theatre;
	
	}
	
	// Get specific Theatre by UserID
	@SuppressWarnings({"deprecation"})
	public Theatre getTheatreByUser(Long userid) {
			
		return  (Theatre) getSession().createCriteria(Theatre.class, "theatre")
				.createAlias("theatre.user", "user")
				.add(Restrictions.eq("user.userID", userid))
				.uniqueResult();
				
	}
	
	// Get Theatres List by City
	@SuppressWarnings({"deprecation"})
	public static List<Theatre> searchTheatreByCity(String keyword){
	       
        List<Theatre> theatres = new ArrayList<Theatre>();
        try{
        	
        	Session session = getSession();
			Transaction t = session.beginTransaction();
            Query q = getSession().createQuery("from Theatre WHERE theatreCity LIKE :keyword");
            q.setString("keyword", "%"+keyword+"%");
            theatres = q.list();
            t.commit();
            return theatres;
            
        } catch(HibernateException e){
        	
        	e.printStackTrace();
        	rollback();
			return null;
			 
        } finally{
        	
            close(); 
        }
    }
	
	// Get Theatres List by Theatre Name
	@SuppressWarnings({"deprecation"})
	public static List<Theatre> searchTheatreByName(String keyword){
	       
        List<Theatre> theatres = new ArrayList<Theatre>();
        try{
        	
        	Session session = getSession();
			Transaction t = session.beginTransaction();
            Query q = getSession().createQuery("from Theatre WHERE theatreName LIKE :keyword");
            q.setString("keyword", "%"+keyword+"%");
            theatres = q.list();
            t.commit();
            return theatres;
            
        } catch(HibernateException e){
        	
        	e.printStackTrace();
			rollback();
			return null;
			 
        } finally{
        	
            close();
        } 
    }

	// Search Theatre by Keyword along with SearchBy
	public List<Theatre> searchTheatre(String searchby, String keyword) {
		
		List<Theatre> searchResult = new ArrayList<Theatre>();
		
		if(searchby.equals("City")) {
			
			searchResult = TheatreDAO.searchTheatreByCity(keyword);
			return searchResult;	
		}
		
		if(searchby.equals("Name")) {
			
			searchResult = TheatreDAO.searchTheatreByName(keyword);
			return searchResult;	
		}
		
		if(searchby.equals("Search By")) {
			
			searchResult = TheatreDAO.getAllTheatres();
			return searchResult;	
		}
		
		return null;
	}
	
	// Update Theatre Login Details
	public boolean updateTheatreDetails(String password, String userName, Theatre theatre) {
		
		try {
			
			theatre.getUser().setUserPwd(password);
			theatre.getUser().setUserName(userName);
			
			Session session = getSession();
			Transaction t = session.beginTransaction();	
			session.saveOrUpdate(theatre);
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
	
	// Get List of all Theatres for particular movie and city.
	public List getTheatres(String movie, String city) {

		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			
			return session.createCriteria(Show.class, "show")
	                .createAlias("show.theatre", "theatre")
	                .createAlias("show.movie", "movie")
	                .add(Restrictions.eq("theatre.theatreCity", city))
	                .add(Restrictions.eq("movie.movieTitle", movie))
	                .setProjection(Projections.distinct(Projections.property("theatre")))
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
