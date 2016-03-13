package com.cheng.networkframestudy.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cheng.networkframestudy.C;
import com.cheng.networkframestudy.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class XUtils3DemoActivity extends AppCompatActivity {

    private TextView mShowResultTV;
    private ProgressBar mLoadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils3demo);

        initView();
    }

    private void initView() {
        this.mShowResultTV = (TextView) this.findViewById(R.id.tv_showresult);
        this.mLoadingPB = (ProgressBar) this.findViewById(R.id.pb_loading);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ipsearch:
                ipSearch();
                break;
            case R.id.btn_telsearch:
                telSearch();
                break;
            case R.id.btn_gettest:
                getTest();
                break;
        }
    }

    private void ipSearch() {
        // 请求参数
        RequestParams params = new RequestParams(C.baseurl.TAOBAO_IP_SEARCH_BASE_URL);
        params.addParameter("ip", "63.223.108.42");
        showLoading();
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException arg0) {
                hideLoading();
            }
            @Override
            public void onError(Throwable arg0, boolean arg1) {
                hideLoading();
                mShowResultTV.setText(arg0.toString());
            }
            @Override
            public void onFinished() {
                hideLoading();
            }
            @Override
            public void onSuccess(String arg0) {
                hideLoading();
                // 成功下载，显示到txtv上面
                mShowResultTV.setText(arg0);
            }
        });
    }

    private void telSearch() {
        // 请求参数
        RequestParams params = new RequestParams(C.baseurl.BAIDU_API_STORE + C.apiurl.MOBILE_PHONE_SERVICE);
        params.addHeader("apikey", C.apikey.BAIDU_API_STORE_KEY);
        params.addParameter("tel", "15111111111");
        showLoading();
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException arg0) {
                hideLoading();
            }
            @Override
            public void onError(Throwable arg0, boolean arg1) {
                hideLoading();
                mShowResultTV.setText(arg0.toString());
            }
            @Override
            public void onFinished() {
                hideLoading();
            }
            @Override
            public void onSuccess(String arg0) {
                hideLoading();
                // 成功下载，显示到txtv上面
                mShowResultTV.setText(arg0);
            }
        });
    }

    private void showLoading() {
        mLoadingPB.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        mLoadingPB.setVisibility(View.GONE);
    }

    public void getTest() {
        // 请求参数
        RequestParams params = new RequestParams("http://www.baidu.com");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException arg0) {
            }
            @Override
            public void onError(Throwable arg0, boolean arg1) {
            }
            @Override
            public void onFinished() {
            }
            @Override
            public void onSuccess(String arg0) {
                // 成功下载，显示到txtv上面
                mShowResultTV.setText(arg0);
            }
        });
    }
}
