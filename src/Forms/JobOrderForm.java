/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.Machine;
import Handlers.MachineHandler;
import DataEntities.ChemicalColor;
import Handlers.ColorHandler;
import Handlers.CustomerHandler;
import DataEntities.Customer;
import DataEntities.Design;
import DataEntities.JobOrder;
import DataEntities.ProcessOrder;
import Database.ColorTextControlSlipRepository;
import Handlers.DesignHandler;
import Handlers.JobHandler;
import Handlers.ProcessOrderHandler;
import Handlers.PrintHandler;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;


/**
 *
 * @author imbuenyson
 */
public class JobOrderForm extends javax.swing.JFrame {
    Machine thisMachine = new Machine();
    Design thisDesign = new Design();
    Customer thisCustomer = new Customer();
    ChemicalColor thisColor = new ChemicalColor();
    JobOrder thisJob = new JobOrder();
    //ProcessOrder thisProcessOrder = new ProcessOrder();
    private final static int POINTS_PER_INCH = 72;
    private int WindowType = 0;
    private Color ErrorColor = new Color(232,228,42);
    /**
     * Creates new form JobOrderForm
     */
    public JobOrderForm() {
        initComponents();
        //initTextFields();
        populateCustomerDropDown();
        populateDesignDropDown();
        populateColorDropDown();
        populateMachineDropDown();
        populateLiquoRatioDropDown();
        SetToCenter();
    }
    
    public void SetToCenter()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
    
    public JobOrderForm(JobOrder thisJobOrder) {
        this();
        thisJob = thisJobOrder;
        //thisProcessOrder = ProcessOrder;
        SetJobOrderDetails();
        //SetProcessOrderDetails();
        SetDropDownDetails();
        WindowType = 1;
    }
    
    //private void SetProcessOrderDetails(){}
    
    private void SetJobOrderDetails()
    {
        //thisJob.setID(thisProcessOrder.getJobOrderID());
        //JobHandler JobOrderHandler = new JobHandler();
        //thisJob = JobOrderHandler.GetJobOrderDetailsFromJobId(thisJob.getID());
        JobOrder.setText(thisJob.getDrNumber());
        BatchNo.setText(thisJob.getBatchNo());
        //Set Date to 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateSpinner.setValue(sdf.parse(thisJob.getJobDate()));
            } catch (ParseException ex) {
                Logger.getLogger(JobOrderForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       Weight.setText(Float.toString(thisJob.getWeight()));
        VolumeTextField.setText(Float.toString(thisJob.getVolumeH20()));
        RollLoad.setText(thisJob.getRollLoad());
    }
    private void SetDropDownDetails()
    {
        SetDesignNameDropDown();
        SetMachineNameDropDown();
        SetCustomerNameDropDown();
        SetColorNameDropDown();
    }
    
    private void SetDesignNameDropDown()
    {
        thisDesign.setDesignId(thisJob.getDesignID());
        DesignHandler thisDesignHandler = new DesignHandler();
        thisDesign.setDesignName(thisDesignHandler.GetDesignNameFromID(thisDesign.getDesignId()));
        DesignDropDownList.setSelectedItem(thisDesign.getDesignName());
    }
    
    private void SetMachineNameDropDown()
    {
        thisMachine.setMachineId(thisJob.getMachineID());
        MachineHandler thisMachineHandler = new MachineHandler();
        thisMachine = thisMachineHandler.GetMachineDetailsById(thisMachine.getMachineId());
        MachineDropDownList.setSelectedItem(thisMachine.getMachineName());
    }
    
    private void SetCustomerNameDropDown()
    {
        thisCustomer.setCustomerId(thisJob.getCustomerID());
        CustomerHandler thisCustomerHandler = new CustomerHandler();
        thisCustomer.setCustomerName(thisCustomerHandler.GetCustomerNameFromCustomerID(thisCustomer.getCustomerId()));
        CustomerDropDownList.setSelectedItem(thisCustomer.getCustomerName());
    }
    
    private void SetColorNameDropDown()
    {
        thisColor.setColorId(thisJob.getColorID());
        ColorHandler thisColorHandler = new ColorHandler();
        thisColor.setColorName(thisColorHandler.GetColorNameFromColorID(thisColor.getColorId()));
        ColorDropDownList.setSelectedItem(thisColor.getColorName());
    }
    
    //--- Private instances declarations
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanel2 = new javax.swing.JPanel();
        MachineDropDownList = new javax.swing.JComboBox<String>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Weight = new javax.swing.JTextField();
        VolumeTextField = new javax.swing.JTextField();
        LiquidRatioDropDown = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        RollLoad = new javax.swing.JTextField();
        Cancel = new javax.swing.JButton();
        NextButton = new javax.swing.JButton();
        ChemicalHeader = new javax.swing.JLabel();
        dateSpinner = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        BatchNo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Color Text Control Slip");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MainPanel.setBackground(new java.awt.Color(102, 102, 102));
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CustomerDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        CustomerDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Customer" }));
        CustomerDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(CustomerDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 190, 30));

        DesignDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        DesignDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Design" }));
        DesignDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesignDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(DesignDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 190, 30));

        ColorDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ColorDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Color" }));
        ColorDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(ColorDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 190, 190, 30));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Color :");
        MainPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 190, 100, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Customer :");
        MainPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 140, 110, 30));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Design :");
        MainPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 190, 110, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Job Order :");
        MainPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 90, 110, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Date :");
        MainPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 140, 100, 30));

        JobOrder.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        MainPanel.add(JobOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 190, 30));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Machine", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MachineDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        MachineDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Machine" }));
        MachineDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MachineDropDownListActionPerformed(evt);
            }
        });
        jPanel2.add(MachineDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 41, 628, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Weight :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 85, 80, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Volume of Water :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 130, -1, 30));

        Weight.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        Weight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                WeightFocusLost(evt);
            }
        });
        Weight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                WeightKeyReleased(evt);
            }
        });
        jPanel2.add(Weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 86, 150, 30));

        VolumeTextField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        VolumeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolumeTextFieldActionPerformed(evt);
            }
        });
        VolumeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                VolumeTextFieldKeyReleased(evt);
            }
        });
        jPanel2.add(VolumeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 131, 460, -1));

        LiquidRatioDropDown.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        LiquidRatioDropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Liquid Ratio" }));
        LiquidRatioDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LiquidRatioDropDownActionPerformed(evt);
            }
        });
        jPanel2.add(LiquidRatioDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 86, 390, 30));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Roll Load :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 175, -1, 30));

        RollLoad.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(RollLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 176, 515, -1));

        MainPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 660, 220));

        Cancel.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        MainPanel.add(Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 465, 205, 40));

        NextButton.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        NextButton.setText("Next");
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });
        MainPanel.add(NextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 465, 205, 40));

        ChemicalHeader.setBackground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        ChemicalHeader.setForeground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setText("Dyeing Control Slip : Page 1/6");
        MainPanel.add(ChemicalHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 22, -1, -1));

        dateSpinner.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        dateSpinner.setModel(new javax.swing.SpinnerDateModel());
        MainPanel.add(dateSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 140, 190, 30));
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy"));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Batch No :");
        MainPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 90, 100, 30));

        BatchNo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        BatchNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BatchNoKeyTyped(evt);
            }
        });
        MainPanel.add(BatchNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 90, 190, 30));

        getContentPane().add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

      
    private void CustomerDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerDropDownListActionPerformed
        Customer customerDetails = new Customer();
        CustomerHandler handler = new CustomerHandler();
        int customerId = -1;
        String customerName = "";
                
        if(!CustomerDropDownList.getSelectedItem().toString().equals("Choose Customer"))
        {
            customerName = CustomerDropDownList.getSelectedItem().toString();
            thisCustomer.setCustomerName(customerName);
        }        
        
        if(!customerName.equals(""))
        {
            customerId = handler.GetCustomerIDFromCustomerName(customerName);
            thisCustomer.setCustomerId(customerId);
        }      
    }//GEN-LAST:event_CustomerDropDownListActionPerformed

    private void ColorDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorDropDownListActionPerformed
        ChemicalColor colorDetails = new ChemicalColor();
        ColorHandler handler = new ColorHandler();
        int colorId = -1;
        String colorName = "";
                
        if(!ColorDropDownList.getSelectedItem().toString().equals("Choose Color"))
        {
            colorName = ColorDropDownList.getSelectedItem().toString();
            thisColor.setColorName(colorName);
        }        
        
        if(!colorName.equals(""))
        {
            colorId = handler.GetColorIDFromColorName(colorName);
            thisColor.setColorId(colorId);
        }
    }//GEN-LAST:event_ColorDropDownListActionPerformed

    private void LiquidRatioDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LiquidRatioDropDownActionPerformed
        ComputeForVolume();     
    }//GEN-LAST:event_LiquidRatioDropDownActionPerformed

    private void WeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_WeightFocusLost
        ComputeForVolume();
    }//GEN-LAST:event_WeightFocusLost

    private void MachineDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MachineDropDownListActionPerformed
        
        Machine machineDetails = new Machine();
        MachineHandler handler = new MachineHandler();
        int machineId = -1;
        String machineName = "";
                
        if(!MachineDropDownList.getSelectedItem().toString().equals("Choose Machine"))
        {
            machineName = MachineDropDownList.getSelectedItem().toString();
            machineId = handler.GetMachineIdByName(machineName);
            
            //if(!machineName.equals(""))
            //{}
         
            if(machineId > -1)
            {
                machineDetails = handler.GetMachineDetailsById(machineId);
            }
            
            thisMachine.setMachineId(machineId);
            thisMachine.setMachineName(machineDetails.getMachineName());
            thisMachine.setMaxCapacity(machineDetails.getMaxCapacity());
            thisMachine.setMaxVolume(machineDetails.getMaxVolume());
            thisMachine.setMinCapacity(machineDetails.getMinCapacity());
            thisMachine.setMinVolume(machineDetails.getMinVolume());
            
            VolumeTextField.setText(Float.toString(thisMachine.getMaxVolume()));
            Weight.setText(Float.toString(thisMachine.getMaxCapacity()));
            
        }        
        
        
    }//GEN-LAST:event_MachineDropDownListActionPerformed

    private void DesignDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesignDropDownListActionPerformed
        Design designDetails = new Design();
        DesignHandler handler = new DesignHandler();
        int designId = -1;
                
        //if(!designName.equals(""))
        if(!DesignDropDownList.getSelectedItem().toString().equals("Choose Design") 
                && (DesignDropDownList.getSelectedItem().toString().length() > 0))
        {
            String designName = DesignDropDownList.getSelectedItem().toString();
            thisDesign.setDesignName(designName);
            designId = handler.GetDesignIDFromName(designName);
            thisDesign.setDesignId(designId);
        }        
        
    }//GEN-LAST:event_DesignDropDownListActionPerformed

    private void WeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WeightKeyReleased
        String weight = Weight.getText();
        weight = weight.replaceAll("[^\\d.]", "");
        Weight.setText(weight);
    }//GEN-LAST:event_WeightKeyReleased

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        // TODO add your handling code here:
        if(SetJobOrderInformationFromTextBox())
        {
            ViewDyeingProgram chooseDyeingProgram = new ViewDyeingProgram(thisJob);
            chooseDyeingProgram.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_NextButtonActionPerformed

    public String get_date_from_spinner(JSpinner this_spinner)
    {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        return formater.format(this_spinner.getValue());
    }
    
    private void ComputeForVolume()
    {
        String weight = Weight.getText();    
       String liquidRatio = LiquidRatioDropDown.getSelectedItem().toString();
        if(!weight.equals("") && !liquidRatio.equals("Liquid Ratio"))
        {
            computeForVolume();
        } 
    }
    /*
    private boolean SetProcessOrderInformation()
    {
        boolean isSuccessful = false;
            thisProcessOrder.setJobOrderID(thisJob.getID());
            if(this.VolumeTextField.getText().length() > 0)
            {
                thisProcessOrder.setVolumeH20(Float.parseFloat(this.VolumeTextField.getText()));
                if(this.Weight.getText().length() > 0)
                {
                    thisProcessOrder.setWeight(Float.parseFloat(this.Weight.getText()));
                    thisProcessOrder.setRollLoad(RollLoad.getText());
                    ProcessOrderHandler ProcessHandler = new ProcessOrderHandler();
                    thisProcessOrder.setID(ProcessHandler.AddNewProcessOrder(thisProcessOrder));
                    if(thisProcessOrder.getID() >0)
                        isSuccessful = true;
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Please check the value in Volume of Water.");
            
        return isSuccessful;
    }*/
    private boolean SetJobOrderInformationFromTextBox()
    {
        boolean isSuccessful = true;
        if(thisCustomer.getCustomerId() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Customer Name.");
        }
        else if(thisColor.getColorId() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Color name.");
        }
        else if(thisDesign.getDesignId() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Design name."); 
        }
        else if(thisMachine.getMachineId() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Machine details.");  
        }
        else if(JobOrder.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Job Order number.");  
        }
        else if(this.BatchNo.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the Batch number.");
        }
        else if(this.VolumeTextField.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the value in Volume of Water."); 
        }
        else if(this.Weight.getText().length() < 1)
        {
            isSuccessful = false;
            JOptionPane.showMessageDialog(null, "Please check the value in the Weight."); 
        }
        
                
        //If all inputs are good
        if(isSuccessful)
        {
            thisJob.setCustomerID(thisCustomer.getCustomerId());
            thisJob.setColorID(thisColor.getColorId());
            thisJob.setDesignID(thisDesign.getDesignId());
            thisJob.setMachineID(thisMachine.getMachineId());
            thisJob.setDrNumber(JobOrder.getText());
            thisJob.setJobDate(get_date_from_spinner(dateSpinner));
            thisJob.setBatchNo(BatchNo.getText());
            thisJob.setVolumeH20(Float.parseFloat(this.VolumeTextField.getText()));
            thisJob.setWeight(Float.parseFloat(this.Weight.getText()));
            thisJob.setRollLoad(RollLoad.getText());
            JobHandler thisJobHandler = new JobHandler();
            if(WindowType == 1)
            {
                //thisJob.setID(this.thisProcessOrder.getJobOrderID());
                thisJobHandler.UpdateJobOrder(thisJob);
            }
            else if(thisJobHandler.CheckIfThisJobOrderHasBeenAdded(thisJob))
            {
                int JobOrderID = thisJobHandler.AddNewJobOrder(thisJob);
                if(JobOrderID == - 1)
                    JOptionPane.showMessageDialog(null, "Job Order was not added.");
                else
                {
                    thisJob.setID(JobOrderID);
                    //JOptionPane.showMessageDialog(null, "Job Order was successfully added.");
                    isSuccessful = true;
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Job Order #"+ thisJob.getDrNumber()+" has already been added");
                JobOrder.setBackground(ErrorColor);
            }
        }                  
        return isSuccessful;
    }
    
    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this Control Slip?","Exit?", JOptionPane.YES_NO_OPTION))
        {
            if(WindowType == 1)
                new JobHandler().DeleteJobOrder(thisJob.getID());
            
            this.dispose();
        }
    }//GEN-LAST:event_CancelActionPerformed

    private void VolumeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolumeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VolumeTextFieldActionPerformed

    private void VolumeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_VolumeTextFieldKeyReleased
        // TODO add your handling code here:
        String Volume = this.VolumeTextField.getText();
        Volume = Volume.replaceAll("[^\\d.]", "");
        this.VolumeTextField.setText(Volume);
    }//GEN-LAST:event_VolumeTextFieldKeyReleased

    private void BatchNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BatchNoKeyTyped
        // TODO add your handling code here:
        String thisBatchNo = this.BatchNo.getText();
        thisBatchNo = thisBatchNo.replaceAll("[^\\d.]", "");
        this.BatchNo.setText(thisBatchNo);
    }//GEN-LAST:event_BatchNoKeyTyped
    
    private void computeForVolume()
    {
        int weightMultiplier = 0;
        
        String selected = LiquidRatioDropDown.getSelectedItem().toString();
        float weight = Float.parseFloat(Weight.getText());
        
        if(selected.equals("1:6"))
        {
            weightMultiplier = 6;
        }
        else if(selected.equals("1:8"))
        {
            weightMultiplier = 8;
        }
        else if(selected.equals("1:9"))
        {
            weightMultiplier = 9;
        }
        else if(selected.equals("1:10"))
        {
            weightMultiplier = 10;
        }
        else if(selected.equals("1:12"))
        {
            weightMultiplier = 12;
        }
        
        int volume = (((int) (weight * weightMultiplier)) + 9) / 10 * 10;
        
        VolumeTextField.setText(Double.toString(volume));
    }
    
    private void populateLiquoRatioDropDown()
    {
        ArrayList<String> LiquidRatioList = new ArrayList<String>();
        
        LiquidRatioList.add("1:6");
        LiquidRatioList.add("1:8");
        LiquidRatioList.add("1:9");
        LiquidRatioList.add("1:10");
        LiquidRatioList.add("1:12");
        
        for(int x=0; x<LiquidRatioList.size(); x++)
        {
            LiquidRatioDropDown.addItem(LiquidRatioList.get(x));
        }

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
    private void populateMachineDropDown(){
        //PopulateList(new MachineHandler().GetAllMachines() , MachineDropDownList);
        
        ArrayList<Machine> MachineList = new MachineHandler().GetAllMachines();
        
        if(MachineList != null){
            for(int x=0; x<MachineList.size(); x++)
            {
                MachineDropDownList.addItem(MachineList.get(x).getMachineName());
            }
        }  
        
    }
    
    private void populateColorDropDown(){
        PopulateList(new ColorHandler().GetAllColor() , ColorDropDownList);
        
        /*
        ArrayList<String> ColorList = new ColorHandler().GetAllColor();
        
        if(ColorList != null){
            for(int x=0; x<ColorList.size(); x++)
            {
                ColorDropDownList.addItem(ColorList.get(x));
            }
        }  
        */
    }
    
    private void populateDesignDropDown(){
        PopulateList(new DesignHandler().GetAllDesigns() , DesignDropDownList);
        /*
        ArrayList<String> DesignList = new DesignHandler().GetAllDesigns();
        
        if(DesignList != null){
            for(int x=0; x<DesignList.size(); x++)
            {
                DesignDropDownList.addItem(DesignList.get(x));
            }
        }   
        */
    }
    
    private void populateCustomerDropDown(){
        PopulateList(new CustomerHandler().GetAllCustomers() , CustomerDropDownList);
        
        //ArrayList<String> CustomerList = new CustomerHandler().GetAllCustomers();
        
        //if(CustomerList != null){
        //    for(int x=0; x<CustomerList.size(); x++)
        //    {
        //        CustomerDropDownList.addItem(CustomerList.get(x));
        //    }
        //}     
    }
    private void PopulateList(ArrayList<String> thisList , JComboBox thisBox)
    {
        if(thisBox != null){
            for(int x=0; x<thisList.size(); x++)
            {
                thisBox.addItem(thisList.get(x));
            }
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
            java.util.logging.Logger.getLogger(JobOrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JobOrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JobOrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JobOrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JobOrderForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BatchNo;
    private javax.swing.JButton Cancel;
    private javax.swing.JLabel ChemicalHeader;
    private javax.swing.JComboBox<String> ColorDropDownList;
    private javax.swing.JComboBox<String> CustomerDropDownList;
    private javax.swing.JComboBox<String> DesignDropDownList;
    private javax.swing.JTextField JobOrder;
    private javax.swing.JComboBox<String> LiquidRatioDropDown;
    private javax.swing.JComboBox<String> MachineDropDownList;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton NextButton;
    private javax.swing.JTextField RollLoad;
    private javax.swing.JTextField VolumeTextField;
    private javax.swing.JTextField Weight;
    private javax.swing.JSpinner dateSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    
  

} // Example3




