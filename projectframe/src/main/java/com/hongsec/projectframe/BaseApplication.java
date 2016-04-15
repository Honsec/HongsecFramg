package com.hongsec.projectframe;

import android.support.multidex.MultiDexApplication;

import com.hongsec.projectframe.utils.SystemUtils;

/**
 * Created by Hongsec on 2016-04-15.
 */
public class BaseApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();


        if(getPackageName().equalsIgnoreCase(SystemUtils.getCurProcessName(this))){
            //메인 프로세스일때 초기화 해야할 작업


        }



    }
}
