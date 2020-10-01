<%-- 
    Document   : admin
    Created on : Mar 27, 2020, 8:35:38 PM
    Author     : deepgamit
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
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
  
        <title>Dashboard :: Admin | BOOKMYMOVIE.COM</title>
        
    </head>
    
    <body>

    	<div class="admin-dashboard">
			<div class="container-fluid">
				<div class="admin-rg">
					<div class="row">
                    	<div class="col-md-3">
							<%@ include file ="admin-profile.jsp" %>
						</div>
				
						<div class="col-md-9 admin-box">
							<div class="row">
								<div class="col-md-6">
									<a href="/bookmymovie/admin/manage-users">
										<div class="option-box">
											<div class="option-box-img">
												<img src="<%=request.getContextPath()%>/resources/img/admin/manage-user-1.png" width="100%">
											</div>
											<h5>MANAGE USERS</h5>
										</div>
									</a>
								</div>
								<div class="col-md-6">
									<a href="/bookmymovie/admin/manage-movies">
										<div class="option-box">
											<div class="option-box-img">
												<img src="<%=request.getContextPath()%>/resources/img/admin/manage-movies.png" width="100%">
											</div>
											<h5>MANAGE MOVIES</h5>
										</div>
									</a>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<a href="/bookmymovie/admin/manage-theatres">
										<div class="option-box">
											<div class="option-box-img">
												<img src="<%=request.getContextPath()%>/resources/img/admin/manage-cinemas.png" width="100%">
											</div>
											<h5>MANAGE THEATRES</h5>
										</div>
									</a>
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
