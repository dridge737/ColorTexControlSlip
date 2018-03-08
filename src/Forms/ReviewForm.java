/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.DesignColor;
import DataEntities.Customer;
import DataEntities.Design;
import DataEntities.DyeingProgram;
import DataEntities.JobOrder;
import DataEntities.Machine;
import DataEntities.ProcessOrder;
import Handlers.ColorHandler;
import Handlers.CustomerHandler;
import Handlers.DesignHandler;
import Handlers.DyeingProgramHandler;
import Handlers.DyeingProgramNameHandler;
import Handlers.JobHandler;
import Handlers.LiquidRatioHandler;
import Handlers.MachineHandler;
import Handlers.PreferenceHandler;
import Handlers.PrintHandlerFinal;
import Handlers.ResinProgramHandler;
import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Eldridge
 */
public class ReviewForm extends javax.swing.JFrame {

    Machine thisDyeingMachine = new Machine();
    Machine thisResinMachine = new Machine();
    Design thisDesign = new Design();
    Customer thisCustomer = new Customer();
    DesignColor thisColor = new DesignColor();
    JobOrder thisJob = new JobOrder();
    ProcessOrder thisProcessOrder = new ProcessOrder();
    int WindowType = 0;
    DyeingProgram thisDyeingProgram = new DyeingProgram();
    boolean ThisJobHasBeenAdded = false;
    ArrayList<String> LiquidRatio = new ArrayList<>();

    //int jobOrderType = 1;
    /**
     * Creates new form ReviewForm
     */
    public ReviewForm() {
        initComponents();
        SetToCenter();
        populateCustomerDropDown();
        populateDesignDropDown();
        populateColorDropDown();
        populateDyeingMachineDropDown();
        populateResinMachineDropDown();
        SetLiquidRatio();   
    }

    public ReviewForm(JobOrder currentJob, int setWindowType) {
        this();
        WindowType = setWindowType;
        thisJob = currentJob;
        SetJobOrderDetails();
        SetDropDownDetails();
        SetDyeingProgramName();
        if (currentJob.getResinProgramID() > 0) {
            SetResinProgramName();
            SetResinMachineNameDropDown();
        } else {
            ResinMachinePanel.setVisible(false);
            //ResinMachinePanel.setEnabled(false);
            //ResinProgramText.setEnabled(false);
            //EditResinProgram.setEnabled(false);
        }

        //If only Dyeing Program is added.
        //if(WindowType == 1)
        //{
        //}
        //else 
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
                = new DyeingProgramHandler().GetDyeingProgramDetailsById(thisJob.getDyeingProgramID());

        DyeingProgramNameHandler thisDyeingProgramName = new DyeingProgramNameHandler();
        String DyeingProgramName = thisDyeingProgramName.GetDyeingProgramNameFromID(thisDyeingProgram.getDyeingProgramNameID());

        DyeingProgramText.setText(DyeingProgramName);

    }

    private void SetResinProgramName() {
        if (thisJob.getResinProgramID() > 0) {
            String ResinProgramName
                    = new ResinProgramHandler().GetResinProgramNameFromResinProgramID(thisJob.getResinProgramID());
            ResinProgramText.setText(ResinProgramName);
        }

    }

    private void SetJobOrderDetails() {
        //thisJob.setID(thisProcessOrder.getJobOrderID());
        //JobHandler JobOrderHandler = new JobHandler();
        //thisJob = JobOrderHandler.GetJobOrderDetailsFromJobId(thisJob.getID());
        if (WindowType == 4) {
            //JobOrder.setText("");
            JobOrder.setBackground(Color.WHITE);
            JobOrder.setEnabled(true);
        }        
        else
        {
            JobOrder.setText(thisJob.getDrNumber());
        }
        BatchNo.setText(Integer.toString(thisJob.getBatchNo()));

        RollLoad.setText(thisJob.getRollLoad());
        Reference.setText(thisJob.getReference());
        ProgramNumber.setText(thisJob.getProgramNumber());
        Location.setText(thisJob.getLocation());
        //Set Date to 
        if(thisJob.getJobDate().contains("-"))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateSpinner.setValue(sdf.parse(thisJob.getJobDate()));
            } catch (ParseException ex) {
                Logger.getLogger(JobOrderForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try {
                dateSpinner.setValue(sdf.parse(thisJob.getJobDate()));
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
    }

    private void SetDesignNameDropDown() {
        thisDesign.setDesignId(thisJob.getDesignID());
        DesignHandler thisDesignHandler = new DesignHandler();
        thisDesign.setDesignName(thisDesignHandler.GetDesignNameFromID(thisDesign.getDesignId()));
        DesignDropDownList.setSelectedItem(thisDesign.getDesignName());
    }

    private void SetDyeingMachineNameDropDown() {
        thisDyeingMachine.setMachineId(thisJob.getDyeingMachineID());
        MachineHandler thisMachineHandler = new MachineHandler();
        thisDyeingMachine = thisMachineHandler.GetMachineDetailsById(thisDyeingMachine.getMachineId());
        DyeingMachineDropDown.setSelectedItem(thisDyeingMachine.getMachineName());
        DyeingWeight.setText(Float.toString(thisJob.getDyeingWeight()));
        DyeingVolumeTextField.setText(Float.toString(thisJob.getDyeingVolumeH20()));
    }

    private void SetResinMachineNameDropDown() {
        thisResinMachine.setMachineId(thisJob.getResinMachineID());
        MachineHandler thisMachineHandler = new MachineHandler();
        thisResinMachine = new MachineHandler().GetMachineDetailsById(thisResinMachine.getMachineId());
        ResinMachineDropDown.setSelectedItem(thisResinMachine.getMachineName());
        ResinWeight.setText(Float.toString(thisJob.getResinWeight()));
        ResinVolumeTextField.setText(Float.toString(thisJob.getResinVolumeH20()));
    }

    private void SetCustomerNameDropDown() {
        thisCustomer.setCustomerId(thisJob.getCustomerID());
        CustomerHandler thisCustomerHandler = new CustomerHandler();
        thisCustomer.setCustomerName(thisCustomerHandler.GetCustomerNameFromCustomerID(thisCustomer.getCustomerId()));
        CustomerDropDownList.setSelectedItem(thisCustomer.getCustomerName());
    }

    private void SetColorNameDropDown() {
        thisColor.setColorId(thisJob.getColorID());
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
        CustomerDropDownList = new javax.swing.JComboBox<String>();
        DesignDropDownList = new javax.swing.JComboBox<String>();
        ColorDropDownList = new javax.swing.JComboBox<String>();
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
        DyeingMachinePanel = new javax.swing.JPanel();
        DyeingMachineDropDown = new javax.swing.JComboBox<String>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        DyeingWeight = new javax.swing.JTextField();
        DyeingVolumeTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        DyeingProgramText = new javax.swing.JTextField();
        EditDyeingProgram = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        DyeingLiquidRatioText = new javax.swing.JTextField();
        DyeingMachineLiquidRatioDropDown = new javax.swing.JComboBox<String>();
        ResinMachinePanel = new javax.swing.JPanel();
        ResinMachineDropDown = new javax.swing.JComboBox<String>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ResinWeight = new javax.swing.JTextField();
        ResinVolumeTextField = new javax.swing.JTextField();
        ResinProgramText = new javax.swing.JTextField();
        ResinLabel = new javax.swing.JLabel();
        EditResinProgram = new javax.swing.JButton();
        ResinFabricTypeDropDown = new javax.swing.JComboBox<String>();
        FabricTypeLabel = new javax.swing.JLabel();
        ResinMachineLiquidRatioDropDown = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BgPanel.setPreferredSize(new java.awt.Dimension(730, 620));

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
        CustomerDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Customer" }));
        CustomerDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(CustomerDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 195, 30));

        DesignDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DesignDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Design" }));
        DesignDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesignDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(DesignDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 195, 30));

        ColorDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ColorDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Color" }));
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

        DyeingMachinePanel.setBackground(new java.awt.Color(102, 102, 102));
        DyeingMachinePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dyeing", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        DyeingMachinePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DyeingMachineDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingMachineDropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Machine" }));
        DyeingMachineDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DyeingMachineDropDownActionPerformed(evt);
            }
        });
        DyeingMachinePanel.add(DyeingMachineDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 275, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Weight :");
        DyeingMachinePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 67, 80, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Volume of Water :");
        DyeingMachinePanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 67, 145, 30));

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
        DyeingMachinePanel.add(DyeingWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 67, 200, 30));

        DyeingVolumeTextField.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingVolumeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DyeingVolumeTextFieldKeyReleased(evt);
            }
        });
        DyeingMachinePanel.add(DyeingVolumeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 67, 170, 30));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Dyeing Program :");
        DyeingMachinePanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 103, 140, 30));

        DyeingProgramText.setEditable(false);
        DyeingProgramText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingProgramText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        DyeingProgramText.setEnabled(false);
        DyeingMachinePanel.add(DyeingProgramText, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 103, 430, 30));

        EditDyeingProgram.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        EditDyeingProgram.setText("...");
        EditDyeingProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditDyeingProgramActionPerformed(evt);
            }
        });
        DyeingMachinePanel.add(EditDyeingProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 103, 40, 30));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Liquid Ratio :");
        DyeingMachinePanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 30, 145, 30));

        DyeingLiquidRatioText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingLiquidRatioText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DyeingLiquidRatioTextKeyReleased(evt);
            }
        });
        DyeingMachinePanel.add(DyeingLiquidRatioText, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 30, 170, 30));

        DyeingMachineLiquidRatioDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DyeingMachineLiquidRatioDropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Liquid Ratio" }));
        DyeingMachineLiquidRatioDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DyeingMachineLiquidRatioDropDownActionPerformed(evt);
            }
        });
        DyeingMachinePanel.add(DyeingMachineLiquidRatioDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 30, 20, 30));

        ResinMachinePanel.setBackground(new java.awt.Color(102, 102, 102));
        ResinMachinePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resin", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        ResinMachinePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ResinMachineDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinMachineDropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Machine" }));
        ResinMachineDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResinMachineDropDownActionPerformed(evt);
            }
        });
        ResinMachinePanel.add(ResinMachineDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 275, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Weight :");
        ResinMachinePanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 67, 80, 30));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Volume of Water :");
        ResinMachinePanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 67, 145, 30));

        ResinWeight.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinWeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ResinWeightFocusLost(evt);
            }
        });
        ResinWeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ResinWeightKeyReleased(evt);
            }
        });
        ResinMachinePanel.add(ResinWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 67, 200, 30));

        ResinVolumeTextField.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinVolumeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ResinVolumeTextFieldKeyReleased(evt);
            }
        });
        ResinMachinePanel.add(ResinVolumeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 67, 170, 30));

        ResinProgramText.setEditable(false);
        ResinProgramText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinProgramText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ResinProgramText.setEnabled(false);
        ResinMachinePanel.add(ResinProgramText, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 103, 440, 30));

        ResinLabel.setBackground(new java.awt.Color(255, 255, 255));
        ResinLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinLabel.setForeground(new java.awt.Color(255, 255, 255));
        ResinLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ResinLabel.setText("Resin Program :");
        ResinLabel.setToolTipText("");
        ResinMachinePanel.add(ResinLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 103, 140, 30));

        EditResinProgram.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        EditResinProgram.setText("...");
        EditResinProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditResinProgramActionPerformed(evt);
            }
        });
        ResinMachinePanel.add(EditResinProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 103, 40, 30));

        ResinFabricTypeDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinFabricTypeDropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fabric Type", "TC 0.3 + 30 liters", "CVC 0.4 + 30 liters", "CC 0.5 + 30 liters", "Polyester and Spun 0.65 + 30 liters", "TC 0.5" }));
        ResinFabricTypeDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResinFabricTypeDropDownActionPerformed(evt);
            }
        });
        ResinMachinePanel.add(ResinFabricTypeDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 30, 170, 30));

        FabricTypeLabel.setBackground(new java.awt.Color(255, 255, 255));
        FabricTypeLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FabricTypeLabel.setForeground(new java.awt.Color(255, 255, 255));
        FabricTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        FabricTypeLabel.setText("Fabric Type :");
        ResinMachinePanel.add(FabricTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 30, 145, 30));

        ResinMachineLiquidRatioDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinMachineLiquidRatioDropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Liquid Ratio" }));
        ResinMachineLiquidRatioDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResinMachineLiquidRatioDropDownActionPerformed(evt);
            }
        });
        ResinMachinePanel.add(ResinMachineLiquidRatioDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 20, 30));

        javax.swing.GroupLayout BgPanelLayout = new javax.swing.GroupLayout(BgPanel);
        BgPanel.setLayout(BgPanelLayout);
        BgPanelLayout.setHorizontalGroup(
            BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BgPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChemicalHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(BgPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(DyeingMachinePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BgPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(ResinMachinePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(BgPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(SavePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SaveExit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(CancelBut, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        BgPanelLayout.setVerticalGroup(
            BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BgPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BgPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ChemicalHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DyeingMachinePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ResinMachinePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SavePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelBut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
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
                .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        else if(thisResinMachine.getMachineId() > 0)
        {
            isSuccessful = CheckIfResinMachineHasInputs();
        }
        
        return isSuccessful;
    }

    public boolean CheckTextBoxIsParseValid(JTextField thisTextField) {
        return thisTextField.getText().length() >= 1;
    }

    private boolean AddTextToJobOrderObject() {
        boolean isSuccessful = false;
        //If all inputs are good
        if (CheckAllFormInformationFromTextBox()) {
            thisJob.setCustomerID(thisCustomer.getCustomerId());
            thisJob.setColorID(thisColor.getColorId());
            thisJob.setDesignID(thisDesign.getDesignId());
            thisJob.setDrNumber(JobOrder.getText());
            thisJob.setJobDate(get_date_from_spinner(dateSpinner));
            if (this.BatchNo.getText().length() < 1) {
                thisJob.setBatchNo(0);
            } else {
                thisJob.setBatchNo(Integer.parseInt(BatchNo.getText()));
            }
            thisJob.setDyeingMachineID(thisDyeingMachine.getMachineId());
            thisJob.setDyeingVolumeH20(Float.parseFloat(this.DyeingVolumeTextField.getText()));
            thisJob.setDyeingWeight(Float.parseFloat(this.DyeingWeight.getText()));
            if (thisResinMachine.getMachineId() > 0) {
                thisJob.setResinMachineID(thisResinMachine.getMachineId());
                thisJob.setResinVolumeH20(Float.parseFloat(this.ResinVolumeTextField.getText()));
                thisJob.setResinWeight(Float.parseFloat(this.ResinWeight.getText()));
            }
            else
            {
                //thisJob.setResinMachineID(thisResinMachine.getMachineId());
                //thisJob.setResinVolumeH20(Float.parseFloat(this.ResinVolumeTextField.getText()));
                //thisJob.setResinWeight(Float.parseFloat(this.ResinWeight.getText()));
            }
            thisJob.setRollLoad(RollLoad.getText());
            thisJob.setReference(Reference.getText());
            thisJob.setProgramNumber(this.ProgramNumber.getText());
            thisJob.setLocation(this.Location.getText());

            isSuccessful = true;
        }
        return isSuccessful;
    }
    private void SavePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavePrintActionPerformed
        // TODO add your handling code here:
        JobHandler thisJobHandler = new JobHandler();
        if (AddTextToJobOrderObject()) {
            if (ThisJobHasBeenAdded != true) {
                //if (WindowType == 3) {
                if (WindowType == 6) {
                    thisJobHandler.UpdateJobOrder(thisJob);
                }
                //else if ((WindowType == 5 || WindowType == 4 || WindowType == 2 || WindowType == 1)) {
                else {
                    thisJobHandler.AddNewJobOrder(thisJob);
                }
                ThisJobHasBeenAdded = true;
                
            }

            try {
                PrintHandlerFinal handler = new PrintHandlerFinal();
                handler.createPDF(thisDyeingMachine, thisDesign, thisCustomer, thisColor, thisJob, thisDyeingProgram, DyeingVolumeTextField.getText());
            } catch (IOException | DocumentException e) {
            }
        }

    }//GEN-LAST:event_SavePrintActionPerformed

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        if(this.ThisJobHasBeenAdded)
        {
            this.dispose();
        }
        else {
            if (this.WindowType == 4 || this.WindowType == 6 || this.WindowType == 5) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to cancel using this Job Order?", "Exit?", JOptionPane.YES_NO_OPTION)) {
                    new ViewCustomerOrder().setVisible(true);
                    this.dispose();
                }
            } else {
                if (thisJob.getResinProgramID() > 0) {
                    new AddResinForm(thisJob).setVisible(true);
                } else {
                    new DyeingForm(thisJob).setVisible(true);

                }
                this.dispose();
            }
        }
    }//GEN-LAST:event_CancelButActionPerformed

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

    private void DesignDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesignDropDownListActionPerformed
        Design designDetails = new Design();
        DesignHandler handler = new DesignHandler();
        //int designId = -1;

        //if(!designName.equals(""))
        if (!DesignDropDownList.getSelectedItem().toString().equals("Choose Design")
                && !(DesignDropDownList.getSelectedItem().toString().length() > 1)) {
            String designName = DesignDropDownList.getSelectedItem().toString();
            thisDesign.setDesignName(designName);
            //designId = handler.GetDesignIDFromName(designName);
            thisDesign.setDesignId(handler.GetDesignIDFromName(designName));
        }
    }//GEN-LAST:event_DesignDropDownListActionPerformed

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

    private void DyeingWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DyeingWeightFocusLost
        //ComputeForDyeingVolume();
        try{
                //Float RoundTo = (float) Math.round(Float.parseFloat(Weight.getText()) / 100) * 100;
            DyeingWeight.setText(new LiquidRatioHandler().RoundToHundreds(Float.parseFloat(DyeingWeight.getText())).toString());
        }
        catch (NumberFormatException formatException) {
            JOptionPane.showMessageDialog(null, "Please change the value of the weight to a valid number.");
        }
    }//GEN-LAST:event_DyeingWeightFocusLost

    private void DyeingWeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DyeingWeightKeyReleased
        String weight = DyeingWeight.getText();
        weight = weight.replaceAll("[^\\d.]", "");
        DyeingWeight.setText(weight);
        if (this.CheckTextBoxIsParseValid(DyeingWeight)) {
            thisJob.setDyeingWeight(Float.parseFloat(weight));
            if(new LiquidRatioHandler().CheckIfPatternMatchesLiquidRatio(this.DyeingLiquidRatioText.getText().toString()))
                ComputerDyeingLiquidRatio();
        }
        
    }//GEN-LAST:event_DyeingWeightKeyReleased

    private void DyeingMachineLiquidRatioDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DyeingMachineLiquidRatioDropDownActionPerformed
        ComputeForDyeingVolume();
    }//GEN-LAST:event_DyeingMachineLiquidRatioDropDownActionPerformed

    private void SaveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveExitActionPerformed
        // TODO add your handling code here:
        JobHandler thisJobHandler = new JobHandler();
        if (ThisJobHasBeenAdded == true) {
            JOptionPane.showMessageDialog(null, "Job order has already been added.");
            this.dispose();
        } else {
            if (this.WindowType != 6) {
                if (AddTextToJobOrderObject()) {
                    if (thisJobHandler.AddNewJobOrder(thisJob) > 0) {
                        JOptionPane.showMessageDialog(null, "Job order has been added.");
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "There is an error in adding the Job Order.");
                    }
                }
            } else if (WindowType == 6) {
                if (AddTextToJobOrderObject()) {
                    if (thisJobHandler.UpdateJobOrder(thisJob)) {
                        JOptionPane.showMessageDialog(null, "Job order has been updated.");
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "There is an error in updating the Job Order.");
                    }
                }
            }
        }
    }//GEN-LAST:event_SaveExitActionPerformed

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
    
    private void EditDyeingProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditDyeingProgramActionPerformed
        if (CheckIfDyeingMachineHasInputs() && CheckCustomerAndJobOrderFromTextBox()) {
            ViewDyeingProgramList thisDyeingProgramListWindow;
            thisDyeingProgramListWindow = new ViewDyeingProgramList(this.thisJob, this.WindowType);
            thisDyeingProgramListWindow.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_EditDyeingProgramActionPerformed

    private boolean CheckIfResinMachineHasInputs()
    {
        boolean isSuccessful = true;
        
        if(thisResinMachine.getMachineId() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Resin Machine.");  
        }
        else if(this.ResinVolumeTextField.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the value in Volume of Water in Resin Machine."); 
        }
        else if(this.ResinWeight.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the value in the Weight in Resin Machine."); 
        }
        
        return isSuccessful;
    }
    
    private void EditResinProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditResinProgramActionPerformed
        // TODO add your handling code here:
        if (CheckIfResinMachineHasInputs() && CheckCustomerAndJobOrderFromTextBox()) {
            thisJob.setResinMachineID(thisResinMachine.getMachineId());
            ViewResinProgramList thisResinProgram;
            thisResinProgram = new ViewResinProgramList(thisJob, 3);
            thisResinProgram.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_EditResinProgramActionPerformed

    private void BatchNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BatchNoKeyReleased
        // TODO add your handling code here:
        String thisBatchNo = this.BatchNo.getText();
        thisBatchNo = thisBatchNo.replaceAll("[^\\d.]", "");
        this.BatchNo.setText(thisBatchNo);
    }//GEN-LAST:event_BatchNoKeyReleased

    private void ResinMachineDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResinMachineDropDownActionPerformed
        // TODO add your handling code here:
        Machine machineDetails = new Machine();
        MachineHandler handler = new MachineHandler();
        int machineId = -1;
        String machineName = "";

        if (!ResinMachineDropDown.getSelectedItem().toString().equals("Choose Machine")) {
            machineName = ResinMachineDropDown.getSelectedItem().toString();

            if (!machineName.equals("")) {
                machineId = handler.GetMachineIdByName(machineName);

                if (machineId > -1) {
                    machineDetails = handler.GetMachineDetailsById(machineId);

                    thisResinMachine.setMaxCapacity(machineDetails.getMaxCapacity());
                    thisResinMachine.setMaxVolume(machineDetails.getMaxVolume());
                    thisResinMachine.setMinCapacity(machineDetails.getMinCapacity());
                    thisResinMachine.setMinVolume(machineDetails.getMinVolume());
                    thisResinMachine.setNumOfLoad(machineDetails.getNumOfLoad());
                }
            }
        }

        thisResinMachine.setMachineId(machineId);
        thisResinMachine.setMachineName(machineDetails.getMachineName());

    }//GEN-LAST:event_ResinMachineDropDownActionPerformed

    private void ResinWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ResinWeightFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ResinWeightFocusLost

    private void ResinWeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResinWeightKeyReleased
        // TODO add your handling code here:
        String weight = ResinWeight.getText();
        weight = weight.replaceAll("[^\\d.]", "");
        ResinWeight.setText(weight);
        if (this.CheckTextBoxIsParseValid(ResinWeight)) {
            thisJob.setResinWeight(Float.parseFloat(weight));
            ComputeForResinVolume();
        }
    }//GEN-LAST:event_ResinWeightKeyReleased

    private void ResinMachineLiquidRatioDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResinMachineLiquidRatioDropDownActionPerformed
        // TODO add your handling code here:
        ComputeForResinVolume();
    }//GEN-LAST:event_ResinMachineLiquidRatioDropDownActionPerformed

    private void DyeingVolumeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DyeingVolumeTextFieldKeyReleased
        // TODO add your handling code here:
        String volume = this.DyeingVolumeTextField.getText();
        volume = volume.replaceAll("[^\\d.]", "");
        DyeingVolumeTextField.setText(volume);
        if (this.CheckTextBoxIsParseValid(DyeingVolumeTextField)) {
            thisJob.setDyeingVolumeH20(Float.parseFloat(volume));
        }
    }//GEN-LAST:event_DyeingVolumeTextFieldKeyReleased

    private void ResinVolumeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResinVolumeTextFieldKeyReleased
        // TODO add your handling code here:
        String volume = this.ResinVolumeTextField.getText();
        volume = volume.replaceAll("[^\\d.]", "");
        ResinVolumeTextField.setText(volume);
        if (this.CheckTextBoxIsParseValid(ResinVolumeTextField)) {
            thisJob.setResinVolumeH20(Float.parseFloat(volume));
        }
    }//GEN-LAST:event_ResinVolumeTextFieldKeyReleased

    private void ComputerDyeingLiquidRatio()
    {
        String weight = DyeingWeight.getText();
        
        //String liquidRatio = LiquidRatioTextField.toString();
            String[] RatioSplit = DyeingLiquidRatioText.getText().split(":", 2);
            int WeightMultiplier = Integer.parseInt(RatioSplit[1]) / Integer.parseInt(RatioSplit[0]);

            int volume = (((int) (Float.parseFloat(weight) * WeightMultiplier))); // /10 * 10;
            DyeingVolumeTextField.setText(Double.toString(volume));
    }
    
    private void DyeingLiquidRatioTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DyeingLiquidRatioTextKeyReleased
        // TODO add your handling code here:
       String weight = DyeingWeight.getText();
        if(new LiquidRatioHandler().CheckIfPatternMatchesLiquidRatio(this.DyeingLiquidRatioText.getText().toString()) &&  !weight.isEmpty())
        {
            ComputerDyeingLiquidRatio();
        }
        
    }//GEN-LAST:event_DyeingLiquidRatioTextKeyReleased

    private void ResinFabricTypeDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResinFabricTypeDropDownActionPerformed
        // TODO add your handling code here:
        if(!ResinWeight.getText().isEmpty() && this.ResinFabricTypeDropDown.getSelectedIndex() != 0)
        {
            ResinVolumeTextField.setText(
                                    Float.toString(
                                            new MachineHandler().ComputeVolumeOfWaterFromWeight(
                                                    Float.parseFloat(ResinWeight.getText()), ResinFabricTypeDropDown.getSelectedItem().toString())));
        }
    }//GEN-LAST:event_ResinFabricTypeDropDownActionPerformed

    /*private void ComputeVolumeOfWaterFromWeight()
    {
        float ComputedVolume = 0;
        int thisResinWeight = Integer.parseInt(ResinWeight.getText());
        switch(ResinFabricTypeDropDown.getSelectedIndex())
        {
            case 1:
                ComputedVolume = (float) ((thisResinWeight * 0.3) + 30);
                break;
            case 2:
                ComputedVolume = (float) (thisResinWeight * 0.4) + 30;
                break;
            case 3:
                ComputedVolume = (float) (thisResinWeight * 0.5) + 30;
                break;
            case 4:
                ComputedVolume = (float) (thisResinWeight * 0.65) + 30;
                break;
            case 5:
                ComputedVolume = (float) (thisResinWeight * 0.5);
                break;
            
        }
    }
    */
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
            java.util.logging.Logger.getLogger(ReviewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReviewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReviewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReviewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReviewForm().setVisible(true);
            }
        });
    }

    public String get_date_from_spinner(JSpinner this_spinner) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        return formater.format(this_spinner.getValue());
    }

    private void ComputeForResinVolume() {
        String weight = ResinWeight.getText();
        //String liquidRatio = this.ResinMachineLiquidRatioDropDown.getSelectedItem().toString();
        if (!weight.isEmpty() && ResinFabricTypeDropDown.getSelectedIndex()!= 0 )//liquidRatio.equals("Liquid Ratio"))
        {
            //String selected = DyeingMachineLiquidRatioDropDown.getSelectedItem().toString();
            ResinVolumeTextField.setText(Float.toString(new MachineHandler().ComputeVolumeOfWaterFromWeight(Float.parseFloat(weight), ResinFabricTypeDropDown.getSelectedItem().toString())));
        }
    }

    private void ComputeForDyeingVolume() {
        String weight = DyeingWeight.getText();
        String liquidRatio = DyeingMachineLiquidRatioDropDown.getSelectedItem().toString();
        if (!weight.equals("") && !liquidRatio.equals("Liquid Ratio")) {
            //String selected = DyeingMachineLiquidRatioDropDown.getSelectedItem().toString();
            DyeingVolumeTextField.setText(computeForVolume((int)Float.parseFloat(weight), liquidRatio));
        }
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

    private void populateResinMachineDropDown() {
        //if Resin requires custom Machine
        ArrayList<Machine> MachineList;
        if(new PreferenceHandler().getResinMachineInputPreference())
        {
            MachineList = new MachineHandler().GetAllManualResinMachines();
        }
        else
            MachineList = new MachineHandler().GetAllAutomaticResinMachines();
        
        //ArrayList<Machine> MachineList = new MachineHandler().GetAllResinMachines();

        if (MachineList != null) {
            for (int x = 0; x < MachineList.size(); x++) {
                ResinMachineDropDown.addItem(MachineList.get(x).getMachineName());
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
    private javax.swing.JLabel ChemicalHeader;
    private javax.swing.JComboBox<String> ColorDropDownList;
    private javax.swing.JComboBox<String> CustomerDropDownList;
    private javax.swing.JComboBox<String> DesignDropDownList;
    private javax.swing.JTextField DyeingLiquidRatioText;
    private javax.swing.JComboBox<String> DyeingMachineDropDown;
    private javax.swing.JComboBox<String> DyeingMachineLiquidRatioDropDown;
    private javax.swing.JPanel DyeingMachinePanel;
    private javax.swing.JTextField DyeingProgramText;
    private javax.swing.JTextField DyeingVolumeTextField;
    private javax.swing.JTextField DyeingWeight;
    private javax.swing.JButton EditDyeingProgram;
    private javax.swing.JButton EditResinProgram;
    private javax.swing.JLabel FabricTypeLabel;
    private javax.swing.JTextField JobOrder;
    private javax.swing.JTextField Location;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JTextField ProgramNumber;
    private javax.swing.JLabel ProgramNumberLabel;
    private javax.swing.JTextField Reference;
    private javax.swing.JLabel ReferenceLabel;
    private javax.swing.JLabel ReferenceLabel1;
    private javax.swing.JComboBox<String> ResinFabricTypeDropDown;
    private javax.swing.JLabel ResinLabel;
    private javax.swing.JComboBox<String> ResinMachineDropDown;
    private javax.swing.JComboBox<String> ResinMachineLiquidRatioDropDown;
    private javax.swing.JPanel ResinMachinePanel;
    private javax.swing.JTextField ResinProgramText;
    private javax.swing.JTextField ResinVolumeTextField;
    private javax.swing.JTextField ResinWeight;
    private javax.swing.JTextField RollLoad;
    private javax.swing.JButton SaveExit;
    private javax.swing.JButton SavePrint;
    private javax.swing.JSpinner dateSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
