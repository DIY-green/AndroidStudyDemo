package com.cheng.bigtalkdesignpatterns.memento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.cheng.designpatternstudy.R;

public class BTDPMementoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_memento);
    }

    public void onClick(View v) {
        Originator o = new Originator();
        o.setState("On");  // 初始状态为On
        o.show();

        Caretaker c = new Caretaker();
        c.setMemento(o.createMemento()); // 保存状态时由于有了很好的封装，可以隐藏Originator的实现细节

        o.setState("Off");  // 改变了状态属性为Off
        o.show();

        o.setMemento(c.getMemento()); // 恢复原初始状态
        o.show();
    }

}
