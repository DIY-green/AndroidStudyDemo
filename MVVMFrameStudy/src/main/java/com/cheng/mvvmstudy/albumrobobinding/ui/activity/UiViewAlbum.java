package com.cheng.mvvmstudy.albumrobobinding.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.robobinding.ViewBinder;

import com.cheng.mvvmstudy.R;
import com.cheng.mvvmstudy.albumrobobinding.base.BaseAbstractUi;
import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;
import com.cheng.mvvmstudy.albumrobobinding.viewmodel.ViewAlbumPresentationModel;
import com.cheng.mvvmstudy.albumrobobinding.ui.i.IViewAlbumView;
import com.cheng.mvvmstudy.albumrobobinding.ui.dialog.DeleteAlbumDialog;

/**
 * @author Cheng Wei
 * @author Robert Taylor
 * @since 1.0
 */
public class UiViewAlbum extends BaseAbstractUi implements IViewAlbumView {
    public static final String ALBUM_ID = "album_id";

    private ViewAlbumPresentationModel presentationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        long albumId = intent.getLongExtra(ALBUM_ID, Album.NO_ID);

        if (albumId == Album.NO_ID) {
            throw new IllegalArgumentException("No album id is given");
        }

        presentationModel = new ViewAlbumPresentationModel(this, getAlbumStore(), albumId);
        ViewBinder viewBinder = createViewBinder(false);
        View contentView = viewBinder.inflateAndBind(R.layout.ui_viewalbum, presentationModel);
        setContentView(contentView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presentationModel.refresh();
    }

    @Override
    public void editAlbum(long albumId) {
        Intent intent = new Intent(this, UiCreateEditAlbum.class);
        intent.putExtra(UiCreateEditAlbum.ALBUM_ID, albumId);
        startActivity(intent);
    }

    @Override
    public void deleteAlbum(Album album) {
        DeleteAlbumDialog deleteAlbumDialog = new DeleteAlbumDialog(this, album);
        deleteAlbumDialog.show();
    }
}
