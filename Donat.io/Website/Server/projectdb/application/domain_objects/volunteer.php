<?php
// error_reporting(E_ERROR);
namespace application\domain;

class volunteer{

	private $vID;
	private $email;
	private $name;
	private $phone;
	private $CNIC;
	private $approved;

	// The Constructor For the class Volunteer
	public function __construct($row, $user = "Volunteer"){

		$this->email = $row->email;
		$this->name = $row->name;
		$this->phone = $row->phone;
		$this->CNIC = $row->CNIC;
		if($user == "Volunteer") {
			$this->approved = $row->approved;
			$this->vID = $row->vID;
		}
	}

	// All Variables' Getters
	public function getvID(){
		return $this->vID;
	}

	public function getEmail(){
		return $this->email;
	}

	public function getName(){
		return $this->name;
	}

	public function getPhone(){
		return $this->phone;
	}

	public function getCNIC(){
		return $this->CNIC;
	}

	public function getApproved(){
		return $this->approved;
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
