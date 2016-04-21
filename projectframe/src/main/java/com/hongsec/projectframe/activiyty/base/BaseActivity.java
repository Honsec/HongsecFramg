package com.hongsec.projectframe.activiyty.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongsec.projectframe.bus.BusBuilder;
import com.hongsec.projectframe.bus.BusTool;

import de.greenrobot.event.EventBus;

/**
 * Created by Hongsec on 2016-04-14.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 앱티비티가 로드 완료되였는지 표기하는 필더 onWindowFocusChanged 참고
     */
    private boolean loaded = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setContentLayoutResID());



        //이벤트 버스 등록
        try {
            if (!EventBus.getDefault().isRegistered(this))
                EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


        preInit();
        initViews();
        initDatas();


    }

    /**
     * 데이터 초기화  in onCreate
     */
    protected abstract void initDatas();

    /**
     * 뷰초기화  in onCreate
     */
    protected abstract void initViews();

    /**
     * 뷰 초기환 전  작업  in onCreate
     */
    protected abstract void preInit();

    /**
     * ContentView ResourceID
     *
     * @return
     */
    protected abstract int setContentLayoutResID();

    /**
     * 액티비티 뷰가 완전히 로드되였을때 호출됨 <br>
     * (액티비티가 완전히 로드되였는지는 onWindowsFocused가 호출될때 완료되였다고 판단함 )
     */
    protected abstract void viewLoadFinished();

    /**
     * Activity꺼질때 어떤 애니메션 보여줄지 결정 {@link ActivityAnim}
     *
     * @return
     */
    protected abstract ActivityAnim setfinishAnimationCode();


    /**
     * 애니메션 스위치 값들
     */
    protected enum ActivityAnim { //TODO
        NONE
    }

    ;

    /**
     * findViewById를 다시 만듬
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T autoFindViewById(int id) {

        return (T) super.findViewById(id);
    }


    /**
     * 액티비티 켝때 애니메션
     *
     * @param intent
     */
    public void startActivityWidthAnimation(Intent intent, ActivityAnim activityAnim) {

        startActivity(intent);

        if (ActivityAnim.NONE == activityAnim) {
            //none
        }

    }


    protected void Bus_onEvent(BusBuilder myBus) {
    }

    ;

    protected void Bus_onEventMainThread(BusBuilder myBus) {
    }

    ;

    protected void Bus_onEventBackgroundThread(BusBuilder myBus) {
    }

    ;

    protected void Bus_onEventAsync(BusBuilder myBus) {
    }

    ;

    /**
     * 자식에서 쓸필요 없음
     *
     * @param myBus
     */
    public void onEvent(BusBuilder myBus) {
        if (BusTool.onEventBusFilter(myBus, BusBuilder.BUSTYPE.onEvent, this.getClass().getSimpleName())) {
            return;
        }
        Bus_onEvent(myBus);
    }

    ;

    /**
     * 자식에서 쓸필요 없음
     *
     * @param myBus
     */
    public void onEventMainThread(BusBuilder myBus) {
        if (BusTool.onEventBusFilter(myBus, BusBuilder.BUSTYPE.onEventMainThread, this.getClass().getSimpleName())) {
            return;
        }
        Bus_onEventMainThread(myBus);
    }

    ;

    /**
     * 자식에서 쓸필요 없음
     *
     * @param myBus
     */
    public void onEventBackgroundThread(BusBuilder myBus) {
        if (BusTool.onEventBusFilter(myBus, BusBuilder.BUSTYPE.onEventBackgroundThread, this.getClass().getSimpleName())) {
            return;
        }
        Bus_onEventBackgroundThread(myBus);
    }

    ;

    /**
     * 자식에서 쓸필요 없음
     *
     * @param myBus
     */
    public void onEventAsync(BusBuilder myBus) {
        if (BusTool.onEventBusFilter(myBus, BusBuilder.BUSTYPE.onEventAsync, this.getClass().getSimpleName())) {
            return;
        }
        Bus_onEventAsync(myBus);
    }

    ;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        loaded = false;

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
    public void finish() {
        super.finish();

        finishAnimation();

    }


    //꺼지는 애니메션
    private void finishAnimation() {

        if (setfinishAnimationCode() == ActivityAnim.NONE) {
            // no animation
        }

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {

            if (!loaded) {
                //로드완료
                loaded = true;

                viewLoadFinished();
            }


        }

    }


}
