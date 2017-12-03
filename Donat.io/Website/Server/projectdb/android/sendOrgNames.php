<?php
if($_SERVER["REQUEST_METHOD"]=="GET") {
	require_once 'connection.php';
	sendOrgNames();
}


function sendOrgNames(){


	$sql = "SELECT orgName, adminID FROM Admin";
	$temp_array = array();
  foreach (Connect::getInstance()->query($sql) as $row) {
 	 $temp_array[] = $row;
  }
  echo json_encode($temp_array);
}

?>
