(function(){
	
	'use strict';
	
	angular.module('restApp').controller('OwnerCtrl', OwnerController);
	OwnerController.$inject = ['$http','$scope','$upload'];
	function OwnerController($http,$scope){
		var octrl=this;		
		
		octrl.getAllRes = function () {
			$http({
				method: 'GET',
				url: 'api/reserve/all'
			}).success(function(data){
				console.log(data);
				$scope.register=data.payload;
				$('#allreservation').show();
				$('#getcustomer').hide();
			}).error(function(error){
				console.log(error);
			});
		};
			
	/*	octrl.getCustomer=function(){				
			$http({
				method: 'GET',
				url: 'api/reserve/customer/' + octrl.query
			}).success(function(data){
				console.log(data);
				$('#allreservation').hide();
				$('#getcustomer').show();
				$scope.customer=data.payload;
				
			}).error(function(error){
				console.log(error);
				bootbox.alert("Oops wrong customer");
			});

		};	
		*/
		octrl.getCustomerDetails=function(id){
			
			$http({
				method: 'GET',
				url: 'api/reserve/reservation/' + id
			}).success(function(data){
				console.log(data);
				bootbox.alert("<table align='center' cellspacing='1' cellpadding='1'><tr><td colspan='2' style='font-size: 35px;color:'red';><b>Guest Information</b></td></tr><tr><td><b>Reservation id:</b></td><td>"+data.payload.id+
						"</td></tr><tr><td><b>Firstname:</b></td><td>"+data.payload.firstname+"</td></tr>" +
						"<tr><td><b>Lastname:</b></td><td>"+data.payload.lastname+"</td></tr> " +
								"<tr><td><b>Reservation Date:</b></td><td>"+data.payload.reservationdate+"</td></tr>" +
										"<tr><td><b>Reservation Time:</b></td><td>"+data.payload.reservationtime+"</td></tr>    " +
												"<tr><td><b>Email:</b></td><td>"+data.payload.email+"</td></tr>   " +
								"<tr><td><b>Phone:</b></td><td>"+data.payload.phone+"</td></tr> " +
										"<tr><td><b>Party Size:</b></td><td>"+data.payload.partysize+"</td></tr>  " +
												"<tr><td><b>Special Needs:</b></td><td>"+data.payload.specialneeds+"</td></tr>               </table>");
				
			}).error(function(error){
				console.log(error);
				bootbox.alert("Oops wrong customer");
			});

		};

	    $scope.available = function(id){
	      console.log(id);
	      $('#tableno').val(id);
	       $("#myModal").on("show", function() {    // wire up the OK button to dismiss the modal when shown
	           $("#myModal a.btn").on("click", function(e) {
	               console.log("button pressed");   // just as an example...
	               $("#myModal").modal('hide');     // dismiss the dialog
	           });
	       });

	       $("#myModal").on("hide", function() {    // remove the event listeners when the dialog is dismissed
	           $("#myModal a.btn").off("click");
	       });
	       
	       $("#myModal").on("hidden", function() {  // remove the actual elements from the DOM when fully hidden
	           $("#myModal").remove();
	       });
	       
	       $("#myModal").modal({                    // wire up the actual modal functionality and show the dialog
	         "backdrop"  : "static",
	         "keyboard"  : true,
	         "show"      : true                     // ensure the modal is shown immediately
	       });
	    };
	    
	    
	    
		octrl.searchReservation=function(){				
			$http({
				method: 'GET',
				url: 'api/reserve/reservation/' + octrl.reservationid
			}).success(function(data){
				console.log(data);
				if (typeof(Storage) != "undefined") {
					
				    localStorage.setItem("reservationid", octrl.reservationid);
				}
				if(data.status=="error"){
					$('#errortext2').text("Invalid Reservation id.Does not exist in the system");
				}
				else{
					$('#updateReservation').show();
					$('#updatereservationdate').val(data.payload.reservationdate);
					$('#updatereservationtime').val(data.payload.reservationtime);
					$('#updatefirstname').val(data.payload.firstname);
					$('#updatelastname').val(data.payload.lastname);
					$('#updatephone').val(data.payload.phone);
					$('#updateemail').val(data.payload.email);
					$('#updatepartysize').val(data.payload.partysize);
					$('#updatespecialneeds').val(data.payload.specialneeds);
				}
				
			}).error(function(error){
				console.log(error);
				$('#errortext2').val("Invalid Reservation id");
				//bootbox.alert("Oops wrong Reservation id");
			});

		};
		
		octrl.updateReservation=function(){				
			$http({
				method: 'POST',
				url: 'api/reserve/update/'+localStorage.getItem("reservationid"),
				data: octrl.updatereserve
			}).success(function(data){
				console.log(data);
				$('#myModal').hide();
			//	octrl.updatereserve = null;
				bootbox.alert(data.status+"!!!!!Reservation has been updated",function(){
					window.location.href="http://localhost:8080/RestaurantReservation/index.html";
					
				});		
			}).error(function(error){
				console.log(error);
				bootbox.alert("Oops wrong information");
			});
		
		};
		
		
	
		
		
		
		octrl.reserve = function () {
			$http({
				method: 'POST',
				url: 'api/reserve/add',
				data: octrl.newreserve
			}).success(function(data){
				console.log(data);
				bootbox.alert(data.status+"!!!!!.Reservation has been added to the system with id "+data.payload.id);				
				octrl.newreserve = null;
			}).error(function(error){
				console.log(error);
			});
		};
		
		
		
		
	/***************************************OWNER*******************************/ 	
			octrl.updateOwner=function(){				
				$http({
					method: 'POST',
					url: 'api/owner/update/'+localStorage.getItem("usernameOwner"),
					data: octrl.update
				}).success(function(data){
					console.log(data);
					bootbox.alert(data.status+"!!!!!Personal Information has been updated");				
					octrl.update = null;
					
				}).error(function(error){
					console.log(error);
					bootbox.alert("Oops wrong information");
				});
			
			};

			octrl.sendMessage = function () {
				$http({
					method: 'POST',
					url: 'api/owner/message',
					data: octrl.newcontact
				}).success(function(data){
					console.log(data);
					bootbox.alert(data.message);
					octrl.newcontact=null;
				}).error(function(error){
					console.log(error);
				});
			};	
			
			
			
			
		octrl.getOwner=function(){				
					$http({
						method: 'GET',
						url: 'api/owner/verifylogin/' + octrl.username + '/' + octrl.password
					}).success(function(data){
						console.log(data);
						if(data.status=="error"){
							$('#errortext3').text(data.status+"!!!!"+data.message);
							//bootbox.alert("Oops wrong username/password");
						}
						else if(data.status="success"){
							if (typeof(Storage) != "undefined") {
						
							    localStorage.setItem("usernameOwner", octrl.username);
							    window.location.href="http://localhost:8080/RestaurantReservation/dashboard.html";
							}
						}
						
					}).error(function(error){
						console.log(error);
						bootbox.alert("Oops wrong username/password");
					});
	
		};
		
		
		octrl.getSeating=function(){				
			$http({
				method: 'GET',
				url: 'api/reserve/seating',
			}).success(function(data){
				$scope.seating=data.payload;
				
				console.log(data);
			}).error(function(error){
				console.log(error);
				bootbox.alert("Oops wrong username/password");
			});

};	
	

octrl.searchOwner=function(){				
	$http({
		method: 'GET',
		url: 'api/owner/searchOwner/' + localStorage.getItem("usernameOwner")
	}).success(function(data){
		console.log(data);
		$('#ownerusername').val(data.payload.username);
		$('#ownerpassword').val(data.payload.password);
		$('#ownercity').val(data.payload.city);
		$('#owneraddress').val(data.payload.address);
		$('#ownerphone').val(data.payload.phone);
		$('#owneremail').val(data.payload.email);
		$('#ownerdob').val(data.payload.dob);
		$('#ownername').val(data.payload.name);
				
	}).error(function(error){
		console.log(error);
		bootbox.alert("Oops wrong Owner name");
	});

};

octrl.removeCustomer=function(id){	

	$http({
		method: 'POST',
		url: 'api/owner/delete/'+id,
	}).success(function(data){	
		console.log(data);
		bootbox.alert("Reservation successfully removed");
		window.location.reload();
		
		//window.location.href="http://localhost:8080/RestaurantReservation/Home.html#tab2";
	}).error(function(error){
		console.log(error);
		bootbox.alert("Oops wrong Reservationid");
	});

};


octrl.addReservationOwner= function () {
	
	
	console.log(octrl.addreserve.tableno);
	/*$http({
		method: 'POST',
		url: 'api/owner/addOwner/'+id,
		data: octrl.addreserve
	}).success(function(data){
		console.log(data);
		bootbox.alert(data.status+"!!!!!.Reservation has been added to the system with id "+data.payload.id);				
		octrl.newreserve = null;
	}).error(function(error){
		console.log(error);
	});*/
};


	}
})();
