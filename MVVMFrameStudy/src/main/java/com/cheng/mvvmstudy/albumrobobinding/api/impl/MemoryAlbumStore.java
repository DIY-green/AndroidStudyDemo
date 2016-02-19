package com.cheng.mvvmstudy.albumrobobinding.api.impl;

import com.cheng.mvvmstudy.albumrobobinding.api.i.IAlbumStore;
import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Cheng Wei
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class MemoryAlbumStore implements IAlbumStore {
    private List<Album> albums;

    public MemoryAlbumStore() {
        albums = new ArrayList<Album>();
    }

    @Override
    public Album get(long albumId) {
        for (Album album : albums) {
            if (album.getId() == albumId)
                return album;
        }

        throw new IllegalArgumentException("No such album for id: " + albumId);
    }

    @Override
    public Album getByIndex(int position) {
        return albums.get(position);
    }

    @Override
    public List<Album> getAll() {
        return Collections.unmodifiableList(albums);
    }

    @Override
    public void save(Album album) {
        if (album.isNew()) {
            album.setId(nextId());
            albums.add(album);
        }

        int index = albums.indexOf(album);
        albums.remove(index);
        albums.add(index, album);
    }

    private long nextId() {
        return albums.size() + 1;
    }

    @Override
    public void delete(Album album) {
        albums.remove(album);
    }

    @Override
    public void clear() {
        albums.clear();
    }
}
