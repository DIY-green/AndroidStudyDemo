package com.cheng.bigtalkdesignpatterns.decorator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.cheng.designpatternstudy.R;

public class BTDPDecotatorActivity extends AppCompatActivity {

    private static final String TAG = "BTDPDecotatorActivity";

    private TextView mShowTest1TV;
    private TextView mShowTest2TV;
    private TextView mShowTest3TV;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_decotator);

        initView();
        initData();
    }

    private void initView() {
        this.mShowTest1TV = (TextView) this.findViewById(R.id.bti_showtest1_tv);
        this.mShowTest2TV = (TextView) this.findViewById(R.id.bti_showtest2_tv);
        this.mShowTest3TV = (TextView) this.findViewById(R.id.bti_showtest3_tv);
    }

    private void initData() {
        person = new Person("小菜");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bti_test1_btn:
                test1();
                break;
            case R.id.bti_test2_btn:
                test2();
                break;
            case R.id.bti_test3_btn:
                test3();
                break;
        }
    }

    private void test1() {
        person.clearStringBuilder();
        Log.e(TAG, "\n第一种装扮：");
        Sneakers sneakers = new Sneakers();
        BigTrouser bigTrouser = new BigTrouser();
        TShirts tShirts = new TShirts();

        sneakers.decorate(person);
        bigTrouser.decorate(sneakers);
        tShirts.decorate(bigTrouser);
        tShirts.show();

        person.getStringBuilder().insert(0, "\n" +
                "第一种装扮：");
        mShowTest1TV.setText(person.getStringBuilder());
    }

    private void test2() {
        person.clearStringBuilder();
        Log.e(TAG, "\n第二种装扮：");
        LeatherShoes leatherShoes = new LeatherShoes();
        Tie tie = new Tie();
        Suit suit = new Suit();

        leatherShoes.decorate(person);
        tie.decorate(leatherShoes);
        suit.decorate(tie);
        suit.show();

        person.getStringBuilder().insert(0, "\n" +
                "第二种装扮：");
        mShowTest2TV.setText(person.getStringBuilder());
    }

    private void test3() {
        person.clearStringBuilder();
        Log.e(TAG, "\n第三种装扮：");
        Sneakers sneakers = new Sneakers();
        LeatherShoes leatherShoes = new LeatherShoes();
        BigTrouser bigTrouser = new BigTrouser();
        Tie tie = new Tie();

        sneakers.decorate(person);
        leatherShoes.decorate(sneakers);
        bigTrouser.decorate(leatherShoes);
        tie.decorate(bigTrouser);
        tie.show();

        person.getStringBuilder().insert(0, "\n" +
                "第三种装扮：");
        mShowTest3TV.setText(person.getStringBuilder());
    }

}
