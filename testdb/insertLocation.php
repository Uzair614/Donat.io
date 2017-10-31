<?php
if($_SERVER["REQUEST_METHOD"]=="POST") {
	require_once 'connection.php';
	insertLocation();
}


function insertLocation(){
	
	global $connect;

	$response = $_POST["Centre"];

	$json_a = json_decode($response, true);
	// INSERT INTO Centre(cname, latitude, longitutde) values (Edhi Centre, 24.897921,67.068203);
	$name = $json_a['name'];
	$lat = $json_a['latitude'];
	$lng = $json_a['longitude'];
	$types = $json_a['arr'];

	$query = "	INSERT INTO Centre(cname, latitude, longitude) 
				VALUES ('$name','$lat','$lng');";

	mysqli_query($connect, $query) or die (mysqli_error($connect));
	

	foreach ($types as $type) {
		$fk_query = "	INSERT INTO CentreType (centreType, cID) 
						VALUES
    					( '$type', (SELECT cID from Centre WHERE latitude='$lat' AND longitude = '$lng') );";
		echo $fk_query;
    	mysqli_query($connect, $fk_query) or die (mysqli_error($connect));
	}
	
	echo "Location added successfully";

	mysqli_close($connect);
}

?>