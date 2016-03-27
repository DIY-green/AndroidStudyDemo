package com.cheng.networkframestudy.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cheng.networkframestudy.C;
import com.cheng.networkframestudy.R;
import com.cheng.networkframestudy.xutils.frame.XUtil3HttpUtil;
import com.cheng.networkframestudy.xutils.frame.callback.HttpCallBack;
import com.cheng.utils.Logger;
import com.cheng.utils.file.SDUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.StringReader;
import java.util.HashMap;

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
            case R.id.btn_posttest:
                postTest();
                break;
            case R.id.btn_uploadfile:
                uploadfile();
                break;
            case R.id.btn_download:
                download();
                break;
        }
    }

    private void showLoading() {
        mLoadingPB.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        mLoadingPB.setVisibility(View.GONE);
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

    public void getTest() {
        String url="http://flash.weather.com.cn/wmaps/xml/china.xml";
        XUtil3HttpUtil.Get(url, null, new HttpCallBack<String>() {

            @Override
            public void onSuccess(String xmlString) {
                super.onSuccess(xmlString);
                try {
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xmlPullParser = factory.newPullParser();
                    xmlPullParser.setInput(new StringReader(xmlString));
                    int eventType = xmlPullParser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                String nodeName = xmlPullParser.getName();
                                if ("city".equals(nodeName)) {
                                    String pName = xmlPullParser.getAttributeValue(0);
                                    Logger.e("TAG", "city is " + pName);
                                }
                                break;
                        }
                        eventType = xmlPullParser.next();
                    }
                } catch (Exception e) {
                    Logger.e(e);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }

        });
    }

    private void postTest() {
        String url="http://api.k780.com:88/?app=idcard.get";
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("appkey", "10003");
        map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        map.put("format", "json");
        map.put("idcard", "110101199001011114");
        XUtil3HttpUtil.Post(url, map, new HttpCallBack<String>(){

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                mShowResultTV.setText(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }
        });
    }

    private void uploadfile() {

    }

    private void download() {
        //文件下载地址
        String url = "http://imgsrc.baidu.com/forum/w%3D580/sign=b935b9f78701a18bf0eb1247ae2d0761/1e20d01373f08202684eaee44bfbfbeda9641bf3.jpg";
        //文件保存在本地的路径
        String filepath = SDUtil.getSDCardPath(C.APP_NAME) + File.separator + "mm" + System.currentTimeMillis() + ".jpg";
        XUtil3HttpUtil.DownLoadFile(url, filepath,new HttpCallBack<File>(){
            @Override
            public void onSuccess(File result) {
                super.onSuccess(result);
                if (result == null) return;
                mShowResultTV.setText(result.getAbsolutePath());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);

            }

        });
    }

}
