/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unused;

/**
 *
 * @author Eldridge
 */
    // Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * @version 1.0 11/09/98
 */
public class JButtonTableExample extends JFrame {

  public JButtonTableExample() {
    super("JButtonTable Example");

    DefaultTableModel dm = new DefaultTableModel();
    dm.setDataVector(new Object[][] { { "button 1", "foo" },
        { "button 2", "bar" } }, new Object[] { "Button", "String" });

    dm.addColumn("Delete");

    JTable table = new JTable(dm);
    table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
    table.getColumn("Delete").setCellEditor(
        new ButtonEditor(new JCheckBox()));
    JScrollPane scroll = new JScrollPane(table);
    getContentPane().add(scroll);
    setSize(400, 100);
    setVisible(true);
  }

  public static void main(String[] args) {
    JButtonTableExample frame = new JButtonTableExample();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
}
/**
 * @version 1.0 11/09/98
 */

class ButtonRenderer extends JButton implements TableCellRenderer {

    
  public ButtonRenderer() {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText("Delete");
    return this;
  }
}

/**
 * @version 1.0 11/09/98
 */

class ButtonEditor extends DefaultCellEditor {
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
      if(JOptionPane.showConfirmDialog(null ,"Would you like to Delete this row?","Delete this Row?",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
      {
          JOptionPane.showMessageDialog(button, "Column with Value: "+thisTable.getValueAt(RowNumber, 1) + " -  Clicked!");
          DefaultTableModel model = (DefaultTableModel) thisTable.getModel();
          model.removeRow(RowNumber);
          thisTable.setModel(model);
      }
    }
    //isPushed = false;
    return new String("Delete");
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}

