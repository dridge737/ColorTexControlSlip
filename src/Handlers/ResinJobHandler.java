/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.ResinChemical;
import DataEntities.ResinJob;
import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;

/**
 *
 * @author Eldridge
 */
public class ResinJobHandler {
    
    public int AddResinJob(ResinJob thisResinJob)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.AddResinJob(thisResinJob);
    }
    
    
    public boolean UpdateResinJob(ResinJob thisResinJob)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.UpdateResinJob(thisResinJob);
    }
    
    public void DeleteResinJob(ResinJob thisResinJob)
    {
        boolean isSuccessful;
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteResinJob(thisResinJob);
    }
    
    public ResinJob GetResinJobFromJobID(ResinJob thisResinJob)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetResinJobFromJobID(thisResinJob);
     
    }
    
    public ArrayList<ResinJob> GetResinJobListFromJobOrderID(int JobOrderID)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetResinJobFromJobID(JobOrderID);
    }
    
    
}
