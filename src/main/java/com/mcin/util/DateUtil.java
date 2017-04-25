package com.mcin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mcin on 2017/3/9.
 */
public class DateUtil {

    /**
     * date 时间转 string
     * @param date
     * @return
     */
    public static String returnHoure(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null){
            return "";
        }
        return sdf.format(date);
    }
}
