/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.HelpForm;

import DataEntities.DyeingProcess;
import Handlers.DyeingProcessHandler;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Eldridge
 */
public class ProcessPanel extends javax.swing.JPanel {
    private int NumberOfTabs = 0;
    //private List<JTextField> subProcessName = new ArrayList<JTextField>();
    private DyeingProcess ThisDyeingProcess = new DyeingProcess();
    /**
     * Creates new form ProcessPanel
     */
    public ProcessPanel() {
        initComponents();
        /* add new Sub Process tab */
        addSubProcessPanel();
    }
    
    public ProcessPanel(DyeingProcess thisProcess)
    {
        initComponents();
        ThisDyeingProcess = thisProcess;
        //Set the Process Name
        this.ProcessText.setText(thisProcess.getDyeingProcessName());
        //Call this function to Set all the Process details
        this.SetAllProcessDetailsFromDyeingProgramID();
    }
    
    /**
     * Set the Details of the process and its Sub-process (if there is) into this ProcessPanel
     * @param DyeingProgramID
     * @param ProcessNumber 
     */
    public void SetAllProcessDetailsFromDyeingProgramID()
    {
        //ArrayList<DyeingProcess> thisDyeingProcess;
        DyeingProcessHandler ProcessHandler = new DyeingProcessHandler();
        //thisDyeingProcess = ProcessHandler.GetAllDyeingProcessByDyeingProgramId(DyeingProgramID);
        int TotalNumberOfSubProcess = ProcessHandler.CountNumberOfSubProcess(ThisDyeingProcess);
        
        if(TotalNumberOfSubProcess > 0)
        {
            ArrayList<DyeingProcess> thisDyeingProcess;
            thisDyeingProcess = ProcessHandler.GetDyeingSubProcessByDyeingProgramIdAndProcessOrder(ThisDyeingProcess);
            
            for(DyeingProcess CurrentDyeingProcess : thisDyeingProcess)
            {
                //Add SubProcess Tab
                this.addNewTab(CurrentDyeingProcess);
            }
            
        }
        else
        {
            setNewTabForChemicals(ThisDyeingProcess);
            //Just Add Chemical and hide Sub Process Textbox
        }
        addPlusTab();
        //ArrayList<DyeingProcess> allDyeingProcess = new ArrayList<DyeingProcess>();
        //DyeingProcessHandler ThisDyeingProcessHandler = new DyeingProcessHandler();
    }
    
    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            addNewTab();
        }

    };
    private void setNewTabForChemicals(DyeingProcess ProcessDetails)
    {
        SubProcessPanel this_panel = new SubProcessPanel();
        this_panel.SetChemicalListFromDyeingProcessID(ProcessDetails.getId());
        this_panel.HideText();
        addTabToSubProcessTabbedPane(this_panel, this.NumberOfTabs++);
        
    }
    
    private void addNewTab() 
    {
        int index = NumberOfTabs -1;
        if (subProcess.getSelectedIndex() == index) { /* if click new tab */
            /* add new tab */
            SubProcessPanel this_panel = new SubProcessPanel();
            //Show Subprocess Text if there is more than one sub process
            if(NumberOfTabs < 3)
               ShowTextOnFirstTab();
            
            /* set tab is custom tab */
            //jTabbedPane1.setTabComponentAt(index, new DemoCustomTab(this));
            addTabToSubProcessTabbedPane(this_panel, index);
            subProcess.removeChangeListener(changeListener);
            subProcess.setSelectedIndex(index);
            subProcess.addChangeListener(changeListener);
            NumberOfTabs++;
        }
    }
    //OVERLOAD for new tabs with items
    private void addNewTab(DyeingProcess SubProcess) 
    {
        /* add new tab */
        SubProcessPanel this_panel = new SubProcessPanel(SubProcess.getId());
        //this_panel.SetSubProcessFromDyeingProgram();
        addTabToSubProcessTabbedPane(this_panel, this.NumberOfTabs++); 
        
    }
    
    public void addSubProcessPanel()
    {
        //Add Initial panel
        SubProcessPanel this_panel = new SubProcessPanel();
        //Hide the SubProcess TextField
        this_panel.HideText();
        subProcess.add(this_panel, "Sub Process 1", NumberOfTabs++);
        //Add The Add Tab /* add tab to add new tab when click */
        addPlusTab();
    }
    
    private void addPlusTab()
    {
        subProcess.add(new JPanel(), "+", NumberOfTabs++);
        subProcess.addChangeListener(changeListener);
    }
    
    public void addTabToSubProcessTabbedPane(SubProcessPanel thisPanel, int index)
    {
        subProcess.add(thisPanel, "Sub Process " + String.valueOf(index+1),
                    index);
        //NumberOfTabs++;
    }
     
     private void ShowTextOnFirstTab()
     {
         Component[] this_pane = this.subProcess.getComponents();
            for (Component c : this_pane) {
                if (c instanceof SubProcessPanel) {
                    SubProcessPanel ThisProcessPanel = ((SubProcessPanel)c);
                    ThisProcessPanel.showText();
                }
            }
     }
     
     public void AddThisPanelInDyeingProcess(int DyeingProgramID , int TabIndex)
     {
         if(ProcessText.getText().length()> 0)
         {
             DyeingProcessHandler ThisDyeingProcessHandler = new DyeingProcessHandler();
             
             ThisDyeingProcess.setDyeingProgramId(DyeingProgramID);
             ThisDyeingProcess.setDyeingProcessName(this.ProcessText.getText());
             ThisDyeingProcess.setDyeingProcessOrder(Integer.toString(TabIndex));
             
             int ProcessID = ThisDyeingProcessHandler.AddDyeingProcess(ThisDyeingProcess);
             //int ProcessID = ThisDyeingProcessHandler.GetDyeingProcessIdByDetails(ThisDyeingProcess);
             ThisDyeingProcess.setId(ProcessID);
            
             //The Dyeing Process was not added
             if (ProcessID == -1)
                 JOptionPane.showMessageDialog(null, "Process : " +ProcessText.getText()+ "  was not successfully added Please check the details.");
             else
             {
                 if(NumberOfTabs > 2)
                     AddThisSubProcessPanelInDyeingProcess(DyeingProgramID,TabIndex);
                 else
                     AddChemicalFromSubProcessPanel(ThisDyeingProcess.getId());
             }
         }
     }
     
     public void AddThisSubProcessPanelInDyeingProcess(int DyeingProgramID, int TabIndex)
     {
         Component[] this_pane = this.subProcess.getComponents();
         int subProcessNumber = 1;
         for (Component c : this_pane)
         {
             if (c instanceof SubProcessPanel)
             {
                  SubProcessPanel ThisProcessPanel = ((SubProcessPanel)c);
                  String parsedOrder = Integer.toString(TabIndex) + "." + ConvertToLetters(subProcessNumber);
                  subProcessNumber++;
                  ThisProcessPanel.AddSubProcess(DyeingProgramID, parsedOrder);
             }
         }
     }
     
     public void AddChemicalFromSubProcessPanel(int DyeingProcessID)
     {
         Component[] this_pane = this.subProcess.getComponents();
         for (Component c : this_pane)
         {
             if (c instanceof SubProcessPanel)
             {
                 SubProcessPanel ThisProcessPanel = ((SubProcessPanel)c);
                 //ThisProcessPanel.AddSubProcess(DyeingProgramID, TOOL_TIP_TEXT_KEY);
                 ThisProcessPanel.AddChemicals(DyeingProcessID);
             }
         }
     }
     
     //FOR UPDATE FUNCTIONS
     public void UpdateThisPanelInDyeingProcess(int DyeingProgramID , int TabIndex)
    {
        //If This is a new Process Panel Then just add to the existing dyeing program
        if(ThisDyeingProcess.getId() == 0)
        {
            AddThisPanelInDyeingProcess(DyeingProgramID, TabIndex);
        }
        else
        {
            if(ProcessText.getText().length()> 0)
            {
                DyeingProcessHandler ThisDyeingProcessHandler = new DyeingProcessHandler();
                ThisDyeingProcess.setDyeingProcessName(this.ProcessText.getText());
                ThisDyeingProcess.setDyeingProcessOrder(Integer.toString(TabIndex));
                
                //The Dyeing Process was not added
                if (ThisDyeingProcessHandler.UpdateDyeingProcess(ThisDyeingProcess) == -1)
                {
                    JOptionPane.showMessageDialog(null, "Process : " +ProcessText.getText()+ " was not successfully updated Please check the details.");
                }
                else
                {
                    if(NumberOfTabs > 1)
                    {
                        UpdateThisSubProcessPanelInDyeingProcess(DyeingProgramID,TabIndex);
                    }
                    else
                        UpdateChemicalFromSubProcessPanel(ThisDyeingProcess.getId());
                }
            }
        }
    }
     
     public void UpdateThisSubProcessPanelInDyeingProcess(int DyeingProgramID, int TabIndex)
     {
         DyeingProcessHandler ProcessHandler = new DyeingProcessHandler();
         int TotalNumberOfSubProcess = ProcessHandler.CountNumberOfSubProcess(ThisDyeingProcess);
         //IF Previously there is no sub process for this process. Then relink dyeing chemical into sub process
         if(TotalNumberOfSubProcess == 0)
         {
             //ERROR HERE!
            if(ThisDyeingProcess.getId()!= 0)
            //Delete Chemicals previously Linked to this Dyeing Process
                ProcessHandler.UpdateDyeingProcess(ThisDyeingProcess); 
             //Just Add SubProcess and Chemicals
            else
            {
                AddThisSubProcessPanelInDyeingProcess(DyeingProgramID, TabIndex);
        
            }
            
         }
         else
         {
             Component[] this_pane = this.subProcess.getComponents();
             int subProcessNumber = 0;
             ArrayList<DyeingProcess> thisDyeingProcess;
                         thisDyeingProcess = ProcessHandler.GetDyeingSubProcessByDyeingProgramIdAndProcessOrder(ThisDyeingProcess);
             for (Component c : this_pane)
             {
                 if (c instanceof SubProcessPanel)
                 {
                     SubProcessPanel ThisProcessPanel = ((SubProcessPanel)c);
                     String parsedOrder = Integer.toString(TabIndex) + "." + ConvertToLetters(subProcessNumber+1);
                     //If There is an existing SubProcess Panel then just Update this SubProcessPanel
                     if(TotalNumberOfSubProcess > subProcessNumber)
                     {
                         //Get The Id of number x subprocess
                         DyeingProcess currentDyeingProcess = thisDyeingProcess.get(subProcessNumber);
                         ThisProcessPanel.UpdateSubProcess(thisDyeingProcess.get(subProcessNumber).getId(), parsedOrder);
                     }
                     //Else if this is a new SubProcess then just add it.
                     else
                     {
                         ThisProcessPanel.AddSubProcess(DyeingProgramID, parsedOrder);
                     }
                     subProcessNumber++;
                 }
             }   
         }
     }
     
     public void UpdateChemicalFromSubProcessPanel(int DyeingProcessID)
     {
         Component[] this_pane = this.subProcess.getComponents();
         for (Component c : this_pane)
         {
             if (c instanceof SubProcessPanel)
             {
                 SubProcessPanel ThisProcessPanel = ((SubProcessPanel)c);
                 //ThisProcessPanel.AddSubProcess(DyeingProgramID, TOOL_TIP_TEXT_KEY);
                 ThisProcessPanel.AddChemicals(DyeingProcessID);
             }
         }
     }
     
     /**
      * Converts number to letters 1 == a, 2 == b, 3 == c
      * @param number
      * @return 
      */
     public char ConvertToLetters(int number)
     {
         char letter;
         switch(number){
             case 1:
                 letter = 'a';
                 break;
             case 2:
                 letter = 'b';
                 break;    
             case 3:
                 letter = 'c';
                 break; 
             case 4:
                 letter = 'd';
                 break; 
             case 5:
                 letter = 'e';
                 break; 
             case 6:
                 letter = 'f';
                 break; 
             case 7:
                 letter = 'g';
                 break; 
             case 8:
                 letter = 'h';
                 break; 
             case 9:
                 letter = 'i';
                 break; 
             case 10:
                 letter = 'j';
                 break; 
             case 11:
                 letter = 'k';
                 break; 
             case 12:
                 letter = 'l';
                 break; 
             case 13:
                 letter = 'm';
                 break; 
             case 14:
                 letter = 'n';
                 break; 
             case 15:
                 letter = 'o';
                 break; 
             case 16:
                 letter = 'p';
                 break; 
             case 17:
                 letter = 'q';
                 break; 
             case 18:
                 letter = 'r';
                 break; 
             case 19:
                 letter = 's';
                 break; 
             default:
                 letter = 'z';
                 break;
                     
         }
         return letter;
     }
     
     public void SetProcessText(String ProcessName)
     {
         this.ProcessText.setText(ProcessName);
     }
     
     
     
     
     
     /**
      * UNUSED
      */
     private void addComponent()
    {
        JPanel this_panel = new SubProcessPanel();
            for (Component c : this_panel.getComponents()) {
                if (c instanceof JTextField) {
                    JTextField textField = ((JTextField)c);
                    String name, contact;
                    //if(textField.getName().startsWith("subProcess")) {
                        // Name field
                        //this.subProcessName.add(textField);
                    //} else {
                        // Contact field
                    //    contact = textField.getText();
                    //}
       // Validate and persist.

                }
            }
    }
     /**
      * Unused
      */
     public void GetSubProcessText()
     {
         Component[] this_pane = this.subProcess.getComponents();
            for (Component c : this_pane) {
                    System.out.println(c.getClass());
                if (c instanceof SubProcessPanel) {
                    SubProcessPanel ThisProcessPanel = ((SubProcessPanel)c);
                    //ThisProcessPanel.GetSubProcessText();
                    //System.out.println(ThisProcessPanel.GetSubProcessText());
                    //System.out.println(ThisProcessPanel.GetChemicalTable().toString());
                }
            }

     }
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProcessLabel = new javax.swing.JLabel();
        ProcessText = new javax.swing.JTextField();
        subProcess = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(255, 255, 255));

        ProcessLabel.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ProcessLabel.setText("Process Name :");

        subProcess.setAutoscrolls(true);
        subProcess.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ProcessLabel)
                        .addGap(10, 10, 10)
                        .addComponent(ProcessText, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ProcessLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProcessText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ProcessLabel;
    private javax.swing.JTextField ProcessText;
    private javax.swing.JTabbedPane subProcess;
    // End of variables declaration//GEN-END:variables
}
