package com.cheng.mvvmstudy.albumrobobinding.ui.activity;

import android.os.Bundle;

import com.cheng.mvvmstudy.R;
import com.cheng.mvvmstudy.albumrobobinding.base.BaseAbstractUi;
import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;
import com.cheng.mvvmstudy.albumrobobinding.viewmodel.CreateEditAlbumPresentationModel;
import com.cheng.mvvmstudy.albumrobobinding.ui.i.ICreateEditAlbumView;

/**
 * @author Cheng Wei
 * @author Robert Taylor
 * @since 1.0
 */
public class UiCreateEditAlbum extends BaseAbstractUi implements ICreateEditAlbumView {

    public static final String ALBUM_ID = UiViewAlbum.ALBUM_ID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long albumId = getIntent().getLongExtra(ALBUM_ID, Album.NO_ID);

        Album.Builder albumBuilder;
        if (Album.isNew(albumId))
            albumBuilder = new Album.Builder();
        else {
            Album album = getAlbumStore().get(albumId);
            albumBuilder = album.createBuilder();
        }


        CreateEditAlbumPresentationModel presentationModel = new CreateEditAlbumPresentationModel(this, getAlbumStore(), albumBuilder);
        initializeContentView(R.layout.ui_createeditalbum, presentationModel);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public String getCreateAlbumTitle() {
        return getString(R.string.create_album);
    }
}