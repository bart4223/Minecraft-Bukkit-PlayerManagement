package com.mahn42.bart4223.PlayerManagement.Statistic;

import com.mahn42.framework.DBSet;

import java.io.File;
import java.util.Iterator;

public class PlayerStatisticDBSet extends DBSet {

    public PlayerStatisticDBSet(File aFile) {
        super(PlayerStatisticDBRecord.class, aFile);
    }

    public PlayerStatisticDBRecord getRecord(String aPlayer) {
        PlayerStatisticDBRecord res = null;
        Iterator<PlayerStatisticDBRecord> itr = iterator();
        while (itr.hasNext()) {
            PlayerStatisticDBRecord rec = itr.next();
            if (rec.IsPlayer(aPlayer)) {
                res = rec;
            }
        }
        return res;
    }

    public PlayerStatisticDBRecord newRecord(String aPlayer) {
        PlayerStatisticDBRecord res = new PlayerStatisticDBRecord(aPlayer);
        addRecord(res);
        return res;
    }

}
