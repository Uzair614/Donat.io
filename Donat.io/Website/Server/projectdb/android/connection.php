<?php
/*
define('hostname', );
define('user', );
define('password', '');
define('databaseName', 'testdb');
*/
class Connect {
	private static $instance;
	private $connection;
	public static function getInstance() {
		if(!isset(self::$instance)) {
			self::$instance = new self();
		}
		return self::$instance->connection;
	}

	private function __construct() {
		// 	error_reporting(0);
		// echo '<script>console.log("Connection constructed before '.get_class(self::$instance).'")</script>';
		// if(mysqli_connect_errno())
		// self::$instance->connection = new stdClass;

		$dsn =  'mysql:host=localhost;dbname=id3215521_projectdb';
		$this->connection = new PDO($dsn,'id3215521_username','database');

		// $this->connection = mysqli_connect("localhost", "root", "", "testdb");

		// $connectione = mysqli_connect("localhost", "root", "", "testdb");
		// echo '<script>console.log("Connection constructed after")</script>';

	}

}
/*
*/

// $connect = mysqli_connect("localhost", "root", "", "testdb");


// $dsn =  'mysql:host=localhost;dbname=testdb';
// $connect = new PDO($dsn,'root','');
// if(!$connect)
// 	echo '<script>console.log("Connection error2")</script>';


?>
