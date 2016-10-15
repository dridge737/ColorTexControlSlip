/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

import java.sql.Date;

/**
 *
 * @author Eldridge
 */
public class JobOrder {
    private int ID;
    private String DrNumber;
    private int MachineID;
    private int DesignID;
    private int ColorID;
    private int CustomerID;
    private String jobDate;
    private String BatchNo;

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the DrNumber
     */
    public String getDrNumber() {
        return DrNumber;
    }

    /**
     * @param DrNumber the DrNumber to set
     */
    public void setDrNumber(String DrNumber) {
        this.DrNumber = DrNumber;
    }

    /**
     * @return the MachineID
     */
    public int getMachineID() {
        return MachineID;
    }

    /**
     * @param MachineID the MachineID to set
     */
    public void setMachineID(int MachineID) {
        this.MachineID = MachineID;
    }

    /**
     * @return the DesignID
     */
    public int getDesignID() {
        return DesignID;
    }

    /**
     * @param DesignID the DesignID to set
     */
    public void setDesignID(int DesignID) {
        this.DesignID = DesignID;
    }

    /**
     * @return the ColorID
     */
    public int getColorID() {
        return ColorID;
    }

    /**
     * @param ColorID the ColorID to set
     */
    public void setColorID(int ColorID) {
        this.ColorID = ColorID;
    }

    /**
     * @return the CustomerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    /**
     * @return the jobDate
     */
    public String getJobDate() {
        return jobDate;
    }

    /**
     * @param jobDate the jobDate to set
     */
    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    /**
     * @return the BatchNo
     */
    public String getBatchNo() {
        return BatchNo;
    }

    /**
     * @param BatchNo the BatchNo to set
     */
    public void setBatchNo(String BatchNo) {
        this.BatchNo = BatchNo;
    }

}
