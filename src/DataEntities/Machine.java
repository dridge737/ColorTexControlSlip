/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;
/**
 *
 * @author imbuenyson
 */
public class Machine {
    private int machineId;
    private String machineName;
    private int maxCapacity;
    private int minCapacity;
    private int maxVolume;
    private int minVolume;
    private int NumOfLoad;
    
    public int getMachineId() {
        return machineId;
    }

    /**
     * @param customerId the Customer_id to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    
        /**
     * @return the Customer_name
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * @param Customer_name the Customer_name to set
     */
    public void setMachineName(String machineName) {
        this.machineName = machineName.toUpperCase();
    }
    
    public void setMaxCapacity(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }
    
    public int getMaxCapacity(){
        return maxCapacity;
    }
    
    public void setMinCapacity(int minCapacity){
        this.minCapacity = minCapacity;
    }
    
    public int getMinCapacity(){
        return minCapacity;
    }
    
    public void setMaxVolume(int maxVolume){
        this.maxVolume = maxVolume;
    }
    
    public int getMaxVolume(){
        return maxVolume;
    }
    
    public void setMinVolume(int minVolume){
        this.minVolume = minVolume;
    }
    
    public int getMinVolume(){
        return minVolume;
    }

    /**
     * @return the NumOfLoad
     */
    public int getNumOfLoad() {
        return NumOfLoad;
    }

    /**
     * @param NumOfLoad the NumOfLoad to set
     */
    public void setNumOfLoad(int NumOfLoad) {
        this.NumOfLoad = NumOfLoad;
    }
}
