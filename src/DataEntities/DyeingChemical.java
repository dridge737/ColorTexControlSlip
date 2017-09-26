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
public class DyeingChemical extends Chemical{
    private int ID;
    private int DyeingProcessID;
    private String Type;
    private float Value;
    private int Order;
    private String State;

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
     * @return the DyeingProcessID
     */
    public int getDyeingProcessID() {
        return DyeingProcessID;
    }

    /**
     * @param DyeingProcessID the DyeingProcessID to set
     */
    public void setDyeingProcessID(int DyeingProcessID) {
        this.DyeingProcessID = DyeingProcessID;
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
     * @return the Value
     */
    public float getValue() {
        return Value;
    }

    /**
     * @param Value the Value to set
     */
    public void setValue(float Value) {
        this.Value = Value;
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
    
}
