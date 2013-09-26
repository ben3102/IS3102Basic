/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.PatientAdministrationBean;


import com.is3102.EntityClass.Doctor;
import com.is3102.EntityClass.Schedule;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import com.is3102.Interface.AdministrativeDischargeandBillingRemote;
import java.util.Date;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Ashish
 */

@ManagedBean
public class AdministrativeDischargeandBillingBean implements AdministrativeDischargeandBillingRemote  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext()
    EntityManager em;
    mCase mcase;
    
    
      public void CalculateBill() throws Exception {  
         
     }
      
      public void setDischargeDate(Long cin)throws ExistException{
          Date date = new Date();
          mcase = em.find(mCase.class, cin);
          mcase.setdateDischarged(date);
                  if (mcase == null)
            throw new ExistException("MCASE ID DOES NOT EXIST.");
          
      }

}
