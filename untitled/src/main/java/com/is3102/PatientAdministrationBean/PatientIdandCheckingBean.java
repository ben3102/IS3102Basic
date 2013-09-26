/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.PatientAdministrationBean;

import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.Patient;
import java.util.Collection;
import com.is3102.Interface.PatientIdandCheckingRemote;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PatientIdandCheckingBean implements PatientIdandCheckingRemote {
    
    @PersistenceContext
    EntityManager em;
    private Patient patient;
    //private AdministrativeAdmissionBean adminadm;
    
    public PatientIdandCheckingBean() {}
    
    public boolean checkRecurrence(String NRIC_PIN) {
        
        patient = em.find(Patient.class, NRIC_PIN);
        if(patient==null)
            return false;
        else
            return true;
    }
    
    public boolean checkAppointment(String NRIC_PIN, String appDate) {
        
        String aDate;
        patient = em.find(Patient.class, NRIC_PIN);
        if(patient.getAppointments().isEmpty())
            return false;
        else {
            Collection<Appointment> appointment=patient.getAppointments(); //get all appointments for a patient
            for(Appointment appt: appointment) {
                aDate = HandleDates.convertDateToString(appt.getAppDate());
                if(aDate.equals(appDate))
                    return true;
            }
            return false;
        }
    }
}