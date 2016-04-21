package com.hongsec.projectframe.common;

/**
 * Created by Hongsec on 2016-04-21.
 * email : piaohongshi0506@gmail.com
 * QQ: 251520264
 */
public class Config {

    public static final boolean  is_log = true;

    public static final boolean is_real = false;

    public static String BASE_URL ="";



    public static void reset(){

        initBASE_URL();
    }

    private static void initBASE_URL() {
        try {                 //TODO 서버 주소 편짐 해야 함
            if (is_real) {
                BASE_URL ="url";
            } else {
                BASE_URL ="url 2";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
