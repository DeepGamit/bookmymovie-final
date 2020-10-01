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
  
    <title>Booking Details :: BookMyMovie.com</title>
</head>
<body>
				
    <%@ include file ="header.jsp" %>
    <br><br>
    <div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8 combo-contain">
				<h3 style="text-align:center; color:green">Confirm Your Booking Details</h3>
				
				<br><br>
				
				<h4>Movie: ${show.getMovie().movieTitle} </h4>
				<br>
				<h5>Multiplex: ${show.getTheatre().theatreName} </h5>
				<h5>Screen Number: ${show.screenNumber}  </h5>
				<h5>Seats: ${seats}  </h5>
				<h5>Number of seats: ${count} </h5>
				<h5>Date: ${show.showDate} </h5>
				<h5>Time: ${show.showTime} </h5>
				<h5>Total Payable: &#8377 ${total} </h5>
				
				<br><br>
				
				<c:set var="user" value="${sessionScope.user}"/>
				<c:if test="${user!=null}">
				
					  <form action="/bookmymovie/makePayment" method="GET">
			     	  	<input type="submit" value="CONFIRM BOOKING" class="btn btn-info">
						<input type="hidden" value="${show.showID}" name="show" />
						<input type="hidden" value="${total}" name="amount" />
						<input type="hidden" value="${seat}" name="seat" />		
				       </form>		
					   <br>
					   <form action="/bookmymovie" method="GET">
					   	<input type="submit" value="CANCEL BOOKING" class="btn btn-danger">
					   </form>	
					   
				</c:if>
				<c:if test="${user==null}">
				
				     <form action="/bookmymovie/makePayment" method="GET">
			     		<input type="submit" value="CONFIRM BOOKING" class="btn btn-danger">
						<input type="hidden" value="${show.showID}" name="show" />
						<input type="hidden" value="${total}" name="amount" />
						<input type="hidden" value="${seat}" name="seat" />		
				    </form>	
				    <br>
				    <form action="/bookmymovie" method="GET">
				    	<input type="submit" value="CANCEL BOOKING" class="btn btn-danger">
				    </form>	
				    
				</c:if>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
    </body>
</html>