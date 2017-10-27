/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement.SocialPoint;

import com.mahn42.framework.DBSet;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Nils
 */
public class SocialPointHistoryDBSet extends DBSet{
 
    public SocialPointHistoryDBSet(File aFile) {
        super(SocialPointHistoryDBRecord.class, aFile);
    }

    public List<SocialPointHistoryDBRecord> getSocialPointsByType(String aPlayer, String aPointType) {
        ArrayList<SocialPointHistoryDBRecord> lResult;
        lResult = new ArrayList<SocialPointHistoryDBRecord>();
        for (Iterator<SocialPointHistoryDBRecord> it = this.iterator(); it.hasNext();) {
            SocialPointHistoryDBRecord lSPHDB = it.next();
            if (aPlayer.equals(lSPHDB.Player) && aPointType.equals(lSPHDB.PointType)) {
                lResult.add(lSPHDB);
            }
        }
        return lResult;
    }    
    
    
}
