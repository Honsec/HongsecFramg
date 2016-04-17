package com.hongsec.projectframe.bus;

import java.util.List;

/**
 * Created by Hongsec on 2016-04-17.
 * email : piaohongshi0506@gmail.com
 * QQ: 251520264
 */
public class BusBuilder {

    public enum BUSTYPE{ onEvent ,onEventMainThread,onEventBackgroundThread,onEventAsync};

    public BUSTYPE bustype;

    /**
     * Target class Name <br/>
     */
    public List<String> target_name=null;

    /**
     * Target to all
     */
    public boolean target_all=false;


    /**
     * Can save anything
     */
    public Object object = null;


}
