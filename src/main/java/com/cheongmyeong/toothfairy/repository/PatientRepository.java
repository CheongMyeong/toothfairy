package com.cheongmyeong.toothfairy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheongmyeong.toothfairy.models.Patient;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	//User findByEmail(String email);
}
