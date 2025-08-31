package com.example.hosptiAl.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hosptiAl.Model.Doctor;
import com.example.hosptiAl.Model.Patient;
import com.example.hosptiAl.Service.DoctorService;

@RestController
public class DoctorContr {
	@Autowired
	private DoctorService doctorService;
	@PostMapping("/addDoc")
	public void addDoc(@RequestBody Doctor doctor) {
		doctorService.addDoc(doctor);
	}
	@GetMapping("/getCompletedoctor")
	public Iterable<Doctor> getCompletedoctor(){
		return doctorService.getCompletedoctor();
	}
	@GetMapping("/getalldoc")
	public Iterable<Doctor> getall(){
		return doctorService.getallDoc();
	}
	@PutMapping("/updateDoc/{doctorId}")
	 public void updatePatient(@PathVariable long doctorId, @RequestBody Doctor patientData) {
		 doctorService.updateDoctor(doctorId, patientData);
	 }
	@GetMapping("/get/{id}")
	public Doctor getallbyId(@PathVariable long id){
		return doctorService.getallbyId(id);
	}
	@GetMapping("/getname/{name}")
	public Doctor getbyName(@PathVariable String name){
		return doctorService.getbyName(name);
	}
	@DeleteMapping("/doc/delete/{id}")
	public String deleteDoctor(@PathVariable Long id) {
	    doctorService.deleteDoctor(id);
	    return "Doctor with ID " + id + " has been deleted.";
	}
//	@GetMapping("/gettime/{id}")
//	public Iterable<String> getTime(@PathVariable long id){
//		
//	}


}
