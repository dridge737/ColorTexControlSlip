/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.JobOrder;
import DataEntities.ProcessOrder;
import DataEntities.ResinProgram;
import Handlers.ColorHandler;
import Handlers.ResinProgramHandler;
import Handlers.ResinChemicalHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;

/**
 *
 * @author Eldridge
 */
public class ViewResinProgramList extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    ResinProgram thisResin = new ResinProgram();
    //ProcessOrder thisProcessOrder;
    JobOrder thisJob;
    int WindowType = 1;
    /**
     * Creates new form ViewResinProgram
     */
    public ViewResinProgramList() {
        this(null,1);
    }
    
    
    public ViewResinProgramList(JobOrder currentJob) {
        this(currentJob, 3);
        //thisJob = currentJob;
        //SelectBut1.setText("Next");
        //DeleteBut.setVisible(false);
        //Header.setText("Control Slip : Page 4/6");
        //BackBut.setText("Back");
    }
    
    public ViewResinProgramList(JobOrder currentJob, int currentWindow)
    {
        initComponents();
        this.set_to_center();
        this.GetUpdatedTable();
        WindowType = currentWindow;
        
        if (WindowType != 1) {
            thisJob = currentJob;
            SelectBut1.setText("Next");
            DeleteBut.setVisible(false);
            BackBut.setText("Back");
            
            if (WindowType == 3) {
                Header.setText("Control Slip : Page 4/6");
            } else if (WindowType == 4) {
                Header.setText("Select Resin");
            }
        }

        
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
        ResinTable = new javax.swing.JTable();
        BackBut = new javax.swing.JButton();
        DeleteBut = new javax.swing.JButton();
        SearchTextBox = new javax.swing.JTextField();
        SelectBut1 = new javax.swing.JButton();
        NewBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Resin Program");
        BgPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 470, 40));
        Header.getAccessibleContext().setAccessibleName("ResinProgram");

        jScrollPane1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        ResinTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        ResinTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ResinTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ResinTable.setRowHeight(20);
        ResinTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(ResinTable);

        BgPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 125, 470, 230));

        BackBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        BackBut.setText("Close");
        BackBut.setToolTipText("");
        BackBut.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        BackBut.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BackBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButActionPerformed(evt);
            }
        });
        BgPanel.add(BackBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 370, 145, 42));

        DeleteBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        DeleteBut.setText("Delete");
        DeleteBut.setToolTipText("");
        DeleteBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButActionPerformed(evt);
            }
        });
        BgPanel.add(DeleteBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 145, 42));

        SearchTextBox.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
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
        BgPanel.add(SearchTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 360, 34));

        SelectBut1.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SelectBut1.setText("Select");
        SelectBut1.setToolTipText("");
        SelectBut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectBut1ActionPerformed(evt);
            }
        });
        BgPanel.add(SelectBut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 145, 42));

        NewBut.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        NewBut.setText("New");
        NewBut.setToolTipText("");
        NewBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewButActionPerformed(evt);
            }
        });
        BgPanel.add(NewBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 98, 34));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        this.ResetResinText();
    }//GEN-LAST:event_SearchTextBoxFocusLost

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
            = new TableRowSorter<>(this.ResinTable.getModel());
        
        this.ResinTable.setRowSorter(rowSorter);
        
        if (row_filter_text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + row_filter_text));        
        }
    }
    
    private void GetUpdatedTable()
    {
        model = getUpdatedResinTableModel();
        this.ResinTable.setModel(model);
    }
    
    public DefaultTableModel getUpdatedResinTableModel() {      
        
        DefaultTableModel model_original = new DefaultTableModel();
        model_original.addColumn("Program Name");
        
        ArrayList<String> ResinList = new ResinProgramHandler().GetAllResinProgram();
        for(int x=0; x<ResinList.size(); x++)
        {
            model_original.addRow(new Object[]{ResinList.get(x).toString()});
        }
        return model_original;
    }
    
    public void ResetResinText()
    {
        this.SearchTextBox.setText("Search :");
        SearchTextBox.setForeground(new Color(204,204,204));
    }
    
    private void SearchTextBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchTextBoxKeyReleased
        // TODO add your handling code here:
        UpdateRowFilter(this.SearchTextBox.getText());

    }//GEN-LAST:event_SearchTextBoxKeyReleased

    private void DeleteButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButActionPerformed
        if(ResinTable.getSelectedRowCount() > 0 )
        {
            int CloseorNoreply = JOptionPane.showConfirmDialog(null,"Delete this Program? "
                  ,"Delete Resin Program?", JOptionPane.YES_NO_OPTION);
            if(CloseorNoreply == JOptionPane.YES_OPTION)
            {
                String resinProgramName = this.ResinTable.getModel().getValueAt(this.ResinTable.getSelectedRow(), 0).toString();
                thisResin.setID(new ResinProgramHandler().GetResinProgramIDFromResinProgramName(resinProgramName));
                new ResinChemicalHandler().DeleteResinChemicalByResinProgramId(thisResin.getID());
                new ResinProgramHandler().DeleteResinProgram(thisResin.getID());
                this.GetUpdatedTable();
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Please select an Item in the table to be deleted.");
        }
    }//GEN-LAST:event_DeleteButActionPerformed
       
    private void SelectBut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectBut1ActionPerformed
        if(ResinTable.getSelectedRowCount() > 0)
        {
            int updatedRowNumber = ResinTable.convertRowIndexToModel(this.ResinTable.getSelectedRow());
            String resinProgramName = ResinTable.getModel().getValueAt(updatedRowNumber,0).toString();
            if(!resinProgramName.isEmpty())
            {
                if(SelectBut1.getText().equals("Next"))
                {
                    new AddResinForm(resinProgramName, thisJob, this.WindowType).setVisible(true);
                }
                else
                    new ViewResinProgramChemicals(resinProgramName).setVisible(true);
                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please select a resin program to be used.");
            }
        }
        
        
    }//GEN-LAST:event_SelectBut1ActionPerformed

    private void BackButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButActionPerformed
        // TODO add your handling code here:
        //Resin list window from review form
        if(WindowType == 4 || WindowType == 5)
        {
            new ReviewForm(thisJob, WindowType).setVisible(true);
        }
        //Resin List from Job order form
        else if(WindowType == 3)
        {
            new MachineSelection(thisJob).setVisible(true);
            //DyeingForm thisDyeingForm = new DyeingForm(thisJob);
            //thisDyeingForm.setVisible(true);
        }
        this.dispose();
        
    }//GEN-LAST:event_BackButActionPerformed

    private void NewButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewButActionPerformed
        // TODO add your handling code here:
        AddResinForm newResinForm  = new AddResinForm();
        newResinForm.setVisible(true);
        //this.dispose();
                
    }//GEN-LAST:event_NewButActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        GetUpdatedTable();
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
            java.util.logging.Logger.getLogger(ViewResinProgramList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewResinProgramList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewResinProgramList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewResinProgramList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewResinProgramList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackBut;
    private javax.swing.JPanel BgPanel;
    private javax.swing.JButton DeleteBut;
    private javax.swing.JLabel Header;
    private javax.swing.JButton NewBut;
    private javax.swing.JTable ResinTable;
    private javax.swing.JTextField SearchTextBox;
    private javax.swing.JButton SelectBut1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
