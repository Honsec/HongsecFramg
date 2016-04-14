package com.hongsec.projectframe.activiyty;

import android.os.Bundle;

import com.hongsec.projectframe.R;
import com.hongsec.projectframe.activiyty.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void preInit() {

    }

    @Override
    protected int setContentLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void viewLoadFinished() {

    }

    @Override
    protected ActivityAnim setfinishAnimationCode() {
        return ActivityAnim.NONE;
    }
}
