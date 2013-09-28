/**
 *
 * @author Bryan
 */

package com.is3102.PatientAdministrationBean;

import com.is3102.EntityClass.Doctor;
import com.is3102.EntityClass.Schedule;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.SchedulingandResourceAllocationBeanRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


@ManagedBean
@RequestScoped
public class SchedulingAndResourceAllocationManagedBean implements Serializable {

    @EJB
    public SchedulingandResourceAllocationBeanRemote sra;
    
    public SchedulingAndResourceAllocationManagedBean() {}

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
      allDoctors  = sra.getDoctors();
    }
    


    public void DoAddDoctor (ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sra.addDoctor(doctorName, doctorUsername, doctorDOB);
            context.addMessage(null, new FacesMessage("Doctor created successfully with user name: " + doctorUsername ));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DoGetDoctorName(){
        try {
            this.setDoctorName(sra.getDoctorName(getDoctorID()));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doGetAvailableDoctors(ActionEvent event){
        try {
   //         this.setAvailableDoctors(sra.getAvailableDoctors(getAppointmentDate(), getAppointmentTime()));
        availableDoctors = sra.getAvailableDoctors(appointmentDate, appointmentTime);
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    public void doGetDoctors(){
        try {
            this.setAllDoctors(sra.getDoctors());
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doGetDoctorsByShift(){
        this.setDoctorsByShift(sra.getDoctors(this.shiftID));

    }
    
    public void doGetDoctorName(){
       FacesContext context = FacesContext.getCurrentInstance();
        try {
            doctorName = sra.getDoctorName(doctorID);
         //   context.addMessage(null, new FacesMessage("Shift assigned successfully to doctor ID: " + doctorID ));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void doGetDoctorID(){
       FacesContext context = FacesContext.getCurrentInstance();
        try {
            doctorID = sra.getDoctorID(doctorName);
            context.addMessage(null, new FacesMessage("ID of "+ doctorName+" is: " + doctorID ));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }


    public void doGetShiftsByDoctor(){
        this.setShiftsByDoctor(sra.getShifts(this.doctorID));
    }

    public void doAssignShift(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sra.assignShift(doctorID, shiftDate, shiftCode);
            context.addMessage(null, new FacesMessage("Shift assigned successfully to doctor ID: " + doctorID ));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doCreateShift(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sra.createShift(shiftDate, shiftCode);
            context.addMessage(null, new FacesMessage("Shift created successfully for the following date: " + shiftDate ));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doViewShift(){
        try {
            this.setAllShifts(sra.viewShifts());
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
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

}

