/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.ProcessOrder;
import Database.ColorTextControlSlipRepository;

/**
 *
 * @author Eldridge
 */
public class ProcessHandler {
     public boolean AddNewResinProgram(ProcessOrder thisProcessOrder)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.AddProcessOrder(thisProcessOrder);
        
        return isSuccessful;
    }
    
    public boolean UpdateResinProgram(ProcessOrder thisProcessOrder)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.UpdateProcessOrderByProcessOrderId(thisProcessOrder);
        return isSuccessful;        
    }
    
    public void DeleteResinProgram(int ProcessID)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteProcessOrderByProcessOrderId(ProcessID);
        
        if(isSuccessful == false)
        {
            
        }
    }
}
