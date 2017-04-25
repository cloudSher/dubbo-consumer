package com.sher.boot.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by wei.zhao on 2017/4/25.
 */
public class DataUtil {

    public static int random(int max){
        return new Random().nextInt(max);
    }



    public static Date todayAndltCurr(long timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),random(calendar.get(Calendar.HOUR_OF_DAY)),random(60),random(60));
        return calendar.getTime();
    }


    public static void main(String args[]){
        System.out.println(todayAndltCurr(1493119856518L));
    }

}
