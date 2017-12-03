<?php
// error_reporting(E_ERROR);
namespace application\domain;

class notification{

	private $mID;
	private $message;
	private $audience;
	private $date;


	// The Constructor For the class Notifications
	public function __construct($row){

		$this->mID = $row->mID;
		$this->message = $row->message;
		$this->date = $row->date;
		if($row->sendToAll == 0)
			$this->audience = "Volunteers";
		else
			$this->audience = "All Users";
	}

	// All Variables' Getters
	public function getmID(){
		return $this->mID;
	}

	public function getMessage(){
		return $this->message;
	}

	public function getDate(){
		return $this->date;
	}

	public function getAudience(){
		return $this->audience;
	}

}

?>
