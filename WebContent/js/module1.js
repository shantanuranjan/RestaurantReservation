/**
 * Created by shantanu on 4/12/2015.
 */
$(document).ready(function() {
	
	validate();
	$('#profile').hide();
	//$('#reservationseating').hide();
   
    $('#updatereservationdate,#updatespecialneeds,#updatefirstname,#updatereservationtime, #updatelastname,#updatephone,#updateemail,#updatepartysize').change(validate);
    
    $('#getcustomer').hide();
    $("input[type='image']").click(function() {
        $("input[id='upload']").click();
    });
	$('#updateReservation').hide();
    jQuery('.tabs .tab-links a').on('click', function(e) {
        var currentAttrValue = jQuery(this).attr('href');
        
        jQuery('.tabs ' + currentAttrValue).show().siblings().hide();
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
        e.preventDefault();
    });

    $('#reservationdate').datepicker({
        format: "dd/mm/yyyy"
    });
    $('#reservationdateOwner').datepicker({
        format: "dd/mm/yyyy"
    });
    $('#updatereservationdate').datepicker({
        format: "dd/mm/yyyy"
    });
 /*   $('#updateReserve').on('click',function(e){
    	
    	    $("#myModal").modal({                    
    	      "backdrop"  : "static",
    	      "keyboard"  : true,
    	      "show"      : true                    
    	    });
    	
    });

  */
    $('a[href^="#"]').on('click',function (e) {
	    e.preventDefault();

	    var target = this.hash,
	    $target = $(target);

	    $('html, body').stop().animate({
	        'scrollTop': $target.offset().top-200 // - 200px (nav-height)
	    }, 900, 'swing', function () {
	        window.location.hash = target;
	    });
	});

   
});

function validate(){
    if ($('#updatereservationdate').val().length   >   0   &&
        $('#updatereservationtime').val().length  >   0   &&
        $('#updatelastname').val().length    >   0  &&
        $('#updatephone').val().length    >   0  &&
        $('#updateemail').val().length    >   0  &&
        $('#updatepartysize').val().length    >   0  &&
        $('#updatespecialneeds').val().length    >   0  &&
        $('#updatefirstname').val().length    >   0) {
        $("#updatereserve").prop("disabled", false);
    }
    else {
        $("#updatereserve").prop("disabled", true);
    }
}