package co.org.ceindetec.derumba.modules.detailsong;

import org.greenrobot.eventbus.Subscribe;

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

    @Override
    public void songRanked(int idEstablecimiento, int idCancion) {

    }

    @Override
    @Subscribe
    public void onEventMainThread(DetailSongEvent detailSongEvent) {
        if (detailSongView != null) {
            detailSongView.hideProgress();
            detailSongView.showInput();

            if (detailSongEvent.isError()) {
                detailSongView.SongNoRanked();
            } else {
                detailSongView.songRanked();
            }

        }
    }
}
