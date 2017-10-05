/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;

/**
 *
 * @author Eldridge
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        SetToCenter();
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
        Exit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ControlSlipButton = new javax.swing.JButton();
        DyeingForm = new javax.swing.JButton();
        ResinForm = new javax.swing.JButton();
        ChemBut = new javax.swing.JButton();
        ColorBut = new javax.swing.JButton();
        CustomerBut = new javax.swing.JButton();
        DesignBut = new javax.swing.JButton();
        MachineBut = new javax.swing.JButton();
        NewDyeingProgram = new javax.swing.JButton();
        NewResinProgram = new javax.swing.JButton();
        PreviousCustomerOrder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control Slip");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Exit.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jPanel1.add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 356, 370, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Dyeing Control System");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(485, 7, 250, -1));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Relianz International Corp.");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 27, 320, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 60));

        ControlSlipButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ControlSlipButton.setText("New Control Slip");
        ControlSlipButton.setToolTipText("");
        ControlSlipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ControlSlipButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ControlSlipButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 370, 30));

        DyeingForm.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        DyeingForm.setText("Change Dyeing Program");
        DyeingForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DyeingFormActionPerformed(evt);
            }
        });
        jPanel1.add(DyeingForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 128, 280, 30));

        ResinForm.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ResinForm.setText("Change Resin Program");
        ResinForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResinFormActionPerformed(evt);
            }
        });
        jPanel1.add(ResinForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 166, 280, 30));

        ChemBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ChemBut.setText("Chemical");
        ChemBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChemButActionPerformed(evt);
            }
        });
        jPanel1.add(ChemBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 242, 180, 30));

        ColorBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        ColorBut.setText("Color");
        ColorBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorButActionPerformed(evt);
            }
        });
        jPanel1.add(ColorBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 242, 180, 30));

        CustomerBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        CustomerBut.setText("Customer");
        CustomerBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerButActionPerformed(evt);
            }
        });
        jPanel1.add(CustomerBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 180, 30));

        DesignBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        DesignBut.setText("Design");
        DesignBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesignButActionPerformed(evt);
            }
        });
        jPanel1.add(DesignBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 180, 30));

        MachineBut.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        MachineBut.setText("Machine");
        MachineBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MachineButActionPerformed(evt);
            }
        });
        jPanel1.add(MachineBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 318, 370, 30));

        NewDyeingProgram.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        NewDyeingProgram.setText("New");
        NewDyeingProgram.setMargin(new java.awt.Insets(2, 7, 2, 7));
        NewDyeingProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewDyeingProgramActionPerformed(evt);
            }
        });
        jPanel1.add(NewDyeingProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 128, 85, 30));

        NewResinProgram.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        NewResinProgram.setText("New");
        NewResinProgram.setMargin(new java.awt.Insets(2, 7, 2, 7));
        NewResinProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewResinProgramActionPerformed(evt);
            }
        });
        jPanel1.add(NewResinProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 166, 85, 30));

        PreviousCustomerOrder.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        PreviousCustomerOrder.setText("View Previous Customer Order");
        PreviousCustomerOrder.setToolTipText("");
        PreviousCustomerOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousCustomerOrderActionPerformed(evt);
            }
        });
        jPanel1.add(PreviousCustomerOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 204, 370, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ControlSlipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ControlSlipButtonActionPerformed
        // TODO add your handling code here:
        JobOrderForm thisJobForm = new JobOrderForm();
        thisJobForm.setVisible(true);
    }//GEN-LAST:event_ControlSlipButtonActionPerformed

    private void MachineButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MachineButActionPerformed
        // TODO add your handling code here:
        MachineForm thisMachine = new MachineForm();
        thisMachine.setVisible(true);
    }//GEN-LAST:event_MachineButActionPerformed

    private void DyeingFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DyeingFormActionPerformed
        // TODO add your handling code here:
        ViewDyeingProgramList newDyeingForm = new ViewDyeingProgramList();
        newDyeingForm.setVisible(true);
    }//GEN-LAST:event_DyeingFormActionPerformed

    private void ResinFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResinFormActionPerformed
        // TODO add your handling code here:
        ViewResinProgramList newResinForm = new ViewResinProgramList();
        newResinForm.setVisible(true);
    }//GEN-LAST:event_ResinFormActionPerformed

    private void CustomerButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerButActionPerformed
        // TODO add your handling code here:
        CustomerForm newCustForm = new CustomerForm();
        newCustForm.setVisible(true);
    }//GEN-LAST:event_CustomerButActionPerformed

    private void ChemButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChemButActionPerformed
        // TODO add your handling code here:
        ChemicalForm thisChemForm = new ChemicalForm();
        thisChemForm.setVisible(true);
    }//GEN-LAST:event_ChemButActionPerformed

    private void ColorButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorButActionPerformed
        // TODO add your handling code here:
        ColorForm newColorForm = new ColorForm();
        newColorForm.setVisible(true);
    }//GEN-LAST:event_ColorButActionPerformed

    private void DesignButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesignButActionPerformed
        // TODO add your handling code here:
        DesignForm thisDesignForm = new DesignForm();
        thisDesignForm.setVisible(true);
    }//GEN-LAST:event_DesignButActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_ExitActionPerformed

    private void NewDyeingProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewDyeingProgramActionPerformed
        // TODO add your handling code here:
        DyeingForm newAddDyeingForm = new DyeingForm();
        newAddDyeingForm.setVisible(true);
    }//GEN-LAST:event_NewDyeingProgramActionPerformed

    private void NewResinProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewResinProgramActionPerformed
        // TODO add your handling code here:
         AddResinForm newResinForm  = new AddResinForm();
        newResinForm.setVisible(true);
    }//GEN-LAST:event_NewResinProgramActionPerformed

    private void PreviousCustomerOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousCustomerOrderActionPerformed
        // TODO add your handling code here:
        /*ViewPreviousJobOrders prevJobOrder = new ViewPreviousJobOrders();
        prevJobOrder.setVisible(true)*/
        ViewCustomerOrder customerJobOrder = new ViewCustomerOrder();
        customerJobOrder.setVisible(true);
    }//GEN-LAST:event_PreviousCustomerOrderActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    
    public static void Backupdbtosql() {
    try {

        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
        CodeSource codeSource = MainWindow.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();


        /*NOTE: Creating Database Constraints*/
        String dbName = "color_tex_processing";
        String dbUser = "";
        String dbPass = "";

        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
        String folderPath = jarDir + "\\backup";

        /*NOTE: Creating Folder if it does not exist*/
        File f1 = new File(folderPath);
        f1.mkdir();

        /*NOTE: Creating Path Constraints for backup saving*/
        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
         String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";

        /*NOTE: Used to create a cmd command*/
        String executeCmd = "C:\\xampp\\mysql\\bin\\mysqldump -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;

        /*NOTE: Executing the command here*/
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();

        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
        if (processComplete == 0) {
            System.out.println("Backup Complete");
        } else {
            System.out.println("Backup Failure");
        }

        } 
        catch (URISyntaxException | IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChemBut;
    private javax.swing.JButton ColorBut;
    private javax.swing.JButton ControlSlipButton;
    private javax.swing.JButton CustomerBut;
    private javax.swing.JButton DesignBut;
    private javax.swing.JButton DyeingForm;
    private javax.swing.JButton Exit;
    private javax.swing.JButton MachineBut;
    private javax.swing.JButton NewDyeingProgram;
    private javax.swing.JButton NewResinProgram;
    private javax.swing.JButton PreviousCustomerOrder;
    private javax.swing.JButton ResinForm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
