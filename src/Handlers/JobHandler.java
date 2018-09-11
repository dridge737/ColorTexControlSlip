/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.DyeingChemical;
import DataEntities.JobOrder;
import DataEntities.JobOrderExtended;
import DataEntities.ResinChemical;
import Database.ColorTextControlSlipRepository;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Eldridge
 */
public class JobHandler {
    public int AddNewJobOrder(JobOrder newJobOrder) {
        //boolean isSuccessful = false;
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.AddJobOrder(newJobOrder);
    }
    
    public boolean UpdateJobOrder(JobOrder thisJobOrder)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        return repo.UpdateJobOrderByJobOrderID(thisJobOrder);
        
    }
    
     public void DeleteJobOrder(int JobOrderId)
    {
        boolean isSuccessful = false;
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteJobOrderByJobOrderID(JobOrderId);
    }
     
     public JobOrder GetJobOrderDetailsFromJobOrderJODateJODyeingProgram(JobOrder thisJobOrder)
     {
         ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetJobOrderDetailsFromJobOrder(thisJobOrder);
     }
     
     public JobOrder GetJobOrderDetailsFromJobId(int JobOrderID)
     {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetJobOrderDetailsFromJobOrderID(JobOrderID);
     }
     
     public JobOrder GetJobOrderDetailsFromDrNumber(String DrNumber)
     {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetJobOrderDetailsFromDrNumber(DrNumber);
     }
     
     public JobOrder GetJobOrderDetailsFromJobOrderID(int JobOrderID)
     {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetJobOrderDetailsFromJobOrderID(JobOrderID);
     }


    public boolean CheckIfThisJobOrderHasBeenAdded(JobOrder thisJob) {
        boolean itExist = false;
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        if(repo.CheckIfJobOrderExists(thisJob) == 1)
            itExist = true;
        return !itExist;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<JobOrder> GetAllJobOrderHistoryByCustomerId (int customerID)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllJobOrderHistoryByCustomerId(customerID);
    }
    
    public ArrayList<JobOrder> GetAllJobOrderHistoryByJobOrderNumber (int customerID)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllJobOrderHistoryByJobOrderNumber(customerID);
    }
    
    public ArrayList<JobOrderExtended> GetAllExtendedJobOrderDetails()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetConnectedJobOrderDetails();
    }
    
    public ArrayList<JobOrderExtended> GetCustomerSpecificExtendedJobOrderDetails(int customerId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetConnectedJobOrderDetails(customerId);
    }
    
    public ArrayList<JobOrderExtended> GetExtendedJobOrderDetails(int customerID)
    {
        ArrayList<JobOrderExtended> thisJobOrder = new ArrayList<>();
        if(customerID != 0)
            thisJobOrder =  GetCustomerSpecificExtendedJobOrderDetails(customerID);
        else
            thisJobOrder = GetAllExtendedJobOrderDetails();
        
        return thisJobOrder;
    }
    

}
