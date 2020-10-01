<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.ZoneId"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="com.me.bookmymovie.pojo.Show"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/3.1.3/css/bootstrap-datepicker.min.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.8.0/parsley.min.js"></script> -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/3.1.3/js/bootstrap-datepicker.min.js"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
	<link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/css/bootstrap-select.min.css"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/js/bootstrap-select.min.js"></script>
	<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    	<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
  
        <title>Search :: BookMyMovie.com</title>
</head>
<body>
				
    <%@ include file ="header.jsp" %>

	<c:set var="theatre" value="${theatre}"/>
	
		<br><br>
     
		<div class="container-fluid">
    
    		<div class = "text-center">
    
		    	 <h2> ${movie} </h2>
		    
		   		 <h3> ${theatre.theatreName} </h3>
		   		 
		   		
		    	 <p>${theatre.theatreCity}</p> 
		    	 <p>${theatre.theatreAddress}</p> 
    	 
    	 
    		</div>
   		<br>

	 		<table class="table table-hover table-striped table-light">
         		<thead class="thead-dark">
           			<tr>
                 		<th>SHOW DATE</th>
                 		<th>SHOW TIME</th>
                 		<th>ACTION</th>     
           			</tr>
         		</thead>
         		<tbody>
         			<c:forEach var="result" items="${result}">	
         			
         				<%	
         				
	         				Show sh = (Show) pageContext.getAttribute("result");
	         				
	         				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  					  	Date dbook = sh.getShowDate();
	  					  	Date date = new Date();
	  					  	String tdate = sdf.format(date);
	  					  	Date today = sdf.parse(tdate);
	         				SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
	  					  	String t = parser.format(date);
	  					  	Date time = parser.parse(t);
	  					  	Date dtime = sh.getShowTime();
	  					  	
	  					  	boolean show = true;
	  					  	
	  					  	if(dtime.compareTo(time) < 0 && dbook.compareTo(today) == 0){
	  					  		show = false;
	  					  	}
	  					  	
	  					  	System.out.println(show);
	  					  	pageContext.setAttribute("show", show);
         				%>
         			
         				<c:if test="${show == true}">
         					

         					
		        			<tr>
		        				<td><c:out value=" ${result.showDate}"/></td>
		                  		<td><c:out value=" ${result.showTime}"/></td>
		                  		<td> 
			                  		<form action="/bookmymovie/bookseats" method="GET">
			                  			
			                  			<button type="submit" class="btn btn-warning">Book Seats</button>
			                  			<input type="hidden" value="${theatre.theatreId}" name="theatreID" />
			                  			<input type="hidden" value="${result.showID}" name="showID" />
			                  			
			                  		</form>
		                  		</td>
		              		 </tr>
	              		 
   						</c:if>	
       				 </c:forEach>   
       			 </tbody>
  			  </table>
		  </div>
      
      
      <!-- My JavaScript -->
	 <script src="<%=request.getContextPath()%>/resources/js/theatre.js"></script>
	 <!-- /My JavaScript -->
                
    
    
    </body>
</html>