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
public class ProcessOrder {
    private int ID;
    private int JobOrderID;
    private float Weight;
    private float VolumeH20;
    private String RollLoad;
    private float Roll;
    private int DyeingProgramID;
    private int ResinProgramID;
            

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
     * @return the Weight
     */
    public float getWeight() {
        return Weight;
    }

    /**
     * @param Weight the Weight to set
     */
    public void setWeight(float Weight) {
        this.Weight = Weight;
    }

    /**
     * @return the VolumeH20
     */
    public float getVolumeH20() {
        return VolumeH20;
    }

    /**
     * @param VolumeH20 the VolumeH20 to set
     */
    public void setVolumeH20(float VolumeH20) {
        this.VolumeH20 = VolumeH20;
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
     * @return the JobOrderID
     */
    public int getJobOrderID() {
        return JobOrderID;
    }

    /**
     * @param JobOrderID the JobOrderID to set
     */
    public void setJobOrderID(int JobOrderID) {
        this.JobOrderID = JobOrderID;
    }

    /**
     * @param RollLoad the RollLoad to set
     */
    public void setRollLoad(String RollLoad) {
        this.RollLoad = RollLoad;
    }

    /**
     * @return the RollLoad
     */
    public String getRollLoad() {
        return RollLoad;
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

   
}
