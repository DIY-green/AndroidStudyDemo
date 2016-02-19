package com.cheng.mvpframestudy.themvp.ui.delegate;

import android.widget.TextView;

import com.cheng.mvpframestudy.R;
import com.cheng.mvpframestudy.themvp.frame.view.AppDelegate;

/**
 * View视图层，完全移除与Presenter业务逻辑的耦合
 *
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public class SimpleDelegate extends AppDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.ui_themvp_simple;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        TextView textView = get(R.id.text);
        textView.setText("在视图代理层创建布局");
    }

    public void setText(String text) {
        //get(id)等同于bindview(id)
        TextView textView = get(R.id.text);
        textView.setText(text);
    }
}
