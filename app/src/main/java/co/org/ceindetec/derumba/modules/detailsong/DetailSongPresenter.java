package co.org.ceindetec.derumba.modules.detailsong;

import co.org.ceindetec.derumba.modules.detailsong.events.DetailSongEvent;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public interface DetailSongPresenter {
    void onShow();

    void onDestroy();

    void songRanked(int idEstablecimiento, int idCancion);

    void onEventMainThread(DetailSongEvent detailSongEvent);
}
