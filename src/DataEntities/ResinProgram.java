/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

/**
 *
 * @author Eldridge
 */
public class ResinProgram {
    private int ID;
    private int ProgramNameID;
    private String ProgramDefault;

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
     * @return the Name
     */
    public int getProgramNameID() {
        return ProgramNameID;
    }

    /**
     * @param Name the Name to set
     */
    public void setProgramNameID(int ProgramNameID) {
        this.ProgramNameID = ProgramNameID;
    }
    
    /**
     * @return the Name
     */
    public String getProgramDefault() {
        return ProgramDefault;
    }

    /**
     * @param Name the Name to set
     */
    public void setProgramDefault(String ProgramDefault) {
        this.ProgramDefault = ProgramDefault;
    }
}
