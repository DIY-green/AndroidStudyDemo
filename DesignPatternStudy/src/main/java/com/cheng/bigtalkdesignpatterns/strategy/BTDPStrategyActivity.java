package com.cheng.bigtalkdesignpatterns.strategy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cheng.designpatternstudy.R;

public class BTDPStrategyActivity extends AppCompatActivity {

    private EditText mPriceET;
    private EditText mGoodNumET;
    private Spinner mDiscountStrategySP;
    private EditText mGoodItemsET;
    private TextView mTotalMoneyTV;

    private double mTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_strategy);
        initView();
    }

    private void initView() {
        this.mPriceET = (EditText) this.findViewById(R.id.bti_price_et);
        this.mGoodNumET = (EditText) this.findViewById(R.id.bti_goodnum_et);
        this.mDiscountStrategySP = (Spinner) this.findViewById(R.id.bti_discountstrategy_sp);
        this.mGoodItemsET = (EditText) this.findViewById(R.id.bti_gooditems_et);
        this.mTotalMoneyTV = (TextView) this.findViewById(R.id.bti_totalmoney_tv);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bti_sure_btn:
                addGoodToCart();
                break;
            case R.id.bti_reset_btn:
                resetUI();
                break;
        }
    }

    private void addGoodToCart() {
        CashContext csuper = new CashContext((String) mDiscountStrategySP.getSelectedItem());
        double totalPrices = 0d;
        double price = Double.parseDouble(mPriceET.getText().toString());
        double num = Double.parseDouble(mGoodNumET.getText().toString());
        totalPrices = csuper.getResult(price * num);
        mTotal += totalPrices;
        StringBuilder stringBuilder = new StringBuilder(mGoodItemsET.getText().toString());
        stringBuilder.append("单价：" + price + " 数量：" + num + "收费：" + totalPrices + "\n");
        mGoodItemsET.setText(stringBuilder);
        mTotalMoneyTV.setText(mTotal + "");
    }

    private void resetUI() {
        mPriceET.setText("");
        mGoodNumET.setText("");
        mDiscountStrategySP.setSelection(0, true);
        mGoodItemsET.setText("");
        mTotalMoneyTV.setText("");
        mTotal = 0d;
    }

}
