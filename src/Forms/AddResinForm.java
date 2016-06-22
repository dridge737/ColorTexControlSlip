/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Forms.HelpForm.ComboBoxTableCellRenderer;
import Handlers.ResinChemicalHandler;
import Handlers.ResinProgramHandler;
import Handlers.ChemicalHandler;
import DataEntities.Chemical;
import DataEntities.ResinChemical;
import DataEntities.ResinProgram;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import DataEntities.Chemical;
import Forms.HelpForm.auto_complete;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Eldridge
 */
public class AddResinForm extends javax.swing.JFrame {

    ArrayList<ResinChemical> resinChemicalList = new ArrayList<ResinChemical>();
    ResinProgram resinProgram = new ResinProgram();
    /**
     * Creates new form ResinForm
     */
    public AddResinForm() {
        initComponents();
        //InitializeChemicalTable();
        addChemicalTextBoxAutoComplete();
        
    }
    
    public void addChemicalTextBoxAutoComplete()
    {
        //Chemical allChemicals = new Chemical();
        ChemicalHandler ChemHandler = new ChemicalHandler();
        ArrayList<String> AllChemical = ChemHandler.GetAllChemical();
        auto_complete dropdownAutoComplete = new auto_complete();
        dropdownAutoComplete.setupAutoComplete(this.ChemicalTextfield, AllChemical);
        this.ChemicalTextfield.setColumns(30);
    
    }
    
    public void InitializeChemicalTable()
    {
        JComboBox comboBox = new JComboBox();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ArrayList<String> AllChemical = new Handlers.ChemicalHandler().GetAllChemical();
        for(String thisChemical : AllChemical)
        {
            model.addElement(thisChemical);
        }
        comboBox.setModel(model);
        ComboBoxTableCellRenderer renderer = new ComboBoxTableCellRenderer();
        renderer.setModel(model);
        TableColumn col = ChemicalTable.getColumnModel().getColumn(0);
        
        col.setCellEditor(new DefaultCellEditor(comboBox));
        
        //DefaultTableModel dtm = (DefaultTableModel) ChemicalTable.getModel();
        //dtm.setRowCount(1);
        
        ChemicalTable.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                // If editing row is last row in table add one more row to table
                if(ChemicalTable.getEditingRow() == (ChemicalTable.getRowCount()-1)){
                    Object chemical = ChemicalTable.getModel().getValueAt(ChemicalTable.getEditingRow(), 0);
                    Object gpl = ChemicalTable.getModel().getValueAt(ChemicalTable.getEditingRow(), 1);
                    if(gpl != null && chemical != null)
                    {
                        ((DefaultTableModel)ChemicalTable.getModel()).addRow(new Object[]{});
                    }
                }
            }            
        });
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
        jLabel3 = new javax.swing.JLabel();
        processText = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ChemicalTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        ChemicalTextfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        GPLTextfield = new javax.swing.JTextField();
        AddtoTable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Resin Program");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ChemicalHeader.setBackground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        ChemicalHeader.setForeground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setText("Resin Program");
        BgPanel.add(ChemicalHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 22, 360, 50));

        SaveBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SaveBut.setText("Add Resin Process");
        SaveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButActionPerformed(evt);
            }
        });
        BgPanel.add(SaveBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 500, 240, 40));

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        CancelBut.setText("Cancel");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });
        BgPanel.add(CancelBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 500, 240, 40));
        CancelBut.getAccessibleContext().setAccessibleName("Add");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Resin Process Name :");
        BgPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 34));

        processText.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        processText.setName(""); // NOI18N
        BgPanel.add(processText, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 90, 440, 34));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resin Chemicals", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 22), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel3.setOpaque(false);

        ChemicalTable.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        ChemicalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chemical", "GPL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ChemicalTable.setEnabled(false);
        ChemicalTable.setOpaque(false);
        ChemicalTable.setRowHeight(25);
        ChemicalTable.setRowSelectionAllowed(false);
        ChemicalTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(ChemicalTable);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Chemical :");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("GPL :");

        AddtoTable.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        AddtoTable.setText("Add");
        AddtoTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddtoTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChemicalTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GPLTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddtoTable, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ChemicalTextfield)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GPLTextfield)
                    .addComponent(AddtoTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        BgPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 145, -1, -1));

        getContentPane().add(BgPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelButActionPerformed

    private void SaveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButActionPerformed
        // TODO add your handling code here:
        boolean isSuccessful = false;
        ResinChemical resinChemical = new ResinChemical();
        Chemical chemicalName = new Chemical();
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        ChemicalHandler chemicalHandler = new ChemicalHandler();
        int resinProgramId = -1;
        int chemicalId = -1;
        
        if(processText.getText().length()>0 && !isNullOrWhitespace(processText.getText()))
        {
            resinProgram.setName(processText.getText());
            resinProgramHandler.AddNewResinProgram(resinProgram);
            resinProgramId = resinProgramHandler.GetResinProgramIDFromResinProgramName(resinProgram.getName());
        }
        
        if(resinProgramId != -1)
        {
            for (int i = 0; i < ChemicalTable.getRowCount(); i++) {
                Object chemicalForResinProgram = ChemicalTable.getModel().getValueAt(i, 0);
                Object gpl = ChemicalTable.getModel().getValueAt(i, 1);

                if(chemicalForResinProgram != null)
                {
                   chemicalId = chemicalHandler.GetChemicalIDFromChemicalName(chemicalForResinProgram.toString());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please enter a name for the resin program.");
                }

                if(chemicalForResinProgram != null && gpl != null)
                {
                    resinChemical.setResinProgramID(resinProgramId);
                    resinChemical.setChemicalID(chemicalId);
                    resinChemical.setGPLValue(Float.parseFloat(gpl.toString()));

                    isSuccessful = resinChemicalHandler.AddNewResinChemical(resinChemical);
                    if(isSuccessful == false)
                    {
                        break;
                    }                    
                }     
                else if((chemicalForResinProgram == null && gpl != null) || (chemicalForResinProgram != null && gpl == null))
                {
                    JOptionPane.showMessageDialog(null, "Please complete data for all rows.");
                }
             }   
            
            if(isSuccessful == true)
            {
                JOptionPane.showMessageDialog(null, "The resin program has been successfully added");
                ClearAllData();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Saving has failed");
                resinChemicalHandler.DeleteResinChemicalByResinProgramId(resinProgramId);
                resinProgramHandler.DeleteResinProgram(resinProgramId);
                ClearAllData();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please make sure the data are complete.");
        }
            
    }//GEN-LAST:event_SaveButActionPerformed

    public void ClearAllData()
    {
        processText.setText("");
        resinChemicalList = new ArrayList<ResinChemical>();
        resinProgram = new ResinProgram();
        ((DefaultTableModel)ChemicalTable.getModel()).setRowCount(1);
        ChemicalTable.getModel().setValueAt("", 0, 0);
        ChemicalTable.getModel().setValueAt("", 0, 1);
    }
    
    private void AddtoTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddtoTableActionPerformed
        // TODO add your handling code here:
        String chemicalName;
        String chemicalTextFieldValue = ChemicalTextfield.getText();
        boolean validChemicalName = true;
        for (int i = 0; i < ChemicalTable.getRowCount(); i++) {
            chemicalName = ChemicalTable.getModel().getValueAt(i, 0).toString();
            
            if(chemicalName.equals(chemicalTextFieldValue))
            {
                validChemicalName = false;
                break;
            }
          }
        
        if(validChemicalName)
        {
            DefaultTableModel model = (DefaultTableModel) ChemicalTable.getModel();
            model.addRow(new Object[] {ChemicalTextfield.getText(), GPLTextfield.getText()});
            this.ChemicalTextfield.setText(null);
            GPLTextfield.setText(null);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "This chemical is already added.");
        }
        
    }//GEN-LAST:event_AddtoTableActionPerformed

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
            java.util.logging.Logger.getLogger(AddResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddResinForm().setVisible(true);
            }
        });
    }

    
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrWhitespace(String s) {
        return s == null || isWhitespace(s);

    }
    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                if (!Character.isWhitespace(c)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddtoTable;
    private javax.swing.JPanel BgPanel;
    private javax.swing.JButton CancelBut;
    private javax.swing.JLabel ChemicalHeader;
    private javax.swing.JTable ChemicalTable;
    private javax.swing.JTextField ChemicalTextfield;
    private javax.swing.JTextField GPLTextfield;
    private javax.swing.JButton SaveBut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField processText;
    // End of variables declaration//GEN-END:variables
}

