package com.cheng.mvpframestudy.themvp.databinder;

import com.cheng.mvpframestudy.themvp.frame.databind.DataBinder;
import com.cheng.mvpframestudy.themvp.model.bean.JavaBean;
import com.cheng.mvpframestudy.themvp.ui.delegate.SimpleDelegate;

/**
 * 设值器，将数据与视图显示绑定，会在数据改变时调用
 *
 * @author kymjs (http://www.kymjs.com/) on 10/30/15.
 */
public class Demo2DataBinder implements DataBinder<SimpleDelegate, JavaBean> {

    @Override
    public void viewBindModel(SimpleDelegate viewDelegate, JavaBean data) {
        viewDelegate.setText(data.getName());
    }
}
