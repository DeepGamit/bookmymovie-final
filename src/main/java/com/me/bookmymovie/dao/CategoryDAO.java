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
import com.me.bookmymovie.pojo.Category;
import com.me.bookmymovie.pojo.Language;
import com.me.bookmymovie.pojo.Movie;

@Repository
public class CategoryDAO extends DAO {
	
	// Get All Categories List
	@SuppressWarnings({"deprecation"})
	public  List<Category> getAllCategories(){
			
		List<Category> category = new ArrayList<Category>();
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = session.createCriteria(Category.class);
			category = crit.list();
			t.commit();
			return category;
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
			rollback();
			return null;
			 
		} finally {
			
			close();
		}
		
			
	}
	
	// Find Category by ID
	public static Category findById(Long id) {
		
		Criteria crit = getSession().createCriteria(Category.class);
		crit.add(Restrictions.eq("categoryID", id));
		Category category = (Category) crit.uniqueResult();
		return category;
		
	}
	
	
	// Add Category to Database
	public String addCategory (String categoryName) {
		
		
		Category category = new Category();
		
		try {
			
			Criteria crit = getSession().createCriteria(Category.class);
			crit.add(Restrictions.eq("categoryName", categoryName).ignoreCase());
			Category find = (Category) crit.uniqueResult();
			
			if(find != null) {
				
				return "Category already exists in Database";
				
			} else {
				
			    category.setCategoryName(categoryName);
			
				Session session = getSession();
				Transaction t = session.beginTransaction();		
				session.save(category);
				t.commit();
				
				return "Category added Successfully";
			
			}
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
			rollback();
			
			return "Category Failed to Add";
			 
		} finally {
			
			close();
			
		}
   }

}
