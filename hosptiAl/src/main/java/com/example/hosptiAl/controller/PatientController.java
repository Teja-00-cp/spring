package com.example.hosptiAl.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

import com.example.hosptiAl.Model.Patient;
import com.example.hosptiAl.Service.PatientService;

//@Controller
@RestController
@RequestMapping("/pat")
public class PatientController {
	@Autowired
	private PatientService  patientService;
	
//	@PostMapping("/addPatient")
//	public void addPatient(@RequestBody Patient patientData) {
//		System.out.println(patientData.toString());
//		patientService.addPatient(patientData);
//	}
	// @GetMapping("/")
//	public String tt(Model mdl) {
//		mdl.addAttribute("patient",new Patient());
//		return "patient";
//	}
//	
//	@GetMapping("/getmm")
//	public String addget(Model model) {
//		model.addAttribute("teja","Hello world");
//		return "index";
//	}
	
//	@PostMapping("/addPatient")
//	public String addPatient(Model model,@ModelAttribute("patient") Patient patientData) {
//		System.out.println(patientData.toString());
//		patientService.addPatient(patientData);
//		model.addAttribute("name",patientData.getName());
//		return "patientdetails";
//	}
	@PutMapping("/update/{patientId}")
	 public void updatePatient(@PathVariable long patientId, @RequestBody Patient patientData) {
		 patientService.updatePatient(patientId, patientData);
	 }
	@GetMapping("/getPatient/{patientId}")
	 public Optional<Patient> getPatientDetails(@PathVariable long patientId){
		System.out.println(patientId);
		 return patientService.getPatientDetails(patientId); 
	 }
	@GetMapping("/getallpatients")
	 public Iterable<Patient> getPatientall(){
		 return patientService.getPatientall(); 
	 }
	@DeleteMapping("/delete/{patientId}")
	 public void  deletePatient(@PathVariable long patientId) {
		 patientService.deletePatient(patientId);
	 }
	@GetMapping("/getname/{name}")
	public Patient getname(@PathVariable String name) {
		System.out.println(name);
		return patientService.getByname(name);
	}

}
