/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.HelpForm;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Eldridge
 */
public class ProcessPanel extends javax.swing.JPanel {

    private int NumberOfTabs = 0;
    private List<JTextField> subProcessName = new ArrayList<JTextField>();
    /**
     * Creates new form ProcessPanel
     */
    public ProcessPanel() {
        initComponents();
        /* add tab to add new tab when click */
        subProcess.add(new SubProcessPanel(), "Sub Process 1", NumberOfTabs++);
        subProcess.add(new JPanel(), "+", NumberOfTabs++);
        subProcess.addChangeListener(changeListener);
        
        
    }
    
    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            addNewTab();
        }

    };
    
    private void addComponent()
    {
        JPanel this_panel = new SubProcessPanel();
            for (Component c : this_panel.getComponents()) {
                if (c instanceof JTextField) {
                    JTextField textField = ((JTextField)c);
                    String name, contact;
                    //if(textField.getName().startsWith("subProcess")) {
                        // Name field
                        this.subProcessName.add(textField);
                    //} else {
                        // Contact field
                    //    contact = textField.getText();
                    //}
       // Validate and persist.

                }
            }
    }
     private void addNewTab() {
        int index = NumberOfTabs - 1;
        if (subProcess.getSelectedIndex() == index) { /* if click new tab */
            /* add new tab */
            JPanel this_panel = new SubProcessPanel();
            subProcess.add(this_panel, "Sub Process " + String.valueOf(NumberOfTabs),
                    index);
            /* set tab is custom tab */
            //jTabbedPane1.setTabComponentAt(index, new DemoCustomTab(this));
            subProcess.removeChangeListener(changeListener);
            subProcess.setSelectedIndex(index);
            subProcess.addChangeListener(changeListener);
            NumberOfTabs++;
            
        }
    }
     
     public void GetSubProcessText()
     {
         Component[] this_pane = this.subProcess.getComponents();
            for (Component c : this_pane) {
                    System.out.println(c.getClass());
                if (c instanceof SubProcessPanel) {
                    SubProcessPanel ThisProcessPanel = ((SubProcessPanel)c);
                    ThisProcessPanel.GetSubProcessText();
                    System.out.println(ThisProcessPanel.GetSubProcessText());
                    System.out.println(ThisProcessPanel.GetChemicalTable().toString());
                }
            }

     }
     
     public void AddDyeingProcess(int TabIndex)
     {
         
     }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        subProcess = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setText("Process Name :");

        subProcess.setAutoscrolls(true);
        subProcess.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(10, 10, 10)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(subProcess, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTabbedPane subProcess;
    // End of variables declaration//GEN-END:variables
}
