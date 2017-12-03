<?php

use application\domain\admin;

class UserModel
{
    /**
     * Every model needs a database connection, passed to the model
     * @param object $db A PDO database connection
     */
    function __construct($db) {
        try {
            $this->db = $db;
        } catch (PDOException $e) {
            exit('Database connection could not be established.');
        }
    }


    public function logInUser($username, $pass)
    {
        // clean the input from javascript code for example
        $username = strip_tags($username);
        $pass = strip_tags($pass);

        $sql = "SELECT * FROM Admin WHERE username = :username AND password = :pass";

        $query = $this->db->prepare($sql);
        // echo '<script>console.log("In query '. $sql .' ")</script>';
        $query->execute(array(':username' => $username, ':pass' => $pass));

        // var_dump($results);
        if($results = $query->fetch()) {
          require_once 'application/domain_objects/admin.php';
          return new admin($results);
        }
        // return $admin;
        // var_dump($admin);
    }

    public function signUpUser($username, $pass, $email, $orgName, $regNo)
    {
        // clean the input from javascript code for example
        $username = strip_tags($username);
        $pass = strip_tags($pass);
        $email = strip_tags($email);
        $orgName = strip_tags($orgName);
        $regNo = strip_tags($regNo);

        $sqlSelect = "SELECT * FROM Admin WHERE regNo = :regNo";
       	$query = $this->db->prepare($sqlSelect);
      	$query->execute(array(':regNo' => $regNo));

      	if($row = $query->fetchObject()) {
      		echo json_encode($row);

       } else {
      	 	$sqlInsert = "INSERT INTO Admin (username, password, email, RegNo, orgName) values (:username, :pass, :email, :regNo, :orgName)";
        	$query = $this->db->prepare($sqlInsert);
       		$query->execute(array(':username' => $username, ':pass' => $pass, ':email' => $email, ':regNo' => $regNp, ':orgName' => $orgName));

      	 	$query = $this->db->prepare($sqlSelect);
      		$query->execute(array(':email' => $payload['email']));
      		if($row = $query->fetchObject()) {
      			echo json_encode($row);
      		}
       }


        $sql = "SELECT * FROM Admin WHERE username = :username AND password = :pass";

        $query = $this->db->prepare($sql);
        // echo '<script>console.log("In query '. $sql .' ")</script>';
        $query->execute(array(':username' => $username, ':pass' => $pass));

        // var_dump($results);
        if($results = $query->fetch()) {
          require_once 'application/domain_objects/admin.php';
          return new admin($results);
        }
        // return $admin;
        // var_dump($admin);
    }

    public function signUpAjax($params)
    {
        $username = 1;
        $email = 1;
        $regNo = 1;
        $sqlName = "SELECT * FROM Admin WHERE username = :username";
       	$query = $this->db->prepare($sqlName);
      	$query->execute(array(':username' => $params->username));
      	if($row = $query->fetchObject()) {
      		$username = 0;
        }

        $sqlEmail = "SELECT * FROM Admin WHERE email = :email";
        $query = $this->db->prepare($sqlEmail);
        $query->execute(array(':email' => $params->email));
        if($row = $query->fetchObject()) {
          $email = 0;
        }

        $sqlRegNo = "SELECT * FROM Admin WHERE regNo = :regNo";
        $query = $this->db->prepare($sqlRegNo);
        $query->execute(array(':regNo' => $params->regNo));
        if($row = $query->fetchObject()) {
          $regNo = 0;
        }
        //
        if(($username == 1) AND ($email == 1) AND ($regNo == 1)) {
          // echo "up in here";
          $sqlInsert = "INSERT INTO Admin (username, password, email, RegNo, orgName) values (:username, :pass, :email, :regNo, :orgName)";
        	$query = $this->db->prepare($sqlInsert);
       		$query->execute(array(':username' => $params->username, ':pass' => $params->pass,
            ':email' => $params->email, ':regNo' => $params->regNo, ':orgName' => $params->orgName));
        }

        $result = array("username" => $username, "email" => $email, "regNo" => $regNo);
        // $result = array($username, $email, $regNo);
        // return $result;
        // return json_encode($result);
        echo json_encode($result, JSON_FORCE_OBJECT);
        // return $params;
    }

    public function retreiveUser($adminID)
    {
        // clean the input from javascript code for example
        $sql = "SELECT * FROM Admin WHERE adminID = :adminID";

        $query = $this->db->prepare($sql);
        $query->execute(array(':adminID' => $adminID));

        if($results = $query->fetch()) {
          require_once 'application/domain_objects/admin.php';
          // die(var_dump(new admin($results)));
          return new admin($results);

        }

    }
}
