$(document).ready(function(){
	

	
	$('table .updateTheatreModal').on('click', function(e) {
		var id =$(this).parent().find('#id').val();
		$('#updateTheatreModal #id').val(id);
		
		var city =$(this).parent().find('#city').val();
		$('#updateTheatreModal #theatreCity').val(city);
		
		var name =$(this).parent().find('#name').val();
		$('#updateTheatreModal #theatreName').val(name);
		
		var add =$(this).parent().find('#address').val();
		$('#updateTheatreModal #theatreAddress').val(add);
		
		var email =$(this).parent().find('#email').val();
		$('#updateTheatreModal #emailId').val(email);
		
	});
	
	
	$('table .updateTheatreModal').on('click', function(e) {
		var id =$(this).parent().find('#id').val();
		$('#updateTheatreModal #id').val(id);
		
		$.ajax({
			type: 'GET',
			url: 'manage-theatres/getTheatre/' + id,
			success: function(theatre){
				$('#updateTheatreModal #theatreName').val(theatre.theatreName);
				$('#updateTheatreModal #theatreCity').val(theatre.theatreCity);
				$('#updateTheatreModal #theatreAddress').val(theatre.theatreAddress);
					
			}
		});
	});
	
	$('table .viewMovieModal').on('click', function(e) {
		var id =$(this).parent().find('#id').val();
		
		$('#viewMovieModal #id').val(id);
		$.ajax({
			type: 'GET',
			url: 'getMovie/' + id,
			success: function(movie){
				$('#viewMovieModal #movieTitle').val(movie.movieTitle);
				$('#viewMovieModal #movieLength').val(movie.movieLength);
				$('#viewMovieModal #synopsis').val(movie.movieSynopsis);
				$('#viewMovieModal #releaseDate').val($.formatDate(movie.relDate));
				
				
			}
		});
	});
	
	$('table .viewUserModal').on('click', function(e) {
		var id =$(this).parent().find('#id').val();
		$('#viewUserModal #id').val(id);
		$.ajax({
			type: 'GET',
			url: 'getUser/' + id,
			success: function(user){
				$('#viewUserModal #id').val(user.userID);
				$('#viewUserModal #name').val(user.userName);
				$('#viewUserModal #email').val(user.userEmail);
				$('#viewUserModal #type').val(user.userType);
				$('#viewUserModal #reg').val($.formatDate(user.regDate));
				$('#viewUserModal #lastlog').val($.formatDateTime(user.lastLog));
				
			}
		});
	});
	
	

	
	$('table .updateMovieModal').on('click', function(e) {
		var id =$(this).parent().find('#id').val();
		$('#ypdateMovieModal #id').val(id);
		
		$.ajax({
			type: 'GET',
			url: 'getMovie/' + id,
			success: function(movie){
				$('#updateMovieModal #movieTitle').val(movie.movieTitle);
				$('#updateMovieModal #movieLength').val(movie.movieLength);
				$('#updateMovieModal #synopsis').val(movie.movieSynopsis);
				$('#updateMovieModal #releaseDate').val($.formattedDate(movie.relDate));
				
				
			}
		});
	});
	
	$('table .updateMovieModal').on('click', function(e) {
		
		var lang = $(this).parent().find('#lang').val();
		var l = lang.split(",");
		
		var cat = $(this).parent().find('#cat').val();
		var c = cat.split(",");
		
		
		var cast = $(this).parent().find('#cast').val();
		var s = cast.split(",");
		
		var length = $(this).parent().find('#length').val();
		var m = length.split(" ");
		
		var id =$(this).parent().find('#id').val();
		
		$('#updateMovieModal #language').selectpicker("val", l);
		$('#updateMovieModal #category').selectpicker("val", c);
		$('#updateMovieModal #cast').selectpicker("val", s);
		$('#updateMovieModal #hours').val(m[0]);
		$('#updateMovieModal #minutes').val(m[2]);
		$('#updateMovieModal #movieID').val(id);
		
	});
	
	

	 
	
	
	
	
	
	$('table .deleteUserModal').on('click', function() {
		var id =$(this).parent().find('#id').val();
		
		$('#deleteUserModal #id').val(id);
	});
	
	$('table .activateUserModal').on('click', function() {
		var id =$(this).parent().find('#id').val();
		
		$('#activateUserModal #id').val(id);
	});
	
	$('table .deleteMovieModal').on('click', function() {
		var id =$(this).parent().find('#id').val();
		$('#deleteMovieModal #id').val(id);
	});
	
	$('table .deleteTheatreModal').on('click', function() {
		var id =$(this).parent().find('#id').val();
		$('#deleteTheatreModal #id').val(id);
	});
	
	$('#datepicker').datepicker({
        uiLibrary: 'bootstrap4'
    });
	 
	$('#datetimepicker').datetimepicker({ footer: true, modal: true });
	
	$('#releaseDate').datepicker({
        uiLibrary: 'bootstrap4'
    });
	
	(function() {
	    'use strict';
	    window.addEventListener('load', function() {
	      // Fetch all the forms we want to apply custom Bootstrap validation styles to
	      var forms = document.getElementsByClassName('needs-validation');
	      // Loop over them and prevent submission
	      var validation = Array.prototype.filter.call(forms, function(form) {
	        form.addEventListener('submit', function(event) {
	          if (form.checkValidity() === false) {
	            event.preventDefault();
	            event.stopPropagation();
	          }
	          form.classList.add('was-validated');
	        }, false);
	      });
	    }, false);
	  })();
	
	
	
	
});



$.formattedDate = function(dateToFormat) {
    var dateObject = new Date(dateToFormat);
    var day = dateObject.getDate();
    var month = dateObject.getMonth() + 1;
    var year = dateObject.getFullYear();
    day = day < 10 ? "0" + day : day; 
    month = month < 10 ? "0" + month : month; 
    var formattedDate = month + "/" + day + "/" + year;    
    return formattedDate;
};

$.formatDate = function(dateToFormat) {
	
	const monthNames = ["January", "February", "March", "April", "May", "June",
		  "July", "August", "September", "October", "November", "December"
		];
    var dateObject = new Date(dateToFormat);
    var day = dateObject.getDate();
    var year = dateObject.getFullYear();
    day = day < 10 ? "0" + day : day; 
    var month = monthNames[dateObject.getMonth()]
    var formattedDate = day +" "+ month + ", " + year;    
    return formattedDate;
};

$.formatDateTime = function(dateToFormat) {
	
	const monthNames = ["January", "February", "March", "April", "May", "June",
		  "July", "August", "September", "October", "November", "December"];
	const days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
    var dateObject = new Date(dateToFormat);
    var date = dateObject.getDate();
    var year = dateObject.getFullYear();
    date = date < 10 ? "0" + date : date; 
    var month = monthNames[dateObject.getMonth()]
    var h = dateObject.getHours();
    var m = dateObject.getMinutes();
    var s = dateObject.getSeconds();
    var day = days[dateObject.getDay()]
    var formattedDate = date +" "+ month + ", " + year + " " + day + " " + h + ":" + m + ":" + s;    
    return formattedDate;
};







  

