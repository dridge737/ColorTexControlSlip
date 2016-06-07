/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.DyeingProcess;
import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author imbuenyson
 */
public class DyeingProcessHandler {
    
    public DyeingProcess GetDyeingProcessDetailsById(String dyeingProcessName, int dyeingProcessId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        DyeingProcess dyeingProcessDetails = new DyeingProcess();
                
        if(repo.CheckIfDyeingProcessExists(dyeingProcessName))
        {
            dyeingProcessDetails = repo.GetDyeingProcessDetailsByDyeingProcessId(dyeingProcessId);
        }
        return dyeingProcessDetails;
    }
    
    public int GetDyeingProcessIdByDetails(DyeingProcess thisDyeingProcess)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetDyeingProcessIDFromDyeingProcessDetails(thisDyeingProcess);
    }
    
    public ArrayList<String> GetAllDyeingProcessNamesByDyeingProgramId(int dyeingProgramId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        ArrayList<String> dyeingProcessNames = new ArrayList<String>();
                
        if(repo.CheckIfDyeingProgramExists(dyeingProgramId) != 0)
        {
            dyeingProcessNames = repo.GetAllDyeingProcessByDyeingProgramId(dyeingProgramId);
        }
        return dyeingProcessNames;
    }
    
    public int AddDyeingProcess(DyeingProcess newDyeingProcess)
    {
        boolean isSuccessful = false;
        int ID = -1;
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        //if(!repo.CheckIfDyeingProcessExists(newDyeingProcess.getDyeingProcessName()))
        //{
        ID = repo.AddDyeingProcess(newDyeingProcess);
        //}
        if(ID == -1)
        {
            JOptionPane.showMessageDialog(null, "Process was not added.");
        }
        return ID;
        //if(isSuccessful == false)
        //enter validation 
        
    }
    
    public void UpdateDyeingProcess(DyeingProcess dyeingProcess)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfDyeingProcessExists(dyeingProcess.getDyeingProcessName()))
        {
            isSuccessful = repo.UpdateDyeingProcessByDyeingProcessID(dyeingProcess);
        }
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void DeleteDyeingProcessById(String dyeingProcessName, int dyeingProcessId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfDyeingProcessExists(dyeingProcessName))
        {
            isSuccessful = repo.DeleteDyeingProcessByDyeingProcessID(dyeingProcessId);
        }
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
}
