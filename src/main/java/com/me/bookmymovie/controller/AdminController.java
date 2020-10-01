package com.me.bookmymovie.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.me.bookmymovie.dao.ActorDAO;
import com.me.bookmymovie.dao.CategoryDAO;
import com.me.bookmymovie.dao.LanguageDAO;
import com.me.bookmymovie.dao.MovieDAO;
import com.me.bookmymovie.dao.TheatreDAO;
import com.me.bookmymovie.dao.UserDAO;
import com.me.bookmymovie.pojo.Actor;
import com.me.bookmymovie.pojo.Category;
import com.me.bookmymovie.pojo.Language;
import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Theatre;
import com.me.bookmymovie.pojo.User;


@Controller
public class AdminController {
	
	@Autowired
	@Value("${email.username}")
    private String username;
	
	@Autowired
	@Value("${email.password}")
    private String password;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	// Admin Dashboard
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getAdminHomePage() {
		
		ModelAndView m = new ModelAndView("admin");
		return m;
	}
	
	// Logout 
	@RequestMapping("/logout")
	public ModelAndView getLogout(HttpSession session){
		session.invalidate();
		ModelAndView m=new ModelAndView("redirect:/");
		return m;
	}
	
	// Add Movie POST Request
	@RequestMapping(value = "/admin/addMovie", method = RequestMethod.POST)
	public ModelAndView addMovie(HttpServletRequest request, MovieDAO moviedao, LanguageDAO languagedao, CategoryDAO categorydao, ActorDAO actordao, Map<String, Object> model) throws ParseException {
		
		String title = request.getParameter("movieTitle");
		String date = request.getParameter("releaseDate");
		String synopsis = request.getParameter("synopsis");
		String mins = request.getParameter("minutes");
		String hours = request.getParameter("hours");
		String[] categories = request.getParameterValues("category");
		String[] languages = request.getParameterValues("language");
		String[] actors = request.getParameterValues("cast");
	
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date releaseDate = format.parse(date);
		
		if(hours.length() == 1 ) {
			hours = "0" + hours;
 		}
		
		String length = hours + " hrs " + mins + " mins";
		
		boolean result = moviedao.insertMovie(title, releaseDate, length, synopsis, categories, languages, actors);
		
		if(result == true) {
			
			String msg = "Movie Added Successfully";
		
            List<Movie> list = moviedao.getAllMovies();
            model.put("movie", list);
            
            List<Category> categoryList = categorydao.getAllCategories();
            model.put("category",categoryList);
            
            List<Language> languageList = languagedao.getAllLanguages();
   		 	model.put("language",languageList);
   		 
	   		List<Actor> actorList = actordao.getAllActors();
	   		model.put("actor",actorList);
            
	   		ModelAndView mv = new ModelAndView("manage-movies", "msg", msg);
            return mv;
            	
		} else {
			
			 String errmsg = "Movie Failed to Add";
			 
			 List<Movie> list = moviedao.getAllMovies();
	         model.put("movie", list);
	         
	         List<Category> categoryList = categorydao.getAllCategories();
	         model.put("category",categoryList);
	         
	         List<Language> languageList = languagedao.getAllLanguages();
			 model.put("language",languageList);
			 
			 List<Actor> actorList = actordao.getAllActors();
			 model.put("actor",actorList);
	            
			 ModelAndView mv = new ModelAndView("manage-movies", "errmsg", errmsg);
	         return mv;
		}
	}
	
	// GET Update Movie Page
	@RequestMapping(value = "/admin/updateMovie", method = RequestMethod.GET)
	public ModelAndView updateMovie(HttpServletRequest request, MovieDAO moviedao, LanguageDAO languagedao, CategoryDAO categorydao, ActorDAO actordao, Map<String, Object> model) throws ParseException {
		
		String title = request.getParameter("movieTitle");
		String date = request.getParameter("releaseDate");
		String synopsis = request.getParameter("synopsis");
		String mins = request.getParameter("minutes");
		String hours = request.getParameter("hours");
		String[] categories = request.getParameterValues("category");
		String[] languages = request.getParameterValues("language");
		String[] actors = request.getParameterValues("cast");
		
		Long id = Long.parseLong(request.getParameter("movieID"));
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date releaseDate = format.parse(date);
		
		Movie movie = moviedao.getMovieById(id);	
		
		if(hours.length() == 1 ) {
			hours = "0" + hours;
 		}
		
		String length = hours + " hrs " + mins + " mins";
		
		boolean result = moviedao.updateMovie(title, releaseDate, length, synopsis, categories, languages, actors, movie);
		
		if(result == true) {
			
			String msg = "Movie Updated Successfully";
		
            List<Movie> list = moviedao.getAllMovies();
            model.put("movie", list);
            
            List<Category> categoryList = categorydao.getAllCategories();
            model.put("category",categoryList);
            
            List<Language> languageList = languagedao.getAllLanguages();
   		 	model.put("language",languageList);
   		 
	   		List<Actor> actorList = actordao.getAllActors();
	   		model.put("actor",actorList);
            
	   	    ModelAndView mv = new ModelAndView("manage-movies", "msg", msg);
            return mv;
			
		} else {
			
			 String errmsg = "Movie Failed to Update";
	
			 List<Movie> list = moviedao.getAllMovies();
	         model.put("movie", list);
	         
	         List<Category> categoryList = categorydao.getAllCategories();
	         model.put("category",categoryList);
	         
	         List<Language> languageList = languagedao.getAllLanguages();
			 model.put("language",languageList);
			 
			 List<Actor> actorList = actordao.getAllActors();
			 model.put("actor",actorList);
	           
			 ModelAndView mv = new ModelAndView("manage-movies", "errmsg", errmsg);
	         return mv;	
		}	
	}
	
	// Manage Movie Dashboard
	@RequestMapping("/admin/manage-movies")
	public ModelAndView getManageMovies(MovieDAO moviedao, LanguageDAO languagedao, CategoryDAO categorydao, ActorDAO actordao,  Map<String, Object> model){
		
         List<Movie> list = moviedao.getAllMovies();
         model.put("movie", list);
         
         List<Category> categoryList = categorydao.getAllCategories();
         model.put("category",categoryList);
         
         List<Language> languageList = languagedao.getAllLanguages();
		 model.put("language",languageList);
		 
		 List<Actor> actorList = actordao.getAllActors();
		 model.put("actor",actorList);
		
		 ModelAndView mv = new ModelAndView("manage-movies");
         return mv;
         
	}
	
	// Search Movie by Keyword
	@RequestMapping(value="/admin/searchMovie",  method = RequestMethod.GET)
	public ModelAndView getSearchedMovies(HttpServletRequest request, MovieDAO moviedao, LanguageDAO languagedao, CategoryDAO categorydao, ActorDAO actordao,  Map<String, Object> model){
		
		
		String searchby = request.getParameter("searchby");
		String keyword = request.getParameter("keyword");
        
        List<Movie> list = moviedao.searchMovie(searchby, keyword);
        model.put("movie", list);
        
        List<Category> categoryList = categorydao.getAllCategories();
        model.put("category",categoryList);
        
        List<Language> languageList = languagedao.getAllLanguages();
		model.put("language",languageList);
		 
		List<Actor> actorList = actordao.getAllActors();
		model.put("actor",actorList);
		ModelAndView mv = new ModelAndView("manage-movies");
        return mv;
        
	}
	
	// Update Movie GET Request
	@RequestMapping(value = "/admin/manage-movies/updateMovie", method = RequestMethod.GET)
	public ModelAndView updateMovie(HttpServletRequest request, Map<String, Object> model, MovieDAO moviedao, LanguageDAO languagedao, CategoryDAO categorydao, ActorDAO actordao){
		
		
		Long movieId = Long.parseLong(request.getParameter("movieId"));
		Movie movie = moviedao.getMovieById(movieId);
		model.put("movie", movie);
	
	 	List<Category> categoryList = categorydao.getAllCategories();
        model.put("category",categoryList);
        
        List<Language> languageList = languagedao.getAllLanguages();
		model.put("language",languageList);
		 
		List<Actor> actorList = actordao.getAllActors();
		model.put("actor",actorList);
		 
		ModelAndView m = new ModelAndView("update-movie");
		return m;
		
	}
	
	// Add Actor POST Request
	@RequestMapping(value="/admin/addActor",  method = RequestMethod.POST)
	public ModelAndView addActor(HttpServletRequest request, MovieDAO moviedao, LanguageDAO languagedao, CategoryDAO categorydao, ActorDAO actordao, Map<String, Object> model) {
		
		String name = request.getParameter("actorName");
		String msg = actordao.addActor(name);
	
        List<Movie> list = moviedao.getAllMovies();
        model.put("movie", list);
         
        List<Category> categoryList = categorydao.getAllCategories();
        model.put("category",categoryList);
         
        List<Language> languageList = languagedao.getAllLanguages();
		model.put("language",languageList);
		 
   		List<Actor> actorList = actordao.getAllActors();
   		model.put("actor",actorList);
        
   		ModelAndView mv = new ModelAndView("manage-movies", "msg", msg);
        return mv;
		
	}
	
	// Add Category POST Request
	@RequestMapping(value="/admin/addCategory",  method = RequestMethod.POST)
	public ModelAndView addCategory(HttpServletRequest request, MovieDAO moviedao, LanguageDAO languagedao, CategoryDAO categorydao, ActorDAO actordao, Map<String, Object> model) {
		
		String name = request.getParameter("categoryName");
		
		String msg = categorydao.addCategory(name);
		
		 ModelAndView mv = new ModelAndView("manage-movies", "msg", msg);
         
         List<Movie> list = moviedao.getAllMovies();
         model.put("movie", list);
         
         List<Category> categoryList = categorydao.getAllCategories();
         model.put("category",categoryList);
         
         List<Language> languageList = languagedao.getAllLanguages();
		 	model.put("language",languageList);
		 
   		 List<Actor> actorList = actordao.getAllActors();
   		 model.put("actor",actorList);
         
         return mv;

	}
	
	// Add Language POST Request 
	@RequestMapping(value="/admin/addLanguage",  method = RequestMethod.POST)
	public ModelAndView addLanguage(HttpServletRequest request, MovieDAO moviedao, LanguageDAO languagedao, CategoryDAO categorydao, ActorDAO actordao, Map<String, Object> model) {
		
		String name = request.getParameter("languageName");
		
		String msg = languagedao.addLanguage(name);
		
		 ModelAndView mv = new ModelAndView("manage-movies", "msg", msg);
         
         List<Movie> list = moviedao.getAllMovies();
         model.put("movie", list);
         
         List<Category> categoryList = categorydao.getAllCategories();
         model.put("category",categoryList);
         
         List<Language> languageList = languagedao.getAllLanguages();
		 	model.put("language",languageList);
		 
   		 List<Actor> actorList = actordao.getAllActors();
   		 model.put("actor",actorList);
         
         return mv;
	}
	
	// Delete Movie POST Request
	@RequestMapping(value="/admin/deleteMovie",  method = RequestMethod.POST)
	public ModelAndView deleteMovie(HttpServletRequest request, MovieDAO moviedao, LanguageDAO languagedao, CategoryDAO categorydao, ActorDAO actordao, Map<String, Object> model) {
		
		Long id = Long.parseLong(request.getParameter("id"));
		boolean result = moviedao.deleteMovie(id);
		
		if(result == true ){
            String msg = "Movie with id "+id+" has been deleted successfully";
            
            List<Movie> list = moviedao.getAllMovies();
            model.put("movie", list);
            
            List<Category> categoryList = categorydao.getAllCategories();
            model.put("category",categoryList);
            
            List<Language> languageList = languagedao.getAllLanguages();
   		 	model.put("language",languageList);
   		 
	   		List<Actor> actorList = actordao.getAllActors();
	   		model.put("actor",actorList);
            
	   		ModelAndView mv = new ModelAndView("manage-movies", "msg", msg);
            return mv;
           
        } else {
        	
            String errmsg = "Movie can not be deleted at this point of time";
            
            List<Movie> list = moviedao.getAllMovies();
            model.put("movie", list);
            
            List<Category> categoryList = categorydao.getAllCategories();
            model.put("category",categoryList);
            
            List<Language> languageList = languagedao.getAllLanguages();
   		 	model.put("language",languageList);
   		 
	   		List<Actor> actorList = actordao.getAllActors();
	   		model.put("actor",actorList);
            
	   		ModelAndView mv = new ModelAndView("manage-movies", "errmsg", errmsg);
            return mv;
        }
	}
	
	// Manage Theatre Dashboard
	@RequestMapping("/admin/manage-theatres")
	public ModelAndView getManageCinemas(Map<String, Object> model, UserDAO userdao, TheatreDAO theatredao){
		
		List<Theatre> theatreList = theatredao.getAllTheatres();
		model.put("theatreList", theatreList);
		
		ModelAndView m = new ModelAndView("manage-theatres");
		return m;
	}
	
	// Update Theatre POST Request
	@RequestMapping(value = "/admin/manage-theatres/updateTheatre", method = RequestMethod.POST)
	public ModelAndView viewAddTheatre(HttpServletRequest request, Map<String, Object> model, TheatreDAO theatredao){
		
		
		Long id = Long.parseLong(request.getParameter("id"));
		Theatre theatre = theatredao.getTheatreById(id);
		String name = request.getParameter("theatreName");
		String city = request.getParameter("theatreCity");
		String address = request.getParameter("theatreAddress");
		boolean result = theatredao.updateTheatre(theatre, name, city, address);
		
		if(result == true ){
            String msg = "User with id "+id+" has been updates successfully";
            model.put("msg", msg);
           
            List<Theatre> theatreList = theatredao.getAllTheatres();
    		model.put("theatreList", theatreList);
            
    		ModelAndView mv = new ModelAndView("manage-theatres");
            return mv;
           
        } else{
        	
           String errmsg = "User can not be updated at this point of time";
           model.put("errmsg", errmsg);
           
           List<Theatre> theatreList = theatredao.getAllTheatres();
   		   model.put("theatreList", theatreList);
   		   
   		   ModelAndView mv = new ModelAndView("manage-theatres");
           return mv;
        }
	}
	
	// Manage Theatre Filter
	@RequestMapping(value="/admin/manage-theatres/searchby",  method = RequestMethod.GET)
	public ModelAndView getSearchedTheatre(HttpServletRequest request, TheatreDAO theatredao,  Map<String, Object> model){
		
		String searchby = request.getParameter("searchby");
		String keyword = request.getParameter("keyword");
		
		
		List<Theatre> theatreList = theatredao.searchTheatre(searchby, keyword);
		model.put("theatreList", theatreList);
	
		ModelAndView m = new ModelAndView("manage-theatres");
        return m;
        
	}
	
	// Managae User Dashboard
	@RequestMapping("/admin/manage-users")
	public ModelAndView getManageUsers(UserDAO userdao, ModelMap model){
		
		List<User> list = userdao.getAllUsers();
		model.put("users",list);
		ModelAndView m = new ModelAndView("manage-users");
		return m;
	}
	
	//Add User GET Request
	@RequestMapping(value = "/admin/manage-users/addUser", method = RequestMethod.GET)
	public ModelAndView viewAddUser(Map<String, Object> model){
		
		List<String> usertype = new ArrayList<String>();
		usertype.add("Admin");
		usertype.add("Theatre");
		
		User user = new User();
		model.put("user", user);
		model.put("usertype", usertype);
		ModelAndView m = new ModelAndView("add-user");
		
		return m;
	}
	
	// Add New User POST Request
	@RequestMapping(value = "/admin/manage-users/addUser", method = RequestMethod.POST)
	public ModelAndView AddUser(HttpServletRequest request, UserDAO userdao, @Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model, SessionStatus status ){
		
		String password = request.getParameter("userPwd");
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
		
		if(result.hasErrors()) {
		 
		 	List<String> usertype = new ArrayList<String>();
			usertype.add("Admin");
			usertype.add("Theatre");
			
		 	ModelAndView m = new ModelAndView("add-user", "usertype", usertype);
            return m;
	    }
		 
	 	String userName = request.getParameter("userName");
	 	
	 	String userEmail = request.getParameter("userEmail");
	 	String userType = request.getParameter("userType");
	 	String hashedPassword = passwordEncoder.encode(password);
	 	boolean r = userdao.doUserExists(userEmail);
		
		 if(r == false) {
			 
			 status.setComplete();
			 
			 String errmsg = "User Already Exists.!";
			 
			 List<User> list = userdao.getAllUsers();
			 model.put("users",list);
			 
			 List<String> usertype = new ArrayList<String>();
			 usertype.add("Admin");
			 usertype.add("Theatre");
			 model.put("usertype", usertype);
			 
			 ModelAndView m = new ModelAndView("add-user", "errmsg", errmsg);
	         return m;
			 
		 } else {
		 	
		 	userdao.addUser(userEmail, hashedPassword, userName, userType);
		 
		 	status.setComplete();
			 
			String msg = "User Added Successfully";
			 
			List<User> list = userdao.getAllUsers();
			model.put("users",list);
			 
			Email email = new HtmlEmail();
			 
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(username, password));
			email.setSSLOnConnect(true);
			
			try {
				
				email.setFrom("bookmymovie@gmail.com");
				email.setSubject("BookMyMovie :: Your Account has been Created");
				email.setMsg(" <h2>Account Details </h2>" + "<br>" +
							  "<h3>Email ID: " +userEmail+ "</h3>" + 
							  "<h3>Password: "+password+ "</h3>" +
							  "<h3>User Name: " +userName+ "</h3>" +
							  "<span style:'color:red;'><b>Note: Please update your password on first time login.</b></span>");
				email.addTo(userEmail);
				email.send();
				
			} catch (EmailException e) {
				
				e.printStackTrace();
			}
			
			ModelAndView m = new ModelAndView("manage-users", "msg", msg);
			return m; 
		 }
	}
	
	// Delete User POST Request
	@RequestMapping(value="/admin/deleteUser",  method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request, UserDAO userdao, ModelMap model) {
		
		Long id = Long.parseLong(request.getParameter("id"));
		boolean result = userdao.inactivateUser(id);
		
		if(result == true ){
            String msg = "User with id "+id+" has been inactivated successfully";
            model.put("msg", msg);
            
            List<User> list = userdao.getAllUsers();
			model.put("users",list);
			
			ModelAndView m = new ModelAndView("manage-users", "msg", msg);
			return m;
           
        } else{
            
           String errmsg = "User can not be deleted at this point of time";
           
           List<User> list = userdao.getAllUsers();
		   model.put("users",list);
			 
		   ModelAndView m = new ModelAndView("manage-users", "errmsg", errmsg);
		   return m;
        }
	}
	
	// User Activate/Inactivate 
	@RequestMapping(value="/admin/activateUser",  method = RequestMethod.POST)
	public ModelAndView activateUser(HttpServletRequest request, UserDAO userdao, ModelMap model) {
		
		Long id = Long.parseLong(request.getParameter("id"));
		boolean result = userdao.activateUser(id);
		
		if(result == true ){
			
            String msg = "User with id "+id+" has been activated successfully";
            model.put("msg", msg);
          
            List<User> list = userdao.getAllUsers();
			model.put("users",list);
			
			ModelAndView m = new ModelAndView("manage-users", "msg", msg);
		    return m;
           
        } else{
            
           String errmsg = "User can not be activated at this point of time";
           
           List<User> list = userdao.getAllUsers();
		   model.put("users",list);
			
		   ModelAndView m = new ModelAndView("manage-users", "errmsg", errmsg);
		   return m;
        }
	}
	
	// Manage Users Filter
	@RequestMapping(value = "/admin/manage-users/filter", method = RequestMethod.GET)
	public ModelAndView filterUser(HttpServletRequest request,Map<String, Object> model, UserDAO userdao){
		
		String filter = request.getParameter("filter");

		List<User> list = userdao.filterUser(filter);
		model.put("users",list);
		
		ModelAndView mv = new ModelAndView("manage-users");
		return mv;
		
	}
}
