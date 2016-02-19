package com.cheng.mvvmstudy.albumrobobinding.viewmodel;

import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;
import com.cheng.mvvmstudy.albumrobobinding.api.i.IAlbumStore;
import com.cheng.mvvmstudy.albumrobobinding.ui.i.ICreateEditAlbumView;

import org.robobinding.annotation.DependsOnStateOf;
import org.robobinding.annotation.PresentationModel;

/**
 * @author Cheng Wei
 * @author Robert Taylor
 * @since 1.0
 */
@PresentationModel
public class CreateEditAlbumPresentationModel {
    private static final String CLASSICAL = "classical";

    private final ICreateEditAlbumView view;
    private final IAlbumStore albumStore;
    private final Album.Builder albumBuilder;

    public CreateEditAlbumPresentationModel(ICreateEditAlbumView view, IAlbumStore albumStore,
                                            Album.Builder albumBuilder) {
        this.view = view;
        this.albumStore = albumStore;
        this.albumBuilder = albumBuilder;
    }

    public void save() {
        albumStore.save(albumBuilder.create());
        view.finishActivity();
    }

    public String getTitle() {
        return albumBuilder.getTitle();
    }

    public void setTitle(String title) {
        albumBuilder.setTitle(title);
    }

    public String getArtist() {
        return albumBuilder.getArtist();
    }

    public void setArtist(String artist) {
        albumBuilder.setArtist(artist);
    }

    public boolean isClassical() {
        return albumBuilder.isClassical();
    }

    public void setClassical(boolean classical) {
        albumBuilder.setClassical(classical);
    }

    @DependsOnStateOf(CLASSICAL)
    public boolean isComposerEnabled() {
        return isClassical();
    }

    public String getComposer() {
        return albumBuilder.getComposer();
    }

    public void setComposer(String composer) {
        albumBuilder.setComposer(composer);
    }

    @DependsOnStateOf(CLASSICAL)
    public String getWindowTitle() {
        if (albumBuilder.isNew())
            return view.getCreateAlbumTitle();

        return isClassical() ? "Edit Classical Album" : "Edit Album";
    }
}
