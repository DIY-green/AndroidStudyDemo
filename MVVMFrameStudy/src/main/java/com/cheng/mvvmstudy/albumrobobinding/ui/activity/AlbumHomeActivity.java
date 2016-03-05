package com.cheng.mvvmstudy.albumrobobinding.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.cheng.mvvmstudy.R;
import com.cheng.mvvmstudy.albumrobobinding.base.BaseAbstractUi;
import com.cheng.mvvmstudy.albumrobobinding.viewmodel.HomePresentationModel;
import com.cheng.mvvmstudy.albumrobobinding.ui.i.IHomeView;

/**
 * @author Cheng Wei
 * @author Robert Taylor
 * @since 1.0
 */
public class AlbumHomeActivity extends BaseAbstractUi implements IHomeView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomePresentationModel presentationModel = new HomePresentationModel(this);
        initializeContentView(R.layout.activity_albumhome, presentationModel);
    }

    @Override
    public void showAlbums() {
        startActivity(new Intent(this, ViewAlbumsActivity.class));
    }
}
