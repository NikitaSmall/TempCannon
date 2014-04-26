package com.github.alexYer.tempCannon.util;

public class Log {
    public static void i(String msg) {
        android.util.Log.i("TempCannon", msg);
    }


    public static void e(String msg) {
        android.util.Log.e("TempCannon", msg);
    }
} 
