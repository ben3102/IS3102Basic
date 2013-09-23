/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Interface;


import com.is3102.EntityClass.Doctor;
import com.is3102.EntityClass.Schedule;
import com.is3102.exception.ExistException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ashish
 */
@Remote
public interface SchedulingandResourceAllocationBeanRemote {

    public void addDoctor(String name, String dob, String username) throws ExistException;

    public List<Doctor> getAvailableDoctors(String appointmentDate, String appointmentTime) throws ExistException, ParseException;

    public List<Doctor> getDoctors() throws ExistException;

    public List<Doctor> getDoctors(Long shiftID);

    public void assignShift(Long doctorId, String shiftDate, String shiftCode) throws ExistException;

    public void createShift(String shiftDate, String shiftCode) throws ExistException;

    public void DisplayPatientInfo() throws Exception;

    public String getDoctorName(Long id) throws ExistException;

    public List<Schedule> viewShifts() throws ExistException;

    public List<Schedule> getShifts(Long doctorID);

}

