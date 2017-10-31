<?php

define('hostname', 'localhost');
define('user', 'root');
define('password', '');
define('databaseName', 'testdb');

$connect = mysqli_connect(hostname, user, password, databaseName);
?>