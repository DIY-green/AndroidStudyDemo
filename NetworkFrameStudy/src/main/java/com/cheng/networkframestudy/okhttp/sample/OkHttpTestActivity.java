package com.cheng.networkframestudy.okhttp.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheng.networkframestudy.R;
import com.cheng.utils.UiUtil;

import java.lang.ref.WeakReference;

/**
 * 李旺成
 * 2016年2月20日09:45:56
 */
public class OkHttpTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttptest);
    }

    protected void onTaskComplete(int taskId, String result) {
        UiUtil.toast(this, taskId + " = " + result);
    }

    protected void onTaskError(Exception e) {
        UiUtil.toast(this, e.getMessage());
    }

    private static final class InnerAsyncTask implements AsyncHttpCallback {

        private WeakReference<OkHttpTestActivity> activity = null;

        public InnerAsyncTask(OkHttpTestActivity act) {
            super();
            this.activity = new WeakReference<OkHttpTestActivity>(act);
        }

        @Override
        public void onTaskComplete(int taskId, String response) {
            OkHttpTestActivity activity = this.activity.get();
            if (null == activity) {
                return;
            }
            activity.onTaskComplete(taskId, response);
        }

        @Override
        public void onTaskError(Exception e) {
            OkHttpTestActivity activity = this.activity.get();
            if (null == activity) {
                return;
            }
            activity.onTaskError(e);
        }
    }
}
