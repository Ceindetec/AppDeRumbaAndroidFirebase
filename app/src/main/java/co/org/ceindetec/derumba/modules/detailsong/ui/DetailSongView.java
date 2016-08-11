package co.org.ceindetec.derumba.modules.detailsong.ui;

import co.org.ceindetec.derumba.entities.Song;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public interface DetailSongView {

    void getInfoSongSuccess(Song infoSong);

    void getInfoSongError();

    void rankSongSuccess();

    void rankSongError();

    void verifyUserLike(boolean likeExist);

    void showProgress();

    void hideProgress();


}
