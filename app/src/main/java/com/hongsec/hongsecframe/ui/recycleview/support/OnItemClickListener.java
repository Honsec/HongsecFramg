package com.hongsec.hongsecframe.ui.recycleview.support;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hongsec on 2016-04-11.
 */
public interface OnItemClickListener<T> {

    void onItemClick(ViewGroup parent,View view,T t,int position);
    boolean onItemLongClick(ViewGroup parent,View view,T t,int position);

}
