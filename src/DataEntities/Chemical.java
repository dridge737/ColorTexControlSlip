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
public class Chemical {
    private int ChemicalId;
    private String ChemicalName;
    private float Cost;
    private ArrayList<String> ChemicalNamesList = new ArrayList<String>();

    /**
     * @return the ChemicalId
     */
    public int getChemicalId() {
        return ChemicalId;
    }

    /**
     * @param ChemicalId the ChemicalId to set
     */
    public void setChemicalId(int ChemicalId) {
        this.ChemicalId = ChemicalId;
    }

    /**
     * @return the ChemicalName
     */
    public String getChemicalName() {
        return ChemicalName;
    }

    /**
     * @param ChemicalName the ChemicalName to set
     */
    public void setChemicalName(String ChemicalName) {
        this.ChemicalName = ChemicalName;
    }

    /**
     * @return the Cost
     */
    public float getCost() {
        return Cost;
    }

    /**
     * @param Cost the Cost to set
     */
    public void setCost(float Cost) {
        this.Cost = Cost;
    }

    /**
     * @return the ChemicalNamesList
     */
    public ArrayList<String> getChemicalNamesList() {
        return ChemicalNamesList;
    }

    /**
     * @param ChemicalNamesList the ChemicalNamesList to set
     */
    public void setChemicalNamesList(ArrayList<String> ChemicalNamesList) {
        this.ChemicalNamesList = ChemicalNamesList;
    }
}
