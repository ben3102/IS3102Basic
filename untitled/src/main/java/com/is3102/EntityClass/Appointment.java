/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Ben
 */
@Entity(name="Appointment")
public class Appointment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appId;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date appDate;
    
    private String place;
    
    @ManyToOne
    private Patient patient;
    
    @OneToOne(cascade = {CascadeType.PERSIST})
    private mCase mcase;
    
    @ManyToOne
    private Doctor doctor;
    
    public Appointment(){}
    
    public void create(Date appDate, String place){
        this.setAppDate(appDate);
        this.setPlace(place);
    }
    
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
        public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }
    
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public mCase getmCase() {
        return mcase;
    }

    public void setmCase(mCase mcase) {
        this.mcase = mcase;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
