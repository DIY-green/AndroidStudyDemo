package com.cheng.mvvmstudy.albumrobobinding.api.i;

import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;

import java.util.List;

/**
 * 
 * @since 1.0
 * @author Cheng Wei
 * @author Robert Taylor
 */
public interface IAlbumStore {
    Album get(long albumId);

    Album getByIndex(int position);

    List<Album> getAll();

    void save(Album album);

    void delete(Album album);

    void clear();
}
