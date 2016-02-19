package com.cheng.mvpframestudy.themvp.presenter.activity;

import android.os.Bundle;
import android.view.View;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.databinder.Demo2DataBinder;
import com.cheng.mvpframestudy.themvp.frame.databind.DataBindActivity;
import com.cheng.mvpframestudy.themvp.frame.databind.DataBinder;
import com.cheng.mvpframestudy.themvp.model.bean.JavaBean;
import com.cheng.mvpframestudy.themvp.ui.delegate.SimpleDelegate;

/**
 * 这里就偷懒直接复用demo1里面的simpleDelegate视图代理了
 * 同样也说明了，我们可以不用做任何改变就把一个视图层的东西拿来复用
 *
 * @author kymjs (http://www.kymjs.com/) on 10/24/15.
 */
public class DemoActivity extends DataBindActivity<SimpleDelegate> {

    JavaBean data = new JavaBean("名字");

    @Override
    protected void bindEventListener() {
        super.bindEventListener();
        //模拟数据改变(比如也可以写在网络请求成功的时候改变数据)
        viewDelegate.get(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setName("改变了数据");
                //通知数据发生了改变
                notifyModelChanged(data);
            }
        });
    }

    @Override
    protected Class<SimpleDelegate> getDelegateClass() {
        return SimpleDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new Demo2DataBinder();
    }

}
