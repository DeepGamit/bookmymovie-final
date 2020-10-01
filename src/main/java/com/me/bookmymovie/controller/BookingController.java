package com.me.bookmymovie.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.bookmymovie.dao.BookDAO;
import com.me.bookmymovie.dao.CustomerDAO;
import com.me.bookmymovie.dao.MovieDAO;
import com.me.bookmymovie.dao.SeatDAO;
import com.me.bookmymovie.dao.ShowDAO;
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
public class BookingController {
	
	@Autowired
	@Value("${email.username}")
    private String username;
	
	@Autowired
	@Value("${email.password}")
    private String password;
	
	//GET Booking Details Request
	@RequestMapping(value = "/bookingDetails", method = RequestMethod.GET)
	public ModelAndView bookingDetails(HttpSession session, HttpServletRequest request, Map<String, Object> model, SeatDAO seatdao, MovieDAO moviedao, TheatreDAO theatredao, ShowDAO showdao){
		
		Long id = Long.parseLong(request.getParameter("showID"));
		Show sh = showdao.getShowById(id);
		Double price = Double.parseDouble(request.getParameter("price"));
		String st[] = request.getParameterValues("seat");
		User user = (User) session.getAttribute("user");
		
		List<Long> seatno = new ArrayList<Long>();
		List<Movie> list = moviedao.getAllMovies();
		 
		if(st == null) {
			
			Show show = showdao.getShowById(id);
			model.put("show", show);
			List<Seat> set = show.getSeats();
			model.put("seats",set);
			model.put("movies",list);
			
			String errmsg = "No Seat Selected. Please Select a Seat to Book";
	        model.put("errmsg", errmsg);
	           
			ModelAndView m=new ModelAndView("book-seat");
			return m;
			
		} else if (st.length > 8){
			
			Show show = showdao.getShowById(id);
			model.put("show", show);
			List<Seat> set = show.getSeats();
			model.put("seats",set);
			model.put("movies",list);
			
			String errmsg = "Cannot Select more than 8 seats at a time!";
	        model.put("errmsg", errmsg);
	           
			ModelAndView m=new ModelAndView("book-seat");
			return m;
			
		} else {
			
			if(user != null) {
				
				int count = st.length;
			    double total = count*price;
			
				String seats="";
				
				for(int i=0;i<count;i++){
					
					Seat s = seatdao.getSeatById(Long.parseLong(st[i]));
					seatno.add(Long.parseLong(st[i]));
					seats = seats + s.getSeatNumber() + ", ";
					
				}
				
				model.put("show", sh);
				model.put("movies",list);
				model.put("count", count);
				model.put("total", total);
				model.put("seat", seatno);
				model.put("seats", seats);
				
				ModelAndView m=new ModelAndView("booking-details");
				return m; 
			
			
			} else {
				
				String errmsg = "Please Login to Book Tickets";
				model.put("movies",list);
				 
				ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
				return mv;	
				
			}
		}

	}
	
	//Make Payment GET Request
	@RequestMapping(value = "/makePayment", method = RequestMethod.GET)
	public ModelAndView makePayment(HttpSession session, HttpServletRequest request, Map<String, Object> model, MovieDAO moviedao, SeatDAO seatdao, BookDAO bookdao, CustomerDAO customerdao, ShowDAO showdao){
	
		String st = request.getParameter("seat");
		Double amount = Double.parseDouble(request.getParameter("amount"));
		Long id = Long.parseLong(request.getParameter("show"));
		
		User user = (User) session.getAttribute("user");
		Show show = showdao.getShowById(id);
		
		List<Movie> list = moviedao.getAllMovies();
		List<Seat> seats = new ArrayList<Seat>();
		
		ArrayList<String> temp = new ArrayList<String>();
		for(String t: st.split(","))
		{	
			String result = t.replace('[', ' ');
			result = result.replace(']', ' ');
			temp.add(result.trim());
		}
	
		String seatNumber="";
		for(String s: temp) {
			
			Seat seat = seatdao.getSeatById(Long.parseLong(s));
			seats.add(seat);
			seatNumber = seatNumber + seat.getSeatNumber() + " ";	
		}
		
		Customer customer = customerdao.getCustomerById(user.getUserID());
		Booking book = bookdao.addBooking(customer,seats,amount,show);
		
		Email email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(username, password));
		email.setSSLOnConnect(true);
		try {
			
			email.setFrom("bookmymovie@gmail.com");
			email.setSubject("BookMyMovie :: Movie Booking Confirmation");
			email.setMsg("<h1>Booking Details<h1/>" + "<br>" +
						  "<h3>Movie Title: " +book.getShow().getMovie().getMovieTitle()+ "</h3>" + "<br>" +
						  "<h3>Theatre: "+book.getShow().getTheatre().getTheatreName()+ "</h3>" +
						  "<h4>Show Date: " + book.getShow().getShowDate() + "</h4>" +
						  "<h4>Show Time: " +book.getShow().getShowTime()+ "</h4>" +
						  "<h4>Show Screen: "+ book.getShow().getScreenNumber()+ "</h4>" +
						  "<h4>Seats: " +seatNumber +"</h4>"+ "<br>" +
						  "<h4>Booking Id: "+ book.getBookingId()+ "</h4>" +
						  "<h4>Booking Date: "+ book.getBookDate()+ "</h4>" +
						  "<h4>Booking Amount: Rs. "+ book.getTotal()+ "</h4>");
			email.addTo(user.getUserEmail());
			email.send();
			
		} catch (EmailException e) {
			
			e.printStackTrace();
		}
		
		model.put("movies",list);
		model.put("booking",book);
		model.put("seats",seatNumber);
		
		ModelAndView m=new ModelAndView("get-ticket");
	    return m;
	}
	
	// Cancel Booking POST Request
	@RequestMapping(value="/booking/cancel",  method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpSession session,HttpServletRequest request, BookDAO bookdao, ModelMap model, CustomerDAO customerdao, MovieDAO moviedao) {
		
		Long id = Long.parseLong(request.getParameter("id"));
		Booking book = bookdao.getBookingById(id);
		
		boolean result = bookdao.cancelBooking(id);
		
		if(result == true ){
			
            String msg = "Booking with id "+id+" has been cancelled successfully";
            model.put("msg", msg);
            
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
     	   	
     	   	Email email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(username, password));
			email.setSSLOnConnect(true);
			try {
				
				email.setFrom("bookmymovie@gmail.com");
				email.setSubject("BookMyMovie :: Movie Cancellation Confirmation");
				email.setMsg(" <h1>Your Booking has been Cancelled </h1>" + "<br>" +
						     "<h2>Booking Details<h2/>" + "<br>" +
							  "<h3>Movie Title: " +book.getShow().getMovie().getMovieTitle()+ "</h3>" + "<br>" +
							  "<h3>Theatre: "+book.getShow().getTheatre().getTheatreName()+ "</h3>" +
							  "<h4>Show Date: " + book.getShow().getShowDate() + "</h4>" +
							  "<h4>Show Time: " +book.getShow().getShowTime()+ "</h4>" +
							  "<h4>Show Screen: "+ book.getShow().getScreenNumber()+ "</h4>" +
							  "<h4>Booking Id: "+ book.getBookingId()+ "</h4>" +
							  "<h4>Booking Date: "+ book.getBookDate()+ "</h4>" +
							  "<h4>Booking Amount: Rs. "+ book.getTotal()+ "</h4>");
				email.addTo(user.getUserEmail());
				email.send();
				
			} catch (EmailException e) {
				
				e.printStackTrace();
			}
			 
			ModelAndView m = new ModelAndView("booking-history", "msg", msg);
			return m;
           
        } else{
            
           String errmsg = "Booking can not be cancelled at this point of time";
           
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
			 
    	   ModelAndView m = new ModelAndView("booking-history", "errmsg", errmsg);
		   return m; 
        }
	}
	
}
