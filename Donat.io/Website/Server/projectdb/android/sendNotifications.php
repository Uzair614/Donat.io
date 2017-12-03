<?php
if($_SERVER["REQUEST_METHOD"]=="GET") {
	require_once 'connection.php';
	sendNotifications();
}


function sendNotifications(){

	$email = $_GET["param1"];
	$sql = "SELECT DISTINCT message, orgName, sendToAll AS Audience FROM Notifications NATURAL JOIN Admin
	NATURAL JOIN Volunteer JOIN Users u ON vID = uID WHERE u.email = :email OR sendTOAll = 1
	ORDER BY date desc";

	$temp_array = array();

	$query = Connect::getInstance()->prepare($sql);

	if($query->execute(array(':email' => $email))) {
		while($row = $query->fetchObject()) {
			$temp_array[] = $row;
		}
	}
	// echo($email);
  echo json_encode($temp_array);
}

?>
