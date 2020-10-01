
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    
    <a class="navbar-brand" href="/bookmymovie/">
      <img src="<%=request.getContextPath()%>/resources/img/logo.png" width="280" height="30" alt="book logo">
    </a>
    
    <form class="form-inline ml-lg-4" action="/bookmymovie/search" method="get">
    	<div class="mr-2">
	        <select name ="city" class="custom-select mr-sm-2" id="inlineFormCustomSelect" placeholder="City" required>
	        	<option value="Ahmedabad">Ahmedabad</option>
	            <option value="Mumbai">Mumbai</option>
	            <option value="Delhi">Delhi</option>
	            <option value="Chennai">Chennai</option>
	            <option value="Banglore">Banglore</option>
	        </select>
        </div>
                        
        <div class="mr-2">
        	<select id="movie" name="movie" class="selectpicker w-100" data-live-search="true" placeholder="Movie Name" required>
           		<c:forEach var="movie" items="${movies}">
					<option value="${movie.movieTitle}">${movie.movieTitle}</option>
		  		</c:forEach>
         	</select>
        </div>
         
         <div class="mr-2">
        	<button type="submit" class="btn btn-info">Search</button>
         </div>
                 
    </form>

    <div class="collapse navbar-collapse"> 
        <div class="mr-auto"></div>
		<ul class="navbar-nav my-2 my-lg-0 mr-2">
	        <c:set var="user" value ="${sessionScope.user}"/>
	        <c:if test="${user!=null}">
	            <%@ include file ="logged-in.jsp" %>																				
	        </c:if>
	        <c:if test="${user==null}">
	        	<button class="btn btn-outline-info mr-3"><a href="/bookmymovie/login" style="text-decoration:none; color:white"> Login </a></button>
	            <button class="btn btn-outline-info"><a href="/bookmymovie/register" style="text-decoration:none; color:white"> SignUp </a></button>
			</c:if>
         </ul>
	 </div>    
</nav>
                                            
                                            
                                            
                                            
                                            



