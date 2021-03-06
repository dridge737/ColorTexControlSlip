/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.DesignColor;
import DataEntities.Customer;
import DataEntities.Design;
import DataEntities.DyeingChemical;
import DataEntities.DyeingProcess;
import DataEntities.DyeingProgram;
import DataEntities.JobOrder;
import DataEntities.JobOrderExtended;
import DataEntities.Machine;
import DataEntities.ProcessOrder;
import DataEntities.ResinChemical;
import DataEntities.ResinJob;
import DataEntities.ResinProgram;
import Forms.HelpForm.ProcessPanel;
import Forms.HelpForm.ResinPanel;
import Handlers.ColorHandler;
import Handlers.CustomerHandler;
import Handlers.DesignHandler;
import Handlers.DyeingChemicalHandler;
import Handlers.DyeingProcessHandler;
import Handlers.DyeingProgramHandler;
import Handlers.DyeingProgramNameHandler;
import Handlers.JobHandler;
import Handlers.LiquidRatioHandler;
import Handlers.MachineHandler;
import Handlers.PreferenceHandler;
import Handlers.PrintHandlerFinal;
import Handlers.ResinChemicalHandler;
import Handlers.ResinJobHandler;
import Handlers.ResinProgramHandler;
import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.ComboBoxEditor;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Eldridge
 */
public class ReviewFormV3 extends javax.swing.JFrame {

    /**
     * @return the WindowType
     */
    public int getWindowType() {
        return WindowType;
    }

    /**
     * @param WindowType the WindowType to set
     */
    public void setWindowType(int WindowType) {
        this.WindowType = WindowType;
    }

    /**
     * @return the thisJob
     */
    public JobOrderExtended getThisJob() {
        return thisJob;
    }

    /**
     * @param thisJob the thisJob to set
     */
    public void setThisJob(JobOrderExtended updateJobVar) {
        this.thisJob = updateJobVar;
    }

    Machine thisDyeingMachine = new Machine();
    Machine thisResinMachine = new Machine();
    Design thisDesign = new Design();
    Customer thisCustomer = new Customer();
    DesignColor thisColor = new DesignColor();
    private JobOrderExtended thisJob = new JobOrderExtended();
    ProcessOrder thisProcessOrder = new ProcessOrder();
    private int WindowType = 0;
    DyeingProgram thisDyeingProgram = new DyeingProgram();
    boolean ThisJobHasBeenAdded = false;
    ArrayList<String> LiquidRatio = new ArrayList<>();

    //int jobOrderType = 1;
    /**
     * Creates new form ReviewForm
     */
    public ReviewFormV3() {
        initComponents();
        SetToCenter();
        populateCustomerDropDown();
        populateDesignDropDown();
        populateColorDropDown();
        populateDyeingMachineDropDown();
        //populateResinMachineDropDown();
        SetLiquidRatio();   
    }

    public ReviewFormV3(JobOrderExtended currentJob, int setWindowType) {
        this();
        WindowType = setWindowType;
        thisJob = currentJob;
        SetJobOrderDetails();
        SetDropDownDetails();
        SetDyeingProgramName();
        AddResinTabs();
        if (!new PreferenceHandler().getReviewFormEditing()) {
            if (this.getWindowType() == 6) {
                DisableReviewFormEditing();
            }
        }

    }
    
    public void AddResinTabs()
    {
         if(thisJob.getThisResinJob() != null)
        {
            //Add a Resin Panel
            ResinPanel thisResinPanel;
            int index = 0;
            for(ResinJob AllResinJob: thisJob.getThisResinJob())
            {
                thisResinPanel = new ResinPanel(AllResinJob);
                index++;
                DyeingResinTab.add(thisResinPanel, "Resin Program " +index);
            }
            
            //if (currentJob.getResinProgramID() > 0) {
       
            
            //ProcessPanel this_panel;
        //if(WindowProcessType > 2 )
        //if(thisJob.getID()>0 )
        //this_panel = new ProcessPanel(thisProcess, WindowProcessType, thisJob);
        //else
        //this_panel = new ProcessPanel(thisProcess, WindowProcessType);
        
        //Add the Tab to the JtabbedPane
        //GUITabbedPaneProcess.add(this_panel, "Process " + String.valueOf(NumberOfProcessTabs+1),
        //            NumberOfProcessTabs);
        //NumberOfProcessTabs++;
            
            /*
            if (currentJob.getThisResinJob().get(0).getResinProgramID() > 0) {
                SetResinProgramName();
                SetResinMachineNameDropDown();
            } else {
                ResinMachinePanel.setVisible(false);
                //ResinMachinePanel.setEnabled(false);
                //ResinProgramText.setEnabled(false);
                //EditResinProgram.setEnabled(false);
            } */ 
        
}
    }
    
    public void SetLiquidRatio()
    {
         populateLiquidRatioDropDown();
    }

    public void SetToCenter() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
    }

    private void SetDyeingProgramName() {
        thisDyeingProgram
                = new DyeingProgramHandler().GetDyeingProgramDetailsById(getThisJob().getDyeingProgramID());

        DyeingProgramNameHandler thisDyeingProgramName = new DyeingProgramNameHandler();
        String DyeingProgramName = thisDyeingProgramName.GetDyeingProgramNameFromID(thisDyeingProgram.getDyeingProgramNameID());

        DyeingProgramText.setText(DyeingProgramName);

    }
    
    private void SetJobOrderDetails() {
        //thisJob.setID(thisProcessOrder.getJobOrderID());
        //JobHandler JobOrderHandler = new JobHandler();
        //thisJob = JobOrderHandler.GetJobOrderDetailsFromJobId(thisJob.getID());
        if (getWindowType() != 6) {
            //JobOrder.setText("");
            JobOrder.setBackground(Color.WHITE);
            JobOrder.setEnabled(true);
        }
        
        JobOrder.setText(getThisJob().getDrNumber());
        BatchNo.setText(getThisJob().getBatchNo());

        RollLoad.setText(getThisJob().getRollLoad());
        Reference.setText(getThisJob().getReference());
        ProgramNumber.setText(getThisJob().getProgramNumber());
        Location.setText(getThisJob().getLocation());
        //Set Date to 
        if(getThisJob().getJobDate().contains("-"))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateSpinner.setValue(sdf.parse(getThisJob().getJobDate()));
            } catch (ParseException ex) {
                Logger.getLogger(JobOrderForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try {
                dateSpinner.setValue(sdf.parse(getThisJob().getJobDate()));
            } catch (ParseException ex) {
                Logger.getLogger(JobOrderForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void SetDropDownDetails() {
        SetDesignNameDropDown();
        SetDyeingMachineNameDropDown();
        
        SetCustomerNameDropDown();
        SetColorNameDropDown();
        //If Review Form Editing is disabled then don't show save and edit
        
    }
    
    private void DisableReviewFormEditing()
    {
        //WindowType = 7;
        this.SaveExit.setVisible(false);
        this.SavePrint.setText("Print");
        UIManager.put( "ComboBox.disabledBackground", new Color(212,212,210) );
        UIManager.put( "ComboBox.disabledForeground", Color.BLACK );
        this.CustomerDropDownList.setEnabled(false);
        this.CustomerDropDownList.setEditable(false);
        this.DesignDropDownList.setEnabled(false);
        this.ColorDropDownList.setEnabled(false);
        this.DyeingMachineDropDown.setEnabled(false);
        
        DyeingLiquidRatioText.setEnabled(false);
        DyeingLiquidRatioText.setDisabledTextColor(Color.BLACK);
        DyeingLiquidRatioText.setBackground(new Color(212,212,210));
        
        BatchNo.setEnabled(false);
        BatchNo.setDisabledTextColor(Color.BLACK);
        BatchNo.setBackground(new Color(212,212,210));
        
        this.dateSpinner.setEnabled(false);
        JTextField tf = ((JSpinner.DefaultEditor) dateSpinner.getEditor()).getTextField();
        tf.setDisabledTextColor(Color.black);
        tf.setBackground(new Color(212,212,210));
       
        this.Reference.setEnabled(false);
        Reference.setDisabledTextColor(Color.BLACK);
        Reference.setBackground(new Color(212, 212, 210));

        this.ProgramNumber.setEnabled(false);
        ProgramNumber.setDisabledTextColor(Color.BLACK);
        ProgramNumber.setBackground(new Color(212, 212, 210));

        this.Location.setEnabled(false);
        Location.setDisabledTextColor(Color.BLACK);
        Location.setBackground(new Color(212, 212, 210));

        this.RollLoad.setEnabled(false);
        RollLoad.setDisabledTextColor(Color.BLACK);
        RollLoad.setBackground(new Color(212, 212, 210));

        DyeingWeight.setEnabled(false);
        DyeingWeight.setDisabledTextColor(Color.BLACK);
        DyeingWeight.setBackground(new Color(212, 212, 210));

        DyeingVolumeTextField.setEnabled(false);
        DyeingVolumeTextField.setDisabledTextColor(Color.BLACK);
        DyeingVolumeTextField.setBackground(new Color(212, 212, 210));

        EditButton.setVisible(false);
        ChangeButton.setVisible(false);
        
               
        Component[] this_pane = this.DyeingResinTab.getComponents();
        int ProcessOrder = 1;
        for (Component c : this_pane) {
                //System.out.println("Component 1 =" +c);
            if (c instanceof ResinPanel) {
                ResinPanel ThisProcessPanel = ((ResinPanel) c);
                ThisProcessPanel.DisablePanelElements();
            }
        }

    }

    private void SetDesignNameDropDown() {
        thisDesign.setDesignId(getThisJob().getDesignID());
        DesignHandler thisDesignHandler = new DesignHandler();
        thisDesign.setDesignName(thisDesignHandler.GetDesignNameFromID(thisDesign.getDesignId()));
        DesignDropDownList.setSelectedItem(thisDesign.getDesignName());
    }

    private void SetDyeingMachineNameDropDown() {
        thisDyeingMachine.setMachineId(getThisJob().getDyeingMachineID());
        MachineHandler thisMachineHandler = new MachineHandler();
        thisDyeingMachine = thisMachineHandler.GetMachineDetailsById(thisDyeingMachine.getMachineId());
        DyeingMachineDropDown.setSelectedItem(thisDyeingMachine.getMachineName());
        DyeingWeight.setText(Float.toString(getThisJob().getDyeingWeight()));
        DyeingVolumeTextField.setText(Float.toString(getThisJob().getDyeingVolumeH20()));
    }

    

    private void SetCustomerNameDropDown() {
        thisCustomer.setCustomerId(getThisJob().getCustomerID());
        CustomerHandler thisCustomerHandler = new CustomerHandler();
        thisCustomer.setCustomerName(thisCustomerHandler.GetCustomerNameFromCustomerID(thisCustomer.getCustomerId()));
        CustomerDropDownList.setSelectedItem(thisCustomer.getCustomerName());
    }

    private void SetColorNameDropDown() {
        thisColor.setColorId(getThisJob().getColorID());
        ColorHandler thisColorHandler = new ColorHandler();
        thisColor.setColorName(thisColorHandler.GetColorNameFromColorID(thisColor.getColorId()));
        ColorDropDownList.setSelectedItem(thisColor.getColorName());
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
        ChemicalHeader = new javax.swing.JLabel();
        SavePrint = new javax.swing.JButton();
        CancelBut = new javax.swing.JButton();
        MainPanel = new javax.swing.JPanel();
        CustomerDropDownList = new javax.swing.JComboBox<>();
        DesignDropDownList = new javax.swing.JComboBox<>();
        ColorDropDownList = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JobOrder = new javax.swing.JTextField();
        dateSpinner = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        BatchNo = new javax.swing.JTextField();
        ReferenceLabel = new javax.swing.JLabel();
        Reference = new javax.swing.JTextField();
        ProgramNumberLabel = new javax.swing.JLabel();
        ProgramNumber = new javax.swing.JTextField();
        ReferenceLabel1 = new javax.swing.JLabel();
        Location = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        RollLoad = new javax.swing.JTextField();
        SaveExit = new javax.swing.JButton();
        DyeingResinTab = new javax.swing.JTabbedPane();
        DyeingMachinePanel = new javax.swing.JPanel();
        DyeingMachineDropDown = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        DyeingWeight = new javax.swing.JTextField();
        DyeingVolumeTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        DyeingProgramText = new javax.swing.JTextField();
        ChangeButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        DyeingLiquidRatioText = new javax.swing.JTextField();
        EditButton = new javax.swing.JButton();
        ViewButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BgPanel.setPreferredSize(new java.awt.Dimension(730, 620));

        Set forwardkeys = new HashSet(BgPanel
            .getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));

        forwardkeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        BgPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardkeys);

        ChemicalHeader.setBackground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        ChemicalHeader.setForeground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setText("Dyeing Control Slip : Review");

        SavePrint.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        SavePrint.setText("Save & Print");
        SavePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavePrintActionPerformed(evt);
            }
        });

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        CancelBut.setText("Back");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });

        MainPanel.setBackground(new java.awt.Color(102, 102, 102));
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CustomerDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        CustomerDropDownList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Customer" }));
        CustomerDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(CustomerDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 195, 30));

        DesignDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DesignDropDownList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Design" }));
        DesignDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesignDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(DesignDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 195, 30));

        ColorDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ColorDropDownList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Color" }));
        ColorDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(ColorDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 100, 190, 30));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Color :");
        MainPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 90, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Customer :");
        MainPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 60, 100, 30));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Design :");
        MainPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 100, 100, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Job Order :");
        MainPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 100, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Batch No :");
        MainPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 90, 30));

        JobOrder.setBackground(new java.awt.Color(204, 204, 204));
        JobOrder.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        JobOrder.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        JobOrder.setEnabled(false);
        MainPanel.add(JobOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 195, 30));

        dateSpinner.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        dateSpinner.setModel(new javax.swing.SpinnerDateModel());
        MainPanel.add(dateSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 60, 190, 30));
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy"));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Date :");
        MainPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 90, 30));

        BatchNo.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        BatchNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BatchNoKeyReleased(evt);
            }
        });
        MainPanel.add(BatchNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 20, 190, 30));

        ReferenceLabel.setBackground(new java.awt.Color(255, 255, 255));
        ReferenceLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ReferenceLabel.setForeground(new java.awt.Color(255, 255, 255));
        ReferenceLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ReferenceLabel.setText("Reference :");
        MainPanel.add(ReferenceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 140, 100, 30));

        Reference.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MainPanel.add(Reference, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 195, 30));

        ProgramNumberLabel.setBackground(new java.awt.Color(255, 255, 255));
        ProgramNumberLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ProgramNumberLabel.setForeground(new java.awt.Color(255, 255, 255));
        ProgramNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ProgramNumberLabel.setText("Program # :");
        MainPanel.add(ProgramNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 90, 30));

        ProgramNumber.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MainPanel.add(ProgramNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 140, 190, 30));

        ReferenceLabel1.setBackground(new java.awt.Color(255, 255, 255));
        ReferenceLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ReferenceLabel1.setForeground(new java.awt.Color(255, 255, 255));
        ReferenceLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ReferenceLabel1.setText("Location :");
        MainPanel.add(ReferenceLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 180, 100, 30));

        Location.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MainPanel.add(Location, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 195, 30));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Roll Load :");
        MainPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 90, 30));

        RollLoad.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MainPanel.add(RollLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 180, 190, 30));

        SaveExit.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        SaveExit.setText("Save & Exit");
        SaveExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveExitActionPerformed(evt);
            }
        });

        DyeingResinTab.setBackground(new java.awt.Color(255, 255, 255));
        DyeingResinTab.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        DyeingMachinePanel.setBackground(new java.awt.Color(102, 102, 102));
        DyeingMachinePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DyeingMachineDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingMachineDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Machine" }));
        DyeingMachineDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DyeingMachineDropDownActionPerformed(evt);
            }
        });
        DyeingMachinePanel.add(DyeingMachineDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 275, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Weight :");
        DyeingMachinePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 80, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Volume of Water :");
        DyeingMachinePanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 145, 30));

        DyeingWeight.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingWeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DyeingWeightFocusLost(evt);
            }
        });
        DyeingWeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DyeingWeightKeyReleased(evt);
            }
        });
        DyeingMachinePanel.add(DyeingWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 205, 30));

        DyeingVolumeTextField.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingVolumeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DyeingVolumeTextFieldKeyReleased(evt);
            }
        });
        DyeingMachinePanel.add(DyeingVolumeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 170, 30));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Dyeing Program :");
        DyeingMachinePanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 140, 30));

        DyeingProgramText.setEditable(false);
        DyeingProgramText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingProgramText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        DyeingProgramText.setEnabled(false);
        DyeingMachinePanel.add(DyeingProgramText, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 310, 30));

        ChangeButton.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ChangeButton.setText("Change");
        ChangeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeButtonActionPerformed(evt);
            }
        });
        DyeingMachinePanel.add(ChangeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 100, 30));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Liquid Ratio :");
        DyeingMachinePanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 145, 30));

        DyeingLiquidRatioText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingLiquidRatioText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DyeingLiquidRatioTextKeyReleased(evt);
            }
        });
        DyeingMachinePanel.add(DyeingLiquidRatioText, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 170, 30));

        EditButton.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        EditButton.setText("Edit");
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });
        DyeingMachinePanel.add(EditButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 60, 30));

        ViewButton.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ViewButton.setText("View");
        ViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewButtonActionPerformed(evt);
            }
        });
        DyeingMachinePanel.add(ViewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 160, 30));

        DyeingResinTab.addTab("Dyeing Details", DyeingMachinePanel);

        javax.swing.GroupLayout BgPanelLayout = new javax.swing.GroupLayout(BgPanel);
        BgPanel.setLayout(BgPanelLayout);
        BgPanelLayout.setHorizontalGroup(
            BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BgPanelLayout.createSequentialGroup()
                .addGroup(BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BgPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BgPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(BgPanelLayout.createSequentialGroup()
                                .addComponent(SavePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(SaveExit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CancelBut, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(DyeingResinTab, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BgPanelLayout.createSequentialGroup()
                .addComponent(ChemicalHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        BgPanelLayout.setVerticalGroup(
            BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ChemicalHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DyeingResinTab, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelBut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SavePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DyeingLiquidRatioTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DyeingLiquidRatioTextKeyReleased
        // TODO add your handling code here:
        String weight = DyeingWeight.getText();
        if(new LiquidRatioHandler().CheckIfPatternMatchesLiquidRatio(this.DyeingLiquidRatioText.getText().toString()) &&  !weight.isEmpty())
        {
            ComputerDyeingLiquidRatio();
        }

    }//GEN-LAST:event_DyeingLiquidRatioTextKeyReleased

    private void ChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeButtonActionPerformed
        if (CheckIfDyeingMachineHasInputs() && CheckCustomerAndJobOrderFromTextBox()) {
            SetJobOrderDetailsFromTextBox();
            ViewDyeingProgramList thisDyeingProgramListWindow;
            thisDyeingProgramListWindow = new ViewDyeingProgramList(this.getThisJob(), this.getWindowType());
            thisDyeingProgramListWindow.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_ChangeButtonActionPerformed

    private void DyeingVolumeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DyeingVolumeTextFieldKeyReleased
        // TODO add your handling code here:
        String volume = this.DyeingVolumeTextField.getText();
        volume = volume.replaceAll("[^\\d.]", "");
        DyeingVolumeTextField.setText(volume);
        if (this.CheckTextBoxIsParseValid(DyeingVolumeTextField)) {
            getThisJob().setDyeingVolumeH20(Float.parseFloat(volume));
        }
    }//GEN-LAST:event_DyeingVolumeTextFieldKeyReleased

    private void DyeingWeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DyeingWeightKeyReleased
        String weight = DyeingWeight.getText();
        weight = weight.replaceAll("[^\\d.]", "");
        DyeingWeight.setText(weight);
        if (this.CheckTextBoxIsParseValid(DyeingWeight)) {
            getThisJob().setDyeingWeight(Float.parseFloat(weight));
            if(new LiquidRatioHandler().CheckIfPatternMatchesLiquidRatio(this.DyeingLiquidRatioText.getText().toString()))
            ComputerDyeingLiquidRatio();
        }

    }//GEN-LAST:event_DyeingWeightKeyReleased

    private void DyeingWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DyeingWeightFocusLost
        //ComputeForDyeingVolume();
        try{
            Float.parseFloat(this.DyeingWeight.getText());
            //Float RoundTo = (float) Math.round(Float.parseFloat(Weight.getText()) / 100) * 100;
            //DyeingWeight.setText(new LiquidRatioHandler().RoundToHundreds(Float.parseFloat(DyeingWeight.getText())).toString());
        }
        catch (NumberFormatException formatException) {
            JOptionPane.showMessageDialog(null, "Please change the value of the weight to a valid number.");
        }
    }//GEN-LAST:event_DyeingWeightFocusLost

    private void DyeingMachineDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DyeingMachineDropDownActionPerformed

        Machine machineDetails = new Machine();
        MachineHandler handler = new MachineHandler();
        int machineId = -1;
        String machineName = "";

        if (!DyeingMachineDropDown.getSelectedItem().toString().equals("Choose Machine")) {
            machineName = DyeingMachineDropDown.getSelectedItem().toString();

            if (!machineName.equals("")) {
                machineId = handler.GetMachineIdByName(machineName);

                if (machineId > -1) {
                    machineDetails = handler.GetMachineDetailsById(machineId);

                    thisDyeingMachine.setMaxCapacity(machineDetails.getMaxCapacity());
                    thisDyeingMachine.setMaxVolume(machineDetails.getMaxVolume());
                    thisDyeingMachine.setMinCapacity(machineDetails.getMinCapacity());
                    thisDyeingMachine.setMinVolume(machineDetails.getMinVolume());
                    thisDyeingMachine.setNumOfLoad(machineDetails.getNumOfLoad());
                }
            }
        }
        thisDyeingMachine.setMachineId(machineId);
        thisDyeingMachine.setMachineName(machineDetails.getMachineName());
    }//GEN-LAST:event_DyeingMachineDropDownActionPerformed

    private boolean CheckIfDyeingMatchesColorCustomerAndDesign()
    {
        boolean Match = true;
        //thisResinProgram.setIDcurrentResinJob.getResinProgramID();
        //ResinProgram thisResinProgram = new ResinProgram();
        DyeingProgram oldDyeingProgram = new DyeingProgramHandler().GetDyeingProgramDetailsById(getThisJob().getDyeingProgramID());

        if ((getThisJob().getColorID() != oldDyeingProgram.getColorID() || getThisJob().getCustomerID() != oldDyeingProgram.getCustomerID())
                || getThisJob().getDesignID() != oldDyeingProgram.getDesignID()) {
            Match = false;
        }
        //if(new ResinProgramHandler().CheckIfResinProgramNameExists(ResinName))            

        return Match;
    }
    
    private boolean CheckIfResinMatchesColorCustomerAndDesign(ResinJob currentResinJob)
    {
        boolean Match = true;
        if(currentResinJob != null)
        {
                //thisResinProgram.setIDcurrentResinJob.getResinProgramID();
                ResinProgram thisResinProgram = new ResinProgram();
                thisResinProgram = new ResinProgramHandler().GetResinProgramDetailsFromResinID(currentResinJob.getResinProgramID());
                
                 if((getThisJob().getColorID() != thisResinProgram.getColorID() || getThisJob().getCustomerID() != thisResinProgram.getCustomerID())
                    || getThisJob().getDesignID() != thisResinProgram.getDesignID())
                     Match = false;                 
                //if(new ResinProgramHandler().CheckIfResinProgramNameExists(ResinName))            
        }
        return Match;
    }
    
    private int CheckAndAddDyeingProgramDetails()
    {
        int dyeingProgramID;
        DyeingProgram currentDyeingProgram = new DyeingProgramHandler().GetDyeingProgramDetailsById(getThisJob().getDyeingProgramID());
                //.GetResinProgramDetailsFromResinID(thisResinJob.getResinProgramID());
        currentDyeingProgram.setColorID(getThisJob().getColorID());
        currentDyeingProgram.setCustomerID(getThisJob().getCustomerID());
        currentDyeingProgram.setDesignID(getThisJob().getDesignID());

        //Check a Program for dyeing program of the same program name / Color / Design / Customer exists  
        //IF it exists then just update the DyeingProgram on the Dyeing Job
        if (new DyeingProgramHandler().CheckIfSpecificDyeingProgramExistForThisCustomer(currentDyeingProgram)) 
        {
            //when dyeing program exists get the dyeing program id and add to the current resin job
            dyeingProgramID = new DyeingProgramHandler().GetDyeingProgramIDForCustomerDyeingProgram(currentDyeingProgram);
            //thisResinJob.setResinProgramID(currentResinProgram.getID());
            
        } //Else add the dyeing program using details from the old dyeing program
        else 
        {
            int oldDyeingProgramID = thisJob.getDyeingProgramID();
            //Add Resin program to the database
            int newDyeingProgramID = new DyeingProgramHandler().AddDyeingProgram(currentDyeingProgram);

            ArrayList<DyeingProcess> DyeingProcessList = new DyeingProcessHandler().GetAllDyeingProcessByDyeingProgramId(oldDyeingProgramID);
            //Get the previous Dyeing process and add to this new dyeing process
            for (DyeingProcess currentProcess : DyeingProcessList) {
                currentProcess.setDyeingProgramId(newDyeingProgramID);
                int oldDyeingProcessID = currentProcess.getId();
                int dyeingProcessID = new DyeingProcessHandler().AddDyeingProcess(currentProcess);
                //Get the previous Dyeing chemicals and add to this new dyeing program
                ArrayList<DyeingChemical> DyeingChemicalList = new DyeingChemicalHandler().GetAllDyeingChemicalFromDyeingProcessID(oldDyeingProcessID);
                for (DyeingChemical currentDyeingChemical : DyeingChemicalList) {

                    currentDyeingChemical.setDyeingProcessID(dyeingProcessID);
                //Add the dyeing chemical to a new row.
                    new DyeingChemicalHandler().AddNewDyeingChemical(currentDyeingChemical);
                    
                }
            }
            dyeingProgramID = newDyeingProgramID;
            
        }
        return dyeingProgramID;
        
    }
    
    private int CheckAndAddResinProgramDetails(ResinJob thisResinJob) {
        int NewResinProgramID;
        ResinProgram currentResinProgram = new ResinProgramHandler().GetResinProgramDetailsFromResinID(thisResinJob.getResinProgramID());
        currentResinProgram.setColorID(getThisJob().getColorID());
        currentResinProgram.setCustomerID(getThisJob().getCustomerID());
        currentResinProgram.setDesignID(getThisJob().getDesignID());

        //Check a Program for Resin program of the same Color / Design / Customer exists  
        //IF it exists then just update the ResinProgram on the Resin Job
        if (new ResinProgramHandler().CheckIfResinProgramExists(currentResinProgram)) 
        {
            //when resin program exists get the Resin program id and add to the current resin job
            currentResinProgram = new ResinProgramHandler().GetResinProgramDetailsForThisResinAndCustomer(currentResinProgram);
            //thisResinJob.setResinProgramID(currentResinProgram.getID());
            NewResinProgramID = currentResinProgram.getID();
            
        } //Else add the resin program using details from the old resin program
        else 
        {
            int oldResinProgramID = thisResinJob.getResinProgramID();
            //Add Resin program to the database
            int newResinProgramID = new ResinProgramHandler().AddNewResinProgram(currentResinProgram);

            //Get the previous resin chemicals and add to this new resin program
            ArrayList<ResinChemical> resinChemicalList = new ResinChemicalHandler().GetResinChemicalsByResinProgramId(oldResinProgramID);
            for (ResinChemical currentResinChemical : resinChemicalList) {
                currentResinChemical.setResinProgramID(newResinProgramID);
                //Add the resin chemical to a new row.
                new ResinChemicalHandler().AddNewResinChemical(currentResinChemical);
            }
            // update the resin program id to reflect the current resin job
            NewResinProgramID = newResinProgramID;
            //thisResinJob.setResinProgramID(newResinProgramID);
        }
        
        return NewResinProgramID;
    }
    private boolean SaveThisJobOrder()
    {
        boolean Success = true;
        JobHandler thisJobHandler = new JobHandler();
        if (ThisJobHasBeenAdded == true) {
            JOptionPane.showMessageDialog(null, "Job order has already been added.");
            this.dispose();
        } else {
            if (AddTextToJobOrderObject()) {
                
                if (!CheckIfDyeingMatchesColorCustomerAndDesign()) {
                    //getThisJob().setDyeingProgramID(CheckAndAddDyeingProgramDetails());
                
                    DyeingProgram currentDyeingProgram = new DyeingProgram();
                    currentDyeingProgram.SetID(getThisJob().getDyeingProgramID());
                    currentDyeingProgram.setCustomerID(getThisJob().getCustomerID());
                    currentDyeingProgram.setColorID(getThisJob().getColorID());
                    currentDyeingProgram.setDesignID(getThisJob().getDesignID());
                    currentDyeingProgram.setDyeingProgramNameID(new DyeingProgramNameHandler().GetDyeingProgramNameIDfromName(DyeingProgramText.getText()));
                    int previousDyeingProgramID = currentDyeingProgram.getID();
                    currentDyeingProgram.SetID(new DyeingProgramHandler().AddDyeingProgram(currentDyeingProgram));
                    getThisJob().setDyeingProgramID(currentDyeingProgram.getID());

                    ArrayList<DyeingProcess> thisDyeingProcess;
                    thisDyeingProcess = new DyeingProcessHandler().GetAllDyeingProcessByDyeingProgramId(previousDyeingProgramID);
                    for (DyeingProcess loopCurrentProcess : thisDyeingProcess) {
                        loopCurrentProcess.setDyeingProgramId(currentDyeingProgram.getID());
                        //Add dyeing process and set ID
                        int previousDyeingProcessID = loopCurrentProcess.getId();
                        loopCurrentProcess.setId(new DyeingProcessHandler().AddDyeingProcess(loopCurrentProcess));

                        ArrayList<DyeingChemical> ThisDyeingChemical;
                        ThisDyeingChemical = new DyeingChemicalHandler().GetAllDyeingChemicalFromDyeingProcessID(previousDyeingProcessID);
                        for (DyeingChemical loopDyeingChemical : ThisDyeingChemical) {
                            loopDyeingChemical.setDyeingProcessID(loopCurrentProcess.getId());
                            new DyeingChemicalHandler().AddNewDyeingChemical(loopDyeingChemical);
                        }
                    }
                }
                
                if (this.getWindowType() != 6) {
                    int jobOrderID = thisJobHandler.AddNewJobOrder(getThisJob());
                    //IF Job Order has already been added
                    if ( jobOrderID > 0) {
                        //If there is a resin Program.
                        if (getThisJob().getThisResinJob() != null) {
                            for (ResinJob thisResinJob : getThisJob().getThisResinJob()) {
                                ResinProgram addNewResinProgramForThisJob =
                                        new ResinProgramHandler().GetResinProgramDetailsFromResinID(thisResinJob.getResinProgramID());
                                addNewResinProgramForThisJob.setColorID(getThisJob().getColorID());
                                addNewResinProgramForThisJob.setCustomerID(getThisJob().getCustomerID());
                                addNewResinProgramForThisJob.setDesignID(getThisJob().getDesignID());
                                
                                ArrayList<ResinChemical> ThisResinChemical = new ResinChemicalHandler().GetResinChemicalsByResinProgramId(thisResinJob.getResinProgramID());
                                int UpdatedResinProgramID = new ResinProgramHandler().AddNewResinProgram(addNewResinProgramForThisJob);
                                
                                for(ResinChemical thisJobResinChemical : ThisResinChemical)
                                {
                                    thisJobResinChemical.setResinProgramID(UpdatedResinProgramID);
                                    new ResinChemicalHandler().AddNewResinChemical(thisJobResinChemical);
                                }
                                thisResinJob.setResinProgramID(UpdatedResinProgramID);
                                thisResinJob.setJobOrderID(jobOrderID);
                                new ResinJobHandler().AddResinJob(thisResinJob);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Job order has been added.");
                         ThisJobHasBeenAdded = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "There is an error in adding the Job Order.");
                        Success = false;
                    }
                } else if (getWindowType() == 6) {

                    if (thisJobHandler.UpdateJobOrder(getThisJob())) {
                        //Also Update Resin Program
                        for (ResinJob thisResinJob : getThisJob().getThisResinJob()) {
                            if (!this.CheckIfResinMatchesColorCustomerAndDesign(thisResinJob)) {
                                thisResinJob.setResinProgramID(CheckAndAddResinProgramDetails(thisResinJob));
                            }

                            thisResinJob.setJobOrderID(getThisJob().getID());
                            new ResinJobHandler().UpdateResinJob(thisResinJob);

                        }
                        JOptionPane.showMessageDialog(null, "Job order has been updated.");
                        // ThisJobHasBeenAdded = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "There is an error in updating the Job Order.");
                        Success = false;
                    }
                }
            }
        }
    
        return Success;
    }
    private void SaveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveExitActionPerformed
        // TODO add your handling code here:
        if(this.SaveThisJobOrder())
            this.dispose();
/*
        JobHandler thisJobHandler = new JobHandler();
        if (ThisJobHasBeenAdded == true) {
            JOptionPane.showMessageDialog(null, "Job order has already been added.");
            this.dispose();
        } else {
            if (AddTextToJobOrderObject()) {
                if (this.getWindowType() != 6) {
                    int jobOrderID = thisJobHandler.AddNewJobOrder(getThisJob());
                    if ( jobOrderID > 0) {
                        if (getThisJob().getThisResinJob() != null) {
                            for (ResinJob thisResinJob : getThisJob().getThisResinJob()) {
                                thisResinJob.setJobOrderID(jobOrderID);
                                new ResinJobHandler().AddResinJob(thisResinJob);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Job order has been added.");
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "There is an error in adding the Job Order.");
                    }

                } else if (getWindowType() == 6) {
                    if(!CheckIfDyeingMatchesColorCustomerAndDesign())
                    {
                        getThisJob().setDyeingProgramID(CheckAndAddDyeingProgramDetails());
                    }
                    
                    if (thisJobHandler.UpdateJobOrder(getThisJob())) {
                        //Also Update Resin Program
                        for (ResinJob thisResinJob : getThisJob().getThisResinJob()) 
                        {
                            if(!this.CheckIfResinMatchesColorCustomerAndDesign(thisResinJob))
                            {
                                thisResinJob.setResinProgramID(CheckAndAddResinProgramDetails(thisResinJob));
                            }
                            
                            thisResinJob.setJobOrderID(getThisJob().getID());
                            new ResinJobHandler().UpdateResinJob(thisResinJob);
                            this.dispose();
                             
                        }
                        JOptionPane.showMessageDialog(null, "Job order has been updated.");
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "There is an error in updating the Job Order.");
                    }
                }
            }
        }
        */
    }//GEN-LAST:event_SaveExitActionPerformed

    private void BatchNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BatchNoKeyReleased
        // TODO add your handling code here:
        //String thisBatchNo = this.BatchNo.getText();
        //thisBatchNo = thisBatchNo.replaceAll("[^\\d.]", "");
        //this.BatchNo.setText(thisBatchNo);
    }//GEN-LAST:event_BatchNoKeyReleased

    private void ColorDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorDropDownListActionPerformed
        DesignColor colorDetails = new DesignColor();
        ColorHandler handler = new ColorHandler();
        //int colorId = -1;
        String colorName = "";

        if (!ColorDropDownList.getSelectedItem().toString().equals("Choose Color")) {
            colorName = ColorDropDownList.getSelectedItem().toString();
            thisColor.setColorName(colorName);
        }

        if (!colorName.equals("")) {
            //colorId = handler.GetColorIDFromColorName(colorName);
            thisColor.setColorId(handler.GetColorIDFromColorName(colorName));
        }
    }//GEN-LAST:event_ColorDropDownListActionPerformed

    private void DesignDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesignDropDownListActionPerformed
        Design designDetails = new Design();
        DesignHandler handler = new DesignHandler();
        //int designId = -1;

        //if(!designName.equals(""))
        if (!DesignDropDownList.getSelectedItem().toString().equals("Choose Design")
            && (DesignDropDownList.getSelectedItem().toString().length() > 1)) {
            String designName = DesignDropDownList.getSelectedItem().toString();
            thisDesign.setDesignName(designName);
            //designId = handler.GetDesignIDFromName(designName);
            thisDesign.setDesignId(handler.GetDesignIDFromName(designName));
        }
    }//GEN-LAST:event_DesignDropDownListActionPerformed

    private void CustomerDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerDropDownListActionPerformed
        Customer customerDetails = new Customer();
        CustomerHandler handler = new CustomerHandler();
        //int customerId = -1;
        String customerName = "";

        if (!CustomerDropDownList.getSelectedItem().toString().equals("Choose Customer")) {
            customerName = this.CustomerDropDownList.getSelectedItem().toString();
            thisCustomer.setCustomerName(customerName);
        }

        if (!customerName.equals("")) {
            //customerId = handler.GetCustomerIDFromCustomerName(customerName);
            thisCustomer.setCustomerId(handler.GetCustomerIDFromCustomerName(customerName));
        }
    }//GEN-LAST:event_CustomerDropDownListActionPerformed

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        if(!this.ThisJobHasBeenAdded)
        {
            //System.out.println(this.getWindowType());
            if (this.getWindowType() == 6 && !(new PreferenceHandler().getReviewFormEditing()) ) {
                new ViewCustomerOrder().setVisible(true);
            } else if (this.getWindowType() == 4 || this.getWindowType() == 5 || this.getWindowType() == 6) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to cancel using this Job Order?", "Exit?", JOptionPane.YES_NO_OPTION)) {
                    new ViewCustomerOrder().setVisible(true);
                }
            }
            else 
            {
                if (getThisJob().getThisResinJob() != null) {
                    new AddResinForm(getThisJob()).setVisible(true);
                } else {
                    new DyeingForm(getThisJob()).setVisible(true);

                }

            }
        }
        this.dispose();
    }//GEN-LAST:event_CancelButActionPerformed

    
    
    private void SavePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavePrintActionPerformed
        // TODO add your handling code here:
        //IF Review form editing is disabled and window type is 6(review order button)
        if(!this.SavePrint.getText().equals("Print"))
            SaveThisJobOrder();
        /*
        JobHandler thisJobHandler = new JobHandler();
        if (AddTextToJobOrderObject()) {
            if (ThisJobHasBeenAdded != true) {
                //if (WindowType == 3) {
                    if (getWindowType() == 6) {
                        thisJobHandler.UpdateJobOrder(getThisJob());
                        
                    }
                    //else if ((WindowType == 5 || WindowType == 4 || WindowType == 2 || WindowType == 1)) {
                        else {
                            thisJobHandler.AddNewJobOrder(getThisJob());
                        }
                        ThisJobHasBeenAdded = true;

                    }
        */
                    try {
                        PrintHandlerFinal handler = new PrintHandlerFinal();
                        handler.createPDF(thisDyeingMachine, thisDesign, thisCustomer, thisColor, getThisJob(), thisDyeingProgram, DyeingVolumeTextField.getText());
                    } catch (IOException | DocumentException e) {
                    }
                //}
    }//GEN-LAST:event_SavePrintActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        // TODO add your handling code here:
         if (CheckIfDyeingMachineHasInputs() && CheckCustomerAndJobOrderFromTextBox()) {
            SetJobOrderDetailsFromTextBox();
            
            //int DyeingProgramID = new DyeingProgramHandler().GetDyeingProgramIDfromName(DyeingTable.getModel().getValueAt(selectedRow, 0).toString());
            //int convertedRowNumber = DyeingTable.convertRowIndexToModel(this.DyeingTable.getSelectedRow());
            String DyeingName = DyeingProgramText.getText();
            DyeingForm thisDyeingForm = new DyeingForm(null, thisJob, WindowType);
            thisDyeingForm.setVisible(true);
            this.dispose();
         }
        
    }//GEN-LAST:event_EditButtonActionPerformed

    private void ViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewButtonActionPerformed
        // TODO add your handling code here:
        String DyeingName = DyeingProgramText.getText();
        DyeingForm thisDyeingForm = new DyeingForm(null, thisJob, WindowType);
        thisDyeingForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ViewButtonActionPerformed

    private boolean CheckCustomerAndJobOrderFromTextBox()
    {
        boolean isSuccessful = true;
        
        if (thisCustomer.getCustomerId() < 1) {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Customer Name.");
        }  else if (JobOrder.getText().length() < 1) {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Job Order number.");
        }
        
        return isSuccessful;
        
    }
    
    private boolean CheckAllFormInformationFromTextBox() {
        boolean isSuccessful = true;

        if (!CheckCustomerAndJobOrderFromTextBox()) {
            isSuccessful = false;
            
        } else if (thisColor.getColorId() < 1) {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Color name.");
        } else if (thisDesign.getDesignId() < 1) {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Design name.");
        } 
        /*else if(this.Reference.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the the Reference."); 
        }
        else if(this.ProgramNumber.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Program Number."); 
        }
        else if(this.Location.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Location."); 
        }
        
         else if(this.BatchNo.getText().length() < 1)
         {
         isSuccessful = false;
         JOptionPane.showMessageDialog(null, "Please check the Batch number.");
         }*/ 
        else if(!CheckIfDyeingMachineHasInputs())
        {
            isSuccessful = false;
        }
        //else if(thisResinMachine.getMachineId() > 0)
        //{
        //    isSuccessful = CheckIfResinMachineHasInputs();
        //}
        
        return isSuccessful;
    }

    public boolean CheckTextBoxIsParseValid(JTextField thisTextField) {
        return thisTextField.getText().length() >= 1;
    }

    private void SetJobOrderDetailsFromTextBox()
    {
        getThisJob().setCustomerID(thisCustomer.getCustomerId());
        getThisJob().setColorID(thisColor.getColorId());
        getThisJob().setDesignID(thisDesign.getDesignId());
        getThisJob().setDrNumber(JobOrder.getText());
        getThisJob().setJobDate(get_date_from_spinner(dateSpinner));
        getThisJob().setBatchNo(BatchNo.getText());
        getThisJob().setDyeingMachineID(thisDyeingMachine.getMachineId());
        getThisJob().setDyeingVolumeH20(Float.parseFloat(this.DyeingVolumeTextField.getText()));
        getThisJob().setDyeingWeight(Float.parseFloat(this.DyeingWeight.getText()));
        getThisJob().setRollLoad(RollLoad.getText());
        getThisJob().setReference(Reference.getText());
        getThisJob().setProgramNumber(this.ProgramNumber.getText());
        getThisJob().setLocation(this.Location.getText());

        Component[] this_pane = this.DyeingResinTab.getComponents();
        int ResinPanelNumber = 1;

        ArrayList<ResinJob> thisResinJobList = new ArrayList<ResinJob>();
        for (Component c : this_pane) {
            if (c instanceof ResinPanel) {
                ResinPanel thisResinPanel = ((ResinPanel) c);
                ResinJob thisResinJob = thisResinPanel.getDataInThisPanel();
                //thisResinJob.setJobOrderID(thisJob.getID());
                thisResinJobList.add(thisResinJob);
            }

        }
        thisJob.setThisResinJob(thisResinJobList);

        
    }
    
    public boolean AddTextToJobOrderObject() {
        boolean isSuccessful = false;
        //If all inputs are good
        if (CheckAllFormInformationFromTextBox()) {
            SetJobOrderDetailsFromTextBox();
            isSuccessful = true;
        }
        return isSuccessful;
    }
    private boolean CheckIfDyeingMachineHasInputs()
    {
        boolean isSuccessful = true;
        
        if(thisDyeingMachine.getMachineId() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please choose a Dyeing Machine.");  
        }
        else if(this.DyeingVolumeTextField.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the value in Volume of Water in Dyeing Machine."); 
        }
        else if(this.DyeingWeight.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the value in the Weight in Dyeing Machine."); 
        }
        
        return isSuccessful;
    }
    
    private void ComputerDyeingLiquidRatio()
    {
        String weight = DyeingWeight.getText();
        if(new LiquidRatioHandler().CheckIfPatternMatchesLiquidRatio(this.DyeingLiquidRatioText.getText().toString()) &&  !weight.equals(""))
        {
        //String liquidRatio = LiquidRatioTextField.toString();
            String[] RatioSplit = DyeingLiquidRatioText.getText().split(":", 2);
            int WeightMultiplier = Integer.parseInt(RatioSplit[1]) / Integer.parseInt(RatioSplit[0]);

            int volume = (((int) (Float.parseFloat(weight) * WeightMultiplier))); // /10 * 10;
            DyeingVolumeTextField.setText(Double.toString(volume));
        }
    }
    
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
            java.util.logging.Logger.getLogger(ReviewFormV3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReviewFormV3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReviewFormV3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReviewFormV3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReviewFormV3().setVisible(true);
            }
        });
    }

    public String get_date_from_spinner(JSpinner this_spinner) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        return formater.format(this_spinner.getValue());
    }

    private String computeForVolume(int weight, String LiquidRatio) {
        int weightMultiplier = 0;

        if (LiquidRatio.equals("1:6")) {
            weightMultiplier = 6;
        } else if (LiquidRatio.equals("1:8")) {
            weightMultiplier = 8;
        } else if (LiquidRatio.equals("1:9")) {
            weightMultiplier = 9;
        } else if (LiquidRatio.equals("1:10")) {
            weightMultiplier = 10;
        } else if (LiquidRatio.equals("1:12")) {
            weightMultiplier = 12;
        }

        double volume = weight * weightMultiplier;

        //DyeingVolumeTextField.setText(Double.toString(volume));
        return Double.toString(volume);
    }

    private void populateLiquidRatioDropDown() {
        LiquidRatio = new LiquidRatioHandler().addLiquidRatioTextBoxAutoComplete(this.DyeingLiquidRatioText);
        
        this.DyeingLiquidRatioText.getDocument().addDocumentListener(new DocumentListener() 
        {
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                ComputerDyeingLiquidRatio();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ComputerDyeingLiquidRatio();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               ComputerDyeingLiquidRatio();
            }
        });
        
        DyeingLiquidRatioText.setFocusTraversalKeysEnabled(false);
        /*
        ArrayList<String> LiquidRatioList = new ArrayList<String>();

        LiquidRatioList.add("1:6");
        LiquidRatioList.add("1:8");
        LiquidRatioList.add("1:9");
        LiquidRatioList.add("1:10");
        LiquidRatioList.add("1:12");

        for (int x = 0; x < LiquidRatioList.size(); x++) {
            DyeingMachineLiquidRatioDropDown.addItem(LiquidRatioList.get(x));
            ResinMachineLiquidRatioDropDown.addItem(LiquidRatioList.get(x));
        }
        */
    }
    /*
     private void initTextFields(){
        
     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
     Date date = new Date();
        
     VolumeTextField.setEditable(false);
     CreatedDate.setEditable(false);
     CreatedDate.setText(dateFormat.format(date).toString());
        
     }
     */

    private void populateDyeingMachineDropDown() {
        ArrayList<Machine> MachineList = new MachineHandler().GetAllDyeingMachines();

        if (MachineList != null) {
            for (int x = 0; x < MachineList.size(); x++) {
                DyeingMachineDropDown.addItem(MachineList.get(x).getMachineName());
            }
        }

    }

    private void populateColorDropDown() {
        ArrayList<String> ColorList = new ColorHandler().GetAllColor();

        if (ColorList != null) {
            for (int x = 0; x < ColorList.size(); x++) {
                ColorDropDownList.addItem(ColorList.get(x));
            }
        }
    }

    private void populateDesignDropDown() {
        ArrayList<String> DesignList = new DesignHandler().GetAllDesigns();

        if (DesignList != null) {
            for (int x = 0; x < DesignList.size(); x++) {
                DesignDropDownList.addItem(DesignList.get(x));
            }
        }
    }

    private void populateCustomerDropDown() {
        ArrayList<String> CustomerList = new CustomerHandler().GetAllCustomers();

        if (CustomerList != null) {
            for (String CustomerList1 : CustomerList) {
                CustomerDropDownList.addItem(CustomerList1);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BatchNo;
    private javax.swing.JPanel BgPanel;
    private javax.swing.JButton CancelBut;
    private javax.swing.JButton ChangeButton;
    private javax.swing.JLabel ChemicalHeader;
    private javax.swing.JComboBox<String> ColorDropDownList;
    private javax.swing.JComboBox<String> CustomerDropDownList;
    private javax.swing.JComboBox<String> DesignDropDownList;
    private javax.swing.JTextField DyeingLiquidRatioText;
    private javax.swing.JComboBox<String> DyeingMachineDropDown;
    private javax.swing.JPanel DyeingMachinePanel;
    private javax.swing.JTextField DyeingProgramText;
    private javax.swing.JTabbedPane DyeingResinTab;
    private javax.swing.JTextField DyeingVolumeTextField;
    private javax.swing.JTextField DyeingWeight;
    private javax.swing.JButton EditButton;
    private javax.swing.JTextField JobOrder;
    private javax.swing.JTextField Location;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JTextField ProgramNumber;
    private javax.swing.JLabel ProgramNumberLabel;
    private javax.swing.JTextField Reference;
    private javax.swing.JLabel ReferenceLabel;
    private javax.swing.JLabel ReferenceLabel1;
    private javax.swing.JTextField RollLoad;
    private javax.swing.JButton SaveExit;
    private javax.swing.JButton SavePrint;
    private javax.swing.JButton ViewButton;
    private javax.swing.JSpinner dateSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
