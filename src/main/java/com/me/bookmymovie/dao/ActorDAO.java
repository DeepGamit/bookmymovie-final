package com.me.bookmymovie.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.me.bookmymovie.pojo.Actor;

@Repository
public class ActorDAO extends DAO {
	
	
	// Get All Actors List
	@SuppressWarnings({"deprecation"})
	public  List<Actor> getAllActors(){
			
		List<Actor> actor = new ArrayList<Actor>();
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = session.createCriteria(Actor.class);
			actor = crit.list();
			t.commit();
			return actor;
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
			rollback();
			return null;
			 
		} finally {
			
			close();
		}
				
	}
	
	// Find Actor by ID
	public static Actor findById(Long id) {
		
		Criteria crit = getSession().createCriteria(Actor.class);
		crit.add(Restrictions.eq("actorID", id));
		Actor actor = (Actor) crit.uniqueResult();
		return actor;
		
	}
	
	
	// Add Actor to Database
	public String addActor(String actorName) {
			
		
		Actor actor = new Actor();
		
		try {
			
			Criteria crit = getSession().createCriteria(Actor.class);
			crit.add(Restrictions.eq("actorName", actorName).ignoreCase());
			Actor find = (Actor) crit.uniqueResult();
			
			if(find != null) {
				
				return "Actor already exists in Database";
				
			} else {
				
				actor.setActorName(actorName);
			
				Session session = getSession();
				Transaction t = session.beginTransaction();		
				session.save(actor);
				t.commit();
				
				return "Actor added Successfully";
			
			}
			
		} catch(HibernateException e) {
			
			 e.printStackTrace();
			 rollback();
			 
			 return "Actor Failed to Add";
			 
		} finally {
			
			close();
		}
		
	}

}
