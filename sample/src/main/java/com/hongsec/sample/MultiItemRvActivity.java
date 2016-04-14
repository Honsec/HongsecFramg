package com.hongsec.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hongsec.sample.bean.ChatMessage;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.MultiItemCommonAdapter;
import com.zhy.base.adapter.recyclerview.MultiItemTypeSupport;
import com.zhy.base.adapter.recyclerview.OnItemClickListener;

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

        ChatAdapterForRv chatAdapterForRv = new ChatAdapterForRv(this,ChatMessage.MOCK_DATAS);
        chatAdapterForRv.setOnItemClickListener(new OnItemClickListener<ChatMessage>() {

            @Override
            public void onItemClick(ViewGroup parent, View view, ChatMessage chatMessage, int position) {
                Toast.makeText(MultiItemRvActivity.this, "Click:" + position + " => " + chatMessage.getContent(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, ChatMessage chatMessage, int position) {
                Toast.makeText(MultiItemRvActivity.this, "LongClick:" + position + " => " + chatMessage.getContent(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mRecyclerView.setAdapter(chatAdapterForRv);


    }







    private class ChatAdapterForRv extends MultiItemCommonAdapter<ChatMessage> {



        public ChatAdapterForRv(Context context, List<ChatMessage> datas ) {
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
        public void convert(ViewHolder viewHolder, ChatMessage chatMessage){

            switch (viewHolder.getLayoutId()){

                case R.layout.main_chat_from_msg:

                    viewHolder.setText(R.id.chat_from_content,chatMessage.getContent());
                    viewHolder.setText(R.id.chat_from_name,chatMessage.getName());
                    viewHolder.setImageResource(R.id.chat_from_icon,chatMessage.getIcon());

                    break;

                case R.layout.main_chat_send_msg:

                    viewHolder.setText(R.id.chat_send_content,chatMessage.getContent());
                    viewHolder.setText(R.id.chat_send_name,chatMessage.getName());
                    viewHolder.setImageResource(R.id.chat_send_icon,chatMessage.getIcon());

                    break;




            }



        }
    }





}
