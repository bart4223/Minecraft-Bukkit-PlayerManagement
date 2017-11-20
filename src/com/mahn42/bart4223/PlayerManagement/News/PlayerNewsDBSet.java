package com.mahn42.bart4223.PlayerManagement.News;

import com.mahn42.framework.DBSet;

import java.io.File;

public class PlayerNewsDBSet extends DBSet {

    public PlayerNewsDBSet(File aFile) {
        super(PlayerNewsDBRecord.class, aFile);
    }

    public PlayerNewsDBRecord newRecord(String aPublisher, String aNews, PlayerNewsDBRecord.eImportance aImportance) {
        PlayerNewsDBRecord res = new PlayerNewsDBRecord(aPublisher, aNews, aImportance);
        addRecord(res);
        return res;
    }

}
