/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheongmyeong.toothfairy.services.impl;

import com.cheongmyeong.toothfairy.models.Staff;
import com.cheongmyeong.toothfairy.repository.StaffRepository;
import com.cheongmyeong.toothfairy.services.IStaffService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService implements IStaffService
{
  
	@Autowired
	private StaffRepository staffRepository;
	
	@Override
	public Staff save(Staff entity) {
		return staffRepository.save(entity);
	}

	@Override
	public Staff update(Staff entity) {
		return staffRepository.save(entity);
	}

	@Override
	public void delete(Staff entity) {
		staffRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		staffRepository.deleteById(id);
	}

	@Override
	public Staff find(Long id) {
		return staffRepository.findById(id).orElse(null);
	}

	@Override
	public List<Staff> findAll() {
		return staffRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Staff> staff) {
		staffRepository.deleteInBatch(staff);
	}
}
