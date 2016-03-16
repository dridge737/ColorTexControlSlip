/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Database.ColorTextControlSlipRepository;
import DataEntities.Customer;

/**
 *
 * @author imbuenyson
 */
public class CustomerHandler {
    
    public void AddNewCustomer(Customer customer)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.AddCustomer(customer);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void UpdateCustomer(Customer customer)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.UpdateCustomerByCustomerId(customer);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
     public void DeleteCustomer(int customerId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteCustomerByCustomerId(customerId);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
}
