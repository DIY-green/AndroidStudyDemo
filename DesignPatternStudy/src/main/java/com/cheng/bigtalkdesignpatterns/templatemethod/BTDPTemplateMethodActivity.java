package com.cheng.bigtalkdesignpatterns.templatemethod;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.cheng.designpatternstudy.R;

public class BTDPTemplateMethodActivity extends AppCompatActivity {

    private static final String TAG = "BTDPTemplateMethodActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_templatemethod);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                testA();
                break;
            case R.id.button2:
                testB();
                break;
        }
    }

    private void testA() {
        Log.e(TAG, "学生甲抄的试卷：");
        TestPaper studentA = new TestPaperA();
        studentA.testQuestion1();
        studentA.testQuestion2();
        studentA.testQuestion3();
    }

    private void testB() {
        Log.e(TAG, "学生乙抄的试卷：");
        TestPaper studentB = new TestPaperB();
        studentB.testQuestion1();
        studentB.testQuestion2();
        studentB.testQuestion3();
    }

}
