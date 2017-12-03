<script>
	$(document).ready(function(){

		$("#locationsNav").addClass('active');

		$("body").on("click","button", function(){

			var id = ($(this).attr('id'));
			var params = {
				cID : id
			}
				$.ajax({
               type: "POST",
               url: "<?php echo URL; ?>admin/ajaxCentreDelete",
               contentType: 'application/x-www-form-urlencoded',
               data: {params : JSON.stringify(params)},
               success: function(data) {
								 $(".table").load("<?php echo URL; ?>admin/locations .table");
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {

                  alert("There was an error");
                }
              });
		          });
	});
</script>
