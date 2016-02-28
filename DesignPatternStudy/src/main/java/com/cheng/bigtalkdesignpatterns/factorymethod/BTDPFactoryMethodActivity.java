package com.cheng.bigtalkdesignpatterns.factorymethod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.cheng.designpatternstudy.R;

public class BTDPFactoryMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_factorymethod);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bti_test1_btn:
                // 工厂方法模式
                IFactory undergraduateFactory = new UndergraduateFactory();
                LeiFeng student = undergraduateFactory.createLeiFeng();
                student.buyRice();
                student.sweep();
                student.wash();
                break;
            case R.id.bti_test2_btn:
                IFactory volunteeFactory = new VolunteerFactory();
                LeiFeng voluntee = volunteeFactory.createLeiFeng();
                voluntee.buyRice();
                voluntee.sweep();
                voluntee.wash();
                break;
        }
    }
}
