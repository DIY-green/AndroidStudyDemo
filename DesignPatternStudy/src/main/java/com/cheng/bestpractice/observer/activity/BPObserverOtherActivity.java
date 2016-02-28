package com.cheng.bestpractice.observer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cheng.designpatternstudy.R;
import com.cheng.bestpractice.observer.util.EventType;
import com.cheng.bestpractice.observer.util.Notify;

public class BPObserverOtherActivity extends AppCompatActivity {

    private Button mUpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpobserver_other);
        mUpdateBtn = (Button) findViewById(R.id.btn_update);
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notify.getInstance().NotifyActivity(EventType.UPDATE_Text);
                Notify.getInstance().NotifyActivity(EventType.UPDATE_MAIN);
            }
        });
    }


}
