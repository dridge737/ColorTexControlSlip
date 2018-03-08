/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.DyeingProcess;
import java.awt.Component;
import java.util.ArrayList;
import DataEntities.DyeingProgram;
import DataEntities.DyeingProgramName;
import DataEntities.JobOrder;
import DataEntities.ProcessOrder;
import Forms.HelpForm.ProcessPanel;
import Handlers.ChemicalHandler;
import Handlers.DyeingProcessHandler;
import Handlers.DyeingProgramHandler;
import Handlers.DyeingProgramNameHandler;
import Handlers.ProcessOrderHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Eldridge
 */
public class DyeingForm extends javax.swing.JFrame {

    //private int NumberOfTabs = 1;
    //private List<SubProcessPanel> subProcessPanels = new ArrayList<SubProcessPanel>();
    //JPanel ThisPanel = new TestProcessPanel();
    //private int NumberOfSubProcessTabs = 1;
    //private List<JTextField> ProcessNameList = new ArrayList<JTextField>();
    //private List<JTextField> subProcessNameList = new ArrayList<JTextField>();
    private int NumberOfProcessTabs = 0;
    DyeingProgram thisDyeingProgram = new DyeingProgram();
    DyeingProgramName thisDyeingProgramName = new DyeingProgramName();
    private int WindowProcessType = 0;
    //1 for new Dyeing Program
    //2 For Update Dyeing Program
    //3 For ControlSlip Dyeing Program
    JobOrder thisJob = new JobOrder();
    Color ColorError = new Color(232,228,42);
    
    /**
     * Creates new form Dyeing Form
     */
    public DyeingForm() {
        //initComponents();
        //WindowProcessType = 1;
        //GUITabbedPaneProcess.add(new ProcessPanel(), "Process 1", NumberOfProcessTabs++);
        //setWindowForthisProcessType();
        this(null, null, 1);
    }
    
    //For Edit or View
    public DyeingForm(String DyeingProgramName)
    {
        this(DyeingProgramName, null, 2);
        //initComponents();
        //WindowProcessType = 2;
        //SetDefaultDyeingProgramFromProgramName(DyeingProgramName);
        //setWindowForthisProcessType();
    }
    
    //Jumps From Review Form / Back from View Resin Form
    public DyeingForm(JobOrder currentJob)
    {
        this(null, currentJob, 3);
        //initComponents();
        //WindowProcessType = 3;
        //thisJob = currentJob;
        //this.SetDyeingProgramFromProgramID(currentJob.getDyeingProgramID());
        //setWindowForthisProcessType();
    }
    
    //For Control Slip Form
    public DyeingForm(String DyeingProgramName,  JobOrder currentJob)
    {
        this(DyeingProgramName, currentJob, 4);
        //initComponents();
        //WindowProcessType = 3;
        //thisJob = currentJob;
        //Check if customer has this dyeingProgramName        
        //SetDyeingProgramFromProgramNameForThisCustomer(DyeingProgramName);
        //setWindowForthisProcessType();
    }
    
    public DyeingForm(String DyeingProgramName, JobOrder currentJob, int WindowType)
    {
        initComponents();
        SetToCenter();
        WindowProcessType = WindowType;
        //For Adding new Dyeing Program
        if(WindowProcessType == 1)
        {
            GUITabbedPaneProcess.add(new ProcessPanel(), "Process 1", NumberOfProcessTabs++);
            Header.setText("Add Dyeing Program");
            SaveBut.setText("Save");
        }
        //For Updating previously added dyeing program
        else if(WindowProcessType == 2)
        {
            SetDefaultDyeingProgramFromProgramName(DyeingProgramName);
            Header.setText("Update Dyeing Program");
            SaveBut.setText("Update");
        }
        //For dyeing program to job order
        else if(WindowProcessType > 2)
        {
            thisJob = currentJob;
            if(DyeingProgramName != null)
            {
                SetDyeingProgramFromProgramNameForThisCustomer(DyeingProgramName);
            }
            else
            {
                this.SetDyeingProgramFromProgramID(currentJob.getDyeingProgramID());
            }
            //if(WindowProcessType == 4) 
            Header.setText("Dyeing Program");
            SaveBut.setText("Next");
            CancelBut.setText("Back");
            Header.setText("Control Slip : Page 3/6");
            ProgramNameText.setEnabled(false);
            ProgramNameText.setBackground(Color.LIGHT_GRAY);
        }
        
        GUITabbedPaneProcess.add(new JPanel(), "+", NumberOfProcessTabs++);
        GUITabbedPaneProcess.addChangeListener(changeListener);
        //setWindowForthisProcessType();
    }
    
    public void SetToCenter()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
    
    public void SetDyeingProgramFromProgramNameForThisCustomer(String DyeingProgramName)
    {
        DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
        thisDyeingProgramName.setDyeingProgramName(DyeingProgramName);
        if(thisDyeingProgramHandler.
                CheckIfSpecificDyeingProgramExistForThisCustomer(DyeingProgramName, thisJob.getCustomerID()))
        {
            this.ProgramNameText.setText(DyeingProgramName);
            thisDyeingProgram.SetID(
                    thisDyeingProgramHandler.GetDyeingProgramIDForCustomerDyeingProgram(DyeingProgramName, thisJob.getCustomerID()));
            //thisDyeingProgram = thisDyeingProgramHandler.GetDyeingProgramDetailsById();
            //SetDyeingProgramProcessFromProgramID(thisDyeingProgram.getID());
            SetDyeingProgramFromProgramID(thisDyeingProgram.getID());
        }
        else
            SetDefaultDyeingProgramFromProgramName(DyeingProgramName);
        
    }
    
    public void SetDyeingProgramFromProgramID(int DyeingProgramID)
    {
        DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
        DyeingProgramNameHandler thisDyeingProgramNameHandler = new DyeingProgramNameHandler();
        
        thisDyeingProgram.SetID(DyeingProgramID);
        thisDyeingProgram = thisDyeingProgramHandler.GetDyeingProgramDetailsById(DyeingProgramID);
        //if(thisDyeingProgramName.getDyeingProgramName().length() > 1)
        //{
            thisDyeingProgramName.setID(thisDyeingProgram.getDyeingProgramNameID());
            thisDyeingProgramName.setDyeingProgramName( 
                    thisDyeingProgramNameHandler.GetDyeingProgramNameFromID(thisDyeingProgramName.getID()) );
        //}
        //thisDyeingProgramName.setDyeingProgramName(thisDyeingProgramName.getDyeingProgramName());
        this.ProgramNameText.setText(thisDyeingProgramName.getDyeingProgramName());
        
        SetDyeingProgramProcessFromProgramID(thisDyeingProgram.getID());
    }
    
    /**
     * Sets Dyeing Program Name Only and then Calls to set the individual process
     * @param DyeingProgramName 
     */
    public void SetDefaultDyeingProgramFromProgramName(String DyeingProgramName)
    {
        //Class and Handler Declaration
        //FOR DYEING PROGRAM NAME
        DyeingProgramNameHandler thisDyeingProgramNameHandler = new DyeingProgramNameHandler();
        DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
        //Set Program Name
        thisDyeingProgramName.setDyeingProgramName(DyeingProgramName);
        //SET ID From dyeing program name
        thisDyeingProgramName.setID(
                thisDyeingProgramNameHandler.GetDyeingProgramNameIDfromName(thisDyeingProgramName.getDyeingProgramName()));
        this.ProgramNameText.setText(thisDyeingProgramName.getDyeingProgramName());
        
        thisDyeingProgram.setDyeingProgramNameID(thisDyeingProgramName.getID());
        thisDyeingProgram = thisDyeingProgramHandler.getDefaultProgramIDForThisDyeingProgramNameID(thisDyeingProgram.getDyeingProgramNameID());
        SetDyeingProgramProcessFromProgramID(thisDyeingProgram.getID());
    }
    
    /**
     * Sets All the Dyeing Process using the Program ID
     * @param DyeingProgramID 
     */
    public void SetDyeingProgramProcessFromProgramID(int DyeingProgramID)
    {
        ArrayList<DyeingProcess> thisDyeingProcess;
        DyeingProcessHandler ProcessHandler = new DyeingProcessHandler();
        thisDyeingProcess = ProcessHandler.GetAllDyeingProcessByDyeingProgramId(DyeingProgramID);

        //Add Dyeing Process Tab for Each Dyeing Process
        for(DyeingProcess thisProcess: thisDyeingProcess)
        {
            //Declare the Tab to be added
            this.addNewTabProcess(thisProcess);
        }
    }
   
    
    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            addNewTabProcess();
        }

    };
    /**
     * Add a new tab with Dyeing Process from the database
     * @param thisProcess
     * @param ProcessNumber
     * @param DyeingProgramID 
     */
    private void addNewTabProcess(DyeingProcess thisProcess)
    {
        //Declare the Tab to be added
        ProcessPanel this_panel;
        if(WindowProcessType > 2 )
        //if(thisJob.getID()>0 )
        this_panel = new ProcessPanel(thisProcess, WindowProcessType, thisJob);
        else
        this_panel = new ProcessPanel(thisProcess, WindowProcessType);
        
        //Add the Tab to the JtabbedPane
        GUITabbedPaneProcess.add(this_panel, "Process " + String.valueOf(NumberOfProcessTabs+1),
                    NumberOfProcessTabs);
        NumberOfProcessTabs++;
        
    }
    private void addNewTabProcess() 
    {
        int index = NumberOfProcessTabs - 1;
        if (GUITabbedPaneProcess.getSelectedIndex() == index) { /* if click new tab */
           
            /* add new tab */
            JPanel this_panel = new ProcessPanel();
            GUITabbedPaneProcess.add(this_panel, "Process " + String.valueOf(NumberOfProcessTabs),
                    index);
            /* set tab is custom tab */
            GUITabbedPaneProcess.removeChangeListener(changeListener);
            GUITabbedPaneProcess.setSelectedIndex(index);
            GUITabbedPaneProcess.addChangeListener(changeListener);
            NumberOfProcessTabs++;
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

        jPanel2 = new javax.swing.JPanel();
        Header = new javax.swing.JLabel();
        SaveBut = new javax.swing.JButton();
        GUITabbedPaneProcess = new javax.swing.JTabbedPane();
        ProgramNameLabel = new javax.swing.JLabel();
        ProgramNameText = new javax.swing.JTextField();
        CancelBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setToolTipText("");
        jPanel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Add Dyeing Program");
        jPanel2.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 710, 40));

        SaveBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        SaveBut.setText("Add Dyeing Program");
        SaveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButActionPerformed(evt);
            }
        });
        jPanel2.add(SaveBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 650, 250, 40));

        GUITabbedPaneProcess.setBackground(new java.awt.Color(255, 255, 255));
        GUITabbedPaneProcess.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(GUITabbedPaneProcess, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 770, 500));

        ProgramNameLabel.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ProgramNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        ProgramNameLabel.setText("Program Name :");
        jPanel2.add(ProgramNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 165, 34));

        ProgramNameText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ProgramNameText.setToolTipText("");
        ProgramNameText.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        jPanel2.add(ProgramNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 600, 34));

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        CancelBut.setText("Cancel");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });
        jPanel2.add(CancelBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 650, 250, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean CheckDyeingFormProcessAndSubProcessIfReady()
    {
        boolean isSuccessful = true;
        if(this.ProgramNameText.getText().trim().length() > 0)
        {
            Component[] this_pane = this.GUITabbedPaneProcess.getComponents();
            int ProcessOrder = 1;
            for (Component c : this_pane)
            {
                if (c instanceof ProcessPanel) {
                    ProcessPanel ThisProcessPanel = ((ProcessPanel)c);
                    if(!ThisProcessPanel.CheckIfProccessAndSubProcessIsReady())
                        isSuccessful = false;
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please input a dyeing program name.");
            ProgramNameText.setBackground(ColorError);
            isSuccessful = false;
        }
        return isSuccessful;
    }
    
    public boolean AddDyeingProgramNameAndDyeingProgram()
    {
        boolean isSuccessful = false;
        DyeingProgramNameHandler thisDyeingProgramNameHandler = new DyeingProgramNameHandler();
        
        thisDyeingProgramName.setDyeingProgramName(this.ProgramNameText.getText());
        //thisDyeingProgram.setDyeingProgramName(this.ProgramNameText.getText());
        
        if(!thisDyeingProgramNameHandler.CheckIfDyeingProgramNameHasBeenAdded(thisDyeingProgramName.getDyeingProgramName()))
        {
            int thisDyeingProgramNameID =
                    thisDyeingProgramNameHandler.AddDyeingProgramName(thisDyeingProgramName.getDyeingProgramName());
            thisDyeingProgram.setDyeingProgramNameID(thisDyeingProgramNameID);
                //ADD and Set Dyeing Program ID
            thisDyeingProgram.setProgramDefault(1);
                //int DyeingProgramID = thisDyeingProgramHandler.GetDyeingProgramIDfromName(thisDyeingProgram.getDyeingProgramName());
                //FIX THIS edit for Dyeing Program Name
                //thisDyeingProgram.SetID(DyeingProgramID);
            isSuccessful = AddDyeingProgram();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Dyeing program name has already been added.");
        }
        
        return isSuccessful;
    }
    
    private boolean AddDyeingProgram()
    {
        boolean AddSuccess = false;
        DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
        thisDyeingProgram.SetID(thisDyeingProgramHandler.AddDyeingProgram(thisDyeingProgram));
        if(thisDyeingProgram.getID() > 0)
        {
            AddDyeingProcessForThisDyeingProgram();
            AddSuccess = true;
        }    
        return AddSuccess;
    }
    
    private boolean AddDyeingProcessForThisDyeingProgram()
    {
        boolean isSuccessful = false;
        if(thisDyeingProgram.getID() != -1)
        {
            Component[] this_pane = this.GUITabbedPaneProcess.getComponents();
            int ProcessOrder = 1;
            for (Component c : this_pane)
            {
                if (c instanceof ProcessPanel) {
                    ProcessPanel ThisProcessPanel = ((ProcessPanel)c);
                    ThisProcessPanel.AddThisPanelInDyeingProcess(thisDyeingProgram.getID(), ProcessOrder++);
                }
            }
            isSuccessful = true;
        }
        return isSuccessful;
    }
    
    private boolean UpdateDyeingProgram()
    {
        boolean SuccessfullyUpdated = true;
        DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
        DyeingProgramNameHandler thisDyeingProgramNameHandler = new DyeingProgramNameHandler();
        thisDyeingProgramName.setDyeingProgramName(this.ProgramNameText.getText());
        //thisDyeingProgram.SetID();
        
        //Returns True If Update is Successful then proceed to update other
        //if(thisDyeingProgramHandler.UpdateDyeingProgram(thisDyeingProgram))
        if(WindowProcessType !=3)
        {
            if(!thisDyeingProgramNameHandler.CheckIfDyeingProgramNameHasBeenAddedtoOtherID(thisDyeingProgramName))
            //if(thisDyeingProgramNameHandler.UpdateDyeingProgramName(thisDyeingProgramName.getDyeingProgramName()))
            {
                SuccessfullyUpdated = thisDyeingProgramNameHandler.UpdateDyeingProgramName(thisDyeingProgramName);
            }
            else
                SuccessfullyUpdated = false;
        }
        if(SuccessfullyUpdated == true)
        {
            Component[] this_pane = this.GUITabbedPaneProcess.getComponents();
            int ProcessOrder = 1;
        
            for (Component c : this_pane)
            {
                if (c instanceof ProcessPanel) {
                        ProcessPanel ThisProcessPanel = ((ProcessPanel)c);
                        ThisProcessPanel.UpdateThisPanelInDyeingProcess(thisDyeingProgram.getID(), ProcessOrder++);
                    }
            }
        }
        return SuccessfullyUpdated;
    }
    private void SaveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButActionPerformed
        // TODO add your handling code here:
        //this.jPanel2.getComp
        boolean CloseWindow = false;
        if(this.CheckDyeingFormProcessAndSubProcessIfReady())
        {
            switch (WindowProcessType) {
                //For Adding Dyeing Program
                case 1:
                    CloseWindow = AddDyeingProgramNameAndDyeingProgram();
                    break;
                //For Updating Dyeing program
                case 2:
                    CloseWindow = UpdateDyeingProgram();
                    break;
                //For Job Order
                default:
                    //if Default program then add, else update.
                    if (thisDyeingProgram.getProgramDefault() == 1) {
                        thisDyeingProgram.setProgramDefault(0);
                        //If there is no program added for the current customer
                        CloseWindow = this.AddDyeingProgram();
                    } else {
                        //Else just update the program added for the current customer
                        CloseWindow = UpdateDyeingProgram();
                    }

                    thisJob.setDyeingProgramID(thisDyeingProgram.getID());
                    break;
            }
        }
        
        if(CloseWindow)
        {
            switch(WindowProcessType)
            {
                case 1:
                    JOptionPane.showMessageDialog(null, "Successfully added dyeing program : "+thisDyeingProgramName.getDyeingProgramName());
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Successfully updated dyeing program : "+thisDyeingProgramName.getDyeingProgramName());
                    break;
                case 3:
                    if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to include a RESIN PROGRAM with this Job?","Add Resin Program?", JOptionPane.YES_NO_OPTION))
                    {//Show the Resin Form
                        //IF a user wants to add Resin program to the job order
                        //Show Machine Selection Form
                        MachineSelection thisMachineSelection = new MachineSelection(thisJob);
                        thisMachineSelection.setVisible(true);
                        //ViewResinProgramList thisResinProgram = new ViewResinProgramList(thisJob);
                        //thisResinProgram.setVisible(true);
                    }
                    else
                    {
                        //thisProcessOrder.setResinProgramID(0);
                        ReviewForm thisReviewForm = new ReviewForm(thisJob, this.WindowProcessType);
                        //Show Review Form and Ask to print
                        thisReviewForm.setVisible(true);
                        //ProcessOrderHandler thisProcess = new ProcessOrderHandler();
                        //thisProcess.AddNewProcessOrder(thisProcessOrder);
                    }
                    break;
                default:
                    ReviewForm thisReviewForm = new ReviewForm(thisJob, this.WindowProcessType);
                        //Show Review Form and Ask to print
                    thisReviewForm.setVisible(true);
                    break;
            }
            this.dispose();
        }
    }//GEN-LAST:event_SaveButActionPerformed

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        if(this.WindowProcessType > 2)
        {
            ViewDyeingProgramList chooseDyeingProgram = new ViewDyeingProgramList(thisJob, this.WindowProcessType);
            chooseDyeingProgram.setVisible(true);
            this.dispose();
        }
        else
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to Cancel this Dyeing Program?","Cancel Dyeing Program?", JOptionPane.YES_NO_OPTION))
        {
            this.dispose();
        }
        
    }//GEN-LAST:event_CancelButActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DyeingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DyeingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DyeingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DyeingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DyeingForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBut;
    private javax.swing.JTabbedPane GUITabbedPaneProcess;
    private javax.swing.JLabel Header;
    private javax.swing.JLabel ProgramNameLabel;
    private javax.swing.JTextField ProgramNameText;
    private javax.swing.JButton SaveBut;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}


