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

public class TabLayoutDemo3Activity extends AppCompatActivity {

    private TabLayout mBottomTL;
    private ViewPager mContentVP;

    private String[] mTitleArr = {"Android", "Java", "iOS"};
    private List<View> mViewList;
    private TabLayoutDemo2PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayoutdemo3);

        initView();
        initData();
    }

    private void initView() {
        mContentVP = (ViewPager) findViewById(R.id.vp_content);
        mBottomTL = (TabLayout) findViewById(R.id.tl_bottom);
    }

    private void initData() {
        int size = mTitleArr.length;
        mViewList = new ArrayList<>(size);
        // 添加 Tab，初始化 View 列表
        for (int i = 0; i < size; i++) {
            mBottomTL.addTab(mBottomTL.newTab().setText(mTitleArr[i]));
            View view = LayoutInflater.from(this).inflate(R.layout.item_tablayoutdemo2_vp, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_text);
            tv.setText(mTitleArr[i]);
            mViewList.add(view);
        }
        mAdapter = new TabLayoutDemo2PagerAdapter(mViewList);

        // 填充 ViewPager
        mContentVP.setAdapter(mAdapter);
        // 给ViewPager添加监听
        mContentVP.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mBottomTL));
        // 设置setOnTabSelectedListener
        mBottomTL.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
}
