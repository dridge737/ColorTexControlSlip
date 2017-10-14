/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

import java.sql.Date;

/**
 *
 * @author Eldridge
 */
public class JobOrderExtended extends JobOrder{
    
    
    private String DesignName;
    private String ColorName;
    private String CustomerName;
    private String DyeingMachineName;
    private String ResinMachineName;
    private String DyeingProgramName;
    private String ResinProgramName;

    
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
        this.DyeingProgramName = DyeingProgramName;
    }

    /**
     * @return the ResinProgramName
     */
    public String getResinProgramName() {
        return ResinProgramName;
    }

    /**
     * @param ResinProgramName the ResinProgramName to set
     */
    public void setResinProgramName(String ResinProgramName) {
        this.ResinProgramName = ResinProgramName;
    }

    /**
     * @return the ResinMachineName
     */
    public String getResinMachineName() {
        return ResinMachineName;
    }

    /**
     * @param ResinMachineName the ResinMachineName to set
     */
    public void setResinMachineName(String ResinMachineName) {
        this.ResinMachineName = ResinMachineName;
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
     * @return the CustomerName
     */
    public String getCustomerName() {
        return CustomerName;
    }

    /**
     * @param CustomerName the CustomerName to set
     */
    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    /**
     * @return the DyeingMachineName
     */
    public String getDyeingMachineName() {
        return DyeingMachineName;
    }

    /**
     * @param DyeingMachineName the DyeingMachineName to set
     */
    public void setDyeingMachineName(String DyeingMachineName) {
        this.DyeingMachineName = DyeingMachineName;
    }
   

}
