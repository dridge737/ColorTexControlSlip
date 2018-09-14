/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.JobOrder;
import DataEntities.JobOrderExtended;
import DataEntities.ResinJob;
import Handlers.JobHandler;
import Handlers.ResinJobHandler;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
        UseBut = new javax.swing.JButton();
        CancelBut = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JobOrderTable = new javax.swing.JTable();
        SearchBox = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");
        getContentPane().setLayout(null);

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Job Order");
        BgPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 22, 710, 50));

        UseBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        UseBut.setText("Use Job Order");
        UseBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UseButActionPerformed(evt);
            }
        });
        BgPanel.add(UseBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, 240, 40));

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        CancelBut.setText("Back");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });
        BgPanel.add(CancelBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, 240, 40));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Search :");
        BgPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 85, 100, 34));

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

        SearchBox.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SearchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchBoxKeyReleased(evt);
            }
        });
        BgPanel.add(SearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 85, 580, -1));

        getContentPane().add(BgPanel);
        BgPanel.setBounds(0, 0, 790, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UseButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UseButActionPerformed
        // TODO add your handling code here:
        if(JobOrderTable.getSelectedRowCount() > 0)
        {
            JobOrderExtended thisJob = new JobOrderExtended();
            int convertedRowNumber = JobOrderTable.convertRowIndexToModel(this.JobOrderTable.getSelectedRow());
            String DrNumber = JobOrderTable.getModel().getValueAt(convertedRowNumber , 0).toString();
            thisJob.setDrNumber(DrNumber);
            thisJob.setJobDate(JobOrderTable.getModel().getValueAt(convertedRowNumber , 4).toString());
            thisJob.setDyeingProgramID(Integer.parseInt(JobOrderTable.getModel().getValueAt(convertedRowNumber , 5).toString()));
            JobHandler thisJobOrderHandler = new JobHandler();
            thisJob = (JobOrderExtended) thisJobOrderHandler.GetJobOrderDetailsFromDrNumber(DrNumber);
            ResinJobHandler thisResinJobHandler = new ResinJobHandler();
            ResinJob thisResinJob = new ResinJob();
            thisResinJob.setJobOrderID(thisJob.getID());
            thisResinJob = thisResinJobHandler.GetResinJobFromJobID(thisResinJob);
            thisJob.getThisResinJob().add(thisResinJob);
            ReviewFormV2 orderReviewForm = new ReviewFormV2(thisJob, 3);
            orderReviewForm.setVisible(true);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please select a job order.");
        }

    }//GEN-LAST:event_UseButActionPerformed

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelButActionPerformed

    private void SearchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchBoxKeyReleased
        // TODO add your handling code here:
         update_row_filter(SearchBox.getText());
    }//GEN-LAST:event_SearchBoxKeyReleased

    private void update_row_filter(String row_filter_text)
    {
        TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(this.JobOrderTable.getModel());
        
        this.JobOrderTable.setRowSorter(rowSorter);
        
        if (row_filter_text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + row_filter_text));        
        }
    }
    
    private void GetUpdatedTable()
    {
        model = getUpdatedTableModel();
        this.JobOrderTable.setModel(model);
        JobOrderTable.removeColumn(JobOrderTable.getColumnModel().getColumn(10));
    }    
    
    public DefaultTableModel getUpdatedTableModel() {      
        
        DefaultTableModel model_original = new DefaultTableModel();
        model_original.addColumn("DR Number");
        model_original.addColumn("Design");
        model_original.addColumn("Color");
        model_original.addColumn("Customer");
        model_original.addColumn("Date");
        model_original.addColumn("Dyeing Program");
        model_original.addColumn("Resin Program");
        model_original.addColumn("Dyeing Machine");
        model_original.addColumn("Resin Machine");
        model_original.addColumn("ID");
        
        ArrayList<JobOrderExtended> JobOrderList = new JobHandler().GetAllExtendedJobOrderDetails();
        
        for(int x=0; x<JobOrderList.size(); x++)
        {
            model_original.addRow(new Object[]{JobOrderList.get(x).getDrNumber(), 
                                               JobOrderList.get(x).getDesignName(),
                                               JobOrderList.get(x).getColorName(),
                                               JobOrderList.get(x).getCustomerName(),
                                               JobOrderList.get(x).getJobDate(),
                                               JobOrderList.get(x).getDyeingProgramName(),
                                               JobOrderList.get(x).getResinProgramName(),
                                               JobOrderList.get(x).getDyeingMachineName(),
                                               JobOrderList.get(x).getResinMachineName(),
                                               JobOrderList.get(x).getDrNumber()
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
    private javax.swing.JTextField SearchBox;
    private javax.swing.JButton UseBut;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
