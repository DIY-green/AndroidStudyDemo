package com.cheng.mvpframestudy.themvp.databinder;

import com.cheng.mvpframestudy.themvp.frame.databind.DataBinder;
import com.cheng.mvpframestudy.themvp.model.bean.User;
import com.cheng.mvpframestudy.themvp.ui.delegate.FragmentDelegate;

/**
 * Created by Administrator on 2015/11/24.
 */
public class Demo4DataBinder implements DataBinder<FragmentDelegate, User> {

    @Override
    public void viewBindModel(FragmentDelegate viewDelegate, User user) {
        if (user!=null && user.getAge()!=null && user.getName()!=null) {
            viewDelegate.setResult(user.getName(), user.getAge());
        }
    }
}
