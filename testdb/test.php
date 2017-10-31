<?php
/**
 * Created by PhpStorm.
 * User: Saad
 * Date: 11/10/2017
 * Time: 3:02 PM
 */

/**
 * Created by PhpStorm.
 * User: Dell
 * Date: 9/21/2017
 * Time: 8:27 PM
 */


// Database Credentials

$dbhost = 'localhost';
$dbname = 'id3215521_projectdb';
$dbuser = 'id3215521_username';
$dbpass = 'database';



ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

$db = new PDO('mysql:host=' . $dbhost.';'. $dbname, $dbuser, $dbpass);

try {
// set the PDO error mode to exception
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
//    echo "Connected successfully";
} catch (PDOException $e) {
//    echo "Connection failed: " . $e->getMessage();
}

$stmt = $db->prepare("USE id3215521_projectdb;");
$stmt->execute();
$stmt = $db->prepare("SELECT * FROM User;");
$stmt->execute();
$result = $stmt->setFetchMode(PDO::FETCH_ASSOC);
foreach ($stmt->fetchAll() as $k => $v) {
    $hash = $v["FName"];
    echo $hash;
}


?>