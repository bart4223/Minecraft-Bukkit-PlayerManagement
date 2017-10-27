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
public class SocialPointDBSet extends DBSet{
    
    public SocialPointDBSet(File aFile) {
        super(SocialPointDBRecord.class, aFile);
    }

    public SocialPointDBRecord getSocialPointByType(String aPlayer, String aPointType, Boolean aCreate) {
        SocialPointDBRecord lSPDB = null;
        for (Iterator<SocialPointDBRecord> it = this.iterator(); it.hasNext();) {
            lSPDB = it.next();
            if (aPlayer.equals(lSPDB.Player) && aPointType.equals(lSPDB.SocialPointType)) {
                return lSPDB;
            }
        }
        if (aCreate) {
            lSPDB = new SocialPointDBRecord();
            lSPDB.Player = aPlayer;
            lSPDB.SocialPointType = aPointType;
            lSPDB.TotalPoints = 0;
            addRecord(lSPDB);
        }
        return lSPDB;
    }    
    
    public List<SocialPointDBRecord> getSocialPoints(String aPlayer) {
        ArrayList<SocialPointDBRecord> lResult;
        lResult = new ArrayList<SocialPointDBRecord>();
        for (Iterator<SocialPointDBRecord> it = this.iterator(); it.hasNext();) {
            SocialPointDBRecord lSPDB = it.next();
            if (aPlayer.equals(lSPDB.Player)) {
                lResult.add(lSPDB);
            }
        }
        return lResult;
    }    

}
