package com.example.hosptiAl.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.hosptiAl.Model.Doctor;
import com.example.hosptiAl.Model.Patient;
import com.example.hosptiAl.Repository.DocRe;

@Service
public class DoctorService {
	@Autowired
	private DocRe docRe;
	
	public void addDoc(Doctor doctor) {
			docRe.save(doctor);
	}
	public Iterable<Doctor> getallDoc(){
		return docRe.findAll();
	}
	public Doctor getallbyId(@PathVariable long id){
		return docRe.findById(id).orElse(null);
	}
	public Doctor getbyName(@PathVariable String name){
		return docRe.findByName(name);
	}
//	public Iterable<String> getTime(@PathVariable long id){
//		Iterable<Doctor> time=docRe.findAllById(id).orElse(null);
//		
//		}
	public Iterable<Doctor> getCompletedoctor() {
		// TODO Auto-generated method stub
		return docRe.findAll();
	}
    public void updateDoctor(long patientId, Doctor doctorData) {
        Doctor existingPatient = docRe.findById(patientId).orElse(null);
			System.out.println(doctorData.toString()+"  "+patientId);
		if (existingPatient != null) {
			System.out.println(doctorData.toString());
			existingPatient.setAvailabilitySchedule(doctorData.getAvailabilitySchedule());
			existingPatient.setSpecialization(doctorData.getSpecialization());	
			existingPatient.setContactNumber(doctorData.getContactNumber());	
			docRe.save(existingPatient);}
			else{
				throw new RuntimeException("Patient not found with id: " + patientId);
			}
    }
	public void deleteDoctor(Long id) {
		docRe.deleteById(id);
	}
}
