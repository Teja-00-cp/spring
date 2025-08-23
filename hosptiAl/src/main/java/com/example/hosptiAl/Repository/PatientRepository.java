package com.example.hosptiAl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hosptiAl.Model.Patient;
import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	 Patient findByName(String name);

}
