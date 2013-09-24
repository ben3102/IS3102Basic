/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationManaged;

import com.is3102.EntityClass.Medical_Anamnesis;
import com.is3102.Interface.MedicalAdmissionBean1Remote;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ashish
 */
@ManagedBean
public class MedicalAdmissionBeanManaged {
 @EJB
 private static MedicalAdmissionBean1Remote mab;
 
    private Long caseId;
    private Long AnamnesisId;
    private String diseaseHistory;
    private String socialHistory; 
    private String medicalHistory;
    private String familyHistory; 
    private String allergies; 
    private String symptoms;
    private Medical_Anamnesis mAnamnesis;

    
    
     /*public void addAnamnesis(Long caseId, Long AnamnesisId, String diseaseHistory,
                             String socialHistory, String medicalHistory,
                             String familyHistory, String allergies, String symptoms) throws ExistException,  CaseException;
    public Medical_Anamnesis getAnamnesis(Long anamnesisId);
    public void removeAnamnesis(Long anamnesisId);*/
    public void DoAddAnamnesis(){
        try {
            mab.addAnamnesis(caseId, AnamnesisId, diseaseHistory, socialHistory, medicalHistory, familyHistory, allergies, symptoms);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void DoGetAnamnesis(){
        try{
            Medical_Anamnesis anamnesis = mab.getAnamnesis(AnamnesisId);
            this.setmAnamnesis(mAnamnesis);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void DoRemoveAnamnesis(){
        try{
            mab.removeAnamnesis(AnamnesisId);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Medical_Anamnesis getmAnamnesis() {
        return mAnamnesis;
    }

    public void setmAnamnesis(Medical_Anamnesis mAnamnesis) {
        this.mAnamnesis = mAnamnesis;
    }
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getAnamnesisId() {
        return AnamnesisId;
    }

    public void setAnamnesisId(Long AnamnesisId) {
        this.AnamnesisId = AnamnesisId;
    }

    public String getDiseaseHistory() {
        return diseaseHistory;
    }

    public void setDiseaseHistory(String diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
    }

    public String getSocialHistory() {
        return socialHistory;
    }

    public void setSocialHistory(String socialHistory) {
        this.socialHistory = socialHistory;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
    
}
