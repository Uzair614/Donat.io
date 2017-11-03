<?php
if($_SERVER["REQUEST_METHOD"]=="GET") {
	require_once 'connection.php';
	sendLocation();
}


function sendLocation(){
	
	global $connect;
	
	$typesPost = $_GET["param1"];
	$types = json_decode($typesPost);
	$param = "";
	$loop = 0;

	foreach ($types as $type) {
		if($loop != 0)
			$param = $param . " OR ";
		else
			$loop = $loop+1;
		$param = $param . "centreType = " . "'" . "$type" . "'";
		// echo $type;
	}

	$query = " 	SELECT DISTINCT cname AS name, latitude, longitude 
				FROM Centre
				NATURAL JOIN CentreType
				WHERE $param; ";
	
	$result = mysqli_query($connect, $query);
	
	$temp_array = array();

	if(mysqli_num_rows($result)) {
		while ($row = mysqli_fetch_assoc($result)) {
			$temp_array[] = $row;
		}
	}

	
	echo json_encode($temp_array);

	mysqli_close($connect);
}

?>