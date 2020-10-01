<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
	
	<title>Manage Users :: Admin | BOOKMYMOVIE.COM</title>

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
                             <a class="navbar-brand" href="/bookmymovie/admin/manage-users">Manage Users</a>
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
                               
                                   <form class="form-inline" method="GET" action="/bookmymovie/admin/manage-users/filter">
                                       <div class="form-group mb-2">
                                         <select name="filter" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                           <option value = "ALL" selected> ALL </option>
                                           <option value="Customer">Customer</option>
                                           <option value="Theatre">Theatre</option>
                                           <option value="Admin">Admin</option>
                                         
                                         </select>
                                       </div>
                                       
                                       <button type="submit" class="btn btn-primary mb-2">Filter</button>
                                    </form>
                                </div>
                                <div class="ml-auto">
                                    <button class="btn btn-warning"> <a href="/bookmymovie/admin/manage-users/addUser"> <i class="far fa-plus-square"></i> &nbsp; New User </a></button>
                                </div>
                               
                           </div>
                     
                         <br>
                       
                         
                          <div class="scroll-users">
                                            
							<table class="table table-hover table-striped table-light">
                                 <thead class="thead-dark">
                                   <tr>
                                        <th>USER_ID</th> 
                                         <th>USER_EMAIL</th>
                                         <th>USER_NAME</th>
                                         <th>USER_TYPE</th>
                                         <th>REG DATE</th>
                                         <th>LAST LOGIN</th>
                                         <th>ACTION</th>
                                   </tr>
                                 </thead>
                                 <tbody>
                                  <c:forEach var="users" items="${users}">
                                     <tr>
                                         <td><c:out value=" ${users.userID}"/></td>
                                         <td><c:out value=" ${users.userEmail}"/></td>
                                         <td><c:out value=" ${users.userName}"/></td>
                                         <td><c:out value=" ${users.userType}"/></td>
                                         <td><c:out value=" ${users.regDate}"/></td>
                                         <td><c:out value=" ${users.lastLog}"/></td>
                                         <td>
                                         		<button class="viewUserModal btn btn-secondary" data-toggle="modal" data-target="#viewUserModal" ><i class="far fa-eye"></i></button>
                                         		
                                         		
                                         		
                                         		<c:if test = "${users.getUserStatus() == 'Active'}">
                                         		
                                         			<button data-toggle="modal" class="deleteUserModal btn btn-danger" data-target="#deleteUserModal"><i class="far fa-trash-alt"></i></button>
										         
										        </c:if>
										        
										        <c:if test = "${users.getUserStatus() == 'Inactive'}">
                                         		
                                         			<button data-toggle="modal" class="activateUserModal btn btn-success" data-target="#activateUserModal"><i class="fa fa-refresh"></i></button>
										         
										        </c:if>
										      
                                         		<input type="hidden" id="id" value="${users.userID}"/>
                                         		<input type="hidden" id="type" value="${users.userType}"/>
                                         </td>
                                                
                                      </tr>
                                    </c:forEach>   
                                 </tbody>
                             </table>
                                                         
                         </div> 
                         
                         <div class="modal fade" id="viewUserModal" tabindex="-1" role="dialog" aria-labelledby="ViewMovie" aria-hidden="true">
                                 <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                   <div class="modal-content">
                                       
                                             <div class="modal-header">
                                                 <h4 class="modal-title " id="modalLongTitle"><b>USER DETAILS</b></h4>
                                               <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                 <span aria-hidden="true">&times;</span>
                                               </button>
                                             </div>
                                             <div class="modal-body">  
													
													<div class="form-group row">
                                                       <label for="id" class="col-sm-3 col-form-label">User ID</label>
                                                       <div class="col-sm-9">
                                                         <input type="text" class="form-control" id="id" disabled>
                                                       </div>
                                                    </div>
                                                    
                                                    <div class="form-group row">
                                                       <label for="email" class="col-sm-3 col-form-label">User Email</label>
                                                       <div class="col-sm-9">
                                                         <input type="text" class="form-control" id="email" disabled>
                                                       </div>
                                                    </div>
                                                    
                                                    <div class="form-group row">
                                                       <label for="name" class="col-sm-3 col-form-label">User Name</label>
                                                       <div class="col-sm-9">
                                                         <input type="text" class="form-control" id="name" disabled>
                                                       </div>
                                                    </div>
                                                    
                                                    <div class="form-group row">
                                                       <label for="type" class="col-sm-3 col-form-label">User Type</label>
                                                       <div class="col-sm-9">
                                                         <input type="text" class="form-control" id="type" disabled>
                                                       </div>
                                                    </div>
                                                    
                       
                                                    
                                                    <div class="form-group row">
                                                       <label for="reg" class="col-sm-3 col-form-label">Registration Date</label>
                                                       <div class="col-sm-9">
                                                         <input type="text" class="form-control" id="reg" disabled>
                                                       </div>
                                                    </div>
                                                    
                                                    <div class="form-group row">
                                                       <label for="lastlog" class="col-sm-3 col-form-label">Last Login:</label>
                                                       <div class="col-sm-9">
                                                         <input type="text" class="form-control" id="lastlog" disabled>
                                                       </div>
                                                    </div>
                                                    
											</div>
                                            <div class="modal-footer">
                                                 <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                  <input type="hidden" name="id" id="id"/>
                                           </div>
                                   </div>
                                 </div>
                             </div>
                             
                             <div id="deleteUserModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="DeleteUser" aria-hidden="true">
                                   <div class="modal-dialog" role="document">
                                           <div class="modal-content">
                                               <form action="/bookmymovie/admin/deleteUser" method="POST">
                                                   <div class="modal-header">
                                                           				
                                                       <h3 class="modal-title"><i class="fas fa-exclamation-triangle"></i> &nbsp; Are you sure?</h3>	
                                                       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                   </div>
                                                   <div class="modal-body">
                                                           <p>Do you really want to Inactive this User? This process cannot be undone.</p>
                                                   </div>
                                                   <div class="modal-footer">
                                                           <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                           <button type="submit" class="btn btn-danger">Inactive</button>
                                                           <input type="hidden" name="id" id="id"/>
                                                   </div>
                                                   
                                               </form>         
                                          </div>
                                   </div>
                           	</div>  
                           	
                           	 <div id="activateUserModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="ActivateUser" aria-hidden="true">
                                   <div class="modal-dialog" role="document">
                                           <div class="modal-content">
                                               <form action="/bookmymovie/admin/activateUser" method="POST">
                                                   <div class="modal-header">
                                                           				
                                                       <h3 class="modal-title"><i class="fas fa-exclamation-triangle"></i> &nbsp; Are you sure?</h3>	
                                                       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                   </div>
                                                   <div class="modal-body">
                                                           <p>Do you really want to re-activate this User? This process cannot be undone.</p>
                                                   </div>
                                                   <div class="modal-footer">
                                                           <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                           <button type="submit" class="btn btn-success">Activate</button>
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

  </body>
</html>