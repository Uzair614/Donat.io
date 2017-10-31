<?php
if($_SERVER["REQUEST_METHOD"]=="POST") {
	require_once 'connection.php';
	sendLocationTest();
}


function sendLocationTest(){
	
	global $connect;
	//$result = $_POST[""];
	// $lastname = $_POST["lastname"];
	// $id = $_POST["id"];

	//$query = " SELECT * from Users; ";
	
	//$result = mysqli_query($connect, $query);
	/*
	$temp_array = array();

	if(mysqli_num_rows($result)) {
		while ($row = mysqli_fetch_assoc($result)) {
			$temp_array[] = $row;
		}
	}
*/
	// $response = array("Centre" => $_POST["Centre"]);
	$response = $_POST["Centre"];
	$json_a = json_decode($response, true);
	// INSERT INTO Centre(cname, latitude, longitutde) values (Edhi Centre, 24.897921,67.068203);
	$name = $json_a['name'];
	$lat = $json_a['latitude'];
	$lng = $json_a['longitude'];

	$query = "INSERT INTO Centre(cname, latitude, longitude) values ('$name','$lat','$lng');";

	echo "Location added successfully";

	mysqli_query($connect, $query) or die (mysqli_error($connect));

	mysqli_close($connect);
}

?>