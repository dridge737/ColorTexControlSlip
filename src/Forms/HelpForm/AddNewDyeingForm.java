/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.HelpForm;

import DataEntities.DyeingProcess;
import Unused.TestProcessPanel;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import DataEntities.DyeingProgram;
import Handlers.ChemicalHandler;
import Handlers.DyeingProcessHandler;
import Handlers.DyeingProgramHandler;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Eldridge
 */
public class AddNewDyeingForm extends javax.swing.JFrame {

    //private int NumberOfTabs = 1;
    //private List<SubProcessPanel> subProcessPanels = new ArrayList<SubProcessPanel>();
    //JPanel ThisPanel = new TestProcessPanel();
    private int NumberOfProcessTabs = 0;
    //private int NumberOfSubProcessTabs = 1;
    //private List<JTextField> ProcessNameList = new ArrayList<JTextField>();
    //private List<JTextField> subProcessNameList = new ArrayList<JTextField>();
    /**
     * Creates new form ResinForm
     */
    public AddNewDyeingForm() {
        initComponents();
        Process.add(new ProcessPanel(), "Process 1", NumberOfProcessTabs++);
        AddThePlusTab();
        //this.jPanel2.add(ThisPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 80, 780, 550));
        //this.jPanel2.validate();
     
    }
    
    public void AddThePlusTab()
    {
        Process.add(new JPanel(), "+", NumberOfProcessTabs++);
        Process.addChangeListener(changeListener);
    }
    //For Edit or View
    public AddNewDyeingForm(String DyeingProgramName, int type)
    {
        initComponents();
        if(type == 1)
        {
            Header.setText("Dyeing Program");
        }
        else if(type == 2)
        {
            Header.setText("Update Dyeing Program");
        }
        SetDyeingProgramFromProgramName(DyeingProgramName);
        AddThePlusTab();
    }
    
    /**
     * Sets Dyeing Program Name Only and then Calls to set the individual process
     * @param DyeingProgramName 
     */
    public void SetDyeingProgramFromProgramName(String DyeingProgramName)
    {
        //Class and Handler Declaration
        DyeingProgram thisDyeingProgram = new DyeingProgram();
        DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
        //Set Program Name
        thisDyeingProgram.setDyeingProgramName(DyeingProgramName);
        thisDyeingProgram.setDyeingProgramId(thisDyeingProgramHandler.GetDyeingProgramIDfromName(thisDyeingProgram.getDyeingProgramName()));
        this.ProgramNameText.setText(thisDyeingProgram.getDyeingProgramName());
        
        SetDyeingProgramProcessFromProgramID(thisDyeingProgram.getDyeingProgramId());
    }
    
    /**
     * Sets All the Dyeing Process using the Program ID
     * @param DyeingProgramID 
     */
    public void SetDyeingProgramProcessFromProgramID(int DyeingProgramID)
    {
        ArrayList<DyeingProcess> thisDyeingProcess;
        DyeingProcessHandler ProcessHandler = new DyeingProcessHandler();
        thisDyeingProcess = ProcessHandler.GetAllDyeingProcessByDyeingProgramId(DyeingProgramID);

        //Add Dyeing Process Tab for Each Dyeing Process
        for(DyeingProcess thisProcess: thisDyeingProcess)
        {
            //Declare the Tab to be added
            this.addNewTabProcess(thisProcess);
        }
    }
   
    
    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            addNewTabProcess();
        }

    };
    /**
     * Add a new tab with Dyeing Process from the database
     * @param thisProcess
     * @param ProcessNumber
     * @param DyeingProgramID 
     */
    private void addNewTabProcess(DyeingProcess thisProcess)
    {
        //Declare the Tab to be added
        ProcessPanel this_panel = new ProcessPanel(thisProcess);
        
        //Add the Tab to the JtabbedPane
        Process.add(this_panel, "Process " + String.valueOf(NumberOfProcessTabs+1),
                    NumberOfProcessTabs);
        NumberOfProcessTabs++;
        
    }
    private void addNewTabProcess() 
    {
        int index = NumberOfProcessTabs - 1;
        if (Process.getSelectedIndex() == index) { /* if click new tab */
           
            /* add new tab */
            JPanel this_panel = new ProcessPanel();
            Process.add(this_panel, "Process " + String.valueOf(NumberOfProcessTabs),
                    index);
            /* set tab is custom tab */
            Process.removeChangeListener(changeListener);
            Process.setSelectedIndex(index);
            Process.addChangeListener(changeListener);
            NumberOfProcessTabs++;
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

        jPanel2 = new javax.swing.JPanel();
        Header = new javax.swing.JLabel();
        SaveBut = new javax.swing.JButton();
        Process = new javax.swing.JTabbedPane();
        ProgramNameLabel = new javax.swing.JLabel();
        ProgramNameText = new javax.swing.JTextField();
        CancelBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dyeing Program");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setToolTipText("");
        jPanel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        Header.setForeground(new java.awt.Color(255, 255, 255));
        Header.setText("Add Dyeing Program");
        jPanel2.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 360, 40));

        SaveBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        SaveBut.setText("Add Dyeing Program");
        SaveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButActionPerformed(evt);
            }
        });
        jPanel2.add(SaveBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 650, 280, 40));

        Process.setBackground(new java.awt.Color(255, 255, 255));
        Process.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(Process, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 770, 500));

        ProgramNameLabel.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        ProgramNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        ProgramNameLabel.setText("Program Name :");
        jPanel2.add(ProgramNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 165, 34));

        ProgramNameText.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        jPanel2.add(ProgramNameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 580, 34));

        CancelBut.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        CancelBut.setText("Cancel");
        CancelBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButActionPerformed(evt);
            }
        });
        jPanel2.add(CancelBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 650, 280, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void AddDyeingProgram()
    {
        DyeingProgram thisDyeingProgram = new DyeingProgram();
        DyeingProgramHandler thisDyeingProgramHandler = new DyeingProgramHandler();
        
        thisDyeingProgram.setDyeingProgramName(this.ProgramNameText.getText());
        
        thisDyeingProgramHandler.AddDyeingProgram(thisDyeingProgram);
        int DyeingProgramID = thisDyeingProgramHandler.GetDyeingProgramIDfromName(thisDyeingProgram.getDyeingProgramName());
        thisDyeingProgram.setDyeingProgramId(DyeingProgramID);
        
        if(DyeingProgramID != -1)
        {
            Component[] this_pane = this.Process.getComponents();
            int ProcessOrder = 1;
            
            for (Component c : this_pane) 
            {
                //System.out.println(c.getClass());
                if (c instanceof ProcessPanel) {
                    ProcessPanel ThisProcessPanel = ((ProcessPanel)c);
                    ThisProcessPanel.AddThisPanelInDyeingProcess(DyeingProgramID, ProcessOrder++);
                    //Component[] ProcessPanel = ThisProcessPanel.getComponents();
                    //for (Component d : ProcessPanel) {
                    //System.out.println(d.getClass());
                    //        if (d instanceof JTextField) {
                    //            JTextField textField = ((JTextField)d);
                    //            System.out.println(textField.getText());
                    //        }
                    //ThisProcessPanel.GetSubProcessText();
                    //System.out.println("Entered");
                    //}
                    //ThisProcessPanel.GetSubProcessText();
                }
            }
        }    
            
        
        
    }
    private void SaveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButActionPerformed
        // TODO add your handling code here:
        //this.jPanel2.getComp
        AddDyeingProgram();
        
    }//GEN-LAST:event_SaveButActionPerformed

    private void CancelButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelButActionPerformed

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
            java.util.logging.Logger.getLogger(AddNewDyeingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewDyeingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewDyeingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewDyeingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new AddNewDyeingForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBut;
    private javax.swing.JLabel Header;
    private javax.swing.JTabbedPane Process;
    private javax.swing.JLabel ProgramNameLabel;
    private javax.swing.JTextField ProgramNameText;
    private javax.swing.JButton SaveBut;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}


