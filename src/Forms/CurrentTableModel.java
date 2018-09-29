/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;


import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import static java.util.Collections.list;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eldridge
 */
public class CurrentTableModel extends DefaultTableModel implements Reorderable {

    @Override
    public void reorder(int fromIndex, int toIndex) {
        Object o = getDataVector().remove(fromIndex);
        //System.out.println("From Index: "+fromIndex + " To Index :" +toIndex);
        if( fromIndex < toIndex ) { toIndex --; }
        
        getDataVector().add(toIndex, o);
        fireTableDataChanged();
        
        //Object row = list.remove(fromIndex);
        //if (fromIndex < toIndex) {
        //    toIndex--;
        //} // We now have one row less!
        //list.add(toIndex, row);
    }

}
