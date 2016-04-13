/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import DataEntities.ChemicalColor;
import Handlers.ColorHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Eldridge
 */
public class ColorForm extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    ChemicalColor thisColor = new ChemicalColor();
    /**
     * Creates new Color Form
     */
    public ColorForm() {
        initComponents();
        this.SetToCenter();
        this.GetUpdatedTable();
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

        jPanel1 = new javax.swing.JPanel();
        ColorHeader = new javax.swing.JLabel();
        DeleteButton = new javax.swing.JButton();
        ColorLabel = new javax.swing.JLabel();
        AddButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ColorTable = new javax.swing.JTable();
        ColorText = new javax.swing.JTextField();
        EditButton = new javax.swing.JButton();
        CloseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Color");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(null);

        ColorHeader.setBackground(new java.awt.Color(255, 255, 255));
        ColorHeader.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        ColorHeader.setForeground(new java.awt.Color(255, 255, 255));
        ColorHeader.setText("Color");
        jPanel1.add(ColorHeader);
        ColorHeader.setBounds(20, 20, 360, 40);

        DeleteButton.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        DeleteButton.setText("Delete");
        DeleteButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DeleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });
        jPanel1.add(DeleteButton);
        DeleteButton.setBounds(212, 390, 100, 50);

        ColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        ColorLabel.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ColorLabel.setForeground(new java.awt.Color(255, 255, 255));
        ColorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ColorLabel.setText("Color List");
        ColorLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel1.add(ColorLabel);
        ColorLabel.setBounds(10, 80, 400, 40);

        AddButton.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        AddButton.setText("Add Color");
        AddButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AddButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });
        jPanel1.add(AddButton);
        AddButton.setBounds(7, 390, 100, 50);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel2.setOpaque(false);

        ColorTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        ColorTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ColorTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ColorTable.setRowHeight(20);
        ColorTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(ColorTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 117, 400, 200);

        ColorText.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        ColorText.setForeground(new java.awt.Color(204, 204, 204));
        ColorText.setText("Color :");
        ColorText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ColorTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ColorTextFocusLost(evt);
            }
        });
        ColorText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ColorTextKeyReleased(evt);
            }
        });
        jPanel1.add(ColorText);
        ColorText.setBounds(10, 330, 400, 30);

        EditButton.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        EditButton.setText("Edit");
        EditButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        EditButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });
        jPanel1.add(EditButton);
        EditButton.setBounds(110, 390, 100, 50);

        CloseButton.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        CloseButton.setText("Close");
        CloseButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CloseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });
        jPanel1.add(CloseButton);
        CloseButton.setBounds(315, 390, 100, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        // TODO add your handling code here:
        if(ColorText.getText().length()>0 && !ColorText.getText().equals("Color :"))
        {
            thisColor.setColorName(this.ColorText.getText());
            ColorHandler thisColorHandler = new ColorHandler();
            thisColor.setColorId(thisColorHandler.GetColorIDFromColorName(thisColor.getColorName()));
            if(AddButton.getText().equals("Save"))
            {
               if(this.thisColor.getColorId() != -1)
               {
                   thisColorHandler.UpdateColor(thisColor); 
                   thisColor.setColorName("");
                   thisColor.setColorId(-1);
                   this.DeleteButton.setEnabled(true);
                   this.EditButton.setText("Edit");
                   AddButton.setText("Add Color");
               }
            }
            else
            {
                if(thisColorHandler.AddNewColor(thisColor)){
                    JOptionPane.showMessageDialog(null, "Successfully added Color : "+ColorText.getText());
                    this.dispose();
                }
                else if(ColorText.getText() == "Color :")
                {
                    JOptionPane.showMessageDialog(null, "Please input a text.");
                }
                else
                    JOptionPane.showMessageDialog(null, "Color with the same name has already been added.");
            }
            
            this.GetUpdatedTable();
            UpdateRowFilter("");
            this.ResetColorText();
        }
        else
        JOptionPane.showMessageDialog(null, "Please add a character/letter to the Color name");
            
    }//GEN-LAST:event_AddButtonActionPerformed

    public void ResetColorText()
    {
        this.ColorText.setText("Color :");
        ColorText.setForeground(new Color(204,204,204));
    }
    
    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        // TODO add your handling code here:
        if(ColorTable.getSelectedRowCount() > 0 )
        {
            int CloseorNoreply = JOptionPane.showConfirmDialog(null,"Delete this Color? "
                  ,"Delete Color?", JOptionPane.YES_NO_OPTION);
            if(CloseorNoreply == JOptionPane.YES_OPTION)
            {
                String ColorName = this.ColorTable.getModel().getValueAt(this.ColorTable.getSelectedRow(), 0).toString();
                thisColor.setColorName(ColorName);
                thisColor.setColorId(new ColorHandler().GetColorIDFromColorName(ColorName));
                new ColorHandler().DeleteColor(thisColor.getColorId());
                this.GetUpdatedTable();
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Please select an Item in the table to be edited");
        }
        
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void GetUpdatedTable()
    {
        model = getUpdatedColorTableModel();
        this.ColorTable.setModel(model);
    }
    
    private void UpdateRowFilter(String row_filter_text)
    {
        TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(this.ColorTable.getModel());
        
        this.ColorTable.setRowSorter(rowSorter);
        
        if (row_filter_text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + row_filter_text));        
        }
    }
    
    private void ColorTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ColorTextKeyReleased
        // TODO add your handling code here:
        UpdateRowFilter(this.ColorText.getText());
        
    }//GEN-LAST:event_ColorTextKeyReleased

    private void ColorTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ColorTextFocusGained
        // TODO add your handling code here:
        if(ColorText.getText().equals("Color :"))
        {
            ColorText.setText("");
            ColorText.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_ColorTextFocusGained

    private void ColorTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ColorTextFocusLost
        // TODO add your handling code here:
        if(ColorText.getText().equals(""))
            this.ResetColorText();
    }//GEN-LAST:event_ColorTextFocusLost

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        // TODO add your handling code here:
        if(ColorTable.getSelectedRowCount() > 0 )
        {
            if(EditButton.getText().equals("Edit"))
            {
                ColorText.setForeground(Color.BLACK);
                thisColor.setColorName(this.ColorTable.getModel().getValueAt(this.ColorTable.getSelectedRow(), 0).toString());
                ColorText.setText(thisColor.getColorName());
                thisColor.setColorId(new ColorHandler().GetColorIDFromColorName(thisColor.getColorName()));
                model.removeRow(this.ColorTable.getSelectedRow());
            
                this.EditButton.setText("Cancel");
                this.AddButton.setText("Save");
                this.DeleteButton.setEnabled(false);
            }
            else
            {
                model.addRow(new String[]{thisColor.getColorName()});
                thisColor.setColorName("");
                this.UpdateRowFilter("");
                EditButton.setText("Edit");
                this.AddButton.setText("Add Color");
                this.DeleteButton.setEnabled(true);
                this.ResetColorText();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please select an Item in the table to be edited");
        }
    }//GEN-LAST:event_EditButtonActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        // TODO add your handling code here:
            this.dispose();
    }//GEN-LAST:event_CloseButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ColorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColorForm().setVisible(true);
            }
        });
    }
    
    public DefaultTableModel getUpdatedColorTableModel() {      
        
        DefaultTableModel model_original = new DefaultTableModel();
        model_original.addColumn("Color");
        
        ArrayList<String> ColorList = new ColorHandler().GetAllColor();
        for(int x=0; x<ColorList.size(); x++)
        {
            model_original.addRow(new Object[]{ColorList.get(x).toString()});
        }
        return model_original;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton CloseButton;
    private javax.swing.JLabel ColorHeader;
    private javax.swing.JLabel ColorLabel;
    private javax.swing.JTable ColorTable;
    private javax.swing.JTextField ColorText;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}