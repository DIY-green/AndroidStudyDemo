package com.diygreen.android6new.newwidget1;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.diygreen.android6new.R;

public class TextInputLayoutActivity extends AppCompatActivity {

    private TextInputLayout mTestTIL;
    private EditText mTestET1;
    private EditText mTestET2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textinputlayout);

        initView();
        initData();
    }

    private void initView() {
        mTestTIL = (TextInputLayout) findViewById(R.id.til_test1);
        mTestET1 = (EditText) findViewById(R.id.et_test1);
        mTestET2 = (EditText) findViewById(R.id.et_test2);
    }

    private void initData() {
        mTestTIL.setHint("请输入你的邮箱：");
        mTestET1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 10) {
                    mTestTIL.setErrorEnabled(true);
                    mTestTIL.setError("邮箱名过长！");
                    mTestET1.setError("EditText 的错误提示");
                } else {
                    mTestTIL.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mTestET2.setError("没有任何修饰的 EditText 的错误提示");
    }


}
