<?php

use application\domain\volunteer;
use application\domain\donationrequest;

class RequestModel
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

    public function getRequests($adminID)
    {

        $sql = "SELECT * FROM DonationRequest WHERE approvedBy is NULL OR approvedBy = :adminID ORDER BY approvedBy";
        $sqlRequester = "SELECT * FROM users WHERE uID = :uID";

        $query = $this->db->prepare($sql);

        $volunteerList = array();
        require_once 'application/domain_objects/donationrequest.php';
        require_once 'application/domain_objects/volunteer.php';

        if($query->execute(array(':adminID' => $adminID))) {
          while($row = $query->fetchObject()) {
            $donationrequest = new donationrequest($row);
            $donationRequestList[] =$donationrequest;
          }
        }
        // die(var_dump($donationRequestList));
        return $donationRequestList;
    }

    public function updateRequest($params, $adminID)
    {

      $action = $params->action;
      if($action == "A") {
          $sql = "UPDATE DonationRequest SET approvedBy = :adminID WHERE dID = :dID";
      } else if ($action == "D"){
          $sql = "DELETE FROM DonationRequest WHERE dID = :dID";
      } else if ($action == "R") {
          $sql = "UPDATE DonationRequest SET approvedBy = null WHERE dID = :dID";
      }

        $query = $this->db->prepare($sql);
        if($action == "A") {
          $query->execute(array(':adminID' => $adminID, ':dID' => $params->dID));
        } else {
          $query->execute(array(':dID' => $params->dID));
        }
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
         // $admin;
    }

    /**
     * Delete a song in the database
     * Please note: this is just an example! In a real application you would not simply let everybody
     * add/update/delete stuff!
     * @param int $song_id Id of song
     */
    public function deleteSong($song_id)
    {
        $sql = "DELETE FROM song WHERE id = :song_id";
        $query = $this->db->prepare($sql);
        $query->execute(array(':song_id' => $song_id));
    }
}
