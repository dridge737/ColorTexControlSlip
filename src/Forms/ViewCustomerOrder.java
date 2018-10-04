/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.Customer;
import DataEntities.JobOrder;
import DataEntities.JobOrderExtended;
import Handlers.CustomerHandler;
import Handlers.JobHandler;
import Handlers.ResinJobHandler;
import Handlers.ResinProgramHandler;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Eldridge
 */
public class ViewCustomerOrder extends javax.swing.JFrame {

    Customer thisCustomer = new Customer();
    DefaultTableModel model = new DefaultTableModel();
    /**
     * Creates new form ViewCustomerOrder
     */
    public ViewCustomerOrder() {
        initComponents();
        populateCustomerDropDown();
        GetUpdatedTable();
        SetToCenter();
        
        JobOrderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
        UseOrder = new javax.swing.JButton();
        CancelBut = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JobOrderTable = new JTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };
        customerDropDown = new javax.swing.JComboBox();
        SearchLabel = new javax.swing.JLabel();
        SearchTextBox = new javax.swing.JTextField();
        ReviewBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Customer Order");
        BgPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 22, 710, 50));

        UseOrder.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        UseOrder.setText("Use Order");
        UseOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UseOrderActionPerformed(evt);
            }
        });
        BgPanel.add(UseOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 210, 40));

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        CancelBut.setText("Back");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });
        BgPanel.add(CancelBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 510, 210, 40));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Customer :");
        BgPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 85, 120, 34));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 22), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JobOrderTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        JobOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Job Order", "Design", "Color", "Date", "Dyeing Program Name", "Resin Program Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        JobOrderTable.setDropMode(javax.swing.DropMode.ON);
        JobOrderTable.setOpaque(false);
        JobOrderTable.setRowHeight(25);
        JobOrderTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(JobOrderTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 39, 710, 270));

        BgPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 730, 320));

        customerDropDown.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        customerDropDown.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Customer" }));
        customerDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerDropDownActionPerformed(evt);
            }
        });
        BgPanel.add(customerDropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 85, 570, 34));

        SearchLabel.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SearchLabel.setForeground(new java.awt.Color(255, 255, 255));
        SearchLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        SearchLabel.setText("Search  :");
        BgPanel.add(SearchLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 130, 120, 34));

        SearchTextBox.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        SearchTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchTextBoxKeyReleased(evt);
            }
        });
        BgPanel.add(SearchTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 570, 34));

        ReviewBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        ReviewBut.setText("Review Order");
        ReviewBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReviewButActionPerformed(evt);
            }
        });
        BgPanel.add(ReviewBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 510, 210, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void populateCustomerDropDown()
    {
        PopulateList(new CustomerHandler().GetAllCustomers(), customerDropDown);
    }
    
    private void PopulateList(ArrayList<String> thisList , JComboBox thisBox)
    {
        if(thisBox != null){
            for(int x=0; x<thisList.size(); x++)
            {
                thisBox.addItem(thisList.get(x));
            }
        }     
    }
    
    private void UseOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UseOrderActionPerformed
        // TODO add your handling code here:
        if(JobOrderTable.getSelectedRowCount() > 0)
        {
            int convertedRowNumber = JobOrderTable.convertRowIndexToModel(this.JobOrderTable.getSelectedRow());
            //String DrNumber = JobOrderTable.getModel().getValueAt(convertedRowNumber , 0).toString();
            int JobId = Integer.parseInt(JobOrderTable.getModel().getValueAt(convertedRowNumber , 9).toString());
            
            //JobOrder thisJob = thisJobOrderHandler.GetJobOrderDetailsFromDrNumber(DrNumber);
            
            //JobHandler thisJobOrderHandler = new JobHandler();
            JobOrderExtended thisJob =  new JobOrderExtended(new JobHandler().GetJobOrderDetailsFromJobOrderID(JobId));
            
            //JobOrderExtended thisJob = new JobOrderExtended(thisJobOrderHandler.GetJobOrderDetailsFromDrNumber(DrNumber));
            thisJob.setThisResinJob( new ResinJobHandler().GetResinJobListFromJobOrderID(thisJob.getID()));
            ReviewFormV2 orderReviewForm = new ReviewFormV2(thisJob, 4);
            orderReviewForm.setVisible(true);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please select a customer order.");
        }
    }//GEN-LAST:event_UseOrderActionPerformed

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelButActionPerformed

    private void customerDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerDropDownActionPerformed
        // TODO add your handling code here:
        Customer customerDetails = new Customer();
        CustomerHandler handler = new CustomerHandler();
        int customerId = -1;
        String customerName = "";
                
        if(!customerDropDown.getSelectedItem().toString().equals("Choose Customer"))
        {
            customerName = customerDropDown.getSelectedItem().toString();
            thisCustomer.setCustomerName(customerName);
        }        
        
        if(!customerName.equals(""))
        {
            customerId = handler.GetCustomerIDFromCustomerName(customerName);
            thisCustomer.setCustomerId(customerId);
        }  
        
        GetUpdatedTable();
    }//GEN-LAST:event_customerDropDownActionPerformed

    private void SearchTextBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchTextBoxKeyReleased
        // TODO add your handling code here:
        update_row_filter(SearchTextBox.getText());
    }//GEN-LAST:event_SearchTextBoxKeyReleased

    private void ReviewButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReviewButActionPerformed
        // TODO add your handling code here:
        if(JobOrderTable.getSelectedRowCount() > 0)
        {
            int convertedRowNumber = JobOrderTable.convertRowIndexToModel(this.JobOrderTable.getSelectedRow());
            //String DrNumber = JobOrderTable.getModel().getValueAt(convertedRowNumber , 0).toString();
            int JobId = Integer.parseInt(JobOrderTable.getModel().getValueAt(convertedRowNumber , 9).toString());
            
            JobHandler thisJobOrderHandler = new JobHandler();
            //JobOrder thisJob = thisJobOrderHandler.GetJobOrderDetailsFromDrNumber(DrNumber);
            
            JobOrderExtended thisJob =  new JobOrderExtended(thisJobOrderHandler.GetJobOrderDetailsFromJobOrderID(JobId));
            thisJob.setThisResinJob(new ResinJobHandler().GetResinJobListFromJobOrderID(thisJob.getID()));
            ReviewFormV2 orderReviewForm = new ReviewFormV2( thisJob, 6);
            orderReviewForm.setVisible(true);
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please select a customer order.");
        }
    }//GEN-LAST:event_ReviewButActionPerformed

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
        JobOrderTable.removeColumn(JobOrderTable.getColumnModel().getColumn(9));
    }    
    
    public DefaultTableModel getUpdatedTableModel() {      
        
        DefaultTableModel model_original = new DefaultTableModel();
        model_original.addColumn("Job Order");
        model_original.addColumn("Design");
        model_original.addColumn("Color");
        model_original.addColumn("Customer");
        model_original.addColumn("Date");
        model_original.addColumn("Dyeing Program");
        model_original.addColumn("Resin Program");
        model_original.addColumn("Dyeing Machine");
        model_original.addColumn("Resin Machine");
        //model_original.addColumn("Resin Machine 2");
        model_original.addColumn("ID");
        
        
        ArrayList<JobOrderExtended> JobOrderList = new JobHandler().GetExtendedJobOrderDetails(thisCustomer.getCustomerId());
                //new JobHandler().GetCustomerSpecificExtendedJobOrderDetails(thisCustomer.getCustomerId());
                //new JobHandler().GetAllExtendedJobOrderDetails();
        //ArrayList
        for (JobOrderExtended JobOrderList1 : JobOrderList) {
            model_original.addRow(new Object[]{
                JobOrderList1.getDrNumber().toString(), 
                JobOrderList1.getDesignName().toString(), 
                JobOrderList1.getColorName().toString(), 
                JobOrderList1.getCustomerName().toString(), 
                JobOrderList1.getJobDate().toString(),
                JobOrderList1.getDyeingProgramName().toString(),
                JobOrderList1.getResinProgramName().toString(), 
                JobOrderList1.getDyeingMachineName().toString(), 
                JobOrderList1.getResinMachineName().toString(),
                //JobOrderList1.getSecondResinMachineName().toString(),
                JobOrderList1.getID()
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
            java.util.logging.Logger.getLogger(ViewCustomerOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCustomerOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCustomerOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCustomerOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCustomerOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BgPanel;
    private javax.swing.JButton CancelBut;
    private javax.swing.JLabel Header;
    private javax.swing.JTable JobOrderTable;
    private javax.swing.JButton ReviewBut;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JTextField SearchTextBox;
    private javax.swing.JButton UseOrder;
    private javax.swing.JComboBox customerDropDown;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
