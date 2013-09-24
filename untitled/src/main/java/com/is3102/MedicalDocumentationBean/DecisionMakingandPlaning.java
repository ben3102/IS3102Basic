/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationBean;

import com.is3102.EntityClass.Consent;
import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.Finding;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.DecisionMakingandPlaningRemote;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashish
 */
@Stateless
public class DecisionMakingandPlaning implements DecisionMakingandPlaningRemote{

  @PersistenceContext()
    EntityManager em;

    public void AddPlanedProcedure(Long CIN, String procedure_code, String procedure_name, Finding finding, String comments ) throws ExistException{
        
        mCase mcase = em.find(mCase.class, CIN);
        if(mcase==null){
            throw new ExistException("No such case exists");
        }
        
        Medical_Procedure procedure = new Medical_Procedure();
        procedure.create(procedure_code, procedure_name, finding, comments);        
        mcase.addmedicaProcedure(procedure);
        procedure.setMcase(mcase);
        
        System.out.println("Medical Procedure " + procedure.getId() + 
                "added to case " + mcase.getCIN());
        
        em.persist(mcase);       
        em.flush();
    }
    
    public void GetConsent(Long procedureId, String patient_comment) throws ExistException {
        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedureId);
        if(procedure==null){
            throw new ExistException("No such procedure exists!");
        }
        
        Consent consent = new Consent();
        consent.create(patient_comment);
        procedure.setConsent(consent);        
        
    }
    
    public List<Medical_Procedure> RetrieveCarePlaning (Long PIN) throws ExistException{
        List<Medical_Procedure> procedures;
        
        mCase mcase = em.find(mCase.class, PIN);
        if(mcase==null){
            throw new ExistException("No such case exists!");
        }
        procedures = mcase.getmProcedures();
        return procedures;
    }
    
    public void RetrieveMedicalKnowledge(){
    }
    
}
