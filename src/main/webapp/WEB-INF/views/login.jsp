<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.8.0/parsley.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/2d1a3b45ce.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
	<link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/css/bootstrap-select.min.css"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/js/bootstrap-select.min.js"></script>
  
    <title>Login :: BookMyMovie.com</title>
    
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
	
    <br> 
	<div class="container">
		<form method="post" id="Login-Form" role="form" action="loginProcess">
        	<div class="form-group">
            	<div class="input-group mb-3">
                 	<div class="input-group-prepend">
                 		<span class="input-group-text"><i class="fa fa-envelope"></i></span>
                 	</div>
                	<input name="login-email" id="login-email" type="text" class="form-control input-lg" placeholder="Enter Email" required>
                </div>                      
            </div>
            <div class="form-group">
                <div class="input-group mb-3">
                	<div class="input-group-prepend">
                		<span class="input-group-text"><i class="fa fa-key"></i></span>
                	</div>
                	<input name="login-password" id="login-password" type="password" class="form-control input-lg" placeholder="Enter Password" required>
                </div>                      
            </div>

           	<button type="submit" class="btn btn-info btn-block btn-lg">LOGIN</button>
    	</form>
   	</div>  
</body>
</html>

