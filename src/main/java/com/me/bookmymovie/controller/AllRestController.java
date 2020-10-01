package com.me.bookmymovie.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonView;
import com.me.bookmymovie.dao.MovieDAO;
import com.me.bookmymovie.dao.TheatreDAO;
import com.me.bookmymovie.dao.UserDAO;
import com.me.bookmymovie.pojo.Movie;
import com.me.bookmymovie.pojo.Theatre;
import com.me.bookmymovie.pojo.User;

@Controller
@RestController
public class AllRestController {

	
	@RequestMapping(value="admin/getUser/{id}", method=RequestMethod.GET, headers="Accept=application/json")
	public User getUserById(@PathVariable(name="id") Long id, UserDAO userdao) {
		return userdao.getUserById(id);
		
	}

	@RequestMapping(value="admin/getMovie/{id}", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody Movie getMovieById(@PathVariable(name="id") Long id, MovieDAO moviedao) {
		return moviedao.getMovieById(id);
		
	}
	
	@RequestMapping(value="admin/manage-theatres/getTheatre/{id}", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody Theatre getTheatreById(@PathVariable(name="id") Long id, TheatreDAO theatredao) {
		return theatredao.getTheatreById(id);
		
	}
}
