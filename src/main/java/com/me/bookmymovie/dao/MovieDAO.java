package com.me.bookmymovie.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.springframework.stereotype.Repository;

import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Show;
import com.me.bookmymovie.pojo.Actor;
import com.me.bookmymovie.pojo.Category;
import com.me.bookmymovie.pojo.Language;
import com.me.bookmymovie.pojo.User;
import com.me.bookmymovie.dao.LanguageDAO;

@Repository
public class MovieDAO extends DAO {
	
	//Get All Movies List
	@SuppressWarnings({"deprecation"})
	public static  List<Movie> getAllMovies(){
			
		List<Movie> movie = new ArrayList<Movie>();
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = session.createCriteria(Movie.class);
			movie = crit.list();
			t.commit();
			return movie;
			
		} catch(HibernateException e) {
			
			 e.printStackTrace();
			 rollback();
			 return null;
			 
		} finally {
			
			close();
		}
			
			
	}
	
	//Add Movie to Database
	public boolean insertMovie(String movieTitle, Date releaseDate, String movieLength, String movieSynopsis, String[] categoryList, String[] languageList, String[] actorList) {
		
		Movie movie = new Movie();
		
		try {
			
			movie.setMovieTitle(movieTitle);
			movie.setMovieLength(movieLength);
			movie.setMovieLength(movieLength);
			movie.setMovieSynopsis(movieSynopsis);
			movie.setRelDate(releaseDate);
			movie.setMovieRating(0.0);
			
			for(int i=0; i<languageList.length;i++) {
				
				long langID = Long.parseLong(languageList[i]);
				Language lang = LanguageDAO.findById(langID);
				
				lang.addMovie(movie);
				
			}
			
			for(int i=0; i<categoryList.length;i++) {
				
				long catID = Long.parseLong(categoryList[i]);
				Category cat = CategoryDAO.findById(catID);
				
				cat.addMovie(movie);
				
			}
			
			for(int i=0; i<actorList.length;i++) {
				
				long actorID = Long.parseLong(actorList[i]);
				Actor actor = ActorDAO.findById(actorID);
				
				actor.addMovie(movie);
				
			}
			
			Session session = getSession();
			Transaction t = session.beginTransaction();	
			session.save(movie);
			System.out.println("nbbtttt");
			System.out.println(movie.getRelDate());
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
	
	// Update Movie Details
	public boolean updateMovie(String movieTitle, Date releaseDate, String movieLength, String movieSynopsis, String[] categoryList, String[] languageList, String[] actorList, Movie movie) {
		
		try {

			movie.setMovieTitle(movieTitle);
			movie.setMovieLength(movieLength);
			movie.setMovieLength(movieLength);
			movie.setMovieSynopsis(movieSynopsis);
			movie.setRelDate(releaseDate);
			movie.setMovieRating(0.0);
			
			// Language
			Set<Long> mainLanguage = new HashSet<Long>();
			Set<Language> languages = movie.getLanguage();
			
			for(Language l : languages) {
				
				mainLanguage.add(l.getLanguageID());
			}
			
			Set<Long> updateLanguage = new HashSet<Long>();
			
			for(int i=0; i<languageList.length;i++) {
				
				updateLanguage.add(Long.parseLong(languageList[i]));
			}
			
			mainLanguage.removeAll(updateLanguage);
			
			for(Long id : mainLanguage) {
				
				Language lang = LanguageDAO.findById(id);
				lang.removeMovie(movie);
			}
			
			updateLanguage.removeAll(mainLanguage);
			
			for(Long id : updateLanguage) {
				
				Language lang = LanguageDAO.findById(id);
				lang.addMovie(movie);	
			}
			
			// Actor
			Set<Long> mainCast = new HashSet<Long>();
			Set<Actor> actors = movie.getCast();
			
			for(Actor a : actors) {
				
				mainCast.add(a.getActorID());
			}
			
			Set<Long> updateCast = new HashSet<Long>();
			for(int i=0; i<actorList.length;i++) {
				
				updateCast.add(Long.parseLong(actorList[i]));	
			}
			
			mainCast.removeAll(updateCast);
			
			for(Long id : mainCast) {
				
				Actor act = ActorDAO.findById(id);
				act.removeMovie(movie);	
			}
			
			updateCast.removeAll(mainCast);
			
			for(Long id : updateCast) {
				
				Actor act = ActorDAO.findById(id);
				act.addMovie(movie);
			}
		
			// Category
			Set<Long> mainCategories = new HashSet<Long>();
			Set<Category> categories = movie.getCategory();
			
			for(Category c : categories) {
				mainCategories.add(c.getCategoryID());
			}
			
			
			Set<Long> updateCategories = new HashSet<Long>();
			
			for(int i=0; i<categoryList.length;i++) {
				
				updateCategories.add(Long.parseLong(categoryList[i]));
				
			}
			
			mainCategories.removeAll(updateCategories);
			
			for(Long id : mainCategories) {
				
				Category ca = CategoryDAO.findById(id);
				ca.removeMovie(movie);
			}
			
			updateCategories.removeAll(mainCategories);
			
			for(Long id : updateCategories) {
				
				Category ca = CategoryDAO.findById(id);
				ca.addMovie(movie);
			}
			
			Session session = getSession();
			Transaction t = session.beginTransaction();	
			session.save(movie);
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

	// Add Movie to Database
	public boolean addMovie(Movie movie) {
		
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();		
			session.save(movie);
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
	
	// Get specific Movie by ID
	@SuppressWarnings({"deprecation"})
	public Movie getMovieById(Long movieid) {
			
			Criteria crit = getSession().createCriteria(Movie.class);
			crit.add(Restrictions.eq("movieID", movieid));
			Movie movie = (Movie) crit.uniqueResult();
			return movie;
			
	}
	
	// Delete Movie from Database
	@SuppressWarnings({"deprecation"})
	public boolean deleteMovie(long id) {
			
			try {
				
				Session session = getSession();
				Transaction t = session.beginTransaction();
				Criteria crit = session.createCriteria(Movie.class);
				crit.add(Restrictions.eq("movieID", id));
				Movie movie = (Movie) crit.uniqueResult();
				session.delete(movie);
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

	// Get specific Movie by Title
	public static Movie getMovieByTitle(String movieTitle) {
	
		try {
			
			Session session = getSession();
			Transaction t = session.beginTransaction();
			Criteria crit = getSession().createCriteria(Movie.class);
			crit.add(Restrictions.eq("movieTitle", movieTitle));
			Movie movie = (Movie) crit.uniqueResult();
			t.commit();
			return movie;
			
		} catch(HibernateException e) {
			
			e.printStackTrace();
			rollback();
			return null;
			 
		} finally {
			
			close();
		}	
		
	}
	
	@SuppressWarnings({"deprecation"})
	public static List<Movie> searchMoviesbyTitle(String keyword){
	       
        List<Movie> movies = new ArrayList<Movie>();
        try{
        	
        	Session session = getSession();
			Transaction t = session.beginTransaction();
            Query<Movie> q = getSession().createQuery("from Movie WHERE movieTitle LIKE :keyword");
            q.setString("keyword", keyword+"%");
            movies = q.list();
            t.commit();
            return movies;
            
        } catch(HibernateException e){
        	 e.printStackTrace();
			 rollback();
			 return null;
        } finally{
            close();
        }
       
    }
	
	@SuppressWarnings({"deprecation"})
	public static List<Movie> searchMoviesbyCast(String keyword){
		
		 List<Movie> movies = new ArrayList<Movie>();
		 List<Movie> movieList = MovieDAO.getAllMovies();
		 
		 for(Movie m : movieList) {
			 
			 Set<Actor> actorsList = m.getCast();
			 for(Actor a : actorsList) {
				
				 if(a.getActorName().startsWith(keyword)) {
					 movies.add(m);
				 }
			 }
		 
		 }
		 
		 return movies;
  
    }
	
	@SuppressWarnings({"deprecation"})
	public static List<Movie> searchMoviesbyLanguage(String keyword){
		
		 List<Movie> movies = new ArrayList<Movie>();
		 List<Movie> movieList = MovieDAO.getAllMovies();
		 
		 
		 
		 for(Movie m : movieList) {
			 
			 Set<Language> languageList = m.getLanguage();
			 
			 for(Language l : languageList) {
				 
				 if(l.getLanguageName().startsWith(keyword)) {
					 movies.add(m);
				 }
			 }
			 
			 
		 }
		 
		 return movies;
  
    }
	
	@SuppressWarnings({"deprecation"})
	public static List<Movie> searchMoviesbyCategory(String keyword){
		
		 List<Movie> movies = new ArrayList<Movie>();
		 List<Movie> movieList = MovieDAO.getAllMovies();
		 
		 for(Movie m : movieList) {
			 
			 Set<Category> categoryList = m.getCategory();
			 
			 for(Category c : categoryList) {
				 
				 if(c.getCategoryName().startsWith(keyword)) {
					 movies.add(m);
				 }
			 }
			 
			
		 }
		 
		 return movies;
 
       
    }
	
	// Search Movie by Keyword along with SearchBy
	public List<Movie> searchMovie(String searchby, String keyword) {
		
		List<Movie> searchResult = new ArrayList<Movie>();
		
		if(searchby.equals("Movie Title")) {
			
			searchResult = MovieDAO.searchMoviesbyTitle(keyword);
			return searchResult;
			
		}
		
		if(searchby.equals("Search By")) {
			
			searchResult = MovieDAO.getAllMovies();
			return searchResult;
			
		}
		
		if(searchby.equals("Cast")) {
			
			searchResult = MovieDAO.searchMoviesbyCast(keyword);
			return searchResult;
			
		}
		
		if(searchby.equals("Category")) {
			
			searchResult = MovieDAO.searchMoviesbyCategory(keyword);
			return searchResult;
			
		}
		
		if(searchby.equals("Language")) {
	
			searchResult = MovieDAO.searchMoviesbyLanguage(keyword);
			return searchResult;
			
		}
		
		return null;
		
	}
	
	
}
