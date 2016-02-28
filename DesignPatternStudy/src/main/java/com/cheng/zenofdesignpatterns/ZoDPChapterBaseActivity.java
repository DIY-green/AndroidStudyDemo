package com.cheng.zenofdesignpatterns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.cheng.designpatternstudy.R;

public abstract class ZoDPChapterBaseActivity extends AppCompatActivity {

    protected TextView mTitleTV;
    protected TextView mContentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zodpchapter);
        initView();
        initData();
    }

    private void initView() {
        this.mTitleTV = (TextView) this.findViewById(R.id.zoi_title_tv);
        this.mContentTV = (TextView) this.findViewById(R.id.zoi_content_tv);

        this.mContentTV.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    protected abstract void initData();

    public void onClick(View v) {

    }
}
