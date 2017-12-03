<?php
// error_reporting(E_ERROR);
namespace application\domain;

class centre{

	private $cID;
	private $cName;
	private $cType;

	// The Constructor For the class Volunteer
	public function __construct($row){

		$this->cID = $row->cID;
		$this->cName = $row->cName;
		$this->cType = $row->centreType;
	}

	// All Variables' Getters


	public function getcID(){
		return $this->cID;
	}

	public function getName(){
		return $this->cName;
	}

	public function getCType(){
		return $this->cType;
	}


}

?>
