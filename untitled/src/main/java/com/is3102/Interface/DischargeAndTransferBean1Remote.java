/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.Interface;

import com.is3102.EntityClass.DischargeSummary;
import com.is3102.EntityClass.Transfer;
import com.is3102.Exception.CaseException;
import javax.ejb.Remote;
import java.util.List;

/**
 *
 * @author Ben
 */
@Remote
public interface DischargeAndTransferBean1Remote {
  
    public void generateDischargeSummary(Long caseId, String diagnosis, String findings,
                                         String recommendation, String patientState, String medicalProcedure)throws CaseException;
    public DischargeSummary getDischargeSummary(Long dischargeId);
    public void deleteDischargeSummary(Long dischargeId);
    public List<DischargeSummary> ListDischargeSummary();
    public void recodeTransfer(Long caseId, String referDoctor, String reason)throws CaseException;
    public Transfer getTransfer(Long transferId);
    public void deleteTransfer(Long transferId);
    public List<Transfer> ListTransfer();
}
