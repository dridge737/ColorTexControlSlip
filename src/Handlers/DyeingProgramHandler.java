/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.DyeingProgram;
import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author imbuenyson
 */
public class DyeingProgramHandler {
    public DyeingProgram GetDyeingProgramDetailsById(int dyeingProgramId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        DyeingProgram dyeingProgramDetails = new DyeingProgram();
                
        if(repo.CheckIfDyeingProgramExistsUsingID(dyeingProgramId) == 1)
        {
            dyeingProgramDetails = repo.GetDyeingProgramDetailsById(dyeingProgramId);
        }
        return dyeingProgramDetails;
    }
    
    public int AddDyeingProgram(DyeingProgram newDyeingProgram)
    {
        //boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.AddDyeingProgram(newDyeingProgram);
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
    
    public int CheckIfSpecificDyeingProgramExistForThisCustomer(String DyeingProgramName , int CustomerID)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.CheckIfSpecificDyeingProgramExistsForThisCustomer(DyeingProgramName, CustomerID);
    }
    
    public int GetDyeingProgramIDForCustomerDyeingProgram(String DyeingProgramName , int CustomerID)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetDyeingProgramIDForThisCustomer(DyeingProgramName, CustomerID);
    }

    public int getDefaultProgramIDForThisDyeingProgramNameID(int DyeingProgramNameID) {
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.getDefaultDyeingProgramForThisDyeingProgramID(DyeingProgramNameID);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
