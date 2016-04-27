package com.diygreen.android6new.newapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diygreen.android6new.R;

public class ShareActivity extends AppCompatActivity {

    private ImageView mIconIV;
    private TextView mAcceptedTV;
    private TextView mShareContentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        initView();
        initData();
    }

    private void initView() {
        mIconIV = (ImageView) findViewById(R.id.iv_icon);
        mAcceptedTV = (TextView) findViewById(R.id.tv_accepted);
        mShareContentTV = (TextView) findViewById(R.id.tv_sharecontent);
    }

    private void initData() {

    }

    public void onClick(View v) {
        Toast.makeText(ShareActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
    }
}
