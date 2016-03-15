/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DataEntities.Customer;
import Handlers.CustomerHandler;
/**
 *
 * @author imbuenyson
 */
public class ColorTexControlSlip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomeIid(1);
        newCustomer.setCustomerName("Eldridge");
        
        CustomerHandler handler = new CustomerHandler();
        handler.AddNewCustomer(newCustomer);
    }
    
}
