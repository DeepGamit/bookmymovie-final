<%-- 
    Document   : manage-movies
    Created on : Mar 28, 2020, 1:10:32 PM
    Author     : deepgamit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<script src="https://kit.fontawesome.com/2d1a3b45ce.js" crossorigin="anonymous"></script>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
  	<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
  	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
	
	<title>Add Show :: Admin | BOOKMYMOVIE.COM</title>
	<script type="text/javascript">
	
		function process (date){
			var parts = date.split("/");
			return new Date(parts[2], parts[0] - 1, parts[1]);
		}
	
	
		function validate(){
			
			var flag1 = 0;
			var flag2 = 0;
			var flag3 = 0;
			var flag4 = 0;
			var flag = 0;
			var inputDate = document.getElementById("datetimepicker").value;
		
			var x = inputDate.split(" ");
			var date = new Date(x[1]);
			var y = x[1].split("/");
			var z = x[0].split(":");
			
			var inpdate = new Date(y[2], y[0]-1, y[1], z[0], z[1]);
			var currDate = new Date();
			
			if(currDate.getTime() > inpdate.getTime()){
				
				document.getElementById("errorMsgDate").style.display="block";
				document.getElementById("datetimepicker").value = "";
				flag = 1;
				
			} else {
				
				document.getElementById("errorMsgDate").style.display="none";
				flag = 0;
			} 
			
			if(document.getElementById("seatPrice").value < 50){
				
				document.getElementById("errorMsgSeatPrice").style.display="block";
				document.getElementById("seatPrice").value = "";
				flag1 = 1;
				
			} else {
				
				document.getElementById("errorMsgSeatPrice").style.display="none";
				flag1 = 0;
			}
			
			if(document.getElementById("numberOfRows").value<5 || document.getElementById("numberOfRows").value>12 ){
				
				document.getElementById("errorMsgNor").style.display="block";
				document.getElementById("numberOfRows").value = "";
				flag2 = 1;
				
			} else {
				
				document.getElementById("errorMsgNor").style.display="none";
				flag2 = 0;
				
			}
			if(document.getElementById("numberOfSeats").value<5 || document.getElementById("numberOfSeats").value>12 ){
				
				document.getElementById("errorMsgNos").style.display="block";
				document.getElementById("numberOfSeats").value = "";
				flag3 = 1;
				
			} else {
				
				document.getElementById("errorMsgNos").style.display="none";
				flag3 = 0;
			}
			
			if(document.getElementById("screenNumber").value<1){
				
				document.getElementById("errorMsgScreen").style.display="block";
				document.getElementById("screenNumber").value = "";
				flag4 = 1;
				
			} else {
				
				document.getElementById("errorMsgScreen").style.display="none";
				flag4 = 0;
			}
			
			if(flag || flag1 || flag2 || flag3 || flag4){
				
				return false;
				
			} else {
				
				return true;
			}
		}
		
	</script>
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
                            <a class="navbar-brand" href="#">Add Show</a>
                            <div class="collapse navbar-collapse">
                               <ul class="navbar-nav ml-auto">
                                   <li>
                                      <a href="/bookmymovie/theatre" class="nav-link menu-link">GO BACK</a>
                                   </li>   	
                               </ul>
                            </div>
                        </nav>
                                              
                        <br>
                    	<h3> Enter Show Details</h3>
                    	<br>
                                                
                        <form action="/bookmymovie/theatre/addShow" method="POST" onsubmit="return validate()">
               
                      		<div class="form-group row">  
                           		<label for="movie" class="col-sm-2 col-form-label">Movie</label>
                               	<div class="col-sm-7">
                                	<select id="movie" name="movie" class="selectpicker w-100" data-live-search="true" required>
	                                    <c:forEach var="movie" items="${movie}">
											<option value="${movie.movieID}">${movie.movieTitle}</option>
			  							</c:forEach>
                                    </select>
                                 </div>
                            </div>
                                         
                        	<div class="form-group row">
                             	<label for="showDateTime" class="col-sm-2 col-form-label">Show Date-Time</label>
                                <div class="col-sm-7">
   									 <input  placeholder="Show Date" class="form-control" name="showDateTime" id="datetimepicker" required>                                
                                </div>
                            </div>
                             
                           	<div class="form-group row">
                             	<div class="col-sm-2"></div>
                                <div id="errorMsgDate" class="col-sm-6" style="display:none;color:red">
									Show Date cannot be in the past.
							    </div>
							</div>
                                                         
                            <div class="form-group row">
                            <label for="seatPrice" class="col-sm-2 col-form-label">Seat Price</label>
                               <div class="col-sm-7">
                                 <input type="number" class="form-control" name="seatPrice" id="seatPrice" placeholder="Seat Price" required>
                               </div>
                            </div>
                            
                            <div class="form-group row">
                            	<div class="col-sm-2"></div>
                                <div id="errorMsgSeatPrice" class="col-sm-6" style="display:none;color:red">
									Seat Price should be greater than or equal to 50.
								</div>
							</div>
                                             
                            <div class="form-group row">
                            <label for="numberOfRows" class="col-sm-2 col-form-label">Number of Rows</label>
                            	<div class="col-sm-7">
                                 	<input type="number" class="form-control" name="numberOfRows" id="numberOfRows" placeholder="Number of Rows" required>
                               </div>
                            </div>
                                             
                            <div class="form-group row">
                            	<div class="col-sm-2"></div>
                               	<div id="errorMsgNor" class="col-sm-6" style="display:none;color:red">
									Number of Rows should be between 5 and 12 .
								</div>
							</div>
                                             
                            <div class="form-group row">
                            	<label for="numberOfSeats" class="col-sm-2 col-form-label">Number of Seats/Row</label>
                             	<div class="col-sm-7">
                               		<input type="number" class="form-control" name="numberOfSeats" id="numberOfSeats" placeholder="Number of Seats/Row" required>
                             	</div>
                          	</div>
                                             
	                        <div class="form-group row">
	                        	<div class="col-sm-2"></div>
	                            <div id="errorMsgNos" class="col-sm-6" style="display:none;color:red">
									Number of Seats should be between 5 and 12 .
								</div>
							</div>
                                             
                            <div class="form-group row">
                            <label for="screenNumber" class="col-sm-2 col-form-label">Screen Number</label>
                               <div class="col-sm-7">
                                 <input type="number" class="form-control" name="screenNumber" id="screenNumber" placeholder="Screen Number" required>
                               </div>
                            </div>
                                             
                                             
                            <div class="form-group row">
                           		<div class="col-sm-2"></div>
                              	<div id="errorMsgScreen" class="col-sm-6" style="display:none;color:red">
									Screen Number should be greater than or equal to 1.
								</div>
							</div>
                                             
                            <div class="form-group row">
                              <div class="col-sm-2"></div>
                              <div class="col-sm-7">
							  	<button type="submit" class="btn btn-primary">Add Show</button>
                              </div>
                            </div>
         
                    	</form>
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
