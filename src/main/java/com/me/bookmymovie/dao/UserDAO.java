package com.me.bookmymovie.dao;
import com.me.bookmymovie.pojo.Admin;
import com.me.bookmymovie.pojo.Customer;
import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Show;
import com.me.bookmymovie.pojo.Theatre;
import com.me.bookmymovie.pojo.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.me.bookmymovie.pojo.User;

@Repository
public class UserDAO extends DAO {
	
	// Add new User to the Database based on UserRole
	public boolean addUser(String useremail, String password, String username, String usertype) {
		
		try {
			
			if(usertype.equals("Theatre")) {
	
				User user = new User();
				Theatre theatre = new Theatre();
				Date regdate = new Date();
			
				user.setUserEmail(useremail);
				user.setUserPwd(password);
				user.setUserName(username);
				user.setUserType(usertype);
				user.setRegDate(regdate);
				user.setUserStatus("Active");
				
				theatre.setUser(user);
				
				Session session = getSession();
				Transaction t = session.beginTransaction();	
				session.save(user);
				session.save(theatre);
				t.commit();
				return true;
				
			} else if(usertype.equals("Admin")) {
				
				User user = new User();
				Admin admin = new Admin();
				Date regdate = new Date();
			
				user.setUserEmail(useremail);
				user.setUserPwd(password);
				user.setUserName(username);
				user.setUserType(usertype);
				user.setRegDate(regdate);
				user.setUserStatus("Active");
				
				admin.setUser(user);
				
				Session session = getSession();
				Transaction t = session.beginTransaction();	
				session.save(user);
				session.save(admin);
				t.commit();
				return true;
				
			} else {
				
				
				User user = new User();
				Customer cust = new Customer();
				Date regdate = new Date();
			
				user.setUserEmail(useremail);
				user.setUserPwd(password);
				user.setUserName(username);
				user.setUserType(usertype);
				user.setRegDate(regdate);
				user.setUserStatus("Active");
				
				cust.setUser(user);
				Session session = getSession();
				Transaction t = session.beginTransaction();	
				session.save("User", user);
				t.commit();
				return true;	
			}
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
		    rollback();
			return false;
			 
		} finally {
			
			close();
		}
	}
	
	// List All Users of specific UserType
	public List<User> findByUserType(String usertype) {
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();	
			Criteria crit = getSession().createCriteria(User.class);
			crit.add(Restrictions.eq("userType", usertype));
			List results = crit.list();
			t.commit();
			return results;
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
			rollback();
			return null;
			 
		} finally {
			
			close();
		}
	}
	
	// Make User Status Inactive
	@SuppressWarnings({"deprecation"})
	public boolean inactivateUser(long id) {
		
		try {
			
			Criteria crit = getSession().createCriteria(User.class);
			crit.add(Restrictions.eq("userID", id));
			User user = (User) crit.uniqueResult();
			user.setUserStatus("Inactive");
			begin();
			getSession().update(user);
			commit();
			return true;
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
			rollback();
			return false;
			 
		} finally {
			
			close();
		}
		
	}
	
	// Make User Status Active
	@SuppressWarnings({"deprecation"})
	public boolean activateUser(long id) {
		
		try {
			
			Criteria crit = getSession().createCriteria(User.class);
			crit.add(Restrictions.eq("userID", id));
			User user = (User) crit.uniqueResult();
			user.setUserStatus("Active");
			begin();
			getSession().update(user);
			commit();
			return true;
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
			rollback();
			return false;
			 
		} finally {
			
			close();
		}
	}
	
	// Get specific User by EmailID
	@SuppressWarnings({"deprecation"})
	public User getUserByEmail(String useremail) {
		
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("userEmail", useremail));
		User user = (User) crit.uniqueResult();
		return user;
	}
	
	// Update User Last Login Details
	public void updateLogin(User user) {
		
		Long userid = user.getUserID();
		Date login = new Date();
		
		Session session = getSession();
		Transaction t = session.beginTransaction();
		String hql = "UPDATE User set LAST_LOG = :login " + "WHERE USER_ID = :userid";
		Query query = session.createQuery(hql);
		query.setParameter("login", login);
		query.setParameter("userid", userid);
		query.executeUpdate();
		t.commit();
		session.close();
	}
	
	// Get specific User by UserID
	@SuppressWarnings({"deprecation"})
	public User getUserById(Long userid) {
		
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("userID", userid));
		User user = (User) crit.uniqueResult();
		return user;
	}
	
	// Find if User Exists or not!
	@SuppressWarnings({"deprecation"})
	public boolean doUserExists(String email){
		
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("userEmail", email));
		User user = (User) crit.uniqueResult();
		
		if(user!= null) {
			return false;
		} else {
			return true;
		}	
	}
	
	// Get List of all Users
	@SuppressWarnings({"deprecation"})
	public  List<User> getAllUsers(){
		
		List<User> users = new ArrayList<User>();
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = session.createCriteria(User.class);
			users = crit.list();
			t.commit();
			return users;
			
		} catch(HibernateException e) {
				
			e.printStackTrace();
			rollback();
			return null;
			 
		} finally {
			
			close();
		}
	}

	// Filters for Users Search
	public List<User> filterUser(String filter) {
		
		try {
			
			if(filter.equals("ALL")) {
				
				Session session = getSession();
				Transaction t = session.beginTransaction();
				Criteria crit = session.createCriteria(User.class);
				List results = crit.list();
				t.commit();
				return results;
			}
				
			Session session = getSession();
			Transaction t = session.beginTransaction();	
			Criteria crit = getSession().createCriteria(User.class);
			crit.add(Restrictions.eq("userType", filter));
			List results = crit.list();
			t.commit();
			return results;
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
			rollback();
			return null;
			 
		} finally {
			
			close();
		}
	}
}
