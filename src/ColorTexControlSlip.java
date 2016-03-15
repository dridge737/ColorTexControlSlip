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
        Customer customer = new Customer();
        customer.setCustomerName("EldridgePanget");
        customer.setCustomerId(1);
        
        CustomerHandler handler = new CustomerHandler();
        handler.DeleteCustomer(customer.getCustomerId());
    }
    
}
