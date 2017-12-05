/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Database.ColorTextControlSlipRepository;
import Forms.HelpForm.auto_complete;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Eldridge
 */
public class LiquidRatioHandler {
    public boolean AddNewLiquidRatio(String LiquidRatio)
    {
         boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        if(repo.CheckIfLiquidRatioExists(LiquidRatio) == 0)
        {
             isSuccessful = repo.AddLiquidRatio(LiquidRatio);
        }
       
        
        return isSuccessful;
    }
    
    public boolean DeleteLiquidRatio(int LiquidRatioID)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteLiquidRatioByLiquidRatioID(LiquidRatioID);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
        return isSuccessful;
    }
    
    public ArrayList<String> GetAllLiquidRatio()
     {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllLiquidRatio();
     }
    
    
    public void addLiquidRatioTextBoxAutoComplete(JTextField LiquidRatioTextBox)
    {
        ArrayList<String> AllLiquidRatio = new ArrayList<String>();
        //Chemical allChemicals = new Chemical();
        LiquidRatioHandler newLiquidRatioHandler = new LiquidRatioHandler();
        AllLiquidRatio = newLiquidRatioHandler.GetAllLiquidRatio();
        auto_complete dropdownAutoComplete = new auto_complete();
        dropdownAutoComplete.setupAutoComplete(LiquidRatioTextBox, AllLiquidRatio);
        LiquidRatioTextBox.setColumns(30);
    }
}
