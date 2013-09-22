/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationBean;

import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.Medical_Anamnesis;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.CaseException;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.MedicalAdmissionBean1Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ben
 */
@Stateful
public class MedicalAdmissionBean1 implements MedicalAdmissionBean1Remote {

    @PersistenceContext()
    EntityManager em;
    Medical_Anamnesis anamnesis;
    Diagnosis diagnosis;
    mCase mcase;
    
    public MedicalAdmissionBean1(){}
    
    //lack of query sentence to database?

    private mCase getCaseInfo(Long caseId)throws CaseException{
          mcase =em.find(mCase.class, new Long(caseId));
        if (mcase==null)
            throw new CaseException("Case does not exsit");
        return mcase;
    }
    @Override
    public void addAnamnesis(Long caseId, Long AnamnesisId, String diseaseHistory,
            String socialHistory, String medicalHistory,
            String familyHistory, String allergies, String symptoms) throws ExistException, CaseException{
        anamnesis=em.find(Medical_Anamnesis.class, AnamnesisId);
        if(anamnesis!=null)
           throw new ExistException("Anamnesis Already Exists"); 
        anamnesis=new Medical_Anamnesis();
        anamnesis.create(AnamnesisId,diseaseHistory,
            socialHistory, medicalHistory,
            familyHistory, allergies, symptoms);
        mcase=getCaseInfo(caseId);
        mcase.setMedicalAnamnesis(anamnesis);
        em.persist(mcase);
    }
    
    @Override
    public Medical_Anamnesis getAnamnesis(Long anamnesisId){
        return em.find(Medical_Anamnesis.class, anamnesisId);
       }
    
    @Override
    public void removeAnamnesis(Long anamnesisId){
       anamnesis=em.find(Medical_Anamnesis.class, anamnesisId);
       em.remove(anamnesis);
        }
    
   //lack of query sentence to database?
    
   //following methods have been codded in the coding bean 
    
  /*  @Override
   public void codeDiagnosis(String diseaseCode,
    String description){
       diagnosis=new Diagnosis();
       //diagnosis.create(diseaseCode,description);
       em.persist(diagnosis);
       //need to confirm bidirectional or unidirectional for Diaggosis
       
   }
   
    @Override
   public void removeDiagnosis(Long diagnosisId) {
         diagnosis=em.find(Diagnosis.class, diagnosisId);
         em.remove(diagnosis);
      
        }
    @Override
   public Diagnosis getDiagnosis(Long diagnosisId){
       return em.find(Diagnosis.class, diagnosisId);
   
   }*/
}
   
   


