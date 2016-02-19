package com.cheng.mvvmstudy.albumrobobinding.viewmodel;

import com.cheng.mvvmstudy.albumrobobinding.ui.i.IHomeView;

import org.robobinding.annotation.PresentationModel;

/**
 * @author Cheng Wei
 * @author Robert Taylor
 * @since 1.0
 */
@PresentationModel
public class HomePresentationModel {
    private final IHomeView view;

    public HomePresentationModel(IHomeView view) {
        this.view = view;
    }

    public void albums() {
        view.showAlbums();
    }
}
