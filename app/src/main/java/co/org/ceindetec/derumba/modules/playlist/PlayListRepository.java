package co.org.ceindetec.derumba.modules.playlist;

/**
 * Created by Ceindetec02 on 18/07/2016.
 */
public interface PlayListRepository {

    //Metodos utilizados por el PlayListSessionInteractor

    void signOff();

    String getCurrentUserEmail();

    String getCurrentUserName();

    void changeConnectionStatus(boolean online);

    //Metodos utilizados por PlayListInteractor

    void datosDummy();

    void subscribeToPlayListEvents();

    void unsubscribeToPlayListEvents();

    void destroyListener();

    void loadPlayList(int idEstablecimiento);

}
