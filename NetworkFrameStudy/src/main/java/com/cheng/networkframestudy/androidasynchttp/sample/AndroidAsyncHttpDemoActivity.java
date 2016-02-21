package com.cheng.networkframestudy.androidasynchttp.sample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cheng.networkframestudy.R;
import com.cheng.utils.UiUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class AndroidAsyncHttpDemoActivity extends AppCompatActivity {

    private static final String BASE_URLUrl = "http://10.138.114.147:8080/okHttpServer/";
    @Bind(R.id.pbar_showprogress)
    ProgressBar pbarShowprogress;
    @Bind(R.id.iv_showimage)
    ImageView ivShowImage;
    @Bind(R.id.tv_showresult)
    TextView tvShowResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidasynchttp_demo);
        ButterKnife.bind(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_gettest:
                testGet();
                break;
            case R.id.btn_posttest:
                testPost();
                break;
            case R.id.btn_uploadfiletest:
                testUploadFile();
                break;
            case R.id.btn_downloadfiletest:
                testDownloadFile();
                break;
            case R.id.btn_downloadimagetest:
                testDownloadImage();
                break;
            case R.id.btn_getusertest:
                testGetUser();
                break;
        }
    }

    private void testGet() {
        String url = "http://www.csdn.net/";
        AAHTestApi.getHtml(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody, "UTF-8");
                    tvShowResult.setText(result);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                UiUtil.toast(AndroidAsyncHttpDemoActivity.this, "error");
            }
        });
    }

    private void testPost() {
//
    }

    private void testUploadFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            UiUtil.toast(AndroidAsyncHttpDemoActivity.this, "文件不存在，请修改文件路径");
            return;
        }
        String url = BASE_URLUrl + "user!postFile";
        AAHTestApi.uploadFile(url, file, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody, "UTF-8");
                    tvShowResult.setText(result);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                UiUtil.toast(AndroidAsyncHttpDemoActivity.this, "error");
            }
        });
    }

    private void testDownloadFile() {
        String url = "http://images.csdn.net/20150817/1.jpg";
        AAHTestApi.downloadFile(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                UiUtil.toast(AndroidAsyncHttpDemoActivity.this, "Download Success!");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                UiUtil.toast(AndroidAsyncHttpDemoActivity.this, "Download Fail!");
            }
        });
    }

    private void testDownloadImage() {

    }

    private void testGetUser() {
        String url = BASE_URLUrl + "user!getUser";
        Map<String, String> params = new HashMap<>();
        params.put("username", "hyman");
        params.put("password", "123");
        AAHTestApi.getUser(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody, "UTF-8");
                    tvShowResult.setText(result);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                UiUtil.toast(AndroidAsyncHttpDemoActivity.this, "error");
            }
        });
    }

}
