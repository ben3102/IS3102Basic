/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Interface;

import javax.ejb.Remote;

/**
 *
 * @author Swarit
 */

@Remote
public interface PatientIdandCheckingRemote {

    public boolean checkRecurrence(String NRIC_PIN);

    public boolean checkAppointment(String NRIC_PIN, String appDate);

}
