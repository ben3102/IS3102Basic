package com.is3102.PatientAdministrationBean;

import com.is3102.EntityClass.Appointment;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import com.is3102.Interface.PatientIdandCheckingRemote;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public PatientIdandCheckingRemote pm;
    String NRIC_PIN;
    Date appDate;
    Patient patient;
    List<Appointment> appointments = new ArrayList<Appointment>();
    List<mCase> cases = new ArrayList<mCase>();
    
    public Patient getPatient() {
        return patient;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    
    public List<mCase> getCases() {
        return cases;
    }

    public String getNRIC_PIN() {
        return NRIC_PIN;
    }

    public void setNRIC_PIN(String NRIC_PIN) {
        this.NRIC_PIN = NRIC_PIN;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public void DoCheckRecurrence(ActionEvent actionEvent) {
        //FacesContext context = FacesContext.getCurrentInstance();
        if (pm.checkRecurrence(NRIC_PIN)) {
            //getPatientCases(String NRIC_PIN)
            patient = pm.getPatientInfo(NRIC_PIN);
            //context.addMessage(null, new FacesMessage("Patient has an existing Record!"));
            //context.addMessage(null, new FacesMessage("Patient ID: " + p.getNRIC_PIN() + "\nPatient Name: " + p.getName() + "\nPatient Address: " + p.getAddress() + "\nPatient Contact Number: " + p.getcNumber()));
        } else {
            patient = null;
            //context.addMessage(null, new FacesMessage("Patient does not have an existing Record!"));
        }
    }

    public void DoCheckAppointment(ActionEvent actionEvent) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Patient patient = pm.getPatient(NRIC_PIN);
        if (patient != null) {
            if (pm.checkAppointment(NRIC_PIN, appDate)) {
                appointments = pm.getPatientAppointments(NRIC_PIN, format.format(appDate));
            } else {
                appointments = null;
            }
        } else {
            appointments = null;
        }
    }
}