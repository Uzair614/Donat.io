<?php
if($_SERVER["REQUEST_METHOD"]=="POST") {
	require_once 'connection.php';
	profile();
}


function profile(){

	$param1 = json_decode($_POST["param1"]);
	// echo($param1->email);

	$sql = "UPDATE Users SET phone = :phone , CNIC = :CNIC WHERE email = :email";
	$query = Connect::getInstance()->prepare($sql);
	$query->execute(array(':phone' => $param1->phone, ':CNIC' => $param1->cnic, ':email' => $param1->email));
}

?>
