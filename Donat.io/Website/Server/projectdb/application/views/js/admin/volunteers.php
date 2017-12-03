<script>
	$(document).ready(function(){

		$("#volunteersNav").addClass('active');

		$("body").on("click","button", function(){

			var arr = $(this).attr("id").split('-');
			var params = {
				vID : arr[0],
				action : arr[1]
			}
				$.ajax({
               type: "POST",
               url: "<?php echo URL; ?>admin/ajaxVolunteerRequest",
               contentType: 'application/x-www-form-urlencoded',
               data: {params : JSON.stringify(params)},
               success: function(data) {
								 $(".table").load("<?php echo URL; ?>admin/volunteers .table");
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
