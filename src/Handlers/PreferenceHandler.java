/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import java.util.prefs.Preferences;

/**
 *
 * @author Eldridge
 */
public class PreferenceHandler {

    
     private Preferences prefs;
     private String CompanyNamePref = "CompanyName";
     private String InitializePref = "Initialize";
     private String ResinMachineInput = "ResinMachineInputType";
     private String ReviewFormEditing = "ReviewFormEditing";
     public boolean setPreference()
     {
         //prefs = Preferences.userNodeForPackage(this.getClass());
        prefs = Preferences.userRoot().node("/user/custom/root");

        //String CompanyNamePref = "CompanyName";
        //String InitializePref = "Initialize";
        //String ResinMachineInput = "ResinMachineInputType";
        //String ID3 = "Test3";

        prefs.get(CompanyNamePref, "Relianz Int'l Corp");
        //False means no need to input. Dryer and Stenter by default.
        prefs.getBoolean(ResinMachineInput, false);
        prefs.getBoolean(ReviewFormEditing, false);
        //prefs.getBoolean(InitializePref, false);
        return prefs.getBoolean(InitializePref, false);
    // First we will get the values
        // Define a boolean value
        //System.out.println(prefs.get(CompanyNamePref, "Relianz International Corporation"));
        // Define a string with default "Hello World
        //System.out.println(prefs.getBoolean(InitializePref, false));
        // Define a integer with default 50
        //System.out.println(prefs.getInt(ID3, 50));

        // now set the values
        //prefs.putBoolean(CompanyNamePref, false);
        //prefs.put(InitializePref, "Hello Europa");
        //prefs.putInt(ID3, 45);
    }
     
    private Preferences getSharedPreferenceLocation() 
    {
        return Preferences.userRoot().node("/user/custom/root");
    }

    public boolean CheckIfInitialized() 
    {
        prefs = getSharedPreferenceLocation();
        return prefs.getBoolean(InitializePref, false);
    }

    public void initialize() 
    {
        prefs = getSharedPreferenceLocation();
        prefs.putBoolean(InitializePref, true);
    }

    public String getCompanyPreference() 
    {
        prefs = getSharedPreferenceLocation();
        return prefs.get(this.CompanyNamePref, "Relianz International Corporation");
    }

    public void setCompanyPreference(String CompanyName) 
    {
        prefs = getSharedPreferenceLocation();
        prefs.put(CompanyNamePref, CompanyName);
    }

    public boolean getReviewFormBooleanConversion(String ReviewEditing)
    {
        
        boolean Enabled;
        if(ReviewEditing.equals("Enabled"))
        {
            Enabled = true;
        }
        else 
            Enabled = false;
        
        return Enabled;
        
    }
    public boolean getResinMachineBooleanConversion(String Type)
    {
        boolean AutoOrManual;
        if (Type.equals("Manual Input")) {
            AutoOrManual = true;
            //prefs.putBoolean(ResinMachineInput, true);
        } else {
            AutoOrManual = false;
        }
        //prefs.putBoolean(ResinMachineInput,   AutoOrManual);
        return AutoOrManual;
    }
    
    public void setResinMachineInputPreference(Boolean Type) 
    {
        prefs = getSharedPreferenceLocation();
        prefs.putBoolean(ResinMachineInput, Type);
    }
    
    /**
     * 
     * @return false if input is not required "Manual Input"
     */
    public boolean getResinMachineInputPreference() 
    {
        prefs = getSharedPreferenceLocation();
        //returns true if resin machine input is required else false;
        return prefs.getBoolean(ResinMachineInput, false);
    }
    
    public void fixResinMachineDatabase()
    {
        boolean ResinMachine = prefs.getBoolean(ResinMachineInput, false);
        if(ResinMachine == false)
        {
            new MachineHandler().AddDryerAndStenterMachine();
        }
    }
    
    /**
     * @return the ReviewFormEditing
     */
    public boolean getReviewFormEditing() {
        prefs = getSharedPreferenceLocation();
        //returns true if resin machine input is required else false;
        //System.out.println(prefs.getBoolean(ReviewFormEditing, false));
        return prefs.getBoolean(ReviewFormEditing, false);
        
    }

    /**
     * @param ReviewFormEditing the ReviewFormEditing to set
     */
    public void setReviewFormEditing(Boolean ReviewFormEditingType) {
       prefs = getSharedPreferenceLocation();
       //System.out.println(prefs.getBoolean(ReviewFormEditing, false));
       prefs.putBoolean(ReviewFormEditing, ReviewFormEditingType);
    }
}
