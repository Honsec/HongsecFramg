package com.hongsec.lib.recycleview.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.hongsec.lib.CommonViewHolder;
import com.hongsec.lib.recycleview.support.MultiItemTypeSupport;

import java.util.List;

public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T>
{

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas,
                                  MultiItemTypeSupport<T> multiItemTypeSupport)
    {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position)
    {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        CommonViewHolder holder = CommonViewHolder.get(mContext, null, parent, layoutId, -1);
        setListener(parent, holder, viewType);
        return holder;
    }

}
