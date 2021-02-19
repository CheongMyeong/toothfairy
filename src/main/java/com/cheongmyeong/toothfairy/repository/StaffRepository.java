package com.cheongmyeong.toothfairy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheongmyeong.toothfairy.models.Staff;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

	//User findByEmail(String email);
}
