/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import DataEntities.Chemical;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Eldridge
 */
public class ResinForm extends javax.swing.JFrame {

    private int NumberOfTabs = 1;
    /**
     * Creates new form ResinForm
     */
    public ResinForm() {
        initComponents();
        InitializeChemicalTable();
        InitializeGPLandPercentColumn();
        /* add tab to add new tab when click */
        jTabbedPane1.add(new JPanel(), "+", NumberOfTabs++);
 
        jTabbedPane1.addChangeListener(changeListener);
    }
    
    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            addNewTab();
        }

    };
    
     private void addNewTab() {
        int index = NumberOfTabs - 1;
        if (jTabbedPane1.getSelectedIndex() == index) { /* if click new tab */
            /* add new tab */
            jTabbedPane1.add(new DyeingPanel(), "Process " + String.valueOf(NumberOfTabs),
                    index);
            /* set tab is custom tab */
            //jTabbedPane1.setTabComponentAt(index, new DemoCustomTab(this));
            jTabbedPane1.removeChangeListener(changeListener);
            jTabbedPane1.setSelectedIndex(index);
            jTabbedPane1.addChangeListener(changeListener);
            NumberOfTabs++;
        }
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
        TableColumn col = jTable1.getColumnModel().getColumn(0);
        col.setCellEditor(new DefaultCellEditor(comboBox));
        //col.setCellRenderer(renderer);
    }
    
   
    
    public void InitializeGPLandPercentColumn()
    {
        JComboBox comboBox = new JComboBox();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("GPL");
        model.addElement("%");
        comboBox.setModel(model);
        ComboBoxTableCellRenderer renderer = new ComboBoxTableCellRenderer();
        renderer.setModel(model);
        TableColumn col = jTable1.getColumnModel().getColumn(1);
        col.setCellEditor(new DefaultCellEditor(comboBox));
        //col.setCellRenderer(renderer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ChemicalHeader = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setText("Process Name :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 15, -1, 32));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 15, 116, 32));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chemicals", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 18))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Chemical", "Type", "Value", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 58, -1, -1));

        jTabbedPane1.addTab("Process 1", jPanel1);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 80, 740, -1));

        ChemicalHeader.setBackground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        ChemicalHeader.setForeground(new java.awt.Color(255, 255, 255));
        ChemicalHeader.setText("Add Dyeing Program");
        jPanel2.add(ChemicalHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 22, 360, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResinForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChemicalHeader;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}


