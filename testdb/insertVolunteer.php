<?php



if($_SERVER["REQUEST_METHOD"]=="POST") {

	require_once 'connection.php';

	createUser();

}



function createuser(){

	

	global $connect;

	

	$firstname = $_POST["firstname"];

	$lastname = $_POST["lastname"];



	$query = "Insert into User(fname,lname) values ('$firstname','$lastname');";

	

	mysqli_query($connect, $query) or die (mysqli_error($connect));

	mysqli_close($connect);

}



?>