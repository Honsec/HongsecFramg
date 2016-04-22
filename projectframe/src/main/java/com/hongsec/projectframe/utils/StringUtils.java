package com.hongsec.projectframe.utils;

import android.content.Context;

/**
 * Created by Hongsec on 2016-04-20.
 */
public class StringUtils {

    /*
    * 根据 source id 名字转换int 型
    * */
    public static int getResource(Context context, String resourcename, int defaultId){

        int result = defaultId;
        try {
            result = context.getResources().getIdentifier(resourcename,"drawable",context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
