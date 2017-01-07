/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.DyeingProgram;
import DataEntities.JobOrder;
import DataEntities.ProcessOrder;
import Handlers.DyeingProgramHandler;
import Handlers.DyeingProgramNameHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
public class ViewDyeingProgram extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    DyeingProgram thisDyeingProgram = new DyeingProgram();
    //ProcessOrder thisProcessOrder = new ProcessOrder();
    private JobOrder thisJob = new JobOrder();
    int WindowType = 1;
    /**
     * Creates new form ViewResinProgram
     */
    public ViewDyeingProgram() {
        initComponents();
        SetToCenter();
        GetUpdatedTable();
    }
    
    public ViewDyeingProgram(JobOrder currentJob) {
        initComponents();
        SetToCenter();
        GetUpdatedTable();
        thisJob = currentJob;
        Header.setText("Dyeing Control Slip : Page 2/6");
        BackBut.setText("Back");
        SelectBut.setText("Next");
        WindowType = 3;
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

        BgPanel = new javax.swing.JPanel();
        Header = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DyeingTable = new javax.swing.JTable();
        BackBut = new javax.swing.JButton();
        SelectBut = new javax.swing.JButton();
        SearchTextBox = new javax.swing.JTextField();
        NewBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Color Text Control Slip");
        setPreferredSize(new java.awt.Dimension(545, 490));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setMinimumSize(new java.awt.Dimension(545, 469));
        BgPanel.setPreferredSize(new java.awt.Dimension(545, 480));
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Dyeing Program");
        BgPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 19, 470, -1));
        Header.getAccessibleContext().setAccessibleName("ResinProgram");

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
        DyeingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(DyeingTable);

        BgPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 123, 470, 280));

        BackBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        BackBut.setText("Cancel");
        BackBut.setToolTipText("");
        BackBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButActionPerformed(evt);
            }
        });
        BgPanel.add(BackBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 408, 220, 42));

        SelectBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SelectBut.setText("Select");
        SelectBut.setToolTipText("");
        SelectBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectButActionPerformed(evt);
            }
        });
        BgPanel.add(SelectBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 408, 220, 42));

        SearchTextBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        SearchTextBox.setForeground(new java.awt.Color(204, 204, 204));
        SearchTextBox.setText("Search :");
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
        BgPanel.add(SearchTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 330, 37));

        NewBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        NewBut.setText("New");
        NewBut.setToolTipText("");
        NewBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewButActionPerformed(evt);
            }
        });
        BgPanel.add(NewBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 128, 37));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
      
        ArrayList<String> DyeingList = new DyeingProgramNameHandler().GetAllDefaultDyeingProgramName();
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
        if(DyeingTable.getSelectedRowCount() > 0)
        {
            //int DyeingProgramID = new DyeingProgramHandler().GetDyeingProgramIDfromName(DyeingTable.getModel().getValueAt(selectedRow, 0).toString());
            int convertedRowNumber = DyeingTable.convertRowIndexToModel(this.DyeingTable.getSelectedRow());
            String DyeingName = DyeingTable.getModel().getValueAt(convertedRowNumber , 0).toString();
            DyeingForm thisDyeingForm;
            if(this.WindowType == 3)
            {
                thisDyeingForm = new DyeingForm(DyeingName, getThisJob());
            }
            else
                thisDyeingForm = new DyeingForm(DyeingName);
            thisDyeingForm.setVisible(true);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please select a Dyeing program to be used.");
        }
    }//GEN-LAST:event_SelectButActionPerformed

    private void BackButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButActionPerformed
        // TODO add your handling code here:
        if(WindowType == 3)
        {
            JobOrderForm newJobOrderForm = new JobOrderForm(getThisJob());
            newJobOrderForm.setVisible(true);
        }
        this.dispose();
        
    }//GEN-LAST:event_BackButActionPerformed

    private void NewButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewButActionPerformed
        // TODO add your handling code here:
        DyeingForm newAddDyeingForm = new DyeingForm();
        newAddDyeingForm.setVisible(true);
        //this.dispose();
        
                
    }//GEN-LAST:event_NewButActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        getUpdatedTableModel();
    }//GEN-LAST:event_formWindowGainedFocus

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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JLabel Header;
    private javax.swing.JButton NewBut;
    private javax.swing.JTextField SearchTextBox;
    private javax.swing.JButton SelectBut;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the thisJob
     */
    public JobOrder getThisJob() {
        return thisJob;
    }

    /**
     * @param thisJob the thisJob to set
     */
    public void setThisJob(JobOrder thisJob) {
        this.thisJob = thisJob;
    }

   
}
