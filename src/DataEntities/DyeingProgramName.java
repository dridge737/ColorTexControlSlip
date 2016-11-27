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
public class DyeingProgramName {
    private int ID;
    private String DyeingProgramName;

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
     * @return the DyeingProgramName
     */
    public String getDyeingProgramName() {
        return DyeingProgramName;
    }

    /**
     * @param DyeingProgramName the DyeingProgramName to set
     */
    public void setDyeingProgramName(String DyeingProgramName) {
        this.DyeingProgramName = DyeingProgramName.toUpperCase();
    }
    
}
