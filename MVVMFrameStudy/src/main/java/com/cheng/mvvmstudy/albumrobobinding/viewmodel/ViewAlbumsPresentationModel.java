package com.cheng.mvvmstudy.albumrobobinding.viewmodel;

import android.util.Log;

import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;
import com.cheng.mvvmstudy.albumrobobinding.api.i.IAlbumStore;
import com.cheng.mvvmstudy.albumrobobinding.ui.i.IViewAlbumsView;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.adapterview.ItemClickEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cheng Wei
 * @author Robert Taylor
 * @since 1.0
 */
@PresentationModel
public class ViewAlbumsPresentationModel implements HasPresentationModelChangeSupport {

    private final IViewAlbumsView view;
    private final IAlbumStore albumStore;
    private final PresentationModelChangeSupport changeSupport;

    public ViewAlbumsPresentationModel(IViewAlbumsView view, IAlbumStore albumStore) {
        this.view = view;
        this.albumStore = albumStore;
        this.changeSupport = new PresentationModelChangeSupport(this);
    }

    @ItemPresentationModel(AlbumItemPresentationModel.class)
    public List<Album> getAlbums() {
        Log.d(ViewAlbumsPresentationModel.class.getSimpleName(), "in getAlbums():" + albumStore.getAll().size() + " albums");
        return new ArrayList<Album>(albumStore.getAll());
    }

    public void refreshAlbums() {
        changeSupport.firePropertyChange("albums");
    }

    public void createAlbum() {
        view.createAlbum();
    }

    public void viewAlbum(ItemClickEvent event) {
        viewAlbum(event.getPosition());
    }

    private void viewAlbum(int selectedAlbumPosition) {
        Album album = albumStore.getByIndex(selectedAlbumPosition);
        view.viewAlbum(album.getId());
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
