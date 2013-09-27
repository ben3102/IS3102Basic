/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Interface;

import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Swarit
 */

@Remote
public interface PatientIdandCheckingRemote {

    public boolean checkRecurrence(String NRIC_PIN);
    
    public Patient getPatientInfo(String NRIC_PIN);
    
    public boolean checkAppointment(String NRIC_PIN, Date appDate);
    
    public List<Appointment> getPatientAppointments(String NRIC_PIN, String appDate);

    public Patient getPatient(String NRIC_PIN);
    
    public List<mCase> getPatientCases(String NRIC_PIN);

}
