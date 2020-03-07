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
        
        machineList = repo.GetAllMachine(null);
        
        return machineList;
        
    }
    
    public ArrayList<Machine> GetAllDyeingMachines()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        ArrayList<Machine> machineList = new ArrayList<>();
        
        machineList = repo.GetAllDyeingMachine();
        
        return machineList;
    }
    
    public int CountAllDyeingMachine()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.CountNumberOfDyeingMachine();
    }
    
    public int CountResinMachine()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.CountNumberOfResinMachine();
    }
    
    public ArrayList<Machine> GetAllResinMachines()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        ArrayList<Machine> machineList = new ArrayList<>();
        
        machineList = repo.GetAllResinMachine();
        
        return machineList;
    }
    
    public ArrayList<Machine> GetAllManualResinMachines()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        //ArrayList<Machine> machineList = new ArrayList<>();
        
        return repo.GetAllManualInputResinMachine();
    }
    
    public ArrayList<Machine> GetAllAutomaticResinMachines()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        //ArrayList<Machine> machineList = new ArrayList<>();
        
        return repo.GetAllAutomaticInputResinMachine();
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
    
    public void AddDryerAndStenterMachine()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        if(repo.CheckIfMachineNameExists("Dryer") == 0)
            repo.AddDryerAndStenter();
       
    }
    
    public float ComputeVolumeOfWaterFromWeight(float Weight, String SelectedItem)
    {
        
        //String selected = FabricDropDown.getSelectedItem().toString();
        //float weight = Float.parseFloat(Weight.getText());
        float volume = 0;

        String[] WeightMultiplier = SelectedItem.split("\\+");
        float multiplier = Float.parseFloat(WeightMultiplier[0].replaceAll("[^0-9\\.]", ""));
        if (WeightMultiplier.length > 1) {
            volume = (float) (Weight * multiplier + Float.parseFloat(WeightMultiplier[1].replaceAll("[^0-9]", "")));
        } else {
            volume = (float) (Weight * multiplier);
        }

            
        return volume;
        //int thisResinWeight = Integer.parseInt(Weight);
        /*
        float ComputedVolume = 0;
        switch(SelectedIndex)
        {
            case 1:
                ComputedVolume = (float) ((Weight * 0.3) + 30);
                break;
            case 2:
                ComputedVolume = (float) ((Weight * 0.4) + 30);
                break;
            case 3:
                ComputedVolume = (float) ((Weight * 0.5) + 30);
                break;
            case 4:
                ComputedVolume = (float) ((Weight * 0.65) + 30);
                break;
            case 5:
                ComputedVolume = (float) (Weight * 0.5);
                break;
            
        }
        
        return ComputedVolume;*/
    }
    
}
