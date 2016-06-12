/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.HelpForm;

import DataEntities.Chemical;
import DataEntities.DyeingChemical;
import DataEntities.DyeingProcess;
import Handlers.ChemicalHandler;
import Handlers.DyeingChemicalHandler;
import Handlers.DyeingProcessHandler;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
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
        addChemicalTextBoxAutoComplete();
        //InitializeChemicalTable();
        //InitializeGPLandPercentColumn();
        //ChemicalTable.getModel().addTableModelListener(newTableListener);
        
    }
    
    public void removeQuantityColumn()
    {
        DefaultTableModel DefaultTable = (DefaultTableModel) this.ChemicalTable.getModel();
        DefaultTable.setColumnCount(3);
    }
    
    
    TableModelListener newTableListener = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
                // If editing row is last row in table add one more row to table
                if(ChemicalTable.getEditingRow() == (ChemicalTable.getRowCount()-1)){
                    Object chemical = ChemicalTable.getModel().getValueAt(ChemicalTable.getEditingRow(), 0);
                    Object gpl = ChemicalTable.getModel().getValueAt(ChemicalTable.getEditingRow(), 1);
                    Object value = ChemicalTable.getModel().getValueAt(ChemicalTable.getEditingRow(), 2);
                    Object quantity = ChemicalTable.getModel().getValueAt(ChemicalTable.getEditingRow(), 3);
                    if(gpl != null && chemical != null && value != null && quantity != null)
                    {
                        ((DefaultTableModel)ChemicalTable.getModel()).addRow(new Object[]{});
                    }
                }
            }
    };
    
    public void addChemicalTextBoxAutoComplete()
    {
        //Chemical allChemicals = new Chemical();
        ChemicalHandler ChemHandler = new ChemicalHandler();
        ArrayList<String> AllChemical = ChemHandler.GetAllChemical();
        auto_complete dropdownAutoComplete = new auto_complete();
        dropdownAutoComplete.setupAutoComplete(this.ChemicalTextfield, AllChemical);
        this.ChemicalTextfield.setColumns(30);
    
    }
    
    public void HideText()
    {
        this.SubProcessLabel.setVisible(false);
        this.SubProcessText.setVisible(false);
        //this.ChemPanel1.setLocation(1, 1);
    }
    
    public void showText()
    {
        this.SubProcessLabel.setVisible(true);
        this.SubProcessText.setVisible(true);
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
        TableColumn col = ChemicalTable.getColumnModel().getColumn(column);
        col.setCellEditor(new DefaultCellEditor(comboBox));
     }
     
     /**
      * Used to add Sub Process in case there are more process below the parent process
      * @param DyeingProgramID ID of the dyeing program that is used
      * @param Order Order String includes a number and a letter after
      */
     public void AddSubProcess(int DyeingProgramID, String Order)
     {
        //if(this.SubProcessText.isVisible())
        //{
            if(SubProcessText.getText().length()> 0)
            {
                DyeingProcess ThisDyeingProcess = new DyeingProcess();
                DyeingProcessHandler ThisDyeingProcessHandler = new DyeingProcessHandler();
                
                //SET All Dyeing Process Columns ID, NAME , ORDER
                ThisDyeingProcess.setDyeingProgramId(DyeingProgramID);
                ThisDyeingProcess.setDyeingProcessName(this.SubProcessText.getText());
                ThisDyeingProcess.setDyeingProcessOrder(Order);
                
                //USE Handler To Add Dyeing Process
                int DyeingProcessID = ThisDyeingProcessHandler.AddDyeingProcess(ThisDyeingProcess);
                ThisDyeingProcess.setDyeingProcessId(   
                        ThisDyeingProcessHandler.GetDyeingProcessIdByDetails(
                                ThisDyeingProcess));
                //Add Chemicals After Adding Dyeing Process
                AddChemicals(ThisDyeingProcess.getDyeingProcessId());
            }
        //}
     }
     
     /**
      * Add to Dyeing Chemicals Table using previously added Dyeing Process row
      * @param DyeingProcessID ID from Dyeing Process Table
      */
     public void AddChemicals(int DyeingProcessID)
     {
        //IF there is more than one sub-process
        Chemical ThisChemical  = new Chemical();
        DyeingChemical ThisDyeingChemical = new DyeingChemical();
        ChemicalHandler ChemicalHandler = new ChemicalHandler();
        DyeingChemicalHandler DyeingChemicalHandler = new DyeingChemicalHandler();
        int Order = 1;
        
        for (int i = 0; i < ChemicalTable.getRowCount(); i++) {
            
            String Chemical = ChemicalTable.getModel().getValueAt(i, 0).toString();
            String Type = ChemicalTable.getModel().getValueAt(i, 1).toString();
            String Value = ChemicalTable.getModel().getValueAt(i, 2).toString();
            if(Chemical.length() > 0 && Type.length() > 0 && !CheckText(Value))
            {
                ThisChemical.setChemicalName(Chemical);
                ThisChemical.setChemicalId(ChemicalHandler.GetChemicalIDFromChemicalName(ThisChemical.getChemicalName()));
                
                ThisDyeingChemical.setChemicalID(ThisChemical.getChemicalId());
                ThisDyeingChemical.setDyeingProcessID(DyeingProcessID);
                ThisDyeingChemical.setType(Type);
                ThisDyeingChemical.setValue(Float.parseFloat(Value));
                ThisDyeingChemical.setOrder(Order++);
                DyeingChemicalHandler.AddNewDyeingChemical(ThisDyeingChemical);
            }
        }
     }
     
     public void SetSubProcessFromDyeingProgram(int SubProcessID)
     {
         DyeingProcess ThisDyeingProcess;
         DyeingProcessHandler ThisDyeingProcessHandler = new DyeingProcessHandler();
         ThisDyeingProcess = ThisDyeingProcessHandler.GetDyeingProcessDetailsById(SubProcessID);
         this.SubProcessText.setText(ThisDyeingProcess.getDyeingProcessName());
         SetChemicalListFromDyeingProcessID(SubProcessID);
       
     }
     
     public void SetChemicalListFromDyeingProcessID(int DyeingProcessID)
     {
         ArrayList<DyeingChemical> ThisDyeingChemical;
         DyeingChemicalHandler ThisDyeingChemicalHandler = new DyeingChemicalHandler();
         DefaultTableModel model = (DefaultTableModel) ChemicalTable.getModel();
         ThisDyeingChemical = ThisDyeingChemicalHandler.GetAllDyeingChemicalFromDyeingProcessID(DyeingProcessID);
         
         for(DyeingChemical thisDyeingChemical : ThisDyeingChemical)
         {
             String ChemicalName = getChemicalNameFromID(thisDyeingChemical.getChemicalID());
             model.addRow(new Object[] {ChemicalName, thisDyeingChemical.getType(), thisDyeingChemical.getValue()});
         }
                 
     }
     
     public String getChemicalNameFromID(int ChemicalID)
     {
         Chemical thisChemical = new Chemical();
         ChemicalHandler ChemicalHandler = new ChemicalHandler();
         thisChemical.setChemicalId(ChemicalID);
         thisChemical.setChemicalName(ChemicalHandler.GetChemicalNameFromChemicalID(thisChemical.getChemicalId()));
         return thisChemical.getChemicalName();
     }

     /**
      * Checks if text is a valid int or float variable
      * @param this_text String to be checked
      * @return true if String is either empty or text is not a valid int or float variable
      */
     public boolean CheckText(String this_text)
    {
        if(this_text.isEmpty())
            return true;
        String regex = "[^0-9]";
        Pattern p = Pattern.compile(regex);
        this_text = this_text.replaceFirst("[.]", "");
        
        return p.matcher(this_text).find();
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SubProcessLabel = new javax.swing.JLabel();
        SubProcessText = new javax.swing.JTextField();
        ChemPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ChemicalTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        ChemicalTextfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        GPLTextfield = new javax.swing.JTextField();
        AddtoTable = new javax.swing.JButton();
        TypeBox = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));

        SubProcessLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        SubProcessLabel.setText("Sub Process Name :");

        ChemPanel1.setBackground(new java.awt.Color(255, 255, 255));
        ChemPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chemicals", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 16))); // NOI18N
        ChemPanel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        ChemicalTable.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ChemicalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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
        ChemicalTable.setEnabled(false);
        ChemicalTable.setRowHeight(25);
        jScrollPane1.setViewportView(ChemicalTable);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("Chemical :");

        ChemicalTextfield.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Value :");

        GPLTextfield.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        AddtoTable.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        AddtoTable.setText("Add");
        AddtoTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddtoTableActionPerformed(evt);
            }
        });

        TypeBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TypeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GPL", "%" }));
        TypeBox.setBorder(null);

        javax.swing.GroupLayout ChemPanel1Layout = new javax.swing.GroupLayout(ChemPanel1);
        ChemPanel1.setLayout(ChemPanel1Layout);
        ChemPanel1Layout.setHorizontalGroup(
            ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChemPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChemPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChemicalTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(GPLTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddtoTable, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        ChemPanel1Layout.setVerticalGroup(
            ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChemPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ChemicalTextfield)
                    .addGroup(ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AddtoTable, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(GPLTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(TypeBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SubProcessLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SubProcessText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ChemPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubProcessText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubProcessLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(ChemPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddtoTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddtoTableActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) ChemicalTable.getModel();
        model.addRow(new Object[] {ChemicalTextfield.getText(), this.TypeBox.getSelectedItem().toString(),GPLTextfield.getText()});
        this.ChemicalTextfield.setText(null);
        GPLTextfield.setText(null);
    }//GEN-LAST:event_AddtoTableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddtoTable;
    private javax.swing.JPanel ChemPanel1;
    private javax.swing.JTable ChemicalTable;
    private javax.swing.JTextField ChemicalTextfield;
    private javax.swing.JTextField GPLTextfield;
    private javax.swing.JLabel SubProcessLabel;
    private javax.swing.JTextField SubProcessText;
    private javax.swing.JComboBox TypeBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
