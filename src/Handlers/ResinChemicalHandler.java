/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.ResinChemical;
import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;

/**
 *
 * @author Eldridge
 */
public class ResinChemicalHandler {
    public ArrayList<ResinChemical> GetResinChemicalsByResinProgramId(int resinProgramId)
    {
        ArrayList<ResinChemical> resinChemicals;
        resinChemicals = new ArrayList<ResinChemical>();
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        resinChemicals = repo.GetResinChemicalsByResinProgramId(resinProgramId);
        
        return resinChemicals;
    }
    
    public boolean AddNewResinChemical(ResinChemical newResinChemical)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.AddResinChemical(newResinChemical);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
        
        return isSuccessful;
    }
     
     public boolean DeleteResinChemicalByResinProgramId(int id)
     {
         boolean isSuccessful = false;
         
         ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
         
         isSuccessful = repo.DeleteResinChemicalByResinProgramID(id);
         
         return isSuccessful;
     }
    
    public void UpdateResinChemical(ResinChemical thisResinChemical)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.UpdateResinChemicalByResinChemicalID(thisResinChemical);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
     public void DeleteResinChemical(int ResinChemicalID)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteResinChemicalByResinChemicalID(ResinChemicalID);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
}
