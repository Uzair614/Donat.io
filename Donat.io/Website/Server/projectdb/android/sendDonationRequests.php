<?php
if($_SERVER["REQUEST_METHOD"]=="GET") {
	require_once 'connection.php';
	sendDonationRequests();
}


function sendDonationRequests(){


	$sql = "SELECT * FROM DonationRequest JOIN Users on uID = requesterID JOIN Admin ON adminID = approvedBy
	WHERE approvedBy != 0 ORDER BY dID desc";
	$temp_array = array();
  foreach (Connect::getInstance()->query($sql) as $row) {
 	 $temp_array[] = $row;
  }
  echo json_encode($temp_array);
}

?>
