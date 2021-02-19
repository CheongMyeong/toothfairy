/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheongmyeong.toothfairy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author pc
 */
    @Entity
    @Table(name = "staff")
    public class Staff {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long staffId;
	
	private String firstName;
	
	private String lastName;
        
        private String position;
        
      
	
	public long getId() {
		return staffId;
	}

	public void setId(long id) {
		this.staffId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
        
        public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Staff [id=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + ", position=" + position +  "]";
	}
}
