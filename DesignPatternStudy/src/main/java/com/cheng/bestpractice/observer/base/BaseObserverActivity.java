package com.cheng.bestpractice.observer.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cheng.bestpractice.observer.observer.impl.EventObserver;
import com.cheng.bestpractice.observer.observer.impl.EventSubject;

import java.lang.ref.WeakReference;

/**
 * 带有观察者模式的Activity,本质上就是观察者
 */
public abstract class BaseObserverActivity extends AppCompatActivity {

    private ActivityEventObserver mActivityEventObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEventObserver = new ActivityEventObserver(this);
        registerObserver(mActivityEventObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeObserver(mActivityEventObserver);
    }


    public void registerObserver(EventObserver observer) {
        final String[] observerEventTypes = getObserverEventType();//获取所有需要监听的业务类型
        if(observerEventTypes != null && observerEventTypes.length>0){
            final EventSubject eventSubject = EventSubject.getInstance();
            eventSubject.registerObserver(observer);

        }

    }

    public void removeObserver(EventObserver observer) {
        final String[] observerEventTypes = getObserverEventType();//获取所有需要监听的业务类型
        if(observerEventTypes != null && observerEventTypes.length>0){
            final EventSubject eventSubject = EventSubject.getInstance();
            eventSubject.removeObserver(observer);
        }
    }

    /**
     * 该方法会在具体的观察者对象中调用，可以根据事件的类型来更新对应的UI，这个方法在UI线程中被调用，
     * 所以在该方法中不能进行耗时操作，可以另外开线程
     * @param eventType 事件类型
     */
    protected abstract void onChange(String eventType);

    /**
     * 通过这个方法来告诉具体的观察者需要监听的业务类型
     * @return
     */
    protected abstract String[] getObserverEventType();

    private static class ActivityEventObserver extends EventObserver {
        //添加弱引用，防止对象不能被回收
        private final WeakReference<BaseObserverActivity> mActivity;
        public ActivityEventObserver(BaseObserverActivity activity){
            super();
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void onChange(String eventType) {
            BaseObserverActivity activity = mActivity.get();
            if(activity != null){
                activity.onChange(eventType);
            }
        }
    }


}
