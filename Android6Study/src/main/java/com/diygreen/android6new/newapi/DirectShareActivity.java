package com.diygreen.android6new.newapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.diygreen.android6new.R;

public class DirectShareActivity extends AppCompatActivity {

    private EditText mContentET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directshare);

        initView();
    }

    private void initView() {
        this.mContentET = (EditText) findViewById(R.id.et_sharecontent);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                testDirectShare();
                break;
        }
    }

    private void testDirectShare() {
        String shareContent = this.mContentET.getText().toString();
        if (TextUtils.isEmpty(shareContent)) return;
        final Intent intent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TITLE, "直接分享");
        startActivity(Intent.createChooser(intent, "ChooserTargetService"));
    }

}
