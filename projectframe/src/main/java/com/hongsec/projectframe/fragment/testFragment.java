package com.hongsec.projectframe.fragment;

import android.os.Bundle;

import com.hongsec.projectframe.bus.BusBuilder;
import com.hongsec.projectframe.fragment.base.LazyFragment;

/**
 * Created by Hongsec on 2016-04-17.
 * email : piaohongshi0506@gmail.com
 * QQ: 251520264
 */
public class testFragment extends LazyFragment {


    @Override
    protected void Bus_onEvent(BusBuilder myBus) {
        super.Bus_onEvent(myBus);
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
//        setContentView();
    }
}



