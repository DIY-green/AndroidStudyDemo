package com.diygreen.andoid4;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

public class GridLayoutDemoActivity extends AppCompatActivity {

    private static final int CONTENT_VP_ITEMARR_SIZE = 11;

    private ViewPager mContentVP;
    private View[] mContentVPItemArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlayoutdemo);

        initView();
        initData();
    }

    private void initView() {
        this.mContentVP = (ViewPager) findViewById(R.id.vp_content);
        this.mContentVPItemArr = new View[CONTENT_VP_ITEMARR_SIZE];
        for (int i = 0; i < CONTENT_VP_ITEMARR_SIZE; i++) {
            mContentVPItemArr[i] = LayoutInflater.from(GridLayoutDemoActivity.this).inflate(R.layout.vp_item_gridlayoutdemo1 + i, null);
        }
    }

    private void initData() {
        this.mContentVP.setAdapter(new GridLayoutDemoPagerAdapter(mContentVPItemArr));
    }
}
