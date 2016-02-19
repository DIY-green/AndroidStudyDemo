package com.cheng.mvpframestudy.themvp.databinder;

import com.cheng.mvpframestudy.themvp.frame.databind.DataBinder;
import com.cheng.mvpframestudy.themvp.model.bean.User;
import com.cheng.mvpframestudy.themvp.ui.delegate.FragmentDelegate;

/**
 * 设值器，将数据与视图显示绑定，会在数据改变时调用
 *
 * @author kymjs (http://www.kymjs.com/) on 10/30/15.
 */
public class Demo6DataBinder implements DataBinder<FragmentDelegate, User> {

    @Override
    public void viewBindModel(FragmentDelegate viewDelegate, User user) {
        if (user != null && user.getAge() != null && user.getName() != null) {
            viewDelegate.setResult(user.getName(), user.getAge());
        }
    }
}