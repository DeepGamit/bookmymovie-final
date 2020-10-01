package com.me.bookmymovie.dao;

import java.util.ArrayList;
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
import com.me.bookmymovie.pojo.Show;
import com.me.bookmymovie.pojo.Theatre;
import com.me.bookmymovie.pojo.User;

@Repository
public class CustomerDAO extends DAO {
	
	// Get specific Customer by ID
	@SuppressWarnings({"deprecation"})
	public Customer getCustomerById(Long userid) {
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			
			return  (Customer) getSession().createCriteria(Customer.class, "customer")
					.createAlias("customer.user", "user")
					.add(Restrictions.eq("user.userID", userid))
					.uniqueResult();
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
			rollback();
			return null;
			 
		} finally {
			
			close();
			
		}
	
	}
	
	// Update Customer Password
	public boolean updateCustomerPassword(String password, String username, Customer customer) {
		
		try {
					
			    customer.getUser().setUserPwd(password);
			    customer.getUser().setUserName(username);
				
				Session session = getSession();
				Transaction t = session.beginTransaction();	
				session.saveOrUpdate(customer);
				session.saveOrUpdate(customer.getUser());
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

	
	// Update Customer Details
	public boolean updateCustomerDetails(String userName, Customer customer) {
				
		try {
			
			customer.getUser().setUserName(userName);
			
			Session session = getSession();
			Transaction t = session.beginTransaction();	
			session.saveOrUpdate(customer);
			session.saveOrUpdate(customer.getUser());
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
	
}
