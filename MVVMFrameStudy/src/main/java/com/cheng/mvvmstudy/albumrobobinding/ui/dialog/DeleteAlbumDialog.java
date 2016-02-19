package com.cheng.mvvmstudy.albumrobobinding.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;

import com.cheng.mvvmstudy.MVVMStudyApp;
import com.cheng.mvvmstudy.R;
import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;
import com.cheng.mvvmstudy.albumrobobinding.ui.i.IDeleteAlbumView;
import com.cheng.mvvmstudy.albumrobobinding.viewmodel.DeleteAlbumPresentationModel;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;

/**
 * @author Robert Taylor
 * @author Cheng Wei
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class DeleteAlbumDialog extends Dialog implements IDeleteAlbumView {
    private final Activity activity;

    public DeleteAlbumDialog(Activity activity, Album album) {
        super(activity);
        this.activity = activity;
        setCancelable(true);
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                navigateToAlbums();
            }
        });

        DeleteAlbumPresentationModel deleteAlbumDialogPresentationModel = new DeleteAlbumPresentationModel(
                this, getAlbumApp().getAlbumStore(), album);
        setTitle(R.string.delete_album);
        initializeContentView(R.layout.ui_deletealbumdialog, deleteAlbumDialogPresentationModel);
    }

    private void initializeContentView(int layoutId, Object presentationModel) {
        BinderFactory binderFactory = getAlbumApp().getReusableBinderFactory();
        ViewBinder viewBinder = binderFactory.createViewBinder(getContext());
        View rootView = viewBinder.inflateAndBind(layoutId, presentationModel);
        setContentView(rootView);
    }

    private MVVMStudyApp getAlbumApp() {
        return (MVVMStudyApp) getContext().getApplicationContext();
    }

    @Override
    public void deleted() {
        navigateToAlbums();
    }

    private void navigateToAlbums() {
        dismiss();
        activity.finish();
    }

    @Override
    public void cancelOperation() {
        dismiss();
    }

}
