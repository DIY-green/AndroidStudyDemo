package com.cheng.bigtalkdesignpatterns.proxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cheng.designpatternstudy.R;

public class BTDPProxyActivity extends AppCompatActivity {

    private Button mTestProxyBtn;
    private TextView mShowTestTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_proxy);
        initView();
    }

    private void initView() {
        this.mTestProxyBtn = (Button) this.findViewById(R.id.bti_testproxy_btn);
        this.mShowTestTV = (TextView) this.findViewById(R.id.bti_showtest_tv);
    }

    public void onClick(View v) {
        SchoolGirl schoolGirl = new SchoolGirl();
        schoolGirl.setName("MM");

        StringBuilder stringBuilder = new StringBuilder();
        Proxy proxy = new Proxy(schoolGirl);
        stringBuilder.append(proxy.giveDools() + "\n");
        stringBuilder.append(proxy.giveFlowers() + "\n");
        stringBuilder.append(proxy.giveChocolate());
        mShowTestTV.setText(stringBuilder);
    }

}
