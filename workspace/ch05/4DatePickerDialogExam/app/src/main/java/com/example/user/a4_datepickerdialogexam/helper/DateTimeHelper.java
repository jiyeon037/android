package com.example.user.a4_datepickerdialogexam.helper;

import java.util.Calendar;

public class DateTimeHelper {
    // ------------- 싱글톤 객체 생성 시작 --------------
    private static DateTimeHelper instance = null;

    public static DateTimeHelper getInstance() {
        if(instance == null) instance = new DateTimeHelper();

        return instance;
    }
    private DateTimeHelper() {}
    // ------------- 싱글톤 객체 생성 끝 --------------
    public int[] getDate(){
        Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH) + 1;
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int[] result = {yy, mm, dd};
        return result;
    }

    public int[] getTime(){
        Calendar calendar = Calendar.getInstance();
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mi = calendar.get(Calendar.MINUTE) + 1;
        int ss = calendar.get(Calendar.SECOND);
        int[] result = {hh, mi, ss};
        return result;
    }
}
