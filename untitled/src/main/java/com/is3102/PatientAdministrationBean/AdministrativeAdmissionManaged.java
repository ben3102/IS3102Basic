package com.is3102.PatientAdministrationBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.mCase;
import com.is3102.EntityClass.Appointment;
import com.is3102.Interface.AdministrativeAdmissionRemote;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Swarit
 */

@ManagedBean
@SessionScoped
//@RequestScoped
public class AdministrativeAdmissionManaged implements Serializable {

    @EJB
    public static AdministrativeAdmissionRemote am; //adminadmManager;

    //Input i = new Input();

    String NRIC_PIN;
    String name;
    String birthday;
    String address;
    String contact;
    String appDate;
    String place;
    String docID;
    String bedNo;
    String appID;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
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

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
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

    void doAddPatient() {
        /*String NRIC_PIN;
        String name;
        String birthday;
        String address;
        String contact;*/

        try {
            System.out.println("\n\n\t\t== Add a Patient ==\n");
            //NRIC_PIN = i.getString("Patient Id: ", null);
            //name = i.getString("Patient Name: ", null);
            //birthday = i.getString("Patient Birthday: ", null);
            //address = i.getString("Patient Address: ", null);
            //contact = i.getString("Patient Contact Number: ", null);

            NRIC_PIN = getNRIC_PIN();
            name = getName();
            birthday = getBirthday();
            address = getAddress();
            contact = getContact();

            String pin = am.addPatient(NRIC_PIN, name, birthday, address, contact);
            System.out.println("\nPatient " + pin + " added successfully");
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("\nERROR: Failed to add Patient");
        }
    }

    void doMakeAppointment() {
        /* //String NRIC_PIN;
        String appDate;
        String place;
        String docID;*/

        try {
            System.out.println("\n\n\t\t== Make an Appointment ==\n");
            //NRIC_PIN = i.getString("Patient Id: ", null);
            //appDate = i.getString("Appointment Date: ", null);
            //place = i.getString("Appointment Place: ", null);
            //docID = i.getString("Doctor ID: ", null);
            NRIC_PIN = getNRIC_PIN();
            appDate = getAppDate();
            place = getPlace();
            docID = getDocID();

            long appID = am.makeAppointment(NRIC_PIN, appDate, place, docID);
            System.out.println("\nAppointment " + appID + " made successfully");
        } catch(Exception ex) {
            //ex.printStackTrace();
            System.out.println(ex.getMessage());
            System.out.println("\nERROR: Failed to make Appointment");
        }
    }

    void doCreateCase() {
        //String bedNo;
        //String appID;

        try {
            System.out.println("\n\n\t\t== Create a Case ==\n");
            //bedNo = i.getString("Bed Number: ", null);
            //appID = i.getString("Appointment ID: ", null);
            NRIC_PIN = getNRIC_PIN();
            List<Appointment> appointmens  = am.getPatientAppointments(NRIC_PIN);
            for(Appointment app: appointmens) {
                System.out.println("\nAppointment ID: " + app.getId() + "\nAppointment Date: " + app.getappDate());
            }
            bedNo = getBedNo();
            appID = getAppID();

            long CIN = am.createCase(bedNo, appID);
            System.out.println("\nCase " + CIN + " created successfully");
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("\nERROR: Failed to create Case");
        }
    }

    void doListAvailBeds() {
        System.out.println("\n\n\t=== AVAILABLE BEDS ===");
        List<Bed> beds = am.getAvailBeds();
        if(beds.isEmpty()) {
            System.out.println("No Bed Available.\n");
            return ;
        }

        for(Bed bed: beds) {
            System.out.println("\nBed Number: " + bed.getBedNo() + "\nRoom Number: " + bed.getRoomNo() +"\nFloor: " + bed.getFloor());
        }
    }

    void doListPatientCases() {
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

    void doListCases() {
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
}