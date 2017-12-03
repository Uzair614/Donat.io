<?php

use application\domain\notification;


class NotificationModel
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

    public function insertNotification($params, $adminID)
    {
      $sql = "INSERT INTO Notifications (adminID, message, date, sendToAll) values (:adminID, :message, NOW(), :audience)";

      // die(var_dump($params));
      $query = $this->db->prepare($sql);
      
      $query->execute(array(':adminID' => $adminID, ':message' => $params->message, ':audience' => $params->audience));
      // if (!$query) {
      //   echo "\nPDO::errorInfo():\n";
      //   var_dump($this->$db->errorInfo());
      // }
    }

    public function getNotifications($adminID)
    {
        $sql = "SELECT * FROM Notifications WHERE adminID = :adminID ORDER BY date desc";

        $query = $this->db->prepare($sql);
        $notificationsList = array();
        require_once 'application/domain_objects/notification.php';

        if($query->execute(array(':adminID' => $adminID))) {
          while($row = $query->fetchObject()) {
            $notification = new notification($row);
            $notificationsList[] =$notification;
          }
        }

        return $notificationsList;
    }

    public function deleteNotification($params, $adminID)
    {
      $sql = "DELETE FROM Notifications WHERE mID = :mID";

      $query = $this->db->prepare($sql);
      $query->execute(array(':mID' => $params->mID));
    }

}
