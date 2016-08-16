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
public class ProcessOrderHandler {
     public int AddNewProcessOrder(ProcessOrder thisProcessOrder)
    {
        //boolean isSuccessful = false;
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        return repo.AddProcessOrder(thisProcessOrder);
    }
    
    public boolean UpdateProcessOrder(ProcessOrder thisProcessOrder)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.UpdateProcessOrderByProcessOrderId(thisProcessOrder);
        return isSuccessful;        
    }
    
    public void DeleteProcessOrder(int ProcessID)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteProcessOrderByProcessOrderId(ProcessID);
        
        if(isSuccessful == false)
        {
            
        }
    }
}
