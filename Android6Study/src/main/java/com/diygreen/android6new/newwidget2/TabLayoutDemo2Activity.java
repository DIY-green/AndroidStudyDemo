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

public class TabLayoutDemo2Activity extends AppCompatActivity {

    private TabLayout mTitleTL;
    private ViewPager mContentVP;

    private String[] mTitleArr = {"Android", "Java", "iOS", "C", "C++", "Swift", "Python"};
    private List<View> mViewList;
    private TabLayoutDemo2PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayoutdemo2);

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
        // 添加 Tab，初始化 View 列表
        for (int i = 0; i < size; i++) {
            mTitleTL.addTab(mTitleTL.newTab().setText(mTitleArr[i]));
            View view = LayoutInflater.from(this).inflate(R.layout.item_tablayoutdemo2_vp, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_text);
            tv.setText(mTitleArr[i]);
            mViewList.add(view);
        }
        mAdapter = new TabLayoutDemo2PagerAdapter(mViewList);

        // 填充 ViewPager
        mContentVP.setAdapter(mAdapter);
        // 给ViewPager添加监听
        mContentVP.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTitleTL));
        // 设置setOnTabSelectedListener
        mTitleTL.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 切换到指定的 item
                mContentVP.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                addTab();
                break;
            case R.id.btn_removefirst:
                removeFirstTab();
                break;
            case R.id.btn_removelast:
                removeLastTab();
                break;
            case R.id.btn_removeall:
                removeAllTabs();
                break;
        }
    }

    private void addTab() {
        TabLayout.Tab tab = mTitleTL.newTab().setText("DIY-TAB");
        mTitleTL.addTab(tab);
        View view = LayoutInflater.from(this).inflate(R.layout.item_tablayoutdemo2_vp, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_text);
        tv.setText("DIY-TAB");
        mViewList.add(view);
        mAdapter.notifyDataSetChanged();
    }

    private void removeFirstTab() {
        int count = mTitleTL.getTabCount();
        if (count <= 0) return;
        mTitleTL.removeTabAt(0);
        mViewList.remove(0);
        mAdapter.notifyDataSetChanged();
    }

    private void removeLastTab() {
        int count = mTitleTL.getTabCount();
        if (count <= 0) return;
        mTitleTL.removeTabAt(count - 1);
        mViewList.remove(count -1);
        mAdapter.notifyDataSetChanged();
    }

    private void removeAllTabs() {
        int count = mTitleTL.getTabCount();
        if (count <= 0) return;
        mTitleTL.removeAllTabs();
        mViewList.clear();
        mAdapter.notifyDataSetChanged();
    }
}
