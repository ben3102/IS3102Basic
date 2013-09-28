package com.is3102.PatientAdministrationBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Patient;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.VisitorInfoServiceRemote;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Swarit
 */
@ManagedBean
@ViewScoped
//@RequestScoped
public class VisitorInfoServiceManaged implements Serializable {

    @EJB
    public VisitorInfoServiceRemote vm;
    //public AdministrativeAdmissionManaged adminadm;
    String NRIC_PIN;
    Date dateAdmitted;
    int countToday;
    int countMonth;
    int countAvg;
    Bed bed;

    public Bed getBed() {
        return bed;
    }

    public int getCountToday() {
        return countToday;
    }

    public int getCountMonth() {
        return countMonth;
    }

    public int getCountAvg() {
        return countAvg;
    }

    public String getNRIC_PIN() {
        return NRIC_PIN;
    }

    public void setNRIC_PIN(String NRIC_PIN) {
        this.NRIC_PIN = NRIC_PIN;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public void doretrievePatientInfo(ActionEvent actionEvent) throws ExistException {
        FacesContext context = FacesContext.getCurrentInstance();
        //try {
        Patient patient = vm.getPatient(NRIC_PIN);
        if (patient != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            bed = vm.retrievePatientInfo(NRIC_PIN, format.format(dateAdmitted));
        } else {
            bed = null;
            //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be found!", null));

        }
    }

    /* Count the number of patients admitted today */
    public void dogetTodaysAdmissions(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        countToday = vm.getTodaysAdmissions();
        context.addMessage(null, new FacesMessage("The total is: " + countToday));
    }

    /* Count the number of patients admitted this month */
    public void dogetCurrentPatients() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            countMonth = vm.getCurrentPatients();
            context.addMessage(null, new FacesMessage("The total is: " + countMonth));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        }
    }

    /* Count the average duration of stay */
    public void dogetStayDuration() {
        FacesContext context = FacesContext.getCurrentInstance();
        countAvg = vm.getStayDuration();
        context.addMessage(null, new FacesMessage("The total is: " + countAvg));
    }
}