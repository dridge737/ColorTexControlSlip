/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

//import DataEntities.DesignColor;
//import DataEntities.Customer;
import DataEntities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imbuenyson
 */
public class ColorTextControlSlipRepository {

    private boolean testConnection() {
        DBConnection dbc = new DBConnection();
        Connection conn = null;

        conn = dbc.getConnection();
        if (conn == null) {
            return false;
        }
        return true;

    }

    //Close Connection; 

    private void closeConn(Connection conn, PreparedStatement ps) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // OVERLOAD

    private void closeConn(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ***************************** FOR CUSTOMER **************************************************
     */
    //BEGIN CUSTOMER REPOSITORY METHODS
    public String GetCustomerNameById(int CustomerId) {
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
            if (resultSet.first()) {
                customerDetails.setCustomerName(resultSet.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt, resultSet);
        return customerDetails.getCustomerName();
    }

    public int GetCustomerIdFromCustomerName(String CustomerName) {
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

            if (resultSet.first()) {
                CustomerID = resultSet.getInt("ID");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, preparedStmt, resultSet);
        return CustomerID;
    }

    public boolean AddCustomer(Customer newCustomer) {
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
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateCustomerByCustomerId(Customer customer) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE Customer SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, customer.getCustomerName().toUpperCase());
            preparedStmt.setInt(2, customer.getCustomerId());
            preparedStmt.execute();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteCustomerByCustomerId(int customerId) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "DELETE FROM customer WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, customerId);
            preparedStmt.execute();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public int CheckIfCustomerExists(String customerName) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM customer WHERE "
                    + " NAME = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, customerName);
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public int CheckIfCustomerNameExistsOnOtherID(Customer ThisCustomer) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
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
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public ArrayList<String> GetAllCustomersName() {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> CustomerList = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM customer ORDER BY NAME asc");
            rs = ps.executeQuery();

            while (rs.next()) {
                CustomerList.add(rs.getString("Name").toUpperCase());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return CustomerList;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ***************************** FOR COLOR **************************************************
     */
    /**
     *
     * @param newColor
     * @return
     */
    public boolean AddColor(DesignColor newColor) {
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
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateColorByColorId(DesignColor thisColor) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE color SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, thisColor.getColorName().toUpperCase());
            preparedStmt.setInt(2, thisColor.getColorId());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM color WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ColorId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public int GetColorIDFromColorName(String ColorName) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ColorID = -1;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                    + " FROM color "
                    + " WHERE Name = ? ");

            ps.setString(1, ColorName);

            rs = ps.executeQuery();
            if (rs.first()) {
                ColorID = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ColorID;
    }

    public String GetColorNameFromColorID(int ColorID) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ColorName = "";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT Name "
                    + " FROM color "
                    + " WHERE ID = ? ");

            ps.setInt(1, ColorID);

            rs = ps.executeQuery();
            if (rs.first()) {
                ColorName = rs.getString("Name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ColorName;
    }

    public boolean CheckIfColorNameExists(String ColorName) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean itExists = false;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM Color WHERE "
                    + " Name = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ColorName);
            rs = ps.executeQuery();

            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        if (checkTest == 1) {
            itExists = true;
        }

        return itExists;
    }

    public ArrayList<String> GetAllColors() {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> ColorList = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM color ");
            rs = ps.executeQuery();

            while (rs.next()) {
                ColorList.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return ColorList;
    }

    /**
     * ******************************************************************************************
     */
    /**
     * ***************************** FOR DESIGN **************************************************
     */
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
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateDesignByDesignID(Design thisDesign) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE design SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, thisDesign.getDesignName().toUpperCase());
            preparedStmt.setInt(2, thisDesign.getDesignId());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM design WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, DesignId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public int CheckIfDesignExists(String DesignName) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM design WHERE "
                    + " NAME = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, DesignName);
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public int CheckIdDesignExistsOnOtherId(Design ThisDesign) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
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
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public int GetDesignIDFromDesignName(String Name) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DesignID = -1;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                    + " FROM design "
                    + " WHERE Name = ? ");

            ps.setString(1, Name);

            rs = ps.executeQuery();
            if (rs.first()) {
                DesignID = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DesignID;
    }

    public String GetDesignNameFromDesignID(int ID) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Name = "";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT Name "
                    + " FROM design "
                    + " WHERE ID = ? ");

            ps.setInt(1, ID);

            rs = ps.executeQuery();
            if (rs.first()) {
                Name = rs.getString("Name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return Name;
    }

    public ArrayList<String> GetAllDesign() {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> DesignList = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM design ");
            rs = ps.executeQuery();

            while (rs.next()) {
                DesignList.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return DesignList;
    }

    /**
     * *********************************************************************************************
     */
    /**
     * ***************************** FOR CHEMICAL **************************************************
     */
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
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateChemicalByChemicalID(Chemical thisChemical) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE chemical SET Name = ?, Cost = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, thisChemical.getChemicalName().toUpperCase());
            preparedStmt.setFloat(2, thisChemical.getCost());
            preparedStmt.setInt(3, thisChemical.getChemicalId());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM chemical WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ChemicalId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public int CheckIfChemicalExists(String ChemicalName) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM chemical WHERE "
                    + " NAME = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ChemicalName);
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public int CheckIfChemicalNameOnDifferentIDExists(Chemical ThisChemical) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
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
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public int GetChemicalIDFromChemicalName(String Name) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DesignID = -1;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                    + " FROM chemical "
                    + " WHERE Name = ? ");

            ps.setString(1, Name);

            rs = ps.executeQuery();
            if (rs.first()) {
                DesignID = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DesignID;
    }

    public String GetChemicalNameFromChemicalID(int ID) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String Name = "";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT Name "
                    + " FROM chemical "
                    + " WHERE ID = ? ");

            ps.setInt(1, ID);

            rs = ps.executeQuery();
            if (rs.first()) {
                Name = rs.getString("Name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return Name;
    }

    public ArrayList<String> GetAllChemicalName() {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> ChemicalList = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM chemical");
            rs = ps.executeQuery();

            while (rs.next()) {
                ChemicalList.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return ChemicalList;

    }

    public ArrayList<Machine> GetAllAutomaticInputResinMachine()
    {
        String query =  ("SELECT * FROM machine WHERE  (NAME Like 'Dryer' OR NAME LIKE 'Stenter') AND MachineType = 1;");
        return GetAllMachine(query);
    }
    
    public ArrayList<Machine> GetAllManualInputResinMachine()
    {
         String query =  ("SELECT * FROM machine WHERE  (NAME NOT LIKE 'Dryer' AND NAME NOT LIKE 'Stenter') AND MachineType = 1;");
        return GetAllMachine(query);
    }
    
    //END CUSTOMER REPOSITORY METHODS
    //BEGIN MACHINE REPOSITORY METHODS
    public ArrayList<Machine> GetAllMachine(String query) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Machine> MachineList = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            if(query == null)
                ps = conn.prepareStatement("SELECT * FROM machine");
            else 
                ps = conn.prepareCall(query);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                Machine machine = new Machine();

                machine.setMachineId(rs.getInt("ID"));
                machine.setMachineName(rs.getString("Name"));
                machine.setMaxCapacity(rs.getInt("MaxCapacity"));
                machine.setMinCapacity(rs.getInt("MinCapacity"));
                machine.setMaxVolume(rs.getInt("MaxVolume"));
                machine.setMinVolume(rs.getInt("MinVolume"));
                machine.setNumOfLoad(rs.getInt("NumOfLoad"));
                machine.setMachineType(rs.getInt("MachineType"));

                MachineList.add(machine);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return MachineList;
    }
    
    public ArrayList<Machine> GetAllDyeingMachine()
    {
        String Query = "SELECT * FROM machine WHERE MachineType = 0 ORDER BY Name ASC";
        return GetAllMachine(Query);
    }
    
    public ArrayList<Machine> GetAllResinMachine()
    {
        String Query = "SELECT * FROM machine WHERE MachineType = 1 ORDER BY Name ASC";
        return GetAllMachine(Query);
    }
    
    public boolean AddDryerAndStenter()
    {
        boolean isSuccess = true;
        Machine ResinMachines = new Machine();
        ResinMachines.setMachineType(1);
        ResinMachines.setMinVolume(1);
        ResinMachines.setMaxVolume(1);
        ResinMachines.setMinCapacity(0);
        ResinMachines.setMaxCapacity(0);
        //Machine StenterMachine = ResinMachines;
        ResinMachines.setMachineName("Dryer");
        if(!AddMachine(ResinMachines))
            isSuccess = false;
        
        ResinMachines.setMachineName("Stenter");
        if(!AddMachine(ResinMachines))
            isSuccess = false;
        
        return isSuccess;
    }

    public boolean AddMachine(Machine newMachine) {

        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO machine (Name, MaxCapacity, MinCapacity, MaxVolume, MinVolume, NumOfLoad, MachineType) VALUES (?, ?, ?, ? ,?, ?, ?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newMachine.getMachineName().toUpperCase());
            preparedStmt.setInt(2, newMachine.getMaxCapacity());
            preparedStmt.setInt(3, newMachine.getMinCapacity());
            preparedStmt.setInt(4, newMachine.getMaxVolume());
            preparedStmt.setInt(5, newMachine.getMinVolume());
            preparedStmt.setInt(6, newMachine.getNumOfLoad());
            preparedStmt.setInt(7, newMachine.getMachineType());
            preparedStmt.execute();

            added = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateMachineByMachineId(Machine machine) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE machine SET Name = ?, MaxCapacity = ?, MinCapacity = ?, MaxVolume = ?, MinVolume = ?, NumOfLoad = ?, MachineType = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, machine.getMachineName().toUpperCase());
            preparedStmt.setInt(2, machine.getMaxCapacity());
            preparedStmt.setInt(3, machine.getMinCapacity());
            preparedStmt.setInt(4, machine.getMaxVolume());
            preparedStmt.setInt(5, machine.getMinVolume());
            preparedStmt.setInt(6, machine.getNumOfLoad());
            preparedStmt.setInt(7, machine.getMachineType());
            preparedStmt.setInt(8, machine.getMachineId());

            preparedStmt.executeUpdate();

            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteMachineByMachineId(int machineId) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "DELETE FROM machine Where ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, machineId);
            preparedStmt.execute();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public int CheckIfMachineExists(int machineId) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM machine WHERE "
                    + " ID = ?) "
                    + " AS 'CheckTest'");

            ps.setInt(1, machineId);
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public int CheckIfMachineNameExists(String MachineName) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM machine WHERE "
                    + " name = ?) "
                    + " AS 'CheckTest'");

            ps.setString(1, MachineName);
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public Machine GetMachineDetailsById(int machineId) {
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

            if (resultSet.first()) {
                machineDetails.setMachineId(resultSet.getInt("ID"));
                machineDetails.setMachineName(resultSet.getString("Name"));
                machineDetails.setMaxCapacity(resultSet.getInt("MaxCapacity"));
                machineDetails.setMinCapacity(resultSet.getInt("MinCapacity"));
                machineDetails.setMaxVolume(resultSet.getInt("MaxVolume"));
                machineDetails.setMinVolume(resultSet.getInt("MinVolume"));
                machineDetails.setNumOfLoad(resultSet.getInt("NumOfLoad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return machineDetails;
    }

    public int GetMachineIdByName(String machineName) {
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

            if (resultSet.first()) {
                machineId = resultSet.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return machineId;
    }
    //END MACHINE REPOSITORY METHODS

    /**
     * ************************************************************************************************************************
     */
    /**
     * *
     *
     * @param newDyeingProgram
     * @return
     */
    //BEGIN DYEING PROGRAM REPO METHODS
    public int AddDyeingProgram(DyeingProgram newDyeingProgram) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        //boolean added = false;
        int DyeingProgramID = -1;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO dyeing_program (ProgramNameID, CustomerID, ColorID, DesignID) VALUES (?, ?, ?, ?)";

            //preparedStmt = conn.prepareStatement(query);
            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, newDyeingProgram.getDyeingProgramNameID());
            preparedStmt.setInt(2, newDyeingProgram.getCustomerID());
            preparedStmt.setInt(3, newDyeingProgram.getColorID());
            preparedStmt.setInt(4, newDyeingProgram.getDesignID());
            preparedStmt.execute();

            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                DyeingProgramID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            //added = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return DyeingProgramID;
    }

    public boolean UpdateDyeingProgramByDyeingProgramId(DyeingProgram dyeingProgram) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE dyeing_program SET ProgramNameID = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, dyeingProgram.getDyeingProgramNameID());
            preparedStmt.setInt(2, dyeingProgram.getID());
            preparedStmt.execute();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteDyeingProgramByDyeingProgramId(int dyeingProgramId) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "DELETE FROM dyeing_program WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, dyeingProgramId);
            preparedStmt.execute();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public int CheckIfDyeingProgramExistsUsingID(int dyeingProgramId) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_program WHERE "
                    + " ID = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, dyeingProgramId);
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public int CheckIfDyeingProgramNameExists(String dyeingProgramName) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_program_name WHERE "
                    + " Name = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, dyeingProgramName);
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public void CheckAndAddIfDryerAndStenterHasBeenAdded()
    {
        
    }

    public int CheckIfDyeingProgramNameOnOtherIDExists(DyeingProgramName ThisDyeProgramName) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_program_name WHERE "
                    + " Name = ?"
                    + " AND ID != ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ThisDyeProgramName.getDyeingProgramName());
            ps.setInt(item++, ThisDyeProgramName.getID());
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public ArrayList<String> GetAllDefaultDyeingProgram() {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> DyeingList = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM dyeing_program rp, resin_program_name rpn "
                    + "WHERE CustomerID = 0 AND rpn.ID = rp.ProgramNameID;");
            rs = ps.executeQuery();

            while (rs.next()) {
                DyeingList.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return DyeingList;
    }
    
    public int CheckIfSpecificDyeingProgramExistsForThisCustomer(DyeingProgram thisDyeingProgram)
    {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            /*ps = conn.prepareStatement(
                    " SELECT EXISTS "
                    + "(SELECT dyeing_program.ID "
                    + " FROM dyeing_program, dyeing_program_name "
                    + " WHERE Name = ? "
                    + " AND ProgramNameID = dyeing_program_name.ID"
                    + " AND dyeing_program.ID "
                    + " IN (SELECT DyeingProgramID FROM job_order WHERE CustomerId = ?))"
                    + " AS 'CheckTest'");
*/
            ps = conn.prepareStatement(
                    " SELECT EXISTS "
                    + "(SELECT dyeing_program.ID "
                    + " FROM dyeing_program "
                    + " WHERE ColorID = ?"
                    + " AND DesignID = ?"
                    + " AND CustomerID = ?"
                    + " AND ProgramNameID = ?) "
                    + " AS 'CheckTest'");
            
            int item = 1;
            ps.setInt(item++, thisDyeingProgram.getColorID());
            ps.setInt(item++, thisDyeingProgram.getDesignID());
            ps.setInt(item++, thisDyeingProgram.getCustomerID());
            ps.setInt(item++, thisDyeingProgram.getDyeingProgramNameID());

            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public int CheckIfSpecificDyeingProgramExistsForThisCustomer(String dyeingProgramName, JobOrderExtended thisJobOrder) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            /*ps = conn.prepareStatement(
                    " SELECT EXISTS "
                    + "(SELECT dyeing_program.ID "
                    + " FROM dyeing_program, dyeing_program_name "
                    + " WHERE Name = ? "
                    + " AND ProgramNameID = dyeing_program_name.ID"
                    + " AND dyeing_program.ID "
                    + " IN (SELECT DyeingProgramID FROM job_order WHERE CustomerId = ?))"
                    + " AS 'CheckTest'");
*/
            ps = conn.prepareStatement(
                    " SELECT EXISTS "
                    + "(SELECT dyeing_program.ID "
                    + " FROM dyeing_program, dyeing_program_name "
                    + " WHERE Name = ? "
                    + " AND ColorID = ?"
                    + " AND DesignID = ?"
                    + " AND CustomerID = ?"
                    + " AND ProgramNameID = dyeing_program_name.ID ) "
                    + " AS 'CheckTest'");
            
            int item = 1;
            ps.setString(item++, dyeingProgramName);
            ps.setInt(item++, thisJobOrder.getColorID());
            ps.setInt(item++, thisJobOrder.getDesignID());
            ps.setInt(item++, thisJobOrder.getCustomerID());

            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public int GetDyeingProgramIDForThisDyeingProgramDetails(DyeingProgram thisDyeingProgram) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DyeingProgramID = -1;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    " SELECT ID FROM dyeing_program "
                    + " WHERE ProgramNameID = ?"
                    + " AND ColorID = ?"
                    + " AND DesignID = ?"
                    + " AND CustomerID = ?;");
                   // + " AND dyeing_program.ID IN (SELECT DISTINCT(DyeingProgramID) FROM job_order WHERE CustomerId = ?)");

            int item = 1;
            ps.setInt(item++, thisDyeingProgram.getDyeingProgramNameID());
            ps.setInt(item++, thisDyeingProgram.getColorID());
            ps.setInt(item++, thisDyeingProgram.getDesignID());
            ps.setInt(item++, thisDyeingProgram.getCustomerID());
            rs = ps.executeQuery();
            if (rs.first()) {
                DyeingProgramID = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DyeingProgramID;
    }

    public int GetDyeingProgramIDForThisCustomer(String dyeingProgramName, JobOrderExtended thisJobOrder) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DyeingProgramID = -1;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    " SELECT dyeing_program.ID FROM dyeing_program, dyeing_program_name "
                    + " WHERE Name = ?"
                    + " AND ColorID = ?"
                    + " AND DesignID = ?"
                    + " AND CustomerID = ?"
                    + " AND ProgramNameID = dyeing_program_name.ID");
                   // + " AND dyeing_program.ID IN (SELECT DISTINCT(DyeingProgramID) FROM job_order WHERE CustomerId = ?)");

            int item = 1;
            ps.setString(item++, dyeingProgramName);
            ps.setInt(item++, thisJobOrder.getColorID());
            ps.setInt(item++, thisJobOrder.getDesignID());
            ps.setInt(item++, thisJobOrder.getCustomerID());
            rs = ps.executeQuery();
            if (rs.first()) {
                DyeingProgramID = rs.getInt("dyeing_program.ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DyeingProgramID;
    }

    public DyeingProgram GetDyeingProgramDetailsById(int dyeingProgramId) {
        DBConnection db = new DBConnection();
        DyeingProgram dyeingProgramDetails = new DyeingProgram();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM dyeing_program WHERE ID = ?;";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, dyeingProgramId);
            resultSet = preparedStmt.executeQuery();
            if (resultSet.first()) {
                dyeingProgramDetails.SetID(resultSet.getInt("ID"));
                dyeingProgramDetails.setDyeingProgramNameID(resultSet.getInt("ProgramNameID"));
                dyeingProgramDetails.setCustomerID(resultSet.getInt("CustomerID"));
                dyeingProgramDetails.setColorID(resultSet.getInt("ColorID"));
                dyeingProgramDetails.setDesignID(resultSet.getInt("DesignID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return dyeingProgramDetails;
    }
    public void PrintJobOrderDetailstToSystemLine(JobOrder thisJobOrder)
    {
        System.out.println("ID = "+ thisJobOrder.getID());
        System.out.println("DR Number = "+ thisJobOrder.getDrNumber());
        System.out.println("Design ID = "+ thisJobOrder.getDesignID());
        System.out.println("Color ID = "+ thisJobOrder.getColorID());
        System.out.println("Customer ID = "+ thisJobOrder.getCustomerID());
        System.out.println("Date = "+ thisJobOrder.getJobDate());
        System.out.println("Batch No = "+ thisJobOrder.getBatchNo());
        System.out.println("Dyeing Machine ID = "+ thisJobOrder.getDyeingMachineID());
        System.out.println("Dyeing Program ID = "+ thisJobOrder.getDyeingProgramID());
        System.out.println("Dyeing Weight = "+ thisJobOrder.getDyeingWeight());
        System.out.println("Dyeing Volume H2O= "+ thisJobOrder.getDyeingVolumeH20());
        System.out.println("Roll = "+ thisJobOrder.getRoll());
        System.out.println("Roll Load = "+ thisJobOrder.getRollLoad());
        System.out.println("Reference = "+ thisJobOrder.getReference());
        System.out.println("Program Number = "+ thisJobOrder.getProgramNumber());
    }
    
    public DyeingProgram getDefaultDyeingProgramForThisDyeingProgramID(DyeingProgram thisDyeingProgram) {
        DyeingProgram dyeingProgramDetails = new DyeingProgram();
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //int DyeingProgramID = -1;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    " SELECT ID FROM dyeing_program "
                    + " WHERE ProgramNameID = ? "
                    + " AND  CustomerID = 0 "
                    + " AND  ColorID = 0 "
                    + " AND  DesignID = 0;"
            );
            
            int item = 1;
            ps.setInt(item++, thisDyeingProgram.getDyeingProgramNameID());
//            ps.setInt(item++, thisJobOrder.getCustomerID());
//            ps.setInt(item++, thisJobOrder.getColorID());
//            ps.setInt(item++, thisJobOrder.getDesignID());
            rs = ps.executeQuery();
            if (rs.first()) {
                dyeingProgramDetails.SetID(rs.getInt("ID"));
                dyeingProgramDetails.setDyeingProgramNameID(thisDyeingProgram.getDyeingProgramNameID());
                dyeingProgramDetails.setColorID(0);
                dyeingProgramDetails.setDesignID(0);
                dyeingProgramDetails.setCustomerID(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return dyeingProgramDetails;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //END DYEING PROGRAM REPO METHODS
    /**
     * *********************************************************************************************
     */
    /**
     * ***************************** FOR Dyeing Program Name
     *
     * @param DyeProgramName ***************************************************
     */
    public int AddDyeingProgramName(String DyeProgramName) {
        DBConnection db = new DBConnection();

        Connection conn = null;
        PreparedStatement preparedStmt = null;
        //boolean added = false;
        int DyeingProgramID = -1;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO dyeing_program_name (Name) VALUES (?)";

            //preparedStmt = conn.prepareStatement(query);
            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, DyeProgramName);
            preparedStmt.execute();

            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                DyeingProgramID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            //added = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return DyeingProgramID;
    }

    public int GetDyeingProgramNameIDFromName(String DyeingProgramName) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int DyeingProgramID = -1;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                    + " FROM dyeing_program_name "
                    + " WHERE Name = ? ");

            ps.setString(1, DyeingProgramName);

            rs = ps.executeQuery();
            if (rs.first()) {
                DyeingProgramID = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DyeingProgramID;
    }

    public String GetDyeingProgramNameFromID(int ID) {

        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String DyeingProgramName = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT Name "
                    + " FROM dyeing_program_name "
                    + " WHERE ID = ? ");

            ps.setInt(1, ID);

            rs = ps.executeQuery();
            if (rs.first()) {
                DyeingProgramName = rs.getString("Name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DyeingProgramName;
    }

    public boolean UpdateDyeingProgramName(DyeingProgramName thisDyeProgName) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE dyeing_program_name SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, thisDyeProgName.getDyeingProgramName());
            preparedStmt.setInt(2, thisDyeProgName.getID());
            preparedStmt.execute();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isSuccessful;
    }

    public ArrayList<String> GetAllDyeingProgramName() {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> DyeingList = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM dyeing_program_name ");
            rs = ps.executeQuery();

            while (rs.next()) {
                DyeingList.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return DyeingList;
    }

    /**
     * *********************************************************************************************
     */
    /**
     * ***************************** FOR Process Order ***************************************************
     */
    public int AddProcessOrder(ProcessOrder thisProcessOrder) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        int ProcessOrder = -1;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO process_order (JobOrderID, Weight, VolH2O, RollLoad, Roll, DyeingProgramID, ResinProgramID) VALUES (?, ?, ?, ?, ?, ?, ?)";

            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, thisProcessOrder.getJobOrderID());
            preparedStmt.setFloat(2, thisProcessOrder.getWeight());
            preparedStmt.setFloat(3, thisProcessOrder.getVolumeH20());
            preparedStmt.setString(4, thisProcessOrder.getRollLoad());
            preparedStmt.setFloat(5, thisProcessOrder.getRoll());
            preparedStmt.setInt(6, thisProcessOrder.getDyeingProgramID());
            preparedStmt.setInt(7, thisProcessOrder.getResinProgramID());

            preparedStmt.execute();
            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                ProcessOrder = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return ProcessOrder;
    }

    public boolean UpdateProcessOrderByProcessOrderId(ProcessOrder thisProcessOrder) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE process_order SET JobOrderID = ? , Weight = ?, VolH2O = ?, RollLoad = ? , Roll = ? , DyeingProgramID = ?, ResinProgramID = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisProcessOrder.getJobOrderID());
            preparedStmt.setFloat(2, thisProcessOrder.getWeight());
            preparedStmt.setFloat(3, thisProcessOrder.getVolumeH20());
            preparedStmt.setString(4, thisProcessOrder.getRollLoad());
            preparedStmt.setFloat(5, thisProcessOrder.getRoll());
            preparedStmt.setInt(6, thisProcessOrder.getDyeingProgramID());
            preparedStmt.setInt(7, thisProcessOrder.getResinProgramID());
            preparedStmt.setInt(8, thisProcessOrder.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;

        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM process_order WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ProcessOrderID);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    /**
     * *********************************************************************************************
     */
    /**
     * ***************************** FOR JOB ORDER **************************************************
     */
    public int AddJobOrder(JobOrder newJobOrder) {
        /*DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        //boolean added = false;
        int JobOrderID = -1;*/
        
        String query = "INSERT INTO job_order (DrNumber, "
                    + " DesignID, "
                    + " ColorID, "
                    + " CustomerID, "
                    + " Date, "
                    + " BatchNo, "
                    + " DyeingMachineID, "
                    + " DyeingWeight, "
                    + " DyeingVolH2O, "
                    //+ " ResinMachineID, "
                    //+ " ResinWeight, "
                    //+ " ResinVolH2O, "
                    + " RollLoad, "
                    + " Roll, "
                    + " DyeingProgramID, "
                    //+ " ResinProgramID, "
                    + " Reference, "
                    + " ProgramNumber, "
                    //                     1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19
                    + " Location ) VALUES ("
                //Removed 4 Resin Data to a new Table 
                //+ "?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?)";
        
        return setJobOrderAndExecuteStatement(newJobOrder, query, 1);
        
        /**
        try {
            conn = db.getConnection();
           
           preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int itemNumber = 1;
            preparedStmt.setString(itemNumber++, newJobOrder.getDrNumber().toUpperCase());
            preparedStmt.setInt(itemNumber++, newJobOrder.getDesignID());
            preparedStmt.setInt(itemNumber++, newJobOrder.getColorID());
            preparedStmt.setInt(itemNumber++, newJobOrder.getCustomerID());
            preparedStmt.setString(itemNumber++, newJobOrder.getJobDate());
            preparedStmt.setInt(itemNumber++, newJobOrder.getBatchNo());

            preparedStmt.setInt(itemNumber++, newJobOrder.getDyeingMachineID());
            preparedStmt.setFloat(itemNumber++, newJobOrder.getDyeingWeight());
            preparedStmt.setFloat(itemNumber++, newJobOrder.getDyeingVolumeH20());

            preparedStmt.setInt(itemNumber++, newJobOrder.getResinMachineID());
            preparedStmt.setFloat(itemNumber++, newJobOrder.getResinWeight());
            preparedStmt.setFloat(itemNumber++, newJobOrder.getResinVolumeH20());

            preparedStmt.setString(itemNumber++, newJobOrder.getRollLoad());
            preparedStmt.setFloat(itemNumber++, newJobOrder.getRoll());
            preparedStmt.setInt(itemNumber++, newJobOrder.getDyeingProgramID());
            preparedStmt.setInt(itemNumber++, newJobOrder.getResinProgramID());
            preparedStmt.setString(itemNumber++, newJobOrder.getReference());
            preparedStmt.setString(itemNumber++, newJobOrder.getProgramNumber());
            preparedStmt.setString(itemNumber++, newJobOrder.getLocation());
            preparedStmt.execute();

            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                JobOrderID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        */
        
    }
//Type = 1 For add 2 = for update. Difference is update needs ID at the end

    public int setJobOrderAndExecuteStatement(JobOrder thisJobOrder, String query, int type) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        //boolean added = false;
        int JobOrderID = -1;
        try {
            conn = db.getConnection();
            int itemNumber = 1;
            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(itemNumber++, thisJobOrder.getDrNumber()); //1
            preparedStmt.setInt(itemNumber++, thisJobOrder.getDesignID()); //2
            preparedStmt.setInt(itemNumber++, thisJobOrder.getColorID()); //3
            preparedStmt.setInt(itemNumber++, thisJobOrder.getCustomerID()); //4
            preparedStmt.setString(itemNumber++, thisJobOrder.getJobDate()); //5
            preparedStmt.setString(itemNumber++, thisJobOrder.getBatchNo()); //6

            preparedStmt.setInt(itemNumber++, thisJobOrder.getDyeingMachineID()); //7
            preparedStmt.setFloat(itemNumber++, thisJobOrder.getDyeingWeight());  //8
            preparedStmt.setFloat(itemNumber++, thisJobOrder.getDyeingVolumeH20()); //9

            preparedStmt.setString(itemNumber++, thisJobOrder.getRollLoad()); //13
            preparedStmt.setFloat(itemNumber++, thisJobOrder.getRoll());      //14
            preparedStmt.setInt(itemNumber++, thisJobOrder.getDyeingProgramID()); //15
            
            preparedStmt.setString(itemNumber++, thisJobOrder.getReference());    //17
            preparedStmt.setString(itemNumber++, thisJobOrder.getProgramNumber());//18
            preparedStmt.setString(itemNumber++, thisJobOrder.getLocation());     //19
           
            //preparedStmt.setInt(itemNumber++, thisJobOrder.getResinMachineID()); //10
            //preparedStmt.setFloat(itemNumber++, thisJobOrder.getResinWeight()); //11
            //preparedStmt.setFloat(itemNumber++, thisJobOrder.getResinVolumeH20()); //12
            //preparedStmt.setInt(itemNumber++, thisJobOrder.getResinProgramID());  //16
            //IF type ==2 == update
            if(type == 2)
            {
                preparedStmt.setInt(itemNumber++, thisJobOrder.getID());
                preparedStmt.executeUpdate();
                JobOrderID= 1;
            }
            //Add Job Order
            else
            {
                preparedStmt.execute();
                ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    JobOrderID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return JobOrderID;
    }

    public boolean UpdateJobOrderByJobOrderID(JobOrder thisJobOrder) {
        
        String query = "UPDATE job_order SET DrNumber = ?, "
                    + " DesignID = ?, "
                    + " ColorID = ?, "
                    + " CustomerID = ?, "
                    + " Date = ?, "
                    + " BatchNo = ?, "
                    + " DyeingMachineID = ? , "
                    + " DyeingWeight = ?, "
                    + " DyeingVolH2O = ?, "
                    //+ " ResinMachineID = ? , "
                    //+ " ResinWeight = ?, "
                    //+ " ResinVolH2O = ?, "
                    + " RollLoad = ?, "
                    + " Roll = ?, "
                    + " DyeingProgramID = ?, "
                    //+ " ResinProgramID = ?,"
                    + " Reference = ?,"
                    + " ProgramNumber = ?,"
                    + " Location = ? "
                    + " WHERE ID = ?";
        
        //Check if JobOrder has been updated. This will return !=1 if job Order has not yet been added.
        return setJobOrderAndExecuteStatement(thisJobOrder, query, 2) != -1; 
        
        /*
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
        conn = db.getConnection();
        int itemNumber = 1;
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(itemNumber++, thisJobOrder.getDrNumber());
        preparedStmt.setInt(itemNumber++, thisJobOrder.getDesignID());
        preparedStmt.setInt(itemNumber++, thisJobOrder.getColorID());
        preparedStmt.setInt(itemNumber++, thisJobOrder.getCustomerID());
        preparedStmt.setString(itemNumber++, thisJobOrder.getJobDate());
        preparedStmt.setInt(itemNumber++, thisJobOrder.getBatchNo());
        preparedStmt.setInt(itemNumber++, thisJobOrder.getDyeingMachineID());
        preparedStmt.setFloat(itemNumber++, thisJobOrder.getDyeingWeight());
        preparedStmt.setFloat(itemNumber++, thisJobOrder.getDyeingVolumeH20());
        preparedStmt.setInt(itemNumber++, thisJobOrder.getResinMachineID());
        preparedStmt.setFloat(itemNumber++, thisJobOrder.getResinWeight());
        preparedStmt.setFloat(itemNumber++, thisJobOrder.getResinVolumeH20());
        preparedStmt.setString(itemNumber++, thisJobOrder.getRollLoad());
        preparedStmt.setFloat(itemNumber++, thisJobOrder.getRoll());
        preparedStmt.setInt(itemNumber++, thisJobOrder.getDyeingProgramID());
        preparedStmt.setInt(itemNumber++, thisJobOrder.getResinProgramID());
        preparedStmt.setString(itemNumber++, thisJobOrder.getReference());
        preparedStmt.setString(itemNumber++, thisJobOrder.getProgramNumber());
        preparedStmt.setString(itemNumber++, thisJobOrder.getLocation());
        preparedStmt.setInt(itemNumber++, thisJobOrder.getID());
        preparedStmt.executeUpdate();
        isSuccessful = true;
        } catch (SQLException ex) {
        Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, preparedStmt);
         */
        
    }

    public boolean DeleteJobOrderByJobOrderID(int JobOrderId) {

        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "DELETE FROM job_order WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, JobOrderId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public int GetJobOrderIDFromDrNumber(String drNumber) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int JobOrderID = -1;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                    + "FROM job_order "
                    + "WHERE DrNumber = ? ");

            ps.setString(1, drNumber);

            rs = ps.executeQuery();
            if (rs.first()) {
                JobOrderID = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return JobOrderID;
    }

    public JobOrder GetJobOrderDetailsFromJobOrder(JobOrder thisJO)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JobOrder thisJobOrder = new JobOrder();
        int x = 1;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                    + "FROM job_order "
                    + "WHERE DrNumber = ?"
                    + "AND DATE = ?"
                    + "AND DyeingMachineID = ? ");

            ps.setString(x++, thisJO.getDrNumber());
            ps.setString(x++, thisJO.getJobDate());
            ps.setInt(x++, thisJO.getDyeingProgramID());

            rs = ps.executeQuery();
            if (rs.first()) {
                thisJobOrder = GetJobOrderDataFromResultSet(rs);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisJobOrder;
    }
    public JobOrder GetJobOrderDetailsFromDrNumber(String DrNumber) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JobOrder thisJobOrder = new JobOrder();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                    + "FROM job_order "
                    + "WHERE DrNumber = ? ");

            ps.setString(1, DrNumber);

            rs = ps.executeQuery();
            if (rs.first()) {
                thisJobOrder = GetJobOrderDataFromResultSet(rs);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisJobOrder;
    }
    
    

    public JobOrder GetJobOrderDetailsFromJobOrderID(int ID) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JobOrder thisJobOrder = new JobOrder();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                    + "FROM job_order "
                    + "WHERE ID = ? ");

            ps.setInt(1, ID);

            rs = ps.executeQuery();
            if (rs.first()) {
                thisJobOrder = GetJobOrderDataFromResultSet(rs);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisJobOrder;
    }
    
    public JobOrder GetJobOrderDataFromResultSet(ResultSet rs) 
    {
        JobOrder thisJobOrder = new JobOrder();
        try {
            thisJobOrder.setID(rs.getInt("ID"));//1
            thisJobOrder.setDrNumber(rs.getString("DrNumber"));//2
            thisJobOrder.setDesignID(rs.getInt("DesignID"));//3
            thisJobOrder.setColorID(rs.getInt("ColorID"));//4
            thisJobOrder.setCustomerID(rs.getInt("CustomerID"));//5
            thisJobOrder.setJobDate(rs.getString("Date"));//6
            thisJobOrder.setBatchNo(rs.getString("BatchNo"));//7
            thisJobOrder.setReference(rs.getString("Reference"));//8
            thisJobOrder.setProgramNumber(rs.getString("ProgramNumber"));//9
            thisJobOrder.setLocation(rs.getString("Location"));//10

            thisJobOrder.setDyeingMachineID(rs.getInt("DyeingMachineID"));//11
            thisJobOrder.setDyeingWeight(rs.getFloat("DyeingWeight"));//12
            thisJobOrder.setDyeingVolumeH20(rs.getFloat("DyeingVolH2O"));//13

            thisJobOrder.setRollLoad(rs.getString("RollLoad"));//14
            thisJobOrder.setRoll(rs.getFloat("Roll"));//15
            thisJobOrder.setDyeingProgramID(rs.getInt("DyeingProgramID"));//16

            //thisJobOrder.setResinMachineID(rs.getInt("ResinMachineID"));
            //thisJobOrder.setResinWeight(rs.getFloat("ResinWeight"));
            //thisJobOrder.setResinVolumeH20(rs.getFloat("ResinVolH2O"));
            //thisJobOrder.setResinProgramID(rs.getInt("ResinProgramID"));
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thisJobOrder;

    }

    public ArrayList<JobOrderExtended> GetConnectedJobOrderDetails() {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<JobOrderExtended> thisJobOrder = new ArrayList<>();
        try {
            conn = db.getConnection();
/*
            ps = conn.prepareStatement("SELECT job_order.ID,"
                    + " DrNumber , "
                    + " Date , "
                    + " col.Name as coName, "
                    + " cus.Name as cName, "
                    + " des.Name as dName , "
                    + " DyeingMach.Name as DyeingMachineName, " 
                    + " ResMach.Name as ResinMachineName, "
                    + " dProgName.Name as dpName, "
                    + " resin_program_name.Name as rpName "
                    + " FROM color col, customer cus , design des, "
                    + " job_order LEFT JOIN Resin_Program ON job_order.ResinProgramID = resin_program.ID "
                    + " LEFT JOIN resin_program_name ON Resin_program.ProgramNameID = resin_program_name.ID "
                    + " LEFT JOIN machine ResMach ON ResMach.ID = job_order.ResinMachineID ," 
                    + " machine DyeingMach, "
                    + " dyeing_program dProg, "
                    + " dyeing_program_name dProgName "
                    + " WHERE col.ID = ColorID "
                    + " AND cus.ID = CustomerID  "
                    + " AND des.ID = designID "
                    + " AND DyeingMach.ID = DyeingMachineID "
                    //+ " AND ResinMach.ID = ResinMachineID"
                    + " AND dProg.ID = DyeingProgramID "
                    + " AND dProg.ProgramNameID = dProgName.ID"
                    + " ORDER BY Date desc;");
            */
            ps = conn.prepareStatement("SELECT job_order.ID, DrNumber ,  Date ,  "
                    + " col.Name as coName,  cus.Name as cName,"
                    + " des.Name as dName ,  DyeingMach.Name as DyeingMachineName, "
                    + " ResMach.Name as ResinMachineName,  dProgName.Name as dpName,"
                    + " resin_program_name.Name as rpName "
                    + " FROM color col, customer cus , design des,  job_order "
                    + " LEFT JOIN resin_job ON job_order.ID = resin_job.JobOrderID "
                    + " LEFT JOIN resin_program ON resin_program.ID = resin_job.ResinProgramID "
                    + " LEFT JOIN resin_program_name ON Resin_program.ProgramNameID = resin_program_name.ID"
                    + " LEFT JOIN machine ResMach ON ResMach.ID = resin_job.ResinMachineID ,"
                    + " machine DyeingMach, "
                    + " dyeing_program dProg, "
                    + " dyeing_program_name dProgName"
                    + " WHERE col.ID = job_order.ColorID "
                    + " AND cus.ID = job_order.CustomerID  "
                    + " AND des.ID = job_order.designID "
                    + " AND DyeingMach.ID = DyeingMachineID "
                    + " AND dProg.ID = DyeingProgramID "
                    + " AND dProg.ProgramNameID = dProgName.ID"
                    + " GROUP BY job_order.ID ORDER BY Date desc, job_order.ID desc"
                    + " ;");

            rs = ps.executeQuery();
            thisJobOrder = SetJobOrderArrayListFromResultSet(rs);
            /*
            JobOrderExtended currentJobOrder;
            while (rs.next()) {
                currentJobOrder = new JobOrderExtended();
                currentJobOrder.setDrNumber(rs.getString("DrNumber"));
                currentJobOrder.setJobDate(rs.getString("Date"));
                currentJobOrder.setColorName(rs.getString("coName"));
                currentJobOrder.setCustomerName(rs.getString("cName"));
                currentJobOrder.setDesignName(rs.getString("dName"));
                currentJobOrder.setDyeingMachineName(rs.getString("DyeingMachineName"));
                currentJobOrder.setDyeingProgramName(rs.getString("dpName"));
                
                if (rs.getString("rpName") == null || rs.getString("ResinMachineName") == null) {
                    currentJobOrder.setResinProgramName("");
                    currentJobOrder.setResinMachineName("");
                } else {
                    currentJobOrder.setResinMachineName(rs.getString("ResinMachineName"));
                    currentJobOrder.setResinProgramName(rs.getString("rpName"));
                    
                }
                thisJobOrder.add(currentJobOrder);
            }
*/
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisJobOrder;

    }
    
    public ArrayList<JobOrderExtended> GetConnectedJobOrderDetails(int customerId) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<JobOrderExtended> thisJobOrder = new ArrayList<>();
        try {
            conn = db.getConnection();

            ps = conn.prepareStatement("SELECT job_order.ID, DrNumber ,  Date ,  "
                    + " col.Name as coName,  cus.Name as cName,"
                    + " des.Name as dName ,  DyeingMach.Name as DyeingMachineName, "
                    + " ResMach.Name as ResinMachineName,  dProgName.Name as dpName,"
                    + " resin_program_name.Name as rpName "
                    + " FROM color col, customer cus , design des,  job_order "
                    + " LEFT JOIN resin_job ON job_order.ID = resin_job.JobOrderID "
                    + " LEFT JOIN resin_program ON resin_program.ID = resin_job.ResinProgramID "
                    + " LEFT JOIN resin_program_name ON Resin_program.ProgramNameID = resin_program_name.ID"
                    + " LEFT JOIN machine ResMach ON ResMach.ID = resin_job.ResinMachineID ,"
                    + " machine DyeingMach, "
                    + " dyeing_program dProg, "
                    + " dyeing_program_name dProgName"
                    + " WHERE col.ID = job_order.ColorID "
                    + " AND cus.ID = job_order.CustomerID  "
                    + " AND des.ID = job_order.designID "
                    + " AND DyeingMach.ID = DyeingMachineID "
                    + " AND dProg.ID = DyeingProgramID "
                    + " AND dProg.ProgramNameID = dProgName.ID"
                    + " AND cus.ID = ?"
                    + " ORDER BY Date desc, job_order.ID desc;");
            
            //ps = conn.prepareStatement("SELECT DrNumber , Date , col.Name as coName, cus.Name as cName, \ndes.Name as dName , mach.Name as DyeingMachineName,  mach2.Name as ResinMachineName, \ndProgName.Name as dpName, resin_program_name.Name as rpName \nFROM color col, customer cus , design des, \njob_order LEFT JOIN Resin_Program ON job_order.ResinProgramID = resin_program.ID\nLEFT JOIN resin_program_name ON Resin_program.ProgramNameID = resin_program_name.ID,\nmachine mach, dyeing_program dProg, dyeing_program_name dProgName ,\nmachine mach2\nWHERE col.ID = ColorID \nAND cus.ID = CustomerID  \nAND des.ID = designID \nAND mach.ID = DyeingMachineID \nAND mach2.ID = ResinMachineID \nAND dProg.ID = DyeingProgramID \nAND dProg.ProgramNameID = dProgName.ID \nAND cus.ID = ?");

            /*ps = conn.prepareStatement("SELECT DrNumber , Date ,  resin_program_name.Name as rName " +
             " FROM job_order LEFT JOIN Resin_Program ON job_order.ResinProgramID = resin_program.ID " +
             " Left JOIN resin_program_name ON Resin_program.ProgramNameID = resin_program_name.ID;");
            
             ps = conn.prepareStatement("SELECT DrNumber , Date , col.Name as \"DesignColor Name\", cus.Name as \"Customer Name\", des.Name as \"Design Name\" , mach.Name as \"Machine Name\", " +
             " dProgName.Name as \"Dyeing Program Name\", rProgName.Name as \"Resin Program Name\" " +
             " FROM color col, customer cus , design des, job_order, machine mach, " +
             " dyeing_program dProg, dyeing_program_name dProgName, resin_program rProg, resin_program_name rProgName " +
             " WHERE col.ID = ColorID " +
             " AND cus.ID = CustomerID " +
             " AND des.ID = designID " +
             " AND mach.ID = MachineID " +
             " AND dProg.ID = DyeingProgramID " +
             " AND dProg.ProgramNameID = dProgName.ID " +
             " AND rProg.ID = ResinProgramID " +
             " AND rProg.ProgramNameID = rProgName.ID;");
             */
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            thisJobOrder = SetJobOrderArrayListFromResultSet(rs);
            /*
            while (rs.next()) {
                JobOrderExtended currentJobOrder = new JobOrderExtended();
                currentJobOrder.setDrNumber(rs.getString("DrNumber"));
                currentJobOrder.setJobDate(rs.getString("Date"));
                currentJobOrder.setColorName(rs.getString("coName"));
                currentJobOrder.setCustomerName(rs.getString("cName"));
                currentJobOrder.setDesignName(rs.getString("dName"));
                currentJobOrder.setDyeingProgramName(rs.getString("dpName"));
                currentJobOrder.setDyeingMachineName(rs.getString("DyeingMachineName"));
                if (rs.getString("rpName") == null || rs.getString("ResinMachineName") == null) 
                {
                    currentJobOrder.setResinProgramName("");
                    currentJobOrder.setResinMachineName("");
                } 
                else {
                    currentJobOrder.setResinMachineName(rs.getString("ResinMachineName"));
                    currentJobOrder.setResinProgramName(rs.getString("rpName"));
                }
                thisJobOrder.add(currentJobOrder);
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisJobOrder;

    }
    
    public ArrayList<JobOrderExtended> SetJobOrderArrayListFromResultSet(ResultSet rs)
    {
        ArrayList<JobOrderExtended> thisJobOrder = new ArrayList<>();
        try {
            while (rs.next()) {
                JobOrderExtended currentJobOrder = new JobOrderExtended();
                currentJobOrder.setID(rs.getInt("job_order.ID"));
                currentJobOrder.setDrNumber(rs.getString("DrNumber"));
                currentJobOrder.setJobDate(rs.getString("Date"));
                currentJobOrder.setColorName(rs.getString("coName"));
                currentJobOrder.setCustomerName(rs.getString("cName"));
                currentJobOrder.setDesignName(rs.getString("dName"));
                currentJobOrder.setDyeingProgramName(rs.getString("dpName"));
                currentJobOrder.setDyeingMachineName(rs.getString("DyeingMachineName"));
                if (rs.getString("rpName") == null || rs.getString("ResinMachineName") == null) 
                {
                    currentJobOrder.setResinProgramName("");
                    currentJobOrder.setResinMachineName("");
                } 
                else {
                    currentJobOrder.setResinMachineName(rs.getString("ResinMachineName"));
                    currentJobOrder.setResinProgramName(rs.getString("rpName"));
                }
                thisJobOrder.add(currentJobOrder);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return thisJobOrder;
    }

    public int CheckIfJobOrderExists(JobOrder thisJobOrder) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM job_order WHERE "
                    + " DrNumber = ?"
                    + " AND Date = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, thisJobOrder.getDrNumber());
            ps.setString(item++, thisJobOrder.getJobDate());
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;

    }

    /**
     * *********************************************************************************************
     */
    /**
     * ***************************** FOR Resin Program *********************************************
     */
    public int AddNewResinProgramName(ResinProgramName newResinProgramName) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        int resinProgramNameId = -1;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO resin_program_name (Name) VALUES (?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newResinProgramName.getName().toUpperCase());
            preparedStmt.executeUpdate();

            PreparedStatement ps = conn.prepareStatement("SELECT ID "
                    + " FROM resin_program_name "
                    + " WHERE Name = ? ");

            ps.setString(1, newResinProgramName.getName().toUpperCase());

            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                resinProgramNameId = rs.getInt("ID");
            }

            added = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return resinProgramNameId;
    }

    public int AddNewResinProgram(ResinProgram newResinProgram) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        int resinProgramId = -1;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO resin_program (ProgramNameID, CustomerID, ColorID, DesignID) VALUES (?, ?, ? , ?)";

            preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, newResinProgram.getProgramNameID());
            preparedStmt.setInt(2, newResinProgram.getCustomerID());
            preparedStmt.setInt(3, newResinProgram.getColorID());
            preparedStmt.setInt(4, newResinProgram.getDesignID());
            preparedStmt.execute();

            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                resinProgramId = generatedKeys.getInt(1);
            }

            added = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return resinProgramId;
    }

    public boolean UpdateResinProgramName(String newResinProgramName, int ResinProgramID) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE resin_program_name SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newResinProgramName.toUpperCase());
            preparedStmt.setInt(2, ResinProgramID);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean UpdateResinProgramByResinId(ResinProgram newResinProgram) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE resin_program SET Name = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            //preparedStmt.setString(1, newResinProgram.getName().toUpperCase());
            preparedStmt.setInt(2, newResinProgram.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean DeleteResinProgramNameByResinProgramNameId(int resinProgramNameId) {

        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "DELETE FROM resin_program_name WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, resinProgramNameId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM resin_program WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ResinProgramID);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public int GetResinIDFromResinName(String ResinName) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ResinID = -1;

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT rProg.ID "
                    + " FROM resin_program_name rProgName, resin_program rProg "
                    + " WHERE rProgName.Name = ? "
                    + " AND rProgName.ID = rProg.ProgramNameID "
                    + " AND CustomerID = 0"
                    + " AND ColorID = 0"
                    + " AND DesignID = 0");

            ps.setString(1, ResinName);

            rs = ps.executeQuery();
            if (rs.first()) {
                ResinID = rs.getInt("rProg.ID");
            }

            //ps = conn.prepareStatement("SELECT ID "
            //                     + " FROM resin_program "
            //                     + " WHERE ProgramNameID = ? AND ProgramDefault = 1");
            //ps.setInt(1, ResinNameID);
            //rs = ps.executeQuery();
            //if(rs.first())
            //{
            //    ResinID = rs.getInt("ID");
            //}
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ResinID;
    }

    public String GetResinNameFromResinID(int ResinID) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ResinName = "";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT rProgName.Name "
                    + " FROM resin_program rProg, resin_program_name rProgName"
                    + " WHERE rProg.ID = ? "
                    + " AND rProg.ProgramNameID = rProgName.ID");

            ps.setInt(1, ResinID);

            rs = ps.executeQuery();
            if (rs.first()) {
                ResinName = rs.getString("Name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ResinName;
    }
    
    public boolean CheckIfResinProgramExists(ResinProgram thisResinProgram) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean itExists = false;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM resin_program WHERE "
                    + " ProgramNameID = ? "
                    + " AND CustomerID = ? "
                    + " AND ColorID = ? "
                    + " AND DesignID = ? ) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, thisResinProgram.getProgramNameID());
            ps.setInt(item++, thisResinProgram.getCustomerID());
            ps.setInt(item++, thisResinProgram.getColorID());
            ps.setInt(item++, thisResinProgram.getDesignID());
            rs = ps.executeQuery();

            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        if (checkTest == 1) {
            itExists = true;
        }

        return itExists;
    }

    public boolean CheckIfResinProgramNameExists(String ResinName) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean itExists = false;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM resin_program_name WHERE "
                    + " Name = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ResinName);
            rs = ps.executeQuery();

            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        if (checkTest == 1) {
            itExists = true;
        }

        return itExists;
    }
    
    public boolean CheckIfResinProgramNameExistsOnDifferentResinProgram(String ResinName, int ResinProgramID) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean itExists = false;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM resin_program_name WHERE "
                    + " Name = ?"
                    + " AND ID != ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ResinName);
            ps.setInt(item++, ResinProgramID);
            rs = ps.executeQuery();

            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        if (checkTest == 1) {
            itExists = true;
        }

        return itExists;
    }

    public int CheckIfSpecificResinProgramExists(ResinProgram thisResinProgram) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement(
                    " SELECT EXISTS "
                    + "(SELECT resin_program.ID "
                    + " FROM resin_program "
                    + " AND CustomerID = ? "
                    + " AND ColorID = ? "
                    + " AND DesignID = ? )"
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, thisResinProgram.getCustomerID());
            ps.setInt(item++, thisResinProgram.getColorID());
            ps.setInt(item++, thisResinProgram.getDesignID());

            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public int CheckIfSpecificResinProgramExistsForThisCustomer(String ResinProgramName, ResinProgram thisResinProgram) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement(
                    " SELECT EXISTS "
                    + "(SELECT resin_program.ID "
                    + " FROM resin_program, resin_program_name "
                    + " WHERE Name = ? "
                    + " AND CustomerID = ? "
                    + " AND ColorID = ? "
                    + " AND DesignID = ? "
                    + " AND ProgramNameID = resin_program_name.ID)"
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, ResinProgramName);
            ps.setInt(item++, thisResinProgram.getCustomerID());
            ps.setInt(item++, thisResinProgram.getColorID());
            ps.setInt(item++, thisResinProgram.getDesignID());

            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }
//Error Change here
    public ResinProgram GetDefaultResinProgramForThisProgramName(String ResinProgramName) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResinProgram thisResinProgram = new ResinProgram();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    " SELECT rProg.ID, ProgramNameID, customerID, colorID, designID  "
                            + "FROM resin_program rProg, resin_program_name rProgName"
                    + " WHERE Name = ? "
                    + " AND CustomerID = 0 "
                    + " AND ColorID = 0 "
                    + " AND DesignID = 0 "
                    + " AND ProgramNameID = rProgName.ID");

            int item = 1;
            ps.setString(item++, ResinProgramName);
            rs = ps.executeQuery();
            if (rs.first()) {
                thisResinProgram.setID(rs.getInt("rProg.ID"));
                thisResinProgram.setProgramNameID(rs.getInt("ProgramNameID"));
                //thisResinProgram.setProgramDefault(rs.getInt("ProgramDefault"));
                thisResinProgram.setCustomerID(rs.getInt("customerID"));
                thisResinProgram.setColorID(rs.getInt("colorID"));
                thisResinProgram.setDesignID(rs.getInt("designID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisResinProgram;

    }
    
    public ResinProgram GetResinProgramDetailsFromResinProgramID(int ResinProgramID) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResinProgram thisResinProgram = new ResinProgram();
        //ResinProgram thisResinProgram = new ResinProgram();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    " SELECT * "
                            + " FROM resin_program "
                            + " WHERE ID = ?");

            int item = 1;
            ps.setInt(item++, ResinProgramID);
            rs = ps.executeQuery();
            
            if (rs.first()) {
                thisResinProgram.setID(rs.getInt("ID"));
                thisResinProgram.setProgramNameID(rs.getInt("ProgramNameID"));
                thisResinProgram.setCustomerID(rs.getInt("CustomerID"));
                thisResinProgram.setColorID(rs.getInt("ColorID"));
                thisResinProgram.setDesignID(rs.getInt("DesignID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisResinProgram;

    }
    
    public ResinProgram GetResinProgramDetailsFromResinProgramNameAndCustomerID(ResinProgram thisResinProgram) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //ResinProgram thisResinProgram = new ResinProgram();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    " SELECT ID, ProgramNameID, customerID, colorID, designID "
                            + " FROM resin_program"
                            + " WHERE ProgramNameID = ?"
                            + " AND CustomerID = ? "
                            + " AND ColorID = ?"
                            + " AND DesignID = ?");

            int item = 1;
            ps.setInt(item++, thisResinProgram.getProgramNameID());
            ps.setInt(item++, thisResinProgram.getCustomerID());
            ps.setInt(item++, thisResinProgram.getColorID());
            ps.setInt(item++, thisResinProgram.getDesignID());
            rs = ps.executeQuery();
            
            if (rs.first()) {
                thisResinProgram.setID(rs.getInt("rProg.ID"));
                thisResinProgram.setProgramNameID(rs.getInt("ProgramNameID"));
                thisResinProgram.setCustomerID(rs.getInt("customerID"));
                thisResinProgram.setColorID(rs.getInt("colorID"));
                thisResinProgram.setDesignID(rs.getInt("designID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisResinProgram;

    }

    public ResinProgram GetResinProgramDetailsFromResinProgramNameAndCustomerID(String ResinProgramName, ResinProgram thisResinProgram) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //ResinProgram thisResinProgram = new ResinProgram();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(
                    " SELECT rProg.ID, ProgramNameID, customerID, colorID, designID "
                            + " FROM resin_program rProg, resin_program_name rProgName"
                            + " WHERE rProgName.Name = ? "
                            + " AND ProgramNameID = rProgName.ID"
                            + " AND CustomerID = ?"
                            + " AND ColorID = ?"
                            + " AND DesignID = ?");

            int item = 1;
            ps.setString(item++, ResinProgramName);
            ps.setInt(item++, thisResinProgram.getCustomerID());
            ps.setInt(item++, thisResinProgram.getColorID());
            ps.setInt(item++, thisResinProgram.getDesignID());
            rs = ps.executeQuery();
            
            if (rs.first()) {
                thisResinProgram.setID(rs.getInt("rProg.ID"));
                thisResinProgram.setProgramNameID(rs.getInt("ProgramNameID"));
                thisResinProgram.setCustomerID(rs.getInt("customerID"));
                thisResinProgram.setColorID(rs.getInt("colorID"));
                thisResinProgram.setDesignID(rs.getInt("designID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisResinProgram;

    }

    public ArrayList<String> GetAllResinProgram() {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> ResinList = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM resin_program_name ");
            rs = ps.executeQuery();

            while (rs.next()) {
                ResinList.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return ResinList;
    }

    /**
     * *********************************************************************************************
     */
    /**
     * ***************************** FOR Resin Chemical ***************************************************
     */
    public int GetResinChemicalIdByChemicalId(int resinChemical, int resinProgramId) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //DyeingProcess dyeingProcess = new DyeingProcess();
        int resinChemicalId = -1;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT ID FROM resin_chemical where ResinProgramID = ? AND ChemicalID = ?");
            ps.setInt(1, resinProgramId);
            ps.setInt(2, resinChemical);
            rs = ps.executeQuery();

            while (rs.next()) {
                resinChemicalId = rs.getInt("ID");
                break;
            }
        } catch (SQLException ex) {
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
            String query = "INSERT INTO resin_chemical (ChemicalID, ResinProgramID, ValueGPL, State, Type, resin_chemical.Order) VALUES (?, ?, ?, ?, ?, ?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisResinChemical.getChemicalID());
            preparedStmt.setInt(2, thisResinChemical.getResinProgramID());
            preparedStmt.setFloat(3, thisResinChemical.getGPLValue());
            preparedStmt.setString(4, thisResinChemical.getState());
            preparedStmt.setString(5, thisResinChemical.getType());
            preparedStmt.setInt(6, thisResinChemical.getOrder());

            preparedStmt.executeUpdate();

            added = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateResinChemicalByResinChemicalID(ResinChemical thisResinChemical) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE resin_chemical SET ChemicalID = ?, ResinProgramID = ?, ValueGPL = ? , State = ?, Type = ?,resin_chemical.Order = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisResinChemical.getChemicalID());
            preparedStmt.setInt(2, thisResinChemical.getResinProgramID());
            preparedStmt.setFloat(3, thisResinChemical.getGPLValue());
            preparedStmt.setString(4, thisResinChemical.getState());
            preparedStmt.setString(5, thisResinChemical.getType());
            preparedStmt.setInt(6, thisResinChemical.getOrder());
            preparedStmt.setInt(7, thisResinChemical.getID());
            
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM resin_chemical WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ResinChemicalId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM resin_chemical WHERE ResinProgramID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ResinProgramId);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public ArrayList<ResinChemical> GetResinChemicalsByResinProgramId(int resinProgramId) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //DyeingProcess dyeingProcess = new DyeingProcess();
        ArrayList<ResinChemical> resinChemicals = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM resin_chemical where ResinProgramID = ? ORDER BY resin_chemical.order");
            ps.setInt(1, resinProgramId);
            rs = ps.executeQuery();

            while (rs.next()) {
                ResinChemical resinChemical = new ResinChemical();
                resinChemical.setID(rs.getInt("ID"));
                resinChemical.setChemicalID(rs.getInt("ChemicalID"));
                resinChemical.setGPLValue(rs.getFloat("ValueGPL"));
                resinChemical.setResinProgramID(rs.getInt("ResinProgramID"));
                resinChemical.setState(rs.getString("State"));
                resinChemical.setType(rs.getString("Type"));
                resinChemical.setOrder(rs.getInt("resin_chemical.Order"));
                resinChemicals.add(resinChemical);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return resinChemicals;
    }

    /**
     * ***************************** FOR Resin Chemical: END ***************************************************
     */

    /**
     * ***************************** FOR DyeingProcess: BEGIN ***************************************************
     */
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
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            added = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return DyeingProcessID;
    }

    public int UpdateDyeingProcessByDyeingProcessID(DyeingProcess dyeingProcess) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        int RowManipulated = -1;
        try {
            conn = db.getConnection();
            String query = "UPDATE dyeing_process SET Name = ? , dyeing_process.Order = ? WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, dyeingProcess.getDyeingProcessName());
            preparedStmt.setString(2, dyeingProcess.getdyeingProcessOrder());
            preparedStmt.setInt(3, dyeingProcess.getId());
            //preparedStmt.setString(3, dyeingProcess.getdyeingProcessOrder());
            RowManipulated = preparedStmt.executeUpdate();

        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM dyeing_process WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, dyeingProcessID);
            preparedStmt.execute();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public boolean CheckIfDyeingProcessExistsWithThisID(int DyeingProcessID) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean itExists = false;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT Name "
                    + " FROM dyeing_process WHERE "
                    + " ID = ? ) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, DyeingProcessID);
            rs = ps.executeQuery();

            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        if (checkTest == 1) {
            itExists = true;
        }

        return itExists;
    }

    /**
     *
     * @param ThisDyeingProcess
     * @return Returns true if Updated name exists on Other Dyeing Process
     */
    public boolean CheckIfDyeingProcessNameExistsOnOtherDyeingProcessID(DyeingProcess ThisDyeingProcess) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean itExists = false;
        int checkTest = 0;
        try {
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

            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        if (checkTest == 1) {
            itExists = true;
        }

        return itExists;
    }

    public boolean CheckIfDyeingProcessExists(DyeingProcess ThisDyeingProcess) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean itExists = false;
        int checkTest = 0;
        try {
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

            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        if (checkTest == 1) {
            itExists = true;
        }

        return itExists;
    }

    public DyeingProcess GetDyeingProcessDetailsByDyeingProcessId(int dyeingProcessId) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DyeingProcess dyeingProcess = new DyeingProcess();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM dyeing_process where ID = ? ");
            ps.setInt(1, dyeingProcessId);
            rs = ps.executeQuery();
            if (rs.first()) {
                dyeingProcess.setId(rs.getInt("ID"));
                dyeingProcess.setDyeingProcessName(rs.getString("Name"));
                dyeingProcess.setDyeingProcessOrder(rs.getString("Order"));
                dyeingProcess.setDyeingProgramId(rs.getInt("DyeingProgramID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return dyeingProcess;
    }

    public DyeingProcess GetDyeingProcessDetailsByDyeingProgramIdAndProcessOrder(int dyeingProgramId, int ProcessOrder) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DyeingProcess dyeingProcess = new DyeingProcess();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM dyeing_process where DyeingProgramID = ? AND dyeing_process.Order = ?");
            ps.setInt(1, dyeingProgramId);
            ps.setString(2, Integer.toString(ProcessOrder));
            rs = ps.executeQuery();

            dyeingProcess.setId(rs.getInt("ID"));
            dyeingProcess.setDyeingProcessName(rs.getString("Name"));
            dyeingProcess.setDyeingProcessOrder(rs.getString("Order"));
            dyeingProcess.setDyeingProgramId(rs.getInt("DyeingProgramID"));
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return dyeingProcess;
    }

    /**
     * Get Dyeing Process Details from Current Dyeing Program ID. All subprocess
     * i.e 1.x is not taken.
     *
     * @param DyeingProgramID
     * @return Array List of all the dyeing process
     */
    public ArrayList<DyeingProcess> GetDyeingProcessDetailsByDyeingProgramId(int DyeingProgramID) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //DyeingProcess dyeingProcess = new DyeingProcess();
        ArrayList<DyeingProcess> AllDyeingProcess = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM dyeing_process WHERE DyeingProgramID = ? AND dyeing_process.ORDER NOT LIKE '%.%' ORDER BY dyeing_process.Order ASC;");
            ps.setInt(1, DyeingProgramID);
            rs = ps.executeQuery();

            while (rs.next()) {
                DyeingProcess dyeingProcess = new DyeingProcess();
                dyeingProcess.setId(rs.getInt("ID"));
                dyeingProcess.setDyeingProcessName(rs.getString("Name"));
                dyeingProcess.setDyeingProcessOrder(rs.getString("Order"));
                dyeingProcess.setDyeingProgramId(rs.getInt("DyeingProgramID"));
                AllDyeingProcess.add(dyeingProcess);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return AllDyeingProcess;
    }

    public ArrayList<DyeingProcess> GetDyeingSubProcessDetailsByDyeingProgramIdAndProcessOrder(int DyeingProgramID, int ProcessNumber) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //DyeingProcess dyeingProcess = new DyeingProcess();
        ArrayList<DyeingProcess> AllDyeingProcess = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT * FROM dyeing_process where DyeingProgramID = ? AND dyeing_process.Order LIKE ?");
            ps.setInt(1, DyeingProgramID);
            String ParseString = Integer.toString(ProcessNumber) + ".%";
            ps.setString(2, ParseString);

            rs = ps.executeQuery();

            while (rs.next()) {
                DyeingProcess dyeingProcess = new DyeingProcess();
                dyeingProcess.setId(rs.getInt("ID"));
                dyeingProcess.setDyeingProcessName(rs.getString("Name"));
                dyeingProcess.setDyeingProcessOrder(rs.getString("Order"));
                dyeingProcess.setDyeingProgramId(rs.getInt("DyeingProgramID"));
                AllDyeingProcess.add(dyeingProcess);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return AllDyeingProcess;
    }

    public int CountNumberOfDyeingProcess(int DyeingProgramID) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int TotalNumberOfProcess = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(ID) AS 'TOTAL' FROM dyeing_process where DyeingProgramID = ? AND dyeing_process.ORDER NOT LIKE '%.%';");
            int item = 1;
            ps.setInt(item++, DyeingProgramID);
            rs = ps.executeQuery();

            if (rs.first()) {
                TotalNumberOfProcess = rs.getInt("TOTAL");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return TotalNumberOfProcess;
    }

    public int CountNumberOfDyeingSubProcess(int DyeingProgramID, int ProcessNumber) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int TotalNumberOfProcess = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(ID) AS 'TOTAL' FROM dyeing_process WHERE DyeingProgramID = ? AND dyeing_process.ORDER LIKE ? ;");
            int item = 1;
            ps.setInt(item++, DyeingProgramID);
            String ParseProcessNumber = Integer.toString(ProcessNumber) + ".%";
            ps.setString(item++, ParseProcessNumber);
            rs = ps.executeQuery();

            if (rs.first()) {
                TotalNumberOfProcess = rs.getInt("TOTAL");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return TotalNumberOfProcess;
    }

    public ArrayList<String> GetAllDyeingProcessByDyeingProgramId(int DyeingProgramID) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> DyeingProcessList = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT Name FROM dyeing_process where DyeingProgramID = ? ");
            int item = 1;
            ps.setInt(item++, DyeingProgramID);
            rs = ps.executeQuery();

            while (rs.next()) {
                DyeingProcessList.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return DyeingProcessList;
    }

    public int GetDyeingProcessIDFromDyeingProcessDetails(DyeingProcess ThisDyeingProcess) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int ProcessID = -1;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT ID "
                    + " FROM dyeing_process "
                    + " WHERE Name = ? AND dyeing_process.Order LIKE ? AND DyeingProgramID = ?");

            ps.setString(1, ThisDyeingProcess.getDyeingProcessName().toUpperCase());
            ps.setString(2, ThisDyeingProcess.getdyeingProcessOrder());
            ps.setInt(3, ThisDyeingProcess.getDyeingProgramId());

            rs = ps.executeQuery();
            if (rs.first()) {
                ProcessID = rs.getInt("ID");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return ProcessID;
    }

    /**
     * ***************************** FOR DyeingProcess: END ***************************************************
     */

    /**
     * *********************************************************************************************
     */
    /**
     * ***************************** FOR Dyeing Chemical ***************************************************
     */
    public boolean AddDyeingChemical(DyeingChemical thisDyeingChemical) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO dyeing_chemical (ChemicalID, DyeingProcessID, Type, Value, dyeing_chemical.order, State) VALUES (?, ?, ?, ?, ?, ?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisDyeingChemical.getChemicalId());
            preparedStmt.setInt(2, thisDyeingChemical.getDyeingProcessID());
            preparedStmt.setString(3, thisDyeingChemical.getType());
            preparedStmt.setFloat(4, thisDyeingChemical.getValue());
            preparedStmt.setInt(5, thisDyeingChemical.getOrder());
            preparedStmt.setString(6, thisDyeingChemical.getState());
            preparedStmt.executeUpdate();

            added = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean UpdateDyeingChemicalByDyeingChemicalID(DyeingChemical thisDyeingChemical) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE dyeing_chemical SET ChemicalID = ?, Type = ?, Value = ? , dyeing_chemical.Order = ? WHERE ID = ? LIMIT 1";

            preparedStmt = conn.prepareStatement(query);
            int item = 1;
            preparedStmt.setInt(item++, thisDyeingChemical.getChemicalId());
            preparedStmt.setString(item++, thisDyeingChemical.getType());
            preparedStmt.setFloat(item++, thisDyeingChemical.getValue());
            preparedStmt.setInt(item++, thisDyeingChemical.getOrder());
            preparedStmt.setInt(item++, thisDyeingChemical.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM dyeing_chemical WHERE ID = ? LIMIT 1";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ThisDyeingChemical.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
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
        try {
            conn = db.getConnection();
            String query = "DELETE FROM dyeing_chemical WHERE DyeingProcessID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, ThisDyeingChemical.getDyeingProcessID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    

    public ArrayList<DyeingChemical> GetAllDyeingChemicalsFromDyeingProcessID(int DyeingProcessID) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DyeingChemical> DyeingChemicalsForProcess = new ArrayList<DyeingChemical>();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                    + " FROM dyeing_chemical "
                    + " WHERE DyeingProcessID = ? "
                    + " ORDER BY dyeing_chemical.Order ASC");

            ps.setInt(1, DyeingProcessID);

            rs = ps.executeQuery();
            while (rs.next()) {
                DyeingChemical thisDyeingChemical = new DyeingChemical();
                thisDyeingChemical.setID(rs.getInt("ID"));
                thisDyeingChemical.setChemicalId(rs.getInt("ChemicalID"));
                thisDyeingChemical.setType(rs.getString("Type"));
                thisDyeingChemical.setValue(rs.getFloat("Value"));
                thisDyeingChemical.setState(rs.getString("State"));
                thisDyeingChemical.setOrder(rs.getInt("Order"));
                DyeingChemicalsForProcess.add(thisDyeingChemical);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return DyeingChemicalsForProcess;
    }

    public int CheckIfDyeingChemicalExistsOnThisProcess(DyeingChemical ThisDyeingChemical) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_chemical WHERE "
                    + " ChemicalID = ?"
                    + " AND DyeingProcessID = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, ThisDyeingChemical.getChemicalId());
            ps.setInt(item++, ThisDyeingChemical.getDyeingProcessID());
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public int CountNumberOfDyeingChemicalForThisProcess(int DyeingProcessID) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int TotalNumberOfProcess = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT COUNT(ID) AS 'TOTAL' FROM dyeing_chemical WHERE DyeingProcessID = ?;");
            int item = 1;
            ps.setInt(item++, DyeingProcessID);
            rs = ps.executeQuery();

            if (rs.first()) {
                TotalNumberOfProcess = rs.getInt("TOTAL");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return TotalNumberOfProcess;
    }

    public int CheckIfSameDyeingChemicalExistsOnThisProcess(DyeingChemical ThisDyeingChemical) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM dyeing_chemical WHERE "
                    + " ChemicalID = ? "
                    + " AND DyeingProcessID = ? "
                    + " AND ID = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setInt(item++, ThisDyeingChemical.getChemicalId());
            ps.setInt(item++, ThisDyeingChemical.getDyeingProcessID());
            ps.setInt(item++, ThisDyeingChemical.getID());
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }

    public ArrayList<JobOrder> GetAllJobOrderHistoryByCustomerId(int customerId) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<JobOrder> CustomerJobOrders = new ArrayList<JobOrder>();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                    + " FROM job_order "
                    + " WHERE CustomerID = ? "
                    + " ORDER BY job_order.Date ASC");

            ps.setInt(1, customerId);

            rs = ps.executeQuery();
            while (rs.next()) {
                JobOrder thisJobOrder = new JobOrder();
                thisJobOrder.setID(rs.getInt("ID"));
                thisJobOrder.setDrNumber(rs.getString("DrNumber"));
                thisJobOrder.setDesignID(rs.getInt("DesignID"));
                thisJobOrder.setColorID(rs.getInt("ColorID"));
                thisJobOrder.setCustomerID(rs.getInt("CustomerID"));
                thisJobOrder.setBatchNo(rs.getString("BatchNo"));

                thisJobOrder.setDyeingMachineID(rs.getInt("DyeingMachineID"));
                thisJobOrder.setDyeingWeight(rs.getFloat("DyeingWeight"));
                thisJobOrder.setDyeingVolumeH20(rs.getFloat("DyeingVolH2O"));

                thisJobOrder.setRollLoad(rs.getString("RollLoad"));
                thisJobOrder.setRoll(rs.getInt("Roll"));
                thisJobOrder.setDyeingProgramID(rs.getInt("DyeingProgramID"));
                CustomerJobOrders.add(thisJobOrder);
                
                //thisJobOrder.setResinMachineID(rs.getInt("ResinMachineID"));
                //thisJobOrder.setResinWeight(rs.getFloat("ResinWeight"));
                //thisJobOrder.setResinVolumeH20(rs.getInt("ResinVolH2O"));
                //thisJobOrder.setResinProgramID(rs.getInt("ResinProgramID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return CustomerJobOrders;
    }

    public ArrayList<JobOrder> GetAllJobOrderHistoryByJobOrderNumber(int jobOrderNumber) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<JobOrder> CustomerJobOrders = new ArrayList<JobOrder>();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                    + " FROM job_order "
                    + " WHERE DrNumber = ? "
                    + " ORDER BY job_order.Date ASC");

            ps.setInt(1, jobOrderNumber);

            rs = ps.executeQuery();
            while (rs.next()) {
                JobOrder thisJobOrder = new JobOrder();
                thisJobOrder.setID(rs.getInt("ID"));
                thisJobOrder.setDrNumber(rs.getString("DrNumber"));
                thisJobOrder.setDesignID(rs.getInt("DesignID"));
                thisJobOrder.setColorID(rs.getInt("ColorID"));
                thisJobOrder.setCustomerID(rs.getInt("CustomerID"));
                thisJobOrder.setBatchNo(rs.getString("BatchNo"));

                thisJobOrder.setDyeingMachineID(rs.getInt("DyeingMachineID"));
                thisJobOrder.setDyeingWeight(rs.getFloat("DyeingWeight"));
                thisJobOrder.setDyeingVolumeH20(rs.getFloat("DyeingVolH2O"));

                thisJobOrder.setRollLoad(rs.getString("RollLoad"));
                thisJobOrder.setRoll(rs.getInt("Roll"));
                thisJobOrder.setDyeingProgramID(rs.getInt("DyeingProgramID"));
                CustomerJobOrders.add(thisJobOrder);

                //thisJobOrder.setResinMachineID(rs.getInt("ResinMachineID"));
                //thisJobOrder.setResinWeight(rs.getFloat("ResinWeight"));
                //thisJobOrder.setResinVolumeH20(rs.getInt("ResinVolH2O"));
                //thisJobOrder.setResinProgramID(rs.getInt("ResinProgramID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return CustomerJobOrders;
    }

    public ResinProgram GetResinProgramDetailsByResinProgramID(int resinProgramId) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;
        ResinProgram thisResinProgram = new ResinProgram();
        try {
            conn = db.getConnection();
            String query = "SELECT * FROM resin_program WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, resinProgramId);
            resultSet = preparedStmt.executeQuery();

            if (resultSet.first()) {
                thisResinProgram.setProgramNameID(resultSet.getInt("ProgramNameID"));
                thisResinProgram.setCustomerID(resultSet.getInt("CustomerID"));
                thisResinProgram.setColorID(resultSet.getInt("ColorID"));
                thisResinProgram.setDesignID(resultSet.getInt("DesignID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt, resultSet);
        return thisResinProgram;
    }

    public int GetResinProgramNameIdFromResinProgramName(String resinProgramName) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        ResultSet resultSet = null;
        int resinProgramNameId = -1;
        try {
            conn = db.getConnection();
            String query = "SELECT ID FROM resin_program_name WHERE Name = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, resinProgramName);
            resultSet = preparedStmt.executeQuery();

            if (resultSet.first()) {
                resinProgramNameId = resultSet.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt, resultSet);
        return resinProgramNameId;
    }

    public boolean AddLiquidRatio(String newLiquidRatio) {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean added = false;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO liquid_ratio (liquid_ratio_value) VALUES (?)";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, newLiquidRatio);
            preparedStmt.executeUpdate();

            added = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return added;
    }

    public boolean DeleteLiquidRatioByLiquidRatioID(int LiquidRatioID) {

        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "DELETE FROM liquid_ratio WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, LiquidRatioID);
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }

    public int CheckIfLiquidRatioExists(String LiquidRatio) {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int checkTest = 0;
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT EXISTS "
                    + " (SELECT ID "
                    + " FROM liquid_ratio WHERE "
                    + " liquid_ratio_value = ?) "
                    + " AS 'CheckTest'");

            int item = 1;
            ps.setString(item++, LiquidRatio);
            rs = ps.executeQuery();
            if (rs.first()) {
                checkTest = rs.getInt("CheckTest");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return checkTest;
    }
    
    public ArrayList<String> GetAllLiquidRatio() {
        DBConnection dbc = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> LiquidRatio = new ArrayList<>();
        try {
            conn = dbc.getConnection();
            ps = conn.prepareStatement("SELECT liquid_ratio_value FROM liquid_ratio");
            rs = ps.executeQuery();

            while (rs.next()) {
                LiquidRatio.add(rs.getString("liquid_ratio_value"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, ps, rs);
        return LiquidRatio;

    }
    
    /**    
    * FOR RESIN JOB ORDER
    * 
    **/
    public int AddResinJob(ResinJob thisResinJob)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        int addedID = -1;
        try {
            conn = db.getConnection();
            String query = "INSERT INTO resin_job (ResinMachineID, ResinWeight, ResinVolH2O, ResinProgramID, JobOrderID) VALUES (?, ?, ?, ?, ?)";

            preparedStmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            int x = 1;
            preparedStmt.setInt(x++, thisResinJob.getResinMachineID());
            preparedStmt.setFloat(x++, thisResinJob.getResinWeight());
            preparedStmt.setFloat(x++, thisResinJob.getResinVolH2O());
            preparedStmt.setInt(x++, thisResinJob.getResinProgramID());
            preparedStmt.setInt(x++, thisResinJob.getJobOrderID());
            preparedStmt.execute();

            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                addedID = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return addedID;
        
    }
    
    public boolean UpdateResinJob(ResinJob thisResinJob)
    {
       DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "UPDATE resin_job SET ResinMachineID = ?, ResinWeight = ?, ResinVolH2O = ?, ResinProgramID = ?, JobOrderID = ? WHERE ID = ?";

            int x = 1;
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(x++, thisResinJob.getResinMachineID());
            preparedStmt.setFloat(x++, thisResinJob.getResinWeight());
            preparedStmt.setFloat(x++, thisResinJob.getResinVolH2O());
            preparedStmt.setInt(x++, thisResinJob.getResinProgramID());
            preparedStmt.setInt(x++, thisResinJob.getJobOrderID());
            preparedStmt.setInt(x++, thisResinJob.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public boolean DeleteResinJob(ResinJob thisResinJob)
    {
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement preparedStmt = null;
        boolean isSuccessful = false;
        try {
            conn = db.getConnection();
            String query = "DELETE FROM ResinJob WHERE ID = ?";

            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, thisResinJob.getID());
            preparedStmt.executeUpdate();
            isSuccessful = true;
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConn(conn, preparedStmt);
        return isSuccessful;
    }
    
    public ResinJob GetResinJobFromJobID(ResinJob thisResinJob)
    {
        
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                    + " FROM resin_job "
                    + " WHERE ID = ? ");

            ps.setInt(1, thisResinJob.getID());

            rs = ps.executeQuery();
            if (rs.first()) {
                thisResinJob.setID( rs.getInt("ID") );
                thisResinJob.setResinMachineID(rs.getInt("ResinMachineID") );
                thisResinJob.setResinWeight(rs.getFloat("ResinWeight") );
                thisResinJob.setResinVolH2O(rs.getFloat("ResinVolH2O") );
                thisResinJob.setResinProgramID(rs.getInt("ResinProgramID") );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisResinJob;
    
    }
    
    public ArrayList<ResinJob> GetResinJobFromJobID(int JobOrderID)
    {
        
        DBConnection db = new DBConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ResinJob> thisResinJobList = new ArrayList<ResinJob>();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement("SELECT * "
                    + " FROM resin_job "
                    + " WHERE JobOrderID = ? ");

            ps.setInt(1, JobOrderID);

            rs = ps.executeQuery();
            while (rs.next()) {
                ResinJob thisResinJob = new ResinJob();
                thisResinJob.setID( rs.getInt("ID") );
                thisResinJob.setResinMachineID(rs.getInt("ResinMachineID") );
                thisResinJob.setResinWeight(rs.getFloat("ResinWeight") );
                thisResinJob.setResinVolH2O(rs.getFloat("ResinVolH2O") );
                thisResinJob.setResinProgramID(rs.getInt("ResinProgramID") );
                thisResinJobList.add(thisResinJob);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ColorTextControlSlipRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeConn(conn, ps, rs);
        return thisResinJobList;
    
    }
    
    
    
}
