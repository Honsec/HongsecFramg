package com.hongsec.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hongsec.lib.CommonViewHolder;
import com.hongsec.lib.recycleview.adapter.MultiItemCommonAdapter;
import com.hongsec.lib.recycleview.support.MultiItemTypeSupport;
import com.hongsec.sample.bean.ChatMessage;

import java.util.List;

/**
 * Created by Hongsec on 2016-04-11.
 */
public class MultiItemRvActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }







    private class ChatAdapterForRv extends MultiItemCommonAdapter<ChatMessage> {



        public ChatAdapterForRv(Context context, List<ChatMessage> datas, MultiItemTypeSupport<ChatMessage> multiItemTypeSupport) {
            super(context, datas, new MultiItemTypeSupport<ChatMessage>() {
                @Override
                public int getLayoutId(int itemType) {

                    if(itemType == ChatMessage.RECIEVE_MSG){

                        return R.layout.main_chat_from_msg;
                    }else{

                        return  R.layout.main_chat_send_msg;
                    }
                }

                @Override
                public int getItemViewType(int position, ChatMessage chatMessage) {

                    if(chatMessage.isComMeg()){

                        return  ChatMessage.RECIEVE_MSG;
                    }else{

                        return ChatMessage.SEND_MSG;
                    }

                }
            });


        }

        @Override
        public void convert(CommonViewHolder viewHolder, ChatMessage chatMessage) {

            switch (viewHolder.getLayoutId()){

                case R.layout.main_chat_from_msg:

                    viewHolder.setText(R.id.chat_send_content,chatMessage.getContent());
                    viewHolder.setText(R.id.chat_from_name,chatMessage.getName());
                    viewHolder.setImageResource(R.id.chat_from_icon,chatMessage.getIcon());

                    break;

                case R.layout.main_chat_send_msg:

                    viewHolder.setText(R.id.chat_send_content,chatMessage.getContent());
                    viewHolder.setText(R.id.chat_from_name,chatMessage.getName());
                    viewHolder.setImageResource(R.id.chat_send_icon,chatMessage.getIcon());

                    break;




            }



        }
    }





}
