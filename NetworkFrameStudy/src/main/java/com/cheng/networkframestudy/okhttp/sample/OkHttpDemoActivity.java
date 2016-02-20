package com.cheng.networkframestudy.okhttp.sample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cheng.base.BaseActivity;
import com.cheng.networkframestudy.R;
import com.cheng.networkframestudy.okhttp.frame.OkHttpUtil;
import com.cheng.networkframestudy.okhttp.frame.callback.BitmapCallback;
import com.cheng.networkframestudy.okhttp.frame.callback.FileCallBack;
import com.cheng.networkframestudy.okhttp.frame.callback.StringCallback;
import com.cheng.utils.UiUtil;
import com.google.gson.Gson;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 李旺成
 * 2016年2月20日08:31:25
 */
public class OkHttpDemoActivity extends BaseActivity {

    private static final String TAG = "OkHttpDemoActivity";

    private TextView tvResult;
    private ImageView ivShowImg;
    private ProgressBar pbLoading;

    private String mBaseUrl = "http://10.138.114.147:8080/okHttpServer/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttpdemo);

        tvResult = (TextView) findViewById(R.id.tv_result);
        ivShowImg = (ImageView) findViewById(R.id.iv_showimg);
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        pbLoading.setMax(100);
    }

    public void testTemplate(View v) {
        NetModel.getInstance().doTaskAsyncGet(100, "http://www.csdn.net/", null, new InnerAsyncTask(this));
    }

    public void getHtml(View view) {
        String url = "http://www.csdn.net/";
        OkHttpUtil
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());

    }

    public void postString(View view) {
        String url = mBaseUrl + "user!postString";
        OkHttpUtil
                .postString()
                .url(url)
                .content(new Gson().toJson(new User("zhy", "123")))
                .build()
                .execute(new MyStringCallback());

    }

    public void postFile(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            Toast.makeText(OkHttpDemoActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = mBaseUrl + "user!postFile";
        OkHttpUtil
                .postFile()
                .url(url)
                .file(file)
                .build()
                .execute(new MyStringCallback());

    }

    public void getUser(View view) {
        String url = mBaseUrl + "user!getUser";
        OkHttpUtil
                .get()//
                .url(url)//
                .addParams("username", "hyman")//
                .addParams("password", "123")//
                .build()//
                .execute(new UserCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        tvResult.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(User response) {
                        tvResult.setText("onResponse:" + response.username);
                    }
                });
    }

    public void getUsers(View view) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "zhy");
        String url = mBaseUrl + "user!getUsers";
        OkHttpUtil//
                .post()//
                .url(url)//
//                .mParamMap(mParamMap)//
                .build()//
                .execute(new ListUserCallback() {//

                    @Override
                    public void onError(Call call, Exception e) {
                        tvResult.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(List<User> response) {
                        tvResult.setText("onResponse:" + response);
                    }
                });
    }

    public void getHttpsHtml(View view) {
        String url = "https://kyfw.12306.cn/otn/";

        OkHttpUtil
                .get()//
                .url(url)//
                .build()//
                .execute(new MyStringCallback());

    }

    public void getImage(View view) {
        tvResult.setText("");
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtil
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        tvResult.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ivShowImg.setImageBitmap(bitmap);
                    }
                });
    }

    public void uploadFile(View view) {

        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        if (!file.exists()) {
            Toast.makeText(OkHttpDemoActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        Map<String, String> headers = new HashMap<>();
        headers.put("APP-Key", "APP-Secret222");
        headers.put("APP-Secret", "APP-Secret111");

        String url = mBaseUrl + "user!uploadFile";

        OkHttpUtil.post()//
                .addFile("mFile", "messenger_01.png", file)//
                .url(url)//
                .params(params)//
                .headers(headers)//
                .build()//
                .execute(new MyStringCallback());
    }

    public void multiFileUpload(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        File file2 = new File(Environment.getExternalStorageDirectory(), "test1.txt");
        if (!file.exists()) {
            Toast.makeText(OkHttpDemoActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "张鸿洋");
        params.put("password", "123");

        String url = mBaseUrl + "user!uploadFile";
        OkHttpUtil.post()//
                .addFile("mFile", "messenger_01.png", file)//
                .addFile("mFile", "test1.txt", file2)//
                .url(url)
                .params(params)//
                .build()//
                .execute(new MyStringCallback());
    }

    public void downloadFile(View view) {
        String url = "https://github.com/hongyangAndroid/okhttp-utils/blob/master/gson-2.2.1.jar?raw=true";
        OkHttpUtil//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment
                        .getExternalStorageDirectory()
                        .getAbsolutePath(), "gson-2.2.1.jar") {//

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void inProgress(float progress) {
                        pbLoading.setProgress((int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file) {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        OkHttpUtils.cancelTag(this);
    }

    protected void onTaskComplete(int taskId, String result) {
        UiUtil.toast(this, taskId + " ===== " + taskId);
    }

    protected void onTaskError(Exception e) {
        UiUtil.toast(this, e.getMessage());
    }

    private static final class InnerAsyncTask implements AsyncHttpCallback {

        private WeakReference<OkHttpDemoActivity> activity = null;

        public InnerAsyncTask(OkHttpDemoActivity act) {
            super();
            this.activity = new WeakReference<OkHttpDemoActivity>(act);
        }

        @Override
        public void onTaskComplete(int taskId, String response) {
            OkHttpDemoActivity activity = this.activity.get();
            if (null == activity) {
                return;
            }
            activity.onTaskComplete(taskId, response);
        }

        @Override
        public void onTaskError(Exception e) {
            OkHttpDemoActivity activity = this.activity.get();
            if (null == activity) {
                return;
            }
            activity.onTaskError(e);
        }
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            setTitle("loading...");
        }

        @Override
        public void onAfter() {
            super.onAfter();
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e) {
            tvResult.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response) {
            tvResult.setText("onResponse:" + response);
        }

        @Override
        public void inProgress(float progress) {
            Log.e(TAG, "inProgress:" + progress);
            pbLoading.setProgress((int) (100 * progress));
        }
    }
}
