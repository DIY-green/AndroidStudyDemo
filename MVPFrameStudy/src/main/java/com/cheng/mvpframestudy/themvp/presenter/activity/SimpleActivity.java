package com.cheng.mvpframestudy.themvp.presenter.activity;

import android.view.View;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.frame.presenter.ActivityPresenter;
import com.cheng.mvpframestudy.themvp.ui.delegate.SimpleDelegate;

/**
 * 在这里做业务逻辑操作，通过viewDelegate操作View层控件
 *
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public class SimpleActivity extends ActivityPresenter<SimpleDelegate> implements View
        .OnClickListener {

    @Override
    protected Class<SimpleDelegate> getDelegateClass() {
        return SimpleDelegate.class;
    }

    @Override
    protected void bindEventListener() {
        super.bindEventListener();
        //可以同时对多个控件设置同一个点击事件,后面id参数可以传多个
        viewDelegate.setOnClickListener(this, R.id.button1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                viewDelegate.setText("你点击了button");
                break;
        }
    }
}