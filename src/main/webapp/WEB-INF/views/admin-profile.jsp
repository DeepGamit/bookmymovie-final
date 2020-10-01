<div class="profile-box">
	<div class="profile-img">
		<img src="<%=request.getContextPath()%>/resources/img/admin/profile.png" width="100%">
	</div>
 	<div class="name">
		<h1>
			${userAdmin.userName}
		</h1>
	</div> 
	<div class="logout-img">
		<a href="/bookmymovie/logout"><img src="<%=request.getContextPath()%>/resources/img/admin/logout.svg" width="100%"></a>
	</div>						
</div>