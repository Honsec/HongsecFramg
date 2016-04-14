package com.hongsec.projectframe.activiyty.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Hongsec on 2016-04-14.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 앱티비티가 로드 완료되였는지 표기하는 필더 onWindowFocusChanged 참고
     */
    private  boolean loaded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setContentLayoutResID());

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
     * @return
     */
    protected abstract int setContentLayoutResID();

    /**
     * 액티비티 뷰가 완전히 로드되였을때 호출됨 <br>
     *    (액티비티가 완전히 로드되였는지는 onWindowsFocused가 호출될때 완료되였다고 판단함 )
     */
    protected abstract void viewLoadFinished();

    /**
     * Activity꺼질때 어떤 애니메션 보여줄지 결정 {@link ActivityAnim}
     * @return
     */
    protected abstract ActivityAnim setfinishAnimationCode();



    /**
     * 애니메션 스위치 값들
     */
    protected enum ActivityAnim{
        NONE
    };

    /**
     * findViewById를 다시 만듬
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T  autoFindViewById(int id){

        return (T) super.findViewById(id);
    }


    /**
     * 액티비티 켝때 애니메션
     * @param intent
     */
    public void startActivityWidthAnimation(Intent intent,ActivityAnim activityAnim ){

        startActivity(intent);

        if(ActivityAnim.NONE == activityAnim){
            //none
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        loaded =false;

    }

    @Override
    public void finish() {
        super.finish();

        finishAnimation();

    }


    //꺼지는 애니메션
    private void finishAnimation() {

        if(setfinishAnimationCode() == ActivityAnim.NONE){
            // no animation
        }

    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus ){

            if(!loaded){
                //로드완료
                loaded = true;

                viewLoadFinished();
            }


        }

    }









}
