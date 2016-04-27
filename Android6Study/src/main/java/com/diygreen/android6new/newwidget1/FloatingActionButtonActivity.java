package com.diygreen.android6new.newwidget1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.diygreen.android6new.R;

public class FloatingActionButtonActivity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar mChangeElevationSB;
    private FloatingActionButton mTestFABtn1;
    private FloatingActionButton mTestFABtn2;
    private FloatingActionButton mTestFABtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatingactionbutton);

        initView();
        initListener();
    }

    private void initView() {
        mTestFABtn1 = (FloatingActionButton) findViewById(R.id.fabtn_test1);
        mTestFABtn2 = (FloatingActionButton) findViewById(R.id.fabtn_test2);
        mTestFABtn3 = (FloatingActionButton) findViewById(R.id.fabtn_test3);
        mChangeElevationSB = (SeekBar) findViewById(R.id.sb_changeelevation);
    }

    private void initListener() {
        mTestFABtn1.setOnClickListener(this);
        mTestFABtn2.setOnClickListener(this);
        mTestFABtn3.setOnClickListener(this);
        mChangeElevationSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTestFABtn1.setElevation(progress);
                mTestFABtn2.setElevation(progress);
                mTestFABtn3.setElevation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(FloatingActionButtonActivity.this, v.toString(), Toast.LENGTH_SHORT).show();
    }
}
