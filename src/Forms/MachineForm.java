/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.Machine;
import Handlers.MachineHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author Eldridge
 */
public class MachineForm extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    Machine thisMachine = new Machine();
    Color DefaultColor = new Color(220,220,220);
    Color ColorError = new Color(232,228,42);
    Color TypingColor = Color.BLACK;
    /**
     * Creates new form AddMachine
     */
    public MachineForm() {
        initComponents();
        this.get_updated_table();
        SetToCenter();
    }
    
    public void SetToCenter()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        AddMachineButton = new javax.swing.JButton();
        EditMachineButton = new javax.swing.JButton();
        DeleteMachineButton = new javax.swing.JButton();
        MachineName = new javax.swing.JTextField();
        MachineTable = new javax.swing.JScrollPane();
        MachineListTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        DeleteMachineButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        MachineMaximumCapacity = new javax.swing.JTextField();
        MachineMinimumCapacity = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        MachineMinimumVolume = new javax.swing.JTextField();
        MachineMaximumVolume = new javax.swing.JTextField();
        LoadingArrangement = new javax.swing.JTextField();
        MachineTypeComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");
        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(825, 590));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddMachineButton.setBackground(new java.awt.Color(220, 220, 220));
        AddMachineButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AddMachineButton.setText("Add");
        AddMachineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMachineButtonActionPerformed(evt);
            }
        });
        jPanel1.add(AddMachineButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, 185, 32));

        EditMachineButton.setBackground(new java.awt.Color(220, 220, 220));
        EditMachineButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        EditMachineButton.setText("Edit");
        EditMachineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditMachineButtonActionPerformed(evt);
            }
        });
        jPanel1.add(EditMachineButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 290, 365, 30));

        DeleteMachineButton.setBackground(new java.awt.Color(220, 220, 220));
        DeleteMachineButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DeleteMachineButton.setText("Delete");
        DeleteMachineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteMachineButtonActionPerformed(evt);
            }
        });
        jPanel1.add(DeleteMachineButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 290, 365, 30));

        MachineName.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MachineName.setForeground(new java.awt.Color(204, 204, 204));
        MachineName.setText("Name");
        MachineName.setToolTipText("");
        MachineName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MachineNameFocusGained(evt);
            }
        });
        MachineName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MachineNameKeyReleased(evt);
            }
        });
        jPanel1.add(MachineName, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 365, 365, 30));

        MachineListTable.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MachineListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        MachineTable.setViewportView(MachineListTable);

        jPanel1.add(MachineTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 73, 740, 212));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Machine");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 18, 228, 44));

        DeleteMachineButton1.setBackground(new java.awt.Color(220, 220, 220));
        DeleteMachineButton1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        DeleteMachineButton1.setText("Close");
        DeleteMachineButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteMachineButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(DeleteMachineButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 540, 185, 32));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MachineMaximumCapacity.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MachineMaximumCapacity.setForeground(new java.awt.Color(204, 204, 204));
        MachineMaximumCapacity.setText("Max Capacity");
        MachineMaximumCapacity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MachineMaximumCapacityFocusGained(evt);
            }
        });
        jPanel2.add(MachineMaximumCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 30, 350, 30));

        MachineMinimumCapacity.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MachineMinimumCapacity.setForeground(new java.awt.Color(204, 204, 204));
        MachineMinimumCapacity.setText("Min Capacity");
        MachineMinimumCapacity.setToolTipText("");
        MachineMinimumCapacity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MachineMinimumCapacityFocusGained(evt);
            }
        });
        jPanel2.add(MachineMinimumCapacity, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 30, 350, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 394, 740, 70));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Volume", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MachineMinimumVolume.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MachineMinimumVolume.setForeground(new java.awt.Color(204, 204, 204));
        MachineMinimumVolume.setText("Min Volume");
        MachineMinimumVolume.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MachineMinimumVolumeFocusGained(evt);
            }
        });
        jPanel3.add(MachineMinimumVolume, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 25, 350, 30));

        MachineMaximumVolume.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MachineMaximumVolume.setForeground(new java.awt.Color(204, 204, 204));
        MachineMaximumVolume.setText("Max Volume");
        MachineMaximumVolume.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MachineMaximumVolumeFocusGained(evt);
            }
        });
        jPanel3.add(MachineMaximumVolume, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 25, 350, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 740, 70));

        LoadingArrangement.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        LoadingArrangement.setForeground(new java.awt.Color(204, 204, 204));
        LoadingArrangement.setText("Number Of Loading Arrangement");
        LoadingArrangement.setToolTipText("");
        LoadingArrangement.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LoadingArrangementFocusGained(evt);
            }
        });
        jPanel1.add(LoadingArrangement, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 365, 365, 30));

        MachineTypeComboBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        MachineTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dyeing Machine", "Resin Machine" }));
        jPanel1.add(MachineTypeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 327, 735, 30));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 820, 580);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MachineMinimumCapacityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MachineMinimumCapacityFocusGained
        // TODO add your handling code here:
        if(MachineMinimumCapacity.getText().equals("Min Capacity"))
        {
            MachineMinimumCapacity.setText("");
            MachineMinimumCapacity.setForeground(TypingColor);
        }
    }//GEN-LAST:event_MachineMinimumCapacityFocusGained

    private void MachineMaximumCapacityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MachineMaximumCapacityFocusGained
        // TODO add your handling code here:MachineMaximumCapacity
        if(MachineMaximumCapacity.getText().equals("Max Capacity"))
        {
            MachineMaximumCapacity.setText("");
            MachineMaximumCapacity.setForeground(TypingColor);
        }
    }//GEN-LAST:event_MachineMaximumCapacityFocusGained

    private void MachineNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MachineNameFocusGained
        // TODO add your handling code here:
        if(MachineName.getText().equals("Name"))
        {
            MachineName.setText("");
            MachineName.setForeground(TypingColor);
        }
    }//GEN-LAST:event_MachineNameFocusGained

    private void DeleteMachineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteMachineButtonActionPerformed
        // TODO add your handling code here:

        if(DeleteMachineButton.getText().equals("Cancel"))
        {
             model.addRow((new Object[]{
                thisMachine.getMachineName(), 
                thisMachine.getMinCapacity(), 
                thisMachine.getMaxCapacity(), 
                thisMachine.getMinVolume(), 
                thisMachine.getMaxVolume()}));
                
                thisMachine.setMachineId(-1);
                thisMachine.setMachineName("");
                thisMachine.setMinCapacity(-1);
                thisMachine.setMaxCapacity(-1);
                thisMachine.setMinVolume(-1);
                thisMachine.setMaxVolume(-1);
                
                this.UpdateRowFilter("");
                
                this.ResetText();
        }
        //If text is equal to Delete
        else
        {
            if(MachineListTable.getSelectedRowCount() > 0 )
            {
            MachineHandler handler = new MachineHandler();
            int CloseorNoreply = JOptionPane.showConfirmDialog(null,"Delete this machine? "
                ,"Delete Machine?", JOptionPane.YES_NO_OPTION);
            if(CloseorNoreply == JOptionPane.YES_OPTION)
            {
                String machineName = MachineListTable.getModel().getValueAt(MachineListTable.getSelectedRow(),0).toString();
                thisMachine.setMachineName(machineName);
                int machineId = handler.GetMachineIdByName(thisMachine.getMachineName());
                thisMachine.setMachineId(machineId);
                handler.DeleteMachineById(thisMachine.getMachineId());
                this.get_updated_table();
                this.UpdateRowFilter("");
            }
            }else
            {
                JOptionPane.showMessageDialog(null, "Please select an Item in the table to be deleted");
            }
        }
    }//GEN-LAST:event_DeleteMachineButtonActionPerformed

    private void SetFormTextFromMachineObject()
    {
        MachineName.setText(thisMachine.getMachineName());
        MachineMinimumCapacity.setText(Integer.toString(thisMachine.getMinCapacity()));
        MachineMaximumCapacity.setText(Integer.toString(thisMachine.getMaxCapacity()));
        MachineMinimumVolume.setText(Integer.toString(thisMachine.getMinVolume()));
        MachineMaximumVolume.setText(Integer.toString(thisMachine.getMaxVolume()));
        LoadingArrangement.setText(Integer.toString(thisMachine.getNumOfLoad()));
        MachineTypeComboBox.setSelectedIndex(thisMachine.getMachineType());   
    }
    
    private void SetMachineObjectFromSelectedTableIndex()
    {
        thisMachine.setMachineName(this.MachineListTable.getModel().getValueAt(this.MachineListTable.getSelectedRow(), 0).toString());
        thisMachine.setMinCapacity(Integer.parseInt(this.MachineListTable.getModel().getValueAt(this.MachineListTable.getSelectedRow(), 1).toString()));
        thisMachine.setMaxCapacity(Integer.parseInt(this.MachineListTable.getModel().getValueAt(this.MachineListTable.getSelectedRow(), 2).toString()));
        thisMachine.setMinVolume(Integer.parseInt(this.MachineListTable.getModel().getValueAt(this.MachineListTable.getSelectedRow(), 3).toString()));
        thisMachine.setMaxVolume(Integer.parseInt(this.MachineListTable.getModel().getValueAt(this.MachineListTable.getSelectedRow(), 4).toString()));
        thisMachine.setNumOfLoad(Integer.parseInt(this.MachineListTable.getModel().getValueAt(this.MachineListTable.getSelectedRow(), 5).toString()));
        thisMachine.setMachineType(Integer.parseInt(this.MachineListTable.getModel().getValueAt(this.MachineListTable.getSelectedRow(), 5).toString()));
        thisMachine.setMachineId(new MachineHandler().GetMachineIdByName(thisMachine.getMachineName()));
    }
    
    private void EditMachineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditMachineButtonActionPerformed
        
        if (EditMachineButton.getText().equals("Edit")) {
            if (MachineListTable.getSelectedRowCount() > 0) {
                
                setTextForegroundColor(TypingColor);
                SetMachineObjectFromSelectedTableIndex();
                SetFormTextFromMachineObject();
                
                model.removeRow(this.MachineListTable.getSelectedRow());

                this.EditMachineButton.setText("Save");
                this.DeleteMachineButton.setText("Cancel");
                this.AddMachineButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an Item in the table to be edited");
            }
        } else {
            MachineHandler handler = new MachineHandler();
            if (GetMachineDetails()) {
                if (handler.UpdateMachine(thisMachine)) {
                    JOptionPane.showMessageDialog(null, "Successfully updated the machine");
                    ResetText();
                    this.get_updated_table();
                    this.UpdateRowFilter("");
                } else {
                    JOptionPane.showMessageDialog(null, "Item was not successfully edited");
                }
            }

        }
    }//GEN-LAST:event_EditMachineButtonActionPerformed
    
    private void setTextForegroundColor(Color thisColor)
    {
        MachineName.setForeground(thisColor);
        MachineMinimumCapacity.setForeground(thisColor);
        MachineMaximumCapacity.setForeground(thisColor);
        MachineMinimumVolume.setForeground(thisColor);
        MachineMaximumVolume.setForeground(thisColor);
        LoadingArrangement.setForeground(thisColor);
    }
    
    private void ResetText()
    {
        EditMachineButton.setText("Edit");
        this.DeleteMachineButton.setText("Delete");
        this.AddMachineButton.setEnabled(true);
        
        this.MachineName.setText("Name");
        this.MachineMinimumCapacity.setText("Min Capacity");
        this.MachineMaximumCapacity.setText("Max Capacity");
        this.MachineMinimumVolume.setText("Min Volume");
        this.MachineMaximumVolume.setText("Max Volume");
        this.LoadingArrangement.setText("Number Of Loading Arrangement");
        this.setTextForegroundColor(DefaultColor);
    }
    
    private void UpdateRowFilter(String row_filter_text)
    {
        TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(this.MachineListTable.getModel());
        
        this.MachineListTable.setRowSorter(rowSorter);
        
        if (row_filter_text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + row_filter_text));        
        }
    }

    private void AddMachineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMachineButtonActionPerformed
        // TODO add your handling code here:
        //Machine newMachineDetails = GetMachineDetails();
        if(GetMachineDetails())
        {
            MachineHandler handler = new MachineHandler();
            handler.AddNewMachine(thisMachine);
            get_updated_table();
            this.UpdateRowFilter("");
        }
    }//GEN-LAST:event_AddMachineButtonActionPerformed

    private boolean GetMachineDetails()
    {   
        boolean DetailsValid = true;
        //Check if machine is a dyeing or resin machine
        if(MachineTypeComboBox.getSelectedItem().toString().equals("Dyeing Machine") == true)
        {
            //Machine is for Dyeing
            thisMachine.setMachineType(0);
        }
        else
            //Machine is for Resin
            thisMachine.setMachineType(1);
        
        //Machine newMachineDetails = new Machine();
        if(MachineName.getText().length() > 0 && !MachineName.getText().equals("Name"))
        {
            thisMachine.setMachineName(MachineName.getText());
            //newMachineDetails.setMachineName(MachineName.getText());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please input a machine name");
            MachineName.setBackground(ColorError);
            DetailsValid = false;
        }
        
        if(LoadingArrangement.getText().matches("[0-9]+") == true)
        {
            thisMachine.setNumOfLoad(Integer.parseInt(LoadingArrangement.getText()));
        }
        else
        {
            if(LoadingArrangement.getText().equals("Number Of Loading Arrangement"))
                JOptionPane.showMessageDialog(null, "Please input the number of loading arrangement");
            else
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid number in the loading arrangement");
            }
            LoadingArrangement.setBackground(ColorError);
            DetailsValid = false;
        }

        //Maximum Capacity Check
        if(MachineMaximumCapacity.getText().matches("[0-9]+") == true )
        {
            thisMachine.setMaxCapacity(Integer.parseInt(MachineMaximumCapacity.getText()));
            //newMachineDetails.setMaxCapacity(Integer.parseInt(MachineMaximumCapacity.getText()));
            //TODO numeric characters only validation message
        }
        else 
        {
            if(MachineMaximumCapacity.getText().length() <= 0)
            {
                JOptionPane.showMessageDialog(null, "Please enter at least one number in the Machine's maximum capacity");
            }
            else
                JOptionPane.showMessageDialog(null, "Please enter a valid number in the Machine's maximum capacity");
            MachineMaximumCapacity.setBackground(ColorError);
            DetailsValid = false;
            //TODO please input machine name
        }
        
        /* Minimum Capacity Check */
        if(MachineMinimumCapacity.getText().matches("[0-9]+") == true )
        {
            thisMachine.setMinCapacity(Integer.parseInt(MachineMinimumCapacity.getText()));
            //newMachineDetails.setMinCapacity(Integer.parseInt(MachineMinimumCapacity.getText()));
            
            //TODO numeric characters only validation message
        }
        else
        {
             if(MachineMinimumCapacity.getText().length() <= 0){
                 JOptionPane.showMessageDialog(null, "Please enter at least one number in the Machine's minimum capacity");
             }
             else
                 JOptionPane.showMessageDialog(null, "Please enter a valid number in the Machine's minimum capacity");
            DetailsValid = false;
            MachineMinimumCapacity.setBackground(ColorError);
            //TODO please input machine name
        }

        /* Maximum Volume Check */
        if(MachineMaximumVolume.getText().matches("[0-9]+") == true)
        {
            thisMachine.setMaxVolume(Integer.parseInt(MachineMaximumVolume.getText()));
            //newMachineDetails.setMaxVolume(Integer.parseInt(MachineMaximumVolume.getText()));
        }
        else 
        {
            if(MachineMaximumVolume.getText().length() <= 0){
                JOptionPane.showMessageDialog(null, "Please input at least one number in the Machine's maximum volume");
            }
            else
                //TODO numeric characters only validation message
                JOptionPane.showMessageDialog(null, "Please enter a valid number in the Machine's maximum volume");
            MachineMaximumVolume.setBackground(ColorError);
            DetailsValid = false;
            //TODO please input machine name
        }

        /* Minimum Volume Check */
        if(MachineMinimumVolume.getText().matches("[0-9]+") == true)
        {
            thisMachine.setMinVolume(Integer.parseInt(MachineMinimumVolume.getText()));
            //newMachineDetails.setMinVolume(Integer.parseInt(MachineMinimumVolume.getText()));
            //TODO numeric characters only validation message
        }
        else 
        {
            if(MachineMinimumVolume.getText().length() <= 0)
            {
                JOptionPane.showMessageDialog(null, "Please input at least one number in the Machine's mimimum volume");
            }
            else
                JOptionPane.showMessageDialog(null, "Please enter a valid number in the Machine's mimimum volume");
            MachineMinimumVolume.setBackground(ColorError);
            DetailsValid = false;
            //TODO please input machine name
        }
        
        return DetailsValid;
    }
    private void MachineMinimumVolumeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MachineMinimumVolumeFocusGained
        // TODO add your handling code here:
        if(MachineMinimumVolume.getText().equals("Min Volume"))
        {
            MachineMinimumVolume.setText("");
            MachineMinimumVolume.setForeground(TypingColor);
        }
    }//GEN-LAST:event_MachineMinimumVolumeFocusGained

    private void MachineMaximumVolumeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MachineMaximumVolumeFocusGained
        // TODO add your handling code here:
        if(MachineMaximumVolume.getText().equals("Max Volume"))
        {
            MachineMaximumVolume.setText("");
            MachineMaximumVolume.setForeground(TypingColor);
        }
    }//GEN-LAST:event_MachineMaximumVolumeFocusGained

    private void MachineNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MachineNameKeyReleased
        // TODO add your handling code here:
        UpdateRowFilter(this.MachineName.getText());
    }//GEN-LAST:event_MachineNameKeyReleased

    private void DeleteMachineButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteMachineButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_DeleteMachineButton1ActionPerformed

    private void LoadingArrangementFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LoadingArrangementFocusGained
        // TODO add your handling code here:
        if(LoadingArrangement.getText().equals("Number Of Loading Arrangement"))
        {
            LoadingArrangement.setText("");
            LoadingArrangement.setForeground(TypingColor);
        }
        
    }//GEN-LAST:event_LoadingArrangementFocusGained

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
            java.util.logging.Logger.getLogger(MachineForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MachineForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MachineForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MachineForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MachineForm().setVisible(true);
            }
        });
    }
    
    public DefaultTableModel GetAllMachines() {      
        
        DefaultTableModel model_original = new DefaultTableModel();
        model_original.addColumn("Name");
        model_original.addColumn("Minimum Capacity");
        model_original.addColumn("Maximum Capacity");
        model_original.addColumn("Minimum Volume");
        model_original.addColumn("Maximum Volume");
        model_original.addColumn("Loading Arrangement");
        model_original.addColumn("Machine Type");
        
       
        ArrayList<Machine> MachineList = new MachineHandler().GetAllMachines();
        for(int x=0; x<MachineList.size(); x++)
        {
            model_original.addRow(new Object[]{
                MachineList.get(x).getMachineName(), 
                MachineList.get(x).getMinCapacity(), 
                MachineList.get(x).getMaxCapacity(), 
                MachineList.get(x).getMinVolume(), 
                MachineList.get(x).getMaxVolume(),
                MachineList.get(x).getNumOfLoad(),
                MachineList.get(x).getMachineType()});
            
        }
        
        //pigment_table.setTableHeader(null);
        this.ResetText();
        return model_original;
    }
    
    private void get_updated_table()
    {
        model = GetAllMachines();
        this.MachineListTable.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMachineButton;
    private javax.swing.JButton DeleteMachineButton;
    private javax.swing.JButton DeleteMachineButton1;
    private javax.swing.JButton EditMachineButton;
    private javax.swing.JTextField LoadingArrangement;
    private javax.swing.JTable MachineListTable;
    private javax.swing.JTextField MachineMaximumCapacity;
    private javax.swing.JTextField MachineMaximumVolume;
    private javax.swing.JTextField MachineMinimumCapacity;
    private javax.swing.JTextField MachineMinimumVolume;
    private javax.swing.JTextField MachineName;
    private javax.swing.JScrollPane MachineTable;
    private javax.swing.JComboBox MachineTypeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
