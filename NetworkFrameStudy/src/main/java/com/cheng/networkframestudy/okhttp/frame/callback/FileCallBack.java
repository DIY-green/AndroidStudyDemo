package com.cheng.networkframestudy.okhttp.frame.callback;

import android.util.Log;

import com.cheng.networkframestudy.okhttp.frame.OkHttpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * 李旺成
 * 2016年2月20日08:14:37
 */
public abstract class FileCallBack extends Callback<File> {

    private String mDestFileDir;                                 // 目标文件存储的文件夹路径
    private String mDestFileName;                                // 目标文件存储的文件名

    public abstract void inProgress(float progress);

    public FileCallBack(String destFileDir, String destFileName) {
        this.mDestFileDir = destFileDir;
        this.mDestFileName = destFileName;
    }

    @Override
    public File parseNetworkResponse(Response response) throws Exception {
        return saveFile(response);
    }

    public File saveFile(Response response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;

            Log.e("FileCallBack", total + "");

            File dir = new File(mDestFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, mDestFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                OkHttpUtil.getInstance().getDelivery().post(new Runnable() {
                    @Override
                    public void run() {

                        inProgress(finalSum * 1.0f / total);
                    }
                });
            }
            fos.flush();
            return file;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }
    }


}
