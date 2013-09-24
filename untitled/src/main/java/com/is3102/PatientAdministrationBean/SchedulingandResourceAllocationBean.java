/*
/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.PatientAdministrationBean;

import com.is3102.EntityClass.Doctor;
import com.is3102.EntityClass.Schedule;
import com.is3102.Exception.ExistException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.is3102.Interface.SchedulingandResourceAllocationBeanRemote;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Ashish
 */

@ManagedBean
public class SchedulingandResourceAllocationBean implements SchedulingandResourceAllocationBeanRemote {



    @PersistenceContext()
    EntityManager em;
    Schedule schedule;
    Schedule existingS;
    Doctor doctor;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addDoctor(String name, String username, String dob) throws ExistException{

        Query q = em.createQuery("SELECT d FROM doctor d where d.Name=:name");
        q.setParameter("name", name);
        List result;
        result = q.getResultList();
        if (!(result.isEmpty()))
            throw new ExistException("DOCTOR WITH SAME NAME ALREADY EXISTS");
        doctor =  new Doctor();
        doctor.create(name, username, dob);
        em.persist(doctor);
        System.out.println("Doctor " + name + " successfully added.");
    }

    public String getDoctorName(Long id) throws ExistException{
        doctor = em.find(Doctor.class, id);
        if (doctor == null)
            throw new ExistException("DOCTOR ID DOES NOT EXIST.");

        return doctor.getName();
    }

    public List<Doctor> getAvailableDoctors(String appointmentDate, String appointmentTime) throws ExistException, ParseException {
        String pattern = "HH:mm";


        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        String shiftCode=null;

        Date sA = sdf.parse("00:00");
        Date eA= sdf.parse("07:59");
        Date sB= sdf.parse("08:00");
        Date eB= sdf.parse("15:59");
        Date sC = sdf.parse("16:00");
        Date eC= sdf.parse("23:59");

        Date appTime = sdf.parse(appointmentTime);


        if((appTime.compareTo(eA)< 0) && (appTime.compareTo(sA)> 0)){
            shiftCode="A";
        }
        else if((appTime.compareTo(eB)< 0) && (appTime.compareTo(sB)> 0)){
            shiftCode="B";
        }
        else if((appTime.compareTo(eC)< 0) && (appTime.compareTo(sC)> 0)){
            shiftCode="C";
        }




        Query q = em.createQuery
                ("SELECT s FROM Schedule s WHERE s.shiftDate = :appointmentDate AND s.shiftCode=:shiftCode");
        q.setParameter("appointmentDate", appointmentDate);
        q.setParameter("shiftCode", shiftCode);
        List result;
        result = q.getResultList();
        if ((result.isEmpty()==true)){
            em.clear();

            throw new ExistException( appTime.compareTo(eA) + "      " +shiftCode + "    "+appointmentDate);
        }
        for (Object o: result){
            schedule = (Schedule)o;
        }
        Long shiftId = schedule.getId();
        return this.getDoctors(shiftId);


    }

    public List<Doctor> getDoctors() throws ExistException {

        Query q = em.createQuery("SELECT c FROM doctor c");
        List docList = new ArrayList();
        for (Object o: q.getResultList()) {
            Doctor doc = (Doctor)o;
            docList.add(doc);
        }
        if (docList.isEmpty()==true){
            throw new ExistException("THERE ARE NO DOCTORS IN THE DATABASE");
        }



        return docList;
    }

    public List<Doctor> getDoctors(Long shiftID){
        Query q = em.createQuery("SELECT d from doctor d left join d.schedules as s where s.id = :shiftID");
        q.setParameter("shiftID", shiftID);
        List scheduleList = q.getResultList();
        return scheduleList;
    }

    public List<Schedule> getShifts(Long doctorID) /*throws ExistException*/{
        Query q = em.createQuery("SELECT s from Schedule s left join s.doctors as d where d.id = :doctorID");
        q.setParameter("doctorID", doctorID);
        List scheduleList = q.getResultList();
        //   List scheduleList = new ArrayList();
     /*   for (Object o: q.getResultList()) {
            Schedule sch = (Schedule)o;
            scheduleList.add(sch);
        }*/
     /*   if (scheduleList.isEmpty()==true){
            throw new ExistException("THERE ARE NO SHIFTS ASSIGNED TO THIS DOCTOR");
        }
      */
        return scheduleList;

    }

    public void assignShift (Long doctorId, String shiftDate, String shiftCode) throws ExistException{

        Query q = em.createQuery
                ("SELECT s FROM Schedule s WHERE s.shiftDate = :shiftDate AND s.shiftCode =:shiftCode");
        q.setParameter("shiftDate", shiftDate);
        q.setParameter("shiftCode", shiftCode);
        List result;
        result = q.getResultList();
        if ((result.isEmpty())){
            em.clear();
            throw new ExistException("SHIFT DOES NOT EXIST");
        }

        for (Object o: result){
            schedule = (Schedule)o;
        }

        doctor = em.find(Doctor.class, doctorId);
        if (doctor == null){
            throw new ExistException("DOCTOR DOES NOT EXIST");
        }

        List existingSch = this.getShifts(doctorId);
        if(existingSch.isEmpty()==true){
            doctor.addSchedule(schedule);
            schedule.addDoctor(doctor);
            em.persist(schedule);
            em.persist(doctor);
        }
        else{
            for(Object p: existingSch){
                existingS = (Schedule)p;
                if(schedule.getShiftDate().compareTo(existingS.getShiftDate())==0  && schedule.getShiftCode().compareTo(existingS.getShiftCode())==0){
                    System.out.println("SCHEDULE ALREADY ASSIGNED TO DOCTOR");
                    return;
                }
            }
            doctor.addSchedule(schedule);
            schedule.addDoctor(doctor);
            em.persist(schedule);
            em.persist(doctor);
        }


    }

    public void createShift(String shiftDate, String shiftCode) throws ExistException{
        Query q = em.createQuery("SELECT c FROM Schedule c where c.shiftDate=:shiftDate AND c.shiftCode=:shiftCode");
        q.setParameter("shiftDate", shiftDate);
        q.setParameter("shiftCode", shiftCode);
        List result;
        result = q.getResultList();
        if (!(result.isEmpty()))
            throw new ExistException("SHIFT ALREADY CREATED.");

        schedule = new Schedule();
        schedule.create(shiftDate, shiftCode);
        em.persist(schedule);
    }

    public List<Schedule> viewShifts() throws ExistException{
        Query q = em.createQuery("SELECT c FROM Schedule c");
        List scheduleList = new ArrayList();
        for (Object o: q.getResultList()) {
            Schedule sch = (Schedule)o;
            scheduleList.add(sch);
        }
        if (scheduleList.isEmpty()==true){
            throw new ExistException("THERE ARE NO SCHEDULES IN THE DATABASE");
        }


        return scheduleList;
    }

    public void DisplayPatientInfo() throws Exception {

    }
}
