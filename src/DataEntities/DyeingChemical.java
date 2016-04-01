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
public class DyeingChemical {
    private int ID;
    private int DyeingProcessID;
    private int ChemicalID;
    private String Type;
    private float Value;

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
    
}
