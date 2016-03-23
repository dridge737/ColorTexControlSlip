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
    private float RollLoad;
    private float Roll;

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
     * @return the RollLoad
     */
    public float getRollLoad() {
        return RollLoad;
    }

    /**
     * @param RollLoad the RollLoad to set
     */
    public void setRollLoad(float RollLoad) {
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

   
}
