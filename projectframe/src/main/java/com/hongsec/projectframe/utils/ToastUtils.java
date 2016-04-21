package com.hongsec.projectframe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Hongsec on 2016-03-10.
 * email : piaohongshi0506@gmail.com
 * QQ: 251520264
 */
public class ToastUtils {

    public static void showLong(Context context,String message){

        Toast toast= Toast.makeText(context,message, Toast.LENGTH_LONG);
        toast.show();
    }
    public static void showLong(Context context,int message){
        Toast toast= Toast.makeText(context,message, Toast.LENGTH_LONG);
        toast.show();
    }


    public static void showShort(Context context,int message){
        Toast toast= Toast.makeText(context,message, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 25);
        toast.show();
    }


}