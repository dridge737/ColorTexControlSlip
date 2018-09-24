package Forms;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Handlers.PreferenceHandler;
import DataEntities.*;
import Handlers.*;
/**
 *
 * @author imbuenyson
 */
public class MainJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //TestResinForm newResinForm = new TestResinForm();
        //newResinForm.setVisible(true);
        
        //AddResinForm newResinForm = new AddResinForm();
        //newResinForm.setVisible(true);
        //DyeingProcess thisProcess = new DyeingProcess();
        
        //DyeingForm TestThisFrame = new DyeingForm("PROCESSTEST" , 2);
        //TestThisFrame.setVisible(true);
        //JobOrderForm thisJobForm = new JobOrderForm();
        //thisJobForm.setVisible(true);
        //JobOrderExtended thisExtension = new JobOrderExtended();
        //if(thisExtension.getThisResinJob() == null)
        //    System.out.println("Hello");
        
        PreferenceHandler initializePref = new PreferenceHandler();
        boolean initialized = false;
        if(initialized == initializePref.CheckIfInitialized())
        {
            initializePref.setPreference();
            SettingForm newSettings = new SettingForm(true);
            newSettings.setVisible(true);
        }
        else
        {
            MainWindow thisMain = new MainWindow();
            thisMain.setVisible(true);
        }
        
    }
    
}
