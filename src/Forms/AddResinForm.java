/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Forms.HelpForm.ComboBoxTableCellRenderer;
import Handlers.ResinChemicalHandler;
import Handlers.ResinProgramHandler;
import Handlers.ChemicalHandler;
import DataEntities.ResinChemical;
import DataEntities.ResinProgram;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import DataEntities.Chemical;
import DataEntities.JobOrder;
import DataEntities.JobOrderExtended;
import DataEntities.ResinProgramName;
import Forms.HelpForm.ButtonColumn;
import Forms.HelpForm.auto_complete;
import Handlers.ComputeHelper;
import Handlers.DesignHandler;
import Handlers.JobHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DropMode;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Eldridge
 */
public class AddResinForm extends javax.swing.JFrame {

    ArrayList<ResinChemical> resinChemicalList = new ArrayList<ResinChemical>();
    ResinProgram resinProgram = new ResinProgram();
    ResinProgramName resinProgramName = new ResinProgramName();
    CurrentTableModel model = new CurrentTableModel();
    //ProcessOrder thisProcessOrder = new ProcessOrder();
    JobOrderExtended thisJob = new JobOrderExtended();
    //Chemical List to see if it is possible to add chemical to Chem Table
    ArrayList<String> AllChemical = new ArrayList<String>();
    //Chemical List to see if the Chemical has already been added to the table.
    ArrayList<String> AddedChemicalList = new ArrayList<String>();
    int WindowType = 0;
    Color ColorError = new Color(232,228,42);
    /**
     * Creates new form ResinForm
     */
    public AddResinForm() {
        initComponents();
        //InitializeChemicalTable();
        EnterForwardTraversal();
        addChemicalTextBoxAutoComplete();
        setTableModel();
        AddDeleteColumn();  
        SetToCenter();
    }
    
    public AddResinForm(String ResinProgramName)
    {
        this(ResinProgramName, null, 1);
        this.SaveBut.setText("Update");
    }
    
    
    public AddResinForm(JobOrderExtended currentJob)
    {
        this();
        //Problem that will be encountered on the second resin window
        if(currentJob.getThisResinJob()!=null)
        {
            String ResinProgramName = new ResinProgramHandler().
                GetResinProgramNameFromResinProgramID(currentJob.getThisResinJob().get(currentJob.getThisResinJob().size()-1).getResinProgramID());
        InitializeWindowForControlSlip(ResinProgramName, currentJob);
            
        }
    }
    
    public AddResinForm(String ResinProgramName , JobOrderExtended currentJob)
    {
        this(ResinProgramName, currentJob, 0);
        //InitializeWindowForControlSlip(ResinProgramName, currentJob);
        //AddDeleteColumn(); 
        
    }
    
    public AddResinForm(String ResinProgramName , JobOrderExtended currentJob, int thisWindowType)
    {
        this();
        WindowType = thisWindowType;
        InitializeWindowForControlSlip(ResinProgramName, currentJob);
        AddDeleteColumn(); 
        
    }
    
    public void EnterForwardTraversal()
    {
        Set forwardkeys = new HashSet(BgPanel
        .getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));

        forwardkeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        BgPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardkeys);

    }
    
    public void SetToCenter()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
    
    public void InitializeWindowForControlSlip(String ResinProgramName, JobOrderExtended thisJobOrder)
    {
        thisJob = thisJobOrder;
        if (thisJobOrder != null) {
            Header.setText("Control Slip : Page 5/6");
            this.SaveBut.setText("Next");
            this.CancelBut.setText("Back");
            ResinProgramNameTextbox.setEnabled(false);
            ResinProgramNameTextbox.setBackground(Color.WHITE);
            ResinProgramNameTextbox.setForeground(Color.BLACK);
        }
        this.setResinProgramDetails(ResinProgramName);
        this.GetUpdatedTable();
        //this.WindowType = 1;
        
        //this.setResinChemicals(resinProgram.getID());
    }
    
    public void setResinChemicals(int resinId)
    {
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        resinChemicalList = resinChemicalHandler.GetResinChemicalsByResinProgramId(resinId);
    }
    
    public void setResinProgramDetails(String ResinProgramName)
    {
        ResinProgramNameTextbox.setText(ResinProgramName);
        
        if (thisJob != null) {
            resinProgram.setColorID(thisJob.getColorID());
            resinProgram.setCustomerID(thisJob.getCustomerID());
            resinProgram.setDesignID(thisJob.getDesignID());

            if (new ResinProgramHandler().CheckIfResinProgramNameExistsForThisCustomer(ResinProgramName, resinProgram)) {
                resinProgram = new ResinProgramHandler().GetResinProgramDetailsForThisResinAndCustomer(ResinProgramName, resinProgram);
            } else {
                resinProgram = new ResinProgramHandler().GetDefaultResinProgram(ResinProgramName);
            }
        } else {
            resinProgram = new ResinProgramHandler().GetDefaultResinProgram(ResinProgramName);
        }
        
        
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
                
                ((CurrentTableModel)table.getModel()).removeRow(modelRow);
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(ChemicalTable, delete, ChemicalTable.getColumnCount()-1);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }
    
    private void GetUpdatedTable()
    {
        model = getUpdatedResinTableModel();
        this.ChemicalTable.setModel(model);
    }
    
    public CurrentTableModel getUpdatedResinTableModel() {      
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        ChemicalHandler chemicalHandler = new ChemicalHandler();
        CurrentTableModel model_original = new CurrentTableModel();
        model_original.addColumn("Chemical Name");
        model_original.addColumn("State");
        model_original.addColumn("Type");
        model_original.addColumn("Value GPL");
        if(this.thisJob != null)
        model_original.addColumn("Quantity");
        model_original.addColumn("Delete");
        
        //int resinProgramId = resinProgramHandler.GetResinProgramIDFromResinProgramName(ResinProcessName.getText());
        resinChemicalList = resinChemicalHandler.GetResinChemicalsByResinProgramId(resinProgram.getID());
        
        for(int x=0; x<resinChemicalList.size(); x++)
        {
            String chemicalName = chemicalHandler.GetChemicalNameFromChemicalID(resinChemicalList.get(x).getChemicalID());
            if(thisJob==null)
                model_original.addRow(new Object[]{chemicalName, 
                resinChemicalList.get(x).getState(), 
                resinChemicalList.get(x).getType(), 
                resinChemicalList.get(x).getGPLValue(), 
                //ComputeQuantityFromWeightOrVol(ThisChemicalList.get(x).getType(), ThisChemicalList.get(x).getGPLValue())
                 "Delete"});
            else
                model_original.addRow(new Object[]{chemicalName, 
                resinChemicalList.get(x).getState(), 
                resinChemicalList.get(x).getType(), 
                resinChemicalList.get(x).getGPLValue(), 
                //ComputeQuantityFromWeightOrVol(ThisChemicalList.get(x).getType(), ThisChemicalList.get(x).getGPLValue())
                new ComputeHelper().ComputeResinQuantity(resinChemicalList.get(x), thisJob.getThisResinJob().get(0)),
                 "Delete"});
        }
        return model_original;
    }
   
    public void setTableModel()
    {
        String[] TableHeader = new String [] {
                "Chemical", "State", "Type" , "GPL" , "Delete"
            };
        model = new CurrentTableModel()
                //new Object [][] {
            //},
            //new String [] {
            //    "Chemical", "GPL", "State", "Type", "Delete"
            //})
                {
                
            
            @Override
            public boolean isCellEditable(int row, int column) {
            //Only the third column
                return column == 3;
            }
        };
        for(String Headers : TableHeader)
        model.addColumn(Headers);
        
        
        ChemicalTable.setDragEnabled(true);
        ChemicalTable.setDropMode(DropMode.INSERT_ROWS);
        ChemicalTable.setTransferHandler(new TableRowTransferHandler(ChemicalTable));
        ChemicalTable.setModel(model);
    }
    
    
    
    public void addChemicalTextBoxAutoComplete()
    {
        //Chemical allChemicals = new Chemical();
        ChemicalHandler ChemHandler = new ChemicalHandler();
        
        AllChemical = ChemHandler.GetAllChemical();
        auto_complete dropdownAutoComplete = new auto_complete();
        dropdownAutoComplete.setupAutoComplete(this.ChemicalTextfield, AllChemical);
        this.ChemicalTextfield.setColumns(30);
        ChemicalTextfield.setFocusTraversalKeysEnabled(false);
    
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
        TableColumn col = ChemicalTable.getColumnModel().getColumn(0);
        
        col.setCellEditor(new DefaultCellEditor(comboBox));
        
        //DefaultTableModel dtm = (DefaultTableModel) ChemicalTable.getModel();
        //dtm.setRowCount(1);
        
        ChemicalTable.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                // If editing row is last row in table add one more row to table
                if(ChemicalTable.getEditingRow() == (ChemicalTable.getRowCount()-1)){
                    Object chemical = ChemicalTable.getModel().getValueAt(ChemicalTable.getEditingRow(), 0);
                    Object gpl = ChemicalTable.getModel().getValueAt(ChemicalTable.getEditingRow(), 1);
                    if(gpl != null && chemical != null)
                    {
                        ((DefaultTableModel)ChemicalTable.getModel()).addRow(new Object[]{});
                    }
                }
            }            
        });
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
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ChemicalTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        ChemicalTextfield = new javax.swing.JTextField();
        GPLTextfield = new javax.swing.JTextField();
        AddtoTable = new javax.swing.JButton();
        StateComboBox = new javax.swing.JComboBox();
        TypeComboBox = new javax.swing.JComboBox();
        ResinProgramNameTextbox = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control Slip");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BgPanel.setBackground(new java.awt.Color(102, 102, 102));
        BgPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        Set forwardkeys = new HashSet(BgPanel
            .getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));

        forwardkeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        BgPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardkeys);

        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Resin Program");
        BgPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 22, 710, 50));

        SaveBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SaveBut.setText("Add Resin Process");
        SaveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButActionPerformed(evt);
            }
        });
        BgPanel.add(SaveBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 485, 240, 40));

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        CancelBut.setText("Cancel");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });
        BgPanel.add(CancelBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 485, 240, 40));
        CancelBut.getAccessibleContext().setAccessibleName("Add");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Resin Chemicals", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 22), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(51, 51, 51));
        jPanel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ChemicalTable.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        ChemicalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chemical", "GPL", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ChemicalTable.setOpaque(false);
        ChemicalTable.setRowHeight(25);
        ChemicalTable.setRowSelectionAllowed(false);
        ChemicalTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(ChemicalTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 81, 653, 228));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Chemical :");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 97, 30));
        jPanel3.add(ChemicalTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 40, 210, 30));

        GPLTextfield.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jPanel3.add(GPLTextfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 40, 110, 30));

        AddtoTable.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        AddtoTable.setForeground(new java.awt.Color(51, 51, 51));
        AddtoTable.setText("Add");
        AddtoTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddtoTableActionPerformed(evt);
            }
        });
        jPanel3.add(AddtoTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 40, 90, 30));

        StateComboBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        StateComboBox.setForeground(new java.awt.Color(51, 51, 51));
        StateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "G", "L" }));
        jPanel3.add(StateComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 40, 40, 30));

        TypeComboBox.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        TypeComboBox.setForeground(new java.awt.Color(51, 51, 51));
        TypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GPL", "%" }));
        jPanel3.add(TypeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 70, 30));

        jPanel1.add(jPanel3);
        jPanel3.setBounds(20, 60, 700, 320);

        ResinProgramNameTextbox.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        ResinProgramNameTextbox.setName(""); // NOI18N
        jPanel1.add(ResinProgramNameTextbox);
        ResinProgramNameTextbox.setBounds(245, 11, 470, 34);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Resin Program Name :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(23, 11, 215, 34);

        BgPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 740, 390));

        getContentPane().add(BgPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        if(CancelBut.getText().equals("Back"))
        {
            new ReviewFormV3(thisJob, WindowType).setVisible(true);
            //new ViewResinProgramList(thisJob,WindowType).setVisible(true);
            this.dispose();
        }
        else
        {
            if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to Cancel this Resin Program?","Cancel Resin Program?", JOptionPane.YES_NO_OPTION))
            {
                this.dispose();
            }
        }
    }//GEN-LAST:event_CancelButActionPerformed

    private void SaveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButActionPerformed
        // TODO add your handling code here:
        boolean isResinNewToCustomer = CheckIfResinNewToCustomer(resinProgram);
        int resinProgramId = -1;
        boolean OpenMachineSelection = false;
        if(ChemicalTable.isEditing())
            ChemicalTable.getCellEditor().stopCellEditing();
        
        if(SaveBut.getText().equals("Update"))
        //if (this.thisJob == null && WindowType != 0) 
        {
            this.UpdateResin();
        } 
        else if (WindowType != 0) {
            if (isResinNewToCustomer != true) {
                this.UpdateResin();
                resinProgramId = resinProgram.getID();
            } else {
                resinProgramId = AddResinWhenNewToCustomer();
            }
            
            if (resinProgramId != -1) {

                thisJob.getThisResinJob().get(thisJob.getThisResinJob().size() - 1).setResinProgramID(resinProgramId);
                if (new DesignHandler().GetDesignNameFromID(thisJob.getDesignID()).endsWith("NL")
                        && thisJob.getThisResinJob().size() < 2) {
                    //Message if users wants to add another Resin
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to include a second RESIN PROGRAM with this Job?", "Add 2nd Resin Program?", JOptionPane.YES_NO_OPTION)) {//Show the Resin Form
                        //IF a user wants to add Resin program to the job order
                        //Show Machine Selection Form
                        OpenMachineSelection = true;
                        
                    }
                }
                
                if(OpenMachineSelection)
                {
                    MachineSelection thisMachineSelection = new MachineSelection(thisJob, 3);
                    thisMachineSelection.setVisible(true);
                }
                else {
                    ReviewFormV3 thisForm = new ReviewFormV3(thisJob, this.WindowType);
                    thisForm.setVisible(true);
                }

                this.dispose();
            }
        } else {
            AddResin();
        }

 
    }//GEN-LAST:event_SaveButActionPerformed
/*
    private void UpdateResinProgramWhenNotNewToCustomer()
    {
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        ChemicalHandler chemicalHandler = new ChemicalHandler();
        int chemicalId = -1;
        boolean isSuccessful = false;
        
        ResinChemical resinChemical = new ResinChemical();
        
        if(resinProgram.getCustomerID() == 0)
        {
            for (int i = 0; i < ChemicalTable.getRowCount(); i++)
                {
                    if(this.CheckIfResinInputIsReady())
                    {
                        
                        this.AddFormRowOfValueToResinChemical(i, chemicalId);
                        //resinChemical
                        resinChemicalHandler.UpdateResinChemical(resinChemical);

                        
                    }
                }   
        }
    }
    */
    
    private boolean CheckIfResinNewToCustomer(ResinProgram resinProgramNameId)
    {
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        
        return resinProgramHandler.CheckIfResinProgramNameExistsForThisCustomer(ResinProgramNameTextbox.getText().trim(),resinProgramNameId);
        
    }
    
    private boolean CheckIfResinInputIsReady()
    {
        boolean Ready = true;
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        
        String thisProcessName = ResinProgramNameTextbox.getText().trim();
        
        if(this.ResinProgramNameTextbox.getText().trim().length() == 0 )
        {
            JOptionPane.showMessageDialog(null, "Please check the Resin process name.");
            ResinProgramNameTextbox.setBackground(ColorError);
            Ready = false;
        }
        else if(ChemicalTable.getRowCount() < 1)
        {
            JOptionPane.showMessageDialog(null, "Please add at least one chemical for this resin program");
            Ready = false;
        }
        
        if(this.resinProgram.getProgramNameID() != 0)
        {
            if (resinProgramHandler.CheckIfResinProgramNameExistsOnOtherID(thisProcessName, this.resinProgram.getProgramNameID()))
            {
                JOptionPane.showMessageDialog(null, "A Resin Program with the same name already exists on the system");
                Ready = false;
            }
                    
        }
        else{
            if (resinProgramHandler.CheckIfResinProgramNameExists(thisProcessName))
            {
                JOptionPane.showMessageDialog(null, "A Resin Program with the same name has already been added");
                  Ready = false;
            }
        }
        return Ready;
    }
    
    
    public void UpdateResin()
    {
        boolean isSuccessful;
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        String thisProcessName = ResinProgramNameTextbox.getText().trim();
        ResinChemical resinChemical = new ResinChemical();

        if (CheckIfResinInputIsReady()) {
            //resinProgramName.setName(thisProcessName);
            if(this.thisJob == null)
                resinProgramHandler.UpdateResinProgramName(thisProcessName,resinProgram.getProgramNameID());
            
            for (int i = 0; i < ChemicalTable.getRowCount(); i++) {

                //Set row value to resin chemical
                resinChemical = AddFormRowOfValueToResinChemical(i, resinProgram.getID());
                if (i < resinChemicalList.size()) {
                    resinChemical.setID(resinChemicalList.get(i).getID());
                    new ResinChemicalHandler().UpdateResinChemical(resinChemical);

                } else
                {
                    new ResinChemicalHandler().AddNewResinChemical(resinChemical);
                }
                
            }
            
            for (int x = ChemicalTable.getRowCount(); x < resinChemicalList.size(); x++) {
                new ResinChemicalHandler().DeleteResinChemical(resinChemicalList.get(x).getID());
            }
            
            JOptionPane.showMessageDialog(null, "Successfully updated the resin program");
            this.dispose();

        }
        
    }
    
        
    public void AddResin()
    {
        boolean isSuccessful = false;
        ResinChemical resinChemical = new ResinChemical();
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        ChemicalHandler chemicalHandler = new ChemicalHandler();
        int resinProgramNameId = -1;
        int resinProgramId = -1;
        String thisProcessName = ResinProgramNameTextbox.getText().trim();

        if (CheckIfResinInputIsReady()) {
            resinProgramName.setName(thisProcessName);

            resinProgramNameId = resinProgramHandler.AddNewResinProgramName(resinProgramName);

            if (resinProgramNameId != -1) {
                resinProgram.setProgramNameID(resinProgramNameId);
                resinProgram.setCustomerID(thisJob.getCustomerID());
                resinProgram.setColorID(thisJob.getColorID());
                resinProgram.setDesignID(thisJob.getDesignID());
                resinProgramId = resinProgramHandler.AddNewResinProgram(resinProgram);
            }
            
        }

        if (resinProgramId != -1) {
            for (int i = 0; i < ChemicalTable.getRowCount(); i++) {

                //Set row value to resin chemical
                resinChemical = AddFormRowOfValueToResinChemical(i, resinProgramId);
                isSuccessful = resinChemicalHandler.AddNewResinChemical(resinChemical);

                if (isSuccessful == false) {
                    break;
                }

            }

            if (isSuccessful == true) {
                JOptionPane.showMessageDialog(null, "The resin program has been successfully added");
                ClearAllData();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Saving has failed");
                resinChemicalHandler.DeleteResinChemicalByResinProgramId(resinProgramId);
                resinProgramHandler.DeleteResinProgram(resinProgramId);
                resinProgramHandler.DeleteResinProgramName(resinProgramNameId);
            }
        } else {
            //JOptionPane.showMessageDialog(null, "Please make sure the data are complete.");
        }

    }
    private ResinChemical AddFormRowOfValueToResinChemical(int row, int resinProgramId)
    {
       
        ResinChemical thisResinChemical = new ResinChemical();
        ChemicalHandler chemicalHandler = new ChemicalHandler();
        thisResinChemical.setResinProgramID(resinProgramId);
        
        String chemicalForResinProgram = ChemicalTable.getModel().getValueAt(row, 0).toString();
        int chemicalId = chemicalHandler.GetChemicalIDFromChemicalName(chemicalForResinProgram.toString());
        String gpl = ChemicalTable.getModel().getValueAt(row, 3).toString();
        if (CheckValueOfThisRow(chemicalId, gpl)) {
            thisResinChemical.setChemicalID(chemicalId);
            thisResinChemical.setState(ChemicalTable.getModel().getValueAt(row, 1).toString());
            thisResinChemical.setType(ChemicalTable.getModel().getValueAt(row, 2).toString());
            thisResinChemical.setGPLValue(Float.parseFloat(gpl.toString()));
            thisResinChemical.setOrder(row+1);
        }

        
        return thisResinChemical;
    }
    
    public boolean CheckValueOfThisRow(int chemicalId, String gpl) {
        boolean valid = false;
        if(chemicalId != -1 && gpl != null)
        {
            valid = true;
        }
        return valid;
    }
    public int AddResinWhenNewToCustomer()
    {
        boolean isSuccessful = false;
        ResinChemical resinChemical = new ResinChemical();
        Chemical chemicalName = new Chemical();
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        int resinProgramNameId = -1;
        int resinProgramId = -1;
        
        String thisProcessName = ResinProgramNameTextbox.getText().trim();

        resinProgramName.setName(thisProcessName);

        resinProgramNameId = resinProgramHandler.GetResinProgramNameIdFromResinProgramName(resinProgramName.getName());

        if (resinProgramNameId != -1) {
            resinProgram.setProgramNameID(resinProgramNameId);
            //Value has already been initialized at the start of the window
            resinProgram.setCustomerID(thisJob.getCustomerID());
            resinProgram.setColorID(thisJob.getColorID());
            resinProgram.setDesignID(thisJob.getDesignID());
            
            resinProgramId = resinProgramHandler.AddNewResinProgram(resinProgram);
        }
            //this.dispose();
        if(resinProgramId != -1)
        {
            for (int i = 0; i < ChemicalTable.getRowCount(); i++) {

                //Set row value to resin chemical
                resinChemical = AddFormRowOfValueToResinChemical(i, resinProgramId);

                isSuccessful = resinChemicalHandler.AddNewResinChemical(resinChemical);

                if (isSuccessful == false) {
                    break;
                }

            }

            if(isSuccessful == true)
            {
                JOptionPane.showMessageDialog(null, "The resin program has been successfully added");
                ClearAllData();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Saving has failed");
                resinChemicalHandler.DeleteResinChemicalByResinProgramId(resinProgramId);
                resinProgramHandler.DeleteResinProgram(resinProgramId);
                resinProgramHandler.DeleteResinProgramName(resinProgramNameId);
            }
        }
        return resinProgramId;
    }

    public void ClearAllData()
    {
        ResinProgramNameTextbox.setText("");
        resinChemicalList = new ArrayList<ResinChemical>();
        resinProgram = new ResinProgram();
        ((DefaultTableModel)ChemicalTable.getModel()).setRowCount(1);
        ChemicalTable.getModel().setValueAt("", 0, 0);
        ChemicalTable.getModel().setValueAt("", 0, 1);
    }
    
    private void AddtoTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddtoTableActionPerformed
        // TODO add your handling code here:
        if(!isNullOrWhitespaceOrEmpty(ChemicalTextfield.getText()) 
                && !isNullOrWhitespaceOrEmpty(GPLTextfield.getText())
                && !CheckTextIfItsANumber(GPLTextfield.getText()))
        {
            String chemicalTextFieldValue = ChemicalTextfield.getText().trim().toUpperCase();
            boolean validChemicalName = CheckIfChemicalisOnTable(chemicalTextFieldValue);
            if(!validChemicalName)
            {
                
                if(AllChemical.indexOf(chemicalTextFieldValue) == -1)
                {
                    if(JOptionPane.YES_OPTION == 
                        JOptionPane.showConfirmDialog(null, "This chemical name has not yet been added. Do you want to add it?","Add this chemical?", JOptionPane.YES_NO_OPTION))
                    {
                        Chemical thisChemical = new Chemical();
                        thisChemical.setChemicalName(chemicalTextFieldValue);
                        ChemicalHandler ChemHandler = new ChemicalHandler();
                        ChemHandler.AddNewChemical(thisChemical);
                        //End of adding Chemical to the database
                        AddTextToTable();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "This chemical has not yet been Added to the Database.");
                    }
                }
                else
                {
                    AddTextToTable();
                }
                
            }
            else
                JOptionPane.showMessageDialog(null, "This chemical has already been added to the Table.");
           
        }
        else
            JOptionPane.showMessageDialog(null, "Please input a chemical name and a chemical value");
        
    }//GEN-LAST:event_AddtoTableActionPerformed

    private void AddTextToTable()
    {
        String ChemicalName = ChemicalTextfield.getText().trim().toUpperCase();
        DefaultTableModel model = (DefaultTableModel) ChemicalTable.getModel();
        ResinChemical thisResinChemical = new ResinChemical();
        thisResinChemical.setGPLValue(Float.parseFloat(GPLTextfield.getText()));
        thisResinChemical.setState(StateComboBox.getSelectedItem().toString());
        thisResinChemical.setType(TypeComboBox.getSelectedItem().toString());
        //model.addRow(new Object[] {ChemicalName, this.StateBox.getSelectedItem() , this.TypeBox.getSelectedItem().toString(),GPLTextfield.getText(), "Delete"} );
        if((WindowType == 1 || WindowType == 3 || WindowType == 4 || WindowType == 5 || WindowType == 6 ) && this.thisJob!= null )
        {
            model.addRow(new Object[] {
                                        ChemicalName,
                                        thisResinChemical.getState(),
                                        thisResinChemical.getType(),
                                        thisResinChemical.getGPLValue(),
                                        //StateComboBox.getSelectedItem().toString(), 
                                        //TypeComboBox.getSelectedItem().toString(),
                                        //ComputeQuantityFromWeightOrVol(TypeComboBox.getSelectedItem().toString(), Float.parseFloat(GPLTextfield.getText())), 
                                        new ComputeHelper().ComputeResinQuantity(thisResinChemical, thisJob.getThisResinJob().get(thisJob.getThisResinJob().size()-1)),
                                        "Delete"});
        }
        else
            model.addRow(new Object[] {ChemicalTextfield.getText(), StateComboBox.getSelectedItem().toString(), TypeComboBox.getSelectedItem().toString(), GPLTextfield.getText() , "Delete"});
        //ChemicalList
        //After Adding Chemical to table add it to list to check if same chemical will be added
        this.AddedChemicalList.add(ChemicalName);
        this.ChemicalTextfield.setText(null);
        GPLTextfield.setText(null);
    }
    
    public boolean CheckIfChemicalisOnTable(String ChemicalName)
    {
        boolean ItIsOnTheTable = false;
        if(this.AddedChemicalList.indexOf(ChemicalName) != -1)
        {
            ItIsOnTheTable = true;
        }
        return ItIsOnTheTable;
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
            java.util.logging.Logger.getLogger(AddResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddResinForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddResinForm().setVisible(true);
            }
        });
    }

     public boolean CheckTextIfItsANumber(String this_text)
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
    private javax.swing.JPanel BgPanel;
    private javax.swing.JButton CancelBut;
    private javax.swing.JTable ChemicalTable;
    private javax.swing.JTextField ChemicalTextfield;
    private javax.swing.JTextField GPLTextfield;
    private javax.swing.JLabel Header;
    private javax.swing.JTextField ResinProgramNameTextbox;
    private javax.swing.JButton SaveBut;
    private javax.swing.JComboBox StateComboBox;
    private javax.swing.JComboBox TypeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}


