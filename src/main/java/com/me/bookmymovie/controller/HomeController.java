package com.me.bookmymovie.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.bookmymovie.dao.MovieDAO;
import com.me.bookmymovie.dao.ShowDAO;
import com.me.bookmymovie.dao.TheatreDAO;
import com.me.bookmymovie.dao.UserDAO;
import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Seat;
import com.me.bookmymovie.pojo.Show;
import com.me.bookmymovie.pojo.Theatre;
import com.me.bookmymovie.pojo.User;

@Controller
public class HomeController {
	

	
	// HomePage
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView goHome(MovieDAO moviedao) {
		
		List<Movie> list = moviedao.getAllMovies();
		ModelAndView mv =new ModelAndView("index", "movies", list);
		return mv;

	}
	
	//HomePage
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHomePage(MovieDAO moviedao) {
		
		List<Movie> list = moviedao.getAllMovies();
        
		ModelAndView mv =new ModelAndView("index", "movies", list);
		return mv;
	}
	
	//GET Sign Up Page Request
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView getRegister(Map<String, Object> model, MovieDAO moviedao){
		
		User user = new User();
		List<Movie> list = moviedao.getAllMovies();
		
		model.put("user", user);
		model.put("movies",list);
		
		ModelAndView mv =new ModelAndView("register");
		return mv;
	}
	
	//GET Login Page Request
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView getLogin(Map<String, Object> model, MovieDAO moviedao){
		
		List<Movie> list = moviedao.getAllMovies();
		
		model.put("movies",list);
		
		ModelAndView mv =new ModelAndView("login");
		return mv;
	}
	
	//GET Theatres 
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ModelAndView search(HttpServletRequest request, ModelMap model, TheatreDAO theatredao, MovieDAO moviedao){
		
		String city = request.getParameter("city");
		String title = request.getParameter("movie");
		
		List result = theatredao.getTheatres(title, city);
		
		Movie movie = moviedao.getMovieByTitle(title);
		List<Movie> movieslist = moviedao.getAllMovies();
		
		model.put("movies",movieslist);
		model.put("movie", movie);
		model.put("city",city);
		
		ModelAndView mv =new ModelAndView("search-movie", "result", result);
		return mv;
	}
	
	// Book Seat GET Request
	@RequestMapping(value="/bookseats",method=RequestMethod.GET)
	public ModelAndView bookseat(HttpServletRequest request, ModelMap model, TheatreDAO theatredao, MovieDAO moviedao, ShowDAO showdao){
		
		Long tid = Long.parseLong(request.getParameter("theatreID"));
		Theatre theatre = theatredao.getTheatreById(tid);
		
		List<Movie> list = moviedao.getAllMovies();
		
		Long id = Long.parseLong(request.getParameter("showID"));
		Show sh = showdao.getShowById(id);
		
		List<Seat> seats = sh.getSeats();
		
		model.put("theatre", theatre);
		model.put("movies",list);
		model.put("show", sh);
		model.put("seats",seats);
		
		ModelAndView mv =new ModelAndView("book-seat");
		return mv;
	}
	
	// GET Shows by Theatre
	@RequestMapping(value="/search/viewShows/{theatre}",method=RequestMethod.GET)
	public ModelAndView viewShows(@PathVariable(name="theatre") String theatreName,HttpServletRequest request, ModelMap model, TheatreDAO theatredao, MovieDAO moviedao, ShowDAO showdao) throws ParseException{
		
		Long id = Long.parseLong(request.getParameter("theatre"));
		Theatre theatre = theatredao.getTheatreById(id);
		
		String city = theatre.getTheatreCity();
		String title = request.getParameter("movieTitle");
	
		List result = showdao.viewShows(title, city, theatreName);
		List<Movie> list = moviedao.getAllMovies();
		
		model.put("theatre", theatre);
		model.put("movies",list);
		model.put("movie", title);
		
		ModelAndView mv =new ModelAndView("view-show", "result", result);
		return mv;
	}
	
	// GET Show Layout 
	@RequestMapping(value="/search/viewShows/{theatre}/getShowsByDate",method=RequestMethod.GET)
	public ModelAndView viewShowsByDate(@PathVariable(name="theatre") String theatreName, HttpServletRequest request, ModelMap model, TheatreDAO theatredao, MovieDAO moviedao, ShowDAO showdao) throws ParseException{
		
		Long id = Long.parseLong(request.getParameter("theatre"));
		String filterDate = request.getParameter("filterDate");
		Theatre theatre = theatredao.getTheatreById(id);
		String city = theatre.getTheatreCity();
		String title = request.getParameter("movieTitle");
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date inputDate = format.parse(filterDate);	

		List<Show> resultlist = showdao.viewShows(title, city, theatreName);
		List<Show> result = showdao.getShowsByDate(inputDate, resultlist);
		List<Movie> list = moviedao.getAllMovies();
		
		model.put("theatre", theatre);
		model.put("movies",list);
		
		ModelAndView mv =new ModelAndView("view-show", "result", result );
		return mv;
	}
	
}
