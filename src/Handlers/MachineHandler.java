/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Database.ColorTextControlSlipRepository;
import DataEntities.Machine;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author imbuenyson
 */
public class MachineHandler {
    
    public String GetMachineTypeText(int MachineType)
    {
        if(MachineType == 0)
            return "Dyeing Machine";
        else
            return "Resin Machine";
    }
    
    public int GetMachineTypeInt(String MachineTypeString)
    {
        if(MachineTypeString.equals("Dyeing Machine") == true)
        {
            //Machine is for Dyeing
            return 0;
        }
        else
            //Machine is for Resin
            return 1;
                    
    }
    public ArrayList<Machine> GetAllMachines()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        ArrayList<Machine> machineList = new ArrayList<>();
        
        machineList = repo.GetAllMachine();
        
        return machineList;
    }
    
    
    public Machine GetMachineDetailsById(int machineId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        Machine machineDetails = new Machine();
                
        if(repo.CheckIfMachineExists(machineId) != 0)
        {
            machineDetails = repo.GetMachineDetailsById(machineId);
        }
        return machineDetails;
    }
    
    public int GetMachineIdByName(String machineName)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        int machineId = -1;
        
        machineId = repo.GetMachineIdByName(machineName);
        
        return machineId;
    }
    
    public boolean AddNewMachine(Machine newMachine)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfMachineExists(newMachine.getMachineId()) == 0)
        {
            isSuccessful = repo.AddMachine(newMachine);
        }
        
        //if(isSuccessful == false)
        //{
            //enter validation 
        //}
        
        return isSuccessful;
    }
    
    public boolean UpdateMachine(Machine machine)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfMachineExists(machine.getMachineId()) != 0)
        {
            isSuccessful = repo.UpdateMachineByMachineId(machine);
        }
        
        
        //if(isSuccessful == false)
        //{
            //enter validation 
        //}
        
        return isSuccessful;
    }
    
    public boolean DeleteMachineById(int machineId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfMachineExists(machineId) != 0)
        {
            isSuccessful = repo.DeleteMachineByMachineId(machineId);
        }
        
        //if(isSuccessful == false)
        //{
            //enter validation 
        //}
        
        return isSuccessful;
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
