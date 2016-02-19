package com.cheng.mvvmstudy.albumrobobinding.viewmodel;

import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;
import com.cheng.mvvmstudy.albumrobobinding.api.i.IAlbumStore;
import com.cheng.mvvmstudy.albumrobobinding.ui.i.IViewAlbumView;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

/**
 * @author Cheng Wei
 * @author Robert Taylor
 * @since 1.0
 */
@PresentationModel
public class ViewAlbumPresentationModel implements HasPresentationModelChangeSupport {
    private final IViewAlbumView view;
    private final IAlbumStore albumStore;
    private final long albumId;
    private final PresentationModelChangeSupport changeSupport;

    private Album album;

    public ViewAlbumPresentationModel(IViewAlbumView view, IAlbumStore albumStore, long albumId) {
        this.view = view;
        this.albumStore = albumStore;
        this.albumId = albumId;
        this.changeSupport = new PresentationModelChangeSupport(this);
    }

    public String getTitle() {
        return album.getTitle();
    }

    public String getArtist() {
        return album.getArtist();
    }

    public String getComposer() {
        return album.getComposer();
    }

    public boolean isComposerEnabled() {
        return album.isClassical();
    }

    public String getClassicalDescription() {
        return album.isClassical() ? "Classical" : "Not classical";
    }

    public void editAlbum() {
        view.editAlbum(album.getId());
    }

    public void deleteAlbum() {
        view.deleteAlbum(album);
    }

    public void refresh() {
        this.album = albumStore.get(albumId);
        changeSupport.refreshPresentationModel();
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
