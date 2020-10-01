<li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" href="#" id="dropdownMenuLink" data-toggle="dropdown" role="button" data-display="static" aria-haspopup="true" aria-expanded="false">
            ${user.userName}
    </a>
    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownUserLink">
            <h1 class="dropdown-header"> Hi, ${user.userName} </h1>
            <%-- <a class="dropdown-item" href="/bookmymovie/edit-profile/${user.userName}">Edit Profile</a> --%>
            <a class="dropdown-item" href="/bookmymovie/change-password/${user.userName}">Change Password</a>
            <a class="dropdown-item" href="/bookmymovie/bookingHistory/${user.userName}">Booking History</a>
            <a class="dropdown-item" href="/bookmymovie/userLogout">Logout</a>
    </div>
</li>
