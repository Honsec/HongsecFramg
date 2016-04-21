package com.hongsec.projectframe;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.multidex.MultiDexApplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hongsec.projectframe.db.DB_APP;
import com.hongsec.projectframe.utils.LruBitmapCache;
import com.hongsec.projectframe.utils.SqUtils;
import com.hongsec.projectframe.utils.SystemUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Hongsec on 2016-04-15.
 */
public class BaseApplication extends MultiDexApplication {


    private Thread.UncaughtExceptionHandler mExceptionHandler;


    private RequestQueue requestQueue;

    private com.android.volley.toolbox.ImageLoader mImageLoader;
    private SqUtils sqUtils;
    private static BaseApplication baseApp = null;
    @Override
    public void onCreate() {
        super.onCreate();

        baseApp = this;

        if(getPackageName().equalsIgnoreCase(SystemUtils.getCurProcessName(this))){
            //메인 프로세스일때 초기화 해야할 작업

            initErrorCapture();
        }

        //init imageloader
        initImageLoader();

        //init volley
        initVolley();

        //init SharePreference
        initShareference();

    }

    public static BaseApplication getBaseApp(){

        return baseApp;
    }

    private void initShareference() {
        if (sqUtils == null )
            sqUtils = new SqUtils(getApplicationContext());
    }

    public SqUtils getSqUtils() {
        initShareference();

        return sqUtils;
    }

    private void initImageLoader() {
        try {
            ImageLoaderConfiguration config = getImageLoaderConfiguration();
            ImageLoader.getInstance().init(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ImageLoaderConfiguration getImageLoaderConfiguration() {
        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        return new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCache(new LruMemoryCache(5 * 1024 * 1024))
                .memoryCacheSize(10 * 1024 * 1024)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .build();
    }

    public com.android.volley.toolbox.ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new com.android.volley.toolbox.ImageLoader(this.requestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }


    public DisplayImageOptions getOptions(int drawableId) {
        if (drawableId != 0) {
            return new DisplayImageOptions.Builder()
                    .showImageOnLoading(drawableId)
                    .showImageForEmptyUri(drawableId)
                    .showImageOnFail(drawableId)
                    .resetViewBeforeLoading(true)
                    .cacheInMemory(true)
                    .cacheOnDisc(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        } else {
            return new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .cacheInMemory(true)
                    .cacheOnDisc(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }


    }


    private void initErrorCapture() {

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {

                String error = getStackTrace(getApplicationContext(),ex);

                DB_APP db_app = new DB_APP(getApplicationContext());
                db_app.openDB();


                db_app.InsertError(error);

                db_app.closeDB();

            }
        });


    }

    public static  String getStackTrace(Context context, Throwable th) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        Throwable casue = th;

        while (casue != null) {
            casue.printStackTrace(printWriter);
            casue = casue.getCause();
        }

        final String stacktraceAsString = result.toString();
        printWriter.close();

        String info =SystemUtils.getDeviceManufacture()+"/" +SystemUtils.getDeviceName() + " / "
                + SystemUtils.getSystemVersion() + " / "
                +SystemUtils.getAppVersion(context).toString()+ "\n";
        String eBody = info + stacktraceAsString;

        return eBody;
    }

    private void initVolley() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(this);
    }

    public RequestQueue getRequestQueue() {
        initVolley();

        return requestQueue;
    }
}
