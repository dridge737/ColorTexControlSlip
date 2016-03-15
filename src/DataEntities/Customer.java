/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;
/**
 *
 * @author imbuenyson
 */
public class Customer {
    private int customerId;
    private String customerName;
    private ArrayList<String> customerNames = new ArrayList<String>();
    
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the Customer_id to set
     */
    public void setCustomeIid(int customerId) {
        this.customerId = customerId;
    }
    
        /**
     * @return the Customer_name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param Customer_name the Customer_name to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName.toUpperCase();
    }
}
