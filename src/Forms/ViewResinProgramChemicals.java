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
    
    public void initialize()
    {
        initComponents();
        set_to_center();
    }
    /**
     * Creates new form ViewResinProgramChemicals
     */
    public ViewResinProgramChemicals() {
        initialize();
    }

    public ViewResinProgramChemicals(String resinProgramName)
    {
        initialize();
        this.resinProgramName = resinProgramName;
        Header.setText("Dyeing Control Slip : Page 5/6");
        this.GetUpdatedTable();
        initEditComponents();
        populateChemicalComboBox();
        ResinProgramLabel.setText(resinProgramName);
        
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
        ResinProgramTextBox.setEnabled(false);
        GPLTextField.setEnabled(false);
        ChemicalComboBox.setEnabled(false);
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
        
        int resinProgramId = resinProgramHandler.GetResinProgramIDFromResinProgramName(resinProgramName);
        ArrayList<ResinChemical> resinChemicalList = resinChemicalHandler.GetResinChemicalsByResinProgramId(resinProgramId);
        
        for(int x=0; x<resinChemicalList.size(); x++)
        {
            String chemicalName = chemicalHandler.GetChemicalNameFromChemicalID(resinChemicalList.get(x).getChemicalID());
            model_original.addRow(new Object[]{chemicalName, resinChemicalList.get(x).getGPLValue()});
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ResinProgramTextBox = new javax.swing.JTextField();
        ChemicalComboBox = new javax.swing.JComboBox<String>();
        GPLTextField = new javax.swing.JTextField();
        EditChemicalButton = new javax.swing.JButton();
        DeleteChemicalButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Color Text Control Slip");

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
        ResinProgramLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ResinProgramLabel.setText("  Resin Program List");
        ResinProgramLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.add(ResinProgramLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 485, 40));

        ResinChemicalTable.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinChemicalTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(ResinChemicalTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 485, 200));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Program Name:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 340, -1, 28));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Chemical :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 390, 89, 30));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("GPL :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, 50, 30));

        ResinProgramTextBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ResinProgramTextBox.setForeground(new java.awt.Color(204, 204, 204));
        ResinProgramTextBox.setText("Resin Program");
        jPanel1.add(ResinProgramTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 340, 337, 28));

        ChemicalComboBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ChemicalComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Chemical" }));
        jPanel1.add(ChemicalComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 390, -1, 30));

        GPLTextField.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        GPLTextField.setForeground(new java.awt.Color(204, 204, 204));
        GPLTextField.setText("GPL Value");
        jPanel1.add(GPLTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 390, 144, 30));

        EditChemicalButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        EditChemicalButton.setText("Edit");
        EditChemicalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditChemicalButtonActionPerformed(evt);
            }
        });
        jPanel1.add(EditChemicalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 440, 130, 30));

        DeleteChemicalButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        DeleteChemicalButton.setText("Delete");
        DeleteChemicalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteChemicalButtonActionPerformed(evt);
            }
        });
        jPanel1.add(DeleteChemicalButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 130, 30));

        BackButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        BackButton.setText("Back");
        jPanel1.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 440, 130, 30));

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
        
            if(EditChemicalButton.getText().equals("Edit"))
            {
                if(ResinChemicalTable.getSelectedRowCount() > 0 )
                {
                    currentChemicalName = this.ResinChemicalTable.getModel().getValueAt(this.ResinChemicalTable.getSelectedRow(), 0).toString();
                    currentGPL = Float.parseFloat(this.ResinChemicalTable.getModel().getValueAt(this.ResinChemicalTable.getSelectedRow(), 1).toString());
                    
                    ResinProgramTextBox.setForeground(Color.BLACK);
                    GPLTextField.setForeground(Color.BLACK);
                    //thisResinChemical.se(this.ColorTable.getModel().getValueAt(this.ColorTable.getSelectedRow(), 0).toString());
                    ResinProgramTextBox.setText(ResinProgramLabel.getText());
                    GPLTextField.setText(this.ResinChemicalTable.getModel().getValueAt(this.ResinChemicalTable.getSelectedRow(), 1).toString());
                    //thisResinChemical.setGPLValue(this.ResinChemicalTable.getModel().getValueAt(this.ResinChemicalTable.getSelectedRow(), 1));
                    model.removeRow(this.ResinChemicalTable.getSelectedRow());

                    ResinProgramTextBox.setEnabled(true);
                    GPLTextField.setEnabled(true);
                    ChemicalComboBox.setEnabled(true);

                    this.DeleteChemicalButton.setText("Cancel");
                    this.EditChemicalButton.setText("Save");
                    this.BackButton.setEnabled(false);
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
                    if(chemicalName != "Choose Chemical")
                    {                    
                        newResinChemical.setChemicalID(chemicalHandler.GetChemicalIDFromChemicalName(chemicalName));
                        newResinChemical.setGPLValue(Float.parseFloat(GPLTextField.getText()));
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
                        newResinChemical.setResinProgramID(resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProgramLabel.getText()));
                    }
                
                    resinChemicalHandler.AddNewResinChemical(newResinChemical);
                }
                
                
                
                GetUpdatedTable();
            }
        
    }//GEN-LAST:event_EditChemicalButtonActionPerformed

    private void DeleteChemicalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteChemicalButtonActionPerformed
        // TODO add your handling code here:
        
            if(DeleteChemicalButton.getText().equals("Cancel"))
            {
                GetUpdatedTable();

                ResinProgramTextBox.setText("Resin Program");
                GPLTextField.setText("GPL Value");
                ChemicalComboBox.setSelectedIndex(0);

                ResinProgramTextBox.setEnabled(false);
                GPLTextField.setEnabled(false);
                ChemicalComboBox.setEnabled(false);

                this.DeleteChemicalButton.setText("Delete");
                this.EditChemicalButton.setText("Edit");
                this.BackButton.setEnabled(true);
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

                    if(chemicalName != "Choose Chemical")
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
    private javax.swing.JButton BackButton;
    private javax.swing.JComboBox<String> ChemicalComboBox;
    private javax.swing.JButton DeleteChemicalButton;
    private javax.swing.JButton EditChemicalButton;
    private javax.swing.JTextField GPLTextField;
    private javax.swing.JLabel Header;
    private javax.swing.JTable ResinChemicalTable;
    private javax.swing.JLabel ResinProgramLabel;
    private javax.swing.JTextField ResinProgramTextBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
