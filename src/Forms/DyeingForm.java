/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.DyeingChemical;
import DataEntities.DyeingProcess;
import java.awt.Component;
import java.util.ArrayList;
import DataEntities.DyeingProgram;
import DataEntities.DyeingProgramName;
import DataEntities.JobOrderExtended;
import DataEntities.ProcessOrder;
import Forms.HelpForm.ProcessPanel;
import Handlers.ChemicalHandler;
import Handlers.DyeingChemicalHandler;
import Handlers.DyeingProcessHandler;
import Handlers.DyeingProgramHandler;
import Handlers.DyeingProgramNameHandler;
import Handlers.PreferenceHandler;
import Handlers.ProcessOrderHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
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
    ArrayList<Integer> ProcessToDelete = new ArrayList<>();
    //1 for new Dyeing Program
    //2 For Update Dyeing Program
    //3 For ControlSlip Dyeing Program
    JobOrderExtended thisJob = new JobOrderExtended();
    Color ColorError = new Color(232,228,42);
    
    /**
     * Creates new form Dyeing Form
     */
    public DyeingForm() {
        this(null, null, 1);
    }
    
    //For Edit or View
    public DyeingForm(String DyeingProgramName)
    {
        this(DyeingProgramName, null, 2);
    }
    
    //Jumps From Review Form / Back from View Resin Form
    public DyeingForm(JobOrderExtended currentJob)
    {
        this(null, currentJob, 3);
    }
    
    //For Control Slip Form
    public DyeingForm(String DyeingProgramName,  JobOrderExtended currentJob)
    {
        this(DyeingProgramName, currentJob, 4);
    }
    
    public DyeingForm(String DyeingProgramName, JobOrderExtended currentJob, int WindowType)
    {
        initComponents();
        SetToCenter();
        WindowProcessType = WindowType;
        //For Adding new Dyeing Program
        OkayButton.setVisible(false);
        thisJob = currentJob;
        InitializeWindowType(DyeingProgramName);
        
        GUITabbedPaneProcess.add(new JPanel(), "+", NumberOfProcessTabs++);
        GUITabbedPaneProcess.addChangeListener(changeListener);
        //setWindowForthisProcessType();
        EnterForwardTraversal();
    }
    
    public void InitializeWindowType(String DyeingProgramName)
    {
        if(WindowProcessType == 1)
        {
            GUITabbedPaneProcess.add(new ProcessPanel(), "Process 1", NumberOfProcessTabs++);
            GUITabbedPaneProcess.setTabComponentAt(NumberOfProcessTabs-1, new CustomTabClose(this.GUITabbedPaneProcess));
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
            if (DyeingProgramName != null) {
                SetDyeingProgramFromProgramNameForThisCustomer(DyeingProgramName);
            } else {
                this.SetDyeingProgramFromProgramID(thisJob.getDyeingProgramID());
            }
            if (WindowProcessType == 4 || WindowProcessType == 6) {
                if (!new PreferenceHandler().getReviewFormEditing() && WindowProcessType == 6) {
                    this.DisableThisForm();
                    Header.setText("View Dyeing Program");
                }
                
                Header.setText("Dyeing Program");
            }
            else {
                Header.setText("Control Slip : Page 3/6");
            }
            SaveBut.setText("Next");
            CancelBut.setText("Back");
            ProgramNameText.setEnabled(false);
            ProgramNameText.setBackground(Color.LIGHT_GRAY);
        }
    }
    
    public void EnterForwardTraversal() {
        Set forwardkeys = new HashSet(BgPanel
                .getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));

        forwardkeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        BgPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardkeys);

    }
    
    public void DisableThisForm()
    {
        SaveBut.setVisible(false);
        CancelBut.setVisible(false);
        OkayButton.setVisible(true);
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
        
        if(thisJob.getDyeingProgramID() != 0 &&
           new DyeingProgramNameHandler().GetDyeingProgramNameFromDyeingProgramID(thisJob.getDyeingProgramID()) == DyeingProgramName)
        {
              this.SetDyeingProgramFromProgramID(thisJob.getDyeingProgramID());
        }
        else {
            if (thisDyeingProgramHandler.
                    CheckIfSpecificDyeingProgramExistForThisCustomer(DyeingProgramName, thisJob)) {
                this.ProgramNameText.setText(DyeingProgramName);
                thisDyeingProgram.SetID(
                        thisDyeingProgramHandler.GetDyeingProgramIDForCustomerDyeingProgram(DyeingProgramName, thisJob));
                //thisDyeingProgram = thisDyeingProgramHandler.GetDyeingProgramDetailsById();
                //SetDyeingProgramProcessFromProgramID(thisDyeingProgram.getID());
                SetDyeingProgramFromProgramID(thisDyeingProgram.getID());
            } else {
                SetDefaultDyeingProgramFromProgramName(DyeingProgramName);
            }

        }
        
    }
    
    public void SetDyeingProgramFromProgramID(int DyeingProgramID)
    {
        DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
        DyeingProgramNameHandler thisDyeingProgramNameHandler = new DyeingProgramNameHandler();
        
        thisDyeingProgram.SetID(DyeingProgramID);
        thisDyeingProgram = thisDyeingProgramHandler.GetDyeingProgramDetailsById(thisDyeingProgram.getID());
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
        //thisJob.setDyeingProgramID(thisDyeingProgram.getID());
        thisDyeingProgram = thisDyeingProgramHandler.getDefaultProgramIDForThisDyeingProgramNameID(thisDyeingProgram);
        SetDyeingProgramProcessFromProgramID(thisDyeingProgram.getID());
    }
    
    /**
     * Sets All the Dyeing Process using the Program ID
     * @param DyeingProgramID 
     */
    public void SetDyeingProgramProcessFromProgramID(int DyeingProgramID)
    {
        ArrayList<DyeingProcess> thisDyeingProcess;
        //DyeingProcessHandler ProcessHandler = new DyeingProcessHandler();
        thisDyeingProcess = new DyeingProcessHandler().GetAllDyeingProcessByDyeingProgramId(DyeingProgramID);

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
        GUITabbedPaneProcess.setTabComponentAt(NumberOfProcessTabs, new CustomTabClose(this.GUITabbedPaneProcess));
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
            GUITabbedPaneProcess.setTabComponentAt(NumberOfProcessTabs-1, new CustomTabClose(this.GUITabbedPaneProcess));
            GUITabbedPaneProcess.removeChangeListener(changeListener);
            GUITabbedPaneProcess.setSelectedIndex(index);
            GUITabbedPaneProcess.addChangeListener(changeListener);
            NumberOfProcessTabs++;
        }
    }
    
    public void removeTab(int index) {
        
        GUITabbedPaneProcess.setSelectedIndex(index);
        ProcessPanel thisProcessPanel = (ProcessPanel) GUITabbedPaneProcess.getSelectedComponent();
        int DeleteProcessID = thisProcessPanel.getThisDyeingProcess().getId();
        ProcessToDelete.addAll(thisProcessPanel.getAllDyeingSubProcessID());
        if(DeleteProcessID != 0)
            this.ProcessToDelete.add(DeleteProcessID);
        
        this.GUITabbedPaneProcess.remove(index);
        NumberOfProcessTabs--;
        
 
        if (index == NumberOfProcessTabs - 1 && index > 0) {
            GUITabbedPaneProcess.setSelectedIndex(NumberOfProcessTabs - 2);
        } else {
            GUITabbedPaneProcess.setSelectedIndex(index);
        }
 
        if (NumberOfProcessTabs == 1) {
            addNewTabProcess();
        }
    }
    
    public void renameTabs()
    {
        int x = 0;
        while (x < GUITabbedPaneProcess.getTabCount() - 1) {
            GUITabbedPaneProcess.setTitleAt(x, "Process " + ++x);
        }
    }
    
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
                   
                    if(!ThisProcessPanel.CheckIfProccessAndSubProcessIsReady()){
                        //ThisProcessPanel
                        isSuccessful = false;
                    }
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
            int thisDyeingProgramNameID
                    = thisDyeingProgramNameHandler.AddDyeingProgramName(thisDyeingProgramName.getDyeingProgramName());
            thisDyeingProgram.setDyeingProgramNameID(thisDyeingProgramNameID);
            //ADD and Set Dyeing Program ID
            if (thisJob != null) {
                thisDyeingProgram.setCustomerID(thisJob.getCustomerID());
                thisDyeingProgram.setColorID(thisJob.getColorID());
                thisDyeingProgram.setDesignID(thisJob.getDesignID());
            } else {
                thisDyeingProgram.setCustomerID(0);
                thisDyeingProgram.setColorID(0);
                thisDyeingProgram.setDesignID(0);
            }

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
    
    private void GetDyeingProgramDetails()
    {
        //Get Dyeing Process and Dyeing Chemical Details
        Component[] this_pane = this.GUITabbedPaneProcess.getComponents();
        int ProcessOrder = 1;
        ArrayList<DyeingProcess> DyeingProcessList = new ArrayList<DyeingProcess>();

        for (Component c : this_pane) {

            if (c instanceof ProcessPanel) {
                ProcessPanel ThisProcessPanel = ((ProcessPanel) c);
                //ThisProcessPanel.AddThisPanelInDyeingProcess(thisDyeingProgram.getID(), ProcessOrder++);
                DyeingProcessList.add(ThisProcessPanel.GetThisPanelDetails(ProcessOrder++));
            }

        }
    }
    
    /**
     * This function adds the dyeing Program and dyeing process to the database.
     * @return 
     */
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
            ArrayList<DyeingProcess> DyeingProcessList = new ArrayList<DyeingProcess>();
            
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
    
    private void ToggleCurrentFlagOfDyeingProgram()
    {
        DyeingProgram ToggleThisDyeingProgram = new DyeingProgram();
        
        ToggleThisDyeingProgram.SetID(thisJob.getDyeingProgramID());
        DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
        thisDyeingProgramHandler.SetCurrentFlagToNo(ToggleThisDyeingProgram);
        
    }
    
    private boolean UpdateDyeingProgram()
    {
        boolean SuccessfullyUpdated = true;
       
        //thisDyeingProgram.SetID();
        
        //Returns True If Update is Successful then proceed to update other
        //if(thisDyeingProgramHandler.UpdateDyeingProgram(thisDyeingProgram))
        if(WindowProcessType !=3)
        {
             if (!thisDyeingProgramName.getDyeingProgramName().equals(this.ProgramNameText.getText())) {
                DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
                DyeingProgramNameHandler thisDyeingProgramNameHandler = new DyeingProgramNameHandler();
                thisDyeingProgramName.setDyeingProgramName(this.ProgramNameText.getText());
            
                if (!thisDyeingProgramNameHandler.CheckIfDyeingProgramNameHasBeenAddedtoOtherID(thisDyeingProgramName)) //if(thisDyeingProgramNameHandler.UpdateDyeingProgramName(thisDyeingProgramName.getDyeingProgramName()))
                {
                    SuccessfullyUpdated = thisDyeingProgramNameHandler.UpdateDyeingProgramName(thisDyeingProgramName);
                } else {
                    SuccessfullyUpdated = false;
                }
            }
        }
        
        if(SuccessfullyUpdated == true)
        {
            Component[] this_pane = this.GUITabbedPaneProcess.getComponents();
            int ProcessOrder = 1;
        
            for (Component c : this_pane)
            {
                //int<ArrayList> ProcessId = new int<ArrayList>();
                if (c instanceof ProcessPanel) {
                        ProcessPanel ThisProcessPanel = ((ProcessPanel)c);
                        
                        ThisProcessPanel.UpdateThisPanelInDyeingProcess(thisDyeingProgram.getID(), ProcessOrder++);
                    }
            }
        }
        DeleteProcess();
        return SuccessfullyUpdated;
    }
    
    private void DeleteProcess()
    {
        for(int ProcessID : this.ProcessToDelete )
        {
            DyeingProcessHandler thisDyeingProcessHandler = new DyeingProcessHandler();
            thisDyeingProcessHandler.DeleteDyeingProcessById(ProcessID);
            DyeingChemicalHandler thisDyeingChemicalHandler = new DyeingChemicalHandler();
            DyeingChemical thisDyeingChem = new DyeingChemical();
            thisDyeingChem.setDyeingProcessID(ProcessID);
            thisDyeingChemicalHandler.DeleteDyeingChemicalByDyeingProcessID(thisDyeingChem);
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

        BgPanel = new javax.swing.JPanel();
        Header = new javax.swing.JLabel();
        SaveBut = new javax.swing.JButton();
        GUITabbedPaneProcess = new javax.swing.JTabbedPane();
        ProgramNameLabel = new javax.swing.JLabel();
        ProgramNameText = new javax.swing.JTextField();
        CancelBut = new javax.swing.JButton();
        OkayButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setToolTipText("");
        BgPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Add Dyeing Program");
        BgPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 710, 40));

        SaveBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        SaveBut.setText("Add Dyeing Program");
        SaveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButActionPerformed(evt);
            }
        });
        BgPanel.add(SaveBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 650, 250, 40));

        GUITabbedPaneProcess.setBackground(new java.awt.Color(255, 255, 255));
        GUITabbedPaneProcess.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BgPanel.add(GUITabbedPaneProcess, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 770, 500));

        ProgramNameLabel.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ProgramNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        ProgramNameLabel.setText("Program Name :");
        BgPanel.add(ProgramNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 165, 34));

        ProgramNameText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ProgramNameText.setToolTipText("");
        ProgramNameText.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        BgPanel.add(ProgramNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 600, 34));

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        CancelBut.setText("Cancel");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });
        BgPanel.add(CancelBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 650, 250, 40));

        OkayButton.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        OkayButton.setText("Ok");
        OkayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkayButtonActionPerformed(evt);
            }
        });
        BgPanel.add(OkayButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 650, 270, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButActionPerformed
        // TODO add your handling code here:
        //this.jPanel2.getComp
        boolean CloseWindow = false;
        
        if(this.CheckDyeingFormProcessAndSubProcessIfReady())
        {
            switch (WindowProcessType) {
                case 1:
                //For Adding Dyeing Program
                    CloseWindow = AddDyeingProgramNameAndDyeingProgram();
                    break;
                case 2:
                //For Updating Dyeing program
                    CloseWindow = UpdateDyeingProgram();
                    DeleteProcess();
                    break;
                
                //Case 6 for review form
                case 6:
                    /*if (thisDyeingProgram.getCustomerID() == 0) {
                        thisDyeingProgram.setCustomerID(thisJob.getCustomerID());
                        thisDyeingProgram.setColorID(thisJob.getColorID());
                        thisDyeingProgram.setDesignID(thisJob.getDesignID());
                        //If there is no program added for the current customer
                        CloseWindow = this.AddDyeingProgram();
                    } else {
                        //Else just update the program added for the current customer
                        CloseWindow = UpdateDyeingProgram();
                    }

                    thisJob.setDyeingProgramID(thisDyeingProgram.getID()); */
                    if (!new PreferenceHandler().getReviewFormEditing())
                        CloseWindow = true;
                        break;
                default:
                    thisDyeingProgram.setCustomerID(thisJob.getCustomerID());
                    thisDyeingProgram.setColorID(thisJob.getColorID());
                    thisDyeingProgram.setDesignID(thisJob.getDesignID());
                    //If there is no program added for the current customer
                    CloseWindow = this.AddDyeingProgram();
                    if(thisJob.getDyeingProgramID()!=0)
                        this.ToggleCurrentFlagOfDyeingProgram();
                    
                    thisJob.setDyeingProgramID(thisDyeingProgram.getID());
                    break;
                //For Job Order
                    //if Default program then add, else update.
                    // Dyeing program is default if there is no connected customer, color and design
                    
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
                        ReviewFormV3 thisReviewForm = new ReviewFormV3(thisJob, this.WindowProcessType);
                        //Show Review Form and Ask to print
                        thisReviewForm.setVisible(true);
                        //ProcessOrderHandler thisProcess = new ProcessOrderHandler();
                        //thisProcess.AddNewProcessOrder(thisProcessOrder);
                    }
                    break;
                default:
                    ReviewFormV3 thisReviewForm = new ReviewFormV3(thisJob, this.WindowProcessType);
                        //Show Review Form and Ask to print
                    thisReviewForm.setVisible(true);
                    break;
            }
            this.dispose();
        }
    }//GEN-LAST:event_SaveButActionPerformed

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        
        //For Job Order
        if(this.WindowProcessType >= 4)
        {
            //thisProcessOrder.setResinProgramID(0);
            ReviewFormV3 thisReviewForm = new ReviewFormV3(thisJob, this.WindowProcessType);
            //Show Review Form and Ask to print
            thisReviewForm.setVisible(true);
            this.dispose();
            
        }else if(this.WindowProcessType > 2)
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

    private void OkayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkayButtonActionPerformed
        // TODO add your handling code here:
        ReviewFormV3 thisReviewForm = new ReviewFormV3(thisJob, 6);
        //Show Review Form and Ask to print
        thisReviewForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_OkayButtonActionPerformed

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
    private javax.swing.JPanel BgPanel;
    private javax.swing.JButton CancelBut;
    private javax.swing.JTabbedPane GUITabbedPaneProcess;
    private javax.swing.JLabel Header;
    private javax.swing.JButton OkayButton;
    private javax.swing.JLabel ProgramNameLabel;
    private javax.swing.JTextField ProgramNameText;
    private javax.swing.JButton SaveBut;
    // End of variables declaration//GEN-END:variables
}


