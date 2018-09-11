/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

/**
 *
 * @author Eldridge
 */
public class ResinProgram {
    
    private int ID;
    private int ProgramNameID;
    private int customerID;
    private int designID;
    private int colorID;
    
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
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the Name
     */
    public int getProgramNameID() {
        return ProgramNameID;
    }

    /**
     * @param Name the Name to set
     */
    public void setProgramNameID(int ProgramNameID) {
        this.ProgramNameID = ProgramNameID;
    }
    
    
}
