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
    
    
    
}
