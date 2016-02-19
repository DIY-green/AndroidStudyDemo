package com.cheng.mvvmstudy.albumrobobinding.viewmodel;

import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;
import com.cheng.mvvmstudy.albumrobobinding.api.i.IAlbumStore;
import com.cheng.mvvmstudy.albumrobobinding.ui.i.IDeleteAlbumView;

import org.robobinding.annotation.PresentationModel;

/**
 * @author Robert Taylor
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@PresentationModel
public class DeleteAlbumPresentationModel {
    private final IDeleteAlbumView view;
    private final IAlbumStore albumStore;
    private final Album album;

    public DeleteAlbumPresentationModel(IDeleteAlbumView view, IAlbumStore albumStore, Album album) {
        this.view = view;
        this.albumStore = albumStore;
        this.album = album;
    }

    public void deleteAlbum() {
        albumStore.delete(album);
        view.deleted();
    }

    public void dismissDialog() {
        view.cancelOperation();
    }

    public String getAlbumTitle() {
        return album.getTitle();
    }

    public String getAlbumArtist() {
        return album.getArtist();
    }
}
