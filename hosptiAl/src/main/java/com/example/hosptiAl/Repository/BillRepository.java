package com.example.hosptiAl.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hosptiAl.Model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	Iterable<Bill> findByPatient_PatientId(Long patientId);
}