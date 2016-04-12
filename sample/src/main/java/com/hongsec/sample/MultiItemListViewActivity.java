package com.hongsec.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.hongsec.sample.bean.ChatMessage;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.MultiItemCommonAdapter;
import com.zhy.base.adapter.abslistview.MultiItemTypeSupport;

import java.util.List;

/**
 * Created by Hongsec on 2016-04-11.
 */
public class MultiItemListViewActivity extends AppCompatActivity {

    protected ListView mListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);
        mListView.setDivider(null);
        mListView.setAdapter(new ChatAdapter(this,ChatMessage.MOCK_DATAS));
    }


    private class ChatAdapter extends MultiItemCommonAdapter<ChatMessage> {


        public ChatAdapter(Context context, List<ChatMessage> datas ) {
            super(context, datas, new MultiItemTypeSupport<ChatMessage>() {
                @Override
                public int getLayoutId(int position, ChatMessage chatMessage) {

                    if (chatMessage.isComMeg()) {

                        return R.layout.main_chat_from_msg;
                    }

                    return R.layout.main_chat_send_msg;
                }

                @Override
                public int getViewTypeCount() {
                    return 2;
                }

                @Override
                public int getItemViewType(int position, ChatMessage chatMessage) {

                    if (chatMessage.isComMeg()) {

                        return ChatMessage.RECIEVE_MSG;
                    }

                    return ChatMessage.SEND_MSG;
                }
            });

        }

        @Override
        public void convert(ViewHolder holder, ChatMessage chatMessage) {

            switch (holder.getLayoutId()) {

                case R.layout.main_chat_from_msg:

                    holder.setText(R.id.chat_from_content, chatMessage.getContent());
                    holder.setText(R.id.chat_from_name, chatMessage.getName());
                    holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());

                    break;

                case R.layout.main_chat_send_msg:
                    holder.setText(R.id.chat_send_content, chatMessage.getContent());
                    holder.setText(R.id.chat_send_name, chatMessage.getName());
                    holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());

                    break;

            }


        }
    }

}
