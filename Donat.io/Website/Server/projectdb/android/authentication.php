<?php
if($_SERVER["REQUEST_METHOD"]=="POST") {
	require_once 'connection.php';
	// require_once 'C:\xampp\htdocs\vendor\autoload.php';
	authentication();
}


function authentication(){

	$id_token = $_POST["param1"];
	// echo $id_token;

	$url = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=".$id_token;
	$xml = file_get_contents($url);
	// $payload = file_get_contents($url);
	// echo $xml;
	$CLIENT_ID = "975918568691-h92r3lfs232j7sgkqt7uhut746il1v6b.apps.googleusercontent.com";
	/*
	$client = new Google_Client(['client_id' => $CLIENT_ID]);
	$payload = $client->verifyIdToken($id_token);

	*/
	$payload = json_decode($xml, true);
	// echo $payload;
	if ($payload["aud"] == $CLIENT_ID)
	{
	  $userid = $payload['sub'];
		// echo "Here";

		$sqlSelect = "SELECT name, email, CNIC, phone AS phoneNo FROM Users WHERE email = :email";
	 	$query = Connect::getInstance()->prepare($sqlSelect);
		$query->execute(array(':email' => $payload['email']));


		if($row = $query->fetchObject()) {
			echo json_encode($row);

	 } else {
		 	$sqlInsert = "INSERT INTO Users (email, name) values (:email, :name)";
	  	$query2= Connect::getInstance()->prepare($sqlInsert);
	 		$query2->execute(array(':email' => $payload['email'], ':name' => $payload['name']));

			// echo $payload['email'];
		 	$query = Connect::getInstance()->prepare($sqlSelect);
			$query->execute(array(':email' => $payload['email']));
			if($row = $query->fetchObject()) {
				echo json_encode($row);
			}
	 }


	  // echo ($payload['email']);
	  // If request specified a G Suite domain:
	  //$domain = $payload['hd'];
	} else {
	  // Invalid ID token
		echo "Failed";
	}


}

?>
