package com.cheng.bigtalkdesignpatterns.visitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.cheng.designpatternstudy.R;

public class BTDPVisitorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_visitor);
    }

    public void onClick(View v) {
        ObjectStructure o = new ObjectStructure();
        o.attach(new Man());
        o.attach(new Woman());

        // 成功时的反应
        Success v1 = new Success();
        o.display(v1);
        // 失败时的反应
        Failing v2 = new Failing();
        o.display(v2);
        // 恋爱时的反应
        Amativeness v3 = new Amativeness();
        o.display(v3);

        // 新增结婚时的反应
        Marriage v4 = new Marriage();
        o.display(v4);
    }
}
