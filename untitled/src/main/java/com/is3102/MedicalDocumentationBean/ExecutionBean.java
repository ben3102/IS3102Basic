/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationBean;

import com.is3102.EntityClass.Doctor;
import com.is3102.EntityClass.ExecutionLog;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.ExecutionRemote;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashish
 */
@Stateless
public class ExecutionBean implements ExecutionRemote{
    
    @PersistenceContext()
    EntityManager em;
    
    public void AddExecutionRecord(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException {
        
        Doctor doctor = em.find(Doctor.class, doctor_id);
        if(doctor==null){
            em.clear();
            throw new ExistException("Doctor not found!");        
        }
        
        ExecutionLog eLog = new ExecutionLog();
        eLog.create(doctor, exeuction_comment);
        
        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedure_id);
        if(procedure==null){
            em.clear();
            throw new ExistException("No such procedure created");
        }
        procedure.addExecutionLog(eLog);
    
    }

    public List<ExecutionLog> CreateEvaluationReport(Long procedure_id) throws ExistException{
        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedure_id);
        if(procedure == null){
            em.flush();
            throw new ExistException("No such procedure created!");
        }               
        List<ExecutionLog> logs = procedure.getExecutionlogs();
        return logs;
    }
}
