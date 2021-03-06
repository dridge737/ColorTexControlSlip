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
    
    public DyeingProcess GetDyeingProcessDetailsById(int dyeingProcessId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        DyeingProcess dyeingProcessDetails = new DyeingProcess();
                
        if(repo.CheckIfDyeingProcessExistsWithThisID(dyeingProcessId))
        {
            dyeingProcessDetails = repo.GetDyeingProcessDetailsByDyeingProcessId(dyeingProcessId);
        }
        return dyeingProcessDetails;
    }
    
    public DyeingProcess GetDyeingProcessDetailsByDyeingProgramIdAndProcessOrder(DyeingProcess ThisProcess)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        DyeingProcess dyeingProcessDetails = new DyeingProcess();
        dyeingProcessDetails = repo.GetDyeingProcessDetailsByDyeingProgramIdAndProcessOrder(ThisProcess.getDyeingProgramId(), Integer.parseInt(ThisProcess.getdyeingProcessOrder()));
        return dyeingProcessDetails;
    }
    
    public ArrayList<DyeingProcess> GetAllDyeingProcessByDyeingProgramId(int dyeingProgramId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
                
        //if(repo.CheckIfDyeingProcessExists(dyeingProcessName))
        //{
            return repo.GetDyeingProcessDetailsByDyeingProgramId(dyeingProgramId);
        //}
        //return dyeingProcessDetails;
    }
    
    public ArrayList<DyeingProcess> GetAllDyeingProcessAndSubProcessByDyeingProgramId(int dyeingProgramId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        //DyeingProcess dyeingProcessDetails = new DyeingProcess();
        ArrayList<DyeingProcess> dyeingProcessListNoSub = repo.GetDyeingProcessDetailsByDyeingProgramId(dyeingProgramId);
        ArrayList<DyeingProcess> dyeingProcessList = new ArrayList<DyeingProcess>();
        
        for(DyeingProcess dyeingProcess: dyeingProcessListNoSub)
        {
            int TotalNumberOfSubProcess = CountNumberOfSubProcess(dyeingProcess);
            dyeingProcessList.add(dyeingProcess);
            if(TotalNumberOfSubProcess > 0)
            {
                dyeingProcessList.addAll(GetDyeingSubProcessByDyeingProgramIdAndProcessOrder(dyeingProcess));
            }
        }
        
                
        //if(repo.CheckIfDyeingProcessExists(dyeingProcessName))
        //{
            return dyeingProcessList;
        //}
        //return dyeingProcessDetails;
    }
    
    public ArrayList<DyeingProcess> GetDyeingSubProcessByDyeingProgramIdAndProcessOrder(DyeingProcess ThisProcess)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
//       DyeingProcess dyeingProcessDetails = new DyeingProcess();
        
        return repo.GetDyeingSubProcessDetailsByDyeingProgramIdAndProcessOrder(ThisProcess.getDyeingProgramId() , Integer.parseInt(ThisProcess.getdyeingProcessOrder()));
        //return dyeingProcessDetails;
    }
    
    public int CountNumberOfProcess (int DyeingProgramID)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.CountNumberOfDyeingProcess(DyeingProgramID);
    }
    public int CountNumberOfSubProcess(DyeingProcess thisProcess)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.CountNumberOfDyeingSubProcess(thisProcess.getDyeingProgramId(),Integer.parseInt(thisProcess.getdyeingProcessOrder()));
    }
    
    public int GetDyeingProcessIdByDetails(DyeingProcess thisDyeingProcess)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetDyeingProcessIDFromDyeingProcessDetails(thisDyeingProcess);
    }
    
    public ArrayList<String> GetAllDyeingProcessNamesByDyeingProgramId(DyeingProcess thisDyeingProcess)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        ArrayList<String> dyeingProcessNames = new ArrayList<String>();
                
        //if(repo.CheckIfDyeingProcessExists(thisDyeingProcess) == 0)
        //{
            dyeingProcessNames = repo.GetAllDyeingProcessByDyeingProgramId(thisDyeingProcess.getDyeingProgramId());
        //}
        return dyeingProcessNames;
    }
    
    public int AddDyeingProcess(DyeingProcess newDyeingProcess)
    {
        int ID = -1;
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        ID = repo.AddDyeingProcess(newDyeingProcess);

        return ID;   
    }
    
    public int UpdateDyeingProcess(DyeingProcess dyeingProcess)
    {
        int ID = -1;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        //if(!repo.CheckIfDyeingProcessNameExistsOnOtherDyeingProcessID(dyeingProcess))
        //{
            ID = repo.UpdateDyeingProcessByDyeingProcessID(dyeingProcess);
        //}
        return ID;
        
    }
    
    public void DeleteDyeingProcessById(int ThisDyeingProcessID)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        //if(repo.CheckIfDyeingProcessExists(ThisDyeingProcess))
        //{
            isSuccessful = repo.DeleteDyeingProcessByDyeingProcessID(ThisDyeingProcessID);
        //}
        
    }
}
