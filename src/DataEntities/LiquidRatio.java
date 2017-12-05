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
public class LiquidRatio {
    private int ID;
    private String liquid_ratio_value;

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
     * @return the liquid_ratio_value
     */
    public String getLiquid_ratio_value() {
        return liquid_ratio_value;
    }

    /**
     * @param liquid_ratio_value the liquid_ratio_value to set
     */
    public void setLiquid_ratio_value(String liquid_ratio_value) {
        this.liquid_ratio_value = liquid_ratio_value;
    }
    
}
