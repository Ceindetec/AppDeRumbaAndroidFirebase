package co.org.ceindetec.derumba.modules.playlist;

import co.org.ceindetec.derumba.entities.User;
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
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void signOff() {
        playListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        playListInteractor.unsubscribe();
        playListInteractor.destroyListener();
        playListSessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return playListSessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void loadPlayList(int idEstablecimiento) {

    }

    @Override
    public void onEventMainThread(PlayListEvent event) {

    }
}
