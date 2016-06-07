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
        InitializeChemicalTable();
        InitializeGPLandPercentColumn();
        ChemicalsTable.getModel().addTableModelListener(newTableListener);
    }
    TableModelListener newTableListener = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
                // If editing row is last row in table add one more row to table
                if(ChemicalsTable.getEditingRow() == (ChemicalsTable.getRowCount()-1)){
                    Object chemical = ChemicalsTable.getModel().getValueAt(ChemicalsTable.getEditingRow(), 0);
                    Object gpl = ChemicalsTable.getModel().getValueAt(ChemicalsTable.getEditingRow(), 1);
                    Object value = ChemicalsTable.getModel().getValueAt(ChemicalsTable.getEditingRow(), 2);
                    Object quantity = ChemicalsTable.getModel().getValueAt(ChemicalsTable.getEditingRow(), 3);
                    if(gpl != null && chemical != null && value != null && quantity != null)
                    {
                        ((DefaultTableModel)ChemicalsTable.getModel()).addRow(new Object[]{});
                    }
                }
            }
    };
    
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
        TableColumn col = ChemicalsTable.getColumnModel().getColumn(column);
        col.setCellEditor(new DefaultCellEditor(comboBox));
     }
     
     public String GetSubProcessText()
     {
         return this.SubProcessText.getText();
     }
     
     public JTable GetChemicalTable()
     {
         return this.ChemicalsTable;
     }
     
     /**
      * Used to add Sub Process in case there are more process below the parent process
      * @param DyeingProgramID ID of the dyeing program that is used
      * @param Order Order String includes a number and a letter after
      */
     public void AddSubProcess(int DyeingProgramID, String Order)
     {
        if(this.SubProcessText.isVisible())
        {
            if(SubProcessText.getText().length()> 0)
            {
                DyeingProcess ThisDyeingProcess = new DyeingProcess();
                DyeingProcessHandler ThisDyeingProcessHandler = new DyeingProcessHandler();
                
                //SET All Dyeing Process Columns ID, NAME , ORDER
                ThisDyeingProcess.setDyeingProgramId(DyeingProgramID);
                ThisDyeingProcess.setDyeingProcessName(this.SubProcessText.getText());
                ThisDyeingProcess.setDyeingProcessOrder(Order);
                
                //USE Handler To Add Dyeing Process
                ThisDyeingProcessHandler.AddDyeingProcess(ThisDyeingProcess);
                ThisDyeingProcess.setDyeingProcessId(   
                        ThisDyeingProcessHandler.GetDyeingProcessIdByDetails(
                                ThisDyeingProcess));
                //Add Chemicals After Adding Dyeing Process
                AddChemicals(ThisDyeingProcess.getDyeingProcessId());
            }
        }
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
        
        for (int i = 0; i < ChemicalsTable.getRowCount() - 1; i++) {
            
            String Chemical = ChemicalsTable.getModel().getValueAt(i, 0).toString();
            String Type = ChemicalsTable.getModel().getValueAt(i, 1).toString();
            String Value = ChemicalsTable.getModel().getValueAt(i, 2).toString();
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
        ChemicalsTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        SubProcessLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        SubProcessLabel.setText("Sub Process Name :");

        ChemPanel1.setBackground(new java.awt.Color(255, 255, 255));
        ChemPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chemicals", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 16))); // NOI18N
        ChemPanel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        ChemicalsTable.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        ChemicalsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(SubProcessLabel)
                .addGap(12, 12, 12)
                .addComponent(SubProcessText, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ChemPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SubProcessLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(SubProcessText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(ChemPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChemPanel1;
    private javax.swing.JTable ChemicalsTable;
    private javax.swing.JLabel SubProcessLabel;
    private javax.swing.JTextField SubProcessText;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
