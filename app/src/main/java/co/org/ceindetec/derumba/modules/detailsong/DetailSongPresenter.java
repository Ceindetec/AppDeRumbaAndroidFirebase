package co.org.ceindetec.derumba.modules.detailsong;

import co.org.ceindetec.derumba.modules.detailsong.events.DetailSongEvent;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public interface DetailSongPresenter {
    void onShow();

    void onDestroy();

    void getInfoSong(String codigoCancion);

    void verifyLikeSong(String codigoPlaylist, String codigoCancion);

    void rankSong(String codigoPlaylist, String codigoCancion);

    void onEventMainThread(DetailSongEvent detailSongEvent);
}
