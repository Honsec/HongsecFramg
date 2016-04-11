package com.hongsec.hongsecframe.ui.recycleview.support;

public interface MultiItemTypeSupport<T>
{
	int getLayoutId(int itemType);

	int getItemViewType(int position, T t);
}