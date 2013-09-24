/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationManaged;

/**
 *
 * @author Ashish
 */
import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.ICD10_Code;
import com.is3102.Interface.CodingBeanRemote;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class CodingBeanManaged {
    
    @EJB
    private static CodingBeanRemote cbr;
    //list of codes returned for a disease name searched.
    private List<ICD10_Code> icdCodes;
    //caseId to search a particular case.
    private long caseId; 
    //ICD10 code entitiy for creating a new case. 
    private ICD10_Code icd10Code; 
    //diagnosis description entered by doctor when adding a new diagnosis for a case. 
    private String diagnosisDescription;
    //disease description entered by doctor used to search ICD10 code
    // also used to set disease description when adding new disease
    private String diseaseDescription;
    //collectin of all Diagnosis objects for a particular case. 
    private Collection<Diagnosis> allDiagnosis;
    //Disease code to add a new disease to the ICD10 Codes master list
    private String diseaseId; 
    //Chapter attribute to add a new disease to the ICD10 Codes master list
    private String ICDChapter; 
    //Block attribute to add a new disease to the ICD10 Codes master list
    private String ICDblock; 
    //Disease name attribute to add a new disease to the ICD10 Codes master list
    private String diseaseName; 
    //Set of all ICD10 Codes stored in the system. 
    private Set<ICD10_Code> allicdCodes;
   
    
    public void DoGetMatchingCodes(){
        List<ICD10_Code> result = cbr.getMatchingCodes(diseaseDescription);
        this.seticdCodes(result);
    }
    
    public void DoUpdateDiagnosis (){
        try{
            cbr.updateDiagnosis(caseId, icd10Code, diagnosisDescription);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void DoListAllDiagnosis(){
        try{
            this.setAllDiagnosis(cbr.listAllDiagnosis(caseId));
                              
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void DoAddCode (){
        try{
            cbr.addCode(diseaseId, ICDChapter, ICDblock, diseaseName, diseaseDescription);
           }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }        

    public void DoListAllICD10Codes(){
        Set<ICD10_Code> allCodes = cbr.listAllCodes();
        this.setAllicdCodes(allCodes);
    }
    
    public void DoRemoveDiagnosis(){
        try {
            cbr.removeDiagnosis(caseId);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Set<ICD10_Code> getAllicdCodes() {
        return allicdCodes;
    }

    public void setAllicdCodes(Set<ICD10_Code> allicdCodes) {
        this.allicdCodes = allicdCodes;
    }

    public List<ICD10_Code> getIcdCodes() {
        return icdCodes;
    }

    public void setIcdCodes(List<ICD10_Code> icdCodes) {
        this.icdCodes = icdCodes;
    }

    public long getCaseId() {
        return caseId;
    }

    public void setCaseId(long caseId) {
        this.caseId = caseId;
    }

    public ICD10_Code getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(ICD10_Code icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public Collection<Diagnosis> getAllDiagnosis() {
        return allDiagnosis;
    }

    public void setAllDiagnosis(Collection<Diagnosis> allDiagnosis) {
        this.allDiagnosis = allDiagnosis;
    }

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getICDChapter() {
        return ICDChapter;
    }

    public void setICDChapter(String ICDChapter) {
        this.ICDChapter = ICDChapter;
    }

    public String getICDblock() {
        return ICDblock;
    }

    public void setICDblock(String ICDblock) {
        this.ICDblock = ICDblock;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public List<ICD10_Code> geticdCodes() {
        return icdCodes;
    }

    public void seticdCodes(List<ICD10_Code> icdCodes) {
        this.icdCodes = icdCodes;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

}
