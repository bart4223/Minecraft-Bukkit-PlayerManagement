package com.mahn42.bart4223.PlayerManagement.News;

import com.mahn42.framework.DBRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class PlayerNewsDBRecord extends DBRecord {

    protected static String C_UTCFORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    protected static String C_LOCALFORMAT = "dd.MM.yyyy";

    protected static String getUTCDateAsString(Date aDate) {
        DateFormat formatter = new SimpleDateFormat(C_UTCFORMAT);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
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

    protected static String getLocalDateAsString(Date aDate) {
        DateFormat formatter = new SimpleDateFormat(C_LOCALFORMAT);
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(aDate);
    }

    public enum eImportance {Normal, Important, VeryImportant}

    public String Publisher;
    public Date Released;
    public String News;
    public Integer Importance;

    public PlayerNewsDBRecord() {
        this("", "", eImportance.Normal);
    }

    public PlayerNewsDBRecord(String aPublisher, String aNews, eImportance aImportance) {
        Publisher = aPublisher;
        Released = new Date();
        News = aNews;
        switch (aImportance) {
            case Normal:
                Importance = 0;
                break;
            case Important:
                Importance = 1;
                break;
            case VeryImportant:
                Importance = 2;
                break;
        }
    }

    @Override
    protected void toCSVInternal(ArrayList aCols) {
        super.toCSVInternal(aCols);
        aCols.add(Publisher);
        aCols.add(getUTCDateAsString(Released));
        aCols.add(News);
        aCols.add(Importance);
    }

    @Override
    protected void fromCSVInternal(DBRecord.DBRecordCSVArray aCols) {
        super.fromCSVInternal(aCols);
        Publisher = aCols.pop();
        Released = getDateFromString(aCols.pop());
        News = aCols.pop();
        Importance = aCols.popInt();
    }

    public String getNews() {
        return String.format("%s %s", getLocalDateAsString(Released), News);
    }

    public eImportance getImportance() {
        switch (Importance) {
            case 0:
                return eImportance.Normal;
            case 1:
                return eImportance.Important;
            case 2:
                return eImportance.VeryImportant;
        }
        return eImportance.Normal;
    }

}
