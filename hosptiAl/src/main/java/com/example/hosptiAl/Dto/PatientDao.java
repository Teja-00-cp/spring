package com.example.hosptiAl.Dto;

import java.time.LocalDate;

import com.example.hosptiAl.Model.User.Role;


public class PatientDao {
	private String userName;
	private String passWord;
	private LocalDate dateOfBirth;
	private String gender;
	private String contactNumber;
	private String address;
	private String medicalHistory ;
	private Role role;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "PatientDao [userName=" + userName + ", passWord=" + passWord + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", contactNumber=" + contactNumber + ", address=" + address
				+ ", medicalHistory=" + medicalHistory + "]";
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
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public PatientDao(String userName, String passWord, LocalDate dateOfBirth, String gender, String contactNumber,
			String address, String medicalHistory) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.address = address;
		this.medicalHistory = medicalHistory;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

}
