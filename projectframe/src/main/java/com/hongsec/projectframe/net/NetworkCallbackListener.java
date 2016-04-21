package com.hongsec.projectframe.net;

public interface NetworkCallbackListener<T> {
    public void onResult(T targetApi);
}