<script>
	$(document).ready(function(){

		$("#dash-header").fadeOut("normal", function() {
        $(this).remove();
    });

		$("#mapsNav").addClass('active');

		$("#addLocation").on("click", function(){

				var params = [];

				var cName = $('#locationName').text();
				if(!cName)
					cName = "Donation Centre";

        var cAddress = $('#locationAddress').text();
        var latitude =  queryPostions.lat();
        var longitude =  queryPostions.lng();

				var queryInsertTypeArray = [];
				if ($('#BloodCheckbox').is(':checked')) {
					queryInsertTypeArray.push("BLOOD");
				} if ($('#MoneyCheckbox').is(':checked')) {
					queryInsertTypeArray.push("MONEY");
				} if ($('#ClothesCheckbox').is(':checked')) {
					queryInsertTypeArray.push("CLOTHES");
				} if ($('#BooksCheckbox').is(':checked')) {
					queryInsertTypeArray.push("BOOKS");
				} if ($('#FoodCheckbox').is(':checked')) {
					queryInsertTypeArray.push("FOOD");
				}

        var params = {
          cName : cName,
          cAddress : cAddress,
          longitude : longitude,
          latitude : latitude,
          queryInsertType : JSON.stringify(queryInsertTypeArray)
        };

        $.ajax({
               type: "POST",
               url: "<?php echo URL; ?>admin/ajaxCenterRequest",
               contentType: 'application/x-www-form-urlencoded',
               data: {params : JSON.stringify(params)},
               success: function(data) {
                 // alert(data);
                 $('#successModal').modal('show');
                 // $("#DonationRequestData").load("portal-request.php #DonationRequestData");
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
