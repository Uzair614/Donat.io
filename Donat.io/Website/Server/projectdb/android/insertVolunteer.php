<?php
if($_SERVER["REQUEST_METHOD"]=="POST") {
	require_once 'connection.php';
	insertVolunteer();
}


function insertVolunteer(){

	$param1 = json_decode($_POST["param1"]);
	// echo($param1->email);

	$sql = "UPDATE Users SET phone = :phone , CNIC = :CNIC WHERE email = :email";
	$query = Connect::getInstance()->prepare($sql);
	$query->execute(array(':phone' => $param1->phone, ':CNIC' => $param1->cnic, ':email' => $param1->email));

	$sql = "SELECT uID FROM Users WHERE email = :email";
	$query = Connect::getInstance()->prepare($sql);
	$query->execute(array(':email' => $param1->email));

	if($results = $query->fetchObject()) {
			$uID = $results->uID;
	}

	$sql = "INSERT INTO Volunteer (vID, adminID) values (:vID, :adminID)";
	$query = Connect::getInstance()->prepare($sql);
	$query->execute(array(':vID' => $uID, ':adminID' => $param1->org));
}

?>
