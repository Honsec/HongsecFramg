package com.hongsec.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hongsec.lib.CommonViewHolder;
import com.hongsec.lib.listview.CommonAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;

    private List<String> mDatas = new ArrayList<>(Arrays.asList("MultiItem ListView",
            "RecyclerView",
            "MultiItem RecyclerView","RecyclerViewWithHeader"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview =(ListView) findViewById(R.id.listview);

        listview.setAdapter(new CommonAdapter<String>(this,R.layout.item_listview,mDatas) {
            @Override
            public void convert(CommonViewHolder holder, String s) {

                holder.setText(R.id.text_title,s);

            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = null;

                switch (position){

                    case 0 : default:
                        intent = new Intent(MainActivity.this,MultiItemListViewActivity.class);

                        break;
                    case 2:

                        intent = new Intent(MainActivity.this,MultiItemRvActivity.class);

                        break;

                    case 1:

                        intent = new Intent(MainActivity.this,RecyclerViewActivity.class);

                        break;


                    case 3:

                        intent = new Intent(MainActivity.this,RvWidthHeaderActivity.class);

                        break;
                }


                if(intent !=null){
                    startActivity(intent);
                }


            }
        });



    }



}
