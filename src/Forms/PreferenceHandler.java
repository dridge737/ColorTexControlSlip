/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

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
     public boolean setPreference()
     {
         //prefs = Preferences.userNodeForPackage(this.getClass());
        prefs = Preferences.userRoot().node("/user/custom/root");

        //String CompanyNamePref = "CompanyName";
        //String InitializePref = "Initialize";
        //String ResinMachineInput = "ResinMachineInputType";
        //String ID3 = "Test3";

        prefs.get(CompanyNamePref, "Relianz Int't Corp");
        //False means no need to input. Dryer and Stenter by default.
        prefs.getBoolean(ResinMachineInput, false);
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

    public void setResinMachineInputPreference(String Type) 
    {
        prefs = getSharedPreferenceLocation();
        if (Type.equals("Manual Input")) {
            prefs.putBoolean(ResinMachineInput, true);
        } else {
            prefs.putBoolean(ResinMachineInput,   false);
        }
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
}
