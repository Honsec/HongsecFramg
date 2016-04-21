package com.hongsec.projectframe.utils;

import android.util.Log;

import com.hongsec.projectframe.common.Config;

public class LogUtils {

    private static String DEFAULT_TAG = "DEFAULT";

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void v(String msg) {
        if(!Config.is_log) return ;
        Log.v(DEFAULT_TAG, msg);

    }

    public static void d(String msg){
        if(!Config.is_log) return ;
        Log.d(DEFAULT_TAG,msg);
    }

    public static void e(String msg){
        if(!Config.is_log) return ;
        Log.e(DEFAULT_TAG,msg);
    }
    public static void v(String tag,String msg){
        if(!Config.is_log) return ;
        Log.v(tag, msg);
    }

    public static void e(String tag,String msg){
        if(!Config.is_log) return ;
        Log.e(tag, msg);
    }
    public static void d(String tag,String msg){
        if(!Config.is_log) return ;
        Log.d(tag, msg);
    }

    public static void v_category(String category,String msg){
        if(!Config.is_log) return ;
        Log.v(DEFAULT_TAG, "["+category+"]"+msg);
    }

    public static void e_category(String category,String msg){
        if(!Config.is_log) return ;
        Log.e(DEFAULT_TAG, "["+category+"]"+msg);
    }
    public static void d_category(String category,String msg){
        if(!Config.is_log) return ;
        Log.d(DEFAULT_TAG,"["+category+"]"+msg);
    }

    public static void v_category(String tag,String category,String msg){
        if(!Config.is_log) return ;
        Log.v(tag, "["+category+"]"+msg);
    }

    public static void e_category(String tag,String category,String msg){
        if(!Config.is_log) return ;
        Log.e(tag, "["+category+"]"+msg);
    }
    public static void d_category(String tag,String category,String msg){
        if(!Config.is_log) return ;
        Log.d(tag,"["+category+"]"+msg);
    }


}
