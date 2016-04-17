package com.hongsec.projectframe.bus;

/**
 * Created by Hongsec on 2016-04-17.
 * email : piaohongshi0506@gmail.com
 * QQ: 251520264
 */
public interface EventBusListener {

    public void onEvent(BusBuilder myBus);

    public void onEventMainThread(BusBuilder myBus);

    public void onEventBackgroundThread(BusBuilder myBus);

    public void onEventAsync(BusBuilder myBus);
}
