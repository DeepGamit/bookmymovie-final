package com.me.bookmymovie.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.me.bookmymovie.dao.BookDAO;
import com.me.bookmymovie.dao.MovieDAO;
import com.me.bookmymovie.dao.ShowDAO;
import com.me.bookmymovie.dao.TheatreDAO;
import com.me.bookmymovie.pojo.Booking;
import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Seat;
import com.me.bookmymovie.pojo.Show;
import com.me.bookmymovie.pojo.Theatre;
import com.me.bookmymovie.pojo.User;

@Controller
public class TheatreController {

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	// Theatre Dashboard
	@RequestMapping(value = "/theatre", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getTheatreHomePage(HttpSession session, Map<String, Object> model, TheatreDAO theatredao, ShowDAO showdao) {
		
		User user = (User) session.getAttribute("userTheatre");
		Theatre theatre = theatredao.getTheatreByUser(user.getUserID());
		
		List<Show> shows = showdao.getShowsByTheatre(theatre);	
		
		ModelAndView m = new ModelAndView("theatre", "shows", shows);
		return m;
	}
	
	// Edit Account Details GET Request
	@RequestMapping(value = "/theatre/editAccountDetails", method = RequestMethod.GET)
	public ModelAndView viewEditAccount(HttpSession session, Map<String, Object> model, TheatreDAO theatredao){
		
		User user = (User) session.getAttribute("userTheatre");
		Theatre theatre = theatredao.getTheatreByUser(user.getUserID());
		
		
		ModelAndView m = new ModelAndView("theatre-edit");
		model.put("theatre", theatre);
		model.put("user", user);
		return m;
	}
	
	// Edit Account Details POST Request
	@RequestMapping(value = "/theatre/editAccountDetails", method = RequestMethod.POST)
	public ModelAndView editAccount(HttpServletRequest request,  @Valid @ModelAttribute("user") User user, BindingResult result, SessionStatus status, HttpSession session, Map<String, Object> model, TheatreDAO theatredao, ShowDAO showdao){
		
		String password = request.getParameter("userPwd");
		String confirm = request.getParameter("confirmPwd");
		
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

		 	ModelAndView m = new ModelAndView("theatre-edit");
            return m;
        }
	
		
		String hashedPassword = passwordEncoder.encode(password);
		String userName = request.getParameter("userName");
		
		status.setComplete();
		
		User u = (User) session.getAttribute("userTheatre");
		Theatre theatre = theatredao.getTheatreByUser(u.getUserID());
		theatredao.updateTheatreDetails(hashedPassword, userName, theatre);
		
		List<Show> shows = showdao.getShowsByTheatre(theatre);	
		
		String msg = "Theatre Account Details updated Successfully";
		
		ModelAndView m = new ModelAndView("theatre", "msg", msg);
        model.put("shows", shows);
		return m;
	}
	
	// Add Show GET Request
	@RequestMapping(value = "/theatre/addShow", method = RequestMethod.GET)
	public ModelAndView viewAddShow(Map<String, Object> model, MovieDAO moviedao){
		
		List<Movie> movies = moviedao.getAllMovies();
		
		ModelAndView m = new ModelAndView("add-show");
        model.put("movie", movies);
		return m;
	}
	
	// Add Show POST Request
	@RequestMapping(value = "/theatre/addShow", method = RequestMethod.POST)
	public ModelAndView AddShow(HttpServletRequest request, HttpSession session, Map<String, Object> model, TheatreDAO theatredao, ShowDAO showdao, MovieDAO moviedao) throws ParseException{
		
		Long movieId = Long.parseLong(request.getParameter("movie"));
		Movie movie = moviedao.getMovieById(movieId);
		String datetime = request.getParameter("showDateTime");
		Double seatPrice = Double.parseDouble(request.getParameter("seatPrice"));
		Integer nor = Integer.parseInt(request.getParameter("numberOfRows"));
		Integer nos = Integer.parseInt(request.getParameter("numberOfSeats"));
		Integer sn = Integer.parseInt(request.getParameter("screenNumber"));
		
		String date = "";
		String time = "";
		for (int i = 0; i < datetime.length(); i++){
		    char c = datetime.charAt(i);   
		    
		    if(i<5) {
		    	time = time + c;	
		    } 
		    
		    if(i>5){
		    	date = date + c;
		    }
		}
		
		String stime = time + ":00";
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date showDate = format.parse(date);
		Date showTime = new SimpleDateFormat("hh:mm:ss").parse(stime);
		
		User user = (User) session.getAttribute("userTheatre");
		Theatre theatre = theatredao.getTheatreByUser(user.getUserID());
		
		showdao.addShow(movie, showDate, showTime, seatPrice, nor, nos, theatre, sn);
		
		List<Show> shows = showdao.getShowsByTheatre(theatre);	
		
		ModelAndView m = new ModelAndView("theatre");
        model.put("shows", shows);
		return m;
	}
	
	// View Bookings GET Request
	@RequestMapping(value = "/theatre/viewBookings", method = RequestMethod.GET)
	public ModelAndView viewBookings(HttpServletRequest request,Map<String, Object> model, ShowDAO showdao){
		
		Long showid = Long.parseLong(request.getParameter("showId"));
		Show show = showdao.getShowById(showid);
		
		List<Booking> bookings = show.getBookings();
		
		ModelAndView m = new ModelAndView("theatre-booking", "bookings", bookings);
		model.put("show", showid);
		return m;
	}
	 
	// View Layout GET Request
	@RequestMapping(value = "/theatre/viewLayout", method = RequestMethod.GET)
	public ModelAndView viewLayout(HttpServletRequest request,Map<String, Object> model, ShowDAO showdao){
		
		Long showid = Long.parseLong(request.getParameter("showId"));
		Show show = showdao.getShowById(showid);
		
		List<Seat> seats = show.getSeats();
		
		ModelAndView m = new ModelAndView("theatre-viewLayout");
		model.put("show", show);
		model.put("seats",seats);
		return m;
	}
	
	// View Booking Filter
	@RequestMapping(value = "/theatre/viewBookings/filter", method = RequestMethod.GET)
	public ModelAndView filterUser(HttpServletRequest request,Map<String, Object> model, ShowDAO showdao, BookDAO bookdao){
		
		String filter = request.getParameter("filter");
		Long showid = Long.parseLong(request.getParameter("show"));
		Show show = showdao.getShowById(showid);
		
		List<Booking> bookings = show.getBookings();
		List<Booking> bookinglist = bookdao.filterBooking(filter, bookings);
		
		ModelAndView m = new ModelAndView("theatre-booking", "bookings", bookinglist);
		model.put("show", showid);
		return m;	
	}
	
	// Search Shows 
	@RequestMapping(value="/theatre/searchShow",  method = RequestMethod.GET)
	public ModelAndView getSearchedTheatre(HttpSession session, HttpServletRequest request,  Map<String, Object> model, ShowDAO showdao, TheatreDAO theatredao){
		
		String searchby = request.getParameter("searchby");
		String keyword = request.getParameter("keyword");
		
		User user = (User) session.getAttribute("userTheatre");
		Theatre theatre = theatredao.getTheatreByUser(user.getUserID());
		List<Show> showlist = showdao.getShowsByTheatre(theatre);	
		List<Show> shows = showdao.searchShow(searchby, keyword, showlist);

		ModelAndView m = new ModelAndView("theatre");
		model.put("shows", shows);
	    return m;
	}

	// GET Shows by Date Request
	@RequestMapping(value="/theatre/getShowByDate",  method = RequestMethod.GET)
	public ModelAndView getSortedTheatre(HttpSession session, HttpServletRequest request,  Map<String, Object> model, ShowDAO showdao, TheatreDAO theatredao) throws ParseException{
		
		String filterDate = request.getParameter("filterDate");
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date inputDate = format.parse(filterDate);
		
		User user = (User) session.getAttribute("userTheatre");
		Theatre theatre = theatredao.getTheatreByUser(user.getUserID());
		List<Show> showlist = showdao.getShowsByTheatre(theatre);	
		List<Show> shows = showdao.getShowsByDate(inputDate, showlist);

		model.put("shows", shows);
		
		ModelAndView m = new ModelAndView("theatre");
        return m;
	}

}
