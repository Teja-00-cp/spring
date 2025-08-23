package com.example.hosptiAl.Model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Patient {
	
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long patientId ;
	public Patient() {
		super();
	}
//	@PrePersist
//	public void generateId() {
//	    this.patientId = java.util.UUID.randomUUID().toString();
//	}

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL) // Corrected mappedBy
    @JsonManagedReference("patient-appointments") // Corrected with a unique name
    private List<Appointment> appointments;

	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", gender="
				+ gender + ", contactNumber=" + contactNumber + ", address=" + address + ", medicalHistory="
				+ medicalHistory + "]";
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
	@Column(unique = true)
	private String name ;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

//	private Date dateOfBirth ;
	private String gender ;
    private String contactNumber ;
    private String address;
	private String medicalHistory;
}
