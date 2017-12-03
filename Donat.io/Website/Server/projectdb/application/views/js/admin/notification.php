<script>
	$(document).ready(function(){
		$("#notificationsNav").addClass('active');

		$("body").on("click","button", function(){

			var id = ($(this).attr('id'));
			if(id == "SendNotification") {
				var message = $('#Message').val();
				var audience = 0;
				if ($("#VolunteerRadio").is(":checked")){
					var audience = 0;
				} else if ($("#UserRadio").is(":checked")){
					var audience = 1;
				}
				var params = {
					message : message,
					audience : audience
				}
				var url = "<?php echo URL; ?>admin/ajaxNotification";
			} else {
				var params = {
						mID : id
					}
					var url = "<?php echo URL; ?>admin/deleteNotification";
			}
			console.log(params);
				$.ajax({
               type: "POST",
               url: url,
               contentType: 'application/x-www-form-urlencoded',
               data: {params : JSON.stringify(params)},
               success: function(data) {
								 // alert(data + "success");
								 $(".table").load("<?php echo URL; ?>admin/notification .table");
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
								 // alert(errorThrown);
								 // alert(textStatus);
								 // alert(XMLHttpRequest.responseText);
         //        }
              });
		          });
	});

</script>
