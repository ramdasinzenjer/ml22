package com.example.lida.myapplication.xtras;

/**
 * Created by SUDHEESH on 11/14/2017.
 */

public class timeStampName {
    public static String time()
    {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        return ts;
    }
}
