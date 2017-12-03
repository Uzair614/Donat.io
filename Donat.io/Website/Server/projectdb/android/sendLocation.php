<?php
if($_SERVER["REQUEST_METHOD"]=="GET") {
	require_once 'connection.php';
	sendLocation();
}


function sendLocation(){

	$sql = "SELECT DISTINCT cname AS name, latitude, longitude, centreType
						FROM Centre
						NATURAL JOIN CentreType";

	$temp_array = array();
	foreach (Connect::getInstance()->query($sql) as $row) {
		$temp_array[] = $row;
	}

	echo json_encode($temp_array);

}

?>
