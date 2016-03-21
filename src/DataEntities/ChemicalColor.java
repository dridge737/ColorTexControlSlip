/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;
import java.util.ArrayList;
/**
 *
 * @author Eldridge
 */
public class ChemicalColor {
    private int ColorId;
    private String ColorName;
    private ArrayList<String> ColorNameList = new ArrayList<String>();

    /**
     * @return the ColorId
     */
    public int getColorId() {
        return ColorId;
    }

    /**
     * @param ColorId the ColorId to set
     */
    public void setColorId(int ColorId) {
        this.ColorId = ColorId;
    }

    /**
     * @return the ColorName
     */
    public String getColorName() {
        return ColorName;
    }

    /**
     * @param ColorName the ColorName to set
     */
    public void setColorName(String ColorName) {
        this.ColorName = ColorName;
    }

    /**
     * @return the ColorNameList
     */
    public ArrayList<String> getColorNameList() {
        return ColorNameList;
    }

    /**
     * @param ColorNameList the ColorNameList to set
     */
    public void setColorNameList(ArrayList<String> ColorNameList) {
        this.ColorNameList = ColorNameList;
    }
    
    
    
}
