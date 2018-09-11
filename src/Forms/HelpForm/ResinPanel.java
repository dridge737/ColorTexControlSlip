/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.HelpForm;

import DataEntities.Machine;
import Handlers.MachineHandler;
import Handlers.PreferenceHandler;
import Handlers.ResinProgramHandler;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Eldridge
 */
public class ResinPanel extends javax.swing.JPanel {

    /**
     * @return the machineDetails
     */
    public Machine getMachineDetails() {
        return machineDetails;
    }

    /**
     * @param machineDetails the machineDetails to set
     */
    public void setMachineDetails(Machine machineDetails) {
        this.machineDetails = machineDetails;
    }

    private Machine machineDetails = new Machine();
    /**
     * Creates new form ResinPanel
     */
    public ResinPanel() {
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

        ResinMachineDropDown = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ResinWeight = new javax.swing.JTextField();
        ResinVolumeTextField = new javax.swing.JTextField();
        ResinProgramText = new javax.swing.JTextField();
        ResinLabel = new javax.swing.JLabel();
        EditResinProgram = new javax.swing.JButton();
        ResinFabricTypeDropDown = new javax.swing.JComboBox<>();
        FabricTypeLabel = new javax.swing.JLabel();
        ResinMachineLiquidRatioDropDown = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(102, 102, 102));

        ResinMachineDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinMachineDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Machine" }));
        ResinMachineDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResinMachineDropDownActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Weight :");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Volume of Water :");

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

        ResinVolumeTextField.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinVolumeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ResinVolumeTextFieldKeyReleased(evt);
            }
        });

        ResinProgramText.setEditable(false);
        ResinProgramText.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinProgramText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ResinProgramText.setEnabled(false);

        ResinLabel.setBackground(new java.awt.Color(255, 255, 255));
        ResinLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinLabel.setForeground(new java.awt.Color(255, 255, 255));
        ResinLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ResinLabel.setText("Resin Program :");
        ResinLabel.setToolTipText("");

        EditResinProgram.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        EditResinProgram.setText("...");
        EditResinProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditResinProgramActionPerformed(evt);
            }
        });

        ResinFabricTypeDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinFabricTypeDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fabric Type", "TC 0.3 + 30 liters", "CVC 0.4 + 30 liters", "CC 0.5 + 30 liters", "Polyester and Spun 0.65 + 30 liters", "TC 0.5" }));
        ResinFabricTypeDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResinFabricTypeDropDownActionPerformed(evt);
            }
        });

        FabricTypeLabel.setBackground(new java.awt.Color(255, 255, 255));
        FabricTypeLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        FabricTypeLabel.setForeground(new java.awt.Color(255, 255, 255));
        FabricTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        FabricTypeLabel.setText("Fabric Type :");

        ResinMachineLiquidRatioDropDown.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinMachineLiquidRatioDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Liquid Ratio" }));
        ResinMachineLiquidRatioDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResinMachineLiquidRatioDropDownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ResinMachineDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(FabricTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(ResinMachineLiquidRatioDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ResinFabricTypeDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(ResinWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(ResinVolumeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(565, 565, 565)
                        .addComponent(EditResinProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ResinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(ResinProgramText, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ResinMachineDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FabricTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResinMachineLiquidRatioDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResinFabricTypeDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ResinWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResinVolumeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EditResinProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResinProgramText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ResinMachineDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResinMachineDropDownActionPerformed
        // TODO add your handling code here:
        
        MachineHandler handler = new MachineHandler();
        int machineId = -1;
        String machineName = "";

        if (!ResinMachineDropDown.getSelectedItem().toString().equals("Choose Machine")) {
            machineName = ResinMachineDropDown.getSelectedItem().toString();

            if (!machineName.equals("")) {
                machineId = handler.GetMachineIdByName(machineName);

                if (machineId > -1) {
                    setMachineDetails(handler.GetMachineDetailsById(machineId));

                    //getMachineDetails().setMaxCapacity(getMachineDetails().getMaxCapacity());
                    //getMachineDetails().setMaxVolume(getMachineDetails().getMaxVolume());
                    //getMachineDetails().setMinCapacity(getMachineDetails().getMinCapacity());
                    //getMachineDetails().setMinVolume(getMachineDetails().getMinVolume());
                    //getMachineDetails().setNumOfLoad(getMachineDetails().getNumOfLoad());
                    //getMachineDetails().setMachineId(machineId);
                }
            }
        }

        //thisResinMachine.setMachineName(getMachineDetails().getMachineName());
    }//GEN-LAST:event_ResinMachineDropDownActionPerformed

    private void ResinWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ResinWeightFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ResinWeightFocusLost

    private void SetResinProgramName() {
        if (thisJob.getResinProgramID() > 0) {
            String ResinProgramName
                    = new ResinProgramHandler().GetResinProgramNameFromResinProgramID(thisJob.getResinProgramID());
            ResinProgramText.setText(ResinProgramName);
        }

    }
    
    private void SetResinMachineNameDropDown() {
        thisResinMachine.setMachineId(thisJob.getThisResinJob().get(0).getResinMachineID());
        MachineHandler thisMachineHandler = new MachineHandler();
        thisResinMachine = new MachineHandler().GetMachineDetailsById(thisResinMachine.getMachineId());
        ResinMachineDropDown.setSelectedItem(thisResinMachine.getMachineName());
        ResinWeight.setText(Float.toString(thisJob.getResinWeight()));
        ResinVolumeTextField.setText(Float.toString(thisJob.getResinVolumeH20()));
    }
    
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
    
     private void ComputeForResinVolume() {
        String weight = ResinWeight.getText();
        //String liquidRatio = this.ResinMachineLiquidRatioDropDown.getSelectedItem().toString();
        if (!weight.isEmpty() && ResinFabricTypeDropDown.getSelectedIndex()!= 0 )//liquidRatio.equals("Liquid Ratio"))
        {
            //String selected = DyeingMachineLiquidRatioDropDown.getSelectedItem().toString();
            ResinVolumeTextField.setText(Float.toString(new MachineHandler().ComputeVolumeOfWaterFromWeight(Float.parseFloat(weight), ResinFabricTypeDropDown.getSelectedItem().toString())));
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

    private void ResinVolumeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ResinVolumeTextFieldKeyReleased
        // TODO add your handling code here:
        String volume = this.ResinVolumeTextField.getText();
        volume = volume.replaceAll("[^\\d.]", "");
        ResinVolumeTextField.setText(volume);
        if (this.CheckTextBoxIsParseValid(ResinVolumeTextField)) {
            thisJob.setResinVolumeH20(Float.parseFloat(volume));
        }
    }//GEN-LAST:event_ResinVolumeTextFieldKeyReleased

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

    private void ResinMachineLiquidRatioDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResinMachineLiquidRatioDropDownActionPerformed
        // TODO add your handling code here:
        ComputeForResinVolume();
    }//GEN-LAST:event_ResinMachineLiquidRatioDropDownActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditResinProgram;
    private javax.swing.JLabel FabricTypeLabel;
    private javax.swing.JComboBox<String> ResinFabricTypeDropDown;
    private javax.swing.JLabel ResinLabel;
    private javax.swing.JComboBox<String> ResinMachineDropDown;
    private javax.swing.JComboBox<String> ResinMachineLiquidRatioDropDown;
    private javax.swing.JTextField ResinProgramText;
    private javax.swing.JTextField ResinVolumeTextField;
    private javax.swing.JTextField ResinWeight;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    // End of variables declaration//GEN-END:variables
}
