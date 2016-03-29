/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.ResinChemical;
import Database.ColorTextControlSlipRepository;

/**
 *
 * @author Eldridge
 */
public class ResinChemicalHandler {
     public void AddNewResinChemical(ResinChemical newResinChemical)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.AddResinChemical(newResinChemical);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
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
