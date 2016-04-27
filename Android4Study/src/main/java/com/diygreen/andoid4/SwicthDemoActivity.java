package com.diygreen.andoid4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SwicthDemoActivity extends AppCompatActivity {

    private Switch mTest1Switch;
    private Switch mTest2Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swicthdemo);

        initView();
        initListener();
    }

    private void initView() {
        this.mTest1Switch = (Switch) findViewById(R.id.switch_test1);
        this.mTest2Switch = (Switch) findViewById(R.id.switch_test2);
    }

    private void initListener() {
        this.mTest1Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String btnText = ((Switch)buttonView).getTextOff().toString();
                if (isChecked) {
                    btnText = ((Switch)buttonView).getTextOn().toString();
                }
                toast(btnText + isChecked);
            }
        });
        this.mTest2Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String btnText = ((Switch)buttonView).getTextOff().toString();
                if (isChecked) {
                    btnText = ((Switch)buttonView).getTextOn().toString();
                }
                toast(btnText + isChecked);
            }
        });
    }

    private void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
