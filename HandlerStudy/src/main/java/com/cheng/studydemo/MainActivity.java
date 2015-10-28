package com.cheng.studydemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cheng.studydemo.handler.ThreadCommunication;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                ThreadCommunication.getInstance(this).childToMainTest();
                break;
            case R.id.button2:
                ThreadCommunication.getInstance(this).mainToChildTest();
                break;
            case R.id.button3:

                break;
        }
    }

}
