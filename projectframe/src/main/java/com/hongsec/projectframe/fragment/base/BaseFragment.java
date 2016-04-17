package com.hongsec.projectframe.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongsec.projectframe.bus.BusBuilder;
import com.hongsec.projectframe.bus.BusTool;

import java.lang.reflect.Field;

import de.greenrobot.event.EventBus;

public abstract class BaseFragment extends Fragment {
    protected LayoutInflater inflater;
    private View contentView;

    /**
     * Application Context
     */
    private Context context;

    private ViewGroup container;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();

        try {
            //이벤트 버스 해제
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        onCreateView(savedInstanceState);
        if (contentView == null)
            return super.onCreateView(inflater, container, savedInstanceState);
        return contentView;
    }

    protected void onCreateView(Bundle savedInstanceState) {

    }


    protected  void Bus_onEvent(BusBuilder myBus){};
    protected  void Bus_onEventMainThread(BusBuilder myBus){};
    protected  void Bus_onEventBackgroundThread(BusBuilder myBus){};
    protected  void Bus_onEventAsync(BusBuilder myBus){};

    /**
     * 자식에서 쓸필요 없음
     * @param myBus
     */
    public void onEvent(BusBuilder myBus){
        if(BusTool.onEventBusFilter(myBus, BusBuilder.BUSTYPE.onEvent,this.getClass().getSimpleName())){
            return ;
        }
        Bus_onEvent(myBus);
    };

    /**
     * 자식에서 쓸필요 없음
     * @param myBus
     */
    public void onEventMainThread(BusBuilder myBus){
        if(BusTool.onEventBusFilter(myBus, BusBuilder.BUSTYPE.onEventMainThread,this.getClass().getSimpleName())){
            return ;
        }
        Bus_onEventMainThread(myBus);
    };

    /**
     * 자식에서 쓸필요 없음
     * @param myBus
     */
    public void onEventBackgroundThread(BusBuilder myBus){
        if(BusTool.onEventBusFilter(myBus, BusBuilder.BUSTYPE.onEventBackgroundThread,this.getClass().getSimpleName())){
            return ;
        }
        Bus_onEventBackgroundThread(myBus);
    };

    /**
     * 자식에서 쓸필요 없음
     * @param myBus
     */
    public void onEventAsync(BusBuilder myBus){
        if(BusTool.onEventBusFilter(myBus, BusBuilder.BUSTYPE.onEventAsync,this.getClass().getSimpleName())){
            return ;
        }
        Bus_onEventAsync(myBus);
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        contentView = null;
        container = null;
        inflater = null;

        try {
            //이벤트 버스 해제
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * getActivity있기에 getApplicationContext 만있으면 좋음
     * @return
     */
    public Context getApplicationContext() {
        return context;
    }

    public void setContentView(int layoutResID) {
        setContentView((ViewGroup) inflater.inflate(layoutResID, container, false));
    }

    public void setContentView(View view) {
        contentView = view;
    }

    public View getContentView() {
        return contentView;
    }


    /**
     * findViewById를 다시 만듬
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T  autoFindViewById(int id){

        return (T) contentView.findViewById(id);
    }


    // http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
