/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

import java.util.ArrayList;

/**
 *
 * @author imbuenyson
 */
public class DyeingProgram {

    private int ID;
    //private String dyeingProgramName;
    private int dyeingProgramNameID;
    private int customerID;
    private int colorID;
    private int designID;
    private ArrayList<DyeingProcess> ChemicalNamesList = new ArrayList<DyeingProcess>();
    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the colorID
     */
    public int getColorID() {
        return colorID;
    }

    /**
     * @param colorID the colorID to set
     */
    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    /**
     * @return the designID
     */
    public int getDesignID() {
        return designID;
    }

    /**
     * @param designID the designID to set
     */
    public void setDesignID(int designID) {
        this.designID = designID;
    }
    
    
    public int getID() {
        return ID;
    }

    /**
     * @param customerId the Customer_id to set
     */
    public void SetID(int dyeingProgramId) {
        this.ID = dyeingProgramId;
    }
    
        /**
     * @return the Customer_name
     */
    //public String getDyeingProgramName() {
      //  return dyeingProgramName;
    //}

    /**
     * @param Customer_name the Customer_name to set
     */
    //public void setDyeingProgramName(String dyeingProgramName) {
    //    this.dyeingProgramName = dyeingProgramName.toUpperCase();
    //}

    /**
     * @return the dyeingProgramNameID
     */
    public int getDyeingProgramNameID() {
        return dyeingProgramNameID;
    }

    /**
     * @param dyeingProgramNameID the dyeingProgramNameID to set
     */
    public void setDyeingProgramNameID(int dyeingProgramNameID) {
        this.dyeingProgramNameID = dyeingProgramNameID;
    }
    
    
    
}
