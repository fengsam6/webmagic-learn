package com.feng.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final String DATE_FORMAT="yyyy-MM-dd hh:mm:ss";
    public static String getCurDateStr(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
       return dateFormat.format(new Date());
    }
}
