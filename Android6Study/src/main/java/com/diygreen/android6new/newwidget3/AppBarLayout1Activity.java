package com.diygreen.android6new.newwidget3;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.diygreen.android6new.R;

import java.util.ArrayList;

public class AppBarLayout1Activity extends AppCompatActivity {

    private RelativeLayout mRootRL;
    private RecyclerView mContentRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbarlayout1);

        initView();
        initData();
    }

    private void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tb_title);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_tabs);

        mRootRL = (RelativeLayout) findViewById(R.id.rl_root);
        mContentRV = (RecyclerView) findViewById(R.id.rv_content);

        setSupportActionBar(toolbar);
        initTabs(tabLayout);
    }

    private void initTabs(TabLayout tabLayout) {
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText("TAB" + i);
            tabLayout.addTab(tab);
        }
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        // 设置布局管理器
        mContentRV.setLayoutManager(layoutManager);
        ArrayList dataList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            dataList.add("DIY-ITEM:" + i);
        }
        RecyclerAdapter adapter = new RecyclerAdapter(dataList);
        mContentRV.setAdapter(adapter);
    }

    public void onClick(View v) {
        Snackbar snackbar = Snackbar.make(mRootRL,
                "我是普通 Snackbar", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
