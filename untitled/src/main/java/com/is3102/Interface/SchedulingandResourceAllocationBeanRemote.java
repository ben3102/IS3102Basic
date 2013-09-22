/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Interface;


import com.is3102.EntityClass.Doctor;
import com.is3102.EntityClass.Schedule;
import com.is3102.Exception.ExistException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ashish
 */
@Remote
public interface SchedulingandResourceAllocationBeanRemote {

    public void addDoctor(String id, String name, String dob, String username) throws ExistException;

    public List<Doctor> getAvailableDoctors(String appointmentDate, String appointmentTime);

    public List<Doctor> getDoctors() throws ExistException;

    public void assignShift(String doctorId, String shiftDate, String shiftCode) throws ExistException;

    public void createShift(String shiftDate, String shiftCode) throws ExistException;

    public void DisplayPatientInfo() throws Exception;

    public String getDoctorName(String id) throws ExistException;

    public List<Schedule> viewShifts() throws ExistException;

    public List<Schedule> getShifts(String doctorID);
    
}
