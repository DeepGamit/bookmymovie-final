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
		<!-- <script src="https://kit.fontawesome.com/2d1a3b45ce.js" crossorigin="anonymous"></script> -->
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    	<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
	
	<title>Manage Theatres :: Admin | BOOKMYMOVIE.COM</title>

</head>
 <body>
 
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
	
	
	<div class="admin-dashboard">
		<div class="container-fluid">
		<div class="admin-rg">
				<div class="row">
					<div class="col-md-2">
						<%@ include file ="admin-profile-2.jsp" %>
					</div>
					<div class="col-md-10">
                                            
                                            
                                                
                                                <nav class="navbar navbar-expand-lg navbar-dark bg-info">							
                                                    <a class="navbar-brand" href="/bookmymovie/admin/manage-theatres">Manage Theatres</a>
                                                    
                                                    <div class="collapse navbar-collapse">
                                                            <ul class="navbar-nav ml-auto">
                                                                    <li>
                                                                       <a href="/bookmymovie/admin" class="nav-link menu-link">GO BACK</a>
                                                                    </li>
                                                                    	
                                                            </ul>
                                                    </div>
                                                </nav>
                                            
                                                <br>
     
                                                <div class="d-flex">
                                                    
                                                    <div class="filter-bar">
                                                    
                                                        <form class="form-inline" method="GET" action="/bookmymovie/admin/manage-theatres/searchby">
                                                            <div class="form-group mb-2">
                                                              <select name="searchby" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                                                <option selected> Search By </option>
                                                                <option value="City">City</option>
                                                                <option value="Name">Name</option>
                                                              </select>
                                                            </div>
                                                            <div class="form-group mx-sm-3 mb-2">
                                                              <label for="keyword" class="sr-only">Keyword</label>
                                                              <input type="text" class="form-control" name="keyword" id="keyword" placeholder="Keyword">
                                                            </div>
                                                            <button type="submit" class="btn btn-primary mb-2">Search</button>
                                                         </form>
                                                     </div>
                                                    <!-- <div class="ml-auto">
                                                        <button class="btn btn-warning"> <a href="/bookmymovie/admin/manage-theatres/addTheatre"> <i class="far fa-plus-square"></i> &nbsp; New Theatre </a></button>
                                                    </div> -->
                                                    
                                                </div>
                                                
                                               <br>
                                                
                                                 <div class="scroll-movies">
                                            
                                                       <table class="table table-hover table-striped table-light">
                                                           <thead class="thead-dark">
                                                             <tr>
                                                             	   <th>ID</th>
                                                                   <th>NAME</th>
                                                                   <th>CITY</th>
                                                                   <th>ADDRESS</th>
                                                                   <th>ACTION</th>
                                                             </tr>
                                                           </thead>
                                                           <tbody>
                                                           	<c:forEach var="theatre" items="${theatreList}">
                                                                <tr>
                                                                	<td><c:out value=" ${theatre.getUser().userEmail}"/></td>
                                                                    <td><c:out value=" ${theatre.theatreName}"/></td>
                                                                    <td><c:out value=" ${theatre.theatreCity}"/></td>
                                                                    <td><c:out value=" ${theatre.theatreAddress}"/></td>
                                                                 
                                                                   
                                                                    <td>   
                                                                        <button data-toggle="modal" class="updateTheatreModal btn btn-success" data-target="#updateTheatreModal"><i class="far fa-edit"></i></button> 
                                                                      
                                                                        <input type="hidden" id="id" value="${theatre.theatreId}"/>
                                                                        <input type="hidden" id="email" value="${theatre.getUser().userEmail}"/>
                                                                        <input type="hidden" id="city" value="${theatre.theatreCity}"/>
                                                                        <input type="hidden" id="name" value="${theatre.theatreName}"/>
                                                                        <input type="hidden" id="address" value="${theatre.theatreAddress}"/>
                                                                    </td>
                                                                 </tr>
                                                             </c:forEach>   
                                                           </tbody>
                                                        </table>
                                                 </div> 
                                                 
                                                 
                                                 <div id="deleteTheatreModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="DeleteTheatre" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                                <div class="modal-content">
                                                                    <form action="/bookmymovie/admin/deleteTheatre" method="POST">
                                                                        <div class="modal-header">
                                                                                				
                                                                            <h3 class="modal-title"><i class="fas fa-exclamation-triangle"></i> &nbsp; Are you sure?</h3>	
                                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                                <p>Do you really want to delete this Theatre? This process cannot be undone.</p>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                                 <button type="submit" class="btn btn-danger" >Delete</button>
                                                                                  <input type="hidden" name="id" id="id"/>
                                                                        </div>
                                                                        
                                                                    </form>         
                                                                </div>
                                                        </div>
                                                </div>  
                                                
                                                
                                                 <div class="modal fade" id="updateTheatreModal" tabindex="-1" role="dialog" aria-labelledby="UpdateMovie" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                                      <div class="modal-content">
                                                          
                                                        <form action="/bookmymovie/admin/manage-theatres/updateTheatre" method="POST">
                                                        
                                                        
                                                        	 <div class="modal-header">
                                                                    <h4 class="modal-title " id="modalLongTitle"><b>UPDATE THEATRE</b></h4>
                                                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                  </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                
                                                                
                                                                	<div class="form-group row">
                                                                        <label for="emailId" class="col-sm-3 col-form-label">Email Id</label>
                                                                        <div class="col-sm-7">
                                                                          <input type="text" class="form-control" name="emailId" id="emailId" placeholder="Email Id" readonly>
                                                                        </div>
                                                                      </div>
                                                                
                                                        			<div class="form-group row">
                                                                        <label for="theatreName" class="col-sm-3 col-form-label">Theatre Name</label>
                                                                        <div class="col-sm-7">
                                                                          <input type="text" class="form-control" name="theatreName" id="theatreName" placeholder="Theatre Name" required>
                                                                        </div>
                                                                      </div>


                                                                     <div class="form-group row">
                                                                        <label for="theatreCity" class="col-sm-3 col-form-label">Theatre City</label>
                                                        				<div class="col-sm-7">
																		    <select class="form-control" name="theatreCity" id="theatreCity" placeholder="Theatre City" required>
																		      <option value="Ahmedabad">Ahmedabad</option>
																		      <option value="Mumbai">Mumbai</option>
																		      <option value="Delhi">Delhi</option>
																		      <option value="Chennai">Chennai</option>
																		      <option value="Banglore">Banglore</option>
																		    </select>
																		 </div>
                                                                      </div>

                                                                      <div class="form-group row">
                                                                        <label for="theatreAddress" class="col-sm-3 col-form-label">Theatre Address</label>
                                                                         <div class="col-sm-7">
                                                                            <textarea class="form-control" name="theatreAddress" id="theatreAddress" rows="3" placeholder="Theatre Address" required></textarea>
                                                                         </div>
                                                                      </div>
                                                                      
                                                                   </div>
                                                        
                                                        		
                                                                

                                                                   <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                        <button type="submit" class="btn btn-primary">Update Theatre</button>
                                                                        <input type="hidden" name="id" id="id"/>
                                                                  </div>


                                                                
                                                        </form>
                                                         
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
	
	

  </body>
</html>
