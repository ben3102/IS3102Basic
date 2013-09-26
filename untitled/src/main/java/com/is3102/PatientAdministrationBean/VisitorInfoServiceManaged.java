package com.is3102.PatientAdministrationBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import com.is3102.EntityClass.Bed;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.AdministrativeAdmissionRemote;
import com.is3102.Interface.PatientIdandCheckingRemote;
import com.is3102.Interface.VisitorInfoServiceRemote;
import java.text.ParseException;
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
@SessionScoped
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
    
    public void doretrievePatientInfo(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Bed bed  = vm.retrievePatientInfo(NRIC_PIN, format.format(dateAdmitted));
            context.addMessage(null, new FacesMessage("Bed Number: " + bed.getBedNo() + " ; " + " Room Number: " + bed.getRoomNo() + " ; " + " Floor Number: " + bed.getFloor()));
    }
        catch(Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be found!", null));
        } finally {
            //clear1();
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