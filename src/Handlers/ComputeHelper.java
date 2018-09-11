/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import DataEntities.DyeingChemical;
import DataEntities.JobOrder;
import DataEntities.ResinChemical;
import DataEntities.ResinJob;

/**
 *
 * @author Eldridge
 */
public class ComputeHelper {
    public float ComputeDyeingQuantity(DyeingChemical thisDyeingChemical, JobOrder thisJobOrder)
    {
        return ComputeQuantityFromWeightOrVol(thisDyeingChemical.getValue(), thisDyeingChemical.getType(), thisDyeingChemical.getState(), thisJobOrder.getDyeingWeight(), thisJobOrder.getDyeingVolumeH20());
    }
    
    public float ComputeResinQuantity(ResinChemical thisResinChemical, ResinJob thisResinJob)
    {
        return ComputeQuantityFromWeightOrVol(thisResinChemical.getGPLValue(), 
                                                thisResinChemical.getType(), 
                                                thisResinChemical.getState(), 
                                                thisResinJob.getResinWeight(), 
                                                thisResinJob.getResinVolH2O());
    }
    
    public float ComputeQuantityFromWeightOrVol(float Value, String Type, String State, float Weight, float VolumeH20)
     {
         Float quantity;
         //if(thisDyeingChemical.getType().equals("%"))
         if(Type.equals("%"))
         {
             //Old Computation
             //quantity = thisJobOrder.getWeight() * Value;
             //New Computation
             //quantity = thisJobOrder.getWeight() * thisDyeingChemical.getValue() * 10;
             //quantity = Weight * thisDyeingChemical.getValue() * 10;
             quantity = Weight * Value * 10;
         }
         else
         {
             if(State.equals("G"))
             //if(thisDyeingChemical.getState().equals("G"))
                 //quantity = thisJobOrder.getVolumeH20() * thisDyeingChemical.getValue();
                 //quantity = VolumeH20 * thisDyeingChemical.getValue();
                 quantity = VolumeH20 * Value;
             else
                 // Formula = Quantity in ml = Chemical Values in GPL * Vol of Water /1.5 
                 //           Change to Liter = /1000 
                 //           Final formula to get liters = (ChemVal * VolOfWater) / (1.5 * 1000)
                 //quantity = thisJobOrder.getVolumeH20() * thisDyeingChemical.getValue() / (float) 1500;
                 quantity = VolumeH20 * Value / (float) 1500;
                 //quantity = VolumeH20 * thisDyeingChemical.getValue() / (float) 1500;
                 
         }
         //DecimalFormat f = new DecimalFormat("##.00");
         //Math.round(quantity * 100) / 100
         return quantity;
     }

}
