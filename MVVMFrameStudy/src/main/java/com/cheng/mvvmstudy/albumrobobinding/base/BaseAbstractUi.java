package com.cheng.mvvmstudy.albumrobobinding.base;

import android.view.View;

import com.cheng.base.BaseUi;
import com.cheng.mvvmstudy.MVVMStudyApp;
import com.cheng.mvvmstudy.albumrobobinding.api.i.IAlbumStore;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;

/**
 * @author Cheng Wei
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public abstract class BaseAbstractUi extends BaseUi {

    public void initializeContentView(int layoutId, Object presentationModel) {
        ViewBinder viewBinder = createViewBinder(true);
        View rootView = viewBinder.inflateAndBind(layoutId, presentationModel);
        setContentView(rootView);
    }

    protected ViewBinder createViewBinder(boolean withPreInitializingViews) {
        BinderFactory binderFactory = getAlbumApp().getReusableBinderFactory();
        return binderFactory.createViewBinder(this, withPreInitializingViews);
    }

    private MVVMStudyApp getAlbumApp() {
        return (MVVMStudyApp) getApplicationContext();
    }

    public IAlbumStore getAlbumStore() {
        return getAlbumApp().getAlbumStore();
    }
}
