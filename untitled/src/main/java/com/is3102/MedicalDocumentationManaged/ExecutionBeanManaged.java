/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationManaged;

import com.is3102.EntityClass.ExecutionLog;
import com.is3102.Interface.ExecutionRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ashish
 */
@ManagedBean
public class ExecutionBeanManaged {
@EJB
private static ExecutionRemote ex;
//Id of procedure instance associate with a case
private Long procedure_id; 
//Doctor id
private Long doctor_id;
//Execution comment documented at the time of completion
private String exeuction_comment;
//list of execution log comments associated with one procedure entity
private List<ExecutionLog> executionLogList;

   public void DoAddExecutionLog(){
      try{
          ex.AddExecutionRecord(procedure_id, doctor_id, exeuction_comment);
      }catch(Exception ex){
          System.out.println(ex.getMessage());
      }
   }
   /*public void AddExecutionRecord(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException;

    public List<ExecutionLog> CreateEvaluationReport(Long procedure_id) throws ExistException;*/
   public void DoCreateEvaluationReport(){
       try{
           List<ExecutionLog> result = ex.CreateEvaluationReport(procedure_id);
           this.setExecutionLogList(result);
                   }catch(Exception ex){
                     System.out.println(ex.getMessage());  
                   }
   }
   
    public Long getProcedure_id() {
        return procedure_id;
    }

    public void setProcedure_id(Long procedure_id) {
        this.procedure_id = procedure_id;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getExeuction_comment() {
        return exeuction_comment;
    }

    public void setExeuction_comment(String exeuction_comment) {
        this.exeuction_comment = exeuction_comment;
    }

    public List<ExecutionLog> getExecutionLogList() {
        return executionLogList;
    }

    public void setExecutionLogList(List<ExecutionLog> executionLogList) {
        this.executionLogList = executionLogList;
    }

}

