package com.cheng.mvpframestudy.themvp.presenter.activity;

import android.os.Bundle;
import android.view.View;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.frame.presenter.ActivityPresenter;
import com.cheng.mvpframestudy.themvp.model.bean.Demo6Data;
import com.cheng.mvpframestudy.themvp.ui.delegate.SimpleDelegate;

import de.greenrobot.event.EventBus;

/**
 * 使用EvenBus实现简易数据绑定
 *
 * @author kymjs (http://www.kymjs.com/) on 10/30/15.
 */
public class Demo6Activity extends ActivityPresenter<SimpleDelegate> {


    @Override
    protected Class<SimpleDelegate> getDelegateClass() {
        return SimpleDelegate.class;
    }

    @Override
    protected void bindEventListener() {
        super.bindEventListener();
        viewDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Demo6Data data = new Demo6Data();
                data.setName("点击了数据，修改名字");
            }
        }, R.id.button1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(Demo6Data data) {
        viewDelegate.setText(data.getName());
    }
}
