/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataEntities;

import Database.ColorTextControlSlipRepository;
import java.util.ArrayList;
/**
 *
 * @author imbuenyson
 */
public class DyeingProcess {
    private int dyeingProcessId;
    private int dyeingProgramId;
    private String dyeingProcessName;
    private String dyeingProcessOrder;
    
    public int getDyeingProcessId() {
        return dyeingProcessId;
    }

    public void setDyeingProcessId(int dyeingID) {
        this.dyeingProcessId = dyeingID;
    }
    
    public int getDyeingProgramId() {
        return dyeingProgramId;
    }

    public void setDyeingProgramId(int dyeingProgramId) {
        this.dyeingProgramId = dyeingProgramId;
    }
    
    public String getDyeingProcessName() {
        return dyeingProcessName;
    }

    public void setDyeingProcessName(String dyeingProcessName) {
        this.dyeingProcessName = dyeingProcessName.toUpperCase();
    }
    
    public String getdyeingProcessOrder() {
        return dyeingProcessOrder;
    }

    public void setDyeingProcessOrder(String dyeingProcessOrder) {
        this.dyeingProcessOrder = dyeingProcessOrder.toUpperCase();
    }
}
