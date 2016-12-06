/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

/**
 *
 * @author imbuenyson
 */
public class DyeingProgram {
    private int ID;
    //private String dyeingProgramName;
    private int dyeingProgramNameID;
    private int ProgramDefault;
    
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
     * @return the ProgramDefault
     */
    public int getProgramDefault() {
        return ProgramDefault;
    }

    /**
     * @param thisProgramDefault the ProgramDefault to set
     */
    public void setProgramDefault(int thisProgramDefault) {
        this.ProgramDefault = thisProgramDefault;
    }

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
