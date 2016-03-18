/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

//import DataEntities.Color;
//import DataEntities.Customer;
import DataEntities.*;
import Database.DBConnection;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author imbuenyson
 */
public class ColorTextControlSlipRepository {
    
    private boolean testConnection()
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        
        conn = dbc.getConnection();
        if(conn == null)
        {
            return false;
        }
        return true;
        
    }
   //Close Connection; 
    private void closeConn(Connection conn, PreparedStatement ps)
    {
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(ps!=null)
                try {
                    ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    // OVERLOAD
    private void closeConn(Connection conn, PreparedStatement ps, ResultSet rs)
    {
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(ps!=null)
                try {
                    ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(rs!=null)
                try {
                    rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean AddCustomer(Customer newCustomer) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO customer (Name) VALUES (?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newCustomer.getCustomerName().toUpperCase());
            preparedStmt.execute();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }
    
    public boolean UpdateCustomerByCustomerId(Customer customer)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE Customer SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, customer.getCustomerName().toUpperCase());
            preparedStmt.setInt(2, customer.getCustomerId());
            preparedStmt.execute();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public boolean DeleteCustomerByCustomerId(int customerId)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM customer WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, customerId);
            preparedStmt.execute();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
/*********************************************************************************************/
/******************************* FOR COLOR ***************************************************/    
    /**
     * 
     * @param newColor
     * @return 
     */
    public boolean AddColor(Color newColor) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO Color (Name) VALUES (?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newColor.getColorName().toUpperCase());
            preparedStmt.executeUpdate();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateColorByColorId(Color thisColor) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE color SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, thisColor.getColorName().toUpperCase());
            preparedStmt.setInt(2, thisColor.getColorId());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteColorByColorId(int ColorId) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM color WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ColorId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public void CheckIfColorExists(String ColorName)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        
        
    }
               
    public int GetColorIDFromColorName(String ColorName)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ColorID = -1;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                                 + "FROM color "
                                 + "WHERE Name = ? ");
            
            ps.setString(1, ColorName);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                ColorID = rs.getInt("ID");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ColorID;
    }
    
    public String GetColorNameFromColorID(int ColorID)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ColorName = "";
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT Name"
                                 + "FROM color "
                                 + "WHERE ID = ? ");
            
            ps.setInt(1, ColorID);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                ColorName = rs.getString("ID");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ColorName;
    }
    
    public boolean CheckIfColorNameExists(String ColorName)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean itExists = false;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM Color WHERE "
                    + " Name = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ColorName);
            rs = ps.executeQuery();
            
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        if(checkTest == 1)
            itExists = true;
        
        return itExists;
    }

/*********************************************************************************************/
/******************************* FOR DESIGN ***************************************************/   
    
    public boolean AddDesign(Design newDesign) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO design (Name) VALUES (?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newDesign.getDesignName().toUpperCase());
            preparedStmt.executeUpdate();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateDesignByDesignID(Design thisDesign) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE design SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, thisDesign.getDesignName().toUpperCase());
            preparedStmt.setInt(2, thisDesign.getDesignId());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteDesignByDesignID(int DesignId) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM design WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, DesignId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
              
    public int GetDesignIDFromDesignName(String Name)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DesignID = -1;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                                 + "FROM design "
                                 + "WHERE Name = ? ");
            
            ps.setString(1, Name);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                DesignID = rs.getInt("ID");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DesignID;
    }
    
    public String GetDesignNameFromDesignID(int ID)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Name = "";
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT Name"
                                 + "FROM design "
                                 + "WHERE ID = ? ");
            
            ps.setInt(1, ID);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                Name = rs.getString("ID");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return Name;
    }
}
