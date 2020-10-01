<%-- 
    Document   : admin
    Created on : Mar 27, 2020, 8:35:38 PM
    Author     : deepgamit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page import="com.me.bookmymovie.pojo.Booking"%>
<%@ page import="com.me.bookmymovie.pojo.Seat"%>
<%@ page import="com.me.bookmymovie.dao.SeatDAO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/admin.css" type="text/css">
		<script src="https://kit.fontawesome.com/2d1a3b45ce.js" crossorigin="anonymous"></script>
		<link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
  
    	<title>Dashboard :: Theatre | BOOKMYMOVIE.COM</title>
        
    </head>
    <body>
    	<div class="admin-dashboard">
			<div class="container-fluid">
				<div class="admin-rg">
					<div class="row">
					
						<div class="col-md-2">
							<%@ include file ="theatre-profile-2.jsp" %>
						</div>
						
						<div class="col-md-10">
							<nav class="navbar navbar-expand-lg navbar-dark bg-info">							
                            	<a class="navbar-brand" href="/bookmymovie/theatre">Manage Bookings</a>
                            	
                            	<div class="collapse navbar-collapse">
	                                <ul class="navbar-nav ml-auto">
	                                        <li>
	                                           <a href="/bookmymovie/theatre" class="nav-link menu-link">GO BACK</a>
	                                        </li>      	
	                                 </ul>
                                 </div>
                             </nav>
                             <br>
                             <div class="d-flex">
                             	<div class="filter-bar">
                             		<form class="form-inline" method="GET" action="/bookmymovie/theatre/viewBookings/filter">
                                    	<div class="form-group mb-2">
                                        	<select name="filter" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                           		<option value = "All" selected> All </option>
                                           		<option value="Active">Active</option>
                                           		<option value="Cancelled">Cancelled</option>
                                         	</select>
                                       	</div>
                                       	<button type="submit" class="btn btn-primary mb-2">Filter</button>
                                       	<input type="hidden" name="show" value="${show}"/>
                                    </form>
                                </div>
                              </div>
                              <br></br>
                              <div class="scroll-movies">
                              		<table class="table table-hover table-striped table-light">
                                    	<thead class="thead-dark">
                                    		<tr>
                                    			<th>BOOKING ID</th>
                                                <th>BOOKING DATE</th>
                                                <th>TOTAL AMOUNT</th>
                                                <th>ALL SEATS</th>
                                                <th>CUSTOMER</th>
                                                <th>BOOKING STATUS</th>
                                             </tr>
                                         </thead>
                                         <tbody>
                                               	<c:forEach var="booking" items="${bookings}">
	                                               	<%
		                                               	Booking booking = (Booking) pageContext.getAttribute("booking");
		                                               	List<Seat> seats = booking.getSeats();
		                                               	String seatNumber="";
		                                               	for(Seat s : seats){
		                                               		seatNumber = seatNumber + s.getSeatNumber() + "  ";
		                                               	}
		                                               	pageContext.setAttribute("seat", seatNumber);
	                                               	%>
	                                                <tr>
	                                                	<td><c:out value=" ${booking.bookingId}"/></td>
                                                        <td><c:out value=" ${booking.bookDate}"/></td>
                                                        <td><c:out value=" ${booking.total}"/></td>
                                                        <td><c:out value=" ${seat}"/></td>
                                                        <td><c:out value=" ${booking.getCustomer().getUser().userEmail}"/></td>
                                                        <td><c:out value=" ${booking.bookingStatus}"/></td>
                                                        
                                                     </tr>
                                                 </c:forEach>   
                                           </tbody>
                                        </table>
                                 	</div>
                            	</div>
							</div>
						</div>
					</div>
				</div>
				  
    <!-- My JavaScript -->
	<script src="<%=request.getContextPath()%>/resources/js/admin.js"></script>
	<!-- /My JavaScript -->
        
       
    <!-- Optional JavaScript -->
    <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        
        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        
    </body>
</html>
