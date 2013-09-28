/**
 *
 * @author Bryan
 */

package com.is3102.PatientAdministrationBean;

import com.is3102.EntityClass.Doctor;
import com.is3102.EntityClass.Schedule;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.SchedulingandResourceAllocationBeanLocal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;



@ManagedBean
@ViewScoped
public class SchedulingAndResourceAllocationManaged implements Serializable {

    @EJB
    private SchedulingandResourceAllocationBeanLocal sra;
    
    public SchedulingAndResourceAllocationManaged() {    
    
    }
    
    //list of codes returned for a disease name searched.

     List<Doctor> allDoctors;
    //caseId to search a particular case.

     String doctorName;

    //ICD10 code entitiy for creating a new case.
     List<Doctor> availableDoctors;
    //diagnosis description entered by doctor when adding a new diagnosis for a case.
     List<Schedule> allShifts;
    //disease description entered by doctor used to search ICD10 code
     List<Schedule> doctorShifts;
    //collectin of all Diagnosis objects for a particular case.
     String doctorUsername;
     String doctorDOB;
     Long doctorID;
     String appointmentTime;
     String appointmentDate;
     Long shiftID;
     List<Doctor> doctorsByShift;
     List<Schedule> shiftsByDoctor;
    @ManagedProperty(value="#{shiftCode}")
     String shiftCode;
    @ManagedProperty(value="#{shiftDate}")
     String shiftDate;

    public void DoGetAllDoctors(ActionEvent actionEvent) throws ExistException {
      allDoctors  = getSra().getDoctors();
    }
    

    @PostConstruct
    public void init(){
       // this.doViewShift();
    }
    
    public void DoAddDoctor (ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try{
                getSra().addDoctor(doctorName, doctorUsername, doctorDOB);
            context.addMessage(null, new FacesMessage("Doctor created successfully with user name: " + doctorUsername ));
            }catch (ParseException ex){
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doctor could not be created!", null));
            }
            
            } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManaged.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DoGetDoctorName(){
        try {
            this.setDoctorName(getSra().getDoctorName(getDoctorID()));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doGetAvailableDoctors(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
   //         this.setAvailableDoctors(sra.getAvailableDoctors(getAppointmentDate(), getAppointmentTime()));
        availableDoctors = getSra().getAvailableDoctors(appointmentDate, appointmentTime);
        context.addMessage(null, new FacesMessage("Available doctors fetched successfully"));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManaged.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doctors could not be fetched!", null));
            
        }
    }
    


    public void doGetDoctors(){
        try {
            this.setAllDoctors(getSra().getDoctors());
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doGetDoctorsByShift(){
        this.setDoctorsByShift(getSra().getDoctors(this.shiftID));

    }
    
    public void doGetDoctorName(){
       FacesContext context = FacesContext.getCurrentInstance();
        try {
            doctorName = getSra().getDoctorName(doctorID);
         //   context.addMessage(null, new FacesMessage("Shift assigned successfully to doctor ID: " + doctorID ));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManaged.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void doGetDoctorID(){
       FacesContext context = FacesContext.getCurrentInstance();
        try {
            doctorID = getSra().getDoctorID(doctorName);
            context.addMessage(null, new FacesMessage("ID of "+ doctorName+" is: " + doctorID ));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManaged.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }


    public void doGetShiftsByDoctor(){
        this.setShiftsByDoctor(getSra().getShifts(this.doctorID));
    }

    public void doAssignShift(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try{
                getSra().assignShift(doctorID, shiftDate, shiftCode);
            context.addMessage(null, new FacesMessage("Shift assigned successfully to doctor ID: " + doctorID ));
            }catch (ParseException ex){
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be assigned!", null));
            }
            
            } catch (ExistException ex) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be assigned!", null));
        }
    }

    public void doCreateShift(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                // declare and initialize testDate variable, this is what will hold
                // our converted string

                        getSra().createShift(shiftDate, shiftCode);
                        context.addMessage(null, new FacesMessage("Shift created successfully for the following date: " + shiftDate ));
            } catch (ParseException ex) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be created!", null));
                
            }
            
        } catch (ExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be created!", null));
        } 
    }

    public void doViewShift(){
        try {
            List<Schedule> t = new ArrayList<Schedule>();
            allShifts = getSra().viewShifts();
            //this.setAllShifts(sra.viewShifts());
            System.out.println(this.allShifts.size());
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManaged.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * @return the allDoctors
     */
    public List<Doctor> getAllDoctors() {
        return allDoctors;
    }

    /**
     * @param allDoctors the allDoctors to set
     */
    public void setAllDoctors(List<Doctor> allDoctors) {
        this.allDoctors = allDoctors;
    }

    /**
     * @return the doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @param doctorName the doctorName to set
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * @return the availableDoctors
     */
    public List<Doctor> getAvailableDoctors() {
        return availableDoctors;
    }

    /**
     * @param availableDoctors the availableDoctors to set
     */
    public void setAvailableDoctors(List<Doctor> availableDoctors) {
        this.availableDoctors = availableDoctors;
    }

    /**
     * @return the allShifts
     */
    public List<Schedule> getAllShifts() {
        return allShifts;
    }

    /**
     * @param allShifts the allShifts to set
     */
    public void setAllShifts(List<Schedule> allShifts) {
        this.allShifts = allShifts;
    }

    /**
     * @return the doctorShifts
     */
    public List<Schedule> getDoctorShifts() {
        return doctorShifts;
    }

    /**
     * @param doctorShifts the doctorShifts to set
     */
    public void setDoctorShifts(List<Schedule> doctorShifts) {
        this.doctorShifts = doctorShifts;
    }

    /**
     * @return the doctorUsername
     */
    public String getDoctorUsername() {
        return doctorUsername;
    }

    /**
     * @param doctorUsername the doctorUsername to set
     */
    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    /**
     * @return the doctorDOB
     */
    public String getDoctorDOB() {
        return doctorDOB;
    }

    /**
     * @param doctorDOB the doctorDOB to set
     */
    public void setDoctorDOB(String doctorDOB) {
        this.doctorDOB = doctorDOB;
    }

    /**
     * @return the doctorID
     */
    public Long getDoctorID() {
        return doctorID;
    }

    /**
     * @param doctorID the doctorID to set
     */
    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * @return the appointmentTime
     */
    public String getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * @param appointmentTime the appointmentTime to set
     */
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /**
     * @return the appointmentDate
     */
    public String getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * @param appointmentDate the appointmentDate to set
     */
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @return the shiftID
     */
    public Long getShiftID() {
        return shiftID;
    }

    /**
     * @param shiftID the shiftID to set
     */
    public void setShiftID(Long shiftID) {
        this.shiftID = shiftID;
    }

    /**
     * @return the doctorsByShift
     */
    public List<Doctor> getDoctorsByShift() {
        return doctorsByShift;
    }

    /**
     * @param doctorsByShift the doctorsByShift to set
     */
    public void setDoctorsByShift(List<Doctor> doctorsByShift) {
        this.doctorsByShift = doctorsByShift;
    }

    /**
     * @return the shiftsByDoctor
     */
    public List<Schedule> getShiftsByDoctor() {
        return shiftsByDoctor;
    }

    /**
     * @param shiftsByDoctor the shiftsByDoctor to set
     */
    public void setShiftsByDoctor(List<Schedule> shiftsByDoctor) {
        this.shiftsByDoctor = shiftsByDoctor;
    }

    /**
     * @return the shiftCode
     */
    public String getShiftCode() {
        return shiftCode;
    }

    /**
     * @param shiftCode the shiftCode to set
     */
    public void setShiftCode(String shiftCode) {
        this.shiftCode = shiftCode;
    }

    /**
     * @return the shiftDate
     */
    public String getShiftDate() {
        return shiftDate;
    }

    /**
     * @param shiftDate the shiftDate to set
     */
    public void setShiftDate(String shiftDate) {
        this.shiftDate = shiftDate;
    }
    
    
    
    // date validation using SimpleDateFormat
// it will take a string and make sure it's in the proper 
// format as defined by you, and it will also make sure that
// it's a legal date

public boolean isValidDate(String date)
{
    // set date format, this can be changed to whatever format
    // you want, MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy etc.
    // you can read more about it here:
    // http://java.sun.com/j2se/1.4.2/docs/api/index.html
    
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    
    // declare and initialize testDate variable, this is what will hold
    // our converted string
    
    Date testDate = null;

    // we will now try to parse the string into date form
    try
    {
      testDate = sdf.parse(date);
    }

    // if the format of the string provided doesn't match the format we 
    // declared in SimpleDateFormat() we will get an exception

    catch (ParseException e)
    {
      System.out.println("the date you provided is in an invalid date" +
                              " format.") ;
      return false;
    }


    if (!sdf.format(testDate).equals(date)) 
    {
      System.out.println("The date that you provided is invalid.");
      return false;
    }
    
    // if we make it to here without getting an error it is assumed that
    // the date was a valid one and that it's in the proper format

    return true;

} // end isValidDate

    /**
     * @return the sra
     */
    public SchedulingandResourceAllocationBeanLocal getSra() {
        return sra;
    }

    /**
     * @param sra the sra to set
     */
    public void setSra(SchedulingandResourceAllocationBeanLocal sra) {
        this.sra = sra;
    }


}

