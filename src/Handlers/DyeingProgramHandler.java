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
    
    public void AddDyeingProgram(DyeingProgram newDyeingProgram)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfDyeingProgramExists(newDyeingProgram.getDyeingProgramName()) == 0)
        {
            isSuccessful = repo.AddDyeingProgram(newDyeingProgram);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Dyeing Program with the same name may have already been added.");//enter validation 
        }
    }
    
    public boolean UpdateDyeingProgram(DyeingProgram dyeingProgram)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        if(repo.CheckIfDyeingProgramNameOnOtherIDExists(dyeingProgram) == 0)
        {
            isSuccessful = repo.UpdateDyeingProgramByDyeingProgramId(dyeingProgram);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Dyeing Program with the same name has already been added.");
        }
        
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
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public int GetDyeingProgramIDfromName(String Name)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetDyeingProgramIDFromName(Name);
    }
    
    public ArrayList<String> GetAllDyeingProgram()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllDyeingProgram();
    }
    
    public ArrayList<String> GetAllDefaultDyeingProgramName()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllDyeingProgram();
    }
    
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
    
}
