/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms.HelpForm;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eldridge
 */
public class ButtonEditor extends DefaultCellEditor {
  protected JButton button;
  private int RowNumber;
  JTable thisTable;
  private boolean isPushed;

  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
      
      this.thisTable = table;
      this.RowNumber = row;
      
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
      
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    //label = (value == null) ? "" : value.toString();
    button.setText("Delete");
    
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed) {
        
      //if(JOptionPane.showConfirmDialog(null ,"Would you like to Delete this row?","Delete this Row?",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
      //{
          //JOptionPane.showMessageDialog(button, "Column with Value: "+thisTable.getValueAt(RowNumber, 1) + " -  Clicked!");
          DefaultTableModel model = (DefaultTableModel) thisTable.getModel();
          model.removeRow(RowNumber);
          thisTable.setModel(model);
      //}
    }
    isPushed = false;
    //fireEditingStopped();
    
    return new String("Delete");
  }

  public boolean stopCellEditing() {
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}
