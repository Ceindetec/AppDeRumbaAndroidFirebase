package co.org.ceindetec.derumba.modules.playlist;

/**
 * Created by avalo.
 */
public interface PlayListSessionInteractor {

    void signOff();

    String getCurrentUserEmail();

    void changeConnectionStatus(boolean online);
}
