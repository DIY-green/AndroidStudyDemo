package com.cheng.mvvmstudy.albumrobobinding.ui.i;

import com.cheng.mvvmstudy.albumrobobinding.model.bean.Album;

/**
 *
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public interface IViewAlbumView {

    void editAlbum(long albumId);

    void deleteAlbum(Album album);

}
