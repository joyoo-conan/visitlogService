package com.codepulse.visitlogService.Common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommLib {
    public static boolean isEmptyOrNull(String str) {
        if (str != null && !str.isEmpty() && !str.trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd"); //yyyyMMdd
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HHmmssSS"); //HHmmssSS
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate.substring(0, 8);
    }
}
