<?php



if($_SERVER["REQUEST_METHOD"]=="POST") {

	require_once 'connection.php';

	createDonationRequest();

}



function createDonationRequest(){
	echo $_POST["param1"];

/*
	global $connect;

	$email = $_POST["email"];

	$latitude = $_POST["latitude"];

	$longitude = $_POST["longitude"];

	$query = " SELECT uID from Users WHERE email = '$email'; ";
	
	$result = mysqli_query($connect, $query);

	$rID = $_POST["requesterID"];

	$dtype = $_POST["dType"];


	$query = "Insert into DonationRequest(requesterID,dType,latitude,longitude) values ('$result','$lastname','$latitude','$longitude');";
	

	mysqli_query($connect, $query) or die (mysqli_error($connect));

	mysqli_close($connect);

*/}



?>