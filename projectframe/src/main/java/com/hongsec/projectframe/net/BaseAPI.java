package com.hongsec.projectframe.net;

import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.hongsec.projectframe.activiyty.base.BaseActivity;
import com.hongsec.projectframe.bean.API;
import com.hongsec.projectframe.common.Config;
import com.hongsec.projectframe.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by chief on 2015-12-22.
 */
public abstract class BaseAPI<T> {

    public int new_ad_count = 0;
    public int new_more_count = 0;
    public API api;
    protected int socketTimeout = 20000;
    protected RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS);
    private String store = "";
    private String baseedid = "";
    private String regid = "";
    private boolean is_review;

    public BaseAPI(BaseActivity context) {
        api = new API();

    }

    public abstract JSONObject getRequestJson();

    public abstract String getURL();

    public abstract void request(BaseActivity context, NetworkCallbackListener<T> callback);

    public abstract int getRequestType();

    /**
     * mm device mode
     *
     * @return
     */
    public String getReqURL() {

        return Config.BASE_URL + getURL() + getResult();
    }

    public String getResult() {
        String result = "?";

        long time = System.currentTimeMillis() / 1000;
        String encode = time + "^aresjoy";

        try {
//            result += "&mm=" + URLEncoder.encode(Common.Encode256(encode), "utf8") +
//                    "&device=android" +
//                    "&model=" + URLEncoder.encode(Build.MODEL, "utf8") +
//                    "&ver=" + Config.VERSION_NAME +
//                    "&build=" + Config.VERSION_CODE +
//                    "&reg=" + regid +
//                    "&edid=" + URLEncoder.encode(Common.Encode256(baseedid), "utf8") +
//                    "&locale=" + Locale.getDefault().toString() +
//                    "&store=" + store;

            //if logined
//            if (!TextUtils.isEmpty(userBaseData.ses) && !TextUtils.isEmpty(userBaseData.mid)) {
//                result += "&ses=" + URLEncoder.encode(userBaseData.ses, "utf8") +
//                        "&mid=" + URLEncoder.encode(userBaseData.mid, "utf8");
//            }

            //if not get or other
            if (checkAddictionable()) {
                result += url2Param();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * set  status,error,msg
     *
     * @param jsonObject
     */
    protected void setResult(JSONObject jsonObject) {
        api.status = getJBoolean(jsonObject, "status");
        api.error = getJInteger(jsonObject, "error");
        api.msg = getJString(jsonObject, "msg");
    }

    protected String url2Param() {
        JSONObject jsonObject = getRequestJson();
        StringBuilder stringBuilder = new StringBuilder();

        Iterator<String> iter = jsonObject.keys();
        while (iter.hasNext()) {

            stringBuilder.append("&");

            String key = iter.next();

            try {
                String value = jsonObject.getString(key);

                String result = URLEncoder.encode(key, "utf8") + "=";

                if (!TextUtils.isEmpty(value)) {
                    result += URLEncoder.encode(value, "utf8");
                }

                stringBuilder.append(result);

            } catch (JSONException e) {

                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        return stringBuilder.toString();
    }

    private boolean checkAddictionable() {
        int requestType = getRequestType();
        switch (requestType) {
            case Request.Method.GET:
                return true;
            case Request.Method.DELETE:
                return true;

            default:
                return false;
        }
    }

    protected void onErrorListener(BaseActivity context, String TAG, VolleyError arg0, boolean default_alert) {

        LogUtils.e(TAG, "onErrorListener : " + arg0);


        //check status code
        check_statusCode(context, arg0, default_alert);

    }

    private void check_statusCode(final BaseActivity context, VolleyError arg0, boolean default_alert) {
        try {
            NetworkResponse response = arg0.networkResponse;
            if (response != null && response.data != null) {
                LogUtils.v("response.statusCode:" + response.statusCode);
                LogUtils.v("response.data:" + new String(response.data));


                String json_str = "";
                json_str = new String(response.data);
                try {
                    JSONObject jsonObject = new JSONObject(json_str);
                    setResult(jsonObject);
                    LogUtils.e(jsonObject.toString(4));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                switch (response.statusCode) {
//                    case 409:
//                        //alert
//                        //                    context
//                        if (default_alert) {
//                            context.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Utils_Alert.showAlertDialog(context, android.R.string.dialog_alert_title, api.msg, true, android.R.string.ok, null, 0, null, null);
//                                }
//                            });
//                        } else {
//                            Custom_Alert(context);
//                        }
//
//                        break;
//                    case 401:
//                        Common.logout(context);
//                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void Custom_Alert(BaseActivity context);


    protected boolean getJBoolean(JSONObject jsonObject, String param_name) {
        boolean result = false;
        try {
            if (jsonObject != null && jsonObject.has(param_name)) {
                result = jsonObject.getBoolean(param_name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected String getJString(JSONObject jsonObject, String param_name) {
        String result = "";
        try {
            if (jsonObject != null && jsonObject.has(param_name)) {
                result = jsonObject.getString(param_name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected JSONArray getJJsonArray(JSONObject jsonObject, String param_name) {
        JSONArray result = new JSONArray();
        try {
            if (jsonObject != null && jsonObject.has(param_name)) {
                result = jsonObject.getJSONArray(param_name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /* protected JSONObject getJJsonObject(JSONObject jsonObject,String param_name){
         JSONObject result=new JSONObject();
         try {
             if(jsonObject!=null&&jsonObject.has(param_name)){
                 result=jsonObject.getJSONObject(param_name);
             }
         } catch (JSONException e) {
             e.printStackTrace();
         }
         return result;
     }*/
    protected int getJInteger(JSONObject jsonObject, String param_name) {
        int result = 0;
        try {
            if (jsonObject != null && jsonObject.has(param_name)) {
                result = jsonObject.getInt(param_name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected long getJLong(JSONObject jsonObject, String param_name) {
        long result = 0;
        try {
            if (jsonObject != null && jsonObject.has(param_name)) {
                result = jsonObject.getLong(param_name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }




}
