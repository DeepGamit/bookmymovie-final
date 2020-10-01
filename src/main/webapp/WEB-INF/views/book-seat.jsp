<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*"%>
<%@ page import="com.me.bookmymovie.pojo.Seat"%>
<%@ page import="com.me.bookmymovie.dao.SeatDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/seats.css" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.8.0/parsley.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/2d1a3b45ce.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
	<link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/css/bootstrap-select.min.css"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/js/bootstrap-select.min.js"></script>
  
        <title>Book Seat :: BookMyMovie.com</title>
</head>
<body>
			
    <%@ include file ="header.jsp" %>
    
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
    

	<c:set var="index" value="0"/>
	<c:set var="price" value="${seats[0].seatPrice}"/>
   			
   			
	<%
		List<Seat> fseats = (List<Seat>) request.getAttribute("seats");
		
		Comparator sortingByNumber = new Comparator<Seat>() {
		   public int compare(Seat s1, Seat s2) {
			return Integer.parseInt(s1.getSeatId()+"") - Integer.parseInt(s2.getSeatId()+"");
		   }
		};

		fseats.sort(sortingByNumber);
		pageContext.setAttribute("fseats", fseats);
	%>
	
	<br>
	<br>
	
   	<form action="/bookmymovie/bookingDetails" method="GET">
		<div class="container">
			<div class="sc">
				<h1>SCREEN</h1><br>
				<h5>${show.getMovie().movieTitle}</h5>
				<h5>${show.getShowDate()} | ${show.getShowTime()}</h5>
				<h5>&#8377 ${show.showPrice}</h5>
					
			</div>
			<div class="row mx-auto justify-content-center">
				<div class="col-sm-2">
					<div class="cust-rows">
						<c:forEach begin="1" end="${show.numberOfRows}" varStatus="loop">
	 		 				<ul>
								<li>ROW ${loop.index} </li>	
							</ul>
	  	 				</c:forEach>	
					</div>
				</div>
				
				<div class="col-sm-8 screen">		
					<section id="seats">			
				 		<div class="row justify-content-center">	  																													 									 
							<c:forEach begin="1" end="${show.numberOfRows}" varStatus="loop">
											
								<div class="col-md-12">
									<c:forEach begin="1" end="${show.numberOfSeat}" varStatus="innerloop">
	
										<c:if test="${fseats[index].getBooking().getBookingId() == null}">
											<input id="${fseats[index].seatId}" class="seat-select" type="checkbox" value="${fseats[index].seatId}" name="seat">
											<label for="${fseats[index].seatId}" class="seat">${fseats[index].seatNumber}</label>											  										
										</c:if>
										<c:if test="${fseats[index].getBooking().getBookingId() != null}">
											<input id="${fseats[index].seatId}" class="seat-select" disabled type="checkbox" value="${fseats[index].seatId}" name="seat">
											<label for="${fseats[index].seatId}" class="seat">${fseats[index].seatNumber}</label>	
										</c:if>
			
										<c:set var="index" value="${index+1}"/>
										
									</c:forEach>
								</div>																																														
											
							</c:forEach>
					 		<br><br><br>
							<div class="col-sm-4">
							  	<div class="form-group">
									<input type="submit" value="BOOK" class="btn btn-primary">
									<input type="hidden" value="${theatre.theatreId}" name="theatreID" />
		               				<input type="hidden" value="${show.showID}" name="showID" />
		               				<input type="hidden" value="${price}" name="price" />
								</div>
							  </div>
						  	</div>
						</section>	
					</div>	
				</div>
			</div>
		</form>
    </body>
</html>