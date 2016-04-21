package com.hongsec.projectframe.utils.errorcap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.hongsec.projectframe.R;
import com.hongsec.projectframe.activiyty.base.BaseActivity;
import com.hongsec.projectframe.utils.ToastUtils;


/**
 * Created by Hongsec on 2016-01-27.
 */
public class CustomCrashAct extends BaseActivity {







    public void restartapp(View v) {

        try {

            final Class<? extends Activity> restartActivityClass = CustomActivityOnCrash.getRestartActivityClassFromIntent(getIntent());

            Intent intent = new Intent(CustomCrashAct.this, restartActivityClass);
            CustomActivityOnCrash.restartApplicationWithIntent(CustomCrashAct.this, intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void closeapp(View v) {

        try {

            CustomActivityOnCrash.closeApplication(CustomCrashAct.this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void show_error(View v) {

        try {

            AlertDialog dialog = new AlertDialog.Builder(CustomCrashAct.this)
                    .setTitle(R.string.customactivityoncrash_error_activity_error_details_title)
                    .setMessage(CustomActivityOnCrash.getAllErrorDetailsFromIntent(CustomCrashAct.this, getIntent()))
                    .setPositiveButton(R.string.customactivityoncrash_error_activity_error_details_close, null)
                    .setNeutralButton(R.string.customactivityoncrash_error_activity_error_details_copy,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    copyErrorToClipboard();
                               ToastUtils.showLong(CustomCrashAct.this, R.string.customactivityoncrash_error_activity_error_details_copied);
                                }
                            })
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void copyErrorToClipboard() {
        try {
            String errorInformation =
                    CustomActivityOnCrash.getAllErrorDetailsFromIntent(CustomCrashAct.this, getIntent());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(getString(R.string.customactivityoncrash_error_activity_error_details_clipboard_label), errorInformation);
                clipboard.setPrimaryClip(clip);
            } else {
                //noinspection deprecation
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                clipboard.setText(errorInformation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {
        if (CustomActivityOnCrash.isShowErrorDetailsFromIntent(getIntent())){
            findViewById(R.id.act_crash_error).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void preInit() {

    }

    @Override
    protected int setContentLayoutResID() {
        return R.layout.activity_crash;
    }

    @Override
    protected void viewLoadFinished() {

    }

    @Override
    protected ActivityAnim setfinishAnimationCode() {
        return null;
    }
}
