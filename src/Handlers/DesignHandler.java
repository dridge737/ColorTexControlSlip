/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.Design;
import Database.ColorTextControlSlipRepository;
import Forms.HelpForm.auto_complete;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Eldridge
 */
public class DesignHandler {
    
    public boolean AddNewDesign(Design newDesign)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        if( repo.CheckIfDesignExists(newDesign.getDesignName()) == 0)
            isSuccessful = repo.AddDesign(newDesign);
        
        return isSuccessful;
    }
    
    public void UpdateDesign(Design thisDesign)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        if(repo.CheckIdDesignExistsOnOtherId(thisDesign) == 0)
            isSuccessful = repo.UpdateDesignByDesignID(thisDesign);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void DeleteDesign(int designID)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteDesignByDesignID(designID);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public String GetDesignNameFromID(int DesignId)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        return repo.GetDesignNameFromDesignID(DesignId);
    }
    
    public int GetDesignIDFromName(String DesignName)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        return repo.GetDesignIDFromDesignName(DesignName);
    }
    
    public ArrayList<String> GetAllDesigns()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        return repo.GetAllDesign();
    }
    
    public ArrayList<String> addDesignTextBoxAutoComplete(JTextField DesignTextBox)
    {
        ArrayList<String> AllDesign = new ArrayList<String>();
        //Chemical allChemicals = new Chemical();
        //LiquidRatioHandler newLiquidRatioHandler = new LiquidRatioHandler();
        AllDesign = this.GetAllDesigns();
        auto_complete dropdownAutoComplete = new auto_complete();
        dropdownAutoComplete.setupAutoComplete(DesignTextBox, AllDesign);
        DesignTextBox.setColumns(30);
        
        return AllDesign;
    }
}
