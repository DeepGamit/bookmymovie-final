package com.me.bookmymovie.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.me.bookmymovie.pojo.Category;
import com.me.bookmymovie.pojo.Language;
import com.me.bookmymovie.pojo.Movie;

@Repository
public class LanguageDAO extends DAO {
	
	
	// Get All Languages List
	@SuppressWarnings({"deprecation"})
	public  List<Language> getAllLanguages(){
			
			List<Language> language = new ArrayList<Language>();
			
			try {
				
				Session session = getSession();
				Transaction t = session.beginTransaction();
				Criteria crit = session.createCriteria(Language.class);
				language = crit.list();
				t.commit();
				return language;
				
			} catch(HibernateException e) {
				
				e.printStackTrace();
				rollback();
				return null;
				 
			} finally {
				
				close();
			}
				
	}
	
	// Find Language by ID
	public static Language findById(Long id) {
		
		Criteria crit = getSession().createCriteria(Language.class);
		crit.add(Restrictions.eq("languageID", id));
		Language language = (Language) crit.uniqueResult();
		return language;
	}
	
	// Add Language to Database
	public String addLanguage(String languageName) {
		
		Language language = new Language();
		
		try {
			
			Criteria crit = getSession().createCriteria(Language.class);
			crit.add(Restrictions.eq("languageName", languageName).ignoreCase());
			Language find = (Language) crit.uniqueResult();
			
			if(find != null) {
				
				return "Language already exists in Database";
				
			} else {
				
				language.setLanguageName(languageName);
			
				Session session = getSession();
				Transaction t = session.beginTransaction();		
				session.save(language);
				t.commit();
				
				return "Language added Successfully";
			
			}
			
		} catch(HibernateException e) {
			
			 e.printStackTrace();
			 rollback();
			 
			 return "Language Failed to Add";
			 
		} finally {
			
			close();
		}
	}

}
