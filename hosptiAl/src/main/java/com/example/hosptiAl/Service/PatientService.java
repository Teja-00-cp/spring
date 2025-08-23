package com.example.hosptiAl.Service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hosptiAl.Exception.PatientError;
import com.example.hosptiAl.Model.Patient;
import com.example.hosptiAl.Repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public ResponseEntity<?> addPatient(Patient patientData) throws PatientError{
		
		if(!validate(patientData.getPatientId())) throw new PatientError("Patient is available");
		
		patientRepository.save(patientData);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	 private boolean validate(long patientId) {

		return patientRepository.findById(patientId)!=null;
		
	}
	 
	 public void updatePatient(long patientId, Patient patientData) throws PatientError{
		 
		 if(validate(patientId)) patientRepository.save(patientData);
		 
		 else throw new PatientError("Patient is unavailable");
		 
	 }
	 
	 public Optional<Patient> getPatientDetails(long patientId){
		 
		 return patientRepository.findById(patientId); 
		 
	 }
	 
	 public void  deletePatient(long patientId) {
		 
		 patientRepository.deleteById(patientId);
		 
	 }
	 
	 public Patient getByname(String name) {
		 return patientRepository.findByName(name);
	 }

	 public Iterable<Patient> getPatientall() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	 }

	
}
