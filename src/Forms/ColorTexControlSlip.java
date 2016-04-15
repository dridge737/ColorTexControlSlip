package Forms;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DataEntities.*;
import Handlers.*;
/**
 *
 * @author imbuenyson
 */
public class ColorTexControlSlip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Customer customer = new Customer();
        customer.setCustomerName("BuenLOL");
        customer.setCustomerId(1);
        
        CustomerHandler handler = new CustomerHandler();
        handler.DeleteCustomer(customer.getCustomerId());
                */
        /*
        ChemicalColor thisColor = new ChemicalColor();
        thisColor.setColorName("Hello");
        
        ColorHandler colHandler = new ColorHandler();
        colHandler.AddNewColor(thisColor);
        boolean itExist = colHandler.CheckIfColorNameExists("Hello");
                */
        ColorForm newColorForm = new ColorForm();
        newColorForm.setVisible(true);
        
        CustomerForm newCustForm = new CustomerForm();
        newCustForm.setVisible(true);
    }
    
}
