/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.DyeingProgram;
import DataEntities.JobOrder;
import DataEntities.JobOrderExtended;
import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author imbuenyson
 */
public class DyeingProgramHandler {
    
    
    public int AddDyeingProgram(DyeingProgram newDyeingProgram)
    {
        //boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.AddDyeingProgram(newDyeingProgram);
    }
    public boolean SetCurrentFlagToNo(DyeingProgram dyeingProgram)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        isSuccessful = repo.SetCurrentFlagToNo(dyeingProgram);
        
        return isSuccessful;
    }
    public boolean UpdateDyeingProgram(DyeingProgram dyeingProgram)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        isSuccessful = repo.UpdateDyeingProgramByDyeingProgramId(dyeingProgram);
        
        return isSuccessful;
    }
    
    public void DeleteDyeingProgramById(int dyeingProgramId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfDyeingProgramExistsUsingID(dyeingProgramId) == 0)
        {
            isSuccessful = repo.DeleteDyeingProgramByDyeingProgramId(dyeingProgramId);
        }
        
    }
    
    //public int GetDyeingProgramIDfromName(String Name)
    //{
    //    ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
    //    return repo.GetDyeingProgramNameIDFromName(Name);
    //}
    
    public boolean CheckIfSpecificDyeingProgramExistForThisCustomer(String DyeingProgramName , JobOrderExtended thisJob)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.CheckIfSpecificDyeingProgramExistsForThisCustomer(DyeingProgramName, thisJob) == 1;
    }
    
    public boolean CheckIfSpecificDyeingProgramExistForThisCustomer(DyeingProgram thisDyeingProgram)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.CheckIfSpecificDyeingProgramExistsForThisCustomer(thisDyeingProgram) == 1;
    }
    
    public int GetDyeingProgramIDForCustomerDyeingProgram(DyeingProgram thisDyeingProgram)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetDyeingProgramIDForThisDyeingProgramDetails(thisDyeingProgram);
    }
    
    public int GetDyeingProgramIDForCustomerDyeingProgram(String DyeingProgramName , JobOrderExtended thisJob)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetDyeingProgramIDForThisCustomer(DyeingProgramName, thisJob);
    }
    
    public DyeingProgram GetDyeingProgramDetailsById(int dyeingProgramId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        //DyeingProgram dyeingProgramDetails = new DyeingProgram();
                
        //if(repo.CheckIfDyeingProgramExistsUsingID(dyeingProgramId) == 1)
        //{
        //    dyeingProgramDetails = repo.GetDyeingProgramDetailsById(dyeingProgramId);
        // dyeingProgramDetails;
        //}
            return repo.GetDyeingProgramDetailsById(dyeingProgramId);
    }
    
    //Requires values from DyeingProgramName ID, Customer ID, Color ID and Design ID
    public DyeingProgram getDefaultProgramIDForThisDyeingProgramNameID(DyeingProgram thisDyeingProgram) {
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.getDefaultDyeingProgramForThisDyeingProgramID(thisDyeingProgram);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
