/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Handlers.ResinProgramHandler;
import Handlers.ResinChemicalHandler;
import Handlers.ChemicalHandler;
import DataEntities.ResinChemical;
import DataEntities.Chemical;
import Handlers.ChemicalHandler;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author imbuenyson
 */
public class ViewResinProgramChemicals extends javax.swing.JFrame {

    String resinProgramName;
    DefaultTableModel model = new DefaultTableModel();
    ResinChemical thisResinChemical = new ResinChemical();
    String currentChemicalName;
    Float currentGPL;
    
    /**
     * Creates new form ViewResinProgramChemicals
     */
    public ViewResinProgramChemicals() {
        initComponents();
        set_to_center();
    }

    public ViewResinProgramChemicals(String resinProgramName)
    {
        this();
        this.resinProgramName = resinProgramName;
        //Header.setText("Control Slip : Page 5/6");
        //this.EditChemicalButton.setText("Next");
        this.GetUpdatedTable();
        initEditComponents();
        populateChemicalComboBox();
        ResinProgramLabel.setText(resinProgramName);
        ResinProgramTextBox.setText(resinProgramName);
    }
    
    private void populateChemicalComboBox()
    {
        ArrayList<String> ChemicalList = new ChemicalHandler().GetAllChemical();
        
        if(ChemicalList != null){
            for(int x=0; x<ChemicalList.size(); x++)
            {
                ChemicalComboBox.addItem(ChemicalList.get(x).toString());
            }
        }         
    }
    
    private void initEditComponents()
    {
        ResinProgramTextBox.setEnabled(true);
        GPLTextField.setEnabled(true);
        ChemicalComboBox.setEnabled(true);
    }
    
    public void set_to_center()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
    
    private void UpdateRowFilter(String row_filter_text)
    {
        TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(this.ResinChemicalTable.getModel());
        
        this.ResinChemicalTable.setRowSorter(rowSorter);
        
        if (row_filter_text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + row_filter_text));        
        }
    }
    
    private void GetUpdatedTable()
    {
        model = getUpdatedResinTableModel();
        this.ResinChemicalTable.setModel(model);
    }
    
    public DefaultTableModel getUpdatedResinTableModel() {      
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        ChemicalHandler chemicalHandler = new ChemicalHandler();
        DefaultTableModel model_original = new DefaultTableModel();
        
        model_original.addColumn("Chemical Name");
        model_original.addColumn("Value GPL");
        model_original.addColumn("State");
        model_original.addColumn("Type");
        
        int resinProgramId = resinProgramHandler.GetResinProgramIDFromResinProgramName(resinProgramName);
        ArrayList<ResinChemical> resinChemicalList = resinChemicalHandler.GetResinChemicalsByResinProgramId(resinProgramId);
        
        for(int x=0; x<resinChemicalList.size(); x++)
        {
            String chemicalName = chemicalHandler.GetChemicalNameFromChemicalID(resinChemicalList.get(x).getChemicalID());
            model_original.addRow(new Object[]{chemicalName, resinChemicalList.get(x).getGPLValue(), resinChemicalList.get(x).getState(), resinChemicalList.get(x).getType()});
        }
        return model_original;
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
        Header = new javax.swing.JLabel();
        ResinProgramLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResinChemicalTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        ResinProgramTextBox = new javax.swing.JTextField();
        ChemicalComboBox = new javax.swing.JComboBox<String>();
        GPLTextField = new javax.swing.JTextField();
        EditChemicalButton = new javax.swing.JButton();
        DeleteChemicalButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        AddChemicalButton = new javax.swing.JButton();
        TypeComboBox = new javax.swing.JComboBox();
        StateComboBox = new javax.swing.JComboBox();
        AddBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel1.setMinimumSize(new java.awt.Dimension(510, 422));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Resin Program Details");
        jPanel1.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 480, 40));

        ResinProgramLabel.setBackground(new java.awt.Color(255, 255, 255));
        ResinProgramLabel.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        ResinProgramLabel.setForeground(new java.awt.Color(255, 255, 255));
        ResinProgramLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResinProgramLabel.setText("Program Label");
        ResinProgramLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.add(ResinProgramLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 87, 485, 40));

        ResinChemicalTable.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinChemicalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Resin Chemical", "Value"
            }
        ));
        jScrollPane1.setViewportView(ResinChemicalTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 485, 200));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Program Name:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 385, -1, 28));

        ResinProgramTextBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinProgramTextBox.setText("Resin Program");
        ResinProgramTextBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ResinProgramTextBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ResinProgramTextBoxFocusLost(evt);
            }
        });
        jPanel1.add(ResinProgramTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 385, 337, 28));

        ChemicalComboBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ChemicalComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chemical" }));
        jPanel1.add(ChemicalComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 340, 170, 30));

        GPLTextField.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        GPLTextField.setForeground(new java.awt.Color(204, 204, 204));
        GPLTextField.setText("GPL Value");
        GPLTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                GPLTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                GPLTextFieldFocusLost(evt);
            }
        });
        jPanel1.add(GPLTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 340, 120, 30));

        EditChemicalButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        EditChemicalButton.setText("Edit");
        EditChemicalButton.setActionCommand("");
        EditChemicalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditChemicalButtonActionPerformed(evt);
            }
        });
        jPanel1.add(EditChemicalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 120, 30));

        DeleteChemicalButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DeleteChemicalButton.setText("Delete");
        DeleteChemicalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteChemicalButtonActionPerformed(evt);
            }
        });
        jPanel1.add(DeleteChemicalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 110, 30));

        BackButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, 80, 30));

        AddChemicalButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        AddChemicalButton.setText("Add");
        AddChemicalButton.setToolTipText("");
        AddChemicalButton.setActionCommand("");
        AddChemicalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddChemicalButtonActionPerformed(evt);
            }
        });
        jPanel1.add(AddChemicalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 120, 30));

        TypeComboBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GPL", "%" }));
        jPanel1.add(TypeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, -1, 30));

        StateComboBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        StateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "G", "L" }));
        jPanel1.add(StateComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 340, 50, 30));

        AddBut.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        AddBut.setText("Add");
        jPanel1.add(AddBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditChemicalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditChemicalButtonActionPerformed
        // TODO add your handling code here:
        String asd = "asd";
            if(EditChemicalButton.getText().equals("Edit"))
            {
                if(ResinChemicalTable.getSelectedRowCount() > 0 )
                {
                    currentChemicalName = this.ResinChemicalTable.getModel().getValueAt(this.ResinChemicalTable.getSelectedRow(), 0).toString();
                    ChemicalComboBox.setSelectedItem(currentChemicalName);
                    currentGPL = Float.parseFloat(this.ResinChemicalTable.getModel().getValueAt(this.ResinChemicalTable.getSelectedRow(), 1).toString());
                    
                    ResinProgramTextBox.setForeground(Color.BLACK);
                    GPLTextField.setForeground(Color.BLACK);
                    //thisResinChemical.se(this.ColorTable.getModel().getValueAt(this.ColorTable.getSelectedRow(), 0).toString());
                    ResinProgramTextBox.setText(ResinProgramLabel.getText());
                    GPLTextField.setText(this.ResinChemicalTable.getModel().getValueAt(this.ResinChemicalTable.getSelectedRow(), 1).toString());
                    //thisResinChemical.setGPLValue(this.ResinChemicalTable.getModel().getValueAt(this.ResinChemicalTable.getSelectedRow(), 1));
                    model.removeRow(this.ResinChemicalTable.getSelectedRow());

                    this.DeleteChemicalButton.setText("Cancel");
                    this.EditChemicalButton.setText("Save");
                    this.BackButton.setEnabled(false);
                    this.AddChemicalButton.setEnabled(false);
                }
                else
                {
                     JOptionPane.showMessageDialog(null, "Please select an Item in the table to be edited");
                }
            }
            else
            {
                ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
                ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
                ChemicalHandler chemicalHandler = new ChemicalHandler();
                ResinChemical newResinChemical = new ResinChemical();
                int chemicalId = -1;
                int resinChemicalId = -1;
                int currentChemicalId = -1;

                String chemicalName = ChemicalComboBox.getSelectedItem().toString();
                
                
                if(currentChemicalName != null)
                {
                    currentChemicalId = chemicalHandler.GetChemicalIDFromChemicalName(currentChemicalName);
                }
                
                if(currentChemicalId > -1)
                {
                    resinChemicalId = resinChemicalHandler.GetResinChemicalIdByChemicalId(currentChemicalId, resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProgramLabel.getText()));
                }
                
                int newResinChemicalId = resinChemicalHandler.GetResinChemicalIdByChemicalId(chemicalHandler.GetChemicalIDFromChemicalName(chemicalName), resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProgramLabel.getText()));
                
                int CloseorNoreply; 
                
                if(newResinChemicalId == -1)
                {
                   CloseorNoreply = JOptionPane.showConfirmDialog(null,"The selected Chemical is different from the chosen existing resin chemical, would you like to add this as a new resin chemical? "
                ,"Non-Existent Resin Chemical", JOptionPane.YES_NO_OPTION);
                   if(CloseorNoreply == JOptionPane.YES_OPTION)
                    {
                    if(chemicalName != "Chemical")
                    {                    
                        newResinChemical.setChemicalID(chemicalHandler.GetChemicalIDFromChemicalName(chemicalName));
                        newResinChemical.setGPLValue(Float.parseFloat(GPLTextField.getText()));
                        newResinChemical.setState(StateComboBox.getSelectedItem().toString());
                        newResinChemical.setType(TypeComboBox.getSelectedItem().toString());
                        newResinChemical.setResinProgramID(resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProgramLabel.getText()));
                    }
                
                    resinChemicalHandler.AddNewResinChemical(newResinChemical);
                    }
                }
                else if(resinChemicalId > -1)
                {
                    resinChemicalHandler.DeleteResinChemical(resinChemicalId);
                    if(chemicalName != "Choose Chemical")
                    {                    
                        newResinChemical.setChemicalID(chemicalHandler.GetChemicalIDFromChemicalName(chemicalName));
                        newResinChemical.setGPLValue(Float.parseFloat(GPLTextField.getText()));
                        newResinChemical.setState(StateComboBox.getSelectedItem().toString());
                        newResinChemical.setType(TypeComboBox.getSelectedItem().toString());
                        newResinChemical.setResinProgramID(resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProgramLabel.getText()));
                    }
                
                    resinChemicalHandler.AddNewResinChemical(newResinChemical);
                }
                this.DeleteChemicalButton.setText("Delete");
                this.EditChemicalButton.setText("Edit");
                this.BackButton.setEnabled(true);
                this.AddChemicalButton.setEnabled(true);
                GetUpdatedTable();
            }
        
    }//GEN-LAST:event_EditChemicalButtonActionPerformed

    private void DeleteChemicalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteChemicalButtonActionPerformed
        // TODO add your handling code here:
        
            if(DeleteChemicalButton.getText().equals("Cancel"))
            {
                GetUpdatedTable();

                ResinProgramTextBox.setText(this.resinProgramName);
                GPLTextField.setText("GPL Value");
                ChemicalComboBox.setSelectedIndex(0);

                this.DeleteChemicalButton.setText("Delete");
                this.EditChemicalButton.setText("Edit");
                this.BackButton.setEnabled(true);
                this.AddChemicalButton.setEnabled(true);
            }
            else
            {
                if(ResinChemicalTable.getSelectedRowCount() > 0 )
                {
                    ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
                    ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
                    ChemicalHandler chemicalHandler = new ChemicalHandler();
                    int chemicalId = -1;
                    int resinChemicalId = -1;

                    String chemicalName = this.ResinChemicalTable.getModel().getValueAt(this.ResinChemicalTable.getSelectedRow(), 0).toString();

                    if(chemicalName != "Chemical")
                    {
                        chemicalId = chemicalHandler.GetChemicalIDFromChemicalName(chemicalName);
                    }
                    
                    if(chemicalId > -1)
                    {
                        resinChemicalId = resinChemicalHandler.GetResinChemicalIdByChemicalId(chemicalId, resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProgramLabel.getText()));
                    }

                    if(resinChemicalId > -1)
                    {
                       resinChemicalHandler.DeleteResinChemical(resinChemicalId); 
                    }

                    GetUpdatedTable();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select an Item in the table to be deleted");
                }
            }
        
        
    }//GEN-LAST:event_DeleteChemicalButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void GPLTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GPLTextFieldFocusGained
        // TODO add your handling code here:
        if(GPLTextField.getText().equals("GPL Value") == true)
        {
            GPLTextField.setForeground(Color.BLACK);
            GPLTextField.setText("");
        }
    }//GEN-LAST:event_GPLTextFieldFocusGained

    private void GPLTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_GPLTextFieldFocusLost
        // TODO add your handling code here:
        if(GPLTextField.getText().trim().equalsIgnoreCase("") == true)
        {
            GPLTextField.setText("GPL Value");
        }
    }//GEN-LAST:event_GPLTextFieldFocusLost

    private void ResinProgramTextBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ResinProgramTextBoxFocusGained
        // TODO add your handling code here:
        //if(ResinProgramTextBox.getText().equals(this.resinProgramName) == true)
        //{
        //    ResinProgramTextBox.setText("");
        //}
    }//GEN-LAST:event_ResinProgramTextBoxFocusGained

    private void ResinProgramTextBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ResinProgramTextBoxFocusLost
        // TODO add your handling code here:
        //if(ResinProgramTextBox.getText().trim().equalsIgnoreCase("") == true)
        //{
        //    ResinProgramTextBox.setText(this.resinProgramName);
        //}
    }//GEN-LAST:event_ResinProgramTextBoxFocusLost

    private void AddChemicalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddChemicalButtonActionPerformed
        // TODO add your handling code here:
        boolean ready = CheckIfDataFieldInputReady();
        int CloseorNoreply; 
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        
        if(ResinProgramTextBox.getText().equals(this.resinProgramName) == false)
        {
            CloseorNoreply = JOptionPane.showConfirmDialog(null,"The resin program name is different from the current program name, do you want to update the program name?"
                            ,"Update Resin Program Name", JOptionPane.YES_NO_OPTION);
            if(CloseorNoreply == JOptionPane.YES_OPTION)
            {
                resinProgramHandler.UpdateResinProgramName(ResinProgramTextBox.getText(), this.resinProgramName);
                ResinProgramLabel.setText(ResinProgramTextBox.getText());
                this.resinProgramName = ResinProgramTextBox.getText();
            }
        }
        
        if(ready == true)
        {
            ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
                ChemicalHandler chemicalHandler = new ChemicalHandler();
                ResinChemical newResinChemical = new ResinChemical();
                int chemicalId = -1;
                int resinChemicalId = -1;
                int currentChemicalId = -1;

                String chemicalName = ChemicalComboBox.getSelectedItem().toString();
                
                int newResinChemicalId = resinChemicalHandler.GetResinChemicalIdByChemicalId(chemicalHandler.GetChemicalIDFromChemicalName(chemicalName), resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProgramLabel.getText()));
                                
                if(newResinChemicalId == -1)
                {
                    if(chemicalName != "Chemical")
                    {                    
                        newResinChemical.setChemicalID(chemicalHandler.GetChemicalIDFromChemicalName(chemicalName));
                        newResinChemical.setGPLValue(Float.parseFloat(GPLTextField.getText()));
                        newResinChemical.setState(StateComboBox.getSelectedItem().toString());
                        newResinChemical.setType(TypeComboBox.getSelectedItem().toString());
                        newResinChemical.setResinProgramID(resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProgramLabel.getText()));
                    }
                    resinChemicalHandler.AddNewResinChemical(newResinChemical);
                }
                
                if(newResinChemicalId > -1)
                {
                    resinChemicalHandler.DeleteResinChemical(newResinChemicalId);
                    if(chemicalName != "Chemical")
                    {                    
                        newResinChemical.setChemicalID(chemicalHandler.GetChemicalIDFromChemicalName(chemicalName));
                        newResinChemical.setState(StateComboBox.getSelectedItem().toString());
                        newResinChemical.setType(TypeComboBox.getSelectedItem().toString());
                        newResinChemical.setGPLValue(Float.parseFloat(GPLTextField.getText()));
                        newResinChemical.setResinProgramID(resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProgramLabel.getText()));
                    }
                
                    resinChemicalHandler.AddNewResinChemical(newResinChemical);
                }
                GetUpdatedTable();
        }
    }//GEN-LAST:event_AddChemicalButtonActionPerformed

    public boolean CheckIfDataFieldInputReady()
    {
        boolean ready = true;
        if(ChemicalComboBox.getSelectedItem().equals("Chemical") == true)
        {
            return ready = false;
        }
        if(GPLTextField.getText().equals("GPL Value") == true)
        {
            return ready = false;
        }
        return ready;
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
            java.util.logging.Logger.getLogger(ViewResinProgramChemicals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewResinProgramChemicals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewResinProgramChemicals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewResinProgramChemicals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewResinProgramChemicals().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBut;
    private javax.swing.JButton AddChemicalButton;
    private javax.swing.JButton BackButton;
    private javax.swing.JComboBox<String> ChemicalComboBox;
    private javax.swing.JButton DeleteChemicalButton;
    private javax.swing.JButton EditChemicalButton;
    private javax.swing.JTextField GPLTextField;
    private javax.swing.JLabel Header;
    private javax.swing.JTable ResinChemicalTable;
    private javax.swing.JLabel ResinProgramLabel;
    private javax.swing.JTextField ResinProgramTextBox;
    private javax.swing.JComboBox StateComboBox;
    private javax.swing.JComboBox TypeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
