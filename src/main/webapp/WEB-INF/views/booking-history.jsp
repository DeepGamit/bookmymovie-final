<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="com.me.bookmymovie.pojo.Booking"%>
<%@ page import="com.me.bookmymovie.pojo.Seat"%>
<%@ page import="com.me.bookmymovie.dao.SeatDAO"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.time.ZoneId"%>

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
  
    <title>Booking History :: BookMyMovie.com</title>
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
	
	<br><br><br>
	
	<div class="container">
	<h3 style="text-align:center;">BOOKING HISTORY</h3>
		<div class="row justify-content-center">
			<c:forEach var="booking" items="${bookings}">
		
				<%
				
					  Map<Long, List<Seat>> map = (Map<Long, List<Seat>>) request.getAttribute("map");
					  Booking booking = (Booking) pageContext.getAttribute("booking");
					  Long id = booking.getBookingId();
					  List<Seat> seats = (List<Seat>) map.get(id);
					  
					  String seatNumber="";
					  
					  for(Seat s : seats){
						  
						  seatNumber = seatNumber + s.getSeatNumber() + " ";
						  
					  }
					  
					  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					  Date dbook = booking.getShow().getShowDate();
					  Date dtime = booking.getShow().getShowTime();
					  Date date = new Date();
					  String tdate = sdf.format(date);
					  Date today = sdf.parse(tdate);
					  SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
					  String t = parser.format(date);
					  Date time = parser.parse(t);
					 
					  
					  boolean cancel = true;
					  
					  if(dbook.compareTo(today) >= 0 && dtime.compareTo(time) > 0) {
						  
				           cancel = true;
				           
				        } else if (dbook.compareTo(today) < 0) {
				        	
				           cancel = false;
				           
				        } else if (dbook.compareTo(today) >= 0 && dtime.compareTo(time) < 0) {
				        	
					       cancel = false;
					           
					    } else {
				        	
				        	cancel = true;
				        	
				       }
					  
					  pageContext.setAttribute("seat", seatNumber);
					  pageContext.setAttribute("cancel", cancel);

				%>
				<div class="col-md-6 combo-contain history-box">
				
							<h4>${booking.getShow().getMovie().movieTitle}</h4>
							<br>
							<p><b>Multiplex :</b> ${booking.getShow().getTheatre().theatreName}</p>
							<p><b>Seats :</b> ${seat}</p>
							<p><b>Show Date :</b> ${booking.getShow().showDate}</p>
							<p><b>Show Time :</b> ${booking.getShow().showTime}</p>
							<p><b>Show Screen :</b> ${booking.getShow().screenNumber}</p>
							<p><b>Booking ID :</b> ${booking.bookingId}</p>
							<p><b>Amount :</b> &#8377 ${booking.total}</p>
							<p><b>Booking Date :</b> ${booking.bookDate}</p>

							<c:if test = "${booking.bookingStatus == 'Active' && cancel}">
								<button data-toggle="modal" class="cancelBookingModal btn btn-danger" data-target="#cancelBookingModal"><i class="fa fa-ban"></i> Cancel </button>	
							</c:if>
							
							<c:if test = "${booking.bookingStatus == 'Active' && !cancel}">
								<button class="btn btn-secondary"><i class="fa fa-check"> Completed </i></button>	
							</c:if>
							
							
							<c:if test = "${booking.bookingStatus == 'Inactive'}">
								<button class="btn btn-secondary"><i class="fa fa-times"> Cancelled </i></button>
							</c:if>
							
							<input type="hidden" id="id" value="${booking.bookingId}"/>
		
				</div>
			</c:forEach>
		</div>
	</div>
	
	
	<div id="cancelBookingModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="CancelBooking" aria-hidden="true">
    	<div class="modal-dialog" role="document">
        	<div class="modal-content">
	            <form action="/bookmymovie/booking/cancel" method="POST">
	                <div class="modal-header">
	                        				
	                    <h3 class="modal-title"><i class="fas fa-exclamation-triangle"></i> &nbsp; Are you sure?</h3>	
	                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                </div>
	                <div class="modal-body">
	                        <p>Do you really want to Cancel this Booking? This process cannot be undone.</p>
	                </div>
	                <div class="modal-footer">
	                        <button type="button" class="btn btn-secondary" data-dismiss="modal">NO</button>
	                        <button type="submit" class="btn btn-danger">YES</button>
	                        <input type="hidden" name="id" id="id"/>
	                </div>
	                
	            </form>         
       		</div>
         </div>
	</div>  
                           	
	<br>
	<br>
	<br>			
    
   <!-- My JavaScript -->
   <script src="<%=request.getContextPath()%>/resources/js/user.js"></script>
    
</body>
</html>