package com.diygreen.android6new.newwidget3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.diygreen.android6new.R;

import java.util.ArrayList;

public class CollapsingToolbarLayout1Activity extends AppCompatActivity {

    private RecyclerView mContentRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsingtoolbarlayout1);

        initView();
        initData();
    }

    private void initView() {
        mContentRV = (RecyclerView) findViewById(R.id.rv_content);
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

}
