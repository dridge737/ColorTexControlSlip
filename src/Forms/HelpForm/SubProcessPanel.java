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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Eldridge
 */
public class SubProcessPanel extends javax.swing.JPanel {

    DyeingProcess ThisDyeingProcess = new DyeingProcess();
    int ChemicalColumn = 0;
    int StateColumn = 1;
    int TypeColumn = 2;
    int ValueColumn = 3;
    /**
     * Creates new form TrialPanel
     */
    public SubProcessPanel() {
        initComponents();
        addChemicalTextBoxAutoComplete();
        setTableModel(0);
        //InitializeChemicalTable();
        //InitializeGPLandPercentColumn();
        //ChemicalTable.getModel().addTableModelListener(newTableListener);   
         AddDeleteColumn();    
    }
    
    public SubProcessPanel(int DyeingProcessID){
        this();
        //initComponents();
        //addChemicalTextBoxAutoComplete();
        //setTableModel(1);
        //AddDeleteColumn();
        
        //TableColumn thisColumn = new TableColumn(ChemicalTable.getColumnCount()-2,50);
        //thisColumn.setHeaderValue("Quantity");
        //ChemicalTable.addColumn(thisColumn);  
        //ChemicalTable.removeColumn(thisColumn);
        SetSubProcessFromDyeingProgram(DyeingProcessID);
    }
    
    public SubProcessPanel(int DyeingProcessID, int type)
    {
        initComponents();
        addChemicalTextBoxAutoComplete();
        
        setTableModel(type);
        AddDeleteColumn();
        //TableColumn thisColumn = new TableColumn(ChemicalTable.getColumnCount()-2,50);
        //thisColumn.setHeaderValue("Quantity");
        //ChemicalTable.addColumn(thisColumn);  
        //ChemicalTable.removeColumn(thisColumn);
        SetSubProcessFromDyeingProgram(DyeingProcessID);
    }
    
    private void setTableModel(int type)
    {
        String[] TableHeader;
        int EditableCol;
        if(type == 1)
        {
            TableHeader = new String [] {
                "Chemical", "Gram/Liter", "Type", "Value" , "Quantity" ,"Delete"
            };
            EditableCol = 5;
        }
        else
        {
            TableHeader = new String [] {
                "Chemical", "Gram/Liter", "Type", "Value" ,"Delete"
            };
            EditableCol = 4;
        }
        
        DefaultTableModel tableModel = new DefaultTableModel( new Object [][] {
            }, TableHeader) {
                @Override
            public boolean isCellEditable(int row, int column) {
            //Only the third column
                return column == 4;
            }
        };
        
        ChemicalTable.setModel(tableModel);
    }
    
    public void AddDeleteColumn()
    {
        Action delete = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                if(JOptionPane.YES_OPTION == 
                        JOptionPane.showConfirmDialog(null, "Do you want to delete this row?","DELETE this item?", JOptionPane.YES_NO_OPTION))
                
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(ChemicalTable, delete, ChemicalTable.getColumnCount()-1);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
        
        //TableColumn thisColumn = new TableColumn(ChemicalTable.getColumnCount()-1,50 ,new ButtonRenderer(),  new ButtonEditor(new JCheckBox()));
        //thisColumn.setHeaderValue("Delete");
        //ChemicalTable.addColumn(thisColumn);
        //DefaultTableModel tableModel = (DefaultTableModel) ChemicalTable.getModel();
        //tableModel.addColumn("Delete");
        //ChemicalTable.setModel(tableModel);
        //ChemicalTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        //ChemicalTable.getColumn("Delete").setCellEditor( new ButtonEditor(new JCheckBox()));
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
             model.addRow(new Object[] {ChemicalName, thisDyeingChemical.getState(),thisDyeingChemical.getType(), thisDyeingChemical.getValue(), "Delete"});
         }
         this.ChemicalTable.setModel(model);
     }
     
     public String getChemicalNameFromID(int ChemicalID)
     {
         Chemical thisChemical = new Chemical();
         ChemicalHandler ChemicalHandler = new ChemicalHandler();
         thisChemical.setChemicalId(ChemicalID);
         thisChemical.setChemicalName(ChemicalHandler.GetChemicalNameFromChemicalID(thisChemical.getChemicalId()));
         return thisChemical.getChemicalName();
     }
    
    TableModelListener newTableListener = new TableModelListener() {
        public void tableChanged(TableModelEvent e) 
        {
            // If editing row is last row in table add one more row to table
            if(ChemicalTable.getEditingRow() == (ChemicalTable.getRowCount()-1))
            {
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
            if(SubProcessText.getText().length()> 0)
            {
                 //SET All Dyeing Process Columns ID, NAME , ORDER
                DyeingProcess ThisDyeingProcess = new DyeingProcess(DyeingProgramID,this.SubProcessText.getText(), Order );
                //ThisDyeingProcess.setDyeingProgramId(DyeingProgramID);
                //ThisDyeingProcess.setDyeingProcessName(this.SubProcessText.getText());
                //ThisDyeingProcess.setDyeingProcessOrder(Order);
                DyeingProcessHandler ThisDyeingProcessHandler = new DyeingProcessHandler();
               
                //USE Handler To Add Dyeing Process
                int DyeingProcessID = ThisDyeingProcessHandler.AddDyeingProcess(ThisDyeingProcess);
                ThisDyeingProcess.setId(   
                        ThisDyeingProcessHandler.GetDyeingProcessIdByDetails(
                                ThisDyeingProcess));
                //Add Chemicals After Adding Dyeing Process
                AddChemicals(ThisDyeingProcess.getId());
            }
     }
     
     /**
      * Add to Dyeing Chemicals Table using previously added Dyeing Process row
      * @param DyeingProcessID ID from Dyeing Process Table
      */
     public void AddChemicals(int DyeingProcessID)
     {
        //IF there is more than one sub-process
        DyeingChemical ThisDyeingChemical;
        DyeingChemicalHandler DyeingChemicalHandler = new DyeingChemicalHandler();
        
        for (int i = 0; i < ChemicalTable.getRowCount(); i++) {
            ThisDyeingChemical = GetThisRowOfValues(i, DyeingProcessID);
             if(ThisDyeingChemical != null)
                 DyeingChemicalHandler.AddNewDyeingChemical(ThisDyeingChemical);
            }
     }
     
     public void UpdateSubProcess(int DyeingProcessID, String Order)
     {
            if(SubProcessText.getText().length()> 0)
            {
                //SET All Dyeing Process Columns ID, NAME , ORDER
                DyeingProcess ThisDyeingProcess = new DyeingProcess(DyeingProcessID, this.SubProcessText.getText(), Order);
                //ThisDyeingProcess.setId(DyeingProcessID);
                //ThisDyeingProcess.setDyeingProcessName(this.SubProcessText.getText());
                //ThisDyeingProcess.setDyeingProcessOrder(Order);
                DyeingProcessHandler ThisDyeingProcessHandler = new DyeingProcessHandler();
                
                //USE Handler To Add Dyeing Process
                if(ThisDyeingProcessHandler.UpdateDyeingProcess(ThisDyeingProcess) != -1)
                //Add Chemicals After Adding Dyeing Process
                    UpdateChemicals(ThisDyeingProcess.getId());
            }
     }
     
     public void UpdateChemicals(int DyeingProcessID)
     {
        //DyeingChemical ThisDyeingChemical = new DyeingChemical();
        //int TotalNumberOfChemicals = DyeingChemicalHandler.CountDyeingChemicalForThisDyeingProcess(DyeingProcessID);
        //DECLARATION
        ChemicalHandler ChemicalHandler = new ChemicalHandler();
        DyeingChemicalHandler DyeingChemicalHandler = new DyeingChemicalHandler();
        
        //Get All Dyeing Chemicals
        ArrayList<DyeingChemical> ChemicalList = 
                DyeingChemicalHandler.GetAllDyeingChemicalFromDyeingProcessID(DyeingProcessID);
        
        for (int OrderNum = 0; OrderNum < ChemicalTable.getRowCount(); OrderNum++) {
            DyeingChemical ThisDyeingChemical = GetThisRowOfValues(OrderNum, DyeingProcessID);
            //IF Chemical Size is still within the number of row that has already been added
                if(OrderNum < ChemicalList.size())
                {
                    ThisDyeingChemical.setID(ChemicalList.get(OrderNum).getID());
                    DyeingChemicalHandler.UpdateDyeingChemical(ThisDyeingChemical);
                }
                else
                    DyeingChemicalHandler.AddNewDyeingChemical(ThisDyeingChemical);
                
        }
        //Delete All the Remaining Dyeing Chemical not included in the Update
        for(int LastRow = ChemicalTable.getRowCount(); LastRow < ChemicalList.size(); LastRow++ )
        {
            DyeingChemical ThisDyeingChemical = new DyeingChemical();
            ThisDyeingChemical.setID(ChemicalList.get(LastRow).getID());
            DyeingChemicalHandler.DeleteDyeingChemical(ThisDyeingChemical);
        }
     }
     
     private DyeingChemical GetThisRowOfValues(int rowNumber, int DyeingProcessID)
     {
         Chemical ThisChemical  = new Chemical();
         DyeingChemical ThisDyeingChemical = new DyeingChemical();
         ChemicalHandler ChemicalHandler = new ChemicalHandler();
         
         String Chemical = ChemicalTable.getModel().getValueAt(rowNumber, ChemicalColumn).toString();
         String Type = ChemicalTable.getModel().getValueAt(rowNumber, TypeColumn).toString();
         String Value = ChemicalTable.getModel().getValueAt(rowNumber, ValueColumn).toString();
         String State = ChemicalTable.getModel().getValueAt(rowNumber, StateColumn).toString();
            if(Chemical.length() > 0 && !CheckText(Value))
            {
                ThisChemical.setChemicalName(Chemical);
                ThisChemical.setChemicalId(ChemicalHandler.GetChemicalIDFromChemicalName(ThisChemical.getChemicalName()));
                ThisDyeingChemical.setChemicalID(rowNumber);
                ThisDyeingChemical.setChemicalID(ThisChemical.getChemicalId());
                ThisDyeingChemical.setDyeingProcessID(DyeingProcessID);
                ThisDyeingChemical.setType(Type);
                ThisDyeingChemical.setValue(Float.parseFloat(Value));
                ThisDyeingChemical.setOrder(rowNumber+1);
                ThisDyeingChemical.setState(State);
                //TO Be FIXED: How to know if this will be Solid or Liquid. G or L               
                //ThisDyeingChemical.setState(State);
            }
            else
                return null;
            
         return ThisDyeingChemical;
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
        StateBox = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(739, 400));

        SubProcessLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        SubProcessLabel.setText("Sub Process Name :");

        ChemPanel1.setBackground(new java.awt.Color(255, 255, 255));
        ChemPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chemicals", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 16))); // NOI18N
        ChemPanel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        ChemicalTable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ChemicalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chemical", "Type", "Value", "State", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ChemicalTable.setRowHeight(25);
        ChemicalTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

        StateBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        StateBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "G", "L" }));
        StateBox.setBorder(null);

        javax.swing.GroupLayout ChemPanel1Layout = new javax.swing.GroupLayout(ChemPanel1);
        ChemPanel1.setLayout(ChemPanel1Layout);
        ChemPanel1Layout.setHorizontalGroup(
            ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChemPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ChemPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChemicalTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(StateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GPLTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddtoTable, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ChemPanel1Layout.setVerticalGroup(
            ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChemPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(ChemPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ChemicalTextfield)
                    .addComponent(TypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(GPLTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddtoTable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StateBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(ChemPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SubProcessLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SubProcessText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if(!isNullOrWhitespaceOrEmpty(ChemicalTextfield.getText()) 
                && !isNullOrWhitespaceOrEmpty(GPLTextfield.getText())
                && !CheckText(GPLTextfield.getText()))
        {
            DefaultTableModel model = (DefaultTableModel) ChemicalTable.getModel();
            model.addRow(new Object[] {ChemicalTextfield.getText(), this.StateBox.getSelectedItem() , this.TypeBox.getSelectedItem().toString(),GPLTextfield.getText(), "Delete"} );
            this.ChemicalTextfield.setText(null);
            GPLTextfield.setText(null);
        }
        else
            JOptionPane.showMessageDialog(null, "Please check your Chemical Name input and GPL value.");
    }//GEN-LAST:event_AddtoTableActionPerformed

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
    
    public static boolean isNullOrWhitespaceOrEmpty(String s) {
        return s == null || isWhitespace(s) || s.length() == 0;
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
    private javax.swing.JPanel ChemPanel1;
    private javax.swing.JTable ChemicalTable;
    private javax.swing.JTextField ChemicalTextfield;
    private javax.swing.JTextField GPLTextfield;
    private javax.swing.JComboBox StateBox;
    private javax.swing.JLabel SubProcessLabel;
    private javax.swing.JTextField SubProcessText;
    private javax.swing.JComboBox TypeBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
