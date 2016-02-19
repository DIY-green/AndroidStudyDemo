package com.cheng.multithreadstudy.sunframework.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cheng.multithreadstudy.sunframework.proxy.MessageProxy;
import com.cheng.multithreadstudy.sunframework.proxy.ModelMap;
import com.cheng.multithreadstudy.sunframework.proxy.common.IRefreshBack;
import com.cheng.multithreadstudy.sunframework.proxy.helper.ActivityHelper;


/**
 * 默认使用Toolbar不用ActionBar
 */
public class BaseAsyncActivity<T extends BaseControl> extends AppCompatActivity implements IRefreshBack {

    protected T mControl;
    protected MessageProxy messageProxy;
    protected ModelMap mModel;
    private ActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new ActivityHelper<T, BaseAsyncActivity>(this);
        mHelper.onCreate();
        initVar();
    }

    public void initVar() {
        mModel = mHelper.getModelMap();
        messageProxy = mHelper.getMessageProxy();
        mControl = (T) mHelper.getControl();
    }

    @Override
    protected void onStart() {
        mHelper.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        mHelper.onResume();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mHelper.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHelper.onPause();
    }

    @Override
    protected void onStop() {
        mHelper.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mHelper.onDestroy();
        super.onDestroy();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
//        if (intent != null && intent.getComponent() != null && !intent.getComponent().getClassName().equals(MainActivity.class.getName()) &&
//                !intent.getComponent().getClassName().equals(LoginActivity.class.getName())) {
//            overridePendingTransition(R.anim.push_bottom_in, R.anim.hold_long);
//        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
//        if (intent != null && intent.getComponent() != null && !intent.getComponent().getClassName().equals(MainActivity.class.getName()) &&
//                !intent.getComponent().getClassName().equals(LoginActivity.class.getName())) {
//            overridePendingTransition(R.anim.push_bottom_in, R.anim.hold_long);
//        }
    }

    @Override
    public void finish() {
        super.finish();
//        if (!((Object) this).getClass().equals(MainActivity.class) && !((Object) this).getClass().equals(LoginActivity.class)) {
//            overridePendingTransition(R.anim.hold_long, R.anim.push_bottom_out);
//        }
    }

    protected boolean isPaused() {
        return mHelper.isPause();
    }

    @Override
    public void onRefresh(int requestCode, Bundle bundle) {

    }

}
