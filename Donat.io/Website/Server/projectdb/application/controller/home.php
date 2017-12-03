<?php

/**
 * Class Home
 *
 * Please note:
 * Don't use the same name for class and method, as this might trigger an (unintended) __construct of the class.
 * This is really weird behaviour, but documented here: http://php.net/manual/en/language.oop5.decon.php
 *
 */
class Home extends Controller
{
    /**
     * PAGE: index
     * This method handles what happens when you move to http://yourproject/home/index (which is the default page btw)
     */


    public function index()
    {
        // session_start();
        require 'application/views/_templates/home/header.php';
        require 'application/views/home/index.php';
        require 'application/views/_templates/home/footer.php';
    }


     public function signup()
     {
         require 'application/views/_templates/home/header.php';
         require 'application/views/home/signup.php';
         require 'application/views/_templates/home/footer.php';
         require 'application/views/js/home/signup.php';

     }


    public function formLogIn() {

      if (isset($_POST['form_Log_In'])) {
        // echo '<script>console.log("In form")</script>';
        // load model, perform an action on the model
        $user_model = $this->loadModel('UserModel');
        $admin = $user_model->logInUser($_POST["username"], $_POST["pass"]);
        if($admin) {
          $_SESSION["admin_id"] = $admin->getAdminID();
          // die(var_dump($_SESSION));
          header('location: '.URL.'admin/index');
        } else {
          header('location: '.URL.'home/index');
        }
      }
    }

    public function formSignUp() {

      if (isset($_POST['form_Sign_Up'])) {
        // echo '<script>console.log("In form")</script>';
        // load model, perform an action on the model
        die(var_dump($_POST));
        $user_model = $this->loadModel('UserModel');
        $admin = $user_model->signUpUser($_POST["username"], $_POST["pass"], $_POST["email"], $_POST["orgName"], $_POST["regNo"] );
        /*
        if($admin) {
          session_start();
          $_SESSION["admin_id"] = $admin->getAdminID();

          header('location: ' . URL . 'admin/index');
        } else {
          header('location: ' . URL . 'home/index');
        }
        */
        // alert("Hello");
      }
      // die(var_dump($_POST));

    }


    public function ajaxSignup() {

       
        $params = json_decode($_POST['params']);
        $user_model = $this->loadModel('UserModel');
        $retVal =  $user_model->signUpAjax($params);
        // echo "whats going on";
        // var_dump(($retVal));
        // // return json_decode($retVal);
        // return $retVal;

    }

}
