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
public class JobOrder {
    private int ID;
    private String DrNumber;
    private int DesignID;
    private int ColorID;
    private int CustomerID;
    private String jobDate;
    private int BatchNo;
    private int DyeingMachineID;
    private float DyeingVolumeH20;
    private float DyeingWeight;
    private String RollLoad;
    private int ResinMachineID;
    private float ResinVolumeH20;
    private float ResinWeight;
    private float Roll;
    private int DyeingProgramID;
    private int ResinProgramID;
    private String Reference;
    private String ProgramNumber;
    private String Location;
    

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
     * @return the DrNumber
     */
    public String getDrNumber() {
        return DrNumber;
    }

    /**
     * @param DrNumber the DrNumber to set
     */
    public void setDrNumber(String DrNumber) {
        this.DrNumber = DrNumber.toUpperCase();
    }

    /**
     * @return the DyeingMachineID
     */
    public int getMachineID() {
        return DyeingMachineID;
    }

    /**
     * @param MachineID the DyeingMachineID to set
     */
    public void setMachineID(int MachineID) {
        this.DyeingMachineID = MachineID;
    }

    /**
     * @return the DesignID
     */
    public int getDesignID() {
        return DesignID;
    }

    /**
     * @param DesignID the DesignID to set
     */
    public void setDesignID(int DesignID) {
        this.DesignID = DesignID;
    }

    /**
     * @return the ColorID
     */
    public int getColorID() {
        return ColorID;
    }

    /**
     * @param ColorID the ColorID to set
     */
    public void setColorID(int ColorID) {
        this.ColorID = ColorID;
    }

    /**
     * @return the CustomerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    

    /**
     * @return the BatchNo
     */
    public int getBatchNo() {
        return BatchNo;
    }

    /**
     * @param BatchNo the BatchNo to set
     */
    public void setBatchNo(int BatchNo) {
        
        this.BatchNo = BatchNo;
    }

    /**
     * @return the DyeingWeight
     */
    public float getWeight() {
        return DyeingWeight;
    }

    /**
     * @param Weight the DyeingWeight to set
     */
    public void setWeight(float Weight) {
        this.DyeingWeight = Weight;
    }

    /**
     * @return the DyeingVolumeH20
     */
    public float getVolumeH20() {
        return DyeingVolumeH20;
    }

    /**
     * @param VolumeH20 the DyeingVolumeH20 to set
     */
    public void setVolumeH20(float VolumeH20) {
        this.DyeingVolumeH20 = VolumeH20;
    }

    /**
     * @return the RollLoad
     */
    public String getRollLoad() {
        return RollLoad;
    }

    /**
     * @param RollLoad the RollLoad to set
     */
    public void setRollLoad(String RollLoad) {
        this.RollLoad = RollLoad;
    }

    /**
     * @return the Roll
     */
    public float getRoll() {
        return Roll;
    }

    /**
     * @param Roll the Roll to set
     */
    public void setRoll(float Roll) {
        this.Roll = Roll;
    }

    /**
     * @return the DyeingProgramID
     */
    public int getDyeingProgramID() {
        return DyeingProgramID;
    }

    /**
     * @param DyeingProgramID the DyeingProgramID to set
     */
    public void setDyeingProgramID(int DyeingProgramID) {
        this.DyeingProgramID = DyeingProgramID;
    }

    /**
     * @return the ResinProgramID
     */
    public int getResinProgramID() {
        return ResinProgramID;
    }

    /**
     * @param ResinProgramID the ResinProgramID to set
     */
    public void setResinProgramID(int ResinProgramID) {
        this.ResinProgramID = ResinProgramID;
    }

    /**
     * @return the jobDate
     */
    public String getJobDate() {
        return jobDate;
    }

    /**
     * @param jobDate the jobDate to set
     */
    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    /**
     * @return the Reference
     */
    public String getReference() {
        return Reference;
    }

    /**
     * @param Reference the Reference to set
     */
    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    /**
     * @return the ProgramNumber
     */
    public String getProgramNumber() {
        return ProgramNumber;
    }

    /**
     * @param ProgramNumber the ProgramNumber to set
     */
    public void setProgramNumber(String ProgramNumber) {
        this.ProgramNumber = ProgramNumber;
    }

    /**
     * @return the Location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * @param Location the Location to set
     */
    public void setLocation(String Location) {
        this.Location = Location;
    }

    /**
     * @return the ResinMachineID
     */
    public int getResinMachineID() {
        return ResinMachineID;
    }

    /**
     * @param ResinMachineID the ResinMachineID to set
     */
    public void setResinMachineID(int ResinMachineID) {
        this.ResinMachineID = ResinMachineID;
    }

    /**
     * @return the ResinVolumeH20
     */
    public float getResinVolumeH20() {
        return ResinVolumeH20;
    }

    /**
     * @param ResinVolumeH20 the ResinVolumeH20 to set
     */
    public void setResinVolumeH20(float ResinVolumeH20) {
        this.ResinVolumeH20 = ResinVolumeH20;
    }

    /**
     * @return the ResinWeight
     */
    public float getResinWeight() {
        return ResinWeight;
    }

    /**
     * @param ResinWeight the ResinWeight to set
     */
    public void setResinWeight(float ResinWeight) {
        this.ResinWeight = ResinWeight;
    }

    

}
