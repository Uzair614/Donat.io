<script>
	$(document).ready(function(){
		$("body").on("click","#form_Sign_Up", function(){
			var access_key = 'e2b2e42c2f33b9979d6b3d7dd255e218';
			var email = $('#email').val();
			var url = 'http://apilayer.net/api/check?access_key=' + access_key + '&email=' + email;
			console.log(url);
			// alert(url);
			var regNo = $('#regNo').val();
			if (!isInt(regNo)) {
				alert("Invalid Registration Number");
				return;
			}

			// alert();
			$.ajax({
			url: url,
			async: false,
			dataType: 'json',
			success: function(json) {

				if(json["format_valid"]) {
					if(validateForm()) {
						servercall();
					}
				}
				else {
					alert("Invalid email");
					console.log('put alert here');
				}
			}
			});

	});

	function validateForm() {
	  var isValid = true;

	  $('.form-control').each(function() {
	    if ( $(this).val() == '' ) {
				// $(this).attr('style', "border-radius: 5px; border:#FF0000 1px solid;");
				isValid = false;
			}
	  });

	  return isValid;
	}

	function servercall() {

		var username = $('#username').val();
		var pass = $('#pass').val();
		var email = $('#email').val();
		var orgName = $('#orgName').val();
		var regNo = $('#regNo').val();
		var params = {
			username : username,
			pass : pass,
			email : email,
			orgName : orgName,
			regNo : regNo
		}
		var url = "<?php echo URL; ?>home/ajaxSignup";

		console.log(url);
		$.ajax({
					 type: "POST",
					 url: url,
					 async: false,
					 contentType: 'application/x-www-form-urlencoded',
					 data: {params : JSON.stringify(params)},
					 success: function(data) {

						 var out = JSON.parse(data);
						 if(out["username"] && out["email"] && out["regNo"])
						 	alert("Account successfully registered");

						 if(out["username"] == 0) {
							 alert("Username already taken");
						 }
						 if(out["email"] == 0) {
							 alert("Email already taken");
						 }
						 if(out["regNo"] == 0) {
							 alert("Registration Number already in use.");
						 }

					 },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
						 // alert("hehererere");
						 // alert(errorThrown);
						 // alert(textStatus);
						 // alert(XMLHttpRequest.responseText);
						}
					});
		}

		function isInt(value) {
		  var x;
		  if (isNaN(value)) {
		    return false;
		  }
		  x = parseFloat(value);
		  return (x | 0) === x;
		}


	});


</script>
