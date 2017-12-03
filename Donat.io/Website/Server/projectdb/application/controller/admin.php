<?php

/**
 * Class Home
 *
 * Please note:
 * Don't use the same name for class and method, as this might trigger an (unintended) __construct of the class.
 * This is really weird behaviour, but documented here: http://php.net/manual/en/language.oop5.decon.php
 *
 */



class Admin extends Controller
{

    public function index()
    {
      $this->session_check();
      $user_model = $this->loadModel('UserModel');
      $adminobj = $user_model->retreiveUser($_SESSION["admin_id"]);
      require 'application/views/_templates/admin/header.php';
      require 'application/views/admin/index.php';
      require 'application/views/_templates/admin/footer.php';
      require 'application/views/js/admin/index.php';
    }

    public function volunteers()
    {
        $this->session_check();
        $volunteer_model = $this->loadModel('VolunteerModel');
        $volunteerList = $volunteer_model->getVolunteers($_SESSION["admin_id"]);

        require 'application/views/_templates/admin/header.php';
        require 'application/views/admin/volunteers.php';
        require 'application/views/_templates/admin/footer.php';
        require 'application/views/js/admin/volunteers.php';
    }

    public function requests()
    {
        $this->session_check();

        $request_model = $this->loadModel('RequestModel');
        $donationRequestList = $request_model->getRequests($_SESSION["admin_id"]);


        $requester_model = $this->loadModel('VolunteerModel');
        foreach ($donationRequestList as $donation) {
          $requester = $requester_model->getRequester($donation->getdID());
          $donation->setRequester($requester);
        }
        require 'application/views/_templates/admin/header.php';
        require 'application/views/admin/requests.php';
        require 'application/views/_templates/admin/footer.php';
        require 'application/views/js/admin/requests.php';
    }

    public function notification()
    {
        $this->session_check();

        $notification_model = $this->loadModel('NotificationModel');
        $notificationsList = $notification_model->getNotifications($_SESSION["admin_id"]);

        require 'application/views/_templates/admin/header.php';
        require 'application/views/admin/notification.php';
        require 'application/views/_templates/admin/footer.php';
        require 'application/views/js/admin/notification.php';
    }

    public function map()
    {
        $this->session_check();
        require 'application/views/_templates/admin/header.php';
        require 'application/views/admin/map.php';
        require 'application/views/_templates/admin/footer.php';
        require 'application/views/js/admin/map.php';
    }

    public function locations()
    {
        $this->session_check();
        $centre_model = $this->loadModel('CentreModel');
        $centreList = $centre_model->getLocations($_SESSION["admin_id"]);

        require 'application/views/_templates/admin/header.php';
        require 'application/views/admin/locations.php';
        require 'application/views/_templates/admin/footer.php';
        require 'application/views/js/admin/locations.php';
    }

    public function ajaxCenterRequest() {

        $this->session_check();
        $params = json_decode($_REQUEST['params']);
        $centre_model = $this->loadModel('CentreModel');
        $centre_model->registerCentre($params, $_SESSION["admin_id"]);
    }

    public function ajaxDonationRequest() {

        $this->session_check();
        $params = json_decode($_REQUEST['params']);
        $request_model = $this->loadModel('RequestModel');
        $request_model->updateRequest($params, $_SESSION["admin_id"]);
    }

    public function ajaxVolunteerRequest() {

        $this->session_check();
        $params = json_decode($_REQUEST['params']);
        $volunteer_model = $this->loadModel('VolunteerModel');
        $volunteer_model->updateVolunteer($params, $_SESSION["admin_id"]);
    }

    public function ajaxNotification() {

        $this->session_check();
        $params = json_decode($_REQUEST['params']);
        $notification_model = $this->loadModel('NotificationModel');
        $notification_model->insertNotification($params, $_SESSION["admin_id"]);
    }

    public function deleteNotification() {

        $this->session_check();
        $params = json_decode($_REQUEST['params']);
        $notification_model = $this->loadModel('NotificationModel');
        $notification_model->deleteNotification($params, $_SESSION["admin_id"]);
    }

    public function ajaxCentreDelete() {

        $this->session_check();
        $params = json_decode($_REQUEST['params']);
        $centre_model = $this->loadModel('CentreModel');
        $centre_model->deleteCentre($params, $_SESSION["admin_id"]);
    }

    public function logOut() {
      // session_start();
      session_destroy();
      header('location: ' . URL . 'home/index');
    }

    private function session_check() {
    // if(session_status()!=PHP_SESSION_ACTIVE)
        // session_start();
      if(!array_key_exists('admin_id',$_SESSION) || empty($_SESSION['admin_id'])) {
        session_destroy();
        header('location: ' . URL . 'home/index');
      }
    }
}
