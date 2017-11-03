<?php
if($_SERVER["REQUEST_METHOD"]=="POST") {
	require_once 'connection.php';
	require_once 'C:\xampp\htdocs\vendor\autoload.php';
	authentication();
}


function authentication(){

	$id_token = $_POST["param1"];
	$CLIENT_ID = "975918568691-h92r3lfs232j7sgkqt7uhut746il1v6b.apps.googleusercontent.com";
	$client = new Google_Client(['client_id' => $CLIENT_ID]);
	$payload = $client->verifyIdToken($id_token);
	if ($payload) {
	  $userid = $payload['sub'];
	  echo ("success");
	  // If request specified a G Suite domain:
	  //$domain = $payload['hd'];
	} else {
	  // Invalid ID token
	}
	

}

?>