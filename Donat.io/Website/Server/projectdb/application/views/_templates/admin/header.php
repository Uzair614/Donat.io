<?php
// use application\domain\admin;
// require 'application/domain_objects/admin.php';

// // $adminobj = $_SESSION['adminobj'];
// $user_model = $this->loadModel('UserModel');
// $adminobj = $user_model->retreiveUser($_SESSION["admin_id"]);
// die(var_dump($_SESSION['adminobj']));
?>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="<?php echo URL; ?>assets/img/apple-icon.png" />
	<link rel="icon" type="image/png" href="<?php echo URL; ?>assets/img/favicon.png" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>Donat.io</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <!-- Bootstrap core CSS     -->
    <link href="<?php echo URL; ?>assets/css/bootstrap.min.css" rel="stylesheet" />

    <!--  Material Dashboard CSS    -->
    <link href="<?php echo URL; ?>assets/css/material-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<?php echo URL; ?>assets/css/demo.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons' rel='stylesheet' type='text/css'>
</head>

<body>

  <div class="wrapper">

	    <div class="sidebar" data-color="purple" data-image="<?php echo URL; ?>assets/img/sidebar-1.jpg">
			<!--
		        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

		        Tip 2: you can also add an image using data-image tag
		    -->




					    	<div class="sidebar-wrapper">
					            <ul class="nav">
					                <li id="indexNav">
					                    <a href="<?php echo URL; ?>admin/index">
					                        <i class="material-icons">person</i>
					                        <p>User Profile</p>
					                    </a>
					                </li>
					                <li id="volunteersNav">
					                    <a href="<?php echo URL; ?>admin/volunteers">
					                        <i class="material-icons">content_paste</i>
					                        <p>View Volunteers</p>
					                    </a>
					                </li>
					                <li id="requestsNav">
					                    <a href="<?php echo URL; ?>admin/requests">
					                        <i class="material-icons">library_books</i>
					                        <p>View Donation Requests</p>
					                    </a>
					                </li>
					                <li id="mapsNav">
					                    <a href="<?php echo URL; ?>admin/map">
					                        <i class="material-icons">location_on</i>
					                        <p>Add Donation Centres</p>
					                    </a>
					                </li>
													<li id="locationsNav">
					                    <a href="<?php echo URL; ?>admin/locations">
																<i class="fa fa-globe" aria-hidden="true"></i>
					                        <p>View your locations</p>
					                    </a>
					                </li>
					                <li id="notificationsNav">
					                    <a href="<?php echo URL; ?>admin/notification">
					                        <i class="material-icons text-gray">notifications</i>
					                        <p>Notifications</p>
					                    </a>
					                </li>
													<li id="logOutNav">
					                    <a href="<?php echo URL; ?>admin/logOut">
																<i class="fa fa-sign-out" aria-hidden="true"></i>
					                        <p>Log Out</p>
					                    </a>
					                </li>
					            </ul>
					    	</div>
					    </div>

					    <div class="main-panel">
							<nav class="navbar navbar-transparent navbar-absolute">
								<div class="container-fluid">
									<div class="col-md-6"  id="dash-header">
										<h2>Donat.io</h2>
									</div>
								</div>
							</nav>
