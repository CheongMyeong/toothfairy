package com.cheongmyeong.toothfairy.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheongmyeong.toothfairy.models.Patient;
import com.cheongmyeong.toothfairy.repository.PatientRepository;
import com.cheongmyeong.toothfairy.services.IPatientService;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class PatientService implements IPatientService {
	
	@Autowired
	private PatientRepository userRepository;
	
	@Override
	public Patient save(Patient entity) {
		return userRepository.save(entity);
	}

	@Override
	public Patient update(Patient entity) {
		return userRepository.save(entity);
	}

	@Override
	public void delete(Patient entity) {
		userRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Patient find(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<Patient> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Patient> users) {
		userRepository.deleteInBatch(users);
	}
	
}
