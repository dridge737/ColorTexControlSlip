/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Database.ColorTextControlSlipRepository;
import DataEntities.Machine;
/**
 *
 * @author imbuenyson
 */
public class MachineHandler {
    
    public Machine GetMachineDetailsById(int machineId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        Machine machineDetails = new Machine();
                
        if(repo.CheckIfMachineExists(machineId) == 0)
        {
            machineDetails = repo.GetMachineDetailsById(machineId);
        }
        return machineDetails;
    }
    
    public void AddNewMachine(Machine newMachine)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfMachineExists(newMachine.getMachineId()) == 0)
        {
            isSuccessful = repo.AddMachine(newMachine);
        }
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void UpdateMachine(Machine machine)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfMachineExists(machine.getMachineId()) == 0)
        {
            isSuccessful = repo.UpdateMachineByMachineId(machine);
        }
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void DeleteMachineById(int machineId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfMachineExists(machineId) == 0)
        {
            isSuccessful = repo.DeleteMachineByMachineId(machineId);
        }
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public boolean CheckIfMachineExists(int machineId)
     {
         boolean isExisting = true;
         
         ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfMachineExists(machineId) == 0)
        {
            isExisting = false;
        }
        
        return isExisting;
     }
    
}
