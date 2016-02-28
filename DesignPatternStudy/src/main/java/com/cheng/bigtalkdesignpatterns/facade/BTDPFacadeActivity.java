package com.cheng.bigtalkdesignpatterns.facade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.cheng.designpatternstudy.R;

public class BTDPFacadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_facade);
    }

    public void onClick(View view) {
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
    }

}
