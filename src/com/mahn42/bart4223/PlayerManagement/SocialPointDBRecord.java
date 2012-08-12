/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

import com.mahn42.framework.DBRecord;
import java.util.ArrayList;

/**
 *
 * @author Nils
 */
public class SocialPointDBRecord extends DBRecord{

    public String Player;
    public String SocialPointType;
    public int TotalPoints;

    @Override
    protected void toCSVInternal(ArrayList aCols) {
        super.toCSVInternal(aCols);
      aCols.add(Player);
      aCols.add(SocialPointType);
      aCols.add(TotalPoints);
    }

    @Override
    protected void fromCSVInternal(DBRecord.DBRecordCSVArray aCols) {
        super.fromCSVInternal(aCols);
     Player = aCols.pop();
     SocialPointType = aCols.pop();
     TotalPoints = Integer.parseInt(aCols.pop());   
    }
    
    
}
