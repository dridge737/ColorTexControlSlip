/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.Design;
import Database.ColorTextControlSlipRepository;

/**
 *
 * @author Eldridge
 */
public class DesignHandler {
     public void AddNewCustomer(Design newDesign)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.AddDesign(newDesign);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void UpdateCustomer(Design thisDesign)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.UpdateDesignByDesignID(thisDesign);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
     public void DeleteCustomer(int designID)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteDesignByDesignID(designID);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
}
