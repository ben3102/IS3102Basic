/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.MedicalDocumentationBean;

import com.is3102.EntityClass.mCase;
import com.is3102.Interface.DischargeAndTransferBean1Remote;
import com.is3102.Interface.MedicalAdmissionBean1Remote;
import javax.ejb.EJB;

/**
 *
 * @author Ben
 */
public class Main {
    @EJB 
    public static MedicalAdmissionBean1Remote MedicalAdmissionBean1;
    @EJB
    public static DischargeAndTransferBean1Remote DischargeAndTransferBean1;
    public Main(){}
    
    public static void main(String[] args){
        mCase mcase=new mCase();
    }
    
}
