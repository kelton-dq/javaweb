/**
 * Copyright (C), 2020-2022, XDU
 * FileName: DateTrans
 * Author: Dingq
 * Date: 2022/4/26 22:20
 * Description:
 */
package myssm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTrans {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //String->Date
    public static Date transToDate(String s){
        try {
            Date date = sdf.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String tansToString(Date date){
        return sdf.format(date);
    }
}