package com.cheng.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cheng.utils.UiUtil;
import com.cheng.utils.ViewFinder;

/**
 * 自定义Activity基类
 */
public class BaseUi extends AppCompatActivity {

    public <T extends View> T findViewByID(int id) {
        return (T)ViewFinder.findViewById(this, id);
    }

    protected void overlay(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void toast(String str) {
        UiUtil.toast(this, str);
    }

}
