/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.ChemicalColor;
import DataEntities.Customer;
import DataEntities.Design;
import DataEntities.DyeingProgram;
import DataEntities.JobOrder;
import DataEntities.Machine;
import DataEntities.ProcessOrder;
import DataEntities.ResinProgram;
import Handlers.ColorHandler;
import Handlers.CustomerHandler;
import Handlers.DesignHandler;
import Handlers.DyeingProgramHandler;
import Handlers.JobHandler;
import Handlers.MachineHandler;
import Handlers.ResinProgramHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

/**
 *
 * @author Eldridge
 */
public class ReviewForm extends javax.swing.JFrame {

    Machine thisMachine = new Machine();
    Design thisDesign = new Design();
    Customer thisCustomer = new Customer();
    ChemicalColor thisColor = new ChemicalColor();
    JobOrder thisJob = new JobOrder();
    ProcessOrder thisProcessOrder = new ProcessOrder();
    /**
     * Creates new form ReviewForm
     */
    public ReviewForm() {
        initComponents();
    }
    
    public ReviewForm(ProcessOrder setThisProcessOrder)
    {
        this();
        thisProcessOrder = setThisProcessOrder;
        SetJobOrderDetails();
        SetProcessOrderDetails();
        SetDropDownDetails();
    }

    private void SetDyeingProgramName()
    {
         DyeingProgram thisDyeingProgram = 
                 new DyeingProgramHandler().GetDyeingProgramDetailsById(thisProcessOrder.getDyeingProgramID());
         DyeingProgramText.setText(thisDyeingProgram.getDyeingProgramName());
    }
    private void SetResinProgramName()
    {
        if(thisProcessOrder.getResinProgramID() > 0)
        {
            String ResinProgramName = 
                new ResinProgramHandler().GetResinProgramNameFromResinProgramID(thisProcessOrder.getResinProgramID());
            ResinProgramText.setText(ResinProgramName);
        }
        
    }
    
     private void SetJobOrderDetails()
    {
        thisJob.setID(thisProcessOrder.getID());
        JobHandler JobOrderHandler = new JobHandler();
        thisJob = JobOrderHandler.GetJobOrderDetailsFromJobId(thisJob.getID());
        JobOrder.setText(thisJob.getDrNumber());
        
        //Set Date to 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateSpinner.setValue(sdf.parse(thisJob.getJobDate()));
            } catch (ParseException ex) {
                Logger.getLogger(JobOrderForm.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void SetDropDownDetails()
    {
        SetDesignNameDropDown();
        SetMachineNameDropDown();
        SetCustomerNameDropDown();
        SetColorNameDropDown();
        SetProcessOrderDetails();
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
    
    private void SetProcessOrderDetails()
    {
        Weight.setText(Float.toString(thisProcessOrder.getWeight()));
        VolumeTextField.setText(Float.toString(thisProcessOrder.getVolumeH20()));
        RollLoad.setText(thisProcessOrder.getRollLoad());
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
        SaveBut = new javax.swing.JButton();
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
        jPanel2 = new javax.swing.JPanel();
        MachineDropDownList = new javax.swing.JComboBox<String>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Weight = new javax.swing.JTextField();
        VolumeTextField = new javax.swing.JTextField();
        LiquidRatioDropDown = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        RollLoad = new javax.swing.JTextField();
        dateSpinner = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        DyeingProgramText = new javax.swing.JTextField();
        ResinProgramText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Color Text Control Slip");

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ChemicalHeader.setBackground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        ChemicalHeader.setForeground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setText("Dyeing Control Slip : Review");
        BgPanel.add(ChemicalHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 22, 690, 50));

        SaveBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SaveBut.setText("Save & Print");
        SaveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButActionPerformed(evt);
            }
        });
        BgPanel.add(SaveBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 525, 240, 40));

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        CancelBut.setText("Back");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });
        BgPanel.add(CancelBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 525, 240, 40));

        MainPanel.setBackground(new java.awt.Color(102, 102, 102));
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CustomerDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        CustomerDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Customer" }));
        CustomerDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(CustomerDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 65, 195, 30));

        DesignDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DesignDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Design" }));
        DesignDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesignDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(DesignDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 195, 30));

        ColorDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ColorDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Color" }));
        ColorDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(ColorDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 65, 190, 30));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Color :");
        MainPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 65, 70, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Customer :");
        MainPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 65, 100, 30));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Design :");
        MainPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 110, 100, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Job Order :");
        MainPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 20, 100, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Date :");
        MainPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 70, 30));

        JobOrder.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MainPanel.add(JobOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 195, 30));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Machine", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MachineDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MachineDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Machine" }));
        MachineDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MachineDropDownListActionPerformed(evt);
            }
        });
        jPanel2.add(MachineDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 628, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Weight :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Volume of Water :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 30));

        Weight.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
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
        jPanel2.add(Weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 119, 30));

        VolumeTextField.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jPanel2.add(VolumeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 477, 30));

        LiquidRatioDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        LiquidRatioDropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Liquid Ratio" }));
        LiquidRatioDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LiquidRatioDropDownActionPerformed(evt);
            }
        });
        jPanel2.add(LiquidRatioDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 417, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Roll Load :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 30));

        RollLoad.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jPanel2.add(RollLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 517, 30));

        MainPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 152, 660, 190));

        dateSpinner.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        dateSpinner.setModel(new javax.swing.SpinnerDateModel());
        MainPanel.add(dateSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 190, 30));
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy"));

        BgPanel.add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 690, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Resin Program :");
        jLabel5.setToolTipText("");
        BgPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 140, 30));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Dyeing Program :");
        BgPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 140, 30));

        DyeingProgramText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        BgPanel.add(DyeingProgramText, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 430, 480, 30));

        ResinProgramText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        BgPanel.add(ResinProgramText, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 470, 480, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButActionPerformed
        // TODO add your handling code here:
        

    }//GEN-LAST:event_SaveButActionPerformed

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        if(thisProcessOrder.getResinProgramID() > 0)
        {
            new AddResinForm(thisProcessOrder.getResinProgramID(), thisProcessOrder).setVisible(true);
        }
        else
        {
            new DyeingForm(thisProcessOrder.getDyeingProgramID(),  thisProcessOrder).setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_CancelButActionPerformed

    private void CustomerDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerDropDownListActionPerformed
        Customer customerDetails = new Customer();
        CustomerHandler handler = new CustomerHandler();
        int customerId = -1;
        String customerName = "";

        if(!CustomerDropDownList.getSelectedItem().toString().equals("Choose Customer"))
        {
            customerName = MachineDropDownList.getSelectedItem().toString();
            thisCustomer.setCustomerName(customerName);
        }

        if(!customerName.equals(""))
        {
            customerId = handler.GetCustomerIDFromCustomerName(customerName);
            thisCustomer.setCustomerId(customerId);
        }
    }//GEN-LAST:event_CustomerDropDownListActionPerformed

    private void DesignDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesignDropDownListActionPerformed
        Design designDetails = new Design();
        DesignHandler handler = new DesignHandler();
        int designId = -1;

        //if(!designName.equals(""))
        if(!DesignDropDownList.getSelectedItem().toString().equals("Choose Design")
            && !(DesignDropDownList.getSelectedItem().toString().length() > 1))
        {
            String designName = DesignDropDownList.getSelectedItem().toString();
            thisDesign.setDesignName(designName);
            designId = handler.GetDesignIDFromName(designName);
            thisDesign.setDesignId(designId);
        }
    }//GEN-LAST:event_DesignDropDownListActionPerformed

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
            thisCustomer.setCustomerId(colorId);
        }
    }//GEN-LAST:event_ColorDropDownListActionPerformed

    private void MachineDropDownListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MachineDropDownListActionPerformed

        Machine machineDetails = new Machine();
        MachineHandler handler = new MachineHandler();
        int machineId = -1;
        String machineName = "";

        if(!MachineDropDownList.getSelectedItem().toString().equals("Choose Machine"))
        {
            machineName = MachineDropDownList.getSelectedItem().toString();
        }

        if(!machineName.equals(""))
        {
            machineId = handler.GetMachineIdByName(machineName);
        }

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
    }//GEN-LAST:event_MachineDropDownListActionPerformed

    private void WeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_WeightFocusLost
        ComputeForVolume();
    }//GEN-LAST:event_WeightFocusLost

    private void WeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WeightKeyReleased
        String weight = Weight.getText().toString();
        weight = weight.replaceAll("[^\\d.]", "");
        Weight.setText(weight);
    }//GEN-LAST:event_WeightKeyReleased

    private void LiquidRatioDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LiquidRatioDropDownActionPerformed
        ComputeForVolume();
    }//GEN-LAST:event_LiquidRatioDropDownActionPerformed

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
    
    
    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to cancel this Job Order?","Exit?", JOptionPane.YES_NO_OPTION))
        {
            
        }
    }
    
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
    
    private void computeForVolume()
    {
        int weightMultiplier = 0;
        double volume = 0;
        
        String selected = LiquidRatioDropDown.getSelectedItem().toString();
        int weight = Integer.parseInt(Weight.getText());
        
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
        
        volume = weight * weightMultiplier;
        
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
        ArrayList<Machine> MachineList = new MachineHandler().GetAllMachines();
        
        if(MachineList != null){
            for(int x=0; x<MachineList.size(); x++)
            {
                MachineDropDownList.addItem(MachineList.get(x).getMachineName());
            }
        }  
        
    }
    
    private void populateColorDropDown(){
        ArrayList<String> ColorList = new ColorHandler().GetAllColor();
        
        if(ColorList != null){
            for(int x=0; x<ColorList.size(); x++)
            {
                ColorDropDownList.addItem(ColorList.get(x));
            }
        }  
    }
    
    private void populateDesignDropDown(){
        ArrayList<String> DesignList = new DesignHandler().GetAllDesigns();
        
        if(DesignList != null){
            for(int x=0; x<DesignList.size(); x++)
            {
                DesignDropDownList.addItem(DesignList.get(x));
            }
        }     
    }
    
    private void populateCustomerDropDown(){
        ArrayList<String> CustomerList = new CustomerHandler().GetAllCustomers();
        
        if(CustomerList != null){
            for(int x=0; x<CustomerList.size(); x++)
            {
                CustomerDropDownList.addItem(CustomerList.get(x));
            }
        }     
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BgPanel;
    private javax.swing.JButton CancelBut;
    private javax.swing.JLabel ChemicalHeader;
    private javax.swing.JComboBox<String> ColorDropDownList;
    private javax.swing.JComboBox<String> CustomerDropDownList;
    private javax.swing.JComboBox<String> DesignDropDownList;
    private javax.swing.JTextField DyeingProgramText;
    private javax.swing.JTextField JobOrder;
    private javax.swing.JComboBox<String> LiquidRatioDropDown;
    private javax.swing.JComboBox<String> MachineDropDownList;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JTextField ResinProgramText;
    private javax.swing.JTextField RollLoad;
    private javax.swing.JButton SaveBut;
    private javax.swing.JTextField VolumeTextField;
    private javax.swing.JTextField Weight;
    private javax.swing.JSpinner dateSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}