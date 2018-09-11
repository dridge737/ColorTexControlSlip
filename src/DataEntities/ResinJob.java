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
public class ResinJob {

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

    /**
     * @return the ResinVolH2O
     */
    public float getResinVolH2O() {
        return ResinVolH2O;
    }

    /**
     * @param ResinVolH2o the ResinVolH2O to set
     */
    public void setResinVolH2O(float ResinVolH2o) {
        this.ResinVolH2O = ResinVolH2o;
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
    private int ID;
    private int ResinMachineID;
    private float ResinWeight;
    private float ResinVolH2O;
    private int ResinProgramID;
    private int JobOrderID;
    
}
