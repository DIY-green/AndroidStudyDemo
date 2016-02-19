package com.cheng.mvpframestudy.themvp.ui.delegate;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.frame.view.AppDelegate;

/**
 * Created by Administrator on 2015/11/24.
 */
public class Demo5Delegate extends AppDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.ui_themvp_demo5;
    }

    @Override
    public int getOptionsMenuId() {
        return R.menu.menu;
    }

    public Toolbar getToolbar() {
        return get(R.id.toolbar);
    }

    public void setText(String text) {
        TextView textView = get(R.id.text);
        textView.setText(text);
    }
}
