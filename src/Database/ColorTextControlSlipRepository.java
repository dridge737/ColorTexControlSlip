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
import java.sql.Statement;
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
    
/*********************************************************************************************/
/******************************* FOR CUSTOMER ***************************************************/  
    //BEGIN CUSTOMER REPOSITORY METHODS
    public String GetCustomerNameById(int CustomerId) 
    {
        DBConnection db = new DBConnection();
        Customer customerDetails = new Customer();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM customer WHERE ID = ?";
              
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, CustomerId);
            resultSet = preparedStmt.executeQuery();
            //customerDetails.setCustomerId(resultSet.getInt("ID"));
            if(resultSet.first())
            {
                customerDetails.setCustomerName(resultSet.getString("Name"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt, resultSet);
        return customerDetails.getCustomerName();
    }
    
    public int GetCustomerIdFromCustomerName(String CustomerName)
    {
        DBConnection db = new DBConnection();
        int CustomerID = -1;
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;
        
         try {
            conn = db.getConnection();
            String query = "SELECT * FROM customer WHERE Name = ?";
            
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, CustomerName);
            resultSet = preparedStmt.executeQuery();
            
            if(resultSet.first())
            {
                CustomerID = resultSet.getInt("ID");
            }
            
         }
         catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
         this.closeConn(conn, preparedStmt, resultSet);
         return CustomerID;
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
    
    
    public int CheckIfCustomerExists(String customerName)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM customer WHERE "
                    + " NAME = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, customerName);
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public int CheckIfCustomerNameExistsOnOtherID(Customer ThisCustomer)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM customer WHERE "
                    + " NAME = ?"
                    + " AND ID != ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ThisCustomer.getCustomerName());
            ps.setInt(item++, ThisCustomer.getCustomerId());
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public ArrayList<String> GetAllCustomersName()
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> CustomerList = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM customer ");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                CustomerList.add(rs.getString("Name"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return CustomerList;
    }
    
/*********************************************************************************************/
/******************************* FOR COLOR ***************************************************/    
    /**
     * 
     * @param newColor
     * @return 
     */
    public boolean AddColor(ChemicalColor newColor) {
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

    public boolean UpdateColorByColorId(ChemicalColor thisColor) 
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
                                 + " FROM color "
                                 + " WHERE Name = ? ");
            
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
            ps = conn.prepareStatement("SELECT Name "
                                 + " FROM color "
                                 + " WHERE ID = ? ");
            
            ps.setInt(1, ColorID);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                ColorName = rs.getString("Name");
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
    
    public ArrayList<String> GetAllColors()
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> ColorList = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM color ");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                ColorList.add(rs.getString("Name"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return ColorList;
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
    
    public int CheckIfDesignExists(String DesignName)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM design WHERE "
                    + " NAME = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, DesignName);
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public int CheckIdDesignExistsOnOtherId(Design ThisDesign)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM design WHERE "
                    + " NAME = ? "
                    + " AND ID = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ThisDesign.getDesignName());
            ps.setInt(item++, ThisDesign.getDesignId());
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
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
                                 + " FROM design "
                                 + " WHERE Name = ? ");
            
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
            ps = conn.prepareStatement("SELECT Name "
                                 + " FROM design "
                                 + " WHERE ID = ? ");
            
            ps.setInt(1, ID);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                Name = rs.getString("Name");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return Name;
    }
    
    public ArrayList<String> GetAllDesign()
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> DesignList = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM design ");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                DesignList.add(rs.getString("Name"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return DesignList;
    }
    
/************************************************************************************************/
/******************************* FOR CHEMICAL ***************************************************/
    
    public boolean AddChemical(Chemical newChemical) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO chemical (Name) VALUES (?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newChemical.getChemicalName().toUpperCase());
            preparedStmt.executeUpdate();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateChemicalByChemicalID(Chemical thisChemical) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE chemical SET Name = ?, Cost = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, thisChemical.getChemicalName().toUpperCase());
            preparedStmt.setFloat(2, thisChemical.getCost());
            preparedStmt.setInt(3, thisChemical.getChemicalId());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteChemicalByChemicalID(int ChemicalId) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM chemical WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ChemicalId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public int CheckIfChemicalExists(String ChemicalName)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM chemical WHERE "
                    + " NAME = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ChemicalName);
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public int CheckIfChemicalNameOnDifferentIDExists(Chemical ThisChemical)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM chemical WHERE "
                    + " NAME = ?"
                    + " AND ID != ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ThisChemical.getChemicalName());
            ps.setInt(item++, ThisChemical.getChemicalId());
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
              
    public int GetChemicalIDFromChemicalName(String Name)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DesignID = -1;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                                 + " FROM chemical "
                                 + " WHERE Name = ? ");
            
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
    
    public String GetChemicalNameFromChemicalID(int ID)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Name = "";
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT Name "
                                 + " FROM chemical "
                                 + " WHERE ID = ? ");
            
            ps.setInt(1, ID);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                Name = rs.getString("Name");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return Name;
    }
    
    public ArrayList<String> GetAllChemicalName()
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> ChemicalList = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM chemical");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                ChemicalList.add(rs.getString("Name"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return ChemicalList;
    
    }
    
    //END CUSTOMER REPOSITORY METHODS
    
    //BEGIN MACHINE REPOSITORY METHODS
    public ArrayList<Machine> GetAllMachine()
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Machine> MachineList = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM machine");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Machine machine = new Machine();
                
                machine.setMachineId(rs.getInt("ID"));
                machine.setMachineName(rs.getString("Name"));
                machine.setMaxCapacity(rs.getInt("MaxCapacity"));
                machine.setMinCapacity(rs.getInt("MinCapacity"));
                machine.setMaxVolume(rs.getInt("MaxVolume"));
                machine.setMinVolume(rs.getInt("MinVolume"));
                
                MachineList.add(machine);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return MachineList;
    }
    
    public boolean AddMachine(Machine newMachine) 
    {
        
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO machine (Name, MaxCapacity, MinCapacity, MaxVolume, MinVolume) VALUES (?, ?, ?, ? ,?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newMachine.getMachineName().toUpperCase());
            preparedStmt.setInt(2, newMachine.getMaxCapacity());
            preparedStmt.setInt(3, newMachine.getMinCapacity());
            preparedStmt.setInt(4, newMachine.getMaxVolume());
            preparedStmt.setInt(5, newMachine.getMinVolume());
            preparedStmt.execute();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }
    
    public boolean UpdateMachineByMachineId(Machine machine)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE machine SET Name = ?, MaxCapacity = ?, MinCapacity = ?, MaxVolume = ?, MinVolume = ?  WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, machine.getMachineName().toUpperCase());
            preparedStmt.setInt(2, machine.getMaxCapacity());
            preparedStmt.setInt(3, machine.getMinCapacity());
            preparedStmt.setInt(4, machine.getMaxVolume());
            preparedStmt.setInt(5, machine.getMinVolume());
            preparedStmt.setInt(6, machine.getMachineId());
            preparedStmt.executeUpdate();
            
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public boolean DeleteMachineByMachineId(int machineId)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM machine Where ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, machineId);
            preparedStmt.execute();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public int CheckIfMachineExists(int machineId)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM machine WHERE "
                    + " ID = ?) "
                    + " AS 'CheckTest'");
            
            ps.setInt(1, machineId);
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public Machine GetMachineDetailsById(int machineId) 
    {
        DBConnection db = new DBConnection();
        Machine machineDetails = new Machine();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM machine WHERE ID = ?";
              
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, machineId);
            resultSet = preparedStmt.executeQuery();
            
            if(resultSet.first())
            {
                machineDetails.setMachineId(resultSet.getInt("ID"));
                machineDetails.setMachineName(resultSet.getString("Name"));
                machineDetails.setMaxCapacity(resultSet.getInt("MaxCapacity"));
                machineDetails.setMinCapacity(resultSet.getInt("MinCapacity"));
                machineDetails.setMaxVolume(resultSet.getInt("MaxVolume"));
                machineDetails.setMinVolume(resultSet.getInt("MinVolume"));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return machineDetails;
    }
    
    public int GetMachineIdByName(String machineName) 
    {
        DBConnection db = new DBConnection();
        int machineId = -1;
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "SELECT ID FROM machine WHERE Name = ?";
              
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, machineName.toUpperCase());
            resultSet = preparedStmt.executeQuery();
            
            if(resultSet.first())
            {
                machineId = resultSet.getInt("ID");
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return machineId;
    }
    //END MACHINE REPOSITORY METHODS
    
    //BEGIN DYEING PROGRAM REPO METHODS
    public boolean AddDyeingProgram(DyeingProgram newDyeingProgram) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO dyeing_program (Name) VALUES (?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newDyeingProgram.getDyeingProgramName().toUpperCase());
            preparedStmt.execute();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }
    
    public boolean UpdateDyeingProgramByDyeingProgramId(DyeingProgram dyeingProgram)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE dyeing_program SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, dyeingProgram.getDyeingProgramName().toUpperCase());
            preparedStmt.setInt(2, dyeingProgram.getDyeingProgramId());
            preparedStmt.execute();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public boolean DeleteDyeingProgramByDyeingProgramId(int dyeingProgramId)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM dyeing_program WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, dyeingProgramId);
            preparedStmt.execute();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public DyeingProgram GetDyeingProgramDetailsById(int dyeingProgramId) 
    {
        
        DBConnection db = new DBConnection();
        DyeingProgram dyeingProgramDetails = new DyeingProgram();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM machine WHERE ID = ?";
              
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, dyeingProgramId);
            resultSet = preparedStmt.executeQuery();
            
            dyeingProgramDetails.setDyeingProgramId(resultSet.getInt("ID"));
            dyeingProgramDetails.setDyeingProgramName(resultSet.getString("Name"));
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return dyeingProgramDetails;
    }
    
    public int GetDyeingProgramIDFromName(String DyeingProgramName)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DyeingProgramID = -1;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                                 + " FROM dyeing_program "
                                 + " WHERE Name = ? ");
            
            ps.setString(1, DyeingProgramName);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                DyeingProgramID = rs.getInt("ID");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DyeingProgramID;
    }
    
    public int CheckIfDyeingProgramExistsUsingID(int dyeingProgramId)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_program WHERE "
                    + " ID = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, dyeingProgramId);
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public int CheckIfDyeingProgramExists(String dyeingProgramName)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_program WHERE "
                    + " Name = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, dyeingProgramName);
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public int CheckIfDyeingProgramNameOnOtherIDExists(DyeingProgram ThisDyeProgram)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_program WHERE "
                    + " Name = ?"
                    + " AND ID != ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ThisDyeProgram.getDyeingProgramName());
            ps.setInt(item++, ThisDyeProgram.getDyeingProgramId());
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public ArrayList<String> GetAllDyeingProgram()
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> DyeingList = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM dyeing_program ");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                DyeingList.add(rs.getString("Name"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return DyeingList;
    }
    //END DYEING PROGRAM REPO METHODS
    
/************************************************************************************************/
/******************************* FOR JOB ORDER ***************************************************/
    
    public boolean AddJobOrder(JobOrder newJobOrder) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO job_order (DrNumber, MachineID, DesignID, ColorID, CustomerID, Date) VALUES (?, ?, ?, ?, ?, ?)";

            preparedStmt = conn.prepareStatement(query);
            int itemNumber = 1;
            preparedStmt.setString(itemNumber++ , newJobOrder.getDrNumber().toUpperCase());
            preparedStmt.setInt(itemNumber++ , newJobOrder.getMachineID());
            preparedStmt.setInt(itemNumber++ , newJobOrder.getDesignID());
            preparedStmt.setInt(itemNumber++ , newJobOrder.getColorID());
            preparedStmt.setInt(itemNumber++ , newJobOrder.getCustomerID());
            preparedStmt.setString(itemNumber++ , newJobOrder.getJobDate());
            preparedStmt.executeUpdate();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateJobOrderByJobOrderID(JobOrder thisJobOrder) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE job_order SET DrNumber = ?, MachineID = ? , DesignID = ?, ColorID = ?, CustomerID = ?, Date = ? WHERE ID = ?";
            
            int itemNumber = 1;
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(itemNumber++, thisJobOrder.getDrNumber());
            preparedStmt.setInt(itemNumber++, thisJobOrder.getMachineID());
            preparedStmt.setInt(itemNumber++, thisJobOrder.getDesignID());
            preparedStmt.setInt(itemNumber++, thisJobOrder.getColorID());
            preparedStmt.setInt(itemNumber++, thisJobOrder.getCustomerID());
            preparedStmt.setString(itemNumber++, thisJobOrder.getJobDate());
            preparedStmt.setInt(itemNumber++, thisJobOrder.getID());
            
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteJobOrderByJobOrderID(int JobOrderId) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM job_order WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, JobOrderId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public int GetJobOrderIDFromDrNumber(String drNumber)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int JobOrderID = -1;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                                 + "FROM job_order "
                                 + "WHERE DrNumber = ? ");
            
            ps.setString(1, drNumber);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                JobOrderID = rs.getInt("ID");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return JobOrderID;
    }
    
    public JobOrder GetJobOrderDetailsFromJobOrderID(int ID)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JobOrder thisJobOrder = new JobOrder();
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                                 + "FROM job_order "
                                 + "WHERE ID = ? ");
            
            ps.setInt(1, ID);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                thisJobOrder.setDrNumber( rs.getString("DrNumber") );
                thisJobOrder.setMachineID(rs.getInt("MachineID") );
                thisJobOrder.setDesignID(rs.getInt("DesignID") );
                thisJobOrder.setColorID(rs.getInt("ColorID"));
                thisJobOrder.setCustomerID(rs.getInt("CutomerID"));
                thisJobOrder.setJobDate(rs.getString("Date"));
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisJobOrder;
    }
    
/************************************************************************************************/
/******************************* FOR Resin Program **********************************************/
    
    public boolean AddResinProgram(ResinProgram newResinProgram) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO resin_program (Name) VALUES (?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newResinProgram.getName().toUpperCase());
            preparedStmt.executeUpdate();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateResinProgramByResinId(ResinProgram newResinProgram) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE resin_program SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newResinProgram.getName().toUpperCase());
            preparedStmt.setInt(2, newResinProgram.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteResinProgramByResinProgramId(int ResinProgramID) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM resin_program WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ResinProgramID);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public int GetResinIDFromResinName(String ResinName)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ResinID = -1;
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                                 + " FROM resin_program "
                                 + " WHERE Name = ? ");
            
            ps.setString(1, ResinName);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                ResinID = rs.getInt("ID");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ResinID;
    }
    
    public String GetResinNameFromResinID(int ResinID)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ResinName = "";
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT Name "
                                 + " FROM resin_program "
                                 + " WHERE ID = ? ");
            
            ps.setInt(1, ResinID);
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                ResinName = rs.getString("Name");
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ResinName;
    }
    
    public boolean CheckIfResinProgramNameExists(String ResinName)
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
                    + " FROM resin_program WHERE "
                    + " Name = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ResinName);
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
    
    public ArrayList<String> GetAllResinProgram()
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> ResinList = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM resin_program ");
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                ResinList.add(rs.getString("Name"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return ResinList;
    }
    
    

/************************************************************************************************/
/******************************* FOR Process Order ****************************************************/
    
    public boolean AddProcessOrder(ProcessOrder thisProcessOrder) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO process_order (JobOrderID, Weight, VolH20, RollLoad, Roll, DyeingProgramID, ResinProgramID) VALUES (?, ?, ?, ?, ?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisProcessOrder.getJobOrderID());
            preparedStmt.setFloat(2, thisProcessOrder.getWeight());
            preparedStmt.setFloat(3, thisProcessOrder.getVolumeH20());
            preparedStmt.setString(4, thisProcessOrder.getRollLoad());
            preparedStmt.setFloat(5, thisProcessOrder.getRoll());
            preparedStmt.setInt(6, thisProcessOrder.getDyeingProgramID());
            preparedStmt.setInt(7, thisProcessOrder.getResinProgramID());
            
            preparedStmt.executeUpdate();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateProcessOrderByProcessOrderId(ProcessOrder thisProcessOrder) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE process_order SET JobOrderID = ? , Weight = ?, VolH20 = ?, RollLoad = ? , Roll = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisProcessOrder.getJobOrderID());
            preparedStmt.setFloat(2, thisProcessOrder.getWeight());
            preparedStmt.setFloat(3, thisProcessOrder.getVolumeH20());
            preparedStmt.setString(4, thisProcessOrder.getRollLoad());
            preparedStmt.setFloat(5, thisProcessOrder.getRoll());
            preparedStmt.setInt(6, thisProcessOrder.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteProcessOrderByProcessOrderId(int ProcessOrderID) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM process_order WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ProcessOrderID);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
/************************************************************************************************/
/******************************* FOR Resin Chemical ****************************************************/ 
    public int GetResinChemicalIdByChemicalId(int resinChemical, int resinProgramId)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //DyeingProcess dyeingProcess = new DyeingProcess();
        int resinChemicalId = -1;
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT ID FROM resin_chemical where ResinProgramID = ? AND ChemicalID = ?");
            ps.setInt(1, resinProgramId); 
            ps.setInt(2, resinChemical);           
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                resinChemicalId = rs.getInt("ID");   
                break;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return resinChemicalId;
    }
    
    public boolean AddResinChemical(ResinChemical thisResinChemical) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO resin_chemical (ChemicalID, ResinProgramID, ValueGPL) VALUES (?, ?, ?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisResinChemical.getChemicalID() );
            preparedStmt.setInt(2, thisResinChemical.getResinProgramID() );
            preparedStmt.setFloat(3, thisResinChemical.getGPLValue() );
            preparedStmt.executeUpdate();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateResinChemicalByResinChemicalID(ResinChemical thisResinChemical) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE resin_chemical SET ChemicalID = ?, ResinProgramID = ?, ValueGPL = ?  WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisResinChemical.getChemicalID());
            preparedStmt.setInt(2, thisResinChemical.getResinProgramID());
            preparedStmt.setFloat(3, thisResinChemical.getGPLValue() );
            preparedStmt.setInt(4, thisResinChemical.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteResinChemicalByResinChemicalID(int ResinChemicalId) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM resin_chemical WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ResinChemicalId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public boolean DeleteResinChemicalByResinProgramID(int ResinProgramId) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM resin_chemical WHERE ResinProgramID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ResinProgramId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public ArrayList<ResinChemical> GetResinChemicalsByResinProgramId(int resinProgramId)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //DyeingProcess dyeingProcess = new DyeingProcess();
        ArrayList<ResinChemical> resinChemicals = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM resin_chemical where ResinProgramID = ?");
            ps.setInt(1, resinProgramId);           
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                ResinChemical resinChemical = new ResinChemical();
                resinChemical.setChemicalID(rs.getInt("ChemicalID"));
                resinChemical.setGPLValue(rs.getFloat("ValueGPL"));
                resinChemicals.add(resinChemical);                
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return resinChemicals;
    }
    /******************************* FOR Resin Chemical: END ****************************************************/ 
    
    /******************************* FOR DyeingProcess: BEGIN ****************************************************/ 
    public int AddDyeingProcess(DyeingProcess dyeingProcess) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        int DyeingProcessID = -1;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO dyeing_process (DyeingProgramID, Name, dyeing_process.Order) VALUES ( ?, ?, ?)";

            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, dyeingProcess.getDyeingProgramId());
            preparedStmt.setString(2, dyeingProcess.getDyeingProcessName());
            preparedStmt.setString(3, dyeingProcess.getdyeingProcessOrder());
            preparedStmt.execute();
            
            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                DyeingProcessID = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
                   
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return DyeingProcessID;
    }

    public int UpdateDyeingProcessByDyeingProcessID(DyeingProcess dyeingProcess) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        int RowManipulated = -1;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE dyeing_process SET Name = ? , dyeing_process.Order = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, dyeingProcess.getDyeingProcessName());
            preparedStmt.setString(2, dyeingProcess.getdyeingProcessOrder());
            preparedStmt.setInt(3, dyeingProcess.getId());
            //preparedStmt.setString(3, dyeingProcess.getdyeingProcessOrder());
            RowManipulated = preparedStmt.executeUpdate();
           
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return RowManipulated;
    }

    public boolean DeleteDyeingProcessByDyeingProcessID(int dyeingProcessID) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM dyeing_process WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, dyeingProcessID);
            preparedStmt.execute();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public boolean CheckIfDyeingProcessExistsWithThisID(int DyeingProcessID)
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
                    + " (SELECT Name "
                    + " FROM dyeing_process WHERE "
                    + " ID = ? ) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, DyeingProcessID);
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
    /**
     * 
     * @param ThisDyeingProcess
     * @return Returns true if Updated name exists on Other Dyeing Process
     */
    public boolean CheckIfDyeingProcessNameExistsOnOtherDyeingProcessID(DyeingProcess ThisDyeingProcess)
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
                    + " (SELECT Name "
                    + " FROM dyeing_process WHERE "
                    + " ID != ? "
                    + " AND DyeingProgramID = ? "
                    + " AND Name = ? ) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, ThisDyeingProcess.getId());
            ps.setInt(item++, ThisDyeingProcess.getDyeingProgramId());
            ps.setString(item++, ThisDyeingProcess.getDyeingProcessName());
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
    
    public boolean CheckIfDyeingProcessExists(DyeingProcess ThisDyeingProcess)
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
                    + " FROM dyeing_process WHERE "
                    + " Name = ?"
                    + " AND DyeingProgramID = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ThisDyeingProcess.getDyeingProcessName());
            ps.setInt(item++, ThisDyeingProcess.getDyeingProgramId());
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
    
    public DyeingProcess GetDyeingProcessDetailsByDyeingProcessId(int dyeingProcessId)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DyeingProcess dyeingProcess = new DyeingProcess();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM dyeing_process where ID = ? ");
            ps.setInt(1, dyeingProcessId);           
            rs = ps.executeQuery();
            if(rs.first())
            {
                dyeingProcess.setId(rs.getInt("ID"));
                dyeingProcess.setDyeingProcessName(rs.getString("Name"));
                dyeingProcess.setDyeingProcessOrder(rs.getString("Order"));
                dyeingProcess.setDyeingProgramId(rs.getInt("DyeingProgramID"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return dyeingProcess;
    }
    
    public DyeingProcess GetDyeingProcessDetailsByDyeingProgramIdAndProcessOrder(int dyeingProgramId, int ProcessOrder)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DyeingProcess dyeingProcess = new DyeingProcess();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM dyeing_process where DyeingProgramID = ? AND dyeing_process.Order = ?");
            ps.setInt(1, dyeingProgramId);           
            ps.setString(2, Integer.toString(ProcessOrder));           
            rs = ps.executeQuery();
            
            dyeingProcess.setId(rs.getInt("ID"));
            dyeingProcess.setDyeingProcessName(rs.getString("Name"));
            dyeingProcess.setDyeingProcessOrder(rs.getString("Order"));
            dyeingProcess.setDyeingProgramId(rs.getInt("DyeingProgramID"));
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return dyeingProcess;
    }
    /**
     * Get Dyeing Process Details from Current Dyeing Program ID. All subprocess i.e 1.x is not taken.
     * @param DyeingProgramID
     * @return Array List of all the dyeing process
     */
    public ArrayList<DyeingProcess> GetDyeingProcessDetailsByDyeingProgramId(int DyeingProgramID)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //DyeingProcess dyeingProcess = new DyeingProcess();
        ArrayList<DyeingProcess> AllDyeingProcess = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM dyeing_process WHERE DyeingProgramID = ? AND dyeing_process.ORDER NOT LIKE '%.%' ORDER BY dyeing_process.Order ASC;");
            ps.setInt(1, DyeingProgramID);           
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                DyeingProcess dyeingProcess = new DyeingProcess();
                dyeingProcess.setId(rs.getInt("ID"));
                dyeingProcess.setDyeingProcessName(rs.getString("Name"));
                dyeingProcess.setDyeingProcessOrder(rs.getString("Order"));
                dyeingProcess.setDyeingProgramId(rs.getInt("DyeingProgramID"));
                AllDyeingProcess.add(dyeingProcess);
                
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return AllDyeingProcess;
    }
    
    public ArrayList<DyeingProcess> GetDyeingSubProcessDetailsByDyeingProgramIdAndProcessOrder(int DyeingProgramID, int ProcessNumber)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //DyeingProcess dyeingProcess = new DyeingProcess();
        ArrayList<DyeingProcess> AllDyeingProcess = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM dyeing_process where DyeingProgramID = ? AND dyeing_process.Order LIKE ?");
            ps.setInt(1, DyeingProgramID);  
            String ParseString =  Integer.toString(ProcessNumber) + ".%";
            ps.setString(2, ParseString);  
            
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                DyeingProcess dyeingProcess = new DyeingProcess();
                dyeingProcess.setId(rs.getInt("ID"));
                dyeingProcess.setDyeingProcessName(rs.getString("Name"));
                dyeingProcess.setDyeingProcessOrder(rs.getString("Order"));
                dyeingProcess.setDyeingProgramId(rs.getInt("DyeingProgramID"));
                AllDyeingProcess.add(dyeingProcess);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return AllDyeingProcess;
    }
    
    public int CountNumberOfDyeingProcess(int DyeingProgramID)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int TotalNumberOfProcess = 0;
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(ID) AS 'TOTAL' FROM dyeing_process where DyeingProgramID = ? AND dyeing_process.ORDER NOT LIKE '%.%';");
            int item = 1;
            ps.setInt(item++, DyeingProgramID);
            rs = ps.executeQuery();
            
            if(rs.first())
            {
                TotalNumberOfProcess = rs.getInt("TOTAL");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return TotalNumberOfProcess;
    }
    
    public int CountNumberOfDyeingSubProcess(int DyeingProgramID, int ProcessNumber)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int TotalNumberOfProcess = 0;
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(ID) AS 'TOTAL' FROM dyeing_process WHERE DyeingProgramID = ? AND dyeing_process.ORDER LIKE ? ;");
            int item = 1;
            ps.setInt(item++, DyeingProgramID);
            String ParseProcessNumber = Integer.toString(ProcessNumber) + ".%";
            ps.setString(item++, ParseProcessNumber);
            rs = ps.executeQuery();
            
            if(rs.first())
            {
                TotalNumberOfProcess = rs.getInt("TOTAL");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return TotalNumberOfProcess;
    }
    
    public ArrayList<String> GetAllDyeingProcessByDyeingProgramId(int DyeingProgramID)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> DyeingProcessList = new ArrayList<>();
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM dyeing_process where DyeingProgramID = ? ");
            int item = 1;
            ps.setInt(item++, DyeingProgramID);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                DyeingProcessList.add(rs.getString("Name"));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return DyeingProcessList;
    }
    
    public int GetDyeingProcessIDFromDyeingProcessDetails(DyeingProcess ThisDyeingProcess)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ProcessID = -1;
        try{
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                                 + " FROM dyeing_process "
                                 + " WHERE Name = ? AND dyeing_process.Order LIKE ? AND DyeingProgramID = ?");
            
            ps.setString(1, ThisDyeingProcess.getDyeingProcessName().toUpperCase());
            ps.setString(2, ThisDyeingProcess.getdyeingProcessOrder());
            ps.setInt(3, ThisDyeingProcess.getDyeingProgramId());
            
            rs = ps.executeQuery();
            if(rs.first())
            {
                ProcessID = rs.getInt("ID");
            }
            
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ProcessID;
    }
    /******************************* FOR DyeingProcess: END ****************************************************/ 
    
    
    /************************************************************************************************/
/******************************* FOR Dyeing Chemical ****************************************************/ 
    
    public boolean AddDyeingChemical(DyeingChemical thisDyeingChemical) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO dyeing_chemical (ChemicalID, DyeingProcessID, Type, Value, dyeing_chemical.order, State) VALUES (?, ?, ?, ?, ?, ?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisDyeingChemical.getChemicalID() );
            preparedStmt.setInt(2, thisDyeingChemical.getDyeingProcessID() );
            preparedStmt.setString(3, thisDyeingChemical.getType());
            preparedStmt.setFloat(4, thisDyeingChemical.getValue());
            preparedStmt.setInt(5, thisDyeingChemical.getOrder());
            preparedStmt.setString(6, thisDyeingChemical.getState());
            preparedStmt.executeUpdate();
            
            added = true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateDyeingChemicalByDyeingChemicalID(DyeingChemical thisDyeingChemical) 
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "UPDATE dyeing_chemical SET ChemicalID = ?, Type = ?, Value = ? WHERE DyeingProcessID = ? AND dyeing_chemical.Order = ? LIMIT 1";

            preparedStmt = conn.prepareStatement(query);
            int item = 1;
            preparedStmt.setInt( item++, thisDyeingChemical.getChemicalID());
            preparedStmt.setString( item++, thisDyeingChemical.getType() );
            preparedStmt.setFloat( item++, thisDyeingChemical.getValue());
            preparedStmt.setInt( item++, thisDyeingChemical.getDyeingProcessID());
            preparedStmt.setInt(item++, thisDyeingChemical.getOrder());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteDyeingChemicalByDyeingChemicalID(DyeingChemical ThisDyeingChemical) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "DELETE FROM dyeing_chemical WHERE ID = ? LIMIT 1";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ThisDyeingChemical.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public boolean DeleteDyeingChemicalByDyeingProcessID(DyeingChemical ThisDyeingChemical) {
    
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try
        {
            conn = db.getConnection();
            String query = "SQL_SAFE_UPDATE = 0; DELETE FROM dyeing_chemical WHERE DyeingProcessID = ?; SET SQL_SAFE_UPDATES = 1;";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ThisDyeingChemical.getDyeingProcessID());
            preparedStmt.setInt(2, ThisDyeingChemical.getOrder());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
     public ArrayList<DyeingChemical> GetAllDyeingChemicalsFromDyeingProcessID(int DyeingProcessID)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DyeingChemical> DyeingChemicalsForProcess = new ArrayList<DyeingChemical>();
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                                 + " FROM dyeing_chemical "
                                 + " WHERE DyeingProcessID = ? "
                                 + " ORDER BY dyeing_chemical.Order ASC");
            
            ps.setInt(1, DyeingProcessID);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
                DyeingChemical thisDyeingChemical = new DyeingChemical();
                thisDyeingChemical.setChemicalID(rs.getInt("ChemicalID"));
                thisDyeingChemical.setType(rs.getString("Type"));
                thisDyeingChemical.setValue(rs.getFloat("Value"));
                thisDyeingChemical.setOrder(rs.getInt("Order"));
                DyeingChemicalsForProcess.add(thisDyeingChemical);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DyeingChemicalsForProcess;
    }
    
     public int CheckIfDyeingChemicalExistsOnThisProcess(DyeingChemical ThisDyeingChemical)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_chemical WHERE "
                    + " ChemicalID = ?"
                    + " AND DyeingProcessID = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, ThisDyeingChemical.getChemicalID());
            ps.setInt(item++, ThisDyeingChemical.getDyeingProcessID());
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
     
     public int CountNumberOfDyeingChemicalForThisProcess(int DyeingProcessID)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int TotalNumberOfProcess = 0;
        try
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(ID) AS 'TOTAL' FROM dyeing_chemical WHERE DyeingProcessID = ?;");
            int item = 1;
            ps.setInt(item++, DyeingProcessID);
            rs = ps.executeQuery();
            
            if(rs.first())
            {
                TotalNumberOfProcess = rs.getInt("TOTAL");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return TotalNumberOfProcess;
    }
    
    public int CheckIfSameDyeingChemicalExistsOnThisProcess(DyeingChemical ThisDyeingChemical)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try 
        {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_chemical WHERE "
                    + " ChemicalID = ? "
                    + " AND DyeingProcessID = ? "
                    + " AND ID = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, ThisDyeingChemical.getChemicalID());
            ps.setInt(item++, ThisDyeingChemical.getDyeingProcessID());
            ps.setInt(item++, ThisDyeingChemical.getID());
            rs = ps.executeQuery();
            if(rs.first())
                checkTest = rs.getInt("CheckTest");
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeConn(conn, ps, rs);
        return checkTest;
    }
}
