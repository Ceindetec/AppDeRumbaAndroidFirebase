package co.org.ceindetec.derumba.modules.playlist;

/**
 * Created by avalo.
 */
public interface PlayListSessionInteractor {

    void signOff();

    String getCurrentUserEmail();

    String getCurrentUserName();

    void changeConnectionStatus(boolean online);
}
