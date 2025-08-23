package com.example.hosptiAl.Dto;

import com.example.hosptiAl.Model.User.Role;

public class DoctorDao {
	private String userName;
	private String passWord;
	private String specialization; 
	private String contactNumber ;
	private String availabilitySchedule;
	private Role role;
	public DoctorDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public DoctorDao(String userName, String passWord, String specialization, String contactNumber,
			String availabilitySchedule, Role role) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.specialization = specialization;
		this.contactNumber = contactNumber;
		this.availabilitySchedule = availabilitySchedule;
		this.role = role;
	}

	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAvailabilitySchedule() {
		return availabilitySchedule;
	}
	public void setAvailabilitySchedule(String availabilitySchedule) {
		this.availabilitySchedule = availabilitySchedule;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
