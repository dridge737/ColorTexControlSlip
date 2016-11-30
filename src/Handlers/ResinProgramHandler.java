/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;
import DataEntities.ResinProgram;
import DataEntities.ResinProgramName;
import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;

/**
 *
 * @author Eldridge
 */
public class ResinProgramHandler {
     public int AddNewResinProgramName(ResinProgramName newResinProgramName)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        int resinProgramNameId = repo.AddNewResinProgramName(newResinProgramName);
        
        return resinProgramNameId;
    }
     
    public int AddNewResinProgram(ResinProgram resinProgram)
    {
        int resinProgramId = -1;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        resinProgramId = repo.AddNewResinProgram(resinProgram);
        
        return resinProgramId;
    }
    
    public boolean UpdateResinProgramName(String newProgramName, String oldProgramName)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.UpdateResinProgramName(newProgramName, oldProgramName);
        
        return isSuccessful;     
    }
    
    public boolean UpdateResinProgram(ResinProgram newResinProgram)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.UpdateResinProgramByResinId(newResinProgram);
        return isSuccessful;        
    }
    
    public void DeleteResinProgram(int ResinId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteResinProgramByResinProgramId(ResinId);
        
        if(isSuccessful == false)
        {
            
        }
    }
    
    public void DeleteResinProgramName(int resinProgramNameId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteResinProgramNameByResinProgramNameId(resinProgramNameId);
        
        if(isSuccessful == false)
        {
            
        }
    }
    
    public int GetResinProgramIDFromResinProgramName(String ResinName)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetResinIDFromResinName(ResinName);
    }
    
    public String GetResinProgramNameFromResinProgramID(int ResinID)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetResinNameFromResinID(ResinID);
    }
    
    public boolean CheckIfResinProgramNameExists(String ResinName)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.CheckIfResinProgramNameExists(ResinName);
    }
    
    public ArrayList<String> GetAllResinProgram()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllResinProgram();
    }
}
