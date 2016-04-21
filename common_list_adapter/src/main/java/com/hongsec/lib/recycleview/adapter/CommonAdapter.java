package com.hongsec.lib.recycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongsec.lib.CommonViewHolder;
import com.hongsec.lib.recycleview.support.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hongsec on 2016-04-11.
 */
public abstract class  CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    protected Context mContext;

    protected int mLayoutId;

    protected List<T> mDatas;

    protected LayoutInflater mInflater;

    private OnItemClickListener<T> mOnItemClickListener;


    public void setOnItemClickListener(OnItemClickListener<T> mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public CommonAdapter(Context context, int layotuId, List<T> datas) {

        mContext = context;
        mLayoutId = layotuId;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;

        if (mDatas == null) {
            mDatas = new ArrayList<T>();
        }

    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonViewHolder commonViewHolder = CommonViewHolder.get(mContext, null, parent, mLayoutId, -1);
        setListener(parent, commonViewHolder, viewType);

        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {

        holder.updatePosition(position);


    }


    public abstract  void convert(CommonViewHolder viewHolder,T t);



    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    protected int getPosition(CommonViewHolder viewHolder) {

        return viewHolder.getAdapterPosition();
    }


    protected boolean isEnabled(int viewType) {

        return true;//TODO
    }


    protected void setListener(final ViewGroup parent, final CommonViewHolder viewHolder, int viewType) {

        if (!isEnabled(viewType)) return;

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnItemClickListener != null) {

                    int position = getPosition(viewHolder);
                    mOnItemClickListener.onItemClick(parent, v, mDatas.get(position), position);

                }


            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if(mOnItemClickListener!=null){

                    int position = getPosition(viewHolder);
                    return mOnItemClickListener.onItemLongClick(parent,v,mDatas.get(position),position);
                }



                return false;
            }
        });


    }


}
