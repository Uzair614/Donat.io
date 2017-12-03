<?php
// error_reporting(E_ERROR);
namespace application\domain;

class DonationRequest{
	private $dID;
	private $requester;
	private $dType;
	private $address;
	private $other;
	private $approvedBy;

	// The Constructor For the class DonationRequest
	public function __construct($row){
		// session_start();
		$this->dID = $row->dID;
		$this->dType = $row->dType;
		$this->address = $row->address;
		$this->other = $row->other;
		$this->approvedBy = $row->approvedBy;
	}
	// All Variables' Getters
	public function setRequester($requester){
		return $this->requester = $requester;
	}

	public function getdID(){
		return $this->dID;
	}

	public function getRequester(){
		return $this->requester;
	}

	public function getDType(){
		return $this->dType;
	}

	public function getAddress(){
		return $this->address;
	}

	public function getOther(){
		return $this->other;
	}

	public function getApprovedBy(){
		return $this->approvedBy;
	}



	// Utility Functions

	// public function getCourses(){
	// 	require 'connect.php';
	// 	$course_query = "SELECT * FROM stu_teach_course WHERE stu_id='".$this->id."'";
	// 	$courses = array();
	// 	if ($result = $db->query($course_query)){
	// 		if ($count = $result->num_rows){
	// 			$i = 0;
	// 			while ($row = $result->fetch_object()) {
	// 				$courses[$i] = $row->course_code;
	// 				$i++;
	// 			}
	// 			$result->free();
	// 		}
	// 	}
	// 	return $courses;
	// }

	// public function getTeachers(){
	// 	require 'connect.php';
	// 	$teacher_query = "SELECT teach_id FROM stu_teach_course WHERE stu_id='".$this->id."'";
	// 	$teachers = array();
	// 	if ($result = $db->query($teacher_query)){
	// 		if ($count = $result->num_rows){
	// 			$i = 0;
	// 			while ($row = $result->fetch_object()) {
	// 				$teachers[$i] = $row->teach_id;
	// 				$i++;
	// 			}
	// 			$result->free();
	// 		}
	// 	}
	// 	return $teachers;
	// }
}

?>
