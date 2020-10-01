package com.me.bookmymovie.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.me.bookmymovie.dao.BookDAO;
import com.me.bookmymovie.dao.CustomerDAO;
import com.me.bookmymovie.dao.MovieDAO;
import com.me.bookmymovie.dao.TheatreDAO;
import com.me.bookmymovie.dao.UserDAO;
import com.me.bookmymovie.pojo.Booking;
import com.me.bookmymovie.pojo.Customer;
import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Seat;
import com.me.bookmymovie.pojo.Show;
import com.me.bookmymovie.pojo.Theatre;
import com.me.bookmymovie.pojo.User;



@Controller
public class UserController {
	
	 
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	// Login POST Request
	@RequestMapping(value="/loginProcess",method=RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, UserDAO userdao, HttpSession session, TheatreDAO theatredao, MovieDAO moviedao, ModelMap model){
		
		String useremail =  request.getParameter("login-email");
		String userpwd = request.getParameter("login-password");
		
		User user = userdao.getUserByEmail(useremail);
		
		if(user == null) {
			
			List<Movie> list = moviedao.getAllMovies();
			model.put("movies",list);
			
			String errmsg = "Please Enter Valid Email Address";
			ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
			return mv;
			
		}
	
		String type = user.getUserType();
		String status = user.getUserStatus();
		
		List<Movie> list = moviedao.getAllMovies();
		
		Boolean checkPassword = passwordEncoder.matches(userpwd, user.getUserPwd());
		
		if(checkPassword) {
			
			if(type.equals("Admin")) {
				
				if (status.equals("Active")){
					
					userdao.updateLogin(user);
					session.setAttribute("userAdmin", user);
					
					ModelAndView mv = new ModelAndView("admin");
					return mv;
				
				} else {
					
					model.put("movies",list);
					String errmsg = "Account Suspended. Contact admin@bookmymovie.com";
					ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
					return mv;
					
				}

			} else if (type.equals("Customer")) {
				
				
				if(status.equals("Active")) {
					
					userdao.updateLogin(user);
					session.setAttribute("user", user);
					
					ModelAndView mv = new ModelAndView("index", "movies", list);
					return mv;
					
				} else {
					
					model.put("movies",list);
					String errmsg = "Account Suspended. Contact admin@bookmymovie.com";
					ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
					return mv;
					
				}
				
			} else {
				
				if(status.equals("Active")) {
					
					userdao.updateLogin(user);
					session.setAttribute("userTheatre", user);
					
					ModelAndView mv = new ModelAndView("redirect:/theatre");
					return mv;
					
				} else {
					
					model.put("movies",list);
					String errmsg = "Account Suspended. Contact admin@bookmymovie.com";
					ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
					return mv;
						
				}		
			} 
			
		} else {
			
			model.put("movies",list);
			String errmsg = "Login Failed.! Please try Again.";
			ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
			return mv;
			
		}		
	}
	
	// Register POST Request
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView registrationProcess(HttpServletRequest request, UserDAO userdao, MovieDAO moviedao, @ModelAttribute("user") @Validated  User user, BindingResult result, ModelMap model, SessionStatus status ){
		
		
		String password =  request.getParameter("userPwd");
		String confirm =  request.getParameter("confirmPwd");
		
		if(6 > user.getUserPwd().length() || 20 < password.length()) {		
			result.rejectValue("userPwd", "user.password.length", "Password should have length between 6 and 20 characters");
		}
		
		Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,20}");
	
		if(!(pattern.matcher(password).matches())) {
			result.rejectValue("userPwd", "user.password.invalid", "Password must contain atleast 1 digit, lowercase letter, uppercase letter, special character and no whitespace");
		}
		
		if(!password.equals(confirm)) {
			result.rejectValue("","user.confirm.invalid", "Confirm Password doesn't match!");
		}
		
		
		 if (result.hasErrors()) {
			 
		 	List<Movie> list = moviedao.getAllMovies();
			model.put("movies",list);
		 
		 	ModelAndView m = new ModelAndView("register");
            return m;
	      }
		
		 
		 String userEmail =  request.getParameter("userEmail");
		 
		 boolean r = userdao.doUserExists(userEmail);
		
		 if(r == false) {
			 
			 status.setComplete();
			 
			 List<Movie> list = moviedao.getAllMovies();
			 model.put("movies",list);
			 
			 String errmsg = "User Already Exists.!";
			 ModelAndView m = new ModelAndView("register", "errmsg", errmsg);
			 return m;
			 
		 } else {
				 
				 String userName =  request.getParameter("userName");
				 String usertype = "Customer";
				 String hashedPassword = passwordEncoder.encode(password);
			 
				 userdao.addUser(userEmail, hashedPassword, userName, usertype);
					
				 status.setComplete();

				 List<Movie> list = moviedao.getAllMovies();
				 model.put("movies",list);
				 
				 String msg = "User Account Created Successfully";
				 ModelAndView m = new ModelAndView("login", "msg", msg);
		         return m;
 
		 }	
	}
	

	// Logout
	@RequestMapping("/userLogout")
    public ModelAndView userLogout(HttpSession session) {
		
	   session.invalidate();
       ModelAndView m=new ModelAndView("redirect:/");
       return m;
       
    }
	
	// GET Booking History
	@RequestMapping(value="/bookingHistory/{username}",method=RequestMethod.GET)
	 public ModelAndView userBookingHistory(HttpSession session, ModelMap model, BookDAO bookdao, MovieDAO moviedao, CustomerDAO customerdao) {
		
       User user = (User) session.getAttribute("user");
	   Customer customer = customerdao.getCustomerById(user.getUserID());
       
       List<Booking> bookings = bookdao.getBookingsByCustomerId(customer.getCustomerId());
       model.put("bookings", bookings);
       
       Map<Long, List<Seat>> map = new HashMap<Long, List<Seat>>();
       for(Booking b : bookings) {
    	   map.put(b.getBookingId(), b.getSeats());
       }
       model.put("map", map);
       
       List<Movie> list = moviedao.getAllMovies();
	   model.put("movies",list);
		 
	   ModelAndView m=new ModelAndView("booking-history");
       return m;
    }
	
	// Change Password GET Request
	@RequestMapping(value="/change-password/{username}",method=RequestMethod.GET)
	public ModelAndView getChangePassword(HttpSession session, ModelMap model, BookDAO bookdao, MovieDAO moviedao, CustomerDAO customerdao) {
		
	     User user = (User) session.getAttribute("user");
		 Customer customer = customerdao.getCustomerById(user.getUserID());
		 
	      List<Movie> movies = moviedao.getAllMovies();
		  
			 
		  ModelAndView m=new ModelAndView("change-password");
		  model.put("movies",movies);
		  model.put("customer", customer);
		  model.put("user", user);
	      return m;
     
  }
	
	// Update Password POST Request
	@RequestMapping(value="/change-password/updatePassword",method=RequestMethod.POST)
	public ModelAndView updatePassword(HttpServletRequest request, HttpSession session, SessionStatus status, @Valid @ModelAttribute("user") User user, BindingResult passResult, ModelMap model, MovieDAO moviedao, CustomerDAO customerdao) {
		
		String password = request.getParameter("userPwd");
		String confirm = request.getParameter("confirmPwd");
		String userName = request.getParameter("userName");
		
		if(6 > user.getUserPwd().length() || 20 < password.length()) {		
			passResult.rejectValue("userPwd", "user.password.length", "Password should have length between 6 and 20 characters");
		}
		
		Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,20}");
	
		if(!(pattern.matcher(password).matches())) {
			passResult.rejectValue("userPwd", "user.password.invalid", "Password must contain atleast 1 digit, lowercase letter, uppercase letter, special character and no whitespace");
		}
		
		if(!password.equals(confirm)) {
			passResult.rejectValue("","user.confirm.invalid", "Confirm Password doesn't match!");
		}
		
		if(passResult.hasErrors()) {
			 
		 	List<Movie> list = moviedao.getAllMovies();
			model.put("movies",list);
		 	ModelAndView m = new ModelAndView("change-password");
            return m;
           
	    }

		String hashedPassword = passwordEncoder.encode(password);
		
		status.setComplete();

		User u = (User) session.getAttribute("user");
		Customer customer = customerdao.getCustomerById(u.getUserID());
		boolean r = customerdao.updateCustomerPassword(hashedPassword, userName, customer);

		if(r) {
			
			String msg = "Password Updated Successfully";
			
			List<Movie> movies = moviedao.getAllMovies();
			model.put("movies",movies);
			ModelAndView m = new ModelAndView("change-password", "msg", msg);
		    return m;
			
		} else {
			
			String errmsg = "Password Failed to Update";
			
			List<Movie> movies = moviedao.getAllMovies();
			model.put("movies",movies);
			
			ModelAndView m = new ModelAndView("change-password", "errmsg",errmsg);
		    return m;
		}

 }
	
}
