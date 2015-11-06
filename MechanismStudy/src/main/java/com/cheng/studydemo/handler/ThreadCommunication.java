package com.cheng.studydemo.handler;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 线程间通信
 */
public class ThreadCommunication {

    private Handler mainHandler = new InnerHandler(Looper.getMainLooper());
    private Handler childHandler;
    private Thread childThread;
    private AlertDialog alertDialog;

    private static ThreadCommunication instance = null;

    private ThreadCommunication(Context context) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Test");
        alertDialog.setMessage("TestHandler");
        initChildHandler(context);
    }

    public static ThreadCommunication getInstance(Context context) {
        if (instance == null) instance = new ThreadCommunication(context);
        return instance;
    }

    public void mainToChildTest() {
        Message msg = Message.obtain();
        msg.arg1 = 111;
        msg.obj = "mainToChildTest()";
        msg.setTarget(mainHandler);
        childHandler.sendMessage(msg);
        childHandler.obtainMessage(0, 112, 0, "mainToChildTest()").sendToTarget();
    }

    public void childToMainTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.arg1 = 222;
                msg.obj = "childToMainTest()";
                mainHandler.sendMessage(msg);
            }
        }).start();
    }

    private void initChildHandler(final Context context) {
        childThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                createChildThreadHandler(context);
                createMainThreadHandler(context);
            }
        });
        childThread.start();
    }

    private void createChildThreadHandler(final Context context) {
        Looper.prepare();
        childHandler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    Log.e("threadHandler", "CurrentThread :" + Thread.currentThread().getName() + "");
                    Log.e("threadHandler", "==>Message.arg1:" + msg.arg1 + "====Message.obj:" + msg.obj);
                    Toast.makeText(context.getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                    alertDialog.show();
                }
            };
        Looper.loop();
    }

    private void createMainThreadHandler(final Context context) {
        childHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.e("threadHandler", "CurrentThread :" + Thread.currentThread().getName() + "");
                Log.e("threadHandler", "==>Message.arg1:" + msg.arg1 + "====Message.obj:" + msg.obj);
                Toast.makeText(context.getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                alertDialog.show();
            }
        };
    }

    private static final class InnerHandler extends Handler {
        public InnerHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("mainHandler", "CurrentThread :" + Thread.currentThread().getName() + "");
            Log.e("mainHandler","==>Message.arg1:" + msg.arg1 + "====Message.obj:" + msg.obj);
        }
    }
}
