package com.lzfutil.util;

import java.util.Date;
import java.util.TimeZone;

public class v {
    public static boolean a() {
        if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08")) {
            return true;
        }
        return false;
    }

    public static Date a(Date date, TimeZone timeZone, TimeZone timeZone2) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime() - ((long) (timeZone.getOffset(date.getTime()) - timeZone2.getOffset(date.getTime()))));
    }
}
