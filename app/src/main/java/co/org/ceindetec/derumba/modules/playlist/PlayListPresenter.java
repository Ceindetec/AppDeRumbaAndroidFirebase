package co.org.ceindetec.derumba.modules.playlist;

import co.org.ceindetec.derumba.modules.playlist.events.PlayListEvent;

/**
 * Created by Ceindetec02 on 18/07/2016.
 */
public interface PlayListPresenter {

    void datosDummy();

    void onCreate();

    void onDestroy();

    void onPause();

    void onResume();

    void signOff();

    String getCurrentUserEmail();

    String getCurrentUserName();

    void loadPlayList(int idEstablecimiento);

    void onEventMainThread(PlayListEvent event);

}
