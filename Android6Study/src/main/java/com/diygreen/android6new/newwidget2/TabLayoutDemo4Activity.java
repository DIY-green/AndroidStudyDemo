package com.diygreen.android6new.newwidget2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.diygreen.android6new.R;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutDemo4Activity extends AppCompatActivity {

    private TabLayout mTitleTL;
    private ViewPager mContentVP;

    private String[] mTitleArr = {"TAB1", "TAB2", "TAB3"};
    private List<View> mViewList;
    private TabLayoutDemo2PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayoutdemo4);

        initView();
        initData();
    }

    private void initView() {
        mTitleTL = (TabLayout) findViewById(R.id.tl_title);
        mContentVP = (ViewPager) findViewById(R.id.vp_content);
    }

    private void initData() {
        int size = mTitleArr.length;
        mViewList = new ArrayList<>(size);
        // 初始化 View 列表
        for (int i = 0; i < size; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_tablayoutdemo2_vp, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_text);
            tv.setText(mTitleArr[i]);
            mViewList.add(view);
        }
        mAdapter = new TabLayoutDemo2PagerAdapter(mViewList);

        // 填充 ViewPager
        mContentVP.setAdapter(mAdapter);
        mTitleTL.setupWithViewPager(mContentVP);
        size = mTitleTL.getTabCount();
        for (int i = 0; i < size; i++) {
            TabLayout.Tab tab = mTitleTL.getTabAt(i);
            if (tab != null) {
                tab.setText(mTitleArr[i]);
            }
        }
        mContentVP.setCurrentItem(1);
    }
}
