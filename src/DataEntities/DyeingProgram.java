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
    private int dyeingProgramId;
    private String dyeingProgramName;
    private int dyeingProgramNameID;
    private int DefaultColumn;
    
    public int getDyeingProgramId() {
        return dyeingProgramId;
    }

    /**
     * @param customerId the Customer_id to set
     */
    public void setDyeingProgramId(int dyeingProgramId) {
        this.dyeingProgramId = dyeingProgramId;
    }
    
        /**
     * @return the Customer_name
     */
    public String getDyeingProgramName() {
        return dyeingProgramName;
    }

    /**
     * @param Customer_name the Customer_name to set
     */
    public void setDyeingProgramName(String dyeingProgramName) {
        this.dyeingProgramName = dyeingProgramName.toUpperCase();
    }

    /**
     * @return the DefaultColumn
     */
    public int getDefaultColumn() {
        return DefaultColumn;
    }

    /**
     * @param DefaultColumn the DefaultColumn to set
     */
    public void setDefaultColumn(int DefaultColumn) {
        this.DefaultColumn = DefaultColumn;
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
