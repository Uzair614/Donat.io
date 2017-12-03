<?php
// error_reporting(E_ERROR);


namespace application\domain;

class admin
{
	private $adminID;
	private $username;
	private $password;
	private $cellNumber;
	private $orgName;
	private $RegNo;
	private $email;


	public function __construct($row){

				$this->adminID = (int)$row->adminID;
				$this->username = $row->username;
				$this->password = $row->password;
				$this->cellNumber = $row->cellNumber;
				$this->orgName = $row->orgName;
				$this->RegNo = $row->RegNo;
				// die(var_dump($this));
		}

	// All Variables' Getters
	public function getAdminID(){
		return $this->adminID;
	}

	public function getUsername(){
		return $this->username;
	}

	public function getPassword(){
		return $this->password;
	}

	public function getCellNumber(){
		return $this->cellNumber;
	}

	public function getOrgName(){
		return $this->orgName;
	}

	public function getRegNo(){
		return $this->RegNo;
	}
}

?>
