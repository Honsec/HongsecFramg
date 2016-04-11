package com.hongsec.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by Hongsec on 2016-04-11.
 */
public class MultiItemListViewActivity extends AppCompatActivity {

    protected ListView mListView ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);
        mListView.setDivider(null);
//        mListView.setAdapter();
    }




    private class ChatAdapter{



    }

}
