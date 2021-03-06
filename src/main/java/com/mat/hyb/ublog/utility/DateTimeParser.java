package com.mat.hyb.ublog.utility;

import org.androidannotations.annotations.EBean;

import java.util.Calendar;

/**
 * Created by matous on 26.4.14 for uBlog.
 */
@EBean
public class DateTimeParser {

    public String getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        if (calendar.get(Calendar.MINUTE) < 10) {
            minute = "0" + minute;
        }
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                + minute;
    }

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return String.valueOf(calendar.get(Calendar.YEAR)) + "-"
                + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-"
                + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }
}
