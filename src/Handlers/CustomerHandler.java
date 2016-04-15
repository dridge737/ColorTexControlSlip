/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Database.ColorTextControlSlipRepository;
import DataEntities.Customer;
import java.util.ArrayList;

/**
 *
 * @author imbuenyson
 */
public class CustomerHandler {
    
    public boolean AddNewCustomer(Customer customer)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.AddCustomer(customer);
        
        return isSuccessful;
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
     
     public boolean CheckIfCustomerExists(String customerName)
     {
         boolean isExisting = true;
         
         ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfCustomerExists(customerName) == 0)
        {
            isExisting = false;
        }
        return isExisting;
     }
     
     public ArrayList<String> GetAllCustomers()
     {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllCustomersName();
     }
     
     public String GetCustomerNameFromCustomerID(int customerId)
     {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        return repo.GetCustomerNameById(customerId);
     }
     
     public int GetCustomerIDFromCustomerName(String customerName)
     {
         ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
         
         return repo.GetCustomerIdFromCustomerName(customerName);
     }
}
