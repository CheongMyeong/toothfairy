package com.cheongmyeong.toothfairy.models;

import java.time.LocalDate;
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
    @Table(name = "appointment")
    public class Appointment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
        
	private long id;
	
	private String patientName;
	
	private String staffName;
        
        private LocalDate schedule;
        
        private String status;
        
      
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
        
        public LocalDate getSchedule() {
		return schedule;
	}

	public void setSchedule(LocalDate schedule) {
		this.schedule = schedule;
	}
        
        public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", patientName=" + patientName + ", staffName=" + staffName + ", schedule=" + schedule + ", status=" + status + "]";
	}
}
