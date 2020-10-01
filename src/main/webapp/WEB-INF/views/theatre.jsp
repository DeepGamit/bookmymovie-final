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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/3.1.3/css/bootstrap-datepicker.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/css/bootstrap-select.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/3.1.3/js/bootstrap-datepicker.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.9/js/bootstrap-select.min.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/admin.css" type="text/css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/2d1a3b45ce.js" crossorigin="anonymous"></script>
        <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
        <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    	<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.14.0/jquery.validate.min.js"></script>
  
        <title>Dashboard :: Theatre | BOOKMYMOVIE.COM</title>
        
    </head>
    <body>
    
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
							<%@ include file ="theatre-profile-2.jsp" %>
						</div>
					
						<div class="col-md-10">
							<div class="d-flex">
								<div class="filter-bar">
									<form class="form-inline" method= "GET" action="/bookmymovie/theatre/searchShow">
	                                    <div class="form-group mb-2">
	                                      <select name="searchby" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
	                                        <option name="Search By" selected> Search By </option>
	                                        <option value="Movie Title">Movie Title</option>
	                                      </select>
	                                    </div>
                                        <div class="form-group mx-sm-3 mb-2">
                                          <label for="keyword" class="sr-only">Keyword</label>
                                          <input type="text" class="form-control" name="keyword" id="keyword" placeholder="Keyword">
                                        </div>
                                        <div class="ml-auto">
                                        	<button type="submit" class="btn btn-primary mb-2">Search</button>
                                        </div>
                                    </form>
                                </div>
                               	<div class="ml-auto">
                                   <button class="btn btn-warning"> <a href="/bookmymovie/theatre/addShow"> <i class="far fa-plus-square"></i> &nbsp; New Show </a></button>
                               	</div>  
                            </div>
                                
                            <div class="d-flex">
                            	<div class="filter-bar">
                            		<form class="form-inline" method= "GET" action="/bookmymovie/theatre/getShowByDate">
                            			<div class="col-sm-9">
                            				<input  placeholder="Filter Date" class="form-control" name="filterDate" id="datepicker" required>
                            			</div>
                            				<button type="submit" class="btn btn-primary mb-2">Sort</button>
                            		</form>
                            	</div>
                            	<div class="ml-auto">
                           	 		<button class="btn btn-warning"> <a href="/bookmymovie/theatre/editAccountDetails"> <i class="far fa-edit"></i>Edit Account</a></button>
                            	</div>
                            </div>
                            <br></br>
                            <div class="scroll-movies">
                            	<table class="table table-hover table-striped table-light">
                            		<thead class="thead-dark">
                            			<tr>
                            				<th>MOVIE</th>
                            				<th>SHOW DATE</th>
                            				<th>SHOW TIME</th>
                            				<th>SEAT PRICE</th>
                            				<th>SCREEN</th>
                            				<th>SEATS</th>
                            				<th colspan="2">ACTION </th>
                            			</tr>
                            		</thead>
                            		<tbody>
                            			<c:forEach var="show" items="${shows}">
                            				<tr>
                            					<td><c:out value=" ${show.getMovie().movieTitle}"/></td>
                            					<td><c:out value=" ${show.showDate}"/></td>
                            					<td><c:out value=" ${show.showTime}"/></td>
                            					<td><c:out value=" ${show.showPrice}"/></td>
                            					<td><c:out value=" ${show.screenNumber}"/></td>
                            					<td><c:out value=" ${show.numberOfRows * show.numberOfSeat}"/></td>
                            					
                            					<td>
                            						<form action="/bookmymovie/theatre/viewBookings" method="GET">
                            							<button type="submit" class="btn btn-info"><i class="far fa-address-book"></i></button>
                            							<input type="hidden" value="${show.showID}" name="showId" />
                            						</form>
                            					</td>
                            					<td>
													<form action="/bookmymovie/theatre/viewLayout" method="GET">
														<button type="submit" class="btn btn-secondary"><i class="far fa-eye"></i></button>
														<input type="hidden" value="${show.showID}" name="showId" />
													</form>
												</td>
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
	<script src="<%=request.getContextPath()%>/resources/js/theatre.js"></script>
	<!-- /My JavaScript -->
     
    </body>
</html>
