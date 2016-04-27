package com.diygreen.android6new.newapi;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.diygreen.android6new.R;

public class ColorStateListActivity extends AppCompatActivity {

    private Button mTestBtn;
    private Button mTestBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorstatelist);

        initView();
    }

    private void initView() {
        this.mTestBtn = (Button) findViewById(R.id.btn_test);
        this.mTestBtn2 = (Button) findViewById(R.id.btn_test2);
    }

    public void onClick(View v) {
        testGetColorStateList();
    }

    @TargetApi(23)
    private void testGetColorStateList() {
        ColorStateList csl = getColorStateList(R.color.text_selector1);
        if (csl != null) {
            mTestBtn.setTextColor(csl);
        }

        Resources resources = getResources();
        ColorStateList csl2 = resources.getColorStateList(R.color.text_selector2);
        if (csl2 != null) {
            mTestBtn2.setTextColor(csl2);
        }

    }
}
