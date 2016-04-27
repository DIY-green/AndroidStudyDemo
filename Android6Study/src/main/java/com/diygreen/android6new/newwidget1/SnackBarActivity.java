package com.diygreen.android6new.newwidget1;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diygreen.android6new.R;

public class SnackBarActivity extends AppCompatActivity {

    private RelativeLayout mRootRL;
    private TextView mShowResultTV;
    private CoordinatorLayout mTestCL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        initView();
    }

    private void initView() {
        mRootRL = (RelativeLayout) findViewById(R.id.rl_root);
        mShowResultTV = (TextView) findViewById(R.id.tv_showresult);
        mTestCL = (CoordinatorLayout) findViewById(R.id.cl_test);
        mTestCL.setVisibility(View.GONE);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_showcommonsb:
                showCommonSB();
                break;
            case R.id.btn_showwithactionsb:
                showWithActionSB();
                break;
            case R.id.btn_setcallbacktest:
                setCallbackTest();
                break;
            case R.id.btn_changebackground:
                changeBackground();
                break;
            case R.id.btn_addcoordinatiorlayout:
                addCoordinatiorLayout();
                break;
        }
    }

    private void showCommonSB() {
        Snackbar snackbar = Snackbar.make(mRootRL,
                "我是普通 Snackbar", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void showWithActionSB() {
        final Snackbar snackbar = Snackbar.make(mRootRL,
                "我是带 Action 的 Snackbar", Snackbar.LENGTH_LONG);
        snackbar.setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SnackBarActivity.this, "撤销成功", Toast.LENGTH_SHORT).show();
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    private void setCallbackTest() {
        final Snackbar snackbar = Snackbar.make(mRootRL,
                "我是带 Action 的 Snackbar", Snackbar.LENGTH_LONG);
        snackbar.setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SnackBarActivity.this, "撤销成功", Toast.LENGTH_SHORT).show();
                snackbar.dismiss();
            }
        });
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                mShowResultTV.setText("Snackbar - onDismissed()");
            }
            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
                mShowResultTV.setText("Snackbar - onShown()");
            }
        });
        snackbar.show();
    }


    private void changeBackground() {
        Snackbar snackbar = Snackbar.make(mRootRL,
                "我是普通 Snackbar", Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        snackbar.show();
    }

    private void addCoordinatiorLayout() {
        mTestCL.setVisibility(View.VISIBLE);
        Snackbar snackbar = Snackbar.make(mTestCL,
                "我是普通 Snackbar", Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        snackbar.show();
    }

}
