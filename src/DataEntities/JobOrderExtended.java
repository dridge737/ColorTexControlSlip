/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

import java.sql.Date;
import java.util.ArrayList;

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
    private ArrayList<ResinJob> thisResinJob;

    public JobOrderExtended(){}
    
    public JobOrderExtended(JobOrder JobOrderDetails) {
        this.setID(JobOrderDetails.getID());
        this.setDrNumber(JobOrderDetails.getDrNumber());
        this.setDesignID(JobOrderDetails.getDesignID());
        this.setColorID(JobOrderDetails.getColorID());
        this.setCustomerID(JobOrderDetails.getCustomerID());
        this.setJobDate(JobOrderDetails.getJobDate());
        this.setBatchNo(JobOrderDetails.getBatchNo());
        this.setDyeingMachineID(JobOrderDetails.getDyeingMachineID());
        this.setDyeingWeight(JobOrderDetails.getDyeingWeight());
        this.setDyeingVolumeH20(JobOrderDetails.getDyeingVolumeH20());
        this.setRollLoad(JobOrderDetails.getRollLoad());
        this.setRoll(JobOrderDetails.getRoll());
        this.setDyeingProgramID(JobOrderDetails.getDyeingProgramID());
        this.setLocation(JobOrderDetails.getLocation());
        this.setReference(JobOrderDetails.getReference());
        this.setLocation(JobOrderDetails.getLocation());
        this.setProgramNumber(JobOrderDetails.getProgramNumber());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    /**
     * @return the thisResinJob
     */
    public ArrayList<ResinJob> getThisResinJob() {
        return thisResinJob;
    }

    /**
     * @param thisResinJob the thisResinJob to set
     */
    public void setThisResinJob(ArrayList<ResinJob> thisResinJob) {
        this.thisResinJob = thisResinJob;
    }
   

}
