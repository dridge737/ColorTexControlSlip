/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.DyeingProgramName;
import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Eldridge
 */
public class DyeingProgramNameHandler {
    public int AddDyeingProgramName(String DyeingProgramName)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.AddDyeingProgramName(DyeingProgramName);
    }
    
    public boolean UpdateDyeingProgramName(DyeingProgramName thisDyeProgName)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.UpdateDyeingProgramName(thisDyeProgName);
        
    }
    
    public String GetDyeingProgramNameFromID(int ID )
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository(); 
        return repo.GetDyeingProgramNameFromID(ID);
    }
    
    public int GetDyeingProgramNameIDfromName(String Name)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetDyeingProgramNameIDFromName(Name);
    }
    
    public ArrayList<String> GetAllDefaultDyeingProgramName()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllDyeingProgramName();
    }
    
    
    //Updated for dyeing_program_name Table
    public ArrayList<String> GetAllDyeingProgramName()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllDyeingProgramName();
    }
    
    public boolean CheckIfDyeingProgramNameHasBeenAdded(String Name)
    {
        boolean Exists = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        //Returns 0 if Dyeing ProgramName does not exist
        if(repo.CheckIfDyeingProgramNameExists(Name) != 0)
        {
            JOptionPane.showMessageDialog(null, "Dyeing Program with the name : "+Name+ " has already been added.");//enter validation 
            Exists = true;
        }
        return Exists;
    }

    public boolean CheckIfDyeingProgramNameHasBeenAddedtoOtherID(DyeingProgramName thisDyeingProgramName)
    {
        boolean Exists = false;
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        if(repo.CheckIfDyeingProgramNameOnOtherIDExists(thisDyeingProgramName) == 0)
        {
            JOptionPane.showMessageDialog(null, "Dyeing Program with the name" +thisDyeingProgramName.getDyeingProgramName()+ "has already been added.");
            Exists = true;
        }
        return Exists;
    }

}
