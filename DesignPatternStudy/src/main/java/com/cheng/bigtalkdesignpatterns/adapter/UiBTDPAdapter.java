package com.cheng.bigtalkdesignpatterns.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.cheng.designpatternstudy.R;

public class UiBTDPAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_adapter);
    }

    public void onClick(View v) {
        Player b = new Forwards("巴蒂尔");
        b.attack();
        Player m = new Guards("麦克格雷迪");
        m.attack();

//        Player ym = new Center("姚明");
//        ym.attack();
//        ym.defense();
        Player ym = new Translator("姚明");
        ym.attack();
        ym.defense();
    }
}
