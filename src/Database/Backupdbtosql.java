/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.*;
import java.io.*;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eldridge
 */
public class Backupdbtosql {

    String dbUser = "root";
    String dbPass = "password123";
    String dbName = "colortex";
    String path="D:\\backup.sql";
    
    public void backup() {
        String executeCmd = "";
        String executeCmd2 = "";
        //executeCmd = "set path=%path%; C:\\Program Files (x86)\\MySQL\\MySQL Server 5.5\\bin;";
//        executeCmd2 = "C://xampp//mysql//bin//mysqldump -u" + dbUser + " " + dbName + "";
        executeCmd2 = "C://Program Files (x86)//MySQL//MySQL Server 5.5//bin//mysqldump -u" + dbUser + " -p"+dbPass+ " "  + dbName;

        Process runtimeProcess; 
        Runtime rt = Runtime.getRuntime();
        File test = new File(path);
        PrintStream ps;
        try {
            Process child = rt.exec(executeCmd2);
            ps = new PrintStream(test);
            InputStream in = child.getInputStream();
            
            int ch;
            while ((ch = in.read()) != -1) {
                ps.write(ch);
            //    System.out.write(ch); //to view it by console
            }

            /*InputStream err = child.getErrorStream();
            while ((ch = err.read()) != -1) {
                System.out.write(ch);
            }*/
            
            //runtimeProcess = Runtime.getRuntime().exec(executeCmd);
           
        } catch (IOException ex) {
            Logger.getLogger(Backupdbtosql.class.getName()).log(Level.SEVERE, null, ex);
        //} catch (InterruptedException ex) {
        //    Logger.getLogger(Backupdbtosql.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Backup is successful");
        
    }
    
    public boolean restore() throws InterruptedException
    {
        String[] executeCmd = new String[]{"C://Program Files (x86)//MySQL//MySQL Server 5.5//bin//mysql", "--user=" + dbUser, "--password=" + dbPass, dbName, "-e", " source " + path};
        //String[] executeCmd = new String[]{"C://xampp//mysql//bin//mysql", "--user=" + dbUser, dbName, "-e", " source " + path};
        Process runtimeProcess;
        try {
            int CloseorNoreply = JOptionPane.showConfirmDialog(null,"Restore the Database from the backup? "
                  ,"Restore Database?", JOptionPane.YES_NO_OPTION);
            if(CloseorNoreply == JOptionPane.YES_OPTION)
            {
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                if (processComplete == 0) {
                    System.out.println("Backup restored successfully");
                    return true;
                } else {
                    System.out.println("Could not restore the backup");
                }
            }
            
        
        }catch (Exception ex) {  
            ex.printStackTrace();  
        }
        return false;

    }
    public void runThis() {
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("cmd.exe /c start C:\\backup.bat");
            System.exit(0);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}


