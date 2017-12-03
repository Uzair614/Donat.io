<script>
	$(document).ready(function(){

		$("#requestsNav").addClass('active');

		$("body").on("click","button", function(){

			var arr = $(this).attr("id").split('-');
			var params = {
				dID : arr[0],
				action : arr[1]
			}
				$.ajax({
               type: "POST",
               url: "<?php echo URL; ?>admin/ajaxDonationRequest",
               contentType: 'application/x-www-form-urlencoded',
               data: {params : JSON.stringify(params)},
               success: function(data) {
								 $(".table").load("<?php echo URL; ?>admin/requests .table");
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
                  // alert( XMLHttpRequest.responseText);
                  // alert("Status: " + textStatus);
                  // alert("Error: " + errorThrown);
                  alert("There was an error");
                }
              });
		          });
						});
</script>
