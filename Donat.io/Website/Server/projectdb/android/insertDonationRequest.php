<?php
if($_SERVER["REQUEST_METHOD"]=="POST") {
	require_once 'connection.php';
	insertRequest();
}


function insertRequest(){

	$param1 = json_decode($_POST["param1"]);
	// echo($_POST["param1"]);

	$sql = "UPDATE Users SET phone = :phone , CNIC = :CNIC WHERE email = :email";
	$query = Connect::getInstance()->prepare($sql);
	$query->execute(array(':phone' => $param1->phone, ':CNIC' => $param1->cnic, ':email' => $param1->email));

	$sql = "SELECT uID FROM Users WHERE email = :email";
	$query = Connect::getInstance()->prepare($sql);
	$query->execute(array(':email' => $param1->email));

	if($results = $query->fetchObject()) {
			$uID = $results->uID;
	}

	$sql = "INSERT INTO DonationRequest (requesterID, dType, address, other) values (:requesterID, :dType, :address, :other)";
	$query = Connect::getInstance()->prepare($sql);
	$query->execute(array(':requesterID' => $uID, ':dType' => $param1->dType, ':address' => $param1->address, ':other' => $param1->other));
}

?>
