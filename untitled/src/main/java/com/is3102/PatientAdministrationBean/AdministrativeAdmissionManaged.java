package com.is3102.PatientAdministrationBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.mCase;
import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.Doctor;
import com.is3102.Interface.AdministrativeAdmissionRemote;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
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
public class AdministrativeAdmissionManaged implements Serializable {

    @EJB
    public AdministrativeAdmissionRemote am; //adminadmManager;

    //Input i = new Input();

    String NRIC_PIN;
    String name;
    Date birthday;
    String address;
    String contact;
    Date appDate;
    String place;
    String docID;
    String bedNo;
    String appID;
    long CIN;

    List<Appointment> appointments = new ArrayList<Appointment>();
    List<Bed> beds = new ArrayList<Bed>();
    
    public long getCIN(){
        return CIN;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
    
    public List<Bed> getBeds() {
        return beds;
    }

    public String getNRIC_PIN() {
        return NRIC_PIN;
    }

    public void setNRIC_PIN(String NRIC_PIN) {
        this.NRIC_PIN = NRIC_PIN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public AdministrativeAdmissionManaged() {}

    public void doAddPatient(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String pin = am.addPatient(NRIC_PIN, name, format.format(birthday), address, contact);
            context.addMessage(null, new FacesMessage("Patient Record " + name + " with PIN " + NRIC_PIN + " successfully created!"));
        } catch(Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be  created!", null));
        } finally {
            clear1();
        }
    }
  
    public void doMakeAppointment(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String id = am.makeAppointment(NRIC_PIN, format.format(appDate), place, docID);
            
            Doctor doctor = am.getDoctor(Long.parseLong(docID));
            
            context.addMessage(null, new FacesMessage("Appointment " + id + " for " + NRIC_PIN + " on date " + format.format(appDate) + " by doctor " + doctor.getName() +" successfully made!"));
        } catch(Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Appointment could not be made!", null));
        } finally {
            clear2();
        }
    }

    public void doCreateCase(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            appointments  = am.getPatientAppointments(NRIC_PIN);
            beds = am.getAvailBeds();
            Doctor doctor = am.getDoctor(Long.parseLong(docID));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCreated = new Date();
            CIN = am.createCase(bedNo, appID);
            context.addMessage(null, new FacesMessage("Case " + CIN + " for " + NRIC_PIN + " on date " + format.format(dateCreated) + " by doctor " + doctor.getName() +" successfully created!"));
        } catch(Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Case could not be created!", null));
        }
    }
    public void doListAvailBeds(ActionEvent actionEvent) {
        beds = am.getAvailBeds();       
    }
    
    public void showAppointments(ActionEvent event) {
        appointments  = am.getPatientAppointments(NRIC_PIN);
    }
    
    public void doListPatientCases() {
        System.out.println("\n\n\t=== CASE DETAILS ===");
        List<mCase> mcases = am.getPatientCases(getNRIC_PIN());
        if(mcases.isEmpty()) {
            System.out.println("Patient has no Case.\n");
            return ;
        }

        for(mCase mcase: mcases) {
            System.out.println("\nCIN: " + mcase.getCIN() + "\nDate of Creation: " + mcase.getDateAdmitted() +"\nDate of Appointment: " + mcase.getAppointment());
        }
    }

    public void doListCases() {
        System.out.println("\n\n\t=== CASE DETAILS ===");
        List<mCase> mcases = am.getmCases();
        if(mcases.isEmpty()) {
            System.out.println("No Case to Display.\n");
            return ;
        }

        for(mCase mcase: mcases) {
            System.out.println("\nPatient's Name: " + mcase.getPatient().getName() + "\nDate of Creation: " + mcase.getDateAdmitted() +"\nDate of Appointment: " + mcase.getAppointment());
        }
    }
    
    public void clear1(){
        setNRIC_PIN(null);
        setName(null);
        setBirthday(null);
        setAddress(null);
        setContact(null);
    }
    
    public void clear2(){
        setNRIC_PIN(null);
        setAppDate(null);
        setDocID(null);
    }
}