package com.cheng.mvpframestudy.themvp.presenter.fragment;

import android.view.View;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.databinder.Demo4DataBinder;
import com.cheng.mvpframestudy.themvp.frame.databind.DataBindFragment;
import com.cheng.mvpframestudy.themvp.frame.databind.DataBinder;
import com.cheng.mvpframestudy.themvp.model.bean.User;
import com.cheng.mvpframestudy.themvp.ui.delegate.FragmentDelegate;

/**
 * Created by Administrator on 2015/11/24.
 */
public class Demo4Fragment extends DataBindFragment<FragmentDelegate> {

    private User data = new User();

    @Override
    protected void bindEventListener() {
        super.bindEventListener();
        viewDelegate.get(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setName(viewDelegate.getText(R.id.editText));
                data.setAge(viewDelegate.getText(R.id.editText2));
                notifyModelChanged(data);
            }
        });
    }

    @Override
    protected Class<FragmentDelegate> getDelegateClass() {
        return FragmentDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new Demo4DataBinder();
    }
}
