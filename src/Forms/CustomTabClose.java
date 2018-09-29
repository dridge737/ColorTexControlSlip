/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
 
/**
 * --------------------- @author nguyenvanquan7826 ---------------------
 * ------------------ website: cachhoc.net -------------------
 * ---------- date: Jul 24, 2014 - filename: DemoButtonTab.java ----------
 */
public class CustomTabClose extends JPanel {
 
    JTabbedPane customJTabbedPane;
 
    /** JPanel contain a JLabel and a JButton to close */
    public CustomTabClose(JTabbedPane customJTabbedPane) {
        this.customJTabbedPane = customJTabbedPane;
        Font LabelFont = new Font("Century Gothic", Font.PLAIN, 16);
            this.setFont(LabelFont);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBorder(new EmptyBorder(4, 2, 2, 2));
        setOpaque(false);
        addLabel();
        add(new CustomButton("x"));
        
    }
 
    private void addLabel() {
        JLabel label = new JLabel() {
            /** set text for JLabel, it will title of tab */
            public String getText() {
                int index = customJTabbedPane.indexOfTabComponent(CustomTabClose.this);
                if (index != -1) {
                    return customJTabbedPane.getTitleAt(index);
                }
                return null;
            }
        };
        /** add more space between the label and the button */
        label.setBorder(new EmptyBorder(0, 0, 0, 10));
        Font LabelFont = new Font("Century Gothic", Font.PLAIN, 16);
        label.setFont(LabelFont);
        add(label);
    }
 
    class CustomButton extends JButton implements MouseListener {
        public CustomButton(String text) {
            int size = 16;
            
            this.setVerticalTextPosition((int) TOP);
            setText(text);
            /** set size for button close */
            setPreferredSize(new Dimension(size, size));
 
            setToolTipText("Close this Tab");
 
            /** set transparent */
            setContentAreaFilled(false);
 
            /** set border for button */
            setBorder(new EtchedBorder());
            /** don't show border */
            setBorderPainted(false);
 
            setFocusable(false);
 
            /** add event with mouse */
            addMouseListener(this);
 
        }
 
        /** when click button, tab will close */
        @Override
        public void mouseClicked(MouseEvent e) {
            int index = customJTabbedPane.indexOfTabComponent(CustomTabClose.this);
            if (index != -1) {
                DyeingForm thisForm = (DyeingForm) customJTabbedPane.getTopLevelAncestor();
                thisForm.removeTab(index);
                thisForm.renameTabs();
            }
        }
 
        @Override
        public void mousePressed(MouseEvent e) {
        }
 
        @Override
        public void mouseReleased(MouseEvent e) {
        }
 
        /** show border button when mouse hover */
        @Override
        public void mouseEntered(MouseEvent e) {
            setBorderPainted(true);
            setForeground(Color.RED);
        }
 
        /** hide border when mouse not hover */
        @Override
        public void mouseExited(MouseEvent e) {
            setBorderPainted(false);
            setForeground(Color.BLACK);
        }
    }
}