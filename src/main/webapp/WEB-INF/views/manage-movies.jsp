<%-- 
    Document   : manage-movies
    Created on : Mar 28, 2020, 1:10:32 PM
    Author     : deepgamit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.me.bookmymovie.pojo.Movie"%>
<%@ page import="com.me.bookmymovie.pojo.Language"%>
<%@ page import="com.me.bookmymovie.pojo.Category"%>
<%@ page import="com.me.bookmymovie.pojo.Actor"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
         
         
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/3.1.3/css/bootstrap-datepicker.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/css/bootstrap-select.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/3.1.3/js/bootstrap-datepicker.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/js/bootstrap-select.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/admin.css" type="text/css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/2d1a3b45ce.js" crossorigin="anonymous"></script>
        <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    	<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.14.0/jquery.validate.min.js"></script>
	
	<title>Manage Movies :: Admin | BOOKMYMOVIE.COM</title>
</head>
<body>
 
 	<c:set var="msg" value="${msg}"/>
 	<c:if test="${msg!=null}">
	 	<div class="alert alert-success alert-dismissible fade show" role="alert">
		  <strong>${msg}</strong>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	</c:if>
	<c:set var="errmsg" value="${errmsg}"/>
 	<c:if test="${errmsg!=null}">
	 	<div class="alert alert-danger alert-dismissible fade show" role="alert">
		  <strong>${errmsg}</strong>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	</c:if>
	
	
	<div class="admin-dashboard">
		<div class="container-fluid">
		<div class="admin-rg">
				<div class="row">
					<div class="col-md-2">
						<%@ include file ="admin-profile-2.jsp" %>
					</div>
					<div class="col-md-10">
                                            
                                            
                                                
                                                <nav class="navbar navbar-expand-lg navbar-dark bg-info">							
                                                    <a class="navbar-brand" href="/bookmymovie/admin/manage-movies">Manage Movies</a>
                                                    
                                                    <div class="collapse navbar-collapse">
                                                            <ul class="navbar-nav ml-auto">
                                                                    <li>
                                                                       <a href="/bookmymovie/admin" class="nav-link menu-link">GO BACK</a>
                                                                    </li>
                                                                    	
                                                            </ul>
                                                    </div>
                                                </nav>
                                            
                                                <br>
     
                                                <div class="d-flex">
                                                    
                                                    <div class="filter-bar">
                                                    
                                                        <form class="form-inline" method="GET" action="/bookmymovie/admin/searchMovie">
                                                            <div class="form-group mb-2">
                                                              <select name = "searchby" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                                                <option selected> Search By </option>
                                                                <option value="Movie Title">Movie Title</option>
                                                                <option value="Cast">Cast</option>
                                                                <!-- <option value="Year">Year</option> -->
                                                                <option value="Language">Language</option>
                                                                <option value="Category">Category</option>
                                                              </select>
                                                            </div>
                                                            <div class="form-group mx-sm-3 mb-2">
                                                              <label for="keyword" class="sr-only">Keyword</label>
                                                              <input type="text" class="form-control" name="keyword" id="keyword" placeholder="Keyword">
                                                            </div>
                                                            <button type="submit" class="btn btn-primary mb-2">Search</button>
                                                         </form>
                                                     </div>
                                                    <div class="ml-auto">
                                                        <button class="btn btn-warning" data-toggle="modal" data-target="#addMovieModal"><i class="far fa-plus-square"></i> &nbsp; New Movie</button>
                                                    </div>
                                                    
                                                </div>
                                                
                                                <div class="modal fade" id="addMovieModal" tabindex="-1" role="dialog" aria-labelledby="AddMovie" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                                      <div class="modal-content">
                                                          
                                                        <form action="/bookmymovie/admin/addMovie" method="POST">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title " id="modalLongTitle"><b>NEW MOVIE</b></h4>
                                                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                  </button>
                                                                </div>
                                                                <div class="modal-body">

                                                                     <div class="form-group row">
                                                                        <label for="movieTitle" class="col-sm-3 col-form-label">Title</label>
                                                                        <div class="col-sm-9">
                                                                          <input type="text" class="form-control" name="movieTitle" id="movieTitle" placeholder="Movie Title" required>
                                                                        </div>
                                                                     </div>


                                                                    <div class="form-group row">
                                                                        <label for="releaseDate" class="col-sm-3 col-form-label">Release Date</label>
                                                                        <div class="col-sm-9">
                                           									 <input  placeholder="Release Date" class="form-control" name="releaseDate" id="datepicker" required>                                
                                                                        </div>
                                                                        
                                                                    </div>
                                                                     <div class="form-group row">
                                                                        <label for="movieLength" class="col-sm-3 col-form-label">Movie Length</label>
                                                                        <div class="col-md-4 mb-3">
                                                                          <input type="number" class="form-control" name="hours" id="hours" placeholder="Hours">
                                                                        </div>
                                                                        <div class="col-md-4 mb-3">
                                                                          <input type="number" class="form-control" name="minutes" id="minutes" placeholder="Minutes" required>
                                                                        </div>
                                                                    </div>




                                                                      <div class="form-group row">  
                                                                        <label for="language" class="col-sm-3 col-form-label">Language</label>
                                                                        <div class="col-sm-9">
                                                                            <select id="language" name="language" class="selectpicker w-100" multiple data-live-search="true" required>
                                                                              <c:forEach var="lang" items="${language}">
															      				<option value="${lang.languageID}">${lang.languageName}</option>
															      			  </c:forEach>
                                                                            </select>
                                                                          </div>
                                                                      </div>
                                                                      
                                                             


                                                                      <div class="form-group row">  
                                                                        <label for="category" class="col-sm-3 col-form-label">Category</label>
                                                                        <div class="col-sm-9">
                                                                            <select id="category" name="category" class="selectpicker w-100" multiple data-live-search="true" required>  
                                                                              <c:forEach var="cat" items="${category}">
															      				<option value="${cat.categoryID}">${cat.categoryName}</option>
															      			  </c:forEach>
                                                                            </select>
                                                                          </div>
                                                                      </div>
                                                                      
                                                                      <div class="form-group row">  
                                                                        <label for="cast" class="col-sm-3 col-form-label">Cast</label>
                                                                        <div class="col-sm-9">
                                                                            <select id="cast" name="cast" class="selectpicker w-100" multiple data-live-search="true" required>  
                                                                              <c:forEach var="cast" items="${actor}">
															      				<option value="${cast.actorID}">${cast.actorName}</option>
															      			  </c:forEach>
                                                                            </select>
                                                                          </div>
                                                                      </div>

                                                                      <div class="form-group row">
                                                                        <label for="synopsis" class="col-sm-3 col-form-label">Synopsis</label>
                                                                         <div class="col-sm-9">
                                                                            <textarea class="form-control" name="synopsis" id="synopsis" rows="3" required></textarea>
                                                                         </div>
                                                                      </div>


                                                                     <div class="form-group row">
                                                                         <div class="col-sm-3">

                                                                         </div>
                                                                         <div class="col-sm-9">

                                                                         </div>
                                                                      </div>



                                                                   <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                         <button type="submit" class="btn btn-primary">Add Movie</button>
                                                                         <button type="reset" class="btn btn-danger">Reset</button>
                                                                  </div>


                                                                </div>
                                                        </form>
                                                         
                                                      </div>
                                                    </div>
                                                </div>
                                                
                                                <br>
                                                
                                                 <div class="scroll-movies">
                                            
                                                            <table class="table table-hover table-striped table-light">
                                                                <thead class="thead-dark">
                                                                  <tr>
                                                                        <th>TITLE</th>
                                                                        <th>RELEASE DATE</th>
                                                                        <th>LENGTH</th>
                                                                        <th>LANGUAGE</th>
                                                                        <th>CATEGORY</th>
                                                                        <th>CAST</th>
                                                                        <th>ACTION</th>
                                                                  </tr>
                                                                </thead>
                                                                <tbody>
                                                                	<c:forEach var="movie" items="${movie}">
                                                                		 <%
	                                                                        
	                                                                        	Movie m = (Movie) pageContext.getAttribute("movie");
	                                                                        	Set<Language> language = m.getLanguage();
	                                                                        	Set<Category> category = m.getCategory();
	                                                                        	Set<Actor> cast = m.getCast();
	                            
	                                                                        	Set<String> languageNames = new HashSet<String>();
	                                                                        	Set<String> categoryNames = new HashSet<String>();
	                                                                        	Set<String> actorNames = new HashSet<String>();
	                                                                        	Set<String> languageIds = new HashSet<String>();
	                                                                        	Set<String> catIds = new HashSet<String>();
	                                                                        	Set<String> castIds = new HashSet<String>();
	                                                                        	
	                                                                        	for(Language lang : language){
	                                                                        		
	                                                                        		languageNames.add(lang.getLanguageName());
	                                                                        		languageIds.add(String.format ("%d", lang.getLanguageID()));
	                                                                        	}
	                                                                        	
																				for(Category cat : category){
	                                                                        		
	                                                                        		categoryNames.add(cat.getCategoryName());
	                                                                        		catIds.add(String.format ("%d", cat.getCategoryID()));
	                                                                        	}
																				for(Actor act : cast){
	                                                                        		
	                                                                        		actorNames.add(act.getActorName());
	                                                                        		castIds.add(String.format ("%d", act.getActorID()));
	                                                                        	}
	                                                                        	
	                                                                        	
	                                                                        	
	                                                                        	String lang = String.join(", ", languageNames); 
	                                                                        	String cat = String.join(", ", categoryNames); 
	                                                                        	String cas = String.join(", ", actorNames); 
	                                                                        	String langids = String.join(",", languageIds); 
	                                                                        	String catids = String.join(",", catIds); 
	                                                                        	String castids = String.join(",", castIds); 
	                                                                        	
	                                                                        	
	                                                                        	
	                                                                        	pageContext.setAttribute("lang", lang);
	                                                                        	pageContext.setAttribute("cat", cat);
	                                                                        	pageContext.setAttribute("cas", cas);
	                                                                        	pageContext.setAttribute("langids", langids);
	                                                                        	pageContext.setAttribute("catids", catids);
	                                                                        	pageContext.setAttribute("castids", castids);
	                                                                        		
	                                                                        
	                                                                        
	                                                                        %>
	                                                                    <tr>
	                                                                    
	                                                                    
	                                                                        <td><c:out value=" ${movie.movieTitle}"/></td>
	                                                                        <td><c:out value=" ${movie.getRelDate()}"/></td>
	                                                                        <td><c:out value=" ${movie.movieLength}"/></td>
	                                                                        <td><c:out value=" ${lang}"/></td>
	                                                                        <td><c:out value=" ${cat}"/></td>
	                                                                        <td><c:out value=" ${cas}"/></td>
	                                                                        
		                                                                 
	                                                                       
	                                                                        <td> 
	                                                                        
	                                                                        	 
	                                                                        
	                                                                        
	                                                                        
	                                                                        	<!-- <button data-target="#viewMovieModal" data-toggle="modal" class="viewMovieModal btn btn-secondary"><i class="far fa-eye"></i></button> -->
	                                                                            <button data-toggle="modal" class="updateMovieModal btn btn-success" data-target="#updateMovieModal"><i class="far fa-edit"></i></button> 
	                                                                            <!-- <button data-toggle="modal" class="deleteMovieModal btn btn-danger" data-target="#deleteMovieModal"><i class="far fa-trash-alt"></i></button> --> 
	                                                                            <input type="hidden" id="id" value="${movie.movieID}"/>
	                                                                            <input type="hidden" id="lang" value="${langids}"/>
	                                                                             <input type="hidden" id="cat" value="${catids}"/>
	                                                                              <input type="hidden" id="cast" value="${castids}"/>
	                                                                              <input type="hidden" id="length" value="${movie.movieLength}"/>
	                                                                              
	                                                                        </td>
	                                                                        
	                                                                     </tr>
	                                                                 </c:forEach>   
                                                                </tbody>
                                                             </table>
                                                 </div>
                                                                        
                                                 <div id="deleteMovieModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="DeleteMovie" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                                <div class="modal-content">
                                                                    <form action="/bookmymovie/admin/deleteMovie" method="POST">
                                                                        <div class="modal-header">
                                                                                				
                                                                            <h3 class="modal-title"><i class="fas fa-exclamation-triangle"></i> &nbsp; Are you sure?</h3>	
                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                                <p>Do you really want to delete this Movie? This process cannot be undone.</p>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                                 <button type="submit" class="btn btn-danger" >Delete</button>
                                                                                  <input type="hidden" name="id" id="id"/>
                                                                        </div>
                                                                        
                                                                    </form>         
                                                                </div>
                                                        </div>
                                                </div>  
                                            
                                              <div class="updateMovieModal modal fade" id="updateMovieModal" tabindex="-1" role="dialog" aria-labelledby="UpdateMovie" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                                      <div class="modal-content">
                                                          
                                                        <form action="/bookmymovie/admin/updateMovie" method="GET" class="needs-validation" novalidate>
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title " id="modalLongTitle"><b>UPDATE MOVIE</b></h4>
                                                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                  </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                
                                                                	<div class="form-group row">
                                                                        <label for="movieID" class="col-sm-3 col-form-label">ID</label>
                                                                        <div class="col-sm-9">
                                                                          <input type="text" class="form-control" id="movieID" name="movieID" placeholder="Movie ID" readonly>
                                                                        </div>
                                                                       
                                                                      </div>

                                                                     <div class="form-group row">
                                                                        <label for="movieTitle" class="col-sm-3 col-form-label">Title</label>
                                                                        <div class="col-sm-9">
                                                                          <input type="text" class="form-control" name="movieTitle" id="movieTitle" placeholder="Movie Title">
                                                                        </div>
                                                                         <div class="invalid-feedback">
																		    Please provide a name.
																		  </div>
                                                                      </div>

                                                                     <div class="form-group row">
                                                                        <label for="releaseDate" class="col-sm-3 col-form-label">Release Date</label>
                                                                        <div class="col-sm-9">
                                           									 <input  placeholder="Release Date" class="form-control" name="releaseDate" id="releaseDate" required>                                
                                                                        </div>
                                                                        
                                                                    </div>

                                                                     <div class="form-group row">
                                                                        <label for="movieLength" class="col-sm-3 col-form-label">Movie Length</label>
                                                                        <div class="col-md-4 mb-3">
                                                                          <input type="number" class="form-control" name="hours" id="hours" placeholder="Hours" required>
                                                                        </div>
                                                                         <div class="invalid-feedback"> Please enter a valid email address </div>
                                                                        <div class="col-md-4 mb-3">
                                                                          <input type="number" class="form-control" name="minutes" id="minutes" placeholder="Minutes" required>
                                                                        </div>
                                                                      </div>

                                                                      <div class="form-group row">  
                                                                        <label for="language" class="col-sm-3 col-form-label">Language</label>
                                                                        <div class="col-sm-9">
                                                                            <select id="language" name="language" class="language selectpicker w-100" multiple data-live-search="true" required>
                                                                              <c:forEach var="lang" items="${language}">
															      				<option value="${lang.languageID}">${lang.languageName}</option>
															      			  </c:forEach>
                                                                            </select>
                                                                          </div>
                                                                      </div>


                                                                      <div class="form-group row">  
                                                                        <label for="category" class="col-sm-3 col-form-label">Category</label>
                                                                        <div class="col-sm-9">
                                                                            <select id="category" name="category" class="category selectpicker w-100" multiple data-live-search="true" required>
                                                                               <c:forEach var="cat" items="${category}">
															      				<option value="${cat.categoryID}">${cat.categoryName}</option>
															      			  </c:forEach>
                                                                            </select>
                                                                          </div>
                                                                      </div>
                                                                      
                                                                      <div class="form-group row">  
                                                                        <label for="cast" class="col-sm-3 col-form-label">Cast</label>
                                                                        <div class="col-sm-9">
                                                                            <select id="cast" name="cast" class="cast selectpicker w-100" multiple data-live-search="true" required>  
                                                                              <c:forEach var="cast" items="${actor}">
															      				<option value="${cast.actorID}">${cast.actorName}</option>
															      			  </c:forEach>
                                                                            </select>
                                                                          </div>
                                                                      </div>

                                                                      <div class="form-group row">
                                                                        <label for="synopsis" class="col-sm-3 col-form-label">Synopsis</label>
                                                                         <div class="col-sm-9">
                                                                            <textarea name="synopsis" class="form-control" id="synopsis" rows="3"></textarea>
                                                                         </div>
                                                                      </div>


                                                                     <div class="form-group row">
                                                                         <div class="col-sm-3">

                                                                         </div>
                                                                         <div class="col-sm-9">

                                                                         </div>
                                                                      </div>



                                                                   <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                        <button type="submit" class="btn btn-primary">Update Movie</button>
                                                                  </div>


                                                                </div>
                                                        </form>
                                                         
                                                      </div>
                                                    </div>
                                                </div>
                                                                        
                                                                        
                                               <div class="modal fade" id="viewMovieModal" tabindex="-1" role="dialog" aria-labelledby="ViewMovie" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                                      <div class="modal-content">
                                                          
                                                        <form action="/bookmymovie/admin/viewMovie" method="POST">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title " id="modalLongTitle"><b>MOVIE DETAILS</b></h4>
                                                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                  </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                
                                                                	<div class="form-group row">
                                                                        <label for="movieTitle" class="col-sm-3 col-form-label">Title</label>
                                                                        <div class="col-sm-9">
                                                                          <input type="text" class="form-control" id="movieTitle" placeholder="Movie Title" disabled>
                                                                        </div>
                                                                      </div>


                                                                    <div class="form-group row">
                                                                        <label for="releaseDate" class="col-sm-3 col-form-label">Release Date</label>
                                                                        <div class="input-group date col-sm-9" id="datepicker" data-date-format="mm-dd-yyyy">
                                                                            <input type="text" placeholder="Release Date" class="form-control" id="releaseDate" disabled> 
                                                                        </div>
                                                                    </div>
                                                                    
                                                                    <div class="form-group row">
                                                                        <label for="movieLength" class="col-sm-3 col-form-label">Length</label>
                                                                        <div class="col-sm-9">
                                                                          <input type="text" class="form-control" id="movieLength" placeholder="Movie Length" disabled>
                                                                        </div>
                                                                      </div>
                                                                      
                                                                      <div class="form-group row">
                                                                        <label for="synopsis" class="col-sm-3 col-form-label">Synopsis</label>
                                                                         <div class="col-sm-9">
                                                                            <textarea class="form-control" id="synopsis" rows="3" disabled></textarea>
                                                                         </div>
                                                                      </div>
																



                                                                   <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                        <input type="hidden" name="id" id="id"/>
                               
                                                                  </div>


                                                                </div>
                                                        </form>
                                                         
                                                      </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="d-flex">
                                                
	                                                <div class="mx-md-3">
	                                                        <button class="btn btn-warning" data-toggle="modal" data-target="#addActorModal"><i class="far fa-plus-square"></i> &nbsp; New Actor</button>
	                                                </div>
	                                                
	                                                <div class="mx-md-3">
	                                                        <button class="btn btn-warning" data-toggle="modal" data-target="#addCategoryModal"><i class="far fa-plus-square"></i> &nbsp; New Category</button>
	                                                </div>
	                                                
	                                                <div class="mx-md-3">
	                                                        <button class="btn btn-warning" data-toggle="modal" data-target="#addLanguageModal"><i class="far fa-plus-square"></i> &nbsp; New Language</button>
	                                                </div>
                                                
                                                </div>
                                                
                                                 <div class="modal fade" id="addActorModal" tabindex="-1" role="dialog" aria-labelledby="AddActor" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                                      <div class="modal-content">
                                                          
                                                        <form action="/bookmymovie/admin/addActor" method="POST">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title " id="modalLongTitle"><b>ADD ACTOR</b></h4>
                                                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                  </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                
                                                               
																	<div class="form-group row">
                                                                        <label for="actorName" class="col-sm-3 col-form-label">Name</label>
                                                                        <div class="col-sm-9">
                                                                          <input type="text" class="form-control" name="actorName" id="actorName" placeholder="Actor Name" required>
                                                                        </div>
                                                                     </div>

                                                                   <div class="modal-footer">
                                                                   
                                                                   		<button type="submit" class="btn btn-primary">Add Actor</button>
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                  </div>
                                                               </div>
                                                        </form>
                                                         
                                                      </div>
                                                    </div>
                                                </div>
                                                
                                                 <div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="AddCategory" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                                      <div class="modal-content">
                                                          
                                                        <form action="/bookmymovie/admin/addCategory" method="POST">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title " id="modalLongTitle"><b>ADD CATEGORY</b></h4>
                                                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                  </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                
                                                               
																	<div class="form-group row">
                                                                        <label for="categoryName" class="col-sm-3 col-form-label">Name</label>
                                                                        <div class="col-sm-9">
                                                                          <input type="text" class="form-control" name="categoryName" id="categoryName" placeholder="Category Name" required>
                                                                        </div>
                                                                     </div>

                                                                   <div class="modal-footer">
                                                                   
                                                                   		<button type="submit" class="btn btn-primary">Add Category</button>
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                  </div>
                                                               </div>
                                                        </form>
                                                         
                                                      </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="modal fade" id="addLanguageModal" tabindex="-1" role="dialog" aria-labelledby="AddLanguage" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                                      <div class="modal-content">
                                                          
                                                        <form action="/bookmymovie/admin/addLanguage" method="POST">
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title " id="modalLongTitle"><b>ADD LANGUAGE</b></h4>
                                                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                  </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                
                                                               
																	<div class="form-group row">
                                                                        <label for="languageName" class="col-sm-3 col-form-label">Name</label>
                                                                        <div class="col-sm-9">
                                                                          <input type="text" class="form-control" name="languageName" id="languageName" placeholder="Language Name" required>
                                                                        </div>
                                                                     </div>

                                                                   <div class="modal-footer">
                                                                   
                                                                   		<button type="submit" class="btn btn-primary">Add Language</button>
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                  </div>
                                                               </div>
                                                        </form>
                                                         
                                                      </div>
                                                    </div>
                                                </div>
                                                
                                                                        
                                                
					</div>						
				</div>
			</div>
		</div>
	</div>
	
	<!-- My JavaScript -->
	<script src="<%=request.getContextPath()%>/resources/js/admin.js"></script>
	<!-- /My JavaScript -->
	
	

  </body>
</html>
