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
  
    <title>Ticket Details :: BookMyMovie.com</title>
</head>
<body>
				
    <%@ include file ="header.jsp" %>
    
    <br><br>
    
    <div class="container">
		<div class="row justify-content-center">
			<div class="col-md-9 ticket">
				<h1 style="text-align:center;">Your Ticket Details</h1>
				<br>
				<div style="text-align:center;" class="row">
					<div>
						<img src="<%=request.getContextPath()%>/resources/img/movies/qr.png" class="img-thumbnail" width="50%">
						<br>
						<br>
						<h3>${booking.getShow().getMovie().movieTitle}</h3>
						<h5>${booking.getShow().getTheatre().theatreName}</h5>
						<h5>Show Date : ${booking.getShow().showDate} </h5>
						<h5>Show Time : ${booking.getShow().showTime} </h5>
						<h5>Show Screen : ${booking.getShow().screenNumber} </h5>
						<h5>Seats : ${seats} </h5>
						<br>
						<h5>Booking ID : ${booking.bookingId}  </h5>
						<h5>Booking Amount : &#8377 ${booking.total}  </h5>
						<h5>Booking Date : ${booking.bookDate} </h5>
						<br>
						<button class=" btn btn-secondary" onclick="window.print()">Print</button>
					</div>
				</div>	
			</div>
		</div>
	</div>
</body>
</html>