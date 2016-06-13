/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.Chemical;
import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;

/**
 *
 * @author Eldridge
 */
public class ChemicalHandler {
     public boolean AddNewChemical(Chemical newChemical)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        if(repo.CheckIfChemicalExists(newChemical.getChemicalName()) == 0)
        {
             isSuccessful = repo.AddChemical(newChemical);
        }
       
        
        return isSuccessful;
    }
    
    public boolean UpdateChemical(Chemical thisChemical)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        if(repo.CheckIfChemicalNameOnDifferentIDExists(thisChemical) == 0)
        {
            isSuccessful = repo.UpdateChemicalByChemicalID(thisChemical);
        }
        
        return isSuccessful;
            //enter validation 
        
    }
    
    public void DeleteChemical(int ChemicalID)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteChemicalByChemicalID(ChemicalID);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
     
    public int GetChemicalIDFromChemicalName(String ChemicalName)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetChemicalIDFromChemicalName(ChemicalName);
    }
    
    public String GetChemicalNameFromChemicalID(int ChemicalID)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetChemicalNameFromChemicalID(ChemicalID);
    }
    
    public ArrayList<String> GetAllChemical()
     {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllChemicalName();
     }
}
