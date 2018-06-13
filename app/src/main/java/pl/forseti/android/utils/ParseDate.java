package pl.forseti.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Volfram on 13.06.2018.
 */

public class ParseDate {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_READABLE = "dd/MM/yyyy HH:mm:ss";

    public static String dateToStringDate(Date date, String pattern) {
        SimpleDateFormat format;
        if (pattern.equals("")) {
            format = new SimpleDateFormat(DATE_PATTERN);
        } else {
            format = new SimpleDateFormat(pattern);
        }

        return format.format(date);
    }
}
