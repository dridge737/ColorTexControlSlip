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
public class Design {
    private int DesignId;
    private String DesignName;
    private ArrayList<String> DesignList = new ArrayList<String>();

    /**
     * @return the DesignId
     */
    public int getDesignId() {
        return DesignId;
    }

    /**
     * @param DesignId the DesignId to set
     */
    public void setDesignId(int DesignId) {
        this.DesignId = DesignId;
    }

    /**
     * @return the DesignName
     */
    public String getDesignName() {
        return DesignName;
    }

    /**
     * @param DesignName the DesignName to set
     */
    public void setDesignName(String DesignName) {
        this.DesignName = DesignName;
    }

    /**
     * @return the DesignList
     */
    public ArrayList<String> getDesignList() {
        return DesignList;
    }

    /**
     * @param DesignList the DesignList to set
     */
    public void setDesignList(ArrayList<String> DesignList) {
        this.DesignList = DesignList;
    }
}
