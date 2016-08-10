package co.org.ceindetec.derumba.modules.playlist;

import org.greenrobot.eventbus.Subscribe;

import co.org.ceindetec.derumba.entities.PlaylistSong;
import co.org.ceindetec.derumba.lib.EventBus;
import co.org.ceindetec.derumba.lib.GreenRobotEventBus;
import co.org.ceindetec.derumba.modules.playlist.events.PlayListEvent;
import co.org.ceindetec.derumba.modules.playlist.ui.PlayListView;

/**
 * Created by Ceindetec02 on 18/07/2016.
 */
public class PlayListPresenterImpl implements PlayListPresenter {

    //Instanciamiento del EventBus
    private EventBus eventBus;

    //Instanciamiento del PlayListView
    private PlayListView playListView;

    //Instanciamiento del PlayListInteractor
    private PlayListInteractor playListInteractor;

    //Instanciamiento del PlayListSessionInteractor
    private PlayListSessionInteractor playListSessionInteractor;

    /**
     * Constructor de la clase
     *
     * @param playListView
     */
    public PlayListPresenterImpl(PlayListView playListView) {

        //Inicializacion de la vista
        this.playListView = playListView;

        //Inicializacion del interactuador
        this.playListInteractor = new PlayListInteractorImpl();
        this.playListSessionInteractor = new PlayListSessionInteractorImpl();

        //Llamado al Singleton que obtiene la instancia del Bus de eventos
        eventBus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void datosDummy() {
        playListInteractor.datosDummy();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
        playListInteractor.subscribe();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        playListInteractor.destroyListener();
        playListView = null;
    }


    @Override
    public void onPause() {
        playListInteractor.unsubscribe();
    }

    @Override
    public void onResume() {
        playListInteractor.subscribe();
    }

    @Override
    public void signOff() {
        playListInteractor.unsubscribe();
        playListInteractor.destroyListener();
        playListSessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return playListSessionInteractor.getCurrentUserEmail();
    }

    @Override
    public String getCurrentUserName() {
        return playListSessionInteractor.getCurrentUserName();
    }

    @Override
    public void loadPlayList(int idEstablecimiento) {

    }

    @Override
    @Subscribe
    public void onEventMainThread(PlayListEvent playListEvent) {
        PlaylistSong songPlaylist = playListEvent.getSongPlayList();
        switch (playListEvent.getEventType()) {
            case PlayListEvent.onSongPlaylistAdded:
                onSongAdded(songPlaylist);
                break;
            case PlayListEvent.onSongPlaylistChanged:
                onSongChanged(songPlaylist);
                break;
            case PlayListEvent.onSongPlaylistRemoved:
                onSongRemoved(songPlaylist);
                break;
        }
    }

    private void onSongAdded(PlaylistSong songPlaylist) {
        if (playListView != null) {
            playListView.onSongAdded(songPlaylist);
        }
    }

    private void onSongChanged(PlaylistSong songPlaylist) {
        if (playListView != null) {
            playListView.onSongChanged(songPlaylist);
        }
    }

    private void onSongRemoved(PlaylistSong songPlaylist) {
        if (playListView != null) {
            playListView.onSongRemoved(songPlaylist);
        }
    }

}
