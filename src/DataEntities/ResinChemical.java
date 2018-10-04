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
public class ResinChemical {

   
    private int ID;
    private int ChemicalID;
    private int ResinProgramID;
    private float GPLValue;
    private String Type;
    private String State;
    private int Order;

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
     * @return the ChemicalID
     */
    public int getChemicalID() {
        return ChemicalID;
    }

    /**
     * @param ChemicalID the ChemicalID to set
     */
    public void setChemicalID(int ChemicalID) {
        this.ChemicalID = ChemicalID;
    }

    /**
     * @return the ResinProgramID
     */
    public int getResinProgramID() {
        return ResinProgramID;
    }

    /**
     * @param ResinProgramID the ResinProgramID to set
     */
    public void setResinProgramID(int ResinProgramID) {
        this.ResinProgramID = ResinProgramID;
    }

    /**
     * @return the GPLValue
     */
    public float getGPLValue() {
        return GPLValue;
    }

    /**
     * @param GPLValue the GPLValue to set
     */
    public void setGPLValue(float GPLValue) {
        this.GPLValue = GPLValue;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return the State
     */
    public String getState() {
        return State;
    }

    /**
     * @param State the State to set
     */
    public void setState(String State) {
        this.State = State;
    }
    
     /**
     * @return the Order
     */
    public int getOrder() {
        return Order;
    }

    /**
     * @param Order the Order to set
     */
    public void setOrder(int Order) {
        this.Order = Order;
    }
           
    
}
