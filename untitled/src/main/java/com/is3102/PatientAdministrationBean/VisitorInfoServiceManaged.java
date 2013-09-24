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
import javax.ejb.EJB;

/**
 *
 * @author Swarit
 */

@ManagedBean
@SessionScoped
//@RequestScoped
public class VisitorInfoServiceManaged implements Serializable {

    @EJB
    public static AdministrativeAdmissionRemote am;
    @EJB
    public static PatientIdandCheckingRemote pm;
    @EJB
    public static VisitorInfoServiceRemote vm;
    //public AdministrativeAdmissionManaged adminadm;

    String NRIC_PIN;
    String dateAdmitted;

    public String getNRIC_PIN() {
        return NRIC_PIN;
    }

    public void setNRIC_PIN(String NRIC_PIN) {
        this.NRIC_PIN = NRIC_PIN;
    }

    public String getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(String dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }


    void doretrievePatientInfo() throws ExistException {
        NRIC_PIN = getNRIC_PIN();
        dateAdmitted = getDateAdmitted();
        Bed bed  = vm.retrievePatientInfo(NRIC_PIN, dateAdmitted);
        System.out.println("\nBed Number: " + bed.getBedNo() + "\nRoom Number: " + bed.getRoomNo() +"\nFloor: " + bed.getFloor());

    }

    /* Count the number of patients admitted today */
    void dogetTodaysAdmissions() {
        int count_today = vm.getTodaysAdmissions();
    }

    /* Count the number of patients admitted this month */
    void dogetCurrentPatients() throws ParseException {
        int count_month = vm.getCurrentPatients();
    }

    /* Count the average duration of stay */
    void dogetStayDuration() {
        int count_avg = vm.getStayDuration();
    }
}