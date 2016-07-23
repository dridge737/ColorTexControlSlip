/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.HelpForm;

import DataEntities.Customer;
import DataEntities.Machine;
import Handlers.MachineHandler;
import DataEntities.ChemicalColor;
import Handlers.ColorHandler;
import Handlers.CustomerHandler;
import DataEntities.Customer;
import DataEntities.Design;
import DataEntities.JobOrder;
import DataEntities.ProcessOrder;
import Forms.ViewDyeingProgram;
import Handlers.DesignHandler;
import Handlers.JobHandler;
import Handlers.ProcessOrderHandler;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.text.Format;
import java.util.Locale;
import javax.swing.JSpinner;

/**
 *
 * @author Eldridge
 */
public class JobOrderPanel extends javax.swing.JPanel {

    Machine thisMachine = new Machine();
    Design thisDesign = new Design();
    Customer thisCustomer = new Customer();
    ChemicalColor thisColor = new ChemicalColor();
    JobOrder thisJob = new JobOrder();
    ProcessOrder thisProcessOrder = new ProcessOrder();
    /**
     * Creates new form JobOrderPanel
     */
    public JobOrderPanel() {
        initComponents();
    }

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
        dateSpinner = new javax.swing.JSpinner();

        MainPanel.setBackground(new java.awt.Color(102, 102, 102));
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CustomerDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        CustomerDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Customer" }));
        CustomerDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(CustomerDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 195, 30));

        DesignDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        DesignDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Design" }));
        DesignDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesignDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(DesignDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 195, 30));

        ColorDropDownList.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ColorDropDownList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Color" }));
        ColorDropDownList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorDropDownListActionPerformed(evt);
            }
        });
        MainPanel.add(ColorDropDownList, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 190, 30));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Color :");
        MainPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 70, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Customer :");
        MainPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 125, 30));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Design :");
        MainPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 125, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Job Order :");
        MainPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 125, 30));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Date :");
        MainPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 70, 30));

        JobOrder.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        MainPanel.add(JobOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 195, 30));

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
        jPanel2.add(Weight, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 86, 119, -1));

        VolumeTextField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(VolumeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 131, 460, -1));

        LiquidRatioDropDown.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        LiquidRatioDropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Liquid Ratio" }));
        LiquidRatioDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LiquidRatioDropDownActionPerformed(evt);
            }
        });
        jPanel2.add(LiquidRatioDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 86, 415, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Roll Load :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 175, -1, 30));

        RollLoad.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(RollLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 176, 515, -1));

        MainPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 660, 220));

        dateSpinner.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        dateSpinner.setModel(new javax.swing.SpinnerDateModel());
        MainPanel.add(dateSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 190, 30));
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void ComputeForVolume()
    {
        String weight = Weight.getText();    
       String liquidRatio = LiquidRatioDropDown.getSelectedItem().toString();
        if(!weight.equals("") && !liquidRatio.equals("Liquid Ratio"))
        {
            computeForVolume();
        } 
    }
    
    private boolean SetProcessOrderInformation()
    {
            thisProcessOrder.setJobOrderID(thisJob.getID());
            if(this.VolumeTextField.getText().length() > 0)
            {
                thisProcessOrder.setVolumeH20(Float.parseFloat(this.VolumeTextField.getText()));
                if(this.Weight.getText().length() > 0)
                {
                    thisProcessOrder.setWeight(Float.parseFloat(this.Weight.getText()));
                    thisProcessOrder.setRollLoad(RollLoad.getText());
                    ProcessOrderHandler ProcessHandler = new ProcessOrderHandler();
                    ProcessHandler.AddNewProcessOrder(thisProcessOrder);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Please check the value in Volume of Water.");
            
        return false;
    }
    private boolean SetJobOrderInformationFromTextBox()
    {
        if(thisCustomer.getCustomerId() > 0)
        {
            thisJob.setCustomerID(thisCustomer.getCustomerId());
            if(thisColor.getColorId() > 0)
            {
                thisJob.setColorID(thisColor.getColorId());
                if(thisDesign.getDesignId() > 0)
                {
                    thisJob.setDesignID(thisDesign.getDesignId());
                    if(thisMachine.getMachineId() > 0)
                    {
                        thisJob.setMachineID(thisMachine.getMachineId());
                        if(JobOrder.getText().length() > 0)
                        {
                            thisJob.setDrNumber(JobOrder.getText());
                            thisJob.setJobDate(get_date_from_spinner(dateSpinner));
                            
                            JobHandler thisJobHandler = new JobHandler();
                            int JobOrderID = thisJobHandler.AddNewJobOrder(thisJob);
                            if(JobOrderID == - 1)
                                JOptionPane.showMessageDialog(null, "Job Order is not successfully added.");
                            else
                            {
                                thisJob.setID(JobOrderID);
                                return true;
                            }
                        }
                        else
                        JOptionPane.showMessageDialog(null, "Please check the Job Order number.");  
                    }
                    else
                    JOptionPane.showMessageDialog(null, "Please check the Machine details.");  
                }
                else
                    JOptionPane.showMessageDialog(null, "Please check the Design name.");  
            }
            else
              JOptionPane.showMessageDialog(null, "Please check the Color name.");  
        }
        else
        JOptionPane.showMessageDialog(null, "Please check the Customer Name.");
        
        return false;
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
    private javax.swing.JComboBox<String> ColorDropDownList;
    private javax.swing.JComboBox<String> CustomerDropDownList;
    private javax.swing.JComboBox<String> DesignDropDownList;
    private javax.swing.JTextField JobOrder;
    private javax.swing.JComboBox<String> LiquidRatioDropDown;
    private javax.swing.JComboBox<String> MachineDropDownList;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JTextField RollLoad;
    private javax.swing.JTextField VolumeTextField;
    private javax.swing.JTextField Weight;
    private javax.swing.JSpinner dateSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
