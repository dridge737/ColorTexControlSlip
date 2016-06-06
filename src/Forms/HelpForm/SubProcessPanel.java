/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.HelpForm;

import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Eldridge
 */
public class SubProcessPanel extends javax.swing.JPanel {

    /**
     * Creates new form TrialPanel
     */
    public SubProcessPanel() {
        initComponents();
        InitializeChemicalTable();
        InitializeGPLandPercentColumn();
    }
    
    public void InitializeChemicalTable()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ArrayList<String> AllChemical = new Handlers.ChemicalHandler().GetAllChemical();
        for(String thisChemical : AllChemical)
        {
            model.addElement(thisChemical);
        }
        InitializeColumns(model, 0);
        //col.setCellRenderer(renderer);
    }
    
     private void InitializeGPLandPercentColumn()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("GPL");
        model.addElement("%");
        ComboBoxTableCellRenderer renderer = new ComboBoxTableCellRenderer();
        renderer.setModel(model);
        
        InitializeColumns(model, 1);
        //col.setCellRenderer(renderer);
    }
     
     private void InitializeColumns(DefaultComboBoxModel model , int column)
     {
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(model);
        ComboBoxTableCellRenderer renderer = new ComboBoxTableCellRenderer();
        renderer.setModel(model);
        TableColumn col = ChemicalsTable.getColumnModel().getColumn(column);
        col.setCellEditor(new DefaultCellEditor(comboBox));
     }
     
     public String GetSubProcessText()
     {
         return this.SubProcessNameText.getText();
     }
     
     public JTable GetChemicalTable()
     {
         return this.ChemicalsTable;
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        SubProcessNameText = new javax.swing.JTextField();
        ChemPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ChemicalsTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("Sub Process Name :");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 15, -1, 32));
        add(SubProcessNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 17, 116, 32));

        ChemPanel1.setBackground(new java.awt.Color(255, 255, 255));
        ChemPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chemicals", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 16))); // NOI18N
        ChemPanel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        ChemicalsTable.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ChemicalsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ChemicalsTable.setRowHeight(25);
        jScrollPane1.setViewportView(ChemicalsTable);

        javax.swing.GroupLayout ChemPanel1Layout = new javax.swing.GroupLayout(ChemPanel1);
        ChemPanel1.setLayout(ChemPanel1Layout);
        ChemPanel1Layout.setHorizontalGroup(
            ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChemPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addContainerGap())
        );
        ChemPanel1Layout.setVerticalGroup(
            ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChemPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(ChemPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 58, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChemPanel1;
    private javax.swing.JTable ChemicalsTable;
    private javax.swing.JTextField SubProcessNameText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
