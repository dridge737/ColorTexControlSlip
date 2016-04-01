/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.DyeingProgram;
import Database.ColorTextControlSlipRepository;
/**
 *
 * @author imbuenyson
 */
public class DyeingProgramHandler {
    public DyeingProgram GetDyeingProgramDetailsById(int dyeingProgramId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        DyeingProgram dyeingProgramDetails = new DyeingProgram();
                
        if(repo.CheckIfDyeingProgramExists(dyeingProgramId) == 0)
        {
            dyeingProgramDetails = repo.GetDyeingProgramDetailsById(dyeingProgramId);
        }
        return dyeingProgramDetails;
    }
    
    public void AddDyeingProgram(DyeingProgram newDyeingProgram)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfDyeingProgramExists(newDyeingProgram.getDyeingProgramId()) == 0)
        {
            isSuccessful = repo.AddDyeingProgram(newDyeingProgram);
        }
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void UpdateDyeingProgram(DyeingProgram dyeingProgram)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfDyeingProgramExists(dyeingProgram.getDyeingProgramId()) == 0)
        {
            isSuccessful = repo.UpdateDyeingProgramByDyeingProgramId(dyeingProgram);
        }
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void DeleteDyeingProgramById(int dyeingProgramId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfDyeingProgramExists(dyeingProgramId) == 0)
        {
            isSuccessful = repo.DeleteDyeingProgramByDyeingProgramId(dyeingProgramId);
        }
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
}
