<?php

use application\domain\centre;
// use application\domain\volunteer;

class CentreModel
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

    public function registerCentre($params, $adminID)
    {
        $sqlBranch = "INSERT INTO CentreBranches (adminID) VALUES (:adminID)";
        $query = $this->db->prepare($sqlBranch);
        if($query->execute(array(':adminID' => $adminID))) {
          $cID = $this->db->lastInsertID();
          $sqlCentre = "INSERT INTO Centre (cID, cName, latitude, longitude) VALUES (:cID, :cName, :latitude, :longitude)";

          $query = $this->db->prepare($sqlCentre);
          if($query->execute(array(':cID' => $cID, ':cName' => $params->cName, ':latitude' =>  $params->latitude, ':longitude' =>  $params->longitude))) {

            $typeArray = json_decode($params->queryInsertType);
            foreach($typeArray as $type) {
              $sqlCentreType = "INSERT INTO CentreType (cID, centreType) VALUES (:cID, :centreType)";

              $query2 = $this->db->prepare($sqlCentreType);
              $query2->execute(array(':cID' => $cID, ':centreType' => $type));
            }
          }
        }
    }

    public function getLocations($adminID)
    {
        $sql = "SELECT * FROM CentreBranches NATURAL JOIN Centre NATURAL JOIN CentreType WHERE adminID = :adminID";

        $query = $this->db->prepare($sql);
        $centresList = array();
        require_once 'application/domain_objects/centre.php';

        if($query->execute(array(':adminID' => $adminID))) {
          while($row = $query->fetchObject()) {
            $centre = new centre($row);
            $centreList[] =$centre;
          }
        }

        return $centreList;
    }

    public function deleteCentre($params, $adminID)
    {
      $sql = "DELETE FROM CentreType WHERE cID = :cID";
      $query = $this->db->prepare($sql);
      $query->execute(array(':cID' => $params->cID));

      $sql = "DELETE FROM Centre WHERE cID = :cID";
      $query = $this->db->prepare($sql);
      $query->execute(array(':cID' => $params->cID));

      $sql = "DELETE FROM CentreBranches WHERE cID = :cID";
      $query = $this->db->prepare($sql);
      $query->execute(array(':cID' => $params->cID));
    }

}
