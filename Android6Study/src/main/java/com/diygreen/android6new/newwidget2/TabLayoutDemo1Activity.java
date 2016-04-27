package com.diygreen.android6new.newwidget2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.diygreen.android6new.R;

public class TabLayoutDemo1Activity extends AppCompatActivity {

    private TabLayout mTestTL;
    private Switch mChangeTabModeSwitch;

    private int mTabsNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayoutdemo1);

        initView();
        initListener();
    }

    private void initView() {
        mTestTL = (TabLayout) findViewById(R.id.tl_test);
        mChangeTabModeSwitch = (Switch) findViewById(R.id.switch_changetabmode);
    }

    private void initListener() {
        mChangeTabModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTestTL.setTabMode(isChecked ? TabLayout.MODE_SCROLLABLE : TabLayout.MODE_FIXED);
                mTestTL.invalidate();
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
        mTabsNum += 1;
        TabLayout.Tab tab = mTestTL.newTab().setText("TAB" + mTabsNum);
        mTestTL.addTab(tab);
    }

    private void removeFirstTab() {
        int count = mTestTL.getTabCount();
        if (count <= 0) return;
        mTestTL.removeTabAt(0);
    }

    private void removeLastTab() {
        int count = mTestTL.getTabCount();
        if (count <= 0) return;
        mTestTL.removeTabAt(count - 1);
    }

    private void removeAllTabs() {
        int count = mTestTL.getTabCount();
        if (count <= 0) return;
        mTabsNum = 0;
        mTestTL.removeAllTabs();
    }

}
