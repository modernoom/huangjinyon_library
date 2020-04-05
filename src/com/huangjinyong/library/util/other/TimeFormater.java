package com.huangjinyong.library.util.other;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huangjinyong
 */
public class TimeFormater {



    public static String timeFormat(String pattern, Date date){
        DateFormat formater=new SimpleDateFormat(pattern);
        return formater.format(date);
    }

    public static Date timeFormatToDate(String pattern,String date){
        DateFormat formater=new SimpleDateFormat(pattern);
        Date parseDate=null;
        try {
            parseDate = formater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }
}
