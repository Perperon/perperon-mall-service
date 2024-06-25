package com.perperon.mall.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dupengcheng
 * @date 2024-06-25
 * @apiNote
 */
public class DateUtils {

    public static String getCurrenTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSSSSSSSS");
        return dateFormat.format(date);
    }
}
