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
	
	<title>Edit Account :: Theatre | BOOKMYMOVIE.COM</title>
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
                        	<a class="navbar-brand" href="#">Account Details</a>                    
                            <div class="collapse navbar-collapse">
                                <ul class="navbar-nav ml-auto">
                                    <li>
                                       <a href="/bookmymovie/theatre" class="nav-link menu-link">GO BACK</a>
                                    </li>      	
                                </ul>
                           	</div>
                       	</nav>
                                                
                        <br>
                        <h3> Update Account Details</h3>
                        <br>
                                                
                        <form:form modelAttribute="user" action="/bookmymovie/theatre/editAccountDetails" method="post">

	                        <div class="form-group row">
	                           <label for="userEmail" class="col-sm-2 col-form-label">Email Id</label>
	                           <div class="col-sm-7">
	                           		<form:input path="userEmail" cssClass="form-control" value="${theatre.getUser().getUserEmail()}" name="userEmail" placeholder="Email ID" readonly="true"/>
	                           		<form:errors path="userEmail" cssClass="error"/>
	                           </div>
	                        </div>
                                      
                            <div class="form-group row">
                               <label for="userName" class="col-sm-2 col-form-label">User Name</label>
                               <div class="col-sm-7">
                                 	<form:input path="userName" cssClass="form-control" value="${theatre.getUser().getUserName()}" name="userName"  placeholder="User Name" />
                               		<form:errors path="userName" cssClass="error"/>
                               </div>
                            </div>
                            
                            <div class="form-group row">
                               <label for="password" class="col-sm-2 col-form-label">Password</label>
                               <div class="col-sm-7">
                                 	<form:password path="userPwd" cssClass="form-control" name="userPwd" placeholder="Password" />
                                 	<form:errors path="userPwd" cssClass="error"/>
                               </div> 
                            </div>
                                      
                            <div class="form-group row">
                            	<label for="password" class="col-sm-2 col-form-label">Password</label>
                              	<div class="col-sm-7">
                                	<input type="password" class="form-control" name="confirmPwd" placeholder="Confirm Password" htmlEscape="true" />
                                	<form:errors cssClass="error"/>
                                </div> 
                             </div>
                                            
                                 
                           	 <div class="form-group row">
                             	<div class="col-sm-2"></div>
                                <div class="col-sm-7">
									<button type="submit" class="btn btn-primary">Update Details</button>
                                 </div>
                             </div>

                        </form:form>
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
