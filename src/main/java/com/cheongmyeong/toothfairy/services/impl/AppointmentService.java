package com.cheongmyeong.toothfairy.services.impl;

import com.cheongmyeong.toothfairy.models.Appointment;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cheongmyeong.toothfairy.repository.AppointmentRepository;

import com.cheongmyeong.toothfairy.services.IAppointmentService;


/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class AppointmentService implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository staffRepository;
	
	@Override
	public Appointment save(Appointment entity) {
		return staffRepository.save(entity);
	}

	@Override
	public Appointment update(Appointment entity) {
		return staffRepository.save(entity);
	}

	@Override
	public void delete(Appointment entity) {
		staffRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		staffRepository.deleteById(id);
	}

	@Override
	public Appointment find(Long id) {
		return staffRepository.findById(id).orElse(null);
	}

	@Override
	public List<Appointment> findAll() {
		return staffRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Appointment> appointment) {
		staffRepository.deleteInBatch(appointment);
	}
        
}
