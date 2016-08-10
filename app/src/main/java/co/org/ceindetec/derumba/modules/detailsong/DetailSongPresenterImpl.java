package co.org.ceindetec.derumba.modules.detailsong;

import org.greenrobot.eventbus.Subscribe;

import co.org.ceindetec.derumba.entities.Song;
import co.org.ceindetec.derumba.lib.EventBus;
import co.org.ceindetec.derumba.lib.GreenRobotEventBus;
import co.org.ceindetec.derumba.modules.detailsong.events.DetailSongEvent;
import co.org.ceindetec.derumba.modules.detailsong.ui.DetailSongView;

/**
 * Created by Ceindetec02 on 21/07/2016.
 */
public class DetailSongPresenterImpl implements DetailSongPresenter {


    private EventBus eventBus;
    private DetailSongView detailSongView;
    private DetailSongInteractor detailSongInteractor;

    public DetailSongPresenterImpl(DetailSongView detailSongView) {
        this.detailSongView = detailSongView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.detailSongInteractor = new DetailSongInteractorImpl();
    }

    /**
     * Metodo implementado de la interface DetailSongPresenter que registra el bus de eventos
     */
    @Override
    public void onShow() {
        eventBus.register(this);
    }

    /**
     * Metodo implementado de la interface DetailSongPresenter que deregistra el bus de eventos
     */
    @Override
    public void onDestroy() {
        detailSongView = null;
        eventBus.unregister(this);
    }

    /**
     * Metodo implementado de la interface DetailSongPresenter que obtiene la información de la canción
     */
    @Override
    public void getInfoSong(String codigoCancion) {
        detailSongView.showProgress();
        detailSongInteractor.getInfoSong(codigoCancion);
    }

    /**
     * @param codigoCancion
     */
    @Override
    public void rankSong(String codigoPlaylist, String codigoCancion) {
        detailSongView.showProgress();
        detailSongInteractor.rankSong(codigoPlaylist, codigoCancion);
    }

    /**
     * @param detailSongEvent
     */
    @Override
    @Subscribe
    public void onEventMainThread(DetailSongEvent detailSongEvent) {
        Song song = detailSongEvent.getInfoSong();
        switch (detailSongEvent.getEventType()) {
            case DetailSongEvent.onGetInfoSongSuccess:
                onGetInfoSongSuccess(song);
                break;
            case DetailSongEvent.onGetInfoSongError:
                onGetInfoSongError();
                break;
            case DetailSongEvent.onRankSongSuccess:
                onRankSongSuccess();
                break;
            case DetailSongEvent.onRankSongError:
                onRankSongError();
                break;
        }
    }

    /**
     * @param song
     */
    private void onGetInfoSongSuccess(Song song) {
        detailSongView.hideProgress();
        detailSongView.getInfoSongSuccess(song);
    }

    /**
     * Metodo
     */
    private void onGetInfoSongError() {
        detailSongView.hideProgress();
        detailSongView.getInfoSongError();
    }

    /**
     *
     */
    private void onRankSongSuccess() {
        detailSongView.hideProgress();
        detailSongView.rankSongSuccess();
    }

    /**
     *
     */
    private void onRankSongError() {
        detailSongView.hideProgress();
        detailSongView.rankSongError();
    }


}
