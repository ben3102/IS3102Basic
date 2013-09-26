package com.is3102.PatientAdministrationBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import com.is3102.EntityClass.Patient;
import com.is3102.Interface.AdministrativeAdmissionRemote;
import com.is3102.Interface.PatientIdandCheckingRemote;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Swarit
 */

@ManagedBean
@SessionScoped
//@RequestScoped
public class PatientIdandCheckingManaged implements Serializable {

    @EJB
    public AdministrativeAdmissionRemote am;
    @EJB
    public PatientIdandCheckingRemote pm;
    public AdministrativeAdmissionManaged adminadm;

    String NRIC_PIN;
    String appDate;
    private ActionEvent actionEvent;

    public String getNRIC_PIN() {
        return NRIC_PIN;
    }

    public void setNRIC_PIN(String NRIC_PIN) {
        this.NRIC_PIN = NRIC_PIN;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    void DoCheckRecurrence() {
        NRIC_PIN = getNRIC_PIN();
        if (pm.checkRecurrence(NRIC_PIN)) {
            Patient p = am.getPatientInfo(NRIC_PIN);
            System.out.println("\nPatient ID: " + p.getNRIC_PIN() + "\nPatient Name: " + p.getName() +"\nPatient Address: " + p.getAddress() +"\nPatient Contact Number: " + p.getcNumber());
            DoCheckAppointment();

        }
        else {
            adminadm = new AdministrativeAdmissionManaged();
            adminadm.doAddPatient(actionEvent);
        }
    }

    void DoCheckAppointment() {
        adminadm = new AdministrativeAdmissionManaged();
        NRIC_PIN = getNRIC_PIN();
        appDate = getAppDate();
        if(pm.checkAppointment(NRIC_PIN, appDate)) {
            Patient p = am.getPatientInfo(NRIC_PIN);

        }
        else {
            adminadm.doMakeAppointment(actionEvent);
        }
        adminadm = new AdministrativeAdmissionManaged();
        adminadm.doCreateCase(actionEvent);

    }
}