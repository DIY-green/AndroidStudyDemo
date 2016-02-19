package com.cheng.highqualitycodestudy.memoryleakhandle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.cheng.base.BaseUi;
import com.cheng.highqualitycodestudy.R;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class UiHowToUseHandler extends BaseUi {

    private TextView mTestTV;
    private GoodMyHandler mGoodMyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_howtousehandler);

        this.mTestTV = findViewByID(R.id.sdi_test_tv);
        this.mGoodMyHandler = new GoodMyHandler();
    }

    /**
     * 参考：
     * http://blog.csdn.net/FeeLang/article/details/39059705
     */

    /******************************************************
     *                  BadCode                           *
     ******************************************************/
    /**
     * 错误的代码示例：
     */
    /**
     * 因为Runnable不是static类型，所以会有一个包含Activity实例的implicit reference --- Activity.this。
     * 如果Activity在runnable变量run之前（10s内）被finish掉了但是Activity.this仍然存在，
     * 那么Activity的对象就不会被GC回收，从而导致memory leak。
     */
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // ... do some work
        }
    };
    /**
     * 即使使用一个静态内部类，也不能保证万事大吉。
     * 假设在runnable执行之前，View被移除了，但是成员变量view还在继续引用它，仍然会导致memory leak。
     */
    static class BadMyRunnable implements Runnable {
        private View view;
        public BadMyRunnable(View v) {
            this.view = v;
        }

        @Override
        public void run() {
            // ... do some work
        }
    }

    /**
     * 上面的两个例子当中，导致内存泄露的两种用法分别是隐式引用（implicit reference）
     * 和 显式引用（explicit reference）。
     *
     * 解决方法
     * 解决隐式引用的方法比较简单，只要使用内部非静态类（non-static inner class）或者
     * top-level class（在一个独立的java文件中定义的变量）就可以将隐式变为显式，从而避免内存泄露。
     * 如果继续使用非静态内部类，那么就要在onPause的时候手动结束那些挂起的任务（pending task）。
     * 解决第二个问题要用到WeakReference，WeakReference的用法可以google一下，
     * 简而言之就是：只要还有其他的stronger reference，WeakReference就可以继续引用。
     */
    private void badCodeDemo() {
        new Handler().postDelayed(runnable, TimeUnit.SECONDS.toMillis(10));
    }


    /******************************************************
     *                 GoodCode                           *
     ******************************************************/
    /**
     * 使用WeakReference
     * 这样一来问题就解决了，美中不足的是每次使用view之前都要做空指针判断。
     */
    static class GoodMyRunnable implements Runnable {
        private WeakReference<View> view;
        public GoodMyRunnable(View v) {
            this.view = new WeakReference<View>(v);
        }

        @Override
        public void run() {
            View v = view.get();
            if (v == null) return;
            // ... do something with the view
        }
    }
    /**
     * 另外一个比较高效的方法就是在onResume中为runnable的view赋值，在onPause中赋值为null。
     */
    private static class GoodMyHandler extends Handler {
        private TextView textView;
        public void attach(TextView v) {
            this.textView = v;
        }
        public void detach() {
            this.textView = null;
        }

        @Override
        public void handleMessage(Message msg) {
            // ...
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoodMyHandler.attach(mTestTV);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoodMyHandler.detach();
    }

    /**
     * 总结
     在继承Handler或者HandlerThread的时候，
     尽量定义一个static类或者top-level类。
     如果用到了ui元素，一定要在Activity的生命周期接触之前释放掉。
     */
}
