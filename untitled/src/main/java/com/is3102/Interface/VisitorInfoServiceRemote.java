/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Interface;

import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Patient;
import com.is3102.Exception.ExistException;
import java.text.ParseException;
import javax.ejb.Remote;

/**
 *
 * @author Swarit
 */

@Remote
public interface VisitorInfoServiceRemote {

    public Bed retrievePatientInfo(String NRIC_PIN, String dateAdmitted) throws ExistException;
    
    public int getTodaysAdmissions();

    public int getCurrentPatients() throws ParseException;

    public int getStayDuration();
    
    public Patient getPatient(String NRIC_PIN);
}
