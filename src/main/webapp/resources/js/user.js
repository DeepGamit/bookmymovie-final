$(document).ready(function(){
	
	
	$('.cancelBookingModal').on('click', function() {
		var id =$(this).parent().find('#id').val();
		$('#cancelBookingModal #id').val(id);
	});
	
	
	
	
});