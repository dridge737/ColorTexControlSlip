/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.DyeingProgram;
import Handlers.DyeingProgramHandler;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Eldridge
 */
public class ViewDyeingProgram extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    DyeingProgram thisDyeingProgram = new DyeingProgram();
    
    /**
     * Creates new form ViewResinProgram
     */
    public ViewDyeingProgram() {
        initComponents();
        GetUpdatedTable();
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
        ResinHeader = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DyeingTable = new javax.swing.JTable();
        ResinLabel = new javax.swing.JLabel();
        BackBut = new javax.swing.JButton();
        SelectBut = new javax.swing.JButton();
        SearchTextBox = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search Dyeing Program");

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ResinHeader.setBackground(new java.awt.Color(255, 255, 255));
        ResinHeader.setFont(new java.awt.Font("Century Gothic", 0, 34)); // NOI18N
        ResinHeader.setForeground(new java.awt.Color(255, 255, 255));
        ResinHeader.setText("Dyeing Program List");
        BgPanel.add(ResinHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 19, 360, 40));
        ResinHeader.getAccessibleContext().setAccessibleName("ResinProgram");

        jScrollPane1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        DyeingTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        DyeingTable.setModel(new javax.swing.table.DefaultTableModel(
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
        DyeingTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DyeingTable.setRowHeight(20);
        DyeingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(DyeingTable);

        BgPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 123, 470, 200));

        ResinLabel.setBackground(new java.awt.Color(255, 255, 255));
        ResinLabel.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        ResinLabel.setForeground(new java.awt.Color(255, 255, 255));
        ResinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResinLabel.setText("Dyeing Program List");
        ResinLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        ResinLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ResinLabelMouseEntered(evt);
            }
        });
        BgPanel.add(ResinLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 85, 470, 40));

        BackBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        BackBut.setText("Back");
        BackBut.setToolTipText("");
        BgPanel.add(BackBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 220, 42));

        SelectBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SelectBut.setText("Next");
        SelectBut.setToolTipText("");
        SelectBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectButActionPerformed(evt);
            }
        });
        BgPanel.add(SelectBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 220, 42));

        SearchTextBox.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        SearchTextBox.setForeground(new java.awt.Color(204, 204, 204));
        SearchTextBox.setText("Search :");
        SearchTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTextBoxActionPerformed(evt);
            }
        });
        SearchTextBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SearchTextBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                SearchTextBoxFocusLost(evt);
            }
        });
        SearchTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchTextBoxKeyReleased(evt);
            }
        });
        BgPanel.add(SearchTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 85, 470, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchTextBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchTextBoxFocusGained
        // TODO add your handling code here:
        if(SearchTextBox.getText().equals("Search :"))
        {
            SearchTextBox.setText("");
            SearchTextBox.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_SearchTextBoxFocusGained

    private void SearchTextBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SearchTextBoxFocusLost
        // TODO add your handling code here:
        if(SearchTextBox.getText().equals(""))
        this.ResetDyeingText();
    }//GEN-LAST:event_SearchTextBoxFocusLost

    private void SearchTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchTextBoxActionPerformed

    private void UpdateRowFilter(String row_filter_text)
    {
        TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(this.DyeingTable.getModel());
        
        this.DyeingTable.setRowSorter(rowSorter);
        
        if (row_filter_text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + row_filter_text));        
        }
    }
    
    private void GetUpdatedTable()
    {
        model = getUpdatedTableModel();
        this.DyeingTable.setModel(model);
    }
    
    public DefaultTableModel getUpdatedTableModel() {      
        
        DefaultTableModel model_original = new DefaultTableModel();
        model_original.addColumn("Program Name");
        
        ArrayList<String> DyeingList = new DyeingProgramHandler().GetAllDyeingProgram();
        for (String DyeingList1 : DyeingList) {
            model_original.addRow(new Object[]{DyeingList1});
        }
        return model_original;
    }
    
    public void ResetDyeingText()
    {
        this.SearchTextBox.setText("Search :");
        SearchTextBox.setForeground(new Color(204,204,204));
    }
    
    private void SearchTextBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchTextBoxKeyReleased
        // TODO add your handling code here:
        UpdateRowFilter(this.SearchTextBox.getText());

    }//GEN-LAST:event_SearchTextBoxKeyReleased

    private void SelectButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectButActionPerformed
        // TODO add your handling code here:
        int selectedRow = this.DyeingTable.getSelectedRow();
       
        DyeingForm thisDyeingForm = new DyeingForm( DyeingTable.getModel().getValueAt(selectedRow, 0).toString(), 3);
        thisDyeingForm.setVisible(true);
    }//GEN-LAST:event_SelectButActionPerformed

    private void ResinLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResinLabelMouseEntered
        // TODO add your handling code here:
        this.ResinLabel.setVisible(false);
    }//GEN-LAST:event_ResinLabelMouseEntered

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
            java.util.logging.Logger.getLogger(ViewDyeingProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewDyeingProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewDyeingProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewDyeingProgram.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewDyeingProgram().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBut;
    private javax.swing.JPanel BgPanel;
    private javax.swing.JTable DyeingTable;
    private javax.swing.JLabel ResinHeader;
    private javax.swing.JLabel ResinLabel;
    private javax.swing.JTextField SearchTextBox;
    private javax.swing.JButton SelectBut;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
