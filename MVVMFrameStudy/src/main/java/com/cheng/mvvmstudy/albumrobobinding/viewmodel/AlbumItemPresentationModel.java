package com.cheng.mvvmstudy.albumrobobinding.viewmodel;

import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * @author Robert Taylor
 * @since 1.0
 */
public class AlbumItemPresentationModel implements ItemPresentationModel<Album> {
    protected Album album;

    public String getTitle() {
        return album.getTitle();
    }

    public String getArtist() {
        return album.getArtist();
    }

    @Override
    public void updateData(Album bean, ItemContext itemContext) {
        this.album = bean;
    }
}
