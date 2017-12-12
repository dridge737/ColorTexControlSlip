/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.ChemicalColor;
import Database.ColorTextControlSlipRepository;
import Forms.HelpForm.auto_complete;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Eldridge
 */
public class ColorHandler {
    public boolean AddNewColor(ChemicalColor newColor)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.AddColor(newColor);
        
        return isSuccessful;
    }
    
    public boolean UpdateColor(ChemicalColor thisColor)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.UpdateColorByColorId(thisColor);
        return isSuccessful;        
    }
    
    public void DeleteColor(int ColorId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteColorByColorId(ColorId);
        
        if(isSuccessful == false)
        {
            
        }
    }
     
    public int GetColorIDFromColorName(String ColorName)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetColorIDFromColorName(ColorName);
    }
    
    public String GetColorNameFromColorID(int ColorID)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetColorNameFromColorID(ColorID);
    }
    
    public boolean CheckIfColorNameExists(String ColorName)
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.CheckIfColorNameExists(ColorName);
    }
    
    public ArrayList<String> GetAllColor()
    {
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        return repo.GetAllColors();
    }
    
    public ArrayList<String> addColorTextBoxAutoComplete(JTextField ColorTextBox)
    {
        ArrayList<String> AllColors = new ArrayList<String>();
        //Chemical allChemicals = new Chemical();
        //LiquidRatioHandler newLiquidRatioHandler = new LiquidRatioHandler();
        AllColors = this.GetAllColor();
        auto_complete dropdownAutoComplete = new auto_complete();
        dropdownAutoComplete.setupAutoComplete(ColorTextBox, AllColors);
        ColorTextBox.setColumns(30);
        
        return AllColors;
    }
}
