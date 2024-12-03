package com.project.back.common.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class ChangeDateFormatUtil {

    public static String changeYYYYMMDD(String original) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = simpleDateFormat.parse(original);
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String writeDatetime = simpleDateFormat.format(datetime);
        return writeDatetime;
    }

    public static String changeYYYYMMDDHHMMSS(String original) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = simpleDateFormat.parse(original);
        simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String joinDate = simpleDateFormat.format(datetime);
        return joinDate;
    }

    public static String changeYYMMDD(String original) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = simpleDateFormat.parse(original);
        simpleDateFormat = new SimpleDateFormat("yy.MM.dd");
        String writeDatetime = simpleDateFormat.format(datetime);
        return writeDatetime;
    }

    public static String nowYYYYMMDD() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = Date.from(Instant.now());
        return simpleDateFormat.format(now);
    }
}
