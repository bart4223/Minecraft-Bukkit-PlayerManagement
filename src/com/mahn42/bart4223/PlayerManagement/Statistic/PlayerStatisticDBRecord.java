package com.mahn42.bart4223.PlayerManagement.Statistic;

import com.mahn42.framework.DBRecord;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class PlayerStatisticDBRecord extends DBRecord {

    protected static String C_UTCFORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    protected static String C_LOCALFORMAT = "dd.MM.yyyy HH:mm:ss";
    protected static DecimalFormat FDecimaFormat = new DecimalFormat("00");

    protected static String getUTCDateAsString(Date aDate) {
        DateFormat formatter = new SimpleDateFormat(C_UTCFORMAT);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(aDate);
    }

    protected static String getLocalDateAsString(Date aDate) {
        DateFormat formatter = new SimpleDateFormat(C_LOCALFORMAT);
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(aDate);
    }

    protected static Date getDateFromString(String aDate) {
        DateFormat formatter = new SimpleDateFormat(C_UTCFORMAT);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date res = null;
        try {
            res = formatter.parse(aDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    protected static String getDurationAsString(Integer aDuration) {
        double dd = Math.floor(aDuration / 86400);
        double hh = Math.floor((aDuration%86400) / 3600);
        double mm = Math.floor((aDuration%3600) / 60);
        double ss = Math.floor(aDuration%60);
        String res = String.format("%s:%s:%s", FDecimaFormat.format(hh), FDecimaFormat.format(mm), FDecimaFormat.format(ss));
        if (dd > 0.0) {
            res = String.format("%.0f Tag(e) %s", dd, res);
        }
        return res;
    }

    public String Player;
    public Date FirstLogin;
    public Date LastLogin;
    public Date CurrentLogin;
    public Integer TotalDuration;
    public Integer LastDuration;

    public PlayerStatisticDBRecord() {
        this("");
    }

    public PlayerStatisticDBRecord(String aPlayer) {
        Player = aPlayer;
        FirstLogin = new Date();
        LastLogin = new Date(0);
        CurrentLogin = new Date();
        TotalDuration = 0;
        LastDuration = 0;
    }

    @Override
    protected void toCSVInternal(ArrayList aCols) {
        super.toCSVInternal(aCols);
        aCols.add(Player);
        aCols.add(getUTCDateAsString(FirstLogin));
        aCols.add(getUTCDateAsString(LastLogin));
        aCols.add(getUTCDateAsString(CurrentLogin));
        aCols.add(TotalDuration);
        aCols.add(LastDuration);
    }

    @Override
    protected void fromCSVInternal(DBRecord.DBRecordCSVArray aCols) {
        super.fromCSVInternal(aCols);
        Player = aCols.pop();
        FirstLogin = getDateFromString(aCols.pop());
        LastLogin = getDateFromString(aCols.pop());
        CurrentLogin = getDateFromString(aCols.pop());
        TotalDuration = aCols.popInt();
        LastDuration = aCols.popInt();
    }

    public Boolean IsPlayer(String aName) {
        return Player.toUpperCase().equals(aName.toUpperCase());
    }

    public String getFirstLoginAsString() {
        return getLocalDateAsString(FirstLogin);
    }

    public String getLastLoginAsString() {
        return getLocalDateAsString(LastLogin);
    }

    public String getTotalDurationAsString() {
        return getDurationAsString(TotalDuration);
    }

    public String getLastDurationAsString() {
        return getDurationAsString(LastDuration);
    }

}
