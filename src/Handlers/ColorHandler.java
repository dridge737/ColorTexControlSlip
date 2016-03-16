/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.Color;
import Database.ColorTextControlSlipRepository;

/**
 *
 * @author Eldridge
 */
public class ColorHandler {
    public void AddNewColor(Color newColor)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.AddColor(newColor);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
    public void UpdateColor(Color thisColor)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.UpdateColorByColorId(thisColor);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
    
     public void DeleteColor(int ColorId)
    {
        boolean isSuccessful = false;
        
        ColorTextControlSlipRepository repo = new ColorTextControlSlipRepository();
        
        isSuccessful = repo.DeleteColorByColorId(ColorId);
        
        if(isSuccessful == false)
        {
            //enter validation 
        }
    }
}
