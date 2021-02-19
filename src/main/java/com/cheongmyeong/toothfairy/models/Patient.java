package com.cheongmyeong.toothfairy.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Entity
@Table(name = "patient")
public class Patient {
        
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	private String firstName;
	
	private String lastName;
        
        private String address;
        
        private Double amount;
        
        private long contactNumber;
        
        private String sex;
        
        private String status;
        
        private String treatment;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
        
        public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
       public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
        
        public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
        public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
                
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", amount=" + amount 
                        + ", contactNumber=" + contactNumber + ", sex="+ sex+ ", status=" + status+ ", treatment=" + treatment + "]";
	}

	
}
