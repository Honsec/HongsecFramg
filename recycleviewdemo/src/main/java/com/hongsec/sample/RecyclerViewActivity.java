package com.hongsec.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hongsec on 2016-04-11.
 */
public class RecyclerViewActivity extends AppCompatActivity{


    private List<String> mDatas= new ArrayList<String>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initDatas();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setAdapter(new CommonAdapter<String>(this,R.layout.item_list,mDatas) {
            @Override
            public void convert(ViewHolder viewHolder, String s) {

                viewHolder.setText(R.id.id_item_list_title,s);

            }
        });

    }

    private void initDatas() {

        for(int i='A';i<'z';i++){
            mDatas.add((char)i+"");
        }

    }
}
