/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Ben
 */
@Entity
public class Medical_Anamnesis implements Serializable {
   
    @Id
    private Long AnamnesisId;
    private String diseaseHistory;
    private String socialHistory;
    private String medicalHistory;
    private String familyHistory;
    private String allergies;
    private String symptoms;
    
    public Medical_Anamnesis(){}
    
    public void create(Long AnamnesisId, String diseaseHistory,
            String socialHistory, String medicalHistory,
            String familyHistory, String allergies, String symptoms){
        this.setAnamnesisId(AnamnesisId);
        this.setDiseaseHistory(diseaseHistory);
        this.setSocialHistory(socialHistory);
        this.setMedicalHistory(medicalHistory);
        this.setFamilyHistory(familyHistory);
        this.setAllergies(allergies);
        this.setSymptoms(symptoms);
    }

    public Long getAnamnesisId() {
        return AnamnesisId;
    }

    public void setAnamnesisId(Long Anamnesisid) {
        this.AnamnesisId = AnamnesisId;
    }

    public String getDiseaseHistory(){
        return diseaseHistory;
    }
    
    public void setDiseaseHistory(String diseaseHistory){
            this.diseaseHistory = diseaseHistory;
    }
    
    public String getSocialHistory(){
        return socialHistory;
    }
    
    public void setSocialHistory(String socialHistory){
            this.socialHistory = socialHistory;
    }
    
    public String getMedicalHistory(){
        return medicalHistory;
    }
    
    public void setMedicalHistory(String medicalHistory){
            this.medicalHistory = medicalHistory;
    }
    
    public String getFamilyHistory(){
        return familyHistory;
    }
    
    public void setFamilyHistory(String familyHistory){
            this.familyHistory = familyHistory;
    }
    
    public String getAllergies(){
        return allergies;
    }
    
    public void setAllergies(String allergies){
            this.allergies = allergies;
    }
    
    public String getSymptoms(){
        return symptoms;
    }
    
    public void setSymptoms(String symptoms){
            this.symptoms = symptoms;
    }
    
    
    
}
