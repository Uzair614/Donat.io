<?php
if($_SERVER["REQUEST_METHOD"]=="GET") {
	require_once 'connection.php';
	getProfile();
}


function getProfile(){

	$email = $_GET["param1"];
	$sqlSelect = "SELECT name, email, CNIC, phone AS phoneNo FROM Users WHERE email = :email";
 	$query = Connect::getInstance()->prepare($sqlSelect);
	if($query->execute(array(':email' => $email))) {
		if($row = $query->fetchObject()) {
			echo json_encode($row);
	 	}
 	}
}

?>
