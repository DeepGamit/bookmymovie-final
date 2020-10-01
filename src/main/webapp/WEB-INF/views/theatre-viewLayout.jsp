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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/seats.css" type="text/css">
	<script src="https://kit.fontawesome.com/2d1a3b45ce.js" crossorigin="anonymous"></script>
	<link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
  
        <title>Dashboard :: Theatre | BOOKMYMOVIE.COM</title>
        
    </head>
    <body>

        <div class="admin-dashboard">
			<div class="container-fluid">
				<div class="admin-rg">
					<div class="row">
						<c:set var="index" value="0"/>
						
						<div class="col-md-2">
							<%@ include file ="theatre-profile-2.jsp" %>
						</div>
					
						<div class="col-md-10">
							<nav class="navbar navbar-expand-lg navbar-dark bg-info">							
                             	<a class="navbar-brand" href="/bookmymovie/theatre">Show Layout</a>
                                  
                                  <div class="collapse navbar-collapse">
                                  	<ul class="navbar-nav ml-auto">
                                        <li>
                                           <a href="/bookmymovie/theatre" class="nav-link menu-link">GO BACK</a>
                                        </li>        	
							  	 	</ul>
                                  </div>
                            </nav>
                                            
                             <br>  
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
								
								<div class="container mx-auto">
										<div class="col-md-12 sc">
											<h1>SCREEN</h1><br>
											<h5>${show.getMovie().movieTitle}</h5>
											<h5>${show.getShowDate()} | ${show.getShowTime()}</h5>
											<h5>Screen - ${show.screenNumber}</h5>
											<h5>&#8377 ${show.showPrice}</h5>	
										</div>
										<div class="row justify-content-center">
												<div class="col-md-8 screen">
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
														  </div>
														</section>	
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
        
       
    <!-- Optional JavaScript -->
    <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        
        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        
    </body>
</html>
