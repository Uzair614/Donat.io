<style>
	/* Always set the map height explicitly to define the size of the div
	 * element that contains the map. */
	#map {
		height: 100vh;
	}
	/* Optional: Makes the sample page fill the window. */
 /* html, body {
		height: 100%;
		margin: 0;
		padding: 0;
	}*/
	#description {
		font-family: Roboto;
		font-size: 15px;
		font-weight: 300;
	}

	#infowindow-content .title {
		font-weight: bold;
	}

	#infowindow-content {
		display: none;
	}

	#map #infowindow-content {
		display: inline;
	}

	.pac-card {
		margin: 10px 10px 0 0;
		border-radius: 2px 0 0 2px;
		box-sizing: border-box;
		-moz-box-sizing: border-box;
		outline: none;
		box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
		background-color: #fff;
		font-family: Roboto;
	}

	#pac-container {
		padding-bottom: 12px;
		margin-right: 12px;
	}

	.pac-controls {
		display: inline-block;
		padding: 5px 11px;
	}

	.pac-controls label {
		font-family: Roboto;
		font-size: 13px;
		font-weight: 300;
	}

	#pac-input {
		background-color: #fff;
		font-family: Roboto;
		font-size: 15px;
		font-weight: 300;
		margin-left: 12px;
		padding: 0 11px 0 13px;
		text-overflow: ellipsis;
		width: 400px;
	}

	#pac-input:focus {
		border-color: #4d90fe;
	}

	#title {
		color: #fff;
		background-color: #4d90fe;
		font-size: 25px;
		font-weight: 500;
		padding: 6px 12px;
	}
</style>
			<div id="map"></div>

	    </div>
	</div>
	<div class="container-fluid">
		<div class="row">
				<div style="height: 50px;"></div>

				<div class="col-md-9">
					<div class="pac-card" id="pac-card">
						<div>
							<div id="title">
								Autocomplete search
							</div>
							<div id="type-selector" class="pac-controls">
								<input type="radio" name="type" id="changetype-all" checked="checked">
								<label for="changetype-all">All</label>

								<input type="radio" name="type" id="changetype-establishment">
								<label for="changetype-establishment">Establishments</label>

								<input type="radio" name="type" id="changetype-address">
								<label for="changetype-address">Addresses</label>

								<input type="radio" name="type" id="changetype-geocode">
								<label for="changetype-geocode">Geocodes</label>
							</div>
							<div id="strict-bounds-selector" class="pac-controls">
								<input type="checkbox" id="use-strict-bounds" value="">
								<label for="use-strict-bounds">Strict Bounds</label>
							</div>
						</div>
						<div id="pac-container">
							<input id="pac-input" type="text"
									placeholder="Enter a location">
						</div>
					</div>
					<div id="map"></div>
					<div id="infowindow-content">
						<img src="" width="16" height="16" id="place-icon">
						<span id="place-name"  class="title"></span><br>
						<span id="place-address"></span>
					</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="exampleModalLabel">Add Location?</h3>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="modal-body">
				<h4>Do you want to add this location?</h4>
				<h5 style="display:inline;" id="locationNameLabel">Name: </h5><h5 style="display:inline;" id="locationName"></h5><br/>
				<h5 style="display:inline;">Address: </h5><h5 style="display:inline;" id="locationAddress"></h5>
				</div>
				<span></span>
				<h5 class = "text-center">Donation Centre Type. Please select as appropriate.</h5>
				<div class="checkbox text-center">
					<label><input type="checkbox" name="optcheckbox" checked="true" id="BloodCheckbox">Blood</label>
				</div>
				<div class="checkbox text-center">
					<label><input type="checkbox" name="optcheckbox" id="MoneyCheckbox">Money</label>
				</div>
				<div class="checkbox text-center">
					<label><input type="checkbox" name="optcheckbox" id="ClothesCheckbox">Clothes</label>
				</div>
				<div class="checkbox text-center">
					<label><input type="checkbox" name="optcheckbox" id="BooksCheckbox">Books</label>
				</div>
				<div class="checkbox text-center">
					<label><input type="checkbox" name="optcheckbox" id="FoodCheckbox">Food</label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="addLocation">Add location</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Success</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					You have successfully entered the location.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script>



	      var queryPostions;
	      function initMap() {
	        var map = new google.maps.Map(document.getElementById('map'), {
	          center: {lat: 24.91832, lng: 67.09701},
	          zoom: 13
	        });
	        var card = document.getElementById('pac-card');
	        var input = document.getElementById('pac-input');
	        var types = document.getElementById('type-selector');
	        var strictBounds = document.getElementById('strict-bounds-selector');
	        map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

	        var autocomplete = new google.maps.places.Autocomplete(input);

	        // Bind the map's bounds (viewport) property to the autocomplete object,
	        // so that the autocomplete requests use the current map bounds for the
	        // bounds option in the request.
	        autocomplete.bindTo('bounds', map);

	        var infowindow = new google.maps.InfoWindow();
	        var infowindowContent = document.getElementById('infowindow-content');
	        infowindow.setContent(infowindowContent);
	        var marker = new google.maps.Marker({
	          map: map,
	          anchorPoint: new google.maps.Point(0, -29)
	        });

	        autocomplete.addListener('place_changed', function() {
	          infowindow.close();
	          marker.setVisible(false);
	          var place = autocomplete.getPlace();
	          if (!place.geometry) {
	            // User entered the name of a Place that was not suggested and
	            // pressed the Enter key, or the Place Details request failed.
	            window.alert("No details available for input: '" + place.name + "'");
	            return;
	          }

	          // If the place has a geometry, then present it on a map.
	          if (place.geometry.viewport) {
	            map.fitBounds(place.geometry.viewport);
	          } else {
	            map.setCenter(place.geometry.location);
	            map.setZoom(17);  // Why 17? Because it looks good.
	          }
	          marker.setPosition(place.geometry.location);
	          queryPostions = place.geometry.location;
	          marker.setVisible(true);

	          var address = '';
	          if (place.address_components) {
	            address = [
	              (place.address_components[0] && place.address_components[0].short_name || ''),
	              (place.address_components[1] && place.address_components[1].short_name || ''),
	              (place.address_components[2] && place.address_components[2].short_name || '')
	            ].join(' ');
	          }
	          // alert(place.geometry.location);
	          infowindowContent.children['place-icon'].src = place.icon;
	          infowindowContent.children['place-name'].textContent = place.name;
	          infowindowContent.children['place-address'].textContent = address;
	          // $('#modal-body').text('Do you want to add this location?');
	          $('#locationName').text(place.name);
	          $('#locationAddress').text(address);
	          $('#locationNameLabel').show();
	          $('#myModal').modal('show');
	          infowindow.open(map, marker);
	        });


	        google.maps.event.addListener(map, 'click', function(event) {
	          marker.setVisible(false);
	          marker.setPosition(event.latLng);
	          marker.setVisible(true);

	          var geocoder = new google.maps.Geocoder;
	          geocoder.geocode({'location': event.latLng}, function(results, status) {
	            if (status === 'OK') {
	              if (results[0]) {
	                queryPostions = event.latLng;
	                $('#locationName').text('');
	                $('#locationNameLabel').hide();
	                $('#locationAddress').text(results[0].formatted_address);
	                $('#myModal').modal('show');
	              }
	            }
	          });
	        });


	        google.maps.event.addListener(marker, 'click', function() {
	          infowindow.open(map,marker);
	        });

	        //Resize Function
	        google.maps.event.addDomListener(window, "resize", function() {
	          var center = map.getCenter();
	          google.maps.event.trigger(map, "resize");
	          map.setCenter(center);
	        });

	      // google.maps.event.addDomListener(window, 'load', initialize);
	        // Sets a listener on a radio button to change the filter type on Places
	        // Autocomplete.
	        function setupClickListener(id, types) {
	          var radioButton = document.getElementById(id);
	          radioButton.addEventListener('click', function() {
	            autocomplete.setTypes(types);
	          });
	        }

	        setupClickListener('changetype-all', []);
	        setupClickListener('changetype-address', ['address']);
	        setupClickListener('changetype-establishment', ['establishment']);
	        setupClickListener('changetype-geocode', ['geocode']);

	        document.getElementById('use-strict-bounds')
	            .addEventListener('click', function() {
	              console.log('Checkbox clicked! New state=' + this.checked);
	              autocomplete.setOptions({strictBounds: this.checked});
	            });
	      }
	    </script>
	    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD0wCz8TGxk7jbiXAPSxPs9F7sNRpLz6gA&libraries=places&callback=initMap"
	        async defer></script>
