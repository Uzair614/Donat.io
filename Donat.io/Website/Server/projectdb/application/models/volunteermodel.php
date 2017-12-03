<?php

use application\domain\admin;
use application\domain\volunteer;

class VolunteerModel
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

    public function getVolunteers($adminID)
    {
        $sql = "SELECT * FROM Volunteer JOIN Users on vID = uID WHERE adminID = :adminID order by approved";

        $query = $this->db->prepare($sql);
        $volunteerList = array();
        require_once 'application/domain_objects/volunteer.php';

        if($query->execute(array(':adminID' => $adminID))) {
          while($row = $query->fetchObject()) {
            $volunteer = new volunteer($row);
            $volunteerList[] =$volunteer;
          }
        }

        return $volunteerList;
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

    public function getRequester($dID)
    {
        // clean the input from javascript code for example
        $sql = "SELECT * FROM Users JOIN DonationRequest ON uID = requesterID WHERE dID = :dID";

        $query = $this->db->prepare($sql);
        $query->execute(array(':dID' => $dID));

        if($results = $query->fetch()) {
          require_once 'application/domain_objects/volunteer.php';
          return new volunteer($results, "Requester");

        }
    }

    public function updateVolunteer($params, $adminID)
    {

      $action = $params->action;
      if($action == "A") {
          $sql = "UPDATE Volunteer SET approved = 1 WHERE vID = :vID AND adminID = :adminID";
      } else if ($action == "D"){
          $sql = "DELETE FROM Volunteer WHERE vID = :vID AND adminID = :adminID";
      } else if ($action == "R") {
          $sql = "UPDATE Volunteer SET approved = 0 WHERE vID = :vID AND adminID = :adminID";
      }

        $query = $this->db->prepare($sql);
        $query->execute(array(':adminID' => $adminID, ':vID' => $params->vID));
    }
}
