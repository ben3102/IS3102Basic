/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.PatientAdministrationBean;

import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import javax.ejb.Stateless;
import com.is3102.Interface.AdministrativeAdmissionRemote;
import com.is3102.Interface.VisitorInfoServiceRemote;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author Swarit
 */

@Stateless
public class VisitorInfoServiceBean implements VisitorInfoServiceRemote {
    @EJB
    public static AdministrativeAdmissionRemote adminadm;
    
    @PersistenceContext
    EntityManager em;
    private Patient patient;
    private mCase mcase;
    
    public Bed retrievePatientInfo(String NRIC_PIN, String dateAdmitted) throws ExistException {
        
        String aDate;
        patient = em.find(Patient.class, NRIC_PIN);
        // patient.getmCases();
        
        List<mCase> mcases = (List<mCase>) patient.getmCases();
        //List<mCase> mcases = new ArrayList<mCase>();
        
        for(mCase mcase: mcases) {
            aDate = HandleDates.convertDateToString(mcase.getDateAdmitted());
            if(aDate.equals(dateAdmitted))
                return mcase.getBed();
            else continue;
         }
         throw new ExistException ("PATIENT NOT FOUND");
    }
    
    /*
    public mCase getPatientCase(String NRIC_PIN, Date dateAdmitted, List<mCase> mcases){ // get current case!!!
        
        Query q = em.createQuery("SELECT mcase FROM mCase mcase WHERE mcase.dateAdmitted=:dateAdmitted");
        q.setParameter("dateAdmitted",dateAdmitted);
        mcase = (mCase)q.getSingleResult();
        return mcase;
    
    } */
    
    /* public void CalculateStatistics() throws Exception {}
    
        getTodaysAdmissions();
        getCurrentPatients();
        getStayDuration();
    } */

    /* Count the number of patients admitted today */
    public int getTodaysAdmissions() {
        Date date = new Date();
        List<mCase> mcases;
        mcases = adminadm.getmCases();
        int i=0;
        for(mCase mc: mcases)
            if(mc.getDateAdmitted().equals(date))
                i++;
        return i;
    }
    /* Count the number of patients admitted this month */
    public int getCurrentPatients() throws ParseException {
        String date_begin = HandleDates.GetFirstDayOfMonth();
        Date dateBegin = HandleDates.getDateFromString(date_begin);
        String date_end = HandleDates.GetLastDayOfMonth();
        Date dateEnd = HandleDates.getDateFromString(date_end);
        String date_admitted;
        Date dateAdmitted;
        
        //Date date = new Date();
        List<mCase> mcases = adminadm.getmCases();
        int i=0;
        for(mCase mc: mcases) {
            date_admitted = HandleDates.convertDateToString(mc.getDateAdmitted());
            dateAdmitted = HandleDates.getDateFromString(date_admitted);
            if(dateAdmitted.compareTo(dateBegin) >=0 && dateAdmitted.compareTo(dateEnd) <0)
                i++;
        }
        return i;        
    }
    
    /* Count the average duration of stay */
    public int getStayDuration() {
        List<mCase> mcases = adminadm.getmCases();
        int counter=0;
        int days = 0;
        int total_days = 0;
        for(mCase mc: mcases) {
            Date dateAdmitted = mc.getDateAdmitted();
            Date dateDischarged = mc.getdateDischarged();
            if (dateDischarged!=null) {
                counter++;
                days = Days.daysBetween(new DateTime(dateAdmitted), new DateTime(dateDischarged)).getDays();
                total_days+=days;
            }
            return (total_days/counter);
        }
        return 0;
    }
}