<?php
if($_SERVER["REQUEST_METHOD"]=="GET") {
	require_once 'connection.php';
	sendUser();
}


function sendUser(){
	
	global $connect;
	
	// $firstname = $_POST["firstname"];
	// $lastname = $_POST["lastname"];
	// $id = $_POST["id"];

	$query = " SELECT * from Users; ";
	
	$result = mysqli_query($connect, $query);
	$temp_array = array();

	if(mysqli_num_rows($result)) {
		while ($row = mysqli_fetch_assoc($result)) {
			$temp_array[] = $row;
		}
	}

	header('Content-Type: application/json');
	echo json_encode(array('Users' => $temp_array ));
	mysqli_close($connect);
}

?>