package com.cheng.bigtalkdesignpatterns.simplefactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.cheng.designpatternstudy.R;

public class BTDPSimpleFactoryActivity extends AppCompatActivity {

    private EditText mNumberAET;
    private EditText mNumberBET;
    private Spinner mOperatorsSP;
    private TextView mResultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdp_simplefactory);

        initView();
    }

    private void initView() {
        this.mNumberAET = (EditText) this.findViewById(R.id.bti_numbera_et);
        this.mNumberBET = (EditText) this.findViewById(R.id.bti_numberb_et);
        this.mOperatorsSP = (Spinner) this.findViewById(R.id.bti_operators_sp);
        this.mResultTV = (TextView) this.findViewById(R.id.bti_result_tv);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bti_reset_btn:
                reset();
                break;
            case R.id.bti_calculate_btn:
                calculate();
                break;
        }
    }

    private void reset() {
        mNumberAET.setText("");
        mNumberBET.setText("");
        mResultTV.setText("");
        mOperatorsSP.setSelection(0, true);
    }

    private void calculate() {
        Operation operation = OperationFactory.createOperate((String) mOperatorsSP.getSelectedItem());
        operation.numberA = getDoubleFromEditText(mNumberAET);
        operation.numberB = getDoubleFromEditText(mNumberBET);
        double result = operation.getResult();
        mResultTV.setText(String.valueOf(result));
    }

    private double getDoubleFromEditText(EditText et) {
        double num = 0;
        if (et == null) {
            et.setError("传入的EditText不能为null");
            return num;
        }
        if (et.getText().length() == 0) {
            et.setError("请在EditText中输入要计算的数值");
            return num;
        }
        String temp = et.getText().toString();
        if (TextUtils.isEmpty(temp)) {
            et.setError("输入不能为空");
            return num;
        }
        try {
            num = Double.parseDouble(temp);
        } catch (Exception e) {
            num = 0;
        }
        return num;
    }

}
