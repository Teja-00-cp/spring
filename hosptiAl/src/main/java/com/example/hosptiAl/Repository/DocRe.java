package com.example.hosptiAl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosptiAl.Model.Doctor;


public interface DocRe extends JpaRepository<Doctor, Long>{
	Doctor findByName(String name);
}
