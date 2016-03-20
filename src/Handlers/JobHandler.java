/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.JobOrder;
import Database.ColorTextControlSlipRepository;

/**
 *
 * @author Eldridge
 */
public class JobHandler {
    public void AddNewCustomer(JobOrder newJobOrder)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
    //    isSuccessful = repo.addJobOrder(newJobOrder);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void UpdateCustomer(JobOrder thisJobOrder)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
    //    isSuccessful = repo.UpdateJobOrderByJobOrderID(thisJobOrder);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
     public void DeleteCustomer(int JobOrderId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
    //    isSuccessful = repo.DeleteJobOrderByJobOrderId(JobOrderId);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
}
