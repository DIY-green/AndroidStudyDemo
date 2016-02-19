package com.cheng.mvvmstudy;

import android.app.Application;
import android.view.View;

import com.cheng.mvvmstudy.albumrobobinding.ViewBindingForView;
import com.cheng.mvvmstudy.albumrobobinding.api.i.IAlbumStore;
import com.cheng.mvvmstudy.albumrobobinding.api.impl.MemoryAlbumStore;
import com.cheng.mvvmstudy.albumrobobinding.model.TestData;

import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

/**
 *
 */
public class MVVMStudyApp extends Application {
    private BinderFactory reusableBinderFactory;
    private IAlbumStore albumStore;

    @Override
    public void onCreate() {
        super.onCreate();

        reusableBinderFactory = new BinderFactoryBuilder()
                .add(new ViewBindingForView().extend(View.class))
                .build();
        albumStore = new MemoryAlbumStore();

        TestData testData = new TestData();
        testData.setUp(albumStore);
    }

    public BinderFactory getReusableBinderFactory() {
        return reusableBinderFactory;
    }

    public IAlbumStore getAlbumStore() {
        return albumStore;
    }
}
