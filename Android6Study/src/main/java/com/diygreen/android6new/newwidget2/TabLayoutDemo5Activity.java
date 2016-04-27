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

public class TabLayoutDemo5Activity extends AppCompatActivity {

    private TabLayout mTitleTL;
    private ViewPager mContentVP;

    private String[] mTitleArr = {"TAB1", "TAB2", "TAB3"};
    private List<View> mTabItemViewList;
    private List<View> mViewPagerViewList;
    private TabLayoutDemo2PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayoutdemo5);

        initView();
        initData();
    }

    private void initView() {
        mTitleTL = (TabLayout) findViewById(R.id.tl_title);
        mContentVP = (ViewPager) findViewById(R.id.vp_content);
    }

    private void initData() {
        int size = mTitleArr.length;
        mTabItemViewList = new ArrayList<>(size);
        mViewPagerViewList = new ArrayList<>(size);
        // 添加 Tab，初始化 View 列表
        for (int i = 0; i < size; i++) {
            View tabView = LayoutInflater.from(this).inflate(R.layout.item_tablayout_item, null);
            TextView tabViewTV = (TextView) tabView.findViewById(R.id.tv_tab);
            tabViewTV.setText(mTitleArr[i]);
            mTabItemViewList.add(tabView);

            View vpView = LayoutInflater.from(this).inflate(R.layout.item_tablayoutdemo2_vp, null);
            TextView viViewTV = (TextView) vpView.findViewById(R.id.tv_text);
            viViewTV.setText(mTitleArr[i] + i);
            mViewPagerViewList.add(vpView);
        }
        mAdapter = new TabLayoutDemo2PagerAdapter(mViewPagerViewList);

        // 填充 ViewPager
        mContentVP.setAdapter(mAdapter);
        mTitleTL.setupWithViewPager(mContentVP);
        size = mTitleTL.getTabCount();
        for (int i = 0; i < size; i++) {
            TabLayout.Tab tab = mTitleTL.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mTabItemViewList.get(i));
            }
        }
        mContentVP.setCurrentItem(1);
    }
}
