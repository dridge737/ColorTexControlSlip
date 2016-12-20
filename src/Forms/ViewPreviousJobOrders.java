/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.JobOrderExtended;
import Handlers.JobHandler;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eldridge
 */
public class ViewPreviousJobOrders extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    
    /**
     * Creates new form ViewPreviousJobOrders
     */
    public ViewPreviousJobOrders() {
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
        Header = new javax.swing.JLabel();
        SaveBut = new javax.swing.JButton();
        CancelBut = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JobOrderTable = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Job Order");
        BgPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 22, 710, 50));

        SaveBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SaveBut.setText("Use Job Order");
        SaveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButActionPerformed(evt);
            }
        });
        BgPanel.add(SaveBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, 240, 40));

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        CancelBut.setText("Cancel");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });
        BgPanel.add(CancelBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, 240, 40));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Search :");
        BgPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 85, 120, 34));

        JobOrderTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        JobOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Job Order", "Design", "Color", "Customer", "Date", "Design", "Dyeing Program", "Machine", "Resin Program"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JobOrderTable.setDropMode(javax.swing.DropMode.ON);
        JobOrderTable.setOpaque(false);
        JobOrderTable.setRowHeight(25);
        JobOrderTable.setRowSelectionAllowed(false);
        JobOrderTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(JobOrderTable);

        BgPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 710, 310));

        jTextField1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        BgPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 85, 590, -1));

        getContentPane().add(BgPanel);
        BgPanel.setBounds(0, 0, 790, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_SaveButActionPerformed

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelButActionPerformed

    private void GetUpdatedTable()
    {
        model = getUpdatedTableModel();
        this.JobOrderTable.setModel(model);
    }    
    
    public DefaultTableModel getUpdatedTableModel() {      
        
        DefaultTableModel model_original = new DefaultTableModel();
        model_original.addColumn("DR Number");
        model_original.addColumn("Design");
        model_original.addColumn("Color");
        model_original.addColumn("Customer");
        model_original.addColumn("Date");
        model_original.addColumn("Dyeing Program");
        model_original.addColumn("Machine");
        model_original.addColumn("Resin Program");
        
        ArrayList<JobOrderExtended> JobOrderList = new JobHandler().GetAllExtendedJobOrderDetails();
        
        for(int x=0; x<JobOrderList.size(); x++)
        {
            model_original.addRow(new Object[]{JobOrderList.get(x).getDrNumber(), 
                                               JobOrderList.get(x).getDesignName(),
                                               JobOrderList.get(x).getColorName(),
                                               JobOrderList.get(x).getCustomerName(),
                                               JobOrderList.get(x).getJobDate(),
                                               JobOrderList.get(x).getDyeingProgramName(),
                                               JobOrderList.get(x).getMachineName(),
                                               JobOrderList.get(x).getResinProgramName()
            });
        }
        return model_original;
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
            java.util.logging.Logger.getLogger(ViewPreviousJobOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPreviousJobOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPreviousJobOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPreviousJobOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPreviousJobOrders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BgPanel;
    private javax.swing.JButton CancelBut;
    private javax.swing.JLabel Header;
    private javax.swing.JTable JobOrderTable;
    private javax.swing.JButton SaveBut;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
