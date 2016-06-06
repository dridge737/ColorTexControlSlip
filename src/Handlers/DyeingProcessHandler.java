/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.DyeingProcess;
import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;
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
    
    public void AddDyeingProcess(DyeingProcess newDyeingProcess)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(!repo.CheckIfDyeingProcessExists(newDyeingProcess.getDyeingProcessName()))
        {
            isSuccessful = repo.AddDyeingProcess(newDyeingProcess);
        }
        
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
