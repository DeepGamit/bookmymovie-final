<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.8.0/parsley.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/2d1a3b45ce.js" crossorigin="anonymous"></script>
	<link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/css/bootstrap-select.min.css"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/js/bootstrap-select.min.js"></script>
  
    <title>Search :: BookMyMovie.com</title>
    
</head>
<body>
				
    <%@ include file ="header.jsp" %>
    <br> 
    <br>
    <c:set var="movie" value="${movie}"/>

	<div class="container-fluid">
		<div class="text-center">
			<h3> ${movie.movieTitle} </h3>
			<c:forEach var="language" items="${movie.getLanguage()}">
   		 		<span> ${language.languageName}</span>
    	 	</c:forEach>
    	 	<p>${movie.relDate} | ${movie.movieLength}</p>
   	 	</div>
    	<br>
    	
    	<table class="table table-hover table-striped table-light">
        	<thead class="thead-dark">
           		<tr>
                	<th>THEATRE NAME</th>
                 	<th>THEATRE ADDRESS</th>
                 	<th>ACTION</th>
         		</tr>
         	</thead>
         	<tbody>
         		<c:forEach var="result" items="${result}">
              		<tr>
              			<td><c:out value=" ${result.theatreName}"/></td>
                   		<td><c:out value=" ${result.theatreAddress}"/></td>
                  		<td> 
	                  		<form action="/bookmymovie/search/viewShows/${result.theatreName}" method="GET">
	                  			<input type="hidden" value="${movie.movieTitle}" name="movieTitle" />
	                  			<input type="hidden" value="${result.theatreId}" name="theatre" />
	                  			<button type="submit" class="btn btn-warning">View Show</button>
	                  		</form>			
                  		</td> 
          			</tr>
       	 		</c:forEach>   
         	</tbody>
      	</table>
   	</div>
</body>
</html>