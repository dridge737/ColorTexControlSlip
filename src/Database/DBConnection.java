/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author imbuenyson
 */
public class DBConnection {
    private String driver, url, username, password;
    
    public DBConnection()
    {
        url = "jdbc:mysql://localhost:3306/color_tex_processing";
        driver = "com.mysql.jdbc.Driver";
        username = "root";
        password = "";
        //password = "password123";
    }
    public Connection getConnection()
    {
        try{
            Class.forName(driver);
            
            Connection conn = DriverManager.getConnection(url, username, password);
            
            return conn;
                    
        }catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex);
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        
        return null;
    }
}
